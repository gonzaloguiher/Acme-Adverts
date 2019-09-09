package services;

import java.util.Collection;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import domain.Contract;
import domain.BillboardFile;

import repositories.BillboardFileRepository;
import security.LoginService;

@Service
@Transactional
public class BillboardFileService {

	@Autowired
	private BillboardFileRepository billboardFileRepository;
	
	@Autowired
	private Validator validator;

	public BillboardFile create(Contract contract) {
		
		Assert.isTrue(LoginService.hasRole("MANAGER"));
		
		BillboardFile result = new BillboardFile();
		result.setContract(contract);
		return result;
	}
	
	public BillboardFile save(BillboardFile billboardFile) {
		
		Assert.isTrue(LoginService.hasRole("MANAGER"));
		
		return billboardFileRepository.saveAndFlush(billboardFile);
	}

	public void delete(BillboardFile billboardFile) {
		
		Assert.isTrue(LoginService.hasRole("MANAGER"));
		
		billboardFileRepository.delete(billboardFile);
		billboardFileRepository.flush();
	}
	
	public void trueDelete(BillboardFile billboardFile) {
		billboardFileRepository.delete(billboardFile);
	}
	
	public Collection<BillboardFile> findAll() {
		return billboardFileRepository.findAll();
	}

	public BillboardFile findOne(int Id) {
		return billboardFileRepository.findOne(Id);
	}
	
	public Collection<BillboardFile> getBillboardFilesByContract(Contract contract) {
		Collection<BillboardFile> billboardFiles = billboardFileRepository.findBillboardFilesByContract(contract);
		return billboardFiles;
	}
	
	public Double getAvgBillboardFilesPerContract() {
		Double res = billboardFileRepository.getAvgBillboardFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}

	public Integer getMinBillboardFilesPerContract() {
		Integer res = billboardFileRepository.getMinBillboardFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Integer getMaxBillboardFilesPerContract() {
		Integer res = billboardFileRepository.getMaxBillboardFilesPerContract();
		if (res == null)
			res = 0;
		return res;
	}

	public Double getStdevBillboardFilesPerContract() {
		Double res = billboardFileRepository.getStdevBillboardFilesPerContract();
		if (res == null)
			res = 0d;
		return res;
	}
	
	public void flush() {
		this.billboardFileRepository.flush();
	}

	public BillboardFile reconstruct(BillboardFile billboardFile, BindingResult binding) {
		
		BillboardFile result;

		if (billboardFile.getId() == 0) {
			
			result = this.create(billboardFile.getContract());
			result.setLocation(billboardFile.getLocation());
			result.setImage(billboardFile.getImage());

		} else {
			
			result = this.findOne(billboardFile.getId());
			result.setLocation(billboardFile.getLocation());
			result.setImage(billboardFile.getImage());
		}

		validator.validate(billboardFile, binding);
		if (binding.hasErrors()) {
			throw new ValidationException();
		}

		return result;
	}
	
}
