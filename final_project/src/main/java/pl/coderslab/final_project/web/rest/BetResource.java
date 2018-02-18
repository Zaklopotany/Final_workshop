package pl.coderslab.final_project.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import pl.coderslab.final_project.entity.bet.BetCategory;
import pl.coderslab.final_project.repository.BetCategoryRepository;

@RestController
@RequestMapping("/api/bets")
public class BetResource {
	
	@Autowired
	BetCategoryRepository betCategoryRepo;

	@PostMapping("/add")
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addNewBetCategory(@RequestBody BetCategory betCategory ) {
		betCategoryRepo.save(betCategory);
	}
	
	//available bets to active matches
	
	
}
