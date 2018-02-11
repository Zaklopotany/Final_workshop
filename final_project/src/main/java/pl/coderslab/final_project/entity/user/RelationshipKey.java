package pl.coderslab.final_project.entity.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class RelationshipKey implements Serializable{
	@ManyToOne
	private User user_one;
	@ManyToOne
	private User user_two;
	
}
