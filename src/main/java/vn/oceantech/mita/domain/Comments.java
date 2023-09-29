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
        name = "tbl_comments"
)
@Entity
@Setter
@Getter
public class Comments implements Serializable {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    Long id;
    String content;

    @ManyToOne
    @JoinColumn(name = "postId")
    Posts post;

    @Temporal(TemporalType.TIMESTAMP) // Specify the temporal type
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    Date date;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    public Comments() {
    }

    public Comments(Long id, String content, Posts post, Date date, User user) {
        this.id = id;
        this.content = content;
        this.post = post;
        this.date = date;
        this.user = user;
    }
}
