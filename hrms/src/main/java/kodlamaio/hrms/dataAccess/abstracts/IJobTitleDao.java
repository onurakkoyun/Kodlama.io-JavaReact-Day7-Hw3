package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.JobTitle;

public interface IJobTitleDao extends JpaRepository<JobTitle, Integer>{
	
	boolean existsJobTitlesByJobTitle(String jobTitle);

}
