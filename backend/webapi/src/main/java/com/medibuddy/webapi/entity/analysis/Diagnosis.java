package com.medibuddy.webapi.entity.analysis;

import java.time.Instant;

import com.medibuddy.webapi.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "diagnoses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Diagnosis extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;

    @Column(name = "ml_model_result", nullable = false)
    private String mlModelResult;

    @Column(name = "predicted_at", nullable = false)
    private Instant predictedAt;

    @Column(name = "notified", nullable = false)
    private Boolean notified;

}
