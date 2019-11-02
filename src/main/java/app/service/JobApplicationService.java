package app.service;

import app.domain.models.service.JobServiceModel;

import java.util.List;

public interface JobApplicationService {

    boolean addJob(JobServiceModel jobServiceModel);

    List<String> getAllSectors();

    List<JobServiceModel> getAllJobs();
}
