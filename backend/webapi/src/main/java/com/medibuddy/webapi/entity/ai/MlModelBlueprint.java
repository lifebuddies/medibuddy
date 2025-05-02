package com.medibuddy.webapi.entity.ai;

import java.util.List;

import com.medibuddy.webapi.entity.BaseEntity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.*;

@Entity
@Table(name = "ml_models")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MlModelBlueprint extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true)
    private String name;
    
    @Column(name = "description", length = 500, nullable = true)
    private String description;
    
    @Column(name = "locale", nullable = true)
    private String locale;

    @Enumerated(EnumType.STRING)
    @Column(name = "model_state", nullable = false)
    private State modelState;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "model_inputs",
        joinColumns = @JoinColumn(name = "model_id"),
        inverseJoinColumns = @JoinColumn(name = "input_id")
    )
    @OrderColumn(name = "input_column_order")
    private List<MlModelColumn> inputsColumns;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Column(name = "output_column")
    private MlModelColumn outputColumn;

    public enum State {
        RUNNING,
        STOPPED,
        UNDER_DEVELOPMENT,
        UNDER_MAINTENANCE
    }

}
