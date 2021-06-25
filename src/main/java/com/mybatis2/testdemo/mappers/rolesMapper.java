package com.mybatis2.testdemo.mappers;

import com.mybatis2.testdemo.entity.Roles;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Mapper
@Component
public interface rolesMapper {

    @Select("SELECT r.id,r.role FROM users u INNER JOIN users_role ur ON u.id = ur.id_usuario" +
            " INNER JOIN roles r ON ur.id_role = r.id WHERE u.id = #{id}")
    ArrayList<Roles> getRolesByUserId(Integer id);

    @Select("select * from roles")
    ArrayList<Roles> getAllRoles();

    @Insert("insert into roles (role) values(#{role})")
      int insertRol();
}
