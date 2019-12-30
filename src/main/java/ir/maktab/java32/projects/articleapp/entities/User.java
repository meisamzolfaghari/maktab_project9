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
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "national_code")
    private String nationalCode;

    @Column(name = "birth_day")
    private Date birthDay;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "user" , fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Article> articles = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER , mappedBy = "users")
    @ToString.Exclude
    private List<Role> roles = new ArrayList<>();
}
