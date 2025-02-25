package com.lhind.model.dto;

import com.lhind.model.enums.EmploymentStatus;

public class EmployeeDTO {

    private Long id;
    private String username;
    private String firstName;
    private String middleName;
    private String lastName;
    private EmploymentStatus employmentStatus;
    private EmployeeDetailsDTO employeeDetailsDTO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmploymentStatus getEmploymentStatus() {
        return employmentStatus;
    }

    public void setEmploymentStatus(EmploymentStatus employmentStatus) {
        this.employmentStatus = employmentStatus;
    }

    public EmployeeDetailsDTO getEmployeeDetailsDTO() {
        return employeeDetailsDTO;
    }

    public void setEmployeeDetailsDTO(EmployeeDetailsDTO employeeDetailsDTO) {
        this.employeeDetailsDTO = employeeDetailsDTO;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employmentStatus=" + employmentStatus +
                ", employeeDetailsDTO=" + employeeDetailsDTO +
                '}';
    }

}
