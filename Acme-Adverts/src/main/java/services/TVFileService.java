package services;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.TVFileRepository;
import security.LoginService;
import domain.Contract;
import domain.TVFile;

@Service
@Transactional
public class TVFileService {

	@Autowired
	private TVFileRepository TVFileRepository;

	@Autowired
	private Validator validator;

	public TVFile create(Contract contract) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		TVFile result = new TVFile();
		result.setContract(contract);
		return result;
	}

	public TVFile save(TVFile TVFile) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		return TVFileRepository.saveAndFlush(TVFile);
	}

	public void delete(TVFile TVFile) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		TVFileRepository.delete(TVFile);
		TVFileRepository.flush();
	}

	public Collection<TVFile> findAll() {
		return TVFileRepository.findAll();
	}

	public TVFile findOne(int Id) {
		return TVFileRepository.findOne(Id);
	}
	
	public Collection<TVFile> getTVFilesByContract(Contract contract) {
		Collection<TVFile> TVFiles = TVFileRepository.findTVFileByContract(contract);
		return TVFiles;
	}
	
	public Double getAvgTVFilesPerContract() {
		Double res = TVFileRepository.getAvgTVFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}

	public Integer getMinTVFilesPerContract() {
		Integer res = TVFileRepository.getMinTVFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Integer getMaxTVFilesPerContract() {
		Integer res = TVFileRepository.getMaxTVFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Double getStdevTVFilesPerContract() {
		Double res = TVFileRepository.getStdevTVFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}

	public void flush() {
		this.TVFileRepository.flush();
	}

	public TVFile reconstruct(TVFile TVFile, BindingResult binding) {

		TVFile result;

		if (TVFile.getId() == 0) {

			result = this.create(TVFile.getContract());
			result.setVideo(TVFile.getVideo());
			result.setName(TVFile.getName());
			result.setSchedule(TVFile.getSchedule());

		} else {

			result = this.findOne(TVFile.getId());
			result.setVideo(TVFile.getVideo());
			result.setName(TVFile.getName());
			result.setSchedule(TVFile.getSchedule());
		}

		validator.validate(TVFile, binding);
		if (binding.hasErrors()) {
			throw new ValidationException();
		}

		return result;
	}
}
