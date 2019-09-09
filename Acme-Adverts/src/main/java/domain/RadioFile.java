package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class RadioFile extends DomainEntity {

	// Attributes -----------------------------------------------------------

	private String sound;
	private String name;
	private String schedule;

	// Getters and Setters ---------------------------------------------------

	@URL
	@NotBlank
	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9] - ([01]?[0-9]|2[0-3]):[0-5][0-9]$")
	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	// Relationships ----------------------------------------------------------

	private Contract contract;

	@Valid
	@ManyToOne(optional = false)
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

}
