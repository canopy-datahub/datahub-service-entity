package org.canopyplatform.canopy.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lkup_center")
@Data
public class LookupCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "description")
    private String description;
}
