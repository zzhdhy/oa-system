package com.zzh.oa_system.model.dao.plandao;

import com.zzh.oa_system.model.entity.process.EvectionMoney;
import com.zzh.oa_system.model.entity.process.Traffic;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:28
 * description:
 */
@Repository
public interface TrafficDao extends PagingAndSortingRepository<Traffic, Long> {

    List<Traffic> findByEvection(EvectionMoney money);
}

