package pl.coderslab.final_project.entity.user;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;

import lombok.Data;

@Entity
@Data
public class WalletHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private Wallet wallet;
	@Digits(fraction=2, integer = 12)
	private double operationValue;
	private Timestamp created = new Timestamp(System.currentTimeMillis());
	private String operationName;
}
