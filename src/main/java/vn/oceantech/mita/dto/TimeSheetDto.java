package vn.oceantech.mita.dto;

import vn.oceantech.mita.domain.TimeSheet;

import java.io.Serializable;
import java.util.Date;


public class TimeSheetDto implements Serializable {

    private static final long serialVersionUID = 6318192070978248463L;
    private Long id;

    private Date dateAttendance;

    private String message;

    private Boolean isOffline;

    private String ip;

    UserDto user;

    public TimeSheetDto() {

    }

    public TimeSheetDto(String message) {
        this.message = message;
    }

    public boolean isOffline() {
        return isOffline;
    }

    public void setOffline(boolean offline) {
        isOffline = offline;
    }

    public TimeSheetDto(TimeSheet entity) {
        this.id = entity.getId();
        this.ip = entity.getIp();
        this.message = entity.getNote();
        this.dateAttendance = entity.getDateAttendance();
        this.isOffline = entity.getOffline();
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
