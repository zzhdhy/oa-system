package com.zzh.oa_system.model.dao.system;

import com.zzh.oa_system.model.entity.system.SystemMenu;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:44
 * description:
 */
@Repository
public interface SystemMenuDao extends PagingAndSortingRepository<SystemMenu, Long> {

}

