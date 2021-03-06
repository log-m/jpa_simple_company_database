//GROUP 3
//Logan Morris, Troy Kim, Karey Smith, Ashley Handoko
package cs4347.hibernateProject.ecomm.services.impl;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import cs4347.hibernateProject.ecomm.entity.Product;
import cs4347.hibernateProject.ecomm.services.ProductPersistenceService;
import cs4347.hibernateProject.ecomm.util.DAOException;

public class ProductPersistenceServiceImpl implements ProductPersistenceService
{
	@PersistenceContext 
	private EntityManager em; 
	
	public ProductPersistenceServiceImpl(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public void create(Product product) throws SQLException, DAOException
	{
	try {
			em.getTransaction().begin();
			em.persist(product);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
}
	}

	@Override
	public Product retrieve(Long id) throws SQLException, DAOException
	{
		try {
			//Get reference to table entry with find
			em.getTransaction().begin();
			Product prod = em.find(Product.class, id);
			em.getTransaction().commit();
			return prod;
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
}
	}

	@Override
	public void update(Product product) throws SQLException, DAOException
	{
		try {
			//Get reference to table entry, change attribute values from the Java side of things
			em.getTransaction().begin();
			Product prod = em.find(Product.class, product.getId());
			prod.setProdName(product.getProdName());
			prod.setProdDescription(product.getProdDescription());
			prod.setProdCategory(product.getProdCategory());
			prod.setProdUPC(product.getProdUPC());
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
			//get reference to table entry and remove it with EntityManager's remove method
			em.getTransaction().begin();
			Product prod = em.find(Product.class, id);
			em.remove(prod);
			em.getTransaction().commit();
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

	@Override
	public Product retrieveByUPC(String upc) throws SQLException, DAOException
	{
		
		em.getTransaction().begin();
		
		//JPQL Query
		Product prods = (Product)em.createQuery("from Product as p where p.prodUPC = :upc")
				.setParameter("upc", upc)
				.getSingleResult(); //There should only be one product for any given upc code, but upc is not the primary key, so JPQL must be used instead of find
				;
		em.getTransaction().commit();
		return prods;
	}

	@Override
	public List<Product> retrieveByCategory(int category) throws SQLException, DAOException
	{
		try{
			em.getTransaction().begin();
			
			//JPQL query
			List<Product> prods = (List<Product>)em.createQuery("from Product as p where p.prodCategory = :cat")
				.setParameter("cat", category)
				.getResultList();
			em.getTransaction().commit();
			return prods;
		}
		catch (Exception ex) {
			em.getTransaction().rollback();
			throw ex;
		}
	}

}
