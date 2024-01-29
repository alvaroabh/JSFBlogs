/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.dao;

import com.barrera.jsfblogs.entities.Blogs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author LTP-Dell
 */
@Stateless
public class BlogEjb {
    
    @PersistenceContext(name="jsfblogs")
    private EntityManager entityManager;
    
    public List<Blogs> getAllBlogs(){
        TypedQuery<Blogs> blogList = entityManager.createNamedQuery("getAllBlogs", Blogs.class);
        return blogList.getResultList();
    }

    
    public void saveBlogs(Blogs blog){
        entityManager.persist(blog);
        entityManager.flush();
    }
    
    public void updateBlogs(Blogs blog){
        entityManager.merge(blog);
        entityManager.flush();
    }
    
    public void deleteBlogs(Blogs blog){
        entityManager.remove(entityManager.merge(blog));
        entityManager.flush();
    }
}
