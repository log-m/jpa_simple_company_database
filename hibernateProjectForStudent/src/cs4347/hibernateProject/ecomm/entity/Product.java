package cs4347.hibernateProject.ecomm.entity;
@Entity
@Table(name = "product")
public class Product 
{
	@Column(name = "id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	@Column(name = "prodName")
	private String prodName;
	@Column(name = "prodDescription")
	private String prodDescription;
	@Column(name = "prodCategory")
	private int prodCategory;
	@Column(name = "prodUPC")
	private String prodUPC;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getProdName()
	{
		return prodName;
	}

	public void setProdName(String prodName)
	{
		this.prodName = prodName;
	}

	public String getProdDescription()
	{
		return prodDescription;
	}

	public void setProdDescription(String prodDescription)
	{
		this.prodDescription = prodDescription;
	}

	public int getProdCategory()
	{
		return prodCategory;
	}

	public void setProdCategory(int prodCategory)
	{
		this.prodCategory = prodCategory;
	}

	public String getProdUPC()
	{
		return prodUPC;
	}

	public void setProdUPC(String prodUPC)
	{
		this.prodUPC = prodUPC;
	}

}
