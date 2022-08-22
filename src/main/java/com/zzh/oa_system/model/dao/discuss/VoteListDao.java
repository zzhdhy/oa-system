package com.zzh.oa_system.model.dao.discuss;

import com.zzh.oa_system.model.entity.discuss.VoteList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:05
 * description:
 */
@Repository
public interface VoteListDao extends JpaRepository<VoteList, Long> {

}

