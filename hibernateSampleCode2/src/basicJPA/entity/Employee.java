package basicJPA.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee
{
	private Long empID;
	private String empName;
	private double salary;
	private Department department;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getEmpID()
	{
		return empID;
	}

	public void setEmpID(Long empID)
	{
		this.empID = empID;
	}

	public String getEmpName()
	{
		return empName;
	}

	public void setEmpName(String empName)
	{
		this.empName = empName;
	}

	public double getSalary()
	{
		return salary;
	}

	public void setSalary(double salary)
	{
		this.salary = salary;
	}

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="dept_id", unique=false) // FK column to be generated.
	public Department getDepartment()
	{
		return department;
	}

	public void setDepartment(Department department)
	{
		this.department = department;
	}

}
