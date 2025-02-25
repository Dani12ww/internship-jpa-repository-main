package com.lhind.service.impl;

import com.lhind.model.entity.EmployeeDetails;
import com.lhind.repository.EmployeeDetailsRepository;
import com.lhind.repository.impl.EmployeeDetailsRepositoryImpl;
import com.lhind.service.EmployeeDetailsService;

import java.util.List;

public class EmployeeDetailsServiceImpl implements EmployeeDetailsService {

    private final EmployeeDetailsRepository employeeDetailsRepository;

    public EmployeeDetailsServiceImpl() {
        this.employeeDetailsRepository = new EmployeeDetailsRepositoryImpl();
    }

    @Override
    public void saveEmployeeDetails(EmployeeDetails employeeDetails) {
        // ✅ Validate lastName before saving
        if (employeeDetails.getLastName() == null || employeeDetails.getLastName().trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Last name cannot be null or empty.");
        }

        // Optional: Validate email
        if (employeeDetails.getEmail() == null || employeeDetails.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Error: Email cannot be null or empty.");
        }

        employeeDetailsRepository.save(employeeDetails);
    }

    @Override
    public List<EmployeeDetails> findAllNamedQuery(String lastName) {
        return employeeDetailsRepository.findAllNamedQuery(lastName);
    }
}
