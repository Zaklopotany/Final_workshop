package pl.coderslab.final_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.MatchRepository;

@Service
public class MatchServiceImpl implements MatchService {
	// indicates type of bet e.g. normal, live, group etc;
	private static final int NORMAL_BET = 0;

	@Autowired
	MatchRepository matchRepo;
	@Autowired
	BetRepository betRepo;

	
	@Override
	public void endMatch(Long matchId) {
		Match match = matchRepo.findOne(matchId);
		match.setOnAir(false);
		match.setActive(false);
		match.setUpComing(false);
		match.setActive(false);
		matchRepo.save(match);
		betRepo.findAllByMatch(match).stream().map(bet -> bet.setFinished(true)).forEach(bet -> betRepo.save(bet));
	}

	
	@Override
	public void startMatch(Long matchId) {
		Match match = matchRepo.findOne(matchId);
		match.setOnAir(true);
		match.setUpComing(false);
		if (match.getBetting_type() == NORMAL_BET) {
			match.setActive(false);
		}
		matchRepo.save(match);
		betRepo.findAllByMatch(match).stream().map(bet -> bet.setActive(false)).forEach(bet -> betRepo.save(bet));
	}

}
