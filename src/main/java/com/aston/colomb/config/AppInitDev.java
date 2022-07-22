package com.aston.colomb.config;

import com.aston.colomb.services.DevService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Classes that implement CommandLineRunner will be executed at start-up each time
// With a real database there would be no need to run this code, for testing it is useful.
@Component
public class AppInitDev implements CommandLineRunner {
    private final DevService devService;

    public AppInitDev(DevService service) {
        this.devService = service;
    }

    @Override
    public void run(String... args) {
        devService.initializeDatabase();
    }
}
