package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "lkup_event_type")
@Data
public class EventType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 1024)
    private String name;

}
