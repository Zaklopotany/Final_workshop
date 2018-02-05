package pl.coderslab.final_project.entity;

import java.sql.Date;
import java.util.HashMap;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	
	@NotEmpty
	@Column(nullable=false, unique=true)
	private String login;
	@NotEmpty
	@Column(nullable=false, unique=true)
	@Email
	private String email;
	@Size(min=10)
	private String password;
	private Date birthDate;
	@OneToOne
	private Wallet wallet;
	
	
	
	
}
