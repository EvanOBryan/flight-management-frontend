package main.java.com.flightmanagement.controller;

import com.flightmanagement.dto.FlightDTO;
import com.flightmanagement.model.Flight;
import com.flightmanagement.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightService.getAllFlights());
        return "flight-list";
    }

    @GetMapping("/add")
    public String addFlightForm(Model model) {
        model.addAttribute("flight", new Flight());
        return "flight-add";
    }

    @PostMapping("/add")
    public String saveFlight(@ModelAttribute Flight flight) {
        flightService.saveFlight(flight);
        return "redirect:/flights";
    }

    @GetMapping("/delete/{flightNumber}")
    public String deleteFlight(@PathVariable String flightNumber) {
        flightService.deleteFlight(flightNumber);
        return "redirect:/flights";
    }
}