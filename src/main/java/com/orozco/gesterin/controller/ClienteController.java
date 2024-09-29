package com.orozco.gesterin.controller;

import com.orozco.gesterin.model.Cliente;
import com.orozco.gesterin.DAO.ClienteDAO;
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
        this.clienteDAO = new ClienteDAO();
    }

    public boolean registrarCliente(Cliente cliente) {
        return this.clienteDAO.registrar(cliente);
    }
    
    public List<Cliente> getAll(){
        return this.clienteDAO.getAll();
    }
    
    public boolean update(Cliente cliente){
        return this.clienteDAO.update(cliente);
    }

}
