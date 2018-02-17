package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import pl.coderslab.final_project.entity.bet.Bet;
import pl.coderslab.final_project.entity.bet.UserBet;

public interface UserBetRepository extends JpaRepository<UserBet,Long>{
	//TODO refactor

	@Query(value="select * from user_bet where active = false and user_id =:userId",nativeQuery=true)
	List<UserBet> getUserBasket(@Param("userId") Long userId);
	//TODO refactor

	@Query(value="select * from user_bet where active =:active and user_id =:userId and finished =:finished",nativeQuery=true)
	List<UserBet> getUserBets(@Param("userId") Long userId,@Param("active") boolean active,@Param("finished") boolean finished);
	
	List<UserBet> findAllByBetAndActive(Bet bet, boolean active);
	List<UserBet> findAllByBet(Bet bet);
	
}
