package com.zzh.oa_system.model.dao.notedao;

import com.zzh.oa_system.model.entity.note.Catalog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:16
 * description:
 */
@Repository
public interface CatalogDao extends PagingAndSortingRepository<Catalog, Long> {

    //删除
    @Query("delete from Catalog c where c.catalogId=?1 ")
    @Modifying
    Integer delete(long catalogId);

    @Query("select c.catalogId from Catalog c where c.catalogName=?1")
    Long findByCatalogName(String catalogname);

    @Query("from Catalog c where c.user.userId=?1")
    List<Catalog> findcatauser(long userid);

    @Query("select c.catalogName from Catalog c where c.user.userId=?1")
    List<String> findcataname(long userid);

    //通过目录id找到用户id
    @Query("select c.user.userId from Catalog c where c.catalogId=?1")
    long finduserid(long catalogId);


}

