package pl.coderslab.final_project.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeamDTO {

	
	private String name;
	private String sport;
	private String country;
	private String league;
	private int rating;
}
