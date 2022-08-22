package com.zzh.oa_system.service.note;

import com.zzh.oa_system.model.dao.notedao.CatalogDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:20
 * description:
 */
@Service
@Transactional
public class CatalogService {

    @Autowired
    CatalogDao catalogDao;

    //删除
    public int delete(long catalogId) {
        return catalogDao.delete(catalogId);
    }


}

