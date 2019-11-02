package app.service;

import app.domain.entities.JobApplication;
import app.domain.entities.enums.Sector;
import app.domain.models.service.JobServiceModel;
import app.repository.JobApplicationRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<String> getAllSectors() {
        return Arrays.stream(Sector.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
