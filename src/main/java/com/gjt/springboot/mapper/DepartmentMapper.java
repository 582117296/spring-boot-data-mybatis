package com.gjt.springboot.mapper;

import com.gjt.springboot.pojo.Department;
import org.apache.ibatis.annotations.*;
//@Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
     Department getAll(Integer id);

    @Delete("delect from department where id=#{id}")
     Department dele(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(departmentName) value(#{departmentName})")
    int insert(Department department);
}
