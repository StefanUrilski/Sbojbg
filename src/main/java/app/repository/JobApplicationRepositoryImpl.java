package app.repository;

import app.domain.entities.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobApplicationRepositoryImpl implements JobApplicationRepository {

    private final EntityManager entityManager;

    @Inject
    public JobApplicationRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public JobApplication save(JobApplication jobApplication) {
        entityManager.getTransaction().begin();
        entityManager.persist(jobApplication);
        entityManager.getTransaction().commit();

        return jobApplication;
    }

    @Override
    public List<JobApplication> findAll() {
        return entityManager
                .createQuery("select j from JobApplication as j", JobApplication.class)
                .getResultList();
    }

    @Override
    public JobApplication findById(String id) {
        return entityManager
                .createQuery("select j from JobApplication as j where j.id = :id", JobApplication.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Long size() {
        return entityManager
                .createQuery("select count(j) from JobApplication j ", Long.class)
                .getSingleResult();
    }

    @Override
    public void removeById(String id) {
        entityManager.getTransaction().begin();
        entityManager
                .createQuery("delete from JobApplication as j where j.id = :id")
                .setParameter("id", id);
        entityManager.getTransaction().commit();
    }
}
