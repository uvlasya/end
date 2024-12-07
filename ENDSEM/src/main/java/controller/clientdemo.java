package controller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
public class clientdemo {

	    public static void main(String[] args) {
	        // Configure Hibernate
	        Configuration config = new Configuration();
	        config.configure("hibernate.cfg.xml");
	        config.addAnnotatedClass(department.class);
	        SessionFactory sessionFactory = config.buildSessionFactory();

	        // Perform update operation
	        Session session = sessionFactory.openSession();
	        Transaction transaction = session.beginTransaction();

	        try {
	            // HQL update query using positional parameters
	            String hql = "UPDATE Department SET name = ?1, location = ?2 WHERE id = ?3";
	            int updatedEntities = session.createQuery(hql)
	                    .setParameter(1, "Updated Name")
	                    .setParameter(2, "Updated Location")
	                    .setParameter(3, 1)
	                    .executeUpdate();

	            System.out.println("Number of records updated: " + updatedEntities);

	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction != null) transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	            sessionFactory.close();
	        }
	    }
	}

