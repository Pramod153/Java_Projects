package com.hibernate.quizapp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

//import com.hibernate.hql_I.Students;

public class UserUtil {
	private static SessionFactory factory=null;
	private static SessionFactory getFactory() {
		if(factory==null) {
			factory=new Configuration().configure().buildSessionFactory();
		}
		return factory;
	}
	public void regUser(User u) {
		Session session = getFactory().openSession();
		session.beginTransaction();
		session.save(u);
		session.getTransaction().commit();
		session.close();
	}
	public User loginUser(String User_name,String Password) {
		Session session = getFactory().openSession();
		session.beginTransaction();
		Query<User> query=session.createQuery("from User where User_name= :value and Password= :value1");
		query.setString("value",name);
		query.setString("value1",assword);
		User u= query.uniqueResult();
		session.close();
		return u;
	}
}
