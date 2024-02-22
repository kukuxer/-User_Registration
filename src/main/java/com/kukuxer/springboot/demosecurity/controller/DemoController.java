package com.kukuxer.springboot.demosecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.awt.*;
import java.io.File;
import java.io.IOException;

@Controller
public class DemoController {
    @GetMapping("/")
    public String showHome() {
        return "home";
    }

    @GetMapping("/leaders")
    public String showLeaders() {
        return "leaders";
    }

    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }

    @GetMapping("/openDota")
    public String openDota() {
        String urlFilePath = "C:\\Users\\porku\\Desktop\\Dota.url";

        try {
            // Open the URL file using the default web browser
            openUrlFile(urlFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "openDota";
    }

    private static void openUrlFile(String filePath) throws IOException {
        String os = System.getProperty("os.name").toLowerCase();

        if (os.contains("win")) {
            // For Windows
            Runtime.getRuntime().exec("cmd /c start " + filePath);
        } else {
            // Unsupported platform
            System.out.println("Opening a URL file is not supported on this platform.");
        }
    }
}

