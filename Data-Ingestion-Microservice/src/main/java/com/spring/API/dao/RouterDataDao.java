package com.spring.API.dao;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.spring.API.dao.RoutersData;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RouterDataDao {
    public void save(ConsumerRecord<String, String> record){
        RoutersData routersData = new RoutersData();
        // remove {}
        String keyValuePairs = record.value().substring(1, record.value().length()-1);
        String[] pairs = keyValuePairs.split(", ");
        for (String pair: pairs){
            String[] keyValue = pair.split("=");
            String key = keyValue[0].trim();
            String value = keyValue[1].trim();
            switch (key){
                case "Timestamp":
                    routersData.setTimeStamp(value);
                    break;
                case "DeviceID":
                    routersData.setDeviceId(value);
                    break;
                case "CPU_Utilization":
                    routersData.setCpuUtilization(Integer.parseInt(value));
                    break;
                case "Memory_Utilization":
                    routersData.setMemoryUtilization(Integer.parseInt(value));
                    break;
                case "Network_Traffic_Status":
                    routersData.setNetworkTrafficStatus(value);
                    break;
            }
        }
        saveObjectToDatabase(routersData);
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
