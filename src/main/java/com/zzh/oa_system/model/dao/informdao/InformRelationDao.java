package com.zzh.oa_system.model.dao.informdao;

import com.zzh.oa_system.model.entity.notice.NoticeUserRelation;
import com.zzh.oa_system.model.entity.notice.NoticesList;
import com.zzh.oa_system.model.entity.user.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:09
 * description:
 */
@Repository
public interface InformRelationDao extends PagingAndSortingRepository<NoticeUserRelation, Long> {

    //根据用户找到通知联系人中间的集合
    List<NoticeUserRelation> findByUserId(User userId);

    //找到该用户未读的消息
    List<NoticeUserRelation> findByReadAndUserId(Boolean read, User userid);

    //根据通知找到所有的通知联系表中的集合
    List<NoticeUserRelation> findByNoticeId(NoticesList notice);

    //根据用户id和通知id找到唯一的对象
    NoticeUserRelation findByUserIdAndNoticeId(User userId, NoticesList notice);

}

