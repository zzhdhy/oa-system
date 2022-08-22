package com.zzh.oa_system.service.discuss;

import com.zzh.oa_system.model.dao.discuss.CommentDao;
import com.zzh.oa_system.model.entity.discuss.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:02
 * description:
 */
@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    //保存
    public Comment save(Comment comment) {
        return commentDao.save(comment);
    }

    public void deleteComment(Long comment) {
        commentDao.delete(comment);
    }

}
