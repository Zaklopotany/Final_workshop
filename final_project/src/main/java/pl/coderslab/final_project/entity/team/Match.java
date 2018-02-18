package pl.coderslab.final_project.entity.team;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Team teamHome;
	@ManyToOne
	private Team teamAway;
	private int minute;
	@Builder.Default
	private boolean active = true; // true - can create bet to this event
	@Builder.Default
	private boolean upComing = true; // true upcoming event; false - past event
	@Builder.Default
	private boolean onAir = false; // true - match is currently running
	private Timestamp date;
	private int scoreHome;
	private int scoreAway;
	private byte betting_type; //0 - only normal, 1- only live betting, 2 - live and normal;
		
}

