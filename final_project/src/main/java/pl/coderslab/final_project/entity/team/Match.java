package pl.coderslab.final_project.entity.team;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

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
	private boolean activ; // true - can create bet to this event
	private boolean upComing; // ture upcoming event; false - past event
	private boolean onAir; // true - match is currently running
	private LocalDateTime date;
	private int score_home;
	private int score_away;
	private byte betting_type; //0 - only normal, 1- only live betting, 2 - live and normal;
		
}

