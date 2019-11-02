package app.web.beans;

import app.domain.models.view.JobDetailViewModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named("jobDelete")
public class DeleteJobBean extends BaseBean {

    private JobDetailViewModel job;

    private ModelMapper modelMapper;
    private JobApplicationService jobService;

    public DeleteJobBean() {
    }

    @Inject
    public DeleteJobBean(ModelMapper modelMapper, JobApplicationService jobService) {
        this.modelMapper = modelMapper;
        this.jobService = jobService;
    }

    @PostConstruct
    private void init() {
        String id = request().getParameter("id");
        job = modelMapper.map(jobService.getById(id), JobDetailViewModel.class);
    }

    public void deleteJob() {
        String id = request().getParameter("id");

        boolean deleted = jobService.deleteById(id);

        if (!deleted) { return; }

        redirect("/home");
    }

    public JobDetailViewModel getJob() {
        return job;
    }

    public void setJob(JobDetailViewModel job) {
        this.job = job;
    }
}
