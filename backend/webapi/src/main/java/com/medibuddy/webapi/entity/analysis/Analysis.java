package com.medibuddy.webapi.entity.analysis;

import java.time.Instant;

import com.medibuddy.webapi.entity.BaseEntity;
import com.medibuddy.webapi.entity.account.User;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "analyses")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Analysis extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "analysis_type_id")
    private AnalysisType analysisType;

    @Column(name = "requested_at", nullable = false)
    private Instant requestedAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public enum Status {
        PENDING,
        PROCESSING,
        COMPLETED
    }

}
