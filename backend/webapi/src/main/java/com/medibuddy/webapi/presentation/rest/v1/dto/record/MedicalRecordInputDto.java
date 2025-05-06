package com.medibuddy.webapi.presentation.rest.v1.dto.record;

public record MedicalRecordInputDto(
    Integer age,
    String chestPainType,
    Integer serumCholesterol,
    String restingEcg,
    Boolean exerciseInducedAngina,
    String stSlope,
    String perfusionScan,
    String sex,
    Integer restingBloodPressure,
    Boolean fastingBloodSugarOver120,
    Integer maxHeartRate,
    Double stDepression,
    Integer numVesselsColored,
    Float weightKg,
    Float heightCm,
    String gender,
    String dietaryRestrictions,
    String allergy,
    String diseaseType,
    String severity,
    String physicalActivityLevel,
    String preferredCuisine,
    Float weeklyExerciseHours,
    Float adherenceToDietPlan,
    Float dietaryNutrientImbalanceScore,
    Float BMI,
    Float dailyCaloricIntake,
    Float cholesterolMgDL,
    Float bloodPressureMmHg,
    Float glucoseMgDL
) {

}
