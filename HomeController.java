package io.priyanka.coronavirustrac.controllers;

import io.priyanka.coronavirustrac.model.LocationStats;
import io.priyanka.coronavirustrac.services.CoronaVirusDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model){
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        int totalConfirmed = allStats.stream().mapToInt(stat -> Integer.parseInt(stat.getConfirmed())).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalConfirmed", totalConfirmed);
        return "home";
    }
}
