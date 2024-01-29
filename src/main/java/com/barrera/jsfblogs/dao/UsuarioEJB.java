/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.dao;

import com.barrera.jsfblogs.entities.Usuarios;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author LTP-Dell
 */
@Stateless
public class UsuarioEJB {
 
    @PersistenceContext(name="jsfblogs")
    private EntityManager entityManager;
    
    public Usuarios getByUsername(String nombre){
        return entityManager.find(Usuarios.class, nombre);
    }
    
    public void saveUsuario(Usuarios usuario){
        entityManager.persist(usuario);
        entityManager.flush();
    }
    
    
}
