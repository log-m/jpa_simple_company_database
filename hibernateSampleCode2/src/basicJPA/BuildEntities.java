package basicJPA;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import basicJPA.entity.Department;
import basicJPA.entity.Employee;

public class BuildEntities
{

	private void createOSI(EntityManager em)
	{
		em.getTransaction().begin();
		
		Department newDept = new Department();
		newDept.setDeptName("OSI");
		newDept.setDescription("Office of Secret Intelligence");
		em.persist(newDept);

		Employee newEmp = new Employee();
		newEmp.setEmpName("Hunter Gathers");
		newEmp.setSalary(100000);
		newEmp.setDepartment(newDept);
		em.persist(newEmp);
		
		newEmp = new Employee();
		newEmp.setEmpName("Brock Sampson");
		newEmp.setSalary(90000);
		newEmp.setDepartment(newDept);
		em.persist(newEmp);
		
		em.getTransaction().commit();
	}

	private void createGuild(EntityManager em)
	{
		em.getTransaction().begin();
		
		Department newDept = new Department();
		newDept.setDeptName("Guild");
		newDept.setDescription("The Guild of Calamitous Intent");
		em.persist(newDept);

		Employee newEmp = new Employee();
		newEmp.setEmpName("The Monarch");
		newEmp.setSalary(100000);
		newEmp.setDepartment(newDept);
		em.persist(newEmp);
		
		newEmp = new Employee();
		newEmp.setEmpName("Dr. Girlfriend");
		newEmp.setSalary(90000);
		newEmp.setDepartment(newDept);
		em.persist(newEmp);
		
		em.getTransaction().commit();
	}
	
	public static void main(String args[])
	{
	    EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-employee");
		EntityManager em = emf.createEntityManager();
		
		BuildEntities app = new BuildEntities();
		app.createGuild(em);
		app.createOSI(em);
		em.close();
		emf.close();
	}
}
