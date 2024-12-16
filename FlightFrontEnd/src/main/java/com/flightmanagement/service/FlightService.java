package com.flightmanagement.service;

import com.flightmanagement.dto.FlightDTO;
import com.flightmanagement.model.Flight;
import com.flightmanagement.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightService {

    @Autowired
    private FlightRepository flightRepository;

    public List<FlightDTO> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(flight -> new FlightDTO(flight.getFlightNumber(), flight.getOrigin(), flight.getDestination()))
                .collect(Collectors.toList());
    }

    public Flight getFlightByNumber(String flightNumber) {
        return flightRepository.findById(flightNumber).orElse(null);
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }

    public void deleteFlight(String flightNumber) {
        flightRepository.deleteById(flightNumber);
    }
}