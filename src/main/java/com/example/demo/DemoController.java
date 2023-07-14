package com.example.demo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    Logger logger;

    @GetMapping("/")
    String getIndex(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        logger.info(principal.toString());
        principal.getAuthorities().stream().forEach((auth)->{
            logger.info(auth.toString());
        });

        return "Index page.";
    }
}
