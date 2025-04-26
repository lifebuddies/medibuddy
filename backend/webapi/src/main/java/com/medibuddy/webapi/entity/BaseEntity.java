package com.medibuddy.webapi.entity;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	protected UUID id;

	@Version
	protected Long version;

	@CreatedDate
	@Column(name = "created_at", nullable = false, updatable = false)
	protected Instant createdAt;

	@LastModifiedDate
	@Column(name = "updated_at", nullable = false)
	protected Instant updatedAt;

	@CreatedBy
	@Column(name = "created_by", length = 50, updatable = false)
	protected String createdBy;

	@LastModifiedBy
	@Column(name = "updated_by", length = 50)
	protected String lastModifiedBy;

}
