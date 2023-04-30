package com.bookkeeper.library.service;

import com.bookkeeper.library.security.MyAuthorDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {

    private MyAuthorDetailsService myAuthorDetailsService;

    @Autowired
    public void setMyAuthorDetailsService(MyAuthorDetailsService myAuthorDetailsService) {
        this.myAuthorDetailsService = myAuthorDetailsService;
    }


}
