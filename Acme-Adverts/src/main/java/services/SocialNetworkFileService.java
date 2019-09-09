package services;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.SocialNetworkFileRepository;
import security.LoginService;
import domain.Contract;
import domain.SocialNetworkFile;

@Service
@Transactional
public class SocialNetworkFileService {

	@Autowired
	private SocialNetworkFileRepository socialNetworkFileRepository;

	@Autowired
	private Validator validator;

	public SocialNetworkFile create(Contract contract) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		SocialNetworkFile result = new SocialNetworkFile();
		result.setContract(contract);
		return result;
	}

	public SocialNetworkFile save(SocialNetworkFile socialNetworkFile) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		return socialNetworkFileRepository.saveAndFlush(socialNetworkFile);
	}

	public void delete(SocialNetworkFile socialNetworkFile) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		socialNetworkFileRepository.delete(socialNetworkFile);
		socialNetworkFileRepository.flush();
	}

	public Collection<SocialNetworkFile> findAll() {
		return socialNetworkFileRepository.findAll();
	}

	public SocialNetworkFile findOne(int Id) {
		return socialNetworkFileRepository.findOne(Id);
	}

	public Collection<SocialNetworkFile> getSocialNetworkFilesByContract(Contract contract) {
		Collection<SocialNetworkFile> socialNetworkFiles = socialNetworkFileRepository.findSocialNetworkFileByContract(contract);
		return socialNetworkFiles;
	}
	
	public Double getAvgSocialNetworkFilesPerContract() {
		Double res = socialNetworkFileRepository.getAvgSocialNetworkFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}

	public Integer getMinSocialNetworkFilesPerContract() {
		Integer res = socialNetworkFileRepository.getMinSocialNetworkFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Integer getMaxSocialNetworkFilesPerContract() {
		Integer res = socialNetworkFileRepository.getMaxSocialNetworkFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Double getStdevSocialNetworkFilesPerContract() {
		Double res = socialNetworkFileRepository.getStdevSocialNetworkFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}
	
	public void flush() {
		this.socialNetworkFileRepository.flush();
	}

	public SocialNetworkFile reconstruct(SocialNetworkFile socialNetworkFile, BindingResult binding) {

		SocialNetworkFile result;

		if (socialNetworkFile.getId() == 0) {

			result = this.create(socialNetworkFile.getContract());
			result.setBanner(socialNetworkFile.getBanner());
			result.setTarget(socialNetworkFile.getTarget());

		} else {

			result = this.findOne(socialNetworkFile.getId());
			result.setBanner(socialNetworkFile.getBanner());
			result.setTarget(socialNetworkFile.getTarget());
		}

		validator.validate(socialNetworkFile, binding);
		if (binding.hasErrors()) {
			throw new ValidationException();
		}

		return result;
	}
	
}
