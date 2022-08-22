package com.zzh.oa_system.model.dao.processdao;

import com.zzh.oa_system.model.entity.process.EvectionMoney;
import com.zzh.oa_system.model.entity.process.Stay;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:39
 * description:
 */
@Repository
public interface StayDao extends PagingAndSortingRepository<Stay, Long> {

    List<Stay> findByEvemoney(EvectionMoney money);
}