package com.in.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.in.dao.UsersDao;
import com.in.user.Users;

@Repository
@Transactional
public class UserDaoImpl implements UsersDao{
	
	@Autowired
    SessionFactory session;
 
    public boolean saveOrUpdate(Users users) {
        session.getCurrentSession().saveOrUpdate(users);
        return true;
    }
 
    public List<Users> list() {
    	System.out.println("inside UserDAO impl list()");
    	//HQL work with entity  class name not Table name
    	List<Users> list = session.getCurrentSession().createQuery("from Users").list();
    	System.out.println("DAO : "+list);
    	for(Users u : list){
			System.out.println("User List:: "+u.getEmail());
		}
        return list;
    }
 
    public boolean delete(Users users) {
        try {
            session.getCurrentSession().delete(users);
        } catch (Exception ex) {
            return false;
        }
 
        return true;
    }
}
