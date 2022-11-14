package com.yyyf.workload.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yyyf.workload.entity.User;
import com.yyyf.workload.mapper.UserMapper;
import com.yyyf.workload.service.IUserService;
import org.springframework.stereotype.Service;

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

}
