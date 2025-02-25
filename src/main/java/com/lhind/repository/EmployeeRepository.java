package com.lhind.repository;

import com.lhind.model.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends Repository<Employee, Long> {

    Optional<Employee> findByUsername(String username);

    List<Employee> findAllNamedQuery(String username);

    Optional<Employee> findById(Long id);

}
