package com.hibernate.quizapp;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class QuestionUtil {

	private static SessionFactory factory=null;
	private static SessionFactory getFactory() {
		if(factory==null) {
			factory=new Configuration().configure().buildSessionFactory();
		}
		return factory;
	}
	
	public List<Question> getAllQuestions(){
		Session session = getFactory().openSession();
		Query<Question> query= session.createQuery("from Question");
		List<Question> q = query.list();
		session.close();
		return q;
		
	}
}
