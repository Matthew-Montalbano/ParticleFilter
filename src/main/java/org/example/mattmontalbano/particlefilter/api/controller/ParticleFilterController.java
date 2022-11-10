package org.example.mattmontalbano.particlefilter.api.controller;

import org.example.mattmontalbano.particlefilter.api.model.CreateParticleFilterRequest;
import org.example.mattmontalbano.particlefilter.api.model.ParticleFilterModel;
import org.example.mattmontalbano.particlefilter.api.service.ParticleFilterService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/particleFilter")
public class ParticleFilterController {

    private final ParticleFilterService particleFilterService;

    public ParticleFilterController(ParticleFilterService particleFilterService) {
        this.particleFilterService = particleFilterService;
    }

    @PostMapping
    public ParticleFilterModel createParticleFilter(@RequestBody CreateParticleFilterRequest request, HttpSession session) {
        return particleFilterService.create(session.getId(), request);
    }

    @GetMapping("/{id}/update")
    public ParticleFilterModel runParticleFilterOverNextObservation(@PathVariable String id) {
        return particleFilterService.runParticleFilterOverNextObservation(id);
    }

    @PostMapping("/test")
    public void test(HttpServletRequest request) {
        System.out.println(request);
    }
}
