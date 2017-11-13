package cs4347.hibernateProject.ecomm.entity;
import javax.persistence.*;
import java.sql.Date;
@Entity
@Table(name = "Purchase")
public class Purchase 
{
	   private Long id;
	private Date purchaseDate;
	private double purchaseAmount;
	private Customer customer;
	private Product product;
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
		
	@Column(name = "purchaseDate")
	public Date getPurchaseDate()
	{
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}
	@Column(name = "purchaseAmount")
	public double getPurchaseAmount()
	{
		return purchaseAmount;
	}

	public void setPurchaseAmount(double purchaseAmount)
	{
		this.purchaseAmount = purchaseAmount;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	public Customer getCustomer()
	{
		return customer;
	}

	public void setCustomer(Customer customer)
	{
		this.customer = customer;
	}
	
	@OneToOne(fetch = FetchType.EAGER)
	public Product getProduct()
	{
		return product;
	}

	public void setProduct(Product product)
	{
		this.product = product;
	}

}
