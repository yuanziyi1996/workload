package com.cqrcb.workload.mapper;

import com.cqrcb.workload.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author yuanziyi
 * @since 2022-11-13
 */

public interface UserMapper extends BaseMapper<User> {
    List<User> queryAll();
}
