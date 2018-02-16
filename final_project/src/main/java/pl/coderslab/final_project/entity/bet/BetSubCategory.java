package pl.coderslab.final_project.entity.bet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BetSubCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String description;
	private byte value; //hold the specific value e.g. bet categoruy (match) , bet subcategory description Win value - 1 means home_team will win etc. 0 - tie
	
}
