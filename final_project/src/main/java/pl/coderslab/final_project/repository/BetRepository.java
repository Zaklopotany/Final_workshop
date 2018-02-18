package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.team.Match;

public interface BetRepository extends JpaRepository<Bet,Long>{
	@Query("Select b from Bet b where b.match.id =:matchId and b.active =:active and b.betCategory.id =:betCatId order by b.betSubCategory.value asc")
	List<Bet> getBetByMach(@Param("matchId") Long matchId, @Param("active") boolean active, @Param("betCatId") Long betCatId);
	
	List<Bet> findAllByMatchAndActive(Match match, boolean active);
	List<Bet> findAllByMatch(Match match);
	List<Bet> findAllByFinishedAndResolved(boolean finished, boolean resolved);
}
