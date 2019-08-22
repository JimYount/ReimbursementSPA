package com.revature.driver;

import java.util.List;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.revature.beans.Bear;
import com.revature.beans.Cave;
import com.revature.beans.HoneyPot;
import com.revature.util.HibernateUtil;

public class Driver {
	private static HibernateUtil hu = HibernateUtil.getInstance();
	private static Logger log = Logger.getLogger(Driver.class);
	
	public static void main(String[] args) {
		//firstAttempt(2);
		//insertion();
		//secondRetrieval(1);
		//buildABear();
		//getVsLoad();
		//proxyResidents();
		//cacheQuestion();
		//luisValenciaWantsToTestTransientObjectsBeingUpdated();
		//updateVsMerge();
		//automaticDirtyChecking();
		//hql();
		//nativeQuery();
		//criteria();
		//namedQueries();
		
		hu.getSessionFactory().close();
	}

	private static void namedQueries() {
		// A Query with an associated name that appears on the mapping.
		Session s = hu.getSession();
		Query<HoneyPot> q = s.createNamedQuery("getAllHoneypots", HoneyPot.class);
		List<HoneyPot> h = q.getResultList();
		log.trace(h);
		q = s.createNamedQuery("getAllHoneypotsWithHoney", HoneyPot.class);
		q.setParameter("amount", 50.0);
		h = q.getResultList();
		log.trace(h);
		s.close();
	}

	private static void criteria() {
		// Criteria
		/*
		 * A Java-based API for querying data programatically
		 * We just use Java to query our database directly
		 * We can ONLY use DQL.
		 */
		Session s = hu.getSession();
		CriteriaBuilder builder = s.getCriteriaBuilder();
		CriteriaQuery<Bear> criteria = builder.createQuery(Bear.class);
		Root<Bear> root = criteria.from(Bear.class);
		criteria.select(root).where(builder.equal(root.get("name"), "Yogi"));
		List<Bear> bears = s.createQuery(criteria).getResultList();
		
		
		log.trace(bears);
		
		
		/*
		 * Benefits:
		 * 			Object-Oriented
		 * 			Don't need to know SQL
		 * 			Don't need to know what the database looks like.
		 * 			100% Java
		 * Cons:
		 * 			Only DQL
		 * 			Possible complexity issues
		 */
	}

	private static void nativeQuery() {
		Session s = hu.getSession();
		HoneyPot h;
		String nativeSQL = "select * from Honey_pot where honeypot_id = :billybob";
		NativeQuery<HoneyPot> n = s.createNativeQuery(nativeSQL, HoneyPot.class);
		n.setParameter("billybob", 1);
		h = n.uniqueResult();
		log.trace(h);
		s.close();
	}

	private static void hql() {
		Session s = hu.getSession();
		// HQL: Hibernate Query Language
		// An object-based version of SQL that allows us to perform DQL and DML
		String query = "from com.revature.beans.Bear"; // Select * from bear
		Query<Bear> q = s.createQuery(query, Bear.class);
		List<Bear> bearList = q.getResultList();
		
		query = "from Bear where cave.caveName = :name";
		q = s.createQuery(query, Bear.class);
		q.setParameter("name", "Big Blue House");
		List<Bear> bearHouse = q.getResultList();
		
		query = "from Bear where height = :height";
		q = s.createQuery(query, Bear.class);
		q.setParameter("height", 8.0);
		Bear eightFoot = q.getSingleResult();
		
		s.close();
		
		log.trace("all bears:");
		log.trace(bearList);
		log.trace("Big Blue House Bears:");
		for(Bear b : bearHouse) {
			log.trace(b);
		}
		
		/*
		 * Benefits:
		 * 			Object-Oriented
		 * 			Don't need to know SQL
		 * 			Don't need to know what the database looks like.
		 * 			DML & DQL
		 * Cons:
		 * 			Need to learn another query language
		 */
		
		
		log.trace(eightFoot);
	}

	private static void automaticDirtyChecking() {
		// Hibernate wants to spare the db unnecessary grief.
		// Hibernate tracks the state of a persistent object
		// and updates the database accordingly so long as the session is open.
		Session s = hu.getSession();
		Transaction tx = s.beginTransaction();
		HoneyPot h = s.get(HoneyPot.class, 3);
		log.trace(h);
		h.setVolume(h.getVolume()-2);
		//s.detach(h);
		//s.update(h);
		log.trace(h);
		tx.commit();
		s.close();
		log.trace(h);
	}

	private static void updateVsMerge() {
		Bear b1 = secondRetrieval(1);
		Bear b2 = secondRetrieval(1);
		log.trace("b1==b2: "+(b1==b2));
		log.trace("b1.equals(b2): "+(b1.equals(b2)));
		b1.setBearColor("dark brown");
		log.trace(b1);
		log.trace(b2);
		log.trace("b1.equals(b2): "+(b1.equals(b2)));
		Session s = hu.getSession();
		Transaction tx = s.beginTransaction();
		s.update(b1);
		Bear b3 = (Bear) s.merge(b2);
		//s.update(b2);
		
		tx.commit();
		s.close();
		
		log.trace("b1 to b2: "+(b1==b2));
		log.trace("b2 to b3: "+(b2==b3));
		log.trace("b3 to b1: "+(b3==b1));
		
	}

	private static void luisValenciaWantsToTestTransientObjectsBeingUpdated() {
		HoneyPot h = new HoneyPot();
		h.setHoneyAmount(50.0);
		h.setVolume(90.0);
		log.trace(h);
		Session s = hu.getSession();
		s.update(h);
		s.close();
		log.trace(h);
	}

	private static void cacheQuestion() {
		Session s = hu.getSession();
		
		HoneyPot h = s.get(HoneyPot.class, 1);
		HoneyPot h1 = s.get(HoneyPot.class, 1);
		
		log.trace("h.equals(h1):"+h.equals(h1));
		log.trace("h==h1:"+(h==h1));
		
		s.close();
	}

	private static void proxyResidents() {
		Session s = hu.getSession();
		Cave c = s.get(Cave.class, 1);
		s.close();
		Set<Bear> bears = c.getResidents();
		log.trace(bears);
	}

	private static void getVsLoad() {
		HoneyPot h;
		h = getHoneyPot(9);
		log.trace(h);
		h = loadHoneyPot(9);
		log.trace(h);
		
	}

	private static HoneyPot loadHoneyPot(int i) {
		Session s = hu.getSession();
		HoneyPot h = s.load(HoneyPot.class, i); // retrieves a proxy of the object
		h.getHoneyAmount();
		s.close();
		return h;
	}

	private static HoneyPot getHoneyPot(int i) {
		Session s = hu.getSession();
		HoneyPot h = s.get(HoneyPot.class, i);
		s.close();
		return h;
	}

	private static void buildABear() {
		Bear b = new Bear();
		Cave c = new Cave();
		HoneyPot h = new HoneyPot();
		h.setHoneyAmount(50.0);
		h.setVolume(100.0);
		b.setHoneyPot(h);
		c.setCaveName("Big Blue House");
		c.setCaveType("House");
		c.setSquareFootage(3000.0);
		b.setCave(c);
		b.setBearColor("brown");
		b.setBreed("Grizzly");
		b.setName("Bear");
		b.setWeight(500.0);
		b.setHeight(8.0);
		
		Bear b2 = new Bear();
		HoneyPot h2 = new HoneyPot();
		h2.setHoneyAmount(50.0);
		h2.setVolume(80.0);
		b2.setHoneyPot(h2);
		b2.setCave(c);
		b2.setName("Ojo");
		b2.setBearColor("brown");
		b2.setBreed("grizzly");
		b2.setWeight(200.0);
		b2.setHeight(3.0);
		
		b.getBearCubs().add(b2);
		log.trace(b);
		
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(b);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		log.trace(b);
	}

	private static void insertion() {
		HoneyPot h = new HoneyPot();
		h.setHoneyAmount(54.0);
		h.setVolume(60.0);
		
		Session s = hu.getSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(h);
			tx.commit();
		} catch(Exception e) {
			tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		
		
		firstAttempt(3);
	}

	private static Bear secondRetrieval(int i) {
		Session s = hu.getSession();
		Bear b = s.get(Bear.class,i);
		log.trace(b);
		s.close();
		return b;
	}

	private static void firstAttempt(int i) {
		// open a session to the database
		Session su = hu.getSession();
		// Retrieve a honeypot from the db
		HoneyPot h = su.get(HoneyPot.class, i);
		log.trace(h);
		
		// close this session
		su.close();
	}

}
