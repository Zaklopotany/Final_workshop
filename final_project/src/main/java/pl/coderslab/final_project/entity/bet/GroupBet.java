package pl.coderslab.final_project.entity.bet;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.Data;
import pl.coderslab.final_project.entity.user.User;

@Entity
@Data
public class GroupBet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ig;
	@ManyToOne 
	private Bet bet;
	@ManyToMany
	private Set<User> users; 
	@ManyToMany
	private Set<UserBet> userBets;
	private Timestamp created = new Timestamp(System.currentTimeMillis());
	private boolean active; //user can bet if active
	
}
