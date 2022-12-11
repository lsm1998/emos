package com.lsm1998.api.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lsm1998.api.model.UserAuth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserAuthMapper extends BaseMapper<UserAuth>
{
    @Select("select tb_auth.key from tb_user_auth inner join tb_auth tb_user_auth.auth_id=tb_auth.id where tb_user_auth.user_id=#{user_id}")
    List<String> userAuthKeyList(@Param("user_id") Long userId);
}
