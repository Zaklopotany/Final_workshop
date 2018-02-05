package pl.coderslab.final_project.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Data
public class Invitation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private User from;
	@ManyToOne
	private User to;
	private byte subject; // Limited to preset values e.g. 1 - group bet,2 - new friend,
	@Size(max = 50)
	private String shortMsg; // short message
	private byte status; // int status 1-waiting, 2-deleted, 3-rejected, 4 - accepted etc.
	private Date created; // creation date
	private Date lastStatus;// last status change

}
