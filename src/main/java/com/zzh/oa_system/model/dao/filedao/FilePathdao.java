package com.zzh.oa_system.model.dao.filedao;

import com.zzh.oa_system.model.entity.file.FilePath;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:07
 * description:
 */
@Repository
public interface FilePathdao extends PagingAndSortingRepository<FilePath, Long> {

    List<FilePath> findByParentId(Long parentId);

    List<FilePath> findByParentIdAndPathIstrash(Long parentId, Long istrash);

    FilePath findByPathNameAndParentId(String pathname, Long parentId);

    FilePath findByPathName(String pathname);

    List<FilePath> findByPathUserIdAndPathIstrash(Long userid, Long istrash);

    FilePath findByParentIdAndPathUserId(Long parentId, Long userid);

    List<FilePath> findByPathUserIdAndPathIstrashAndPathNameLikeAndParentIdNot(Long userid, Long istrash, String likefilename, Long userrootpathid);

}

