package main.java.com.flightmanagement.repository;

import com.flightmanagement.model.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, String> {

    List<Flight> findByOrigin(String origin);

    List<Flight> findByDestination(String destination);

    List<Flight> findByOriginAndDestination(String origin, String destination);

    @Query("SELECT f FROM Flight f WHERE f.departureTime BETWEEN :startTime AND :endTime")
    List<Flight> findFlightsByDepartureTimeRange(@Param("startTime") String startTime, 
                                                 @Param("endTime") String endTime);

    @Query("SELECT f FROM Flight f WHERE f.arrivalTime BETWEEN :startTime AND :endTime")
    List<Flight> findFlightsByArrivalTimeRange(@Param("startTime") String startTime, 
                                               @Param("endTime") String endTime);

    boolean existsByFlightNumber(String flightNumber);

    @Query("SELECT COUNT(f) FROM Flight f WHERE f.origin = :origin AND f.destination = :destination")
    int countFlightsByRoute(@Param("origin") String origin, @Param("destination") String destination);

    @Query("SELECT DISTINCT f.origin FROM Flight f")
    List<String> findDistinctOrigins();

    @Query("SELECT DISTINCT f.destination FROM Flight f")
    List<String> findDistinctDestinations();

    @Query("SELECT f FROM Flight f WHERE f.origin = :origin AND f.destination = :destination AND f.departureTime LIKE :date%")
    List<Flight> findFlightsByRouteAndDate(@Param("origin") String origin, 
                                           @Param("destination") String destination, 
                                           @Param("date") String date);

    void deleteByOrigin(String origin);

    void deleteByDestination(String destination);

    Page<Flight> findByOrigin(String origin, Pageable pageable);
}