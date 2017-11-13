package cs4347.hibernateProject.ecomm.entity;
@Entity
@Table(name = "CreditCard")
public class CreditCard 
{
	@Column(name = "id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
	
	private Long id;
	
	private String name;
	
	private String ccNumber;
	
	private String expDate;
	
	private String securityCode;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "name")
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
	
	@Column(name = "ccNumber")
	public String getCcNumber()
	{
		return ccNumber;
	}

	public void setCcNumber(String ccNumber)
	{
		this.ccNumber = ccNumber;
	}

	@Column(name = "expDate")
	public String getExpDate()
	{
		return expDate;
	}

	public void setExpDate(String expDate)
	{
		this.expDate = expDate;
	}

	@Column(name = "securityCode")
	public String getSecurityCode()
	{
		return securityCode;
	}

	public void setSecurityCode(String securityCode)
	{
		this.securityCode = securityCode;
	}

}
