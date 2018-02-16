package pl.coderslab.final_project.entity.team;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
/**
 * This entity holds team matches
 * 
 * boolean active indicates whether bets can be created to this event
 * boolean upComing indicates whether it is an upcoming event or past false- past or currently running
 * boolean onAir indicates whether match is currently running
 * byte betting type indicates that explicit type of bet can be created to this match
 * 0-normal bets, 1  - only live bets , 2 - live and normal bets
 *  create new entities 
 * @author zaklopotany
 *
 */
@Entity
@Data
public class Match {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Team team_home;
	@ManyToOne
	private Team team_away;
	private int minute;
	private boolean active = true; // true - can create bet to this event
	private boolean upComing = true; // true upcoming event; false - past event
	private boolean onAir = false; // true - match is currently running
	private Timestamp date;
	private int score_home;
	private int score_away;
	private byte betting_type; //0 - only normal, 1- only live betting, 2 - live and normal;
		
}

