package com.example.demo.controllers;

import ApiControllers.TimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
public class CodeController {

    private TimeService timeService;

    public CodeController() {
        this.timeService = new TimeService();
    }

    @PostMapping("/endpoint")
    public String endpoint(@RequestParam(name = "code", required = true, defaultValue = "Nada")
                           String code, Model model) {
        model.addAttribute("code", code);
        if ("hola".equalsIgnoreCase(code)) {
            String imageUrl = getImageUrlBasedOnTime();
            model.addAttribute("imageUrl", imageUrl);
        } else {
            model.addAttribute("imageUrl", null);
        }
        return "endpoint";
    }

    private String getImageUrlBasedOnTime() {
        LocalTime currentTime = timeService.getCurrentTimeFromInternet();
        LocalTime startTime = LocalTime.of(4, 30);
        LocalTime endTime = LocalTime.of(5, 0);

        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            return "https://academy.avast.com/hs-fs/hubfs/New_Avast_Academy/what_is_the_stuxnet_virus_academy_a1/Stuxnet-Virus-01.png?width=1320&name=Stuxnet-Virus-01.png";
        } else {
            return "https://i0.wp.com/historiaencomentarios.com/wp-content/uploads/2024/02/Dios-Zeus.png?fit=2310%2C1576&ssl=1";
        }
    }
}