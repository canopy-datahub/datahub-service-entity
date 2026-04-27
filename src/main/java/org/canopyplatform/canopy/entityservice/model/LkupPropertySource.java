package org.canopyplatform.canopy.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lkup_property_source")
@Data
public class LkupPropertySource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    private String name;

}
