package com.zzh.oa_system.model.dao.processdao;

import com.zzh.oa_system.model.entity.process.ProcessList;
import com.zzh.oa_system.model.entity.process.Regular;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:36
 * description:
 */
@Repository
public interface RegularDao extends PagingAndSortingRepository<Regular, Long> {

    Regular findByProId(ProcessList pro);

}
