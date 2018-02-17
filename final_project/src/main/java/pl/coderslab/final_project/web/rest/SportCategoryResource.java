package pl.coderslab.final_project.web.rest;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.final_project.entity.team.SportCategory;
import pl.coderslab.final_project.repository.SportCategoryRepository;


@RestController
@RequestMapping("/api/sportCategory")
public class SportCategoryResource {
	
	private SportCategoryRepository sportCatRepo;
	
	SportCategoryResource( SportCategoryRepository sportCatRepo) {
		this.sportCatRepo = sportCatRepo;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public List<SportCategory> getSportCategoryId() {
		return sportCatRepo.findAll();
	}
	
	@PostMapping(path="/add")
	public void addNewSportCategory(@RequestBody SportCategory sportCategory) {
		sportCatRepo.save(sportCategory);
		
	}
}
