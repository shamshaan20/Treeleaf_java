package com.ManyToOne;


import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Store {
    public static void main(String[] args) {

        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("src/main/java/com/hibernate.cfg.xml").build();
        Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();

        SessionFactory factory=meta.getSessionFactoryBuilder().build();
        Session session=factory.openSession();

        Transaction t=session.beginTransaction();

        Employee e1=new Employee();
        e1.setName("Diwash");
        e1.setEmail("diwsh@gmail.com");

        Employee e2=new Employee();
        e2.setName("rameshwor");
        e2.setEmail("rame@gmail.com");

        Address address1=new Address();
        address1.setAddressLine1("Nayabajar-13, Kathmandu");
        address1.setCity("Kathmandu");
        address1.setState("Bagmati");
        address1.setCountry("Nepal");
        address1.setPincode(44600);

        e1.setAddress(address1);
        e2.setAddress(address1);

        session.persist(e1);
        session.persist(e2);
        t.commit();

        session.close();
        System.out.println("success");
    }
}