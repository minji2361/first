package com.kg.myapp.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.kg.myapp.vo.EmpVO;

@Service
public interface IEmpService {

	EmpVO selectEmployee(int empId);
	List<EmpVO> selectAllEmployees();
	public int getEmpCount();
	public int getEmpCount(int deptId);
	public void insertEmp(EmpVO emp);
	public void updateEmp(EmpVO emp);
	public void deleteEmp(int empId);
	public void deleteJobHistory(int empId);
	List<Map<String, Object>> getAllDeptId();
	List<Map<String, Object>> getAllJobId();
	List<Map<String, Object>> getAllManagerId();
	List<EmpVO> getEmpList();
	EmpVO getEmpInfo(int empId);
	List<EmpVO> getEmpList(int deptId);
	List<EmpVO> getEmpList(String name);
	List<EmpVO> getEmpIdList(int empId);
	public void updateManagers(int empId);
//	public int getManagerCount(int employeeId);
//	public int getDeptCount(int employeeId);
	public int getUpdateCount(int employeeId);
	public int idCheck(int empId);
	
}
