package com.lhind.repository;

import com.lhind.model.entity.Booking;
import com.lhind.model.entity.EmployeeDetails;

import java.util.List;

public interface BookingRepository extends Repository<Booking, Long>{

    List<Booking> findByEmployeeLastName(String lastName);

    List<EmployeeDetails> findFromNativeQuery(String number);
}
