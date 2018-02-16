package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.final_project.entity.bet.Bet;

public interface BetRepository extends JpaRepository<Bet,Long>{
	//TODO refactor
	@Query(value="Select b.* from bet b " + 
			"where " + 
			"b.match_id =:match " + 
			"and " + 
			"b.bet_category_id =:betCat " + 
			"order by b.bet_sub_category_id asc",nativeQuery=true)
	List<Bet> getBetByMach(@Param("match") Long match, @Param("betCat") Long betCat);
}
