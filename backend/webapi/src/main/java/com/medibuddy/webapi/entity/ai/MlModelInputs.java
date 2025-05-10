package com.medibuddy.webapi.entity.ai;

import java.time.Instant;

import com.medibuddy.webapi.entity.BaseEntity;
import com.medibuddy.webapi.entity.account.User;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "ml_model_inputs")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MlModelInputs extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "age")
    private float age;

    @Column(name = "chest_pain_type")
    private String chestPainType;

    @Column(name = "serum_cholesterol")
    private long serumCholesterol;

    @Column(name = "resting_ecg")
    private String restingEcg;

    @Column(name = "exercise_induced_angina")
    private boolean exerciseInducedAngina;

    @Column(name = "st_slope")
    private String stSlope;

    @Column(name = "perfusion_scan")
    private String perfusionScan;

    @Column(name = "sex")
    private String sex;

    @Column(name = "resting_bp")
    private long restingBloodPressure;

    @Column(name = "fasting_blood_sugar_gt_120")
    private boolean fastingBloodSugarOver120;

    @Column(name = "max_heart_rate")
    private long maxHeartRate;

    @Column(name = "st_depression")
    private double stDepression;

    @Column(name = "vessels_colored")
    private long numVesselsColored;

    @Column(name = "weight_kg")
    private float weightKg;

    @Column(name = "height_cm")
    private float heightCm;

    @Column(name = "gender")
    private String gender;

    @Column(name = "dietary_restrictions")
    private String dietaryRestrictions;

    @Column(name = "allergy")
    private String allergy;

    @Column(name = "disease_type")
    private String diseaseType;

    @Column(name = "severity")
    private String severity;

    @Column(name = "physical_activity_level")
    private String physicalActivityLevel;

    @Column(name = "preferred_cuisine")
    private String preferredCuisine;

    @Column(name = "weekly_exercise_hours")
    private float weeklyExerciseHours;

    @Column(name = "adherence_to_diet_plan")
    private float adherenceToDietPlan;

    @Column(name = "dietary_nutrient_imbalance_score")
    private float dietaryNutrientImbalanceScore;

    @Column(name = "bmi")
    private float BMI;

    @Column(name = "daily_caloric_intake")
    private float dailyCaloricIntake;

    @Column(name = "cholesterol_mg_dl")
    private float cholesterolMgDL;

    @Column(name = "blood_pressure_mmHg")
    private float bloodPressureMmHg;

    @Column(name = "glucose_mg_dL")
    private float glucoseMgDL;

}
