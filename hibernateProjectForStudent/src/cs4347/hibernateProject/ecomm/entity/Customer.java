package cs4347.hibernateProject.ecomm.entity;
import java.sql.Date;

import javax.persistence.*;
@Entity
@Table(name = "Customer")
public class Customer
{
	 private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Character gender;
	
	private Date dob;
	
	private String email;
	
	private Address address;
	
	private CreditCard creditCard;
	
	@Column(name = "id")
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	@Column(name = "firstName")
	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	@Column(name = "lastName")
	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}
	
	@Column(name = "gender")
	public Character getGender()
	{
		return gender;
	}

	public void setGender(Character gender)
	{
		this.gender = gender;
	}
	
	@Column(name = "dob")
	public Date getDob()
	{
		return dob;
	}

	public void setDob(Date dob)
	{
		this.dob = dob;
	}

	@Column(name = "email")
	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public Address getAddress()
	{
		return address;
	}

	public void setAddress(Address address)
	{
		this.address = address;
	}

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	public CreditCard getCreditCard()
	{
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard)
	{
		this.creditCard = creditCard;
	}
}
