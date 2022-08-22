package com.zzh.oa_system.service.user;

import com.zzh.oa_system.model.dao.user.UserLogRecordDao;
import com.zzh.oa_system.model.entity.user.LoginRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:50
 * description:
 */
@Transactional
@Service
public class UserLogRecordService {

    @Autowired
    UserLogRecordDao userLogRecordDao;

    public Page<LoginRecord> ulogpaging(int page, String basekey, Long userid, Object time) {
        Pageable pa = new PageRequest(page, 15);
        if (!StringUtils.isEmpty(basekey)) {
            //模糊
            return userLogRecordDao.findbasekey(userid, basekey, pa);
        }//0为降序 1为升序
        if (!StringUtils.isEmpty(time)) {
            if (time.toString().equals("0")) {
                return userLogRecordDao.findByUserOrderBylogTimeDesc(userid, pa);
            }
            if (time.toString().equals("1")) {
                return userLogRecordDao.findByUserOrderBylogTimeAsc(userid, pa);
            }
        } else {
            return userLogRecordDao.findByUserOrderBylogTimeDesc(userid, pa);
        }
        return null;

    }
}

