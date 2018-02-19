package pl.coderslab.final_project.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.GroupBet;
import pl.coderslab.final_project.entity.user.User;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.GroupBetRepository;
import pl.coderslab.final_project.repository.UserRepository;

@Service
public class GroupBetServiceImpl implements GroupBetService {

	private BetRepository betRepo;
	private UserRepository userRepo;
	private GroupBetRepository groupBetRepo;

	GroupBetServiceImpl(BetRepository betRepo, UserRepository userRepo, GroupBetRepository groupBetRepo) {
		this.betRepo = betRepo;
		this.userRepo = userRepo;
		this.groupBetRepo = groupBetRepo;
	}

	@Override
	public void createNewGroupBet(Long groupMatchId) throws Exception {
		Long id = ((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
		User creator = userRepo.findOne(id);
		Bet bet = betRepo.findOne(groupMatchId);
		if (bet.isGroupAvailable() == false || bet.isActive() == false) {
			throw new Exception("invalid bet type");
		}

		Set<User> userGroup = new HashSet<>(Arrays.asList(creator));
		GroupBet groupBet = GroupBet.builder().active(true).bet(bet).creator(creator).users(userGroup).build();
		groupBetRepo.save(groupBet);
	}

	@Override
	public void inviteUsersToGroupBet(GroupBet groupBet) throws Exception {
		Long id = ((CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId();
		GroupBet originalBet = groupBetRepo.findOne(groupBet.getId());
		Long groupBetCreatorId = originalBet.getCreator().getId();
		if(id != groupBetCreatorId) {
			throw new Exception("Invalid user");
		}
		
		//TODO implement friend check list
		
		Set<User> originalBetUsers = originalBet.getUsers();
		originalBetUsers.addAll(groupBet.getUsers());
		originalBet.setUsers(originalBetUsers);

		groupBetRepo.save(originalBet);
	}
	
	

}
