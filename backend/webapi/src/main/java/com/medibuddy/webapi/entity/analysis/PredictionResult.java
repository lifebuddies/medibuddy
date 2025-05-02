package com.medibuddy.webapi.entity.analysis;

import java.time.Instant;

import com.medibuddy.webapi.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "prediction_results")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class PredictionResult extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "analysis_request_id")
    private AnalysisRequest analysisRequest;

    @Column(name = "disease_probability", nullable = false)
    private Double diseaseProbability;

    @Column(name = "predicted_at", nullable = false)
    private Instant predictedAt;

    @Column(name = "notified", nullable = false)
    private Boolean notified;

}
