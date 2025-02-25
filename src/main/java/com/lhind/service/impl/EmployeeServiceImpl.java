package com.lhind.service.impl;

import com.lhind.mapper.EmployeeMapper;
import com.lhind.model.dto.EmployeeDTO;
import com.lhind.repository.EmployeeRepository;
import com.lhind.repository.impl.EmployeeRepositoryImpl;
import com.lhind.service.EmployeeService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl() {
        this.employeeRepository = new EmployeeRepositoryImpl();
        this.employeeMapper = new EmployeeMapper();
    }

    @Override
    public List<EmployeeDTO> loadAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void saveEmployee(EmployeeDTO employee) {
        employeeRepository.save(employeeMapper.toEntity(employee));
    }

    @Override
    public List<EmployeeDTO> findAllNamedQuery(String username) {
        return employeeRepository.findAllNamedQuery(username)
                .stream()
                .map(employeeMapper::toDto)
                .collect(Collectors.toList());
    }

    // Implemented findById method
    @Override
    public Optional<EmployeeDTO> findById(Long id) {
        return employeeRepository.findById(id)
                .map(employeeMapper::toDto);
    }
}
