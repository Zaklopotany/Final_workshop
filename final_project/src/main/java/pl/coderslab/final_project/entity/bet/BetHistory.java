package pl.coderslab.final_project.entity.bet;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class BetHistory {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Bet bet;
	private double rating;
	private Timestamp created = new Timestamp(System.currentTimeMillis());
	
	
}
