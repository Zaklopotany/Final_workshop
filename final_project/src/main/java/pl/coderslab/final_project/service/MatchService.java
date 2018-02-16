package pl.coderslab.final_project.service;

import pl.coderslab.final_project.entity.team.Match;

public interface MatchService {
	/**
	 * change the match status by
	 * changing boolean onAir and upcoming to false
	 * 
	 * @param matchId
	 */
	void endMatch(Long matchId);
	
	/**
	 * change match status upComing = false
	 * onAir = true
	 * @param matchId
	 */
	void startMatch(Long matchId);
}
