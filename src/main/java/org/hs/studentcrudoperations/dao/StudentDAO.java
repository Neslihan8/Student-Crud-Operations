/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hs.studentcrudoperations.dao;

import org.hs.studentcrudoperations.pojo.Student;
import java.util.List;  
import java.util.ArrayList;  
import org.hibernate.Query;  
import org.hibernate.Session;  
import javax.faces.application.FacesMessage;  
import org.hs.studentcrudoperations.util.HibernateUtil;
import org.primefaces.context.RequestContext; 

/**
 *
 * @author Neslihan
 */
public class StudentDAO {
     private Student user;  
    private Student newuser;  
    private List < Student > DaoAllUsers;  
    //Session session;  
    public List < Student > AllUsers()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            DaoAllUsers = session.createCriteria(Student.class).list();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
        return DaoAllUsers;  
    }  
    public Integer getId()  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        String hql = "select max(U.id) from Student U";  
        Query query = session.createQuery(hql);  
        List < Integer > results = query.list();  
        Integer userId = 1;  
        if (results.get(0) != null)  
        {  
            userId = results.get(0) + 1;  
        }  
        session.flush();  
        session.close();  
        return userId;  
    }  
  
    public void add(Student newuser)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
         String Id = Integer.toString(newuser.getId());  
            session.beginTransaction();  
            session.merge(newuser);  
            session.flush();  
            System.out.println("New Student saved, id: " +  
                newuser.getId());  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void delete(Student user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            String name = user.getName();  
            session.beginTransaction();  
            session.delete(user);  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
    public void update(Student user)  
    {  
        Session session = HibernateUtil.getSessionFactory().openSession();  
        try  
        {  
            session.beginTransaction();  
            session.update(user);  
            session.flush();  
            session.getTransaction().commit();  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
            session.getTransaction().rollback();  
        }  
        session.close();  
    }  
}
