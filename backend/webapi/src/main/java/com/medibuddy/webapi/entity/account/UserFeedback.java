package com.medibuddy.webapi.entity.account;

import com.medibuddy.webapi.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "user_feedback")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserFeedback extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private FeedbackType type;

    @Column(name = "message", length = 500, nullable = false)
    private String message;

    public enum FeedbackType {
        COMPLAINT,
        FEATURE_REQUEST
    }

}
