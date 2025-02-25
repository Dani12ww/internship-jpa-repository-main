package com.lhind.mapper;

import com.lhind.model.dto.EmployeeDetailsDTO;
import com.lhind.model.entity.EmployeeDetails;
import com.lhind.model.enums.EmploymentStatus;

public class EmployeeDetailsMapper extends AbstractMapper<EmployeeDetails, EmployeeDetailsDTO> {

    @Override
    public EmployeeDetails toEntity(EmployeeDetailsDTO dto) {
        if (dto == null) {
            return null;
        }

        EmployeeDetails employeeDetails = new EmployeeDetails();
        employeeDetails.setId(dto.getId()); // Preserve ID on updates
        employeeDetails.setEmail(dto.getEmail());
        employeeDetails.setEmploymentDate(dto.getEmploymentDate());
        employeeDetails.setPhoneNumber(dto.getPhoneNumber());
        employeeDetails.setLastName(dto.getLastName()); // ✅ Map lastName

        // Use the status from DTO if available, otherwise default to ACTIVE
        employeeDetails.setEmploymentStatus(dto.getEmploymentStatus() != null ?
                dto.getEmploymentStatus() :
                EmploymentStatus.ACTIVE);

        return employeeDetails;
    }

    @Override
    public EmployeeDetailsDTO toDto(EmployeeDetails entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDetailsDTO employeeDetailsDTO = new EmployeeDetailsDTO();
        employeeDetailsDTO.setId(entity.getId());
        employeeDetailsDTO.setEmail(entity.getEmail());
        employeeDetailsDTO.setPhoneNumber(entity.getPhoneNumber());
        employeeDetailsDTO.setEmploymentDate(entity.getEmploymentDate());
        employeeDetailsDTO.setEmploymentStatus(entity.getEmploymentStatus());
        employeeDetailsDTO.setLastName(entity.getLastName()); // ✅ Map lastName to DTO

        return employeeDetailsDTO;
    }
}
