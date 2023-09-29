package vn.oceantech.mita.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Table(
        name = "tbl_like"
)
@Entity
@Setter
@Getter
public class Likes implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;
    Integer type;
    @Temporal(TemporalType.TIMESTAMP) // Specify the temporal type
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Date date;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @ManyToOne
    @JoinColumn(name = "postId")
    Posts post;


    public Likes() {
    }

    public Likes(Long id, Integer type, Date date, User user, Posts post) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.user = user;
        this.post = post;
    }
}
