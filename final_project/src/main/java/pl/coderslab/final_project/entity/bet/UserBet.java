package pl.coderslab.final_project.entity.bet;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import pl.coderslab.final_project.entity.user.User;
/**
 * //active :  false - bet in basket not active; after activation user cannot change;
 * //finished : false - bet not resolved;
 * //finished true, won true bet won. 
 * // groupBet: false - normal bet, true - group bet
 * 
 * @author zaklopotany
 *
 */
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
	
	
	
	private boolean active = false; 
	private boolean finished = false; 
	private boolean won = false; 
	private boolean groupBet = false; 
	private Timestamp created = new Timestamp(System.currentTimeMillis());
	private double money = 0.0;
	
	
	
}
