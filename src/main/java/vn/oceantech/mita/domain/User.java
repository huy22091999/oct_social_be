package vn.oceantech.mita.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "tbl_user"
)
@XmlRootElement
public class User implements UserDetails {
    private static final long serialVersionUID = 4572941405687566992L;
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(
            name = "username",
            length = 100,
            nullable = false,
            unique = true
    )
    private String username;
    @Column(
            name = "password",
            nullable = false
    )
    private String password;
    @Column(
            name = "display_name",
            nullable = false
    )
    private String displayName;
    @Column(
            name = "just_created",
            nullable = false
    )
    private Boolean justCreated = true;
    @Column(
            name = "last_login_time",
            nullable = true
    )
    private Date lastLoginTime;
    @Column(
            name = "total_login_failures",
            nullable = true
    )
    private Long totalLoginFailures;
    @Column(
            name = "last_login_failures",
            nullable = true
    )
    private Long lastLoginFailures;
    @Column(
            name = "email",
            length = 150,
            nullable = true,
            unique = false
    )
    private String email;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @Fetch(FetchMode.SELECT)
    @JoinTable(
            name = "tbl_user_role",
            joinColumns = {@JoinColumn(
                    name = "user_id"
            )},
            inverseJoinColumns = {@JoinColumn(
                    name = "role_id"
            )}
    )
    private Set<Role> roles = new HashSet();

    @Transient
    private Boolean changePassword = false;
    @Transient
    private String confirmPassword;
    @Column(
            name = "active",
            nullable = false
    )
    private Boolean active = true;
    @Column(
            name = "account_non_expired",
            nullable = true
    )
    private Boolean accountNonExpired = true;
    @Column(
            name = "account_non_locked",
            nullable = true
    )
    private Boolean accountNonLocked = true;
    @Column(
            name = "credentials_non_expired",
            nullable = true
    )
    private Boolean credentialsNonExpired = true;

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

    public Integer getCountDayCheckin() {
        return countDayCheckin;
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

    public String getTokenDevice() {
        return tokenDevice;
    }

    public void setTokenDevice(String tokenDevice) {
        this.tokenDevice = tokenDevice;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getJustCreated() {
        return this.justCreated;
    }

    public void setJustCreated(Boolean justCreated) {
        this.justCreated = justCreated;
    }

    public Date getLastLoginTime() {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getTotalLoginFailures() {
        return this.totalLoginFailures;
    }

    public void setTotalLoginFailures(Long totalLoginFailures) {
        this.totalLoginFailures = totalLoginFailures;
    }

    public Long getLastLoginFailures() {
        return this.lastLoginFailures;
    }

    public void setLastLoginFailures(Long lastLoginFailures) {
        this.lastLoginFailures = lastLoginFailures;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Boolean getActive() {
        return this.active;
    }

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Boolean getAccountNonExpired() {
        return this.accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return this.accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @JsonIgnore
    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet();
        authorities.addAll(this.roles);
        return authorities;
    }

    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    public boolean isEnabled() {
        return this.active;
    }

    public Boolean getChangePassword() {
        return this.changePassword;
    }

    public void setChangePassword(Boolean changePassword) {
        this.changePassword = changePassword;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
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

    public User() {
    }

    public User(Long id, String username, String email, Boolean accountNonLocked) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.accountNonLocked = accountNonLocked;
    }

    public User(Long id, String username, String email, String displayName, Boolean accountNonLocked, Boolean accountNonExpired) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.displayName = displayName;
        this.accountNonLocked = accountNonLocked;
        this.accountNonExpired = accountNonExpired;
    }

    public User(User user, boolean isSetPerson) {
        this.accountNonExpired = user.isAccountNonExpired();
        this.accountNonLocked = user.isAccountNonLocked();
        this.active = user.getActive();
        this.changePassword = user.changePassword;
        this.confirmPassword = user.confirmPassword;
        this.credentialsNonExpired = user.isCredentialsNonExpired();
        this.email = user.getEmail();
        this.justCreated = user.getJustCreated();
        this.lastLoginFailures = user.getLastLoginFailures();
        this.lastLoginTime = user.getLastLoginTime();
        this.password = user.getPassword();
        this.changePassword = user.getChangePassword();
        this.confirmPassword = user.getConfirmPassword();
        this.username = user.getUsername();
        this.displayName = user.getDisplayName();
        this.setId(user.getId());
        this.image = user.getImage();
        this.roles = user.getRoles();


    }

}
