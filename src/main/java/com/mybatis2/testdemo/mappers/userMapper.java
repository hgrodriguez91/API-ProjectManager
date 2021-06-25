package com.mybatis2.testdemo.mappers;
import com.mybatis2.testdemo.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import java.util.List;


@Mapper
@Component("userRepo")
public interface userMapper {


   @Results( id = "user_result", value ={
       @Result( column = "id", property = "id", id = true),
        @Result(column = "username", property = "username"),
        @Result( column = "password", property = "password"),
        @Result( column = "id", property = "roles", many = @Many( select = "com.mybatis2.testdemo.mappers.rolesMapper.getRolesByUserId", fetchType = FetchType.LAZY))
   })
   @Select("select * from users ")
    List<User> findAll();

   @ResultMap("user_result")
   @Select("select * from users where id= #{id}")
    User findUserByUserId(Integer id);

   @ResultMap("user_result")
   @Select("select * from users where username = #{username}")
    User findByUsername(String username);


   @Insert("insert into users values({username},#{password})")
    int insertUser(String username, String password);


}
