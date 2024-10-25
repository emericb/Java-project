package org.project.javaproject.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NutrimentDetails {
    @JsonProperty("energy-kcal")
    private double energyKcal;
}
