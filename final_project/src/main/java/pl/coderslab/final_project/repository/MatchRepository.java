package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.final_project.entity.team.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{
/**
 * This is super mega method to connect Bets with matches 
 * purpose of this method is to provide view data
 * @param category 
 * @param sport
 * @param league
 * @param batCat
 * @return list of Maches that match four above params
 */
	//TODO refactor

	@Query(value = "select distinct m.* from `match` m "
			+ "join bet b on m.id = b.match_id "
			+ "join team t1 on  m.team_home_id = t1.id " 
			+ "where t1.nation_category_id =:nation "
			+ "and t1.league_category_id =:league " 
			+ "and t1.sport_category_id =:sport "
			+ "and b.active = true"
			+" and b.bet_category_id =:betCat", nativeQuery=true)
	List<Match> getMatchByBetCategory(@Param("nation") Long nation, @Param("sport") Long sport,@Param("league") Long league,@Param("betCat") Long betCat);
	
	//TODO refactor	
	@Query(value="SELECT m.* FROM final_project.`match` m " + 
			"join team t on m.team_home_id = t.id " + 
			"where " + 
			"league_category_id =:league and " + 
			"nation_category_id =:nation and " + 
			"sport_category_id =:sport and " +
			"activ = true", nativeQuery=true)
	List<Match> getMatchesBetAvailable(@Param("nation") Long country,@Param("league") Long league,@Param("sport") Long sport);
}
