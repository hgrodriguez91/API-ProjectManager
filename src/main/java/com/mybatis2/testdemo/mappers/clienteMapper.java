package com.mybatis2.testdemo.mappers;

import com.mybatis2.testdemo.entity.Cliente;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper
public interface clienteMapper {

   @Results(id = "cliente_result", value = {
           @Result( column = "id", property = "id", id = true),
           @Result(column = "nombre", property = "nombre"),
           @Result(column = "direccion",property = "direccion"),
           @Result(column = "id", property = "proyectos",
                   many = @Many(select = "com.mybatis2.testdemo.mappers.proyectosMapper.getProyectosByClientId",
                           fetchType = FetchType.EAGER))
   })


    @Select("Select * from clientes")
    ArrayList<Cliente> getAllClients();

   @ResultMap("cliente_result")
   @Select("Select * from clientes Where id= #{id}")
    Cliente getClientById(Integer id);

   @ResultMap("cliente_result")
   @Select("Select * from clientes where nombre =#{name}")
   Cliente getClientByName(String name );

   @Insert("Insert into clientes values (#{cliente.codigo},#{cliente.nombre},#{cliente.direccion})")
    int createClient(Cliente cliente);

   @Delete("Delete * from clientes where id={id}")
    int deleteClient(Integer id);

   @Update("Update clientes set codigo= #{cliente.codigo}, nombre =#{cliente.nombre}, direccion = #{cliente.direccion}")
    int updateClient(Cliente cliente);
}
