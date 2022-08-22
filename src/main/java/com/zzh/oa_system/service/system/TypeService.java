package com.zzh.oa_system.service.system;

import com.zzh.oa_system.model.dao.system.TypeDao;
import com.zzh.oa_system.model.entity.system.SystemTypeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:28
 * description:
 */
@Service
@Transactional
public class TypeService {

    @Autowired
    private TypeDao typeDao;

    /**
     * 新增和更新
     *
     * @param list
     * @return
     */
    public SystemTypeList save(SystemTypeList list) {
        return typeDao.save(list);
    }

    /**
     * 删除方法
     */
    public void deleteType(Long typeId) {
        typeDao.delete(typeId);
    }


}

