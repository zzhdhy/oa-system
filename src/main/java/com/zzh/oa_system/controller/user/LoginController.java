package com.zzh.oa_system.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * projectName: oa_system
 *
 * @author: 赵振华
 * time: 2021/4/29 20:30
 * description:
 */
@Controller
@RequestMapping("/")
public class LoginController {

    @RequestMapping("login")
    public String login() {
        return "user/login";
    }

}
