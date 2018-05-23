package com.stasio.database.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "title")
    private String title;

    @NotNull
    @Column(name = "link")
    private String link;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = User.class)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, targetEntity = Tag.class)
    private List<Tag> tags;

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", user=" + user +
                ", tags=" + tags +
                '}';
    }

    public Film() {
    }

    public Film(String title, String link, User user, List<Tag> tags) {
        this.title = title;
        this.link = link;
        this.user = user;
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }
}
