package com.example.WaterConnect.controller;

import org.springframework.web.bind.annotation.*;

import com.example.WaterConnect.service.ProfileService;

@RestController
@RequestMapping("/profile")
@CrossOrigin("*")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // UPDATE PROFILE (supports individual & multiple fields)
    @PutMapping("/update/{id}")
    public Object updateProfile(@PathVariable Long id, @RequestBody Object updatedFields) {
        return profileService.updateProfile(id, updatedFields);
    }

    // GET PROFILE
    @GetMapping("/{id}")
    public Object getProfile(@PathVariable Long id) {
        return profileService.getProfile(id);
    }
}
