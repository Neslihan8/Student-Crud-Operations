/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.hs.studentcrudoperations.bean;

import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import org.hs.studentcrudoperations.pojo.Student;
import java.io.Serializable;
import org.primefaces.event.RowEditEvent;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import org.hs.studentcrudoperations.dao.StudentDAO;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Neslihan
 */
@Named("studentBean")
@SessionScoped
public class StudentBean implements Serializable {

    private List< Student> usersList;
    private List< Student> searchList;
    private List< Student> searchByRecordNoList;
    StudentDAO userDao = new StudentDAO();
    Student user = new Student();
    Student newuser = new Student();

    public List< Student> getUsers() {
        usersList = userDao.AllUsers();
        int count = usersList.size();
        return usersList;
    }

    public void addUser() {
        int userId = 0;
        userId = userDao.getId();
        newuser.setId(userId);
        String Id = Integer.toString(newuser.getId());
        userDao.add(newuser);
        System.out.println("Student successfully saved.");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Save Information", "Student successfully saved.");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        newuser = new Student();
    }

    public void changeUser(Student user) {
        this.user = user;
    }

    public void UpdateUser(Student user) {
        String Name = user.getName();
        FacesMessage message1 = new FacesMessage(FacesMessage.SEVERITY_INFO, "Name", Name);
        RequestContext.getCurrentInstance().showMessageInDialog(message1);
        userDao.update(user);
        System.out.println("Student Info successfully saved.");
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Student Information", "User updated successfully .");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
        user = new Student();
    }

    public void deleteUser(Student user) {

        userDao.delete(user);
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete", "Record deleted successfully");
        RequestContext.getCurrentInstance().showMessageInDialog(message);
    }

    public Student getUser() {
        return user;
    }

    public void setUser(Student user) {
        this.user = user;
    }

    public Student getNewuser() {
        return newuser;
    }

    public void setNewuser(Student newuser) {
        this.newuser = newuser;
    }

    public List< Student> getUsersList() {
        return usersList;
    }

    public void setUsersList(List< Student> usersList) {
        this.usersList = usersList;
    }

    public List< Student> getSearchList() {
        return searchList;
    }

    public void setSearchList(List< Student> searchList) {
        this.searchList = searchList;
    }

    public List< Student> getSearchByRecordNoList() {
        return searchByRecordNoList;
    }

    public void setSearchByRecordNoList(List< Student> searchByRecordNoList) {
        this.searchByRecordNoList = searchByRecordNoList;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(" Edited Record");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Student editeduser = (Student) event.getObject();
        userDao.update(editeduser);
    }

    public void onCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        usersList.remove((Student) event.getObject());
    }
}
