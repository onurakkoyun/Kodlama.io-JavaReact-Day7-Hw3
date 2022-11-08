package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.IEmployerService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.IEmployerDao;
import kodlamaio.hrms.entities.concretes.Employer;

@Service
public class EmployerManager implements IEmployerService{

	private IEmployerDao employerDao;
	private SystemStaffManager systemStaffManager;
	private EmployerCheckManager employerCheckManager;
	@Autowired
	public EmployerManager(IEmployerDao employerDao,EmployerCheckManager employerCheckManager, SystemStaffManager systemStaffManager) {
		super();
		this.employerDao = employerDao;
		this.employerCheckManager = employerCheckManager;
		this.systemStaffManager = systemStaffManager;
	}

	@Override
	public DataResult<List<Employer>> getAll() {
		return new SuccessDataResult<List<Employer>>
		(this.employerDao.findAll(), "İş verenler listelendi");
	}

	@Override
	public Result add(Employer employer) {
		
		if (employerCheckManager.isEmployerAnyEmpty(employer)) {
			if (employerCheckManager.isEmployerPasswordMatch(employer)) {
				if (employerCheckManager.isDomainEqualEmail(employer)) {
					if (systemStaffManager.isStaffVerifiedEmployerEmail(employer)) {
						
						if (employerCheckManager.existsEmployerByEmail(employer.getEmail())) {
							
							this.employerDao.save(employer);
							return new SuccessResult("İş veren eklendi");
							
						}
						else {
							return new ErrorResult("Bu email sistemde kayıtlı!");
						}
						
						
						
					}
					else {
						return new ErrorResult("Personel emailinizi henüz doğrulamadı!");
					}
					
				}
				else {
					return new ErrorResult("Emailin domain adresi firmaya ait değil!");
				}
			}
			else {
				return new ErrorResult("Şifreler eşleşmiyor!");
			}
		}
		else {
			return new ErrorResult("Bütün bilgiler girilmelidir!");
		}
		
		
	}

	@Override
	public Result delete(int id) {
		this.employerDao.deleteById(id);
		return new SuccessResult("İşçi silindi");
	}
	
}
