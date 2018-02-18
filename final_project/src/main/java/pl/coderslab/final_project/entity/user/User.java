package pl.coderslab.final_project.entity.user;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String firstName;
	private String lastName;
	private boolean enabled;
	
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "user_role"
	, joinColumns = @JoinColumn(name = "user_id")
	, inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> role;
	

	
	
}
