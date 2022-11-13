package com.cqrcb.workload.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqrcb.workload.entity.User;
import com.cqrcb.workload.mapper.UserMapper;
import com.cqrcb.workload.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yuanziyi
 * @since 2022-11-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Autowired
    UserMapper userMapper;

    //查询全部
    public List<User> queryAll() {
        userMapper.selectById(1);
        List<User> users = userMapper.queryAll();
        return users;
    }
}
