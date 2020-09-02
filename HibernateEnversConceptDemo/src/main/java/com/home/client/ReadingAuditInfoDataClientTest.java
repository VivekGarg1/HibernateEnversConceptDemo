package com.home.client;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;

import com.home.entities.User;
import com.home.util.HibernateUtil;

public class ReadingAuditInfoDataClientTest {
	public static void main(String[] args) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			long userId = 1;
			AuditReader auditReader = AuditReaderFactory.get(session);
			List<Number> revisions = auditReader.getRevisions(User.class, userId);
			for (Number rev : revisions) {
				User user = auditReader.find(User.class, userId, rev);
				System.out.println("Revision no: " + rev);
				System.out.println(user);
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
