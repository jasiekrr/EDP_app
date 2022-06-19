package com.example.edpapp.models;

import java.util.HashMap;
import java.util.Map;

public class BuildingsCostsCalculator {
    private final int difficultyLevel;

    public BuildingsCostsCalculator(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
    public Map<String, Double> getBuildingCosts(String buildingType, int level) {
        switch (buildingType) {
            case "barracks" -> {
                return getBarracksCosts(level);
            }
            case "blacksmith" -> {
                return getBlacksmithCosts(level);
            }
            case "castle" -> {
                return getCastleCosts(level);
            }
            case "farm" -> {
                return getFarmCosts(level);
            }
            case "lumberjack" -> {
                return getLumberjackCosts(level);
            }
            case "mine" -> {
                return getMineCosts(level);
            }
            case "port" -> {
                return getPortCosts(level);
            }
            case "watchtower" -> {
                return getWatchtowerCosts(level);
            }
            case "houses" -> {
                return getHousesCosts(level);
            }
        }
        return null;
    }

    public Map<String, Double> getHousesCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 1000 + difficultyLevel * 500.0);
            put("stone", level * 500.0 + difficultyLevel * 100.0);
            put("food", level * 1000 + difficultyLevel * 700.0);
            put("iron", 0.0);
            put("gold", 0.0);
        }};
    }

    public Map<String, Double> getWatchtowerCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 200.0 + difficultyLevel * 500.0);
            put("stone", level * 500.0 + difficultyLevel * 100.0);
            put("food", level * 500.0 + difficultyLevel * 700.0);
            put("iron", level * 100.0);
            put("gold", 0.0);
        }};
    }

    public Map<String, Double> getPortCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 1000.0 + difficultyLevel * 500.0);
            put("stone", level * 1000.0 + difficultyLevel * 100.0);
            put("food", level * 200.0 + difficultyLevel * 700.0);
            put("iron", level * 200.0);
            put("gold", level * 200.0 + difficultyLevel * 100.0);
        }};
    }

    public Map<String, Double> getMineCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 500.0 + difficultyLevel * 500.0);
            put("stone", level * 100.0 + difficultyLevel * 100.0);
            put("food", level * 1000.0 + difficultyLevel * 700.0);
            put("iron", level * 500.0);
            put("gold", 0.0);
        }};
    }

    public Map<String, Double> getLumberjackCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 100.0 + difficultyLevel * 100.0);
            put("stone", level * 100.0 + difficultyLevel * 100.0);
            put("food", level * 100.0 + difficultyLevel * 700.0);
            put("iron", level * 100.0);
            put("gold", 0.0);
        }};
    }

    public Map<String, Double> getFarmCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 100.0 + difficultyLevel * 1000.0);
            put("stone", level * 100.0 + difficultyLevel * 100.0);
            put("food", level * 100.0 + difficultyLevel * 100.0);
            put("iron", 0.0);
            put("gold", 0.0);
        }};
    }

    public Map<String, Double> getBarracksCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 100.0 + difficultyLevel * 1000.0);
            put("stone", level * 100.0 + difficultyLevel * 500.0);
            put("food", level * 100.0 + difficultyLevel * 500.0);
            put("iron", level * 100.0 + difficultyLevel * 500.0);
            put("gold", 0.0);
        }};
    }
    public Map<String, Double> getBlacksmithCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 100.0 + difficultyLevel * 1000.0);
            put("stone", level * 100.0 + difficultyLevel * 500.0);
            put("food", level * 100.0 + difficultyLevel * 500.0);
            put("iron", 0.0);
            put("gold", level * 100.0 + difficultyLevel * 100.0);
        }};
    }

    public Map<String, Double> getCastleCosts(int level) {
        return new HashMap<>() {{
            put("wood", level * 300.0 + difficultyLevel * 1000.0);
            put("stone", level * 300.0 + difficultyLevel * 500.0);
            put("food", level * 100.0 + difficultyLevel * 500.0);
            put("iron", level * 100.0 + difficultyLevel * 500.0);
            put("gold", level * 100.0 + difficultyLevel * 100.0);
        }};
    }
}