package com.zzh.oa_system.model.dao.discuss;

import com.zzh.oa_system.model.entity.discuss.Discuss;
import com.zzh.oa_system.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:04
 * description:
 */
@Repository
public interface DiscussDao extends JpaRepository<Discuss, Long> {

    //根据用户来查找讨论区的自己所发布的；
    Page<Discuss> findByUser(User user, Pageable pa);

    //根据用户的标题来找
    Page<Discuss> findByUserAndTitleLike(User user, String title, Pageable pa);

    //根据标题来找
    Page<Discuss> findByTitleLike(String title, Pageable pa);
}
