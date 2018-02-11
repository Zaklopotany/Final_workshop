package pl.coderslab.final_project.entity.user;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Entity
@Data
public class SubscriptionContent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private UserSubscription subscription;
	private Date created;
	private Date sendData;
	private String content;
	private boolean sent; //1 - sent 0 - waiting
}
