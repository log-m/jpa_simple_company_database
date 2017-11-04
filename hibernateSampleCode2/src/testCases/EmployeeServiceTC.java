package testCases;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import basicJPA.EmployeeServiceImpl;
import basicJPA.entity.Employee;

public class EmployeeServiceTC
{
	protected static EntityManagerFactory emf;

	@BeforeClass
	public static void createEntityManagerFactory()
	{
		emf = Persistence.createEntityManagerFactory("jpa-employee");
	}

	@AfterClass
	public static void closeEntityManagerFactory()
	{
		emf.close();
	}

	@Test
	public void testCreate()
	{
		EntityManager em = emf.createEntityManager();
		EmployeeServiceImpl empService = new EmployeeServiceImpl(em);

		Employee emp = buildEmployee();
		assertNull(emp.getEmpID());
		empService.create(emp);
		assertNotNull(emp.getEmpID());

		Employee emp2 = em.find(Employee.class, emp.getEmpID());
		assertNotNull(emp2);
		assertTrue(emp2 == emp);

		em.close();
	}

	@Test
	public void testFindEmployee()
	{
		EntityManager em = emf.createEntityManager();
		EmployeeServiceImpl empService = new EmployeeServiceImpl(em);

		Employee emp = buildEmployee();
		empService.create(emp);
		Long id = emp.getEmpID();

		Employee emp2 = empService.find(id);
		assertNotNull(emp2);
		assertEquals(emp.getEmpID(), emp2.getEmpID());
		assertEquals(emp.getEmpName(), emp2.getEmpName());
		assertEquals(emp.getSalary(), emp2.getSalary(), .01);

		em.close();
	}

	@Test
	public void testUpdateEmployee()
	{
		EntityManager em = emf.createEntityManager();
		EmployeeServiceImpl empService = new EmployeeServiceImpl(em);

		Employee emp = buildEmployee();
		empService.create(emp);
		Long id = emp.getEmpID();

		Long ts = System.currentTimeMillis();
		emp.setSalary(ts);

		empService.update(emp);
		em.close();

		em = emf.createEntityManager();
		empService = new EmployeeServiceImpl(em);

		Employee emp2 = empService.find(id);
		assertEquals(ts, emp2.getSalary(), .01);

		em.close();
	}

	@Test
	public void testRetrieveEmpByDeptAndSalary()
	{
		EntityManager em = emf.createEntityManager();
		EmployeeServiceImpl empService = new EmployeeServiceImpl(em);
		
		List<Employee> emps = empService.retrieveEmpByDeptAndSalary("OSI", 100000);
		assertTrue(!emps.isEmpty());
	}

	private Employee buildEmployee()
	{
		Employee emp = new Employee();
		Long ts = System.currentTimeMillis();
		emp.setEmpName("Employee " + ts);
		emp.setSalary(ts);
		return emp;
	}

}
