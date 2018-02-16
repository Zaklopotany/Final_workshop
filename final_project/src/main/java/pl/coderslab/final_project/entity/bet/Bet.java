package pl.coderslab.final_project.entity.bet;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import lombok.Data;
import pl.coderslab.final_project.entity.team.Match;

@Entity
@Data
public class Bet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Match match;
	@ManyToOne
	private BetCategory betCategory;
	@ManyToOne
	private BetSubCategory betSubCategory;

	@Digits(fraction = 2, integer = 12)
	private double rate; // rate of the bet, has to be > 1

	private Timestamp created;
	private boolean groupAvailable = true; // true - groups can bet on this bet
	private boolean active = true; // 1 - bet active user can bid
	private boolean finished = false; // false game is still running or waitting for somenthing, true game has finished and users bets were checked
	 
	
	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append(match.getTeam_home().getName())
		.append(" x ")
		.append(match.getTeam_away().getName())
		.append(" - ").append(betSubCategory.getDescription());
		return  buf.toString();
	}
}
