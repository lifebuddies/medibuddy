package com.medibuddy.webapi.entity.analysis;

import com.medibuddy.webapi.entity.BaseEntity;
import com.medibuddy.webapi.entity.analysis.Diagnosis.Criticality;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "disease_advice")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class DiseaseAdvice extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "analysis_type_id")
    private AnalysisType analysisType;

    @Column(name = "criticality", nullable = false)
    @Enumerated(EnumType.STRING)
    private Criticality criticality;

    @Column(name = "message", length = 500, nullable = false)
    private String message;

    @Column(name = "locale", nullable = false)
    private String locale;

}
