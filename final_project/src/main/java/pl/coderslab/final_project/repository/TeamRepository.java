package pl.coderslab.final_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.coderslab.final_project.entity.team.SportCategory;
import pl.coderslab.final_project.entity.team.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{
	Team findOneByName(String name);
	
	List<Team> findBySportCategory(SportCategory sportCategory);
	
}
