/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;

/**
 *
 * @author LTP-Dell
 */
@Entity
@Table(name = "USUARIOS")
@NamedQueries({
    @NamedQuery(name="getByUsername", query="SELECT u FROM Usuarios u WHERE u.username= :nombre")
})
public class Usuarios implements Serializable{
    
    @Id
    @Column(name="USERNAME", length=25, nullable=false)
    private String username;
    
    @Column(name="PASSWORD", length=100, nullable=false)
    private String password;
    
    public Usuarios(){
        
    }
    
    public Usuarios(String username, String password){
        this.username = username;
        this.password = password;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
}
