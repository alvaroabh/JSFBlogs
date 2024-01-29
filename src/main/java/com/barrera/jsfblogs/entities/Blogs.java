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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@Table(name = "BLOGS")
@NamedQueries({
    @NamedQuery(name="getAllBlogs", query="SELECT b FROM Blogs b")
})
public class Blogs implements Serializable{
    
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO, generator="blog_seq_gen")
    @SequenceGenerator(name="blog_seq_gen", sequenceName="BLOG_SEQ")
    private Integer id;
    
    @Column(name = "TITLE", length = 50, nullable = false)
    private String title;
    
    @Column(name = "DESCRIPTION", length = 4000, nullable = false)
    private String description;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
       name = "BLOGS_READERS",
       joinColumns = @JoinColumn(name = "B_ID" , referencedColumnName = "ID"),
       inverseJoinColumns = @JoinColumn(name = "R_ID", referencedColumnName = "ID")
    )
    private List<Readers> blogReaders;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Readers> getBlogReaders() {
        return blogReaders;
    }

    public void setBlogReaders(List<Readers> blogReaders) {
        this.blogReaders = blogReaders;
    }
}
