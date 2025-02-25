package com.lhind.mapper;

import com.lhind.model.dto.EmployeeDTO;
import com.lhind.model.entity.Employee;
import com.lhind.model.entity.EmployeeDetails;

public class EmployeeMapper extends AbstractMapper<Employee, EmployeeDTO> {

    private final EmployeeDetailsMapper employeeDetailsMapper;

    public EmployeeMapper() {
        this.employeeDetailsMapper = new EmployeeDetailsMapper();
    }

    @Override
    public Employee toEntity(EmployeeDTO employeeDTO) {
        if (employeeDTO == null) {
            return null;
        }

        Employee employee = new Employee();
        employee.setId(employeeDTO.getId()); // Add this to preserve ID on updates
        employee.setLastName(employeeDTO.getLastName());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setMiddleName(employeeDTO.getMiddleName());
        employee.setUserName(employeeDTO.getUsername());

        if (employeeDTO.getEmployeeDetailsDTO() != null) {
            EmployeeDetails details = employeeDetailsMapper.toEntity(employeeDTO.getEmployeeDetailsDTO());
            employee.setEmployeeDetails(details);
            // Complete the bidirectional relationship
            details.setEmployee(employee);

            // Ensure employment status is set
            if (details.getEmploymentStatus() == null) {
                details.setEmploymentStatus(employeeDTO.getEmploymentStatus());
            }
        }

        return employee;
    }

    @Override
    public EmployeeDTO toDto(Employee employee) {
        if (employee == null) {
            return null;
        }

        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setMiddleName(employee.getMiddleName());
        employeeDTO.setUsername(employee.getUserName()); // Fix: Set username in DTO

        if (employee.getEmployeeDetails() != null) {
            employeeDTO.setEmploymentStatus(employee.getEmployeeDetails().getEmploymentStatus());
        }

        employeeDTO.setEmployeeDetailsDTO(employeeDetailsMapper.toDto(employee.getEmployeeDetails()));
        return employeeDTO;
    }
}