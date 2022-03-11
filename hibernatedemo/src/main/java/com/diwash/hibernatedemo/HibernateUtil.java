package com.diwash.hibernatedemo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {
	public static Session getSession() throws IOException {
		// Create configuration instance
		Configuration configuration = new Configuration();

		// Create properties file
		Properties properties = new Properties();
		properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("hibernate.properties"));
		// Pass hibernate properties file
		configuration.setProperties(properties);
		configuration.addAnnotatedClass(Employee.class);
		// Since version 4.x, service registry is being used
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();

		// Create session factory instance
		SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
		// Get current session
		Session session = factory.getCurrentSession();
		// Begin transaction
		session.getTransaction().begin();

		return session;
	}
}
