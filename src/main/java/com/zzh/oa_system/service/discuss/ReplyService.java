package com.zzh.oa_system.service.discuss;

import com.zzh.oa_system.model.dao.discuss.ReplyDao;
import com.zzh.oa_system.model.entity.discuss.Reply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:05
 * description:
 */
@Service
@Transactional
public class ReplyService {
    @Autowired
    private ReplyDao replyDao;

    // 保存对象至数据库
    public Reply save(Reply reply) {
        return replyDao.save(reply);
    }

    // 删除一个回复
    public void deleteReply(Reply reply) {
        replyDao.delete(reply);
    }

}
