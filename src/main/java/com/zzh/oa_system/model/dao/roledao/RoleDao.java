package com.zzh.oa_system.model.dao.roledao;

import com.zzh.oa_system.model.entity.role.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:40
 * description:
 */
@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

    @Query("select ro from Role as ro where ro.roleName like %?1%")
    Page<Role> findbyrolename(String val, Pageable pa);

}
