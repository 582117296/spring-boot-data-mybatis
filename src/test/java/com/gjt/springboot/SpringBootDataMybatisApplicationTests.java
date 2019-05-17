package com.gjt.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootDataMybatisApplicationTests {

    @Autowired
    DataSource dataSource;

    @Test
    public void contextLoads() {

        System.out.println(dataSource.getClass());
        try {
            Connection connection = dataSource.getConnection();
            System.out.println("连接了>>>>>"+connection);
            System.out.println("链接地址"+connection.getMetaData().getURL());
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
