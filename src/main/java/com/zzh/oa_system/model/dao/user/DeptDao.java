package com.zzh.oa_system.model.dao.user;

import com.zzh.oa_system.model.entity.user.Dept;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:47
 * description:
 */
@Repository
public interface DeptDao extends PagingAndSortingRepository<Dept, Long> {

    List<Dept> findByDeptId(Long id);


    @Query("select de.deptName from Dept de where de.deptId=:id")
    String findname(@Param("id") Long id);
}

