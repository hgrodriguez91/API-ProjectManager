package com.mybatis2.testdemo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = {"handler"})
public class Proyecto {

    private Integer id;
    private String nombre;
    private String descripcion;
    private Integer id_cliente;

    public Proyecto(Integer id, String nombre, String descripcion, Integer id_cliente) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.id_cliente = id_cliente;
    }

    public Proyecto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }
}
