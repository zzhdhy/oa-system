package com.zzh.oa_system.model.entity.process;

import javax.persistence.*;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:33
 * description:
 */

@Table
@Entity(name = "aoa_holiday")
//请假表
public class Holiday {

    @Id
    @Column(name = "holiday_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long holidayId;

    @Column(name = "type_id")
    private Long typeId;  //请假类型

    @Column(name = "leave_days")
    private Double leaveDays; //请假天数

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pro_id")
    private ProcessList proId;

    @Column(name = "personnel_advice")
    private String personnelAdvice;//人事部意见及说明

    @Column(name = "manager_advice")
    private String managerAdvice;//经理意见及说明

    @Transient
    private String nameuser;


    public String getPersonnelAdvice() {
        return personnelAdvice;
    }

    public void setPersonnelAdvice(String personnelAdvice) {
        this.personnelAdvice = personnelAdvice;
    }

    public String getManagerAdvice() {
        return managerAdvice;
    }

    public void setManagerAdvice(String managerAdvice) {
        this.managerAdvice = managerAdvice;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Double getLeaveDays() {
        return leaveDays;
    }

    public void setLeaveDays(Double leaveDays) {
        this.leaveDays = leaveDays;
    }

    public ProcessList getProId() {
        return proId;
    }

    public void setProId(ProcessList proId) {
        this.proId = proId;
    }

    @Override
    public String toString() {
        return "Holiday [holidayId=" + holidayId + ", typeId=" + typeId + ", leaveDays=" + leaveDays
                + ", personnelAdvice=" + personnelAdvice + ", managerAdvice=" + managerAdvice + ", nameuser=" + nameuser
                + "]";
    }


}
