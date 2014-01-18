package org.appengine.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAService {

	private static final EntityManagerFactory FACTORY = Persistence
			.createEntityManagerFactory("transactions-optional");

	private JPAService() {

	}

	public static EntityManager getEntityManager() {
		return FACTORY.createEntityManager();
	}
}
