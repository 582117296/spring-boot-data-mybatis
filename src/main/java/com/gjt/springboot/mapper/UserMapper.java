package com.gjt.springboot.mapper;

import com.gjt.springboot.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    @Insert("insert into user (account_Id,name,token,gmt_create,gmt_modified) values (#{account_Id},#{name},#{token},#{gmt_create},#{gmt_modified})")
    int addUser(User user);

}
