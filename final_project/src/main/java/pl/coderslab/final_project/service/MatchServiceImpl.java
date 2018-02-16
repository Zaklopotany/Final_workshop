package pl.coderslab.final_project.service;

import org.springframework.beans.factory.annotation.Autowired;

import pl.coderslab.final_project.entity.team.Match;
import pl.coderslab.final_project.repository.BetRepository;
import pl.coderslab.final_project.repository.MatchRepository;

public class MatchServiceImpl implements MatchService{
	//indicates type of bet e.g. normal, live, group etc;
	private static final int NORMAL_BET = 0;
	
	@Autowired
	MatchRepository matchRepo;
	@Autowired
	BetRepository betRepo;
	
	
	/**
	 * close an event and mark all bets as finished
	 * they will be find by scheduled task and resolved
	 */
	@Override
	public void endMatch(Long matchId) {
		Match match = matchRepo.findOne(matchId);
		match.setOnAir(false);
		match.setActive(false);
		match.setUpComing(false);
		matchRepo.save(match);
		betRepo.findAllByMatch(match).stream().map(bet -> bet.setFinished(true)).forEach(bet -> betRepo.save(bet));
	}

	
	/**
	 * Start Match (change metch status onAir - true;
	 * and close all normal bets
	 */
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
