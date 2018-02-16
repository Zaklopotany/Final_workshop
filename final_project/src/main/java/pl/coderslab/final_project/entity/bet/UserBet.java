package pl.coderslab.final_project.entity.bet;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;

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
	private boolean active = false; // false -- bet in basket not active; after activation user cannot change;
	private boolean finished =false; // false -- bet in basket not finished; true bet in basket calculate win;
	private boolean won = false; //finished true, won true bet won. 
	private boolean groupBet = false; //false - normal bet, true - group bet
	private Timestamp created = new Timestamp(System.currentTimeMillis());
	private double money = 0.0;
	
	
	
}
