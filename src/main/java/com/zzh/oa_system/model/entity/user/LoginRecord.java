package com.zzh.oa_system.model.entity.user;

import javax.persistence.*;
import java.util.Date;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:44
 * description:
 */
@Entity
@Table(name = "aoa_user_login_record")
public class LoginRecord {

    @Id
    @Column(name = "record_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //记录id

//	@Column(name = "user_id")
//	private Long userId;	//所属用户  Manytoone   外键 user_id

    @Column(name = "ip_addr")
    private String ipAddr;    //ip地址

    @Column(name = "login_time")
    private Date loginTime;    //登陆时间

    @Column(name = "session_id")
    private String sessionId;    //session id

    private String browser;    //使用浏览器


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public LoginRecord(String ipAddr, Date loginTime, String browser, User user) {
        super();
        this.ipAddr = ipAddr;
        this.loginTime = loginTime;
        this.browser = browser;
        this.user = user;
    }

    public LoginRecord() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "LoginRecord [id=" + id + ", ipAddr=" + ipAddr + ", loginTime=" + loginTime + ", sessionId=" + sessionId
                + ", browser=" + browser + "]";
    }


}
