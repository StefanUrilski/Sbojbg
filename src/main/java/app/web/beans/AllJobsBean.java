package app.web.beans;

import app.domain.models.view.JobsViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named("allJobs")
@RequestScoped
public class AllJobsBean extends BaseBean {

    private List<JobsViewModel> jobs;

    private JobApplicationService jobService;
    private ModelMapper modelMapper;

    public AllJobsBean() {
    }

    @Inject
    public AllJobsBean(JobApplicationService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void init() {
        jobs = jobService.getAllJobs().stream()
                .map(job -> modelMapper.map(job, JobsViewModel.class))
                .collect(Collectors.toList());
    }

    public List<JobsViewModel> getJobs() {
        return jobs;
    }

    public void setJobs(List<JobsViewModel> jobs) {
        this.jobs = jobs;
    }
}
