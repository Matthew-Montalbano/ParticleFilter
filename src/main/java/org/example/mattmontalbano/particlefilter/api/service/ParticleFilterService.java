package org.example.mattmontalbano.particlefilter.api.service;

import org.example.mattmontalbano.particlefilter.algorithm.*;
import org.example.mattmontalbano.particlefilter.api.model.CreateParticleFilterRequest;
import org.example.mattmontalbano.particlefilter.api.model.ParticleFilterModel;
import org.example.mattmontalbano.particlefilter.api.model.ParticleFilterProcessingResponse;
import org.example.mattmontalbano.particlefilter.api.model.ScenarioModel;
import org.example.mattmontalbano.particlefilter.api.repository.ParticleFilterRepository;
import org.example.mattmontalbano.particlefilter.api.repository.ScenarioRepository;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Service
public class ParticleFilterService {

    private final ParticleFilterRepository _particleFilterRepository;
    private final ScenarioRepository _scenarioRepository;


    public ParticleFilterService(ParticleFilterRepository particleFilterRepository,
                                 ScenarioRepository scenarioRepository) {
        this._particleFilterRepository = particleFilterRepository;
        this._scenarioRepository = scenarioRepository;
    }

    public ParticleFilterProcessingResponse create(CreateParticleFilterRequest request) {
        ScenarioModel scenarioModel = _scenarioRepository.findById(request.scenarioId());
        NewRandom randGen = new NewRandom(new Random(scenarioModel.seed()));
        PositionObservation[] observations = PositionObservation.createObservations(scenarioModel.trueTargetStates(),
                                                                                    scenarioModel.standardDeviation(),
                                                                                    randGen);
        Scenario scenario = new Scenario(scenarioModel.trueTargetStates(), observations);

        long startTime = observations[0].getTime();
        Particle[] startingParticles = ParticleSetGenerator.createParticlesAroundObservation(observations[0],
                                                                                             request.numParticles(),
                                                                                             startTime,
                                                                                             request.maxSpeed(),
                                                                                             randGen);
        ParticleFilter particleFilter = new ParticleFilter(startingParticles, request.meanManeuverTime(), randGen);

        ParticleFilterRunner particleFilterRunner = new ParticleFilterRunner(particleFilter, scenario);

        String id = UUID.randomUUID().toString();
        ParticleFilterModel particleFilterModel = new ParticleFilterModel(id, particleFilterRunner);

        _particleFilterRepository.create(particleFilterModel);

        return new ParticleFilterProcessingResponse(id, startTime, startingParticles);
    }

    public Particle[] updateParticleFilterTime(String particleFilterId, long time) {
        ParticleFilterModel particleFilterModel = _particleFilterRepository.findById(particleFilterId);
        particleFilterModel.particleFilterRunner().updateTime(time);
        return particleFilterModel.particleFilterRunner().getParticleFilter().getParticleSet();
    }

    public ParticleFilterProcessingResponse processNextObservation(String particleFilterId) {
        ParticleFilterModel particleFilterModel = _particleFilterRepository.findById(particleFilterId);
        particleFilterModel.particleFilterRunner().processNextObservation();
        ParticleFilterRunner particleFilterRunner = particleFilterModel.particleFilterRunner();
        return new ParticleFilterProcessingResponse(particleFilterId,
                                                    particleFilterRunner.getCurrentTime(),
                                                    particleFilterRunner.getParticleFilter()
                                                                        .getParticleSet());
    }

}
