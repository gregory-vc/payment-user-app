package org.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ProfileService implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ProfileService.class);

    @Value("${service.example-property}")
    private String exampleProperty;

    @Override
    public void run(String... args) {
        log.info("Example property: {}", exampleProperty);
    }
}
