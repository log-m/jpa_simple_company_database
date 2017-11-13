package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cs4347.hibernateProject.ecomm.entity.Customer;
import cs4347.hibernateProject.ecomm.services.CustomerPersistenceService;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class CustomerPersistenceServiceImpl implements CustomerPersistenceService
{
	@PersistenceContext 
	private EntityManager em; 
	
	public CustomerPersistenceServiceImpl(EntityManager em) {
		this.em = em;
	}
	
	/**
	 */
	@Override
	public void create(Customer customer) throws SQLException, DAOException
	{
			try {
			em.getTransaction().begin();
			em.persist(customer);
			em.persist(address);
			em.persist(creditCard);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public Customer retrieve(Long id) 
	{
		try {
			em.getTransaction().begin();
			Customer cust = em.find(Customer.class, id);
			em.getTransaction().commit();
			return cust;
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
	}

	@Override
	public void update(Customer c1) throws SQLException, DAOException
	{
		try {
			em.getTransaction().begin();
			Customer c2 = em.find(Customer.class, c1.getID());
			c2.setFirstName(c1.getFirstName());
			c2.setLastName(c1.getLastName());
			c2.setGender(c1.getGender());
			c2.setDob(c1.getDob());
			c2.setEmail(c1.getEmail());
			//c2.setAddress(c1.getAddress());
			Address a1 = c1.getAddress();
			Address a2 = c2.getAddress();
			a2.setAddress1(a1.getAddress1());
			a2.setAddress2(a1.getAddress2());
			a2.setCity(a1.getCity());
			a2.setState(a1.getState());
			a2.setZipCode(a1.getZipCode());
			//c2.setCreditCard(c1.getCreditCard());
			CreditCard cr1 = c1.getCreditCard();
			CreditCard cr2 = c2.getCreditCard();
			cr2.setName(cr1.getName());
			cr2.setCcNumber(cr1.getCcNumber());
			cr2.setExpDate(cr1.getExpDate());
			cr2.setSecurityCode(cr1.getSecurityCode());
			
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}
	

	@Override
	public void delete(Long id) throws SQLException, DAOException
	{
		try{
				em.getTransaction().begin();
				Customer cust = em.find(Customer.class, id);
				em.remove(cust);
				em.getTransaction().commit();
		    }
		    
		    catch(Expection ex){
		    	em.Transaction().rollback();
			throw ex;
		     }
	}

	@Override
	public List<Customer> retrieveByZipCode(String zipCode) throws SQLException, DAOException
	{
	em.getTransaction().begin();
		List<Customer> cust = (List<Customer>) em.createQuery("from Customer as p where p.address.zipcode = :zip")
			.setParameter("zip", zipCode)
			.getResultList();
		em.getTransaction().commit();
		return cust;
	}

	@Override
	public List<Customer> retrieveByDOB(Date startDate, Date endDate) throws SQLException, DAOException
	{
		return null;
	}
}
