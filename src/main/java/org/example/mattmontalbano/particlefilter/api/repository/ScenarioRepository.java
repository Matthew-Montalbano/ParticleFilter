package org.example.mattmontalbano.particlefilter.api.repository;

import org.example.mattmontalbano.particlefilter.algorithm.TimeStateObject;
import org.example.mattmontalbano.particlefilter.api.model.ScenarioModel;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ScenarioRepository {

    private final Map<String, ScenarioModel> _scenarios = new HashMap<>();

    public ScenarioRepository() {
        loadDefaultScenariosFromJSON();
    }

    public Map<String, ScenarioModel> findAll() {
        return _scenarios;
    }

    public ScenarioModel findById(String id) {
        if (!_scenarios.containsKey(id)) {
            throw new IllegalArgumentException("Scenario not found");
        }
        return _scenarios.get(id);
    }

    public ScenarioModel create(ScenarioModel scenarioModel) {
        _scenarios.put(scenarioModel.id(), scenarioModel);
        return scenarioModel;
    }

    private void loadDefaultScenariosFromJSON() {
        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader(
                "C:\\Users\\Matthew Montalbano\\Documents\\ParticleFilter\\src\\main\\resources\\scenarios.json")) {
            Object obj = parser.parse(reader);
            JSONArray scenarios = (JSONArray) ((JSONObject) obj).get("scenarios");
            for (Object scenario : scenarios) {
                JSONObject JSONScenario = (JSONObject) scenario;
                String scenarioId = (String) JSONScenario.get("id");
                JSONArray states = (JSONArray) JSONScenario.get("trueTargetStates");
                List<TimeStateObject> timeStates = new ArrayList<>();
                for (Object stateObject : states) {
                    JSONObject state = (JSONObject) stateObject;
                    timeStates.add(new TimeStateObject((double) state.get("x"),
                                                       (double) state.get("y"),
                                                       (double) state.get("xVelocity"),
                                                       (double) state.get("yVelocity"),
                                                       (long) state.get("time")));
                }
                double standardDeviation = (double) JSONScenario.get("standardDeviation");
                long seed = (long) JSONScenario.get("seed");
                _scenarios.put(scenarioId,
                               new ScenarioModel(scenarioId,
                                                 timeStates.toArray(new TimeStateObject[0]),
                                                 standardDeviation,
                                                 seed));
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}
