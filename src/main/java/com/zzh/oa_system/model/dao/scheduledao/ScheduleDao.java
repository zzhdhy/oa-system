package com.zzh.oa_system.model.dao.scheduledao;

import com.zzh.oa_system.model.entity.schedule.ScheduleList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:43
 * description:
 */
@Repository
public interface ScheduleDao extends JpaRepository<ScheduleList, Long> {

    @Query("from ScheduleList s where s.user.userId=?1")
    List<ScheduleList> findstart(long userid);
}