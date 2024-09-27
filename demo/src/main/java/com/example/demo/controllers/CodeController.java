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
        if ("stuxnet".equalsIgnoreCase(code)) {
            String imageUrl = getImageUrlBasedOnTime();
            model.addAttribute("imageUrl", imageUrl);
        } else {
            model.addAttribute("imageUrl", null);
        }
        return "endpoint";
    }

    private String getImageUrlBasedOnTime() {
        LocalTime currentTime = timeService.getCurrentTimeFromInternet();
        LocalTime startTime = LocalTime.of(23, 45);
        LocalTime endTime = LocalTime.of(23, 47);

        if (currentTime.isAfter(startTime) && currentTime.isBefore(endTime)) {
            return "https://imgur.com/a/ZVODwte";
        } else {
            return "https://i.imgur.com/OsN2aRn.png";
        }
    }
}