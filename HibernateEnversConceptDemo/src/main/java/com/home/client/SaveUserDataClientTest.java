package com.home.client;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.home.entities.User;
import com.home.util.HibernateUtil;

public class SaveUserDataClientTest {
	public static void main(String[] args) {
		Transaction tx = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			tx = session.beginTransaction();
			System.out.println("Create records ----------------------");
			User user1 = new User();
			user1.setFirstName("Vivek");
			user1.setLastName("Garg");
			user1.setCreatedOn(new Date());
			
			User user2 = new User();
			user2.setFirstName("Prabhat");
			user2.setLastName("Singh");
			user2.setCreatedOn(new Date());
			
			session.save(user1);
			session.save(user2);
			System.out.println("Create record successfully-----------------");
			tx.commit();
		} catch (Exception e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
		
		System.out.println("Update Record-----------------------------------");
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			long userId=1l;
			User user = session.get(User.class,userId);
			if(user!=null) {
				tx=session.beginTransaction();
				user.setLastName("gargg");
				session.update(user);
				tx.commit();
				System.out.println("Update Record successfully----------------------------------");
			}
			else {
				System.out.println("User detail not found with id: "+userId);
			}
		} catch (Exception e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
		
System.out.println("Delete Record-----------------------------------");
		
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {

			long userId=2l;
			User user = session.get(User.class,userId);
			if(user!=null) {
				tx=session.beginTransaction();
				session.delete(user);
				tx.commit();
				System.out.println("Delete Record successfully----------------------------------");
			}
			else {
				System.out.println("User detail not found with id: "+userId);
			}
		} catch (Exception e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			throw e;
		}
	}

}
