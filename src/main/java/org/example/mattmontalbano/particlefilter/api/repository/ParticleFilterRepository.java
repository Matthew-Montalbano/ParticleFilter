package org.example.mattmontalbano.particlefilter.api.repository;

import org.example.mattmontalbano.particlefilter.api.model.ParticleFilterModel;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ParticleFilterRepository {

    private final Map<String, ParticleFilterModel> _particleFilters = new HashMap<>();

    public ParticleFilterModel findById(String id) {
        if (!_particleFilters.containsKey(id)) {
            throw new IllegalArgumentException("Particle Filter not found");
        }
        return _particleFilters.get(id);
    }

    public ParticleFilterModel create(ParticleFilterModel particleFilterModel) {
        _particleFilters.put(particleFilterModel.id(), particleFilterModel);
        return particleFilterModel;
    }

}
