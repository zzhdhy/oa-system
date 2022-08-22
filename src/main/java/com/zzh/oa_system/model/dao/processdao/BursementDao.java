package com.zzh.oa_system.model.dao.processdao;

import com.zzh.oa_system.model.entity.process.Bursement;
import com.zzh.oa_system.model.entity.process.ProcessList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:30
 * description:
 */
@Repository
public interface BursementDao extends PagingAndSortingRepository<Bursement, Long> {

    Bursement findByProId(ProcessList process);


}
