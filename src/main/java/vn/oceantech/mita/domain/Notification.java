package vn.oceantech.mita.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@XmlRootElement
@Table(
        name = "tbl_notification"
)
@Entity
public class Notification implements Serializable {
    @Transient
    private static final long serialVersionUID = 6318192070978248463L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "date")
    private Date date;
    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;

    @Column(name = "type")
    private String type;
    @ManyToOne
    @JoinColumn(name = "userId")
    User user;


    public Notification() {
    }

    public Notification(Long id, Date date, String title, String body, User user, String type) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.body = body;
        this.type = type;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
