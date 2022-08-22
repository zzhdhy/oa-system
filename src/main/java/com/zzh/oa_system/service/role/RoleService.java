package com.zzh.oa_system.service.role;

import com.zzh.oa_system.model.dao.roledao.RolepowerlistDao;
import com.zzh.oa_system.model.entity.role.Role;
import com.zzh.oa_system.model.entity.role.Rolepowerlist;
import com.zzh.oa_system.model.entity.system.SystemMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:26
 * description:
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    private RolepowerlistDao rldao;

    public void index(List<SystemMenu> menulist, Role rolep) {

        for (SystemMenu systemMenu : menulist) {

            rldao.save(new Rolepowerlist(rolep, systemMenu));
        }
    }

    //保存一个对象；
    public Rolepowerlist sava(Rolepowerlist rolepower) {
        return rldao.save(rolepower);
    }

}
