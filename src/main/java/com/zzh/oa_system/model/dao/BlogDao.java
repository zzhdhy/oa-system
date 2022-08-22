package com.zzh.oa_system.model.dao;

import com.zzh.oa_system.model.entity.Blog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:52
 * description:
 */
@Repository
public interface BlogDao extends CrudRepository<Blog, Integer> {

    @Query("from Blog blog where blog.userid=?1 and blog.type=1 order by blog.tid desc")
    List<Blog> findUserBlog(@Param("userid") Integer userid);

    @Query("from Blog blog where blog.userid=?1 and blog.type=0 order by blog.tid desc")
    List<Blog> findUserRecyBlog(@Param("userid") Integer userid);

    @Query("update Blog blog set blog.type=0 where blog.tid= ?1")
    @Modifying
    int deteleByid(@Param("tid") Integer tid);

    @Query("update Blog blog set blog.type=1 where blog.tid= ?1")
    @Modifying
    int findByid(@Param("tid") Integer tid);

    @Query("from Blog blog where blog.title like :searchs or blog.intro like :searchs or blog.keybody like :searchs")
    List<Blog> findContentLike(@Param("searchs") String searchs);

}

