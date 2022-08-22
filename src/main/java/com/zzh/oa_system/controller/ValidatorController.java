package com.zzh.oa_system.controller;

import com.zzh.oa_system.common.formValid.BindingResultVOUtil;
import com.zzh.oa_system.common.formValid.MapToList;
import com.zzh.oa_system.common.formValid.ResultEnum;
import com.zzh.oa_system.common.formValid.ResultVO;
import com.zzh.oa_system.model.dao.roledao.RoleDao;
import com.zzh.oa_system.model.dao.system.StatusDao;
import com.zzh.oa_system.model.dao.system.TypeDao;
import com.zzh.oa_system.model.dao.user.DeptDao;
import com.zzh.oa_system.model.dao.user.UserDao;
import com.zzh.oa_system.model.entity.role.Role;
import com.zzh.oa_system.model.entity.system.SystemStatusList;
import com.zzh.oa_system.model.entity.system.SystemTypeList;
import com.zzh.oa_system.model.entity.task.Tasklist;
import com.zzh.oa_system.model.entity.user.Dept;
import com.zzh.oa_system.model.entity.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 20:34
 * description:
 */
@Controller
@RequestMapping("/")
public class ValidatorController {

    Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private StatusDao sdao;
    @Autowired
    private TypeDao tydao;
    @Autowired
    private UserDao udao;
    @Autowired
    private DeptDao ddao;
    @Autowired
    private RoleDao rdao;

    @RequestMapping("ck_addtask")
    public String addtask(HttpServletRequest request, @Valid Tasklist tlist, BindingResult br, HttpSession session,
                          @RequestParam(value = "page", defaultValue = "0") int page,
                          @RequestParam(value = "size", defaultValue = "10") int size) {
        request.setAttribute("tasklist", tlist);
        Pageable pa = new PageRequest(page, size);

        // 这里返回ResultVO对象，如果校验通过，ResultEnum.SUCCESS.getCode()返回的值为200；否则就是没有通过；
        ResultVO res = BindingResultVOUtil.hasErrors(br);
        System.out.println("tlist:" + tlist);
        if (!ResultEnum.SUCCESS.getCode().equals(res.getCode())) {
            List<Object> list = new MapToList<>().mapToList(res.getData());
            request.setAttribute("errormess", list.get(0).toString());
            System.out.println("list错误的实体类信息：" + tlist);
            System.out.println("list错误详情:" + list);
            System.out.println("list错误第一条:" + list.get(0));
            System.out.println("错误的信息——：" + list.get(0).toString());
            // 下面的info信息是打印出详细的信息
            log.info("getData:{}", res.getData());
            log.info("getCode:{}", res.getCode());
            log.info("getMsg:{}", res.getMsg());


            String userId = ((String) session.getAttribute("userId")).trim();
            Long userid = Long.parseLong(userId);


            // 查询类型表
            Iterable<SystemTypeList> typelist = tydao.findAll();
            // 查询状态表
            Iterable<SystemStatusList> statuslist = sdao.findAll();
            // 查询部门下面的员工
            Page<User> pagelist = udao.findByFatherId(userid, pa);
            List<User> emplist = pagelist.getContent();
            // 查询部门表
            Iterable<Dept> deptlist = ddao.findAll();
            // 查角色表
            Iterable<Role> rolelist = rdao.findAll();
            request.setAttribute("typelist", typelist);
            request.setAttribute("statuslist", statuslist);
            request.setAttribute("emplist", emplist);
            request.setAttribute("page", pagelist);
            request.setAttribute("deptlist", deptlist);
            request.setAttribute("rolelist", rolelist);
            return "task/addtask";
        } else {

            return "forward:/addtasks";
        }


    }

}
