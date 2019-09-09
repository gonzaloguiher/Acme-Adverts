package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SocialNetworkFile extends DomainEntity {

	// Attributes -----------------------------------------------------------

	private String banner;
	private String target;

	// Getters and Setters ---------------------------------------------------

	@URL
	@NotBlank
	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	@URL
	@NotBlank
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
