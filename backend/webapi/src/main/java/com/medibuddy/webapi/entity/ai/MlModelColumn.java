package com.medibuddy.webapi.entity.ai;

import com.medibuddy.webapi.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "ml_model_columns")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MlModelColumn extends BaseEntity {

	@Column(name = "name", nullable = false, unique = true)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "data_type", nullable = false)
	private DataType dataType;

	@Column(name = "description", length = 500, nullable = false)
	private String description;

	@Column(name = "locale", nullable = true)
	private String locale;

	public enum DataType {
		BOOLEAN,
		INTEGER,
		LONG,
		FLOAT,
		DOUBLE,
		STRING
	}

}
