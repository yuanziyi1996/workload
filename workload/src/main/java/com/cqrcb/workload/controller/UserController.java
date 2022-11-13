package com.cqrcb.workload.controller;


import com.cqrcb.workload.common.RestResponse;
import com.cqrcb.workload.entity.User;
import com.cqrcb.workload.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yuanziyi
 * @since 2022-11-13
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    @GetMapping("/getall")
    public RestResponse<List<User>> testController() {
        List<User> users = userService.list();
        return RestResponse.success(users);
    }
}
