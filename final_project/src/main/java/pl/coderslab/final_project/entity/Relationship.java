package pl.coderslab.final_project.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Relationship {
	//user one always lower than user two !! very important !! prevents duplicates
	@OneToOne
	@Id
	private User user_one;
	@Id
	@OneToOne
	private User user_two;
	private byte status;
	@OneToOne
	private User last_user_action;
	
}
