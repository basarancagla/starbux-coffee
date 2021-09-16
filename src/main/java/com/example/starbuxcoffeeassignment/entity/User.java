package com.example.starbuxcoffeeassignment.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="TBL_USER")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_name")
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="user_role")
    private boolean userRole;

    @Column(name="create_user")
    private int createUser;

    @Column(name="create_time")
    private Date createTime;

    public User() {

    }

    public Long getId() {
        return id;
    }

    public User(String userName, String password, boolean userRole, int createUser, Date createTime) {
        this.userName = userName;
        this.password = password;
        this.userRole = userRole;
        this.createUser = createUser;
        this.createTime = createTime;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUserRole() {
        return userRole;
    }

    public boolean getUserRole() {
        return userRole;
    }

    public void setUserRole(boolean userRole) {
        this.userRole = userRole;
    }

    public int getCreateUser() {
        return createUser;
    }

    public void setCreateUser(int createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


}
