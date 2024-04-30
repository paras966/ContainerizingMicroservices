package com.spring.API.repository;

import com.spring.API.dao.RoutersData;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class RouterDataOperation {
    public List<RoutersData> getAllData(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(RoutersData.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query<RoutersData> query = session.createQuery("FROM RoutersData", RoutersData.class);
        List<RoutersData> routersDataList = query.getResultList();
        transaction.commit();
        session.close();
        sessionFactory.close();
        return routersDataList;
    }

    public List<Object> getAllDeviceData(String deviceId){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(RoutersData.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query<Object> query = session.createQuery("SELECT timeStamp, cpuUtilization, memoryUtilization FROM RoutersData WHERE deviceId = :deviceId");
        query.setParameter("deviceId", deviceId);
        List<Object> results = query.getResultList();
        transaction.commit();
        session.close();
        sessionFactory.close();
        return results;
    }

    public List<String> getAllNts(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(RoutersData.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT networkTrafficStatus FROM RoutersData");
        List<String> results = query.getResultList();
        transaction.commit();
        session.close();
        sessionFactory.close();
        return  results;
    }
    public List<Object> getAllResources(){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(RoutersData.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        Query<Object> query = session.createQuery("SELECT cpuUtilization, memoryUtilization FROM RoutersData");
        List<Object> results = query.getResultList();
        transaction.commit();
        session.close();
        sessionFactory.close();
        return results;
    }

    public void saveObjectToDatabase(RoutersData routersData){
        Configuration configuration = new Configuration().configure().addAnnotatedClass(RoutersData.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        transaction = session.beginTransaction();
        session.save(routersData);
        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}
