package com.revature.driver;

import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
		getVsLoad();
		//proxyResidents();
		System.exit(0);
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

	private static void secondRetrieval(int i) {
		Session s = hu.getSession();
		Bear b = s.get(Bear.class,i);
		log.trace(b);
		s.close();
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
