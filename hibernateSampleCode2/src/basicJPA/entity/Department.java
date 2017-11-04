package basicJPA.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department
{
	private Long deptID;
	private String deptName;
	private String description;
	private Set<Employee> employees;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getDeptID()
	{
		return deptID;
	}

	public void setDeptID(Long deptID)
	{
		this.deptID = deptID;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	@OneToMany(fetch=FetchType.LAZY, mappedBy="department")
	public Set<Employee> getEmployees()
	{
		return employees;
	}

	public void setEmployees(Set<Employee> employees)
	{
		this.employees = employees;
	}
	
	public void addEmployee(Employee emp) {
		employees.add(emp);
	}

	public void removeEmployee(Employee emp) {
		employees.remove(emp);
	}

}
