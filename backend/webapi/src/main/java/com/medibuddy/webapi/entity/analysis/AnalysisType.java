package com.medibuddy.webapi.entity.analysis;

import com.medibuddy.webapi.entity.BaseEntity;
import com.medibuddy.webapi.entity.ai.MlModelBlueprint;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "analysis_types")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class AnalysisType extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "locale", nullable = false)
    private String locale;

	@OneToOne(optional = false)
	@Column(name = "model_id", unique = true)
	private MlModelBlueprint model;

}
