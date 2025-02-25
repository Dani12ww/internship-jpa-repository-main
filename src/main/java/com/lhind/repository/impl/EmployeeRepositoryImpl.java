package com.lhind.repository.impl;

import com.lhind.configuration.EntityManagerConfiguration;
import com.lhind.model.entity.Employee;
import com.lhind.repository.EmployeeRepository;
import com.lhind.util.Queries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final EntityManager entityManager;

    public EmployeeRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Employee.class, id));
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery(Queries.FIND_ALL_EMPLOYEES, Employee.class);
        return query.getResultList();
    }

    @Override
    public void save(Employee employee) {
        entityManager.getTransaction().begin();
        if (employee.getId() != null) {
            findById(employee.getId()).ifPresent(existingEmployee -> {
                existingEmployee.setFirstName(employee.getFirstName());
                existingEmployee.setMiddleName(employee.getMiddleName());
                existingEmployee.setLastName(employee.getLastName());
                entityManager.merge(existingEmployee);
            });
        } else {
            entityManager.persist(employee);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Employee employee) {
        if (employee.getId() != null) {
            entityManager.getTransaction().begin();
            findById(employee.getId()).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Finds an employee by username using dynamic JPQL query.
     * No NamedQuery is used to avoid modifying the Employee entity.
     */
    @Override
    public Optional<Employee> findByUsername(String username) {
        try {
            TypedQuery<Employee> query = entityManager.createQuery(
                    "SELECT e FROM Employee e WHERE e.userName = :username", Employee.class);
            query.setParameter("username", username);
            return Optional.ofNullable(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Employee> findAllNamedQuery(String firstName) {
        TypedQuery<Employee> query = entityManager.createQuery(
                "SELECT e FROM Employee e WHERE e.firstName = :uname", Employee.class);
        query.setParameter("uname", firstName);
        return query.getResultList();
    }

}
