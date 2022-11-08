package kodlamaio.hrms.business.abstracts;

import kodlamaio.hrms.entities.concretes.Employer;

public interface IEmployerCheckService {
	public boolean isEmployerAnyEmpty(Employer employer);
	public boolean isEmployerEmailVerified(Employer employer);
	public boolean isEmployerPasswordMatch(Employer employer);
	public boolean isDomainEqualEmail(Employer employer);
	
	public boolean existsEmployerByEmail(String email);
}
