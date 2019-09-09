package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Contract;
import domain.SocialNetworkFile;

@Repository
public interface SocialNetworkFileRepository extends JpaRepository<SocialNetworkFile, Integer> {

	@Query("select s from SocialNetworkFile s where s.contract = ?1")
	Collection<SocialNetworkFile> findSocialNetworkFileByContract(Contract c);
	
	@Query("select avg(c.socialNetworkFiles.size) from Contract c")
	Double getAvgSocialNetworkFilesPerContract();
	
	@Query("select min(c.socialNetworkFiles.size) from Contract c")
	Integer getMinSocialNetworkFilesPerContract();
	
	@Query("select max(c.socialNetworkFiles.size) from Contract c")
	Integer getMaxSocialNetworkFilesPerContract();
	
//	@Query("select stddev(c.socialNetworkFiles.size) from Contract c")
//	Double getStdevSocialNetworkFilesPerContract();
	
	@Query("select sqrt(sum(c.socialNetworkFiles.size*c.socialNetworkFiles.size))/(count(c.socialNetworkFiles.size)-(avg(c.socialNetworkFiles.size)*avg(c.socialNetworkFiles.size))) from Contract c")
	Double getStdevSocialNetworkFilesPerContract();
}