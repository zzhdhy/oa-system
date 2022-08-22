package com.zzh.oa_system.model.dao.discuss;

import com.zzh.oa_system.model.entity.discuss.Comment;
import com.zzh.oa_system.model.entity.discuss.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:03
 * description:
 */
@Repository
public interface CommentDao extends JpaRepository<Comment, Long> {
    //根据回复表来找有关的所有评论
    List<Comment> findByReply(Reply reply);

    @Query("from Comment t where t.reply.replyId in (?1)")
    List<Comment> findComments(Long[] taskids);


}