package com.lsm1998.football.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsm1998.football.model.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User>
{
}