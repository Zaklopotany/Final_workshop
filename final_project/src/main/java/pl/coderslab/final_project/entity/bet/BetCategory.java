package pl.coderslab.final_project.entity.bet;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Entity
@Data
public class BetCategory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private double value; //additional record to specify bet e.g. Desc: Team will score more than {value}  
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<BetSubCategory> betSubCategory;
	
}
