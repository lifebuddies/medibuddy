package com.medibuddy.webapi.entity.account;

import com.medibuddy.webapi.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "newsletter_subscriptions")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class NewsletterSubscription extends BaseEntity {

    @ManyToOne(optional = true)
    @JoinColumn(name = "user_id", nullable = true)
    private User user;

    @Column(name = "email", nullable = false)
    private String email;

}
