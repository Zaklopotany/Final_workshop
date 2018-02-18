package pl.coderslab.final_project.entity.team;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Team {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private SportCategory sportCategory;
	@ManyToOne 
	private NationCategory nationCategory;
	@ManyToOne
	private LeagueCategory leagueCategory;
	
	private int rating;	
	@Column(unique=true)
	private String name;
	
}
