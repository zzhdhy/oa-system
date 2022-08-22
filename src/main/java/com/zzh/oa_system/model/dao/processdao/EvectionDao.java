package com.zzh.oa_system.model.dao.processdao;

import com.zzh.oa_system.model.entity.process.Evection;
import com.zzh.oa_system.model.entity.process.ProcessList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:31
 * description:
 */
@Repository
public interface EvectionDao extends PagingAndSortingRepository<Evection, Long> {

    Evection findByProId(ProcessList process);

}
