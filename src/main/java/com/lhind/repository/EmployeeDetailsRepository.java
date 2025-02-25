package com.lhind.repository;

import com.lhind.model.entity.EmployeeDetails;

import java.util.List;

public interface EmployeeDetailsRepository extends Repository<EmployeeDetails, Long>{
    void save(EmployeeDetails employeeDetails);
    List<EmployeeDetails> findAllNamedQuery(String lastName);
}
