package org.example.mattmontalbano;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ParticleWeightDistributionTest {

    @Test
    public void givenRandomZero_whenSampleParticle_returnFirstElement() {
        Particle[] particles = generateParticleMocksWithWeights(new double[] {0.2, 0.5, 0.3});
        ParticleWeightDistribution dist = new ParticleWeightDistribution(particles);
        NewRandom randomMock = mock(NewRandom.class);
        when(randomMock.nextDouble()).thenReturn(0.0);

        Particle expected = particles[0];
        Particle actual = dist.sampleParticle(randomMock);

        assertEquals(expected, actual);
    }

    @Test
    public void givenRandomOne_whenSampleParticle_returnLastElement() {
        Particle[] particles = generateParticleMocksWithWeights(new double[] {0.2, 0.5, 0.3});
        ParticleWeightDistribution dist = new ParticleWeightDistribution(particles);
        NewRandom randomMock = mock(NewRandom.class);
        when(randomMock.nextDouble()).thenReturn(1.0);

        Particle expected = particles[2];
        Particle actual = dist.sampleParticle(randomMock);

        assertEquals(expected, actual);
    }

    @Test
    public void givenSingleParticle_whenSampleParticle_returnOnlyParticle() {
        Particle[] particles = generateParticleMocksWithWeights(new double[] {1});
        ParticleWeightDistribution dist = new ParticleWeightDistribution(particles);
        NewRandom randomMock = mock(NewRandom.class);
        when(randomMock.nextDouble()).thenReturn(1.0);

        Particle expected = particles[0];
        Particle actual = dist.sampleParticle(randomMock);

        assertEquals(expected, actual);
    }

    private Particle[] generateParticleMocksWithWeights(double[] weights) {
        Particle[] particleMocks = new Particle[weights.length];
        for (int i = 0; i < weights.length; i++) {
            Particle particleMock = mock(Particle.class);
            when(particleMock.getWeight()).thenReturn(weights[i]);
            particleMocks[i] = particleMock;
        }
        return particleMocks;
    }
}
