package com.medibuddy.webapi.entity.analysis;

import java.time.Instant;

import com.medibuddy.webapi.entity.BaseEntity;
import com.medibuddy.webapi.entity.account.User;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "medical_records")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MedicalRecord extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "age")
    private Integer age;

    @Column(name = "chest_pain_type")
    private String chestPainType;

    @Column(name = "serum_cholesterol")
    private Integer serumCholesterol;

    @Column(name = "resting_ecg")
    private String restingEcg;

    @Column(name = "exercise_induced_angina")
    private Boolean exerciseInducedAngina;

    @Column(name = "st_slope")
    private String stSlope;

    @Column(name = "perfusion_scan")
    private String perfusionScan;

    @Column(name = "sex")
    private String sex;

    @Column(name = "resting_bp")
    private Integer restingBloodPressure;

    @Column(name = "fasting_blood_sugar_gt_120")
    private Boolean fastingBloodSugarOver120;

    @Column(name = "max_heart_rate")
    private Integer maxHeartRate;

    @Column(name = "st_depression")
    private Double stDepression;

    @Column(name = "vessels_colored")
    private Integer numVesselsColored;

}
