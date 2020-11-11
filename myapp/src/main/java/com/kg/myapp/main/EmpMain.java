package com.kg.myapp.main;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.kg.myapp.service.EmpService;
import com.kg.myapp.vo.EmpVO;

public class EmpMain {
	
	public static void main(String[] args) {
		AbstractApplicationContext con =
				new GenericXmlApplicationContext("application-config.xml");
		EmpService empService = con.getBean(EmpService.class);
		empService.deleteEmp(100);
		
//		System.out.println(empService.selectEmployee(100));
//		List<EmpVO> empList = empService.selectAllEmployees();
//		for (EmpVO emp : empList) {
//			System.out.println(emp);
//		}		
//		System.out.println(empService.getEmpCount());
//		System.out.println(empService.getEmpCount(30));
//		System.out.println(empService.getAllManagerId());
//		con.close();
	}

}
