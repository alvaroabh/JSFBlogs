/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.bean;

import com.barrera.jsfblogs.dao.BlogEjb;
import com.barrera.jsfblogs.dao.ReaderEJB;
import com.barrera.jsfblogs.entities.Blogs;
import com.barrera.jsfblogs.entities.Readers;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author LTP-Dell
 */
@ManagedBean(name="blogReadersBean")
@ViewScoped
public class BlogReadersBean {
    
    private List<Blogs> blogsLists;
    private Blogs selectedBlog;
    private List<Readers> readersLists;
    private List<Readers> allReadersLists;
    private Readers selectedReader;
    private Readers addReader;

    public List<Readers> getAllReadersLists() {
        return allReadersLists;
    }

    public void setAllReadersLists(List<Readers> allReadersLists) {
        this.allReadersLists = allReadersLists;
    }
    

    public Readers getAddReader() {
        return addReader;
    }

    public void setAddReader(Readers addReader) {
        this.addReader = addReader;
    }
    
    @EJB
    private BlogEjb blogEjb;
    
    @EJB
    private ReaderEJB readerEjb;

    public Blogs getSelectedBlog() {
        return selectedBlog;
    }

    public void setSelectedBlog(Blogs selectedBlog) {
        this.selectedBlog = selectedBlog;
    }

    public Readers getSelectedReader() {
        return selectedReader;
    }

    public void setSelectedReader(Readers selectedReader) {
        this.selectedReader = selectedReader;
    }
    
    @PostConstruct
    public void init(){
        cargarBlogs();
        cargarReaders();
    }

    public List<Blogs> getBlogsLists() {
        return blogsLists;
    }

    public void setBlogsLists(List<Blogs> blogsLists) {
        this.blogsLists = blogsLists;
    }

    public List<Readers> getReadersLists() {
        return readersLists;
    }

    public void setReadersLists(List<Readers> readersLists) {
        this.readersLists = readersLists;
    }
    
    public void cargarBlogs(){
        blogsLists = blogEjb.getAllBlogs();
    }
    
    public void cargarReaders(){
        allReadersLists = readerEjb.getAllReaders();
    }
    
    public void onRowSelect(SelectEvent<Blogs> event) {
       this.readersLists = this.selectedBlog.getBlogReaders();
       PrimeFaces.current().ajax().update("dtReaders");
    }
    

     public void openNew() {
        this.addReader = new Readers();
    }
     
     public void saveReader(){
         if(this.addReader == null){
             //NINGUNO SELECCIONADO
             return;
         }
         
         if(this.readersLists.contains(addReader)){
             //VALIDAR YA REGISTRADO
             return;
         }
         
         selectedBlog.getBlogReaders().add(addReader);
         blogEjb.saveBlogs(selectedBlog);
         
         cargarBlogs();
         cargarReaders();
                
     }
     
}
