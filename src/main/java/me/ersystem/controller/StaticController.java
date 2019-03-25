package me.ersystem.controller;

import me.ersystem.dto.IncidentTypeStatDto;
import me.ersystem.dto.LocationStatDto;
import me.ersystem.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 *
 * @since 22-Mar-2019
 */
@Controller
public class StaticController {

    @Autowired
    IncidentService service;

    @GetMapping("chart")
    public String viewChart(Model model){
        List<LocationStatDto> locationStatistics = service.getLocationStatistics();
        model.addAttribute("LocStat",locationStatistics);
        List<IncidentTypeStatDto> typeStatistics = service.getTypeStatistics();
        model.addAttribute("typeStat",typeStatistics);
        return "charts";
    }


}
