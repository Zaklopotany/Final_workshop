package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.UserBet;

public interface UserBetRepository extends JpaRepository<UserBet,Long>{
	//TODO refactor - merge into one method 
	@Query("Select ub from UserBet ub where ub.active = false and ub.user.id =:userId")
	List<UserBet> getUserBasket(@Param("userId") Long userId);
	//TODO refactor merge into one method
	@Query("Select ub from UserBet ub where ub.active =:active and ub.user.id =:userId and ub.finished =:finished")
	List<UserBet> getUserBets(@Param("userId") Long userId,@Param("active") boolean active,@Param("finished") boolean finished);
	
	List<UserBet> findAllByBetAndActive(Bet bet, boolean active);
	List<UserBet> findAllByBet(Bet bet);
	
}
