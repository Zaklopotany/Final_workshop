package pl.coderslab.final_project.entity.bet;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.coderslab.final_project.entity.user.User;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupBet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne 
	private Bet bet;
	@ManyToOne
	private User creator;
	@ManyToMany
	private Set<User> users; 
	@ManyToMany
	private Set<UserBet> userBets;
	@Builder.Default
	private Timestamp created = new Timestamp(System.currentTimeMillis());
	private boolean active; //user can bet if active
	
}
