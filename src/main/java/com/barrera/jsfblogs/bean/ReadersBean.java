/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.bean;

import com.barrera.jsfblogs.dao.ReaderEJB;
import com.barrera.jsfblogs.entities.Readers;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author LTP-Dell
 */
@ManagedBean(name="readersBean")
@ViewScoped
public class ReadersBean {
    
    @EJB
    private ReaderEJB readerEjb;
    private List<Readers> listReaders;
    private Readers reader;

    public List<Readers> getListReaders() {
        return listReaders;
    }

    public void setListReaders(List<Readers> listReaders) {
        this.listReaders = listReaders;
    }

    public Readers getReader() {
        return reader;
    }

    public void setReader(Readers reader) {
        this.reader = reader;
    }
    
    @PostConstruct
    public void init(){
        cargarLista();
    }
    
    public void cargarLista(){
         listReaders = readerEjb.getAllReaders();
     }
    
     public void openNew() {
        this.reader = new Readers();
    }
     
     public void saveReader() {
         
        if (this.reader.getId() == null) {
            readerEjb.saveReader(reader);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reader Added"));
        }
        else {
            readerEjb.updateReader(reader);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reader Updated"));
        }
        
        cargarLista();

        PrimeFaces.current().executeScript("PF('manageReaderDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-readers");
    }
     
     public void deleteReader(){
         readerEjb.deleteReader(reader);
         reader = null;
         cargarLista();
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Reader Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-readers");
     }
}
