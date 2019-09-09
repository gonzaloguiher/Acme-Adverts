package services;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.InfoFileRepository;
import security.LoginService;
import domain.Contract;
import domain.InfoFile;

@Service
@Transactional
public class InfoFileService {

	@Autowired
	private InfoFileRepository infoFileRepository;

	@Autowired
	private Validator validator;

	public InfoFile create(Contract contract) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		InfoFile result = new InfoFile();
		result.setContract(contract);
		return result;
	}

	public InfoFile save(InfoFile infoFile) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		return infoFileRepository.saveAndFlush(infoFile);
	}

	public void delete(InfoFile infoFile) {

		Assert.isTrue(LoginService.hasRole("MANAGER"));

		infoFileRepository.delete(infoFile);
		infoFileRepository.flush();
	}

	public Collection<InfoFile> findAll() {
		return infoFileRepository.findAll();
	}

	public InfoFile findOne(int Id) {
		return infoFileRepository.findOne(Id);
	}
	
	public Collection<InfoFile> getInfoFilesByContract(Contract contract) {
		Collection<InfoFile> infoFiles = infoFileRepository.findInfoFilesByContract(contract);
		return infoFiles;
	}
	
	public Double getAvgInfoFilesPerContract() {
		Double res = infoFileRepository.getAvgInfoFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}

	public Integer getMinInfoFilesPerContract() {
		Integer res = infoFileRepository.getMinInfoFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Integer getMaxInfoFilesPerContract() {
		Integer res = infoFileRepository.getMaxInfoFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Double getStdevInfoFilesPerContract() {
		Double res = infoFileRepository.getStdevInfoFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}

	public void flush() {
		this.infoFileRepository.flush();
	}

	public InfoFile reconstruct(InfoFile infoFile, BindingResult binding) {

		InfoFile result;

		if (infoFile.getId() == 0) {

			result = this.create(infoFile.getContract());
			result.setTitle(infoFile.getTitle());
			result.setText(infoFile.getText());

		} else {

			result = this.findOne(infoFile.getId());
			result.setTitle(infoFile.getTitle());
			result.setText(infoFile.getText());
		}

		validator.validate(infoFile, binding);
		if (binding.hasErrors()) {
			throw new ValidationException();
		}

		return result;
	}
	
}
