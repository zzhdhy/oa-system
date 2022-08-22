package com.zzh.oa_system.model.entity.note;

import com.zzh.oa_system.model.entity.user.User;

import javax.persistence.*;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:24
 * description:目录表 此处有一个parentid需要连接
 */
@Entity
@Table(name = "aoa_catalog")
public class Catalog {

    @Id
    @Column(name = "catalog_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long catalogId; //目录id


    @Column(name = "catalog_name")
    private String catalogName; //目录名字

    @ManyToOne
    @JoinColumn(name = "cata_user_id")
    private User user;

    //判断id
    @Column(name = "parent_id")
    private Integer parentId;


    @Override
    public String toString() {
        return "Catalog [catalogName=" + catalogName + "]";
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Catalog() {
        super();
        // TODO Auto-generated constructor stub
    }


    public Catalog(String catalogName, User user) {
        super();
        this.catalogName = catalogName;
        this.user = user;
    }


}
