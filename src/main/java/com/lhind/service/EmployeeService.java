package com.lhind.service;

import com.lhind.model.dto.EmployeeDTO;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    List<EmployeeDTO> loadAllEmployees();

    void saveEmployee(EmployeeDTO employee);

    List<EmployeeDTO> findAllNamedQuery(String firstName);

    Optional<EmployeeDTO> findById(Long id);

}
