package com.medibuddy.webapi.entity.analysis;

import java.time.Instant;

import com.medibuddy.webapi.entity.BaseEntity;
import com.medibuddy.webapi.entity.account.User;
import com.medibuddy.webapi.entity.ai.MlModelColumn;

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
    @JoinColumn(name = "username")
    private User user;

    @Column(name = "date", nullable = false)
    private Instant date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "input_column_id")
    private MlModelColumn column;

    @Column(name = "value", nullable = false)
    private String value;

}
