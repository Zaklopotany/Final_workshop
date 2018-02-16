package pl.coderslab.final_project.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MatchDTO {

	
	private Long id;
	private String teamHome;
	private String teamAway;
	private int scoreHome;
	private int scoreAway;
	
}
