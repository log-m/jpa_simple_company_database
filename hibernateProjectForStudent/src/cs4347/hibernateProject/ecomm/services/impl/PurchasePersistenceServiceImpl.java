package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cs4347.hibernateProject.ecomm.entity.Purchase;
import cs4347.hibernateProject.ecomm.services.PurchasePersistenceService;
import cs4347.hibernateProject.ecomm.services.PurchaseSummary;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class PurchasePersistenceServiceImpl implements PurchasePersistenceService
{
	@PersistenceContext 
	private EntityManager em; 
	
	public PurchasePersistenceServiceImpl(EntityManager em) {
		this.em = em;
	}
	
	/**
	 * Create purchase given purchase
	 * @param purchase to create
	 */
	@Override
	public void create(Purchase purchase) throws SQLException, DAOException
	{
		try{
			em.getTransaction().begin();
			em.persist(purchase);
			em.getTransaction().commit();
		}
		catch(Exception ex){
			em.getTransaction().rollback();
			throw ex; 
		}
	}

	/**
	 * Retrieve purchase given its id
	 * @param id of purchase to retrieve
	 * @return purchase id
	 */
	@Override
	public Purchase retrieve(Long id) throws SQLException, DAOException
	{
		try{
			em.getTransaction().begin();
			Purchase purch = em.find(Purchase.class, id);
			em.getTransaction().commit();
			return purch;
		}
		catch(Exception ex){
			em.getTransaction().rollback();
			throw ex; 
		}
	}

	/**
	 * Delete purchase given its id.
	 * @param purchase to delete
	 */
	@Override
	public void update(Purchase purchase) throws SQLException, DAOException
	{
		try{
			em.getTransaction().begin();
			Purchase purch = em.find(Purchase.class, purchase.getId());
			purch.setPurchaseDate(purchase.getPurchaseDate());
			purch.setPurchaseAmount(purchase.getPurchaseAmount());
			purch.setCustomer(purchase.getCustomer());
			purch.setProduct(purchase.getProduct());
			em.getTransaction().commit();
		}
		catch(Exception ex){
			em.getTransaction().rollback();
			throw ex; 
		}
	}

	/**
	 * Delete purchase given its id.
	 * @param id id of purchase
	 */
	@Override
	public void delete(Long id) throws SQLException, DAOException
	{
		try{
			em.getTransaction().begin();
			Purchase purch = em.find(Purchase.class, id);
			em.remove(purch);
			em.getTransaction().commit();
		}
		catch(Exception ex){
			em.getTransaction().rollback();
			throw ex; 
		}
	}
	
	/**
	 * Retrieve purchases made by the given customer.
	 * @param customerID id of customer to retrieve purchases
	 * @return list of purchases made by customer
	 */
	@Override
	public List<Purchase> retrieveForCustomerID(Long customerID) throws SQLException, DAOException
	{
		em.getTransaction().begin();
		List<Purchase> purchs = (List<Purchase>) em.createQuery("from Purchase as p where p.customer.id = :customerID")
			.setParameter("customerID", customerID)
			.getResultList();
		em.getTransaction().commit();
		return purchs;
	}

	/**
	 * Produce a purchase summary report for the given customer.
	 * @param customerID id of the customer to retrieve purchase summary
	 * @return pruchase summary for given customer
	 */
	@Override
	public PurchaseSummary retrievePurchaseSummary(Long customerID) throws SQLException, DAOException
	{
		PurchaseSummary purchSum = new PurchaseSummary();
		List<Double> results = em.createQuery("SELECT MIN(p.purchaseAmount), MAX(p.purchaseAmount), AVG(p.purchaseAmount)
		FROM Purchase as p WHERE p.customer.id = :customerID")
			.setParameter("customerID", customerID)
			.getResultList();
		purchSum.minPurchase = results.get(0);
		purchSum.maxPurchase = results.get(1);
		purchSum.avgPurchase = results.get(2);
		return purchSum;
	}

	/**
	 * Retrieve purchases made for the given product.
	 * @param productID id of product to retrieve purchases
	 * @return list of purchases for given product
	 */
	@Override
	public List<Purchase> retrieveForProductID(Long productID) throws SQLException, DAOException
	{
		em.getTransaction().begin();
		List<Purchase> purchs = (List<Purchase>) em.createQuery("from Purchase as p where p.product.id = :productID")
			.setParameter("productID, productID")
			.getResultList();
		em.getTransaction().commit();
		return purchs;
	}
}
