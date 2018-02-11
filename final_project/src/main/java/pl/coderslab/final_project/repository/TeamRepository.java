package pl.coderslab.final_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.team.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

}
