package com.zzh.oa_system.model.dao.discuss;

import com.zzh.oa_system.model.entity.discuss.Discuss;
import com.zzh.oa_system.model.entity.discuss.Reply;
import com.zzh.oa_system.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:05
 * description:
 */
@Repository
public interface ReplyDao extends JpaRepository<Reply, Long> {
    //根据讨论表的id来找所有的回复表,分页处理
    Page<Reply> findByDiscuss(Discuss discuss, Pageable pa);

    //根据讨论表和用户来查找，并分页处理
    Page<Reply> findByDiscussAndUser(Discuss discuss, User user, Pageable pa);

    List<Reply> findByDiscuss(Discuss discuss);


}
