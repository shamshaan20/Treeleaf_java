package com.diwash.hibernatedemo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.io.IOException;
import java.util.List;

public class App {
	public static void main(String[] args) {

		try {
			Session session = HibernateUtil.getSession();
			Criteria criteria = session.createCriteria(Employee.class).add(
					Restrictions.disjunction().add(Restrictions.eq("empName", "user1")).add(Restrictions.eq("empId", 2))

			);
			List<Employee> empList = criteria.list();
			session.getTransaction().commit();

			for (Employee employee : empList) {
				System.out.println(employee.getId());
				System.out.println(employee.getEmpName());
				System.out.println(employee.getEmpId());
				System.out.println("============");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
