package com.zzh.oa_system.service.system;

import com.zzh.oa_system.model.dao.IndexDao;
import com.zzh.oa_system.model.dao.roledao.RolepowerlistDao;
import com.zzh.oa_system.model.dao.user.UserDao;
import com.zzh.oa_system.model.entity.role.Rolemenu;
import com.zzh.oa_system.model.entity.system.SystemMenu;
import com.zzh.oa_system.model.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 19:27
 * description:
 */
@Service
@Transactional
public class MenuSysService {
    @Autowired
    private IndexDao iDao;
    @Autowired
    private RolepowerlistDao rdao;
    @Autowired
    private UserDao uDao;

    //	新增与修改菜单管理的内容
    public SystemMenu save(SystemMenu menu) {
        return iDao.save(menu);
    }

    //	1、上移下移按钮先改变其他的排序值
    public int changeSortId(Integer sortId, Integer arithNum, Long parentId) {
        return iDao.changeSortId(sortId, arithNum, parentId);
    }

    //	2、上移下移按钮先改变自己的排序值
    public int changeSortId2(Integer sortId, Integer arithNum, Long menuId) {
        return iDao.changeSortId2(sortId, arithNum, menuId);
    }


    public void findMenuSys(HttpServletRequest req, User user) {

        List<Rolemenu> oneMenuAll = rdao.findbyparentxianall(0L, user.getRole().getRoleId(), true, true);
        List<Rolemenu> twoMenuAll = rdao.findbyparentsxian(0L, user.getRole().getRoleId(), true, true);
        req.setAttribute("oneMenuAll", oneMenuAll);
        req.setAttribute("twoMenuAll", twoMenuAll);

    }

    public void findAllMenuSys(HttpServletRequest req) {
//		查找所有父级
        Iterable<SystemMenu> oneMenuAll = iDao.findByParentIdOrderBySortId(0L);
//		查找所有子级
        Iterable<SystemMenu> twoMenuAll = iDao.findByParentIdNotOrderBySortId(0L);

        req.setAttribute("oneMenuAll", oneMenuAll);
        req.setAttribute("twoMenuAll", twoMenuAll);
    }

    /**
     * 在service层执行删除方法
     */
    public int deleteThis(Long menuId) {
        return iDao.deleteThis(menuId);
    }

}

