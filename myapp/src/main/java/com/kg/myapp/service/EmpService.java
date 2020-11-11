package com.kg.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.kg.myapp.dao.IEmpRepository;
import com.kg.myapp.vo.EmpVO;
import com.kg.myapp.vo.JobVO;

@Service
public class EmpService {
	
	@Autowired
	IEmpRepository empRepository;
	
	public EmpVO selectEmployee(int empId) {
		return empRepository.selectEmployee(empId);
	}

	public List<EmpVO> selectAllEmployees(){
		return empRepository.selectAllEmployees();
	}
	
	public int getEmpCount() {
		return empRepository.getEmpCount();
	}
	
	public int getEmpCount(int deptId) {
		return empRepository.getEmpCount(deptId);
	}
	
	public void insertEmp(EmpVO emp) {
		empRepository.insertEmp(emp);
	}
	
	public void updateEmp(EmpVO emp) {
		empRepository.deleteJobHistory(emp.getEmployeeId());
		empRepository.updateEmp(emp);
	}
	
	public void deleteEmp(int empId) {
		empRepository.deleteJobHistory(empId);
		empRepository.updateManagers(empId);
		empRepository.deleteEmp(empId);
	}
	
	public void deleteJobHistory(int empId) {
		empRepository.deleteJobHistory(empId);
	}
	
	public List<Map<String, Object>> getAllDeptId() {
		return empRepository.getAllDeptId();
	}
	
	public List<Map<String, Object>> getAllJobId() {
		return empRepository.getAllJobId();
	}
	
	public List<Map<String, Object>> getAllManagerId() {
		return empRepository.getAllManagerId();
	}	
	
	public List<EmpVO> getEmpList() {
	return empRepository.getEmpList();
	}
	
	public EmpVO getEmpInfo(int empId) {
	return empRepository.getEmpInfo(empId);
	}
	
	//public List<EmpVO> getDeptList(int dptId) {
	//return empRepository.getDeptList(dptId);	
	//}
	
	public List<EmpVO> getEmpList(int deptId) {
	return empRepository.getEmpList(deptId);
	}
	
	public List<EmpVO> getEmpList(String name) {
	return empRepository.getEmpList(name);
	}
	
	public List<EmpVO> getEmpIdList(int empId) {
	return empRepository.getEmpIdList(empId);
	}
	
//	public int getManagerCount(int empId) {
//		return empRepository.getManagerCount(empId);
//	}
//	
//	public int getDeptCount(int employeeId) {
//		return empRepository.getDeptCount(employeeId);
//	}
	
	public int getUpdateCount(int employeeId) {
		return empRepository.getUpdateCount(employeeId);
	}
	
	public int idCheck(int empId) {
		return empRepository.idCheck(empId);
	}
	
	/*
	 * public List<JobVO> getAllJobId2() { return empRepository.getAllJobId2(); }
	 */
	
}






















