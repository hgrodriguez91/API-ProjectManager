package com.mybatis2.testdemo.controllers;

import com.mybatis2.testdemo.entity.Cliente;
import com.mybatis2.testdemo.mappers.clienteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
   private clienteMapper  clienteMapper;

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteMapper.getAllClients();
    }

    @GetMapping("/{id}")
   public Cliente getClientById(@PathVariable Integer id){
        return clienteMapper.getClientById(id);
    }

    @PostMapping()
    public String addClient(@RequestBody Cliente cliente ){
        int result = clienteMapper.createClient(cliente);
        if(result>0){
            return "CLIENT CREATED ";
        }
        return "CLIENT CREATE FAIL";
    }
    @PutMapping
    public String updateClient(@RequestBody Cliente cliente){
        int result = clienteMapper.updateClient(cliente);
        if(result>0){
            return "CLIENT UPDATED ";
        }
        return "CLIENT UPDATE FAIL";
    }

    @DeleteMapping("/{id}")
    public String deleteClient(@RequestBody Integer id){
        int result = clienteMapper.deleteClient(id);
        if(result>0){
            return "CLIENT DELETED ";
        }
        return "CLIENT DELETE FAIL";
    }

}
