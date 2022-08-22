package com.zzh.oa_system.service.user;

import com.zzh.oa_system.model.dao.user.UserLogRecordDao;
import com.zzh.oa_system.model.entity.user.LoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:30
 * description:
 */
@Service
@Transactional
public class UserLongRecordService {
    @Autowired
    private UserLogRecordDao ulDao;

    public LoginRecord save(LoginRecord lr) {
        return ulDao.save(lr);
    }

}

