package com.zzh.oa_system.model.dao.user;

import com.zzh.oa_system.model.entity.user.Position;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:48
 * description:
 */
@Repository
public interface PositionDao extends PagingAndSortingRepository<Position, Long> {

    @Query("select po.name from Position po where po.id=:id")
    String findById(@Param("id") Long id);

    List<Position> findByDeptidAndNameNotLike(Long deptid, String name);

    List<Position> findByDeptidAndNameLike(Long deptid, String name);

    List<Position> findByDeptid(Long deletedeptid);
}
