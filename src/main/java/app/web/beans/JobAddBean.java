package app.web.beans;

import app.domain.models.binding.JobAddBindingModel;
import app.domain.models.service.JobServiceModel;
import app.service.JobApplicationService;
import org.modelmapper.ModelMapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Named("jobAdd")
@RequestScoped
public class JobAddBean extends BaseBean {

    private JobAddBindingModel job;
    private List<String> sectors;

    private JobApplicationService jobService;
    private ModelMapper modelMapper;

    public JobAddBean() {
    }

    @Inject
    public JobAddBean(JobApplicationService jobService, ModelMapper modelMapper) {
        this.jobService = jobService;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    public void init() {
        job = new JobAddBindingModel();
        sectors = jobService.getAllSectors();
    }

    public void addJob() {
        JobServiceModel jobServiceModel = modelMapper.map(job, JobServiceModel.class);

        boolean jobAdded = jobService.addJob(jobServiceModel);

        if (!jobAdded) { return; }

        redirect("/home");
    }

    public JobAddBindingModel getJob() {
        return job;
    }

    public void setJob(JobAddBindingModel job) {
        this.job = job;
    }

    public List<String> getSectors() {
        return sectors;
    }

    public void setSectors(List<String> sectors) {
        this.sectors = sectors;
    }
}
