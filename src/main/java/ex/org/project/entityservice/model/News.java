package ex.org.project.entityservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "slug")
    private String slug;

    @Column(length = 1024)
    private String title;

    @Column(columnDefinition = "text")
    private String description;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private NewsType type;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "expiration_date")
    private Date expirationDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "modified_at")
    private Date modifiedAt;

    @Column(name = "modified_by")
    private Integer modifiedBy;

    @OneToMany(mappedBy="news", fetch = FetchType.EAGER)
    @OrderBy("display_order ASC")
    private List<NewsLink> links;
}
