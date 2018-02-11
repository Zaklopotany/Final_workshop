package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.bet.UserBet;

public interface UserBetRepository extends JpaRepository<UserBet,Long>{

}
