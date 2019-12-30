package ir.maktab.java32.projects.articleapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String brief;
    private String content;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "last_update_date")
    private Date lastUpdateDate;
    @Column(name = "publish_date")
    private Date publishDate;
    @Column(name = "is_publised")
    private boolean isPublished;

    @ManyToOne
    @ToString.Exclude
    private User user;

    @ManyToMany(mappedBy = "articles" , fetch = FetchType.EAGER)
    @ToString.Exclude
    private Category category;

    @ManyToMany(mappedBy = "articles" , fetch = FetchType.EAGER)
    @ToString.Exclude
    List<Tag> tags = new ArrayList<>();

}
