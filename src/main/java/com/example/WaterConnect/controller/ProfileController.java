package com.example.WaterConnect.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WaterConnect.model.User;
import com.example.WaterConnect.service.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @PutMapping("/update/{id}")
    public Object updateProfile(@PathVariable Long id, @RequestBody User updatedUser) {
        return profileService.updateProfile(id, updatedUser);
    }

    @GetMapping("/{id}")
    public Object getProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }

}