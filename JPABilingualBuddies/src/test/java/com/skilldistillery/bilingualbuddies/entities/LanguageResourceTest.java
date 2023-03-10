package com.skilldistillery.bilingualbuddies.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LanguageResourceTest {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	private LanguageResource lr;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("JPABilingualBuddies");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		lr = em.find(LanguageResource.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		lr = null;
	}

	@Test
	void test() {
		assertNotNull(lr);
		assertEquals("Learn English", lr.getName());
	}

	@Test
	void test_ManyToOne_to_Lang() {
		assertNotNull(lr);
		assertEquals("English", lr.getLanguage().getName());
	}
	
	@Test
	void test_MTO_mapping_to_user() {
		assertNotNull(lr);
		assertEquals(1, lr.getAddedBy().getId());
		
	}

}
