package com.zzh.oa_system.model.dao.processdao;

import com.zzh.oa_system.model.entity.process.Bursement;
import com.zzh.oa_system.model.entity.process.DetailsBurse;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:31
 * description:
 */
@Repository
public interface DetailsBurseDao extends PagingAndSortingRepository<DetailsBurse, Long> {

    List<DetailsBurse> findByBurs(Bursement bu);
}
