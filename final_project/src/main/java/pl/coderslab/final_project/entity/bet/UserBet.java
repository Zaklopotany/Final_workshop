package pl.coderslab.final_project.entity.bet;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import pl.coderslab.final_project.entity.user.User;

@Entity
@Data
public class UserBet {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User user;
	@ManyToOne
	private Bet bet;
	@ManyToOne 
	private BetHistory betHistory;

	private boolean groupBet; //false - normal bet, true - group bet
	private LocalDateTime created;
	private double money;
	
	
	
}
