package pl.coderslab.final_project.entity.user;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;

@Entity
@Data
public class Relationship{
	
	//user one always lower than user two !! very important !! prevents duplicates
	@EmbeddedId
	private RelationshipKey key;
//	
//	@OneToOne
//	private User user_one;
//	@OneToOne
//	private User user_two;
	@OneToOne
	private User last_user_action;
	
	private byte status; //status of friendship -1 friends , 2 blocked etc
}
