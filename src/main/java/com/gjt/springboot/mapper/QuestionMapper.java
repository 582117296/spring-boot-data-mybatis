package com.gjt.springboot.mapper;

import com.gjt.springboot.pojo.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface QuestionMapper {

    @Insert("insert into 'question' (title,description,gmt_create,gmt_modifiled,create,tag) values ('#{title}','#{description}',#{gmtCreate},#{gmtModifiled},#{create},'#{tag}')")
    int insertQuestion(Question question);
}
