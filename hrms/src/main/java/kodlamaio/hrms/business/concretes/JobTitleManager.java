package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.IJobTitleService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.IJobTitleDao;
import kodlamaio.hrms.entities.concretes.JobTitle;

@Service
public class JobTitleManager implements IJobTitleService{
	
	private IJobTitleDao jobTitleDao;

	public JobTitleManager(IJobTitleDao jobTitleDao) {
		super();
		this.jobTitleDao = jobTitleDao;
	}

	@Override
	public DataResult<List<JobTitle>> getAll() {
		return new SuccessDataResult<List<JobTitle>>
		(this.jobTitleDao.findAll(), "İş pozisyonları listelendi");
	}

	@Override
	public Result add(JobTitle jobTitle) {
		if (existsJobTitlesByJobTitle(jobTitle.getJobTitle())) {
			this.jobTitleDao.save(jobTitle);
			return new SuccessResult("İş pozisyonu eklendi");
		}
		else {
			return new ErrorResult("Böyle bir pozisyon zaten var.");
		}
	}

	@Override
	public Result delete(int id) {
		this.jobTitleDao.deleteById(id);
		return new SuccessResult("İş pozisyonu silindi");
	}

	@Override
	public boolean existsJobTitlesByJobTitle(String jobTitle) {
		if (this.jobTitleDao.existsJobTitlesByJobTitle(jobTitle)) {
			return false;
		}
		else {
			return true;
		}
	}

	
	
	

}
