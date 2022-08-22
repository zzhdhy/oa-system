package com.zzh.oa_system.model.dao.user;

import com.zzh.oa_system.model.entity.user.LoginRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:49
 * description:
 */
@Repository
public interface UserLogRecordDao extends JpaRepository<LoginRecord, Long> {

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM aoa_user_login_record WHERE DATE_FORMAT(aoa_user_login_record.login_time,'%Y-%m-%d')=?1")
    Integer countlog(String date);

    //模糊查询
    @Query("from LoginRecord u where u.user.userId=?1 and ( DATE_format(u.loginTime,'%Y-%m-%d ') like %?2% or u.ipAddr like %?2%) order by u.loginTime DESC")
    Page<LoginRecord> findbasekey(long userid, String baseKey, Pageable pa);

    //按照时间降序(默认)
    @Query("from LoginRecord u where u.user.userId=?1 order by u.loginTime DESC")
    Page<LoginRecord> findByUserOrderBylogTimeDesc(long userid, Pageable pa);

    //按照时间升序
    @Query("from LoginRecord u where u.user.userId=?1 order by u.loginTime ASC")
    Page<LoginRecord> findByUserOrderBylogTimeAsc(long userid, Pageable pa);
}
