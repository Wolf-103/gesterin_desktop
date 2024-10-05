package com.orozco.gesterin.controller;

import com.orozco.gesterin.model.Cliente;
import com.orozco.gesterin.DAO.Implementaciones.ClienteDAO;
import java.util.List;

/**
 *
 * @author CRISTIAN MANUEL OROZCO
 * @legajo VINF014304
 * @fecha 4 oct. 2024
 * @description Sistema GESTERIN
 */
public class UsuarioController {

    private final ClienteDAO clienteDAO;

    public UsuarioController() {
        this.clienteDAO = new ClienteDAO();
    }

    public boolean save(Cliente cliente) {
        return this.clienteDAO.save(cliente);
    }
    
    public List<Cliente> findAll(){
        return this.clienteDAO.findAll();
    }
    
    public List<Cliente> findByParams(String params){
        return this.clienteDAO.findAllByParams(params);
    }
    
    public boolean update(Cliente cliente){
        return this.clienteDAO.update(cliente);
    }
    
    public boolean delete(Long idCliente){
        return this.clienteDAO.delete(idCliente);
    }

}
