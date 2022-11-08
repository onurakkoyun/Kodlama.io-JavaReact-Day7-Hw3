package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.IEmployeeService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.IEmployeeDao;
import kodlamaio.hrms.entities.concretes.Employee;

@Service
public class EmployeeManager implements IEmployeeService{
	
	private IEmployeeDao employeeDao;
	EmployeeCheckManager employeeCheckManager;
	
	@Autowired
	public EmployeeManager(IEmployeeDao employeeDao, EmployeeCheckManager employeeCheckManager) {
		super();
		this.employeeDao = employeeDao;
		this.employeeCheckManager = employeeCheckManager;
	}

	@Override
	public DataResult<List<Employee>> getAll() {
		return new SuccessDataResult<List<Employee>>
		(this.employeeDao.findAll(), "İş arayanlar listelendi");
	}

	@Override
	public Result add(Employee employee) {		
		if (employeeCheckManager.isEmployeeAnyEmpty(employee)) {
			
			if (employeeCheckManager.isEmployeePasswordMatch(employee)) {
				
				if (employeeCheckManager.existsEmployeeByEmail(employee.getEmail())) {
					
					if (employeeCheckManager.existsEmployeeByIdentityNumber(employee.getIdentityNumber())) {
						
						if (employeeCheckManager.isEmployeeEmailVerified(employee)) {
							
							if (employeeCheckManager.checkIfRealPerson(employee)) {
								
								this.employeeDao.save(employee);
								return new SuccessResult("İş arayan eklendi");
							}
							else {
								return new ErrorResult("Kişi bilgileri doğrulanamadı!");
							}
							
								
						}
						else {
							return new ErrorResult("Email doğrulanmadı!");
						}
						
					}
					else {
						return new ErrorResult("Bu kimlik numarası sistemde kayıtlı!");
					}
					
				}
				else {
					return new ErrorResult("Bu email sistemde kayıtlı!");
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
		this.employeeDao.deleteById(id);
		return new SuccessResult("İşçi silindi");
	}

}
