package vn.oceantech.mita.dto;

import vn.oceantech.mita.domain.Tracking;
import vn.oceantech.mita.domain.User;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

public class TrackingDto {
    Long id;
    String content;
    Date date;
    UserDto user;

    public TrackingDto(){
    }

    public TrackingDto(Tracking entity){
        this.id=entity.getId();
        this.content=entity.getContent();
        this.date=entity.getDate();
        this.user=new UserDto(entity.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
