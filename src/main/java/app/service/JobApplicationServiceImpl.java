package app.service;

import app.domain.entities.JobApplication;
import app.domain.models.service.JobServiceModel;
import app.repository.JobApplicationRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;

public class JobApplicationServiceImpl implements JobApplicationService {

    private final ModelMapper modelMapper;
    private final JobApplicationRepository jobRepository;

    @Inject
    public JobApplicationServiceImpl(ModelMapper modelMapper, JobApplicationRepository jobRepository) {
        this.modelMapper = modelMapper;
        this.jobRepository = jobRepository;
    }

    @Override
    public void addJob(JobServiceModel jobServiceModel) {
        JobApplication job = modelMapper.map(jobServiceModel, JobApplication.class);

        jobRepository.save(job);
    }
}
