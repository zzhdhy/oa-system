package com.zzh.oa_system.service.user;

import com.zzh.oa_system.model.dao.user.UserLogDao;
import com.zzh.oa_system.model.entity.user.UserLog;
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
 * time: 2021/4/29 18:51
 * description:
 */
@Transactional
@Service
public class UserLogService {

    @Autowired
    UserLogDao userLogDao;

    public Page<UserLog> ulogpaging(int page, String basekey, Long userid, Object time) {
        Pageable pa = new PageRequest(page, 15);
        if (!StringUtils.isEmpty(basekey)) {
            //模糊
            return userLogDao.findbasekey(userid, basekey, pa);
        }//0为降序 1为升序
        if (!StringUtils.isEmpty(time)) {
            if (time.toString().equals("0")) {
                return userLogDao.findByUserOrderBylogTimeDesc(userid, pa);
            }
            if (time.toString().equals("1")) {
                return userLogDao.findByUserOrderBylogTimeAsc(userid, pa);
            }
        } else {
            return userLogDao.findByUserOrderBylogTimeDesc(userid, pa);
        }
        return null;

    }
}
