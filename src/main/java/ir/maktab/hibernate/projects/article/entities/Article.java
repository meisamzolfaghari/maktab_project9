package ir.maktab.hibernate.projects.article.entities;

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
    private User user;

    @ManyToOne
    private Category category;

    @ManyToMany(mappedBy = "articles" , fetch = FetchType.EAGER)
    @ToString.Exclude
    List<Tag> tags = new ArrayList<>();

    public Article(String title, String brief, String content, Date createDate, boolean isPublished, User user, Category category, List<Tag> tags) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.isPublished = isPublished;
        this.user = user;
        this.category = category;
        this.tags = tags;
    }

    public Article(String title, String brief, String content, Date createDate, Date lastUpdateDate, Date publishDate, boolean isPublished, User user, Category category, List<Tag> tags) {
        this.title = title;
        this.brief = brief;
        this.content = content;
        this.createDate = createDate;
        this.lastUpdateDate = lastUpdateDate;
        this.publishDate = publishDate;
        this.isPublished = isPublished;
        this.user = user;
        this.category = category;
        this.tags = tags;
    }
}
