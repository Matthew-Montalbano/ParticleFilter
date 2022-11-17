package org.example.mattmontalbano.particlefilter.api.controller;

import org.example.mattmontalbano.particlefilter.algorithm.Particle;
import org.example.mattmontalbano.particlefilter.api.model.CreateParticleFilterRequest;
import org.example.mattmontalbano.particlefilter.api.model.IDRequest;
import org.example.mattmontalbano.particlefilter.api.model.ParticleFilterProcessingResponse;
import org.example.mattmontalbano.particlefilter.api.service.ParticleFilterService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin
@RequestMapping("/particleFilter")
public class ParticleFilterController {

    private final ParticleFilterService particleFilterService;

    public ParticleFilterController(ParticleFilterService particleFilterService) {
        this.particleFilterService = particleFilterService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ParticleFilterProcessingResponse createParticleFilter(@RequestBody CreateParticleFilterRequest request) {
        return particleFilterService.create(request);
    }

    @PostMapping("/processNextObservation")
    public ParticleFilterProcessingResponse processNextObservation(@RequestBody IDRequest id) {
        return particleFilterService.processNextObservation(id.id());
    }

    @PostMapping("/updateTime")
    public Particle[] updateParticleFilterTime(HttpSession session, @RequestBody long time) {
        return particleFilterService.updateParticleFilterTime(session.getId(), time);
        // TODO: Client strategy: update time every second, starting from the scenario start time and ending at the scenario end time
        // Might have to send scenario start and end time in the scenario model

    }
}
