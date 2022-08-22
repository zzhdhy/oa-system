package com.zzh.oa_system.service.file;

import com.zzh.oa_system.model.dao.filedao.FileListdao;
import com.zzh.oa_system.model.dao.filedao.FilePathdao;
import com.zzh.oa_system.model.entity.file.FileList;
import com.zzh.oa_system.model.entity.file.FilePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:09
 * description:
 */
@Service
public class FileTransactionalHandlerService {

    @Autowired
    private FileListdao fldao;
    @Autowired
    private FilePathdao fpdao;
    @Autowired
    private FileService fileService;

    /**
     * 根据文件id 将文件放入回收站
     *
     * @param fileids
     */

    @Transactional
    public void trashfile(List<Long> fileids, Long setistrashhowmany, Long userid) {
        for (Long fileid : fileids) {
            FileList fileList = fldao.findOne(fileid);
            fileList.setFileIstrash(setistrashhowmany);
            if (userid != null) {
                fileList.setFpath(null);
            }

            fldao.save(fileList);
        }

    }

    /**
     * 文件还原
     *
     * @param checkfileids
     */
    @Transactional
    public void filereturnback(List<Long> checkfileids, Long userid) {
        FilePath fpath = fpdao.findByParentIdAndPathUserId(1L, userid);
        for (Long checkfileid : checkfileids) {
            FileList fileList = fldao.findOne(checkfileid);

            if (userid != null) {
                String name = fileService.onlyname(fileList.getFileName(), fpath, fileList.getFileShuffix(), 1, true);
                fileList.setFpath(fpath);
                fileList.setFileName(name);
            }
            fileList.setFileIstrash(0L);
            fldao.save(fileList);
        }

    }
}
