package com.zzh.oa_system.model.dao.discuss;

import com.zzh.oa_system.model.entity.discuss.VoteList;
import com.zzh.oa_system.model.entity.discuss.VoteTitles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:06
 * description:
 */
@Repository
public interface VoteTitleListDao extends JpaRepository<VoteTitles, Long> {

    //根据投票id来找所有投票标题的集合
    List<VoteTitles> findByVoteList(VoteList voteList);
}