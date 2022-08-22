package com.zzh.oa_system.model.dao.maildao;

import com.zzh.oa_system.model.entity.mail.Mailnumber;
import com.zzh.oa_system.model.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:10
 * description:
 */
@Repository
public interface MailnumberDao extends PagingAndSortingRepository<Mailnumber, Long> {
    //根据状态和user来找account
    Page<Mailnumber> findByMailUserId(User user, Pageable page);

    //根据用户和type排序account
    Page<Mailnumber> findByMailUserIdOrderByMailType(User user, Pageable page);

    //根据用户和status排序account
    Page<Mailnumber> findByMailUserIdOrderByStatus(User user, Pageable page);

    //根据用户和创建时间排序account
    Page<Mailnumber> findByMailUserIdOrderByMailCreateTimeDesc(User user, Pageable page);

    //根据用户和发件别名模糊查找account
    @Query("select mn from Mailnumber mn where  mn.mailUserName like %:val% and mn.mailUserId=:tu ")
    Page<Mailnumber> findByMailUserNameLikeAndMailUserId(@Param("val") String val, @Param("tu") User tu, Pageable page);

    List<Mailnumber> findByStatusAndMailUserId(Long status, User u);

}

