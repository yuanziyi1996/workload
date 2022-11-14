package com.yyyf.workload.controller;


import com.yyyf.workload.common.RestResponse;
import com.yyyf.workload.entity.User;
import com.yyyf.workload.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public RestResponse<List<User>> getAllUser() {
        List<User> users = userService.list();
        return RestResponse.success(users);
    }

    @GetMapping("/get")
    public RestResponse<User> getById(@RequestParam(value = "id") Integer id) {
        User user = userService.getById(id);
        return RestResponse.success(user);
    }

    @PostMapping("/update")
    public RestResponse<User> updateUser(@RequestBody User user) {
        userService.updateById(user);
        return RestResponse.success(user);
    }
}
