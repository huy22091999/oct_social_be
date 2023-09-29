package vn.oceantech.mita.domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@XmlRootElement
@Table(
        name = "tbl_time_sheet"
)
@Entity
public class TimeSheet implements Serializable {
    @Transient
    private static final long serialVersionUID = 6318192070978248463L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(name = "date_attendance")
    private Date dateAttendance;


    @Column(name = "note")
    private String note;

    @Column(name = "ip")
    private String ip;

    @ManyToOne
    @JoinColumn(name = "userId")
    User user;

    @Column(name = "isOffline")
    private Boolean isOffline;

    public TimeSheet() {
    }

    public TimeSheet(Long id, Date dateAttendance, String note, String ip, User user, Boolean isOffline) {
        this.id = id;
        this.dateAttendance = dateAttendance;
        this.note = note;
        this.ip = ip;
        this.user = user;
        this.isOffline = isOffline;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateAttendance() {
        return dateAttendance;
    }

    public void setDateAttendance(Date dateAttendance) {
        this.dateAttendance = dateAttendance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
        this.isOffline = Objects.equals(ip, "222.252.21.66");
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getOffline() {
        return isOffline;
    }

    public void setOffline(Boolean offline) {
        isOffline = offline;
    }
}
