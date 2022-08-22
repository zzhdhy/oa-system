package com.zzh.oa_system.model.entity.user;

import javax.persistence.*;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:44
 * description:
 */
@Entity
@Table(name = "aoa_position")
public class Position {

    @Id
    @Column(name = "position_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //职位id

    @Column(unique = true)
    private String name;    //职位名称。

    private Integer level;    //职位层级

    private String describtion;//职位描述

    private Long deptid;

    public Position() {

    }

    public Position(String name, Integer level) {

        this.name = name;
        this.level = level;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDescribtion() {
        return describtion;
    }

    public void setDescribtion(String describtion) {
        this.describtion = describtion;
    }

    public Long getDeptid() {
        return deptid;
    }

    public void setDeptid(Long deptid) {
        this.deptid = deptid;
    }

    @Override
    public String toString() {
        return "Position [id=" + id + ", name=" + name + ", level=" + level + ", describtion=" + describtion
                + ", deptid=" + deptid + "]";
    }

}

