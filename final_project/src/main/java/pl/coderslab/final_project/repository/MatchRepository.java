package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.team.Match;

public interface MatchRepository extends JpaRepository<Match, Long>{

}
