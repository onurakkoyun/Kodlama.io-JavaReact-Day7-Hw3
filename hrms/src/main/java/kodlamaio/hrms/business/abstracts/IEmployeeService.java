package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Employee;

public interface IEmployeeService {
	
	DataResult<List<Employee>> getAll();
	Result add(Employee employee);
	Result delete(int id);
	
	

}
