package vn.oceantech.mita.dto;


import vn.oceantech.mita.domain.Role;
import vn.oceantech.mita.domain.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UserDto {
    private Long id;
    private String displayName;
    private String username;
    private String password;
    private String oldPassword;
    private String confirmPassword;
    private boolean isSetPassword;
    private boolean changePass;
    private Boolean active;
    private String lastName;
    private String firstName;
    private LocalDateTime dob;
    private String birthPlace;
    private String email;
    private boolean hasPhoto;
    private Set<RoleDto> roles = new HashSet();

    private String gender;
    private String university;
    private Integer year; //năm 1-5, 6- đã tốt nghiêm

    private Integer countDayCheckin;

    private Integer countDayTracking;

    private String tokenDevice;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;

    public UserDto() {
    }

    public UserDto(User entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.displayName = entity.getDisplayName();
            this.username = entity.getUsername();
            this.password = entity.getPassword();
            this.confirmPassword = entity.getConfirmPassword();
            this.active = entity.getActive();
            this.email = entity.getEmail();
            this.gender = entity.getGender();
            this.university = entity.getUniversity();
            this.year = entity.getYear();
            this.countDayCheckin = entity.getCountDayCheckin();
            this.countDayTracking = entity.getCountDayTracking();
            this.tokenDevice = entity.getTokenDevice();
            this.image = entity.getImage();
            Iterator var2;
            if (entity.getRoles() != null) {
                this.roles.clear();
                var2 = entity.getRoles().iterator();

                while (var2.hasNext()) {
                    Role role = (Role) var2.next();
                    this.roles.add(new RoleDto(role));
                }
            }
        }
    }

    public Integer getCountDayCheckin() {
        return countDayCheckin;
    }

    public String getTokenDevice() {
        return tokenDevice;
    }

    public void setTokenDevice(String tokenDevice) {
        this.tokenDevice = tokenDevice;
    }

    public void setCountDayCheckin(Integer countDayCheckin) {
        this.countDayCheckin = countDayCheckin;
    }

    public Integer getCountDayTracking() {
        return countDayTracking;
    }

    public void setCountDayTracking(Integer countDayTracking) {
        this.countDayTracking = countDayTracking;
    }

    public User toEntity() {
        User entity = new User();
        if (this.id != null) {
            entity.setId(this.id);
        }
        entity.setUniversity(this.university);
        entity.setYear(this.year);
        entity.setDisplayName(this.displayName);
        entity.setUsername(this.username);
        entity.setPassword(this.password);
        entity.setActive(this.active);
        entity.setEmail(this.email);
        entity.setAccountNonExpired(true);
        entity.setAccountNonLocked(true);
        entity.setCredentialsNonExpired(true);
        entity.setTokenDevice(this.tokenDevice);
        Iterator var2;
        if (this.roles.size() > 0) {
            var2 = this.roles.iterator();

            while (var2.hasNext()) {
                RoleDto dto = (RoleDto) var2.next();
                entity.getRoles().add(dto.toEntity());
            }
        }
        return entity;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public boolean isSetPassword() {
        return this.isSetPassword;
    }

    public void setSetPassword(boolean isSetPassword) {
        this.isSetPassword = isSetPassword;
    }

    public boolean getChangePass() {
        return this.changePass;
    }

    public void setChangePass(boolean changePass) {
        this.changePass = changePass;
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public LocalDateTime getDob() {
        return this.dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public String getBirthPlace() {
        return this.birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public boolean isHasPhoto() {
        return this.hasPhoto;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Set<RoleDto> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<RoleDto> roles) {
        this.roles = roles;
    }

    public Boolean getActive() {
        return this.active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}
