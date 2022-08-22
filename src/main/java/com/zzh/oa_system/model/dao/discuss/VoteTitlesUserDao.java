package com.zzh.oa_system.model.dao.discuss;

import com.zzh.oa_system.model.entity.discuss.VoteTitleUser;
import com.zzh.oa_system.model.entity.discuss.VoteTitles;
import com.zzh.oa_system.model.entity.user.User;
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
public interface VoteTitlesUserDao extends JpaRepository<VoteTitleUser, Long> {

    //在用户投票的标题表中查找所有的同一标题的集合
    List<VoteTitleUser> findByVoteTitles(VoteTitles voteTitles);

    //在用户投票的标题表中查找所有的同一投票的集合
    List<VoteTitleUser> findByVoteId(Long voteId);

    VoteTitleUser findByVoteTitlesAndUser(VoteTitles voteTitles, User user);


}