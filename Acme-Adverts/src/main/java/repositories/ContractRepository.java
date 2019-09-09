package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contract;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Integer> {

	@Query("select c from Contract c where c.pakage.id = ?1")
	Contract getContractByPackage(int id);
	
	@Query("select c from Contract c join c.pakage p where p.manager.id = ?1")
	Collection<Contract> getContractsByManager(int id);
	
	@Query("select c from Contract c join c.billboardFiles b where b.id = ?1")
	Contract findByBillboardFileId(int id);
	
	@Query("select c from Contract c join c.infoFiles i where i.id = ?1")
	Contract findByInfoFileId(int id);
	
	@Query("select c from Contract c join c.radioFiles r where r.id = ?1")
	Contract findByRadioFileId(int id);
	
	@Query("select c from Contract c join c.TVFiles t where t.id = ?1")
	Contract findByTVFileId(int id);
	
	@Query("select c from Contract c join c.socialNetworkFiles s where s.id = ?1")
	Contract findBySocialNetworkFileId(int id);
	
	@Query("select (select (count (c.billboardFiles.size) * 1.0 + (c.socialNetworkFiles.size) * 1.0 + (c.infoFiles.size) * 1.0 + (c.radioFiles.size) * 1.0 + (c.TVFiles.size) * 1.0) from Contract c)/count(c1) from Contract c1")
	Double getAvgFilesPerContract();
	
	@Query("select distinct count (c.billboardFiles.size) * 1.0 + (c.socialNetworkFiles.size) * 1.0 + (c.infoFiles.size) * 1.0 + (c.radioFiles.size) * 1.0 + (c.TVFiles.size) * 1.0 from Contract c where (select count (c1.billboardFiles.size) * 1.0 + (c1.socialNetworkFiles.size) * 1.0 + (c1.infoFiles.size) * 1.0 + (c1.radioFiles.size) * 1.0 + (c1.TVFiles.size) * 1.0 from Contract c1 where c1.id = c.id) <= all (select count (c2.billboardFiles.size) * 1.0 + (c2.socialNetworkFiles.size) * 1.0 + (c2.infoFiles.size) * 1.0 + (c2.radioFiles.size) * 1.0 + (c2.TVFiles.size) * 1.0 from Contract c2 group by c2) group by c")
	Integer getMinFilesPerContract();
	
	@Query("select distinct count (c.billboardFiles.size) * 1.0 + (c.socialNetworkFiles.size) * 1.0 + (c.infoFiles.size) * 1.0 + (c.radioFiles.size) * 1.0 + (c.TVFiles.size) * 1.0 from Contract c where (select count (c1.billboardFiles.size) * 1.0 + (c1.socialNetworkFiles.size) * 1.0 + (c1.infoFiles.size) * 1.0 + (c1.radioFiles.size) * 1.0 + (c1.TVFiles.size) * 1.0 from Contract c1 where c1.id = c.id) >= all (select count (c2.billboardFiles.size) * 1.0 + (c2.socialNetworkFiles.size) * 1.0 + (c2.infoFiles.size) * 1.0 + (c2.radioFiles.size) * 1.0 + (c2.TVFiles.size) * 1.0 from Contract c2 group by c2) group by c")
	Integer getMaxFilesPerContract();
		
	@Query("select sqrt((count (c.billboardFiles.size) * 1.0 + (c.socialNetworkFiles.size) * 1.0 + (c.infoFiles.size) * 1.0 + (c.radioFiles.size) * 1.0 + (c.TVFiles.size) * 1.0)*(count (c.billboardFiles.size) * 1.0 + (c.socialNetworkFiles.size) * 1.0 + (c.infoFiles.size) * 1.0 + (c.radioFiles.size) * 1.0 + (c.TVFiles.size) * 1.0))/(count(c)-(((count (c.billboardFiles.size) * 1.0 + (c.socialNetworkFiles.size) * 1.0 + (c.infoFiles.size) * 1.0 + (c.radioFiles.size) * 1.0 + (c.TVFiles.size) * 1.0)/count(c))*((count (c.billboardFiles.size) * 1.0 + (c.socialNetworkFiles.size) * 1.0 + (c.infoFiles.size) * 1.0 + (c.radioFiles.size) * 1.0 + (c.TVFiles.size) * 1.0)/count(c)))) from Contract c")
	Double getStdevFilesPerContract();
	
}
