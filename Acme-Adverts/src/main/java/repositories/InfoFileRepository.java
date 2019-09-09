package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contract;
import domain.InfoFile;

@Repository
public interface InfoFileRepository extends JpaRepository<InfoFile, Integer> {

	@Query("select i from InfoFile i where i.contract = ?1")
	Collection<InfoFile> findInfoFilesByContract(Contract c);
	
	@Query("select avg(c.infoFiles.size) from Contract c")
	Double getAvgInfoFilesPerContract();
	
	@Query("select min(c.infoFiles.size) from Contract c")
	Integer getMinInfoFilesPerContract();
	
	@Query("select max(c.infoFiles.size) from Contract c")
	Integer getMaxInfoFilesPerContract();
	
//	@Query("select stddev(c.infoFiles.size) from Contract c")
//	Double getStdevInfoFilesPerContract();
	
	@Query("select sqrt(sum(c.infoFiles.size*c.infoFiles.size))/(count(c.infoFiles.size)-(avg(c.infoFiles.size)*avg(c.infoFiles.size))) from Contract c")
	Double getStdevInfoFilesPerContract();
}