package com.zzh.oa_system.model.dao.notedao;

import com.zzh.oa_system.model.entity.note.Director;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:20
 * description:
 */
@Repository
public interface DirectorDao extends PagingAndSortingRepository<Director, Long> {

}

