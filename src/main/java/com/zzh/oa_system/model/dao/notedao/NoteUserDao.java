package com.zzh.oa_system.model.dao.notedao;

import com.zzh.oa_system.model.entity.note.Noteuser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 18:25
 * description:
 */
@Repository
public interface NoteUserDao extends PagingAndSortingRepository<Noteuser, Long> {

    @Query("select n.id from Noteuser n where n.noteId=?1 and n.userId=?2")
    Long findid(long noteid, long userid);
}

