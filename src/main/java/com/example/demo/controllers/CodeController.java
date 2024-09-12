package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Controller
public class CodeController {

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
        ZoneId zoneId = ZoneId.of("America/Santiago");
        ZonedDateTime now = ZonedDateTime.now(zoneId);
        LocalTime time = now.toLocalTime();

        if (time.isBefore(LocalTime.NOON)) {
            return "https://academy.avast.com/hs-fs/hubfs/New_Avast_Academy/what_is_the_stuxnet_virus_academy_a1/Stuxnet-Virus-01.png?width=1320&name=Stuxnet-Virus-01.png"; // URL de la imagen de la mañana
        } else {
            return "https://i0.wp.com/historiaencomentarios.com/wp-content/uploads/2024/02/Dios-Zeus.png?fit=2310%2C1576&ssl=1"; // URL de la imagen de la tarde
        }
    }
}