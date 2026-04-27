package org.canopyplatform.canopy.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lkup_property_type")
@Data
public class LkupPropertyType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;


    @Column(name="name")
    private String name;


}
