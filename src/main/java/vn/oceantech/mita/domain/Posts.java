package vn.oceantech.mita.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@XmlRootElement
@Table(
        name = "tbl_post"
)
@Entity
@Setter
@Getter
public class Posts implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;
    String content;
    @Temporal(TemporalType.TIMESTAMP) // Specify the temporal type
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Date date;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @OneToMany(mappedBy = "post")
    Set<Document> media;

    @OneToMany(mappedBy = "post")
    Set<Likes> likes;

    @OneToMany(mappedBy = "post")
    Set<Comments> comments;

    public Posts() {
    }

    public Posts(Long id, String content, Date date, User user, Set<Document> media, Set<Likes> likes, Set<Comments> comments) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.user = user;
        this.media = media;
        this.likes = likes;
        this.comments = comments;
    }
}
