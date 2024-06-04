package com.mobabuild.api_build.controller;

import com.mobabuild.api_build.service.IFavoriteBuildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/favoritebuild")
public class FavoriteBuildController {

    @Autowired
    private IFavoriteBuildService favoriteBuildService;
}
