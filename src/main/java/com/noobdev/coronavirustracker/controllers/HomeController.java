package com.noobdev.coronavirustracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.noobdev.coronavirustracker.models.LocationStats;
import com.noobdev.coronavirustracker.services.CoronaVirusDataService;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    CoronaVirusDataService coronaVirusDataService;

    @GetMapping("/")
    public String home(Model model) {
        List<LocationStats> allStats = coronaVirusDataService.getAllStats();
        
       // model.addAttribute("total", )
        model.addAttribute("locationStats", allStats);
        model.addAttribute("total", coronaVirusDataService.total);

        return "home";
    }
}
