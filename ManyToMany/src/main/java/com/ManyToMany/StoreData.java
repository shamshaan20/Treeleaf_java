package com.ManyToMany;

import java.util.ArrayList;
import org.hibernate.*;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class StoreData {
    public static void main(String[] args) {

        StandardServiceRegistry ssr=new StandardServiceRegistryBuilder().configure("src/main/java/hibernate.cfg.xml").build();
        Metadata meta=new MetadataSources(ssr).getMetadataBuilder().build();
        SessionFactory factory=meta.getSessionFactoryBuilder().build();
        Session session=factory.openSession();
        Transaction t=session.beginTransaction();

        Answer ans1=new Answer();
        ans1.setAnswername("Java is a programming language");
        ans1.setPostedBy("Diwash");

        Answer ans2=new Answer();
        ans2.setAnswername("Java is a platform");
        ans2.setPostedBy("Bijay Maharjan");

        Question q1=new Question();
        q1.setQname("What is Java?");
        ArrayList<Answer> l1=new ArrayList<Answer>();
        l1.add(ans1);
        l1.add(ans2);
        q1.setAnswers(l1);

        Answer ans3=new Answer();
        ans3.setAnswername("Servlet is an Interface");
        ans3.setPostedBy("Prajjwal");

        Answer ans4=new Answer();
        ans4.setAnswername("Servlet is an API");
        ans4.setPostedBy("Renjy");

        Question q2=new Question();
        q2.setQname("What is Servlet?");
        ArrayList<Answer> l2=new ArrayList<Answer>();
        l2.add(ans3);
        l2.add(ans4);
        q2.setAnswers(l2);
        session.persist(q1);
        session.persist(q2);

        t.commit();
        session.close();
        System.out.println("success");
    }
}