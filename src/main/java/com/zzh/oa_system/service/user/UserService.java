package com.zzh.oa_system.service.user;

import com.zzh.oa_system.model.dao.user.UserDao;
import com.zzh.oa_system.model.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
public class UserService {

    @Autowired
    UserDao userDao;

    //找到该管理员下面的所有用户并且分页
    public Page<User> findmyemployuser(int page, String baseKey, long parentid) {
        Pageable pa = new PageRequest(page, 10);
        if (!StringUtils.isEmpty(baseKey)) {
            // 模糊查询
            return userDao.findbyFatherId(baseKey, parentid, pa);
        } else {
            return userDao.findByFatherId(parentid, pa);
        }

    }
}
