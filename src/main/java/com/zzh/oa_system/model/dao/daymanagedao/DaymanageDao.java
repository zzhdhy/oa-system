package com.zzh.oa_system.model.dao.daymanagedao;

import com.zzh.oa_system.model.entity.schedule.ScheduleList;
import com.zzh.oa_system.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:03
 * description:
 */
@Repository
public interface DaymanageDao extends JpaRepository<ScheduleList, Long> {

    List<ScheduleList> findByUser(User user);

    List<ScheduleList> findByUsers(List<User> users);

    Page<ScheduleList> findByUsers(List<User> users, Pageable pa);

    Page<ScheduleList> findByUser(User user, Pageable pa);

    Page<ScheduleList> findByUserAndUsers(User user, List<User> users, Pageable pa);
}

