package com.gjt.springboot.mapper;

import com.gjt.springboot.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_Id,name,token,gmt_create,gmt_modified) values (#{account_Id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    int addUser(User user);

    @Select("select * from user where token =#{record}")
    User queryUserByToken(@Param("record") String token);
    @Select("select * from user where account_Id =#{record}")
    User queryUserByAccountId(@Param("record")String valueOf);
}
