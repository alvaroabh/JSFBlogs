package com.barrera.jsfblogs.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Blogs.class)
public abstract class Blogs_ {

	public static volatile ListAttribute<Blogs, Readers> blogReaders;
	public static volatile SingularAttribute<Blogs, String> description;
	public static volatile SingularAttribute<Blogs, Integer> id;
	public static volatile SingularAttribute<Blogs, String> title;

}

