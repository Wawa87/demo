package com.example.demo;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    Logger logger;

    @GetMapping("/")
    String getIndex(SecurityContext securityContext) {
        UserDetails principal = (UserDetails) securityContext.getAuthentication().getPrincipal();
        logger.info(principal.getUsername());
        return "Index page.";
    }
}
