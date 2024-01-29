package com.barrera.jsfblogs.entities;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Readers.class)
public abstract class Readers_ {

	public static volatile ListAttribute<Readers, Blogs> blogReads;
	public static volatile SingularAttribute<Readers, String> name;
	public static volatile SingularAttribute<Readers, Integer> id;

}

