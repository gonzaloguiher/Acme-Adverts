package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.BillboardFile;
import domain.Contract;

@Repository
public interface BillboardFileRepository extends JpaRepository<BillboardFile, Integer> {
	
	@Query("select b from BillboardFile b where b.contract = ?1")
	Collection<BillboardFile> findBillboardFilesByContract(Contract c);
	
	@Query("select avg(c.billboardFiles.size) from Contract c")
	Double getAvgBillboardFilesPerContract();
	
	@Query("select min(c.billboardFiles.size) from Contract c")
	Integer getMinBillboardFilesPerContract();
	
	@Query("select max(c.billboardFiles.size) from Contract c")
	Integer getMaxBillboardFilesPerContract();
	
//	@Query("select stddev(c.billboardFiles.size) from Contract c")
//	Double getStdevBillboardFilesPerContract();
	
	@Query("select sqrt(sum(c.billboardFiles.size*c.billboardFiles.size))/(count(c.billboardFiles.size)-(avg(c.billboardFiles.size)*avg(c.billboardFiles.size))) from Contract c")
	Double getStdevBillboardFilesPerContract();
}