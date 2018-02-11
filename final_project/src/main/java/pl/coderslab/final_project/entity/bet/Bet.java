package pl.coderslab.final_project.entity.bet;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import pl.coderslab.final_project.entity.team.Match;

@Entity
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

	private LocalDateTime created;
	private boolean groupAvailable = true; // true - groups can bet on this bet
	private boolean active = true; // 1 - bet active user can bid
}
