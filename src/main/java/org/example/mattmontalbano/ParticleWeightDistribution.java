package org.example.mattmontalbano;

public class ParticleWeightDistribution {
    private double[] _cumulativeWeightArray;
    private Particle[] _particles;

    public ParticleWeightDistribution(Particle[] particles) {
        _cumulativeWeightArray = createCumulativeWeightArray(particles);
        _particles = particles;
    }

    private double[] createCumulativeWeightArray(Particle[] particles) {
        double[] cumulativeWeightArray = new double[particles.length - 1];
        double weightSum = 0;
        for (int i = 0; i < cumulativeWeightArray.length; i++) {
            weightSum += particles[i].getWeight();
            cumulativeWeightArray[i] = weightSum;
        }
        return cumulativeWeightArray;
    }

    public Particle sampleParticle() {
        double target = NewRandomSingleton.getInstance().nextDouble() * getTotalWeight();
        int particleIndex = binarySearch(target);
        return _particles[particleIndex];
    }

    private int binarySearch(double target) {
        int low = 0;
        int high = _cumulativeWeightArray.length - 1;
        while (low < high) {
            int mid = low + ((high - low) / 2);
            if (target >= _cumulativeWeightArray[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

    private double getTotalWeight() {
        return _cumulativeWeightArray[_cumulativeWeightArray.length - 1];
    }
}
