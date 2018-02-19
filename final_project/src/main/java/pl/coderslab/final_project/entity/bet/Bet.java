package pl.coderslab.final_project.entity.bet;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.coderslab.final_project.entity.team.Match;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@ApiModelProperty(notes = "Database generated product ID")
	private Long id;
	@ManyToOne
	@ApiModelProperty(notes = "Match the bet refers to")
	private Match match;
	@ManyToOne
	@ApiModelProperty(notes = "Bet category e.g. match, goals")
	private BetCategory betCategory;
	@ManyToOne
	@ApiModelProperty(notes = "Applies to bet category e.g. match - home win, tie")
	private BetSubCategory betSubCategory;

	@Setter(AccessLevel.NONE)
	@Digits(fraction = 2, integer = 12)
	@ApiModelProperty(notes = "bet rating  always >1")
	private double rate; // rate of the bet, has to be > 1

	
	@ApiModelProperty(notes = "Date and time when the match will start")
	private Timestamp created;
//	private boolean groupAvailable = true; // true - groups can bet on this bet
	@Setter(AccessLevel.NONE)
	@Builder.Default
	@ApiModelProperty(notes = "Flag - when is true users can bid")
	private boolean active = true; // 1 - bet active user can bid
	@Setter(AccessLevel.NONE)
	@Builder.Default
	@ApiModelProperty(notes = "Flag - when is false game is still running, true - game has ended bet can be picked up by bet resolver ")
	private boolean finished = false; // false game is still running or waiting for acceptance, true game has finished and users bets were checked
	@Builder.Default
	@ApiModelProperty(notes = "Flag - when is false bet and all user bets connected to this bet awits to be resolved")
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
		buf.append(match.getTeamHome().getName())
		.append(" x ")
		.append(match.getTeamAway().getName())
		.append(" - ").append(betSubCategory.getDescription());
		return  buf.toString();
	}
}
