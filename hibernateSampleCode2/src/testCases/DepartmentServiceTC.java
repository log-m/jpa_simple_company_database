package testCases;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import basicJPA.DepartmentServiceImpl;
import basicJPA.entity.Department;
import basicJPA.entity.Employee;

public class DepartmentServiceTC
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
		DepartmentServiceImpl deptService = new DepartmentServiceImpl(em);

		Department dept = buildDepartment();
		assertNull(dept.getDeptID());
		deptService.create(dept);
		assertNotNull(dept.getDeptID());

		Department dept2 = em.find(Department.class, dept.getDeptID());
		assertNotNull(dept2);
		assertTrue(dept == dept2);

		em.close();
	}

	@Test
	public void testFindDepartment()
	{
		EntityManager em = emf.createEntityManager();
		DepartmentServiceImpl deptService = new DepartmentServiceImpl(em);

		Department dept = buildDepartment();
		deptService.create(dept);
		assertNotNull(dept.getDeptID());

		Department dept2 = deptService.find(dept.getDeptID());
		assertNotNull(dept2);
		assertEquals(dept.getDeptID(), dept2.getDeptID());
		assertEquals(dept.getDeptName(), dept2.getDeptName());
		assertEquals(dept.getDescription(), dept2.getDescription());

		em.close();
	}

	@Test
	public void testUpdateDepartment()
	{
		EntityManager em = emf.createEntityManager();
		DepartmentServiceImpl deptService = new DepartmentServiceImpl(em);

		Department dept = buildDepartment();
		deptService.create(dept);
		assertNotNull(dept.getDeptID());

		Long ts = System.currentTimeMillis();
		dept.setDescription("UpdatedDescription " + ts);

		deptService.update(dept);
		em.close();

		em = emf.createEntityManager();
		deptService = new DepartmentServiceImpl(em);

		Department dept2 = deptService.find(dept.getDeptID());
		assertEquals(dept.getDescription(), dept2.getDescription());

		em.close();
	}

	@Test
	public void testAddEmpToDept() throws Exception
	{
		EntityManager em = emf.createEntityManager();
		DepartmentServiceImpl deptService = new DepartmentServiceImpl(em);

		Department dept = deptService.findByName("OSI");
		int beforeSize = dept.getEmployees().size();

		Employee emp = buildEmployee();
		deptService.addEmpToDept("OSI", emp);
		
		Department dept2 = deptService.findByName("OSI");
		assertTrue(beforeSize < dept2.getEmployees().size());
	}

	@Test
	public void testRemoveEmpFromDept() throws Exception
	{
		EntityManager em = emf.createEntityManager();
		DepartmentServiceImpl deptService = new DepartmentServiceImpl(em);

		Department dept = deptService.findByName("OSI");
		int beforeSize = dept.getEmployees().size();

		Employee emp = (Employee)dept.getEmployees().toArray()[0];
		deptService.removeEmpFromDept("OSI", emp);
		
		Department dept2 = deptService.findByName("OSI");
		assertTrue(beforeSize > dept2.getEmployees().size());
	}

	private Department buildDepartment()
	{
		Department dept = new Department();
		Long ts = System.currentTimeMillis();
		dept.setDeptName("DeptName " + ts);
		dept.setDescription("DeptDesc " + ts);
		return dept;
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
