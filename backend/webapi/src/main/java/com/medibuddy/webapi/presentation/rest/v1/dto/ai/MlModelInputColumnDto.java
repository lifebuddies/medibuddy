package com.medibuddy.webapi.presentation.rest.v1.dto.ai;

import com.medibuddy.webapi.entity.ai.MlModelColumn.DataType;

public record MlModelInputColumnDto(
	String name,
	DataType dataType,
	String description
)  {

}
