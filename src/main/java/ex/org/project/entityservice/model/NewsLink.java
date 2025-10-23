package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "news_link")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @Column(name = "link_label")
    private String linkLabel;

    @Column(name = "link_url")
    private String linkUrl;

    @Column(name = "display_order")
    private Integer displayOrder;
}
