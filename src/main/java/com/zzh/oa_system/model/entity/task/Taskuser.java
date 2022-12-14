package com.zzh.oa_system.model.entity.task;

import com.zzh.oa_system.model.entity.user.User;

import javax.persistence.*;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 15:43
 * description:
 */
@Entity
@Table(name = "aoa_task_user")
//任务接收人联系表
public class Taskuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_id")
    private Long pkId;//任务接收人主键id


    @ManyToOne
    @JoinColumn(name = "task_id")
    private Tasklist taskId;//任务id外键

    @ManyToOne
    @JoinColumn(name = "task_recive_user_id")
    private User userId;//接收人id外键

    @Column(name = "status_id")
    private Integer statusId;//任务状态id


    public Taskuser() {
        // TODO Auto-generated constructor stub
    }

    public Long getPkId() {
        return pkId;
    }

    public void setPkId(Long pkId) {
        this.pkId = pkId;
    }


    public Tasklist getTaskId() {
        return taskId;
    }

    public void setTaskId(Tasklist taskId) {
        this.taskId = taskId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    @Override
    public String toString() {
        return "Taskuser [pkId=" + pkId + ", statusId=" + statusId + "]";
    }


}
