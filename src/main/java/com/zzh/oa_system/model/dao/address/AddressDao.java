package com.zzh.oa_system.model.dao.address;

import com.zzh.oa_system.model.entity.note.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 17:54
 * description:
 */
@Repository
public interface AddressDao extends JpaRepository<Director, Long> {

    //根据姓名首拼模糊查询

}
