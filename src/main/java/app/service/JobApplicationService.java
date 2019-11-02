package app.service;

import app.domain.models.service.JobServiceModel;

import java.util.List;

public interface JobApplicationService {

    void addJob(JobServiceModel jobServiceModel);

    List<String> getAllSectors();
}
