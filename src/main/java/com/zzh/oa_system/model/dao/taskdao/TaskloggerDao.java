package com.zzh.oa_system.model.dao.taskdao;

import com.zzh.oa_system.model.entity.task.Tasklogger;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:46
 * description:
 */
@Repository
public interface TaskloggerDao extends PagingAndSortingRepository<Tasklogger, Long> {

    @Query("select tl from Tasklogger tl where tl.taskId.taskId=:id")
    List<Tasklogger> findByTaskId(@Param("id") Long id);
}

