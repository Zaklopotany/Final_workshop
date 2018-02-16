package pl.coderslab.final_project.entity.bet;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
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

	@Setter(AccessLevel.NONE)
	@Digits(fraction = 2, integer = 12)
	private double rate; // rate of the bet, has to be > 1

	private Timestamp created;
//	private boolean groupAvailable = true; // true - groups can bet on this bet
	@Setter(AccessLevel.NONE)
	private boolean active = true; // 1 - bet active user can bid
	@Setter(AccessLevel.NONE)
	private boolean finished = false; // false game is still running or waiting for something, true game has finished and users bets were checked
	private boolean resolved = false; // bet awaits to be resolved
	 
	
	public Bet setActive(boolean active) {
		this.active=active;
		return this;
	}
	
	public Bet setRate(double rate) {
		this.rate = rate;
		return this;
	}
	
	public Bet setFinished(boolean finished) {
		this.finished = finished;
		return this;
	}
	
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
