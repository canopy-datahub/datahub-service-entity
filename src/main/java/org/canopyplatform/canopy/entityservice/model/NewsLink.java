package org.canopyplatform.canopy.entityservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
