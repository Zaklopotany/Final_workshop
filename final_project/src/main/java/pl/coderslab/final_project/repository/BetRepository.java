package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.bet.Bet;

public interface BetRepository extends JpaRepository<Bet,Long>{

}
