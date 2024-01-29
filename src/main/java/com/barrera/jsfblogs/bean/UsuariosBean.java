/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.security.MessageDigest;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.xml.bind.DatatypeConverter;

import com.barrera.jsfblogs.dao.UsuarioEJB;
import com.barrera.jsfblogs.entities.Usuarios;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author LTP-Dell
 */
@ManagedBean(name="usuariosBean")
@ViewScoped
public class UsuariosBean {
    
    @EJB
    private UsuarioEJB usuarioEjb;
    private Usuarios usuario;

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }
    
    private String username;
    private String password;
    
    FacesMessage message = null;

    @PostConstruct
    public void init(){
        usuario = new Usuarios();
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String login() {
        
        try{
            usuario = usuarioEjb.getByUsername(username);
            
            if(validate(true)){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario",usuario);
                clean();            
                return "/main.xhtml";
            }
  
        } catch(Exception e){
            sendErrorMessage(e.getMessage());
        }
        
        return null;
    }
    
    public boolean validate(boolean isLogin) throws NoSuchAlgorithmException{
            
            if(usuarioEjb.getByUsername(username) != null && !isLogin){
                sendErrorMessage("Username already exists, try another");
                return false;
            }
            
            if(usuario == null && isLogin){
                sendErrorMessage("User not exists");
                return false;
            }
            
            if(isLogin && !checkPassword(password,usuario.getPassword())  ){
                sendErrorMessage("Incorrect password");
                return false;
            }
            
            return true;
    }
    
    
    public void registrarse(){
        try {
            
            if(validate(false)){
                usuario.setUsername(username);
                usuario.setPassword(hashPassword(password));

                usuarioEjb.saveUsuario(usuario);

                sendInfoMessage("User created");

                clean();
            }
            
            
        }catch(Exception e){
                sendErrorMessage(e.getMessage());
        }
        
    }
    
    private void sendInfoMessage(String info){
        message = new FacesMessage(FacesMessage.SEVERITY_INFO,info,username);
                    FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private void sendErrorMessage(String error){
        message = new FacesMessage(FacesMessage.SEVERITY_ERROR,error,username);
         FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    private void redirect(String url) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect(context.getRequestContextPath() + url);
    }
    
    
    private void clean(){
        this.username= null;
        this.password = null;
        this.usuario = new Usuarios();
    }
    
    private boolean fieldRequired(String field){
        if(field == null || field.isEmpty()){
            return true;
        }
        
        return false;
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        clean();
        return "/login.xhtml";
    }
    
    private String hashPassword(String pass) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pass.getBytes());
        
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
    
    private boolean checkPassword(String pass, String hashPass) throws NoSuchAlgorithmException{
        return hashPass.equals(hashPassword(pass));
    }
    
}
