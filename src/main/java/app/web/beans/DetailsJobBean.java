package app.web.beans;

import app.domain.models.view.JobDetailViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobDetails")
@RequestScoped
public class DetailsJobBean extends BaseBean {

    private JobDetailViewModel job;

    private ModelMapper modelMapper;
    private JobApplicationService jobService;

    public DetailsJobBean() {
    }

    @Inject
    public DetailsJobBean(ModelMapper modelMapper, JobApplicationService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    @PostConstruct
    public void init() {
        String id = request().getParameter("id");
        job = modelMapper.map(jobService.getById(id), JobDetailViewModel.class);
    }

    public JobDetailViewModel getJob() {
        return job;
    }

    public void setJob(JobDetailViewModel job) {
        this.job = job;
    }
}
