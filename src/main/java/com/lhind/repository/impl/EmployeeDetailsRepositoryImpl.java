package com.lhind.repository.impl;

import com.lhind.configuration.EntityManagerConfiguration;
import com.lhind.model.entity.EmployeeDetails;
import com.lhind.repository.EmployeeDetailsRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class EmployeeDetailsRepositoryImpl implements EmployeeDetailsRepository {

    private final EntityManager entityManager;

    public EmployeeDetailsRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<EmployeeDetails> findById(Long id) {
        return Optional.ofNullable(entityManager.find(EmployeeDetails.class, id));
    }

    @Override
    public List<EmployeeDetails> findAll() {
        TypedQuery<EmployeeDetails> query = entityManager.createQuery(
                "SELECT ed FROM EmployeeDetails ed", EmployeeDetails.class);
        return query.getResultList();
    }

    @Override
    public void save(EmployeeDetails employeeDetails) {
        entityManager.getTransaction().begin();
        if (employeeDetails.getId() != null) {
            findById(employeeDetails.getId()).ifPresent(existingDetails -> {
                existingDetails.setEmail(employeeDetails.getEmail());
                existingDetails.setPhoneNumber(employeeDetails.getPhoneNumber());
                existingDetails.setEmploymentDate(employeeDetails.getEmploymentDate());
                existingDetails.setLastName(employeeDetails.getLastName()); // ✅ Ensure lastName is set
                entityManager.merge(existingDetails);
            });
        } else {
            entityManager.persist(employeeDetails);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(EmployeeDetails employeeDetails) {
        if (employeeDetails.getId() != null) {
            entityManager.getTransaction().begin();
            findById(employeeDetails.getId()).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();
        }
    }

    @Override
    public List<EmployeeDetails> findAllNamedQuery(String lastName) {
        TypedQuery<EmployeeDetails> query = entityManager.createQuery(
                "SELECT ed FROM EmployeeDetails ed WHERE ed.lastName = :lastName", EmployeeDetails.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}
