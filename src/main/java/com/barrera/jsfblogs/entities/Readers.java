/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.barrera.jsfblogs.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author LTP-Dell
 */
@Entity
@Table(name = "READERS")
@NamedQueries({
    @NamedQuery(name="getAllReaders", query="SELECT r FROM Readers r")
})
public class Readers implements Serializable{
    
     @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="reader_seq_gen")
    @SequenceGenerator(name="reader_seq_gen", sequenceName="READER_SEQ")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Blogs> getBlogReads() {
        return blogReads;
    }

    public void setBlogReads(List<Blogs> blogReads) {
        this.blogReads = blogReads;
    }
    
    @Column(name = "NAME", length = 50, nullable = false)
    private String name;
    
    @ManyToMany (mappedBy = "blogReaders", fetch = FetchType.EAGER)
    private List<Blogs> blogReads;
    
}
