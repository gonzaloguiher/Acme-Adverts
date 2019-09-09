package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contract;
import domain.TVFile;

@Repository
public interface TVFileRepository extends JpaRepository<TVFile, Integer> {

	@Query("select t from TVFile t where t.contract = ?1")
	Collection<TVFile> findTVFileByContract(Contract c);
	
	@Query("select avg(c.TVFiles.size) from Contract c")
	Double getAvgTVFilesPerContract();
	
	@Query("select min(c.TVFiles.size) from Contract c")
	Integer getMinTVFilesPerContract();
	
	@Query("select max(c.TVFiles.size) from Contract c")
	Integer getMaxTVFilesPerContract();
	
//	@Query("select stddev(c.TVFiles.size) from Contract c")
//	Double getStdevTVFilesPerContract();
	
	@Query("select sqrt(sum(c.TVFiles.size*c.TVFiles.size))/(count(c.TVFiles.size)-(avg(c.TVFiles.size)*avg(c.TVFiles.size))) from Contract c")
	Double getStdevTVFilesPerContract();
}