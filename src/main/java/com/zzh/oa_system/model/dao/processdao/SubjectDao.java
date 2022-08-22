package com.zzh.oa_system.model.dao.processdao;

import com.zzh.oa_system.model.entity.process.Subject;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:39
 * description:
 */
@Repository
public interface SubjectDao extends PagingAndSortingRepository<Subject, Long> {

    List<Subject> findByParentId(Long id);

    List<Subject> findByParentIdNot(Long id);


}
