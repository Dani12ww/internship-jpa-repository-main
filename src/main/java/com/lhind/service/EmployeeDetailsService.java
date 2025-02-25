package com.lhind.service;

import com.lhind.model.entity.EmployeeDetails;

import java.util.List;

public interface EmployeeDetailsService {

    void saveEmployeeDetails(EmployeeDetails employeeDetails);

    List<EmployeeDetails> findAllNamedQuery(String lastName);
}
