package org.example.mattmontalbano.particlefilter.api.service;

import org.example.mattmontalbano.particlefilter.api.repository.ScenarioRepository;
import org.springframework.stereotype.Service;

@Service
public class ScenarioService {

    private final ScenarioRepository scenarioRepository;

    public ScenarioService(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }
}
