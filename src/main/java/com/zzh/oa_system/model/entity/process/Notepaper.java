package com.zzh.oa_system.model.entity.process;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zzh.oa_system.model.entity.user.User;

import javax.persistence.*;
import java.util.Date;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:34
 * description:
 */
@Entity
@Table(name = "aoa_notepaper")
/**
 * 便签表
 *
 */
public class Notepaper {


    @Id
    @Column(name = "notepaper_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notepaperId;            //主键id

    private String title;                //便签标题

    @Column(columnDefinition = "text")    //便签内容
    private String concent;


    @ManyToOne
    @JoinColumn(name = "notepaper_user_id")
    //demo
    @JsonIgnore
    private User userId;                //编写便签的用户

    @Column(name = "create_time")
    private Date createTime;            //便签创建时间

    public Long getNotepaperId() {
        return notepaperId;
    }

    public void setNotepaperId(Long notepaperId) {
        this.notepaperId = notepaperId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConcent() {
        return concent;
    }

    public void setConcent(String concent) {
        this.concent = concent;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Notepaper [notepaperId=" + notepaperId + ", title=" + title + ", concent=" + concent + ", createTime="
                + createTime + "]";
    }


}

