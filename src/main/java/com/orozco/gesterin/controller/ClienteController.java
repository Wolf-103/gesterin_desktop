package com.orozco.gesterin.controller;

import com.orozco.gesterin.model.Cliente;
import com.orozco.gesterin.DAO.Implementaciones.ClienteDAO;
import com.orozco.gesterin.DAO.Implementaciones.ConnectionMysqlImpl;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 17 sep. 2024
 * @description Sistema GESTERIN
 */
public class ClienteController {

    private final ClienteDAO clienteDAO;

    public ClienteController() {
        this.clienteDAO = new ClienteDAO( new ConnectionMysqlImpl());
    }

    public Cliente save(Cliente cliente) {
        return this.clienteDAO.save(cliente);
    }
    
    public List<Cliente> findAll(){
        return this.clienteDAO.findAll();
    }
    
    public List<Cliente> findByParams(String params){
        return this.clienteDAO.findAllByParams(params);
    }
    
    public Cliente update(Cliente cliente){
        return this.clienteDAO.update(cliente);
    }
    
    public boolean delete(Long idCliente){
        return this.clienteDAO.delete(idCliente);
    }
    
    public void bajaCliente(Cliente cliente){
        cliente.setEstado(false);
        this.update(cliente);
    }

}
