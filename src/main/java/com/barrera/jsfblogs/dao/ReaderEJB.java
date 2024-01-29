/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.dao;

import com.barrera.jsfblogs.entities.Readers;
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
public class ReaderEJB {
    
    @PersistenceContext(name="jsfblogs")
    private EntityManager entityManager;
    
     public List<Readers> getAllReaders(){
        TypedQuery<Readers> readerList = entityManager.createNamedQuery("getAllReaders", Readers.class);
        return readerList.getResultList();
    }
     
      public void saveReader(Readers reader){
        entityManager.persist(reader);
        entityManager.flush();
    }
    
    public void updateReader(Readers reader){
        entityManager.merge(reader);
        entityManager.flush();
    }
    
    public void deleteReader(Readers reader){
        entityManager.remove(entityManager.merge(reader));
        entityManager.flush();
    }

    
}
