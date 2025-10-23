package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "event_link")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @Column(name = "link_label", length = 255)
    private String linkLabel;

    @Column(name = "link_url", columnDefinition = "text")
    private String linkUrl;

    @Column(name = "display_order")
    private Integer displayOrder;
}
