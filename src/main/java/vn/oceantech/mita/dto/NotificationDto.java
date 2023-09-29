package vn.oceantech.mita.dto;

import vn.oceantech.mita.domain.Notification;

import java.io.Serializable;
import java.util.Date;


public class NotificationDto implements Serializable {

    private static final long serialVersionUID = 6318192070978248463L;
    private Long id;

    private Date date;

    private String title;

    private String body;

    private String type;

    UserDto user;

    public NotificationDto() {

    }

    public NotificationDto(Notification entity) {
        this.id = entity.getId();
        this.date = entity.getDate();
        this.title = entity.getTitle();
        this.body = entity.getTitle();
        this.type = entity.getType();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
