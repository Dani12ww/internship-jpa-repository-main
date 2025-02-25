package com.lhind.repository.impl;

import com.lhind.configuration.EntityManagerConfiguration;
import com.lhind.model.entity.Booking;
import com.lhind.model.entity.EmployeeDetails;
import com.lhind.repository.BookingRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class BookingRepositoryImpl implements BookingRepository {

    private final EntityManager entityManager;

    public BookingRepositoryImpl() {
        entityManager = EntityManagerConfiguration.getEntityManager();
    }

    @Override
    public Optional<Booking> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Booking.class, id));
    }

    @Override
    public List<Booking> findAll() {
        TypedQuery<Booking> query = entityManager.createQuery("SELECT b FROM Booking b", Booking.class);
        return query.getResultList();
    }

    @Override
    public void save(Booking booking) {
        entityManager.getTransaction().begin();
        if (booking.getId() != null) {
            findById(booking.getId()).ifPresent(existingBooking -> {
                existingBooking.setBookingNumber(booking.getBookingNumber());
                existingBooking.setBookingStartDate(booking.getBookingStartDate());
                existingBooking.setBookingEndDate(booking.getBookingEndDate());
                existingBooking.setBookingStatus(booking.getBookingStatus());
                existingBooking.setCost(booking.getCost());
                entityManager.merge(existingBooking);
            });
        } else {
            entityManager.persist(booking);
        }
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(Booking booking) {
        if (booking.getId() != null) {
            entityManager.getTransaction().begin();
            findById(booking.getId()).ifPresent(entityManager::remove);
            entityManager.getTransaction().commit();
        }
    }

    /**
     * Finds all bookings by an employee's last name.
     */
    @Override
    public List<Booking> findByEmployeeLastName(String lastName) {
        TypedQuery<Booking> query = entityManager.createQuery(
                "SELECT b FROM Booking b JOIN b.employee e WHERE e.lastName = :lastName", Booking.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public List<EmployeeDetails> findFromNativeQuery(String number) {
        return List.of();
    }
}
