/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.bean;

import com.barrera.jsfblogs.dao.BlogEjb;
import com.barrera.jsfblogs.entities.Blogs;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author LTP-Dell
 */
@ManagedBean(name="blogsBean")
@ViewScoped
public class BlogsBean {
    
    @EJB
    private BlogEjb blogEjb;
    private List<Blogs> listBlogs;
    private Blogs blog;

    public List<Blogs> getListBlogs() {
        return listBlogs;
    }

    public void setListBlogs(List<Blogs> listBlogs) {
        this.listBlogs = listBlogs;
    }

    public Blogs getBlog() {
        return blog;
    }

    public void setBlog(Blogs blog) {
        this.blog = blog;
    }
    
    @PostConstruct
    public void init(){
        cargarLista();
    }
    
     public void openNew() {
        this.blog = new Blogs();
    }
     
     public void cargarLista(){
         listBlogs = blogEjb.getAllBlogs();
     }
     
     public void saveBlog() {
         
        if (this.blog.getId() == null) {
            blogEjb.saveBlogs(blog);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Blog Added"));
        }
        else {
            blogEjb.updateBlogs(blog);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Blog Updated"));
        }
        
        cargarLista();

        PrimeFaces.current().executeScript("PF('manageBlogDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-blogs");
    }
     
     public void deleteBlog(){
         blogEjb.deleteBlogs(blog);
         blog = null;
         cargarLista();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Blog Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-blogs");
     }
}
