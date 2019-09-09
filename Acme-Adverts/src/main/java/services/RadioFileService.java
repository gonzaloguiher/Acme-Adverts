package services;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.RadioFileRepository;
import security.LoginService;
import domain.Contract;
import domain.RadioFile;

@Service
@Transactional
public class RadioFileService {

	@Autowired
	private RadioFileRepository radioFileRepository;

	@Autowired
	private Validator validator;

	public RadioFile create(Contract contract) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		RadioFile result = new RadioFile();
		result.setContract(contract);
		return result;
	}

	public RadioFile save(RadioFile radioFile) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		return radioFileRepository.saveAndFlush(radioFile);
	}

	public void delete(RadioFile radioFile) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		radioFileRepository.delete(radioFile);
		radioFileRepository.flush();
	}

	public Collection<RadioFile> findAll() {
		return radioFileRepository.findAll();
	}

	public RadioFile findOne(int Id) {
		return radioFileRepository.findOne(Id);
	}
	
	public Collection<RadioFile> getRadioFilesByContract(Contract contract) {
		Collection<RadioFile> radioFiles = radioFileRepository.findRadioFileByContract(contract);
		return radioFiles;
	}
	
	public Double getAvgRadioFilesPerContract() {
		Double res = radioFileRepository.getAvgRadioFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}

	public Integer getMinRadioFilesPerContract() {
		Integer res = radioFileRepository.getMinRadioFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Integer getMaxRadioFilesPerContract() {
		Integer res = radioFileRepository.getMaxRadioFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Double getStdevRadioFilesPerContract() {
		Double res = radioFileRepository.getStdevRadioFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}

	public void flush() {
		this.radioFileRepository.flush();
	}

	public RadioFile reconstruct(RadioFile radioFile, BindingResult binding) {

		RadioFile result;

		if (radioFile.getId() == 0) {

			result = this.create(radioFile.getContract());
			result.setSound(radioFile.getSound());
			result.setName(radioFile.getName());
			result.setSchedule(radioFile.getSchedule());

		} else {

			result = this.findOne(radioFile.getId());
			result.setSound(radioFile.getSound());
			result.setName(radioFile.getName());
			result.setSchedule(radioFile.getSchedule());
		}

		validator.validate(radioFile, binding);
		if (binding.hasErrors()) {
			throw new ValidationException();
		}

		return result;
	}

}