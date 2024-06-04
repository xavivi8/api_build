package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.entities.Authority;
import com.mobabuild.api_build.service.IAuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authority")
public class AuthorityController {

    @Autowired
    private IAuthorityService authorityService;

    @GetMapping("/findAll")
    public Iterable<Authority> getAllAuthorities() {
        return authorityService.findAll();
    }
}
