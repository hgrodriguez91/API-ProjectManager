package com.mybatis2.testdemo.mappers;

import com.mybatis2.testdemo.entity.Proyecto;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;


public interface proyectosMapper {

    @Select("Select * from proyectos")
    ArrayList<Proyecto> getAllProyectos();

    @Select("Select * from proyectos Where id=#{id}")
    Proyecto getProyectoById();

    @Select("Select id, nombre, descripcion from proyectos where idcliente = #{id}")
    ArrayList<Proyecto> getProyectosByClientId(Integer id);
}
