package com.zzh.oa_system.service.note;

import com.zzh.oa_system.model.dao.notedao.AttachmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:15
 * description:
 */
@Service
@Transactional
public class AttachService {

    @Autowired
    AttachmentDao attachmentDao;

    public Integer updateatt(String attname, String attpath, String shu, Long size, String type, Date uptime, Long attid) {
        return attachmentDao.updateatt(attname, attpath, shu, size, type, uptime, attid);
    }


}
