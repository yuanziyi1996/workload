package com.cqrcb.workload.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cqrcb.workload.entity.User;
import com.cqrcb.workload.mapper.UserMapper;
import com.cqrcb.workload.service.IUserService;
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
