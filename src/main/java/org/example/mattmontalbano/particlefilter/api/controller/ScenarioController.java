package org.example.mattmontalbano.particlefilter.api.controller;


import org.example.mattmontalbano.particlefilter.api.model.ScenarioModel;
import org.example.mattmontalbano.particlefilter.api.repository.ScenarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/scenarios")
public class ScenarioController {

    private final ScenarioRepository scenarioRepository;

    public ScenarioController(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    @GetMapping
    public Map<String, ScenarioModel> findAll() {
        return scenarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ScenarioModel findById(@PathVariable String id) {
        return scenarioRepository.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ScenarioModel create(@RequestBody ScenarioModel scenarioModel) {
        return scenarioRepository.create(scenarioModel);
    }
}
