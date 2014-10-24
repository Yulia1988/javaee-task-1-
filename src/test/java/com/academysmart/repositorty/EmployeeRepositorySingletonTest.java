package com.academysmart.repositorty;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.repository.EmployeeRepositorySingleton;

public class EmployeeRepositorySingletonTest {

	@BeforeClass
	public static void beforeClass()throws Exception {
		EmployeeRepositorySingleton.getRepository().addEmployee("Иван",
				"Иванов", "ivanov@mail.ru");
	}

	@Test
	public void testGetRepositoryReturnOneInstance() {
		//Assert.fail("Test is not implemented");
	}
	
	
	@Test(expected=com.academysmart.exception.IncorrectEmailException.class)
	public void testAddEmployeWithIncorrectEmail() throws ServletException  {
		//Assert.fail("Test is not implemented");
				EmployeeRepositorySingleton.getRepository().addEmployee("Иван",
				"Иванов", "ivanov@mail.ru");
	}

}
