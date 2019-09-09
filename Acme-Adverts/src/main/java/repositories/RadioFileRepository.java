package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contract;
import domain.RadioFile;

@Repository
public interface RadioFileRepository extends JpaRepository<RadioFile, Integer> {

	@Query("select r from RadioFile r where r.contract = ?1")
	Collection<RadioFile> findRadioFileByContract(Contract c);
	
	@Query("select avg(c.radioFiles.size) from Contract c")
	Double getAvgRadioFilesPerContract();
	
	@Query("select min(c.radioFiles.size) from Contract c")
	Integer getMinRadioFilesPerContract();
	
	@Query("select max(c.radioFiles.size) from Contract c")
	Integer getMaxRadioFilesPerContract();
	
//	@Query("select stddev(c.radioFiles.size) from Contract c")
//	Double getStdevRadioFilesPerContract();
	
	@Query("select sqrt(sum(c.radioFiles.size*c.radioFiles.size))/(count(c.radioFiles.size)-(avg(c.radioFiles.size)*avg(c.radioFiles.size))) from Contract c")
	Double getStdevRadioFilesPerContract();
}