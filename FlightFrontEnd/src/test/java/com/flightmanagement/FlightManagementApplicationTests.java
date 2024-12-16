package com.flightmanagement;

import com.flightmanagement.model.Flight;
import com.flightmanagement.repository.FlightRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FlightManagementApplicationTests {

    @Autowired
    private FlightRepository flightRepository;

    @Test
    void testFindByOrigin() {
        Flight flight = new Flight("F123", "NYC", "LAX", "2024-12-20T10:00", "2024-12-20T13:00");
        flightRepository.save(flight);

        List<Flight> flights = flightRepository.findByOrigin("NYC");
        assertFalse(flights.isEmpty());
        assertEquals("NYC", flights.get(0).getOrigin());
    }

    @Test
    void testExistsByFlightNumber() {
        Flight flight = new Flight("F124", "SFO", "ORD", "2024-12-20T15:00", "2024-12-20T18:00");
        flightRepository.save(flight);

        assertTrue(flightRepository.existsByFlightNumber("F124"));
    }
}