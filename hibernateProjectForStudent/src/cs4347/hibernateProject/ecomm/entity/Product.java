package cs4347.hibernateProject.ecomm.entity;
@Entity
@Table(name = "product")
public class Product 
{
	@Column(name = "id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	
	private String prodName;
	
	private String prodDescription;
	
	private int prodCategory;
	
	private String prodUPC;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	@Column(name = "prodName")
	public String getProdName()
	{
		return prodName;
	}

	public void setProdName(String prodName)
	{
		this.prodName = prodName;
	}
	@Column(name = "prodDescription")
	public String getProdDescription()
	{
		return prodDescription;
	}

	public void setProdDescription(String prodDescription)
	{
		this.prodDescription = prodDescription;
	}
	@Column(name = "prodCategory")
	public int getProdCategory()
	{
		return prodCategory;
	}

	public void setProdCategory(int prodCategory)
	{
		this.prodCategory = prodCategory;
	}
	@Column(name = "prodUPC")
	public String getProdUPC()
	{
		return prodUPC;
	}

	public void setProdUPC(String prodUPC)
	{
		this.prodUPC = prodUPC;
	}

}
