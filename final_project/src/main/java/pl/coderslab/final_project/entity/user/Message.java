package pl.coderslab.final_project.entity.user;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;


@Entity
@Data
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NotNull
//	@UserMessageExist
	private User user;
	
	@ManyToOne
	@NotNull
	private User sender;
	@NotEmpty
	@NotNull
	@Column(nullable = false)
	private String text;
	private boolean readOrNot = false; // 0 if unread
	private LocalDateTime created;
	private boolean isVisibleToSender = true;
	private boolean isVisibleToUser = true;
}
