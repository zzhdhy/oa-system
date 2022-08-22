package com.zzh.oa_system.model.entity.process;

import com.zzh.oa_system.model.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:37
 * description:
 */
@Entity
@Table(name = "aoa_traffic")
//交通费用明细表
public class Traffic {

    @Id
    @Column(name = "traffic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trafficId;

    @OneToOne
    @JoinColumn(name = "user_name")
    private User user;//出差人员

    @Column(name = "depart_time")
    private Date departTime;//出发时间

    @Column(name = "depart_name")
    private String departName;//出发城市

    @Column(name = "reach_name")
    private String reachName;//到达城市

    @Column(name = "traffic_name")
    private String trafficName;//交通工具

    @Column(name = "seat_type")
    private String seatType;//座位类型

    @Column(name = "traffic_money")
    private Double trafficMoney;//交通标准

    @ManyToOne()
    @JoinColumn(name = "evection_id")
    private EvectionMoney evection;

    @Transient
    private String username;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getTrafficId() {
        return trafficId;
    }

    public void setTrafficId(Long trafficId) {
        this.trafficId = trafficId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getReachName() {
        return reachName;
    }

    public void setReachName(String reachName) {
        this.reachName = reachName;
    }

    public String getTrafficName() {
        return trafficName;
    }

    public void setTrafficName(String trafficName) {
        this.trafficName = trafficName;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Double getTrafficMoney() {
        return trafficMoney;
    }

    public void setTrafficMoney(Double trafficMoney) {
        this.trafficMoney = trafficMoney;
    }

    public EvectionMoney getEvection() {
        return evection;
    }

    public void setEvection(EvectionMoney evection) {
        this.evection = evection;
    }

    @Override
    public String toString() {
        return "Traffic [trafficId=" + trafficId + ", departTime=" + departTime + ", departName=" + departName
                + ", reachName=" + reachName + ", trafficName=" + trafficName + ", seatType=" + seatType
                + ", evection=" + evection + ", username=" + username + "]";
    }


}
