package com.zzh.oa_system.service.system;

import com.zzh.oa_system.model.dao.system.StatusDao;
import com.zzh.oa_system.model.entity.system.SystemStatusList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:27
 * description:
 */
@Service
@Transactional
public class StatusService {

    @Autowired
    private StatusDao statusDao;

    /**
     * 新增和更新方法
     *
     * @param status
     * @return
     */
    public SystemStatusList save(SystemStatusList status) {
        return statusDao.save(status);
    }

    /**
     * 删除方法
     */
    public void deleteStatus(Long statusId) {
        statusDao.delete(statusId);
    }

}
