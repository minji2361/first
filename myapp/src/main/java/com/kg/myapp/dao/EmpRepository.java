//package com.kg.myapp.dao;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Repository;
//
//import com.kg.myapp.vo.EmpDetailVO;
//import com.kg.myapp.vo.EmpVO;
//import com.kg.myapp.vo.JobVO;
//
////alter table employees modify (file_size VARCHAR2(45) null);로 db로 변경
////단 들어간 파일이 있으면 수정이 안되서 입력된 값이 있으면 지우고 해야한다.
//
//@Repository
//public class EmpRepository implements IEmpRepository {
//
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//	
//	RowMapper<EmpVO> empMapper = new RowMapper<EmpVO>() {
//
//		@Override
//		public EmpVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//			EmpVO emp = new EmpVO();
//			emp.setEmployeeId(rs.getInt("employee_id"));
//			emp.setFirstName(rs.getString("first_name"));
//			emp.setLastName(rs.getString("last_name"));
//			emp.setEmail(rs.getString("email"));
//			emp.setPhoneNumber(rs.getString("phone_number"));
//			emp.setHireDate(rs.getDate("hire_date"));
//			emp.setJobId(rs.getString("job_id"));
//			emp.setSalary(rs.getDouble("salary"));
//			emp.setCommissionPct(rs.getDouble("commission_pct"));
//			emp.setManagerId(rs.getInt("manager_id"));
//			emp.setDepartmentId(rs.getInt("department_id"));
//			emp.setEmpPic(rs.getBytes("emp_pic"));
//			return emp;
//		}
//		
//	};
//	
//	public EmpVO selectEmployee(int empId) {
//		String sql = "SELECT * FROM employees WHERE employee_id=?";
//		return jdbcTemplate.queryForObject(sql, empMapper, empId);
//	}
//	
//	public List<EmpVO> selectAllEmployees(){
//		String sql = "SELECT * FROM employees";
//		return jdbcTemplate.query(sql, empMapper);
//	}
//	
//	public int getEmpCount() {
//		String sql = "SELECT COUNT(*) FROM employees";
//		return jdbcTemplate.queryForObject(sql, Integer.class);
//	}
//	
//	public int getEmpCount(int deptId) {
//		String sql = "SELECT COUNT(*) FROM employees WHERE department_id=?";
//		return jdbcTemplate.queryForObject(sql, Integer.class, deptId);
//	}
//	
//	public void insertEmp(EmpVO emp) {
//		String sql = "INSERT INTO employees "
//				+ "values(?,?,?,?,?,sysdate,?,?,?,?,?,?,?)";
//		jdbcTemplate.update(sql, emp.getEmployeeId(),  
//				emp.getFirstName(), emp.getLastName(), emp.getEmail(),
//				emp.getPhoneNumber(), emp.getJobId(), emp.getSalary(), 
//				emp.getCommissionPct(), emp.getManagerId(), emp.getDepartmentId(),
//				emp.getEmpPic(), emp.getFileSize()
//				);
//	}
//	
//	public void updateEmp(EmpVO emp) {
//		String sql = "UPDATE employees SET first_name=?, last_name=?, "
//				+ "email=?, phone_number=?, hire_date=?, job_id=?, "
//				+ "salary=?, commission_pct=?, manager_id=?, "
//				+ "department_id=? WHERE employee_id=?";
//		jdbcTemplate.update(sql, emp.getFirstName(), emp.getLastName(), emp.getEmail(),
//				emp.getPhoneNumber(), emp.getHireDate(), emp.getJobId(), emp.getSalary(), 
//				emp.getCommissionPct(), emp.getManagerId(), emp.getDepartmentId(),
//				emp.getEmployeeId());
//	}
//	
//	public void deleteEmp(int empId) {
//		String sql = "DELETE FROM employees WHERE employee_id=?";
//		jdbcTemplate.update(sql, empId);
//	}
//	
//	public void deleteJobHistory(int empId) {
//		String sql = "DELETE FROM job_history WHERE employee_id=?";
//		jdbcTemplate.update(sql, empId);
//	}
//	
//	public List<Map<String, Object>> getAllDeptId() {
//		String sql = "SELECT department_id AS departmentId,"
//				+ "department_name AS departmentName FROM departments";
//		return jdbcTemplate.queryForList(sql);
//	}
//	
//	public List<Map<String, Object>> getAllJobId() {
//		String sql = "SELECT job_id AS jobId, "
//				+ "job_title AS jobTitle FROM jobs";
//		return jdbcTemplate.queryForList(sql);
//	}
//	
//	public List<Map<String, Object>> getAllManagerId() {
//		String sql = "SELECT employee_id AS managerId, "
//				+ "first_name||' '||last_name AS managerName "
//				+ "FROM employees "
//				+ "WHERE employee_id in "
//				+ "(SELECT DISTINCT manager_id FROM employees)";
//		return jdbcTemplate.queryForList(sql);
//	}
//	
//
//	public List<EmpVO> getEmpList() {
//	String sql = "select * from employees";
//	return jdbcTemplate.query(sql, empMapper);
//	}
//	
//
//	public EmpVO getEmpInfo(int empId) {
//	String sql = "SELECT employee_id, first_name, last_name, email, phone_number, "
//			+ "hire_date, e.job_id, job_title, salary, commission_pct, e.manager_id, "
//			+ "manager_name, e.department_id, department_name, emp_pic, file_size "
//			+ "FROM employees e "
//			+ "LEFT JOIN jobs j "
//			+ "ON e.job_id=j.job_id "
//			+ "LEFT JOIN departments d "
//			+ "ON e.department_id=d.department_id "
//			+ "LEFT JOIN "
//			// employee_id manager_id -> 가운데에 as가 생략되면서 employee_id를 manager_id로 하겠다는 의미이다.
//			// last_name manager_name도 동일하게 as 생략하면서 last_name을 manager_name으로 하겠다는 의미
//			+ "(SELECT employee_id manager_id, first_name||' '||last_name manager_name "
//			+ "FROM employees "
//			+ "WHERE employee_id IN (SELECT DISTINCT manager_id FROM employees)) m "
//			+ "ON e.manager_id=m.manager_id "
//			+ "WHERE employee_id=?";
//	return jdbcTemplate.queryForObject(sql, new RowMapper<EmpDetailVO>() {
//		@Override
//		public EmpDetailVO mapRow(ResultSet rs, int rowNum) throws SQLException {
//			EmpDetailVO emp = new EmpDetailVO();
//			emp.setFileSize(rs.getLong("file_size"));
//			emp.setEmployeeId(rs.getInt("employee_id"));
//			emp.setFirstName(rs.getString("first_name"));
//			emp.setLastName(rs.getString("last_name"));
//			emp.setEmail(rs.getString("email"));
//			emp.setPhoneNumber(rs.getString("phone_number"));
//			emp.setHireDate(rs.getDate("hire_date"));
//			emp.setJobId(rs.getString("job_id"));
//			emp.setSalary(rs.getDouble("salary"));
//			emp.setCommissionPct(rs.getDouble("commission_pct"));
//			emp.setManagerId(rs.getInt("manager_id"));
//			emp.setDepartmentId(rs.getInt("department_id"));
//			emp.setJobTitle(rs.getString("job_title"));
//			emp.setManagerName(rs.getString("manager_name"));
//			emp.setDepartmentName(rs.getString("department_name"));
//			emp.setEmpPic(rs.getBytes("emp_pic"));
//			return emp;
//			}		
//		}, empId);
//	}
//
//	//public EmpVO getDeptList(int deptId) {
//	//	String sql = "SELECT * FROM employees WHERE department_id=?";
//	//}
//	
//	public List<EmpVO> getEmpList(int deptId) {
//		String sql = "SELECT * FROM employees WHERE department_id=?";
//		return jdbcTemplate.query(sql, empMapper, deptId);
//		}
//	
//	public List<EmpVO> getEmpList(String name) {
//		String sql = "SELECT * FROM employees WHERE first_name LIKE ? OR last_name LIKE ?";
//		//return jdbcTemplate.query(sql, empMapper, "%"+name+"%", "%"+name+"%");
//		//Repository에서 %를 붙였을 경우 Controller에서 % 붙일 필요 없다
//		return jdbcTemplate.query(sql, empMapper, name, name);
//		}
//	
//	public List<EmpVO> getEmpIdList(int empId) {
//		String sql = "SELECT * FROM employees WHERE employee_id LIKE ?";
//		return jdbcTemplate.query(sql, empMapper, "%"+empId+"%");
//		}
//	
//	public void updateManagers(int empId) {
//		String sql = "UPDATE (SELECT * FROM employees WHERE manager_id=?) SET manager_id=null";
//		jdbcTemplate.update(sql, empId);
//		sql = "UPDATE (SELECT * FROM departments WHERE manger_id=?) SET manager_id=null";
//		jdbcTemplate.update(sql, empId);
//	}
//	//employees와 departments의 manager_id를 모두 삭제하는 sql
//	//deleteEmp에서 JobHistory 삭제하고 updateManager 하고 deleteEmp실행되게 해야한다 ->EmpService에서 확인
//	
//	public int getManagerCount(int employeeId) {
//		String sql = "SELECT COUNT(*) FROM employees WHERE manager_id=?";
//		return jdbcTemplate.queryForObject(sql, Integer.class, employeeId);
//	}
//	
//	public int getDeptCount(int employeeId) {
//		String sql = "SELECT COUNT(*) FROM departments WHERE manager_id=?";
//		return jdbcTemplate.queryForObject(sql, Integer.class, employeeId);
//	}
//
//	@Override
//	public int getUpdateCount(int employeeId) {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//	
//	/*
//	 * public List<JobVO> getAllJobId2() { String sql =
//	 * "SELECT job_id AS jobId, job_title AS jobTitle " + "FROM jobs"; return
//	 * jdbcTemplate.query(sql, new RowMapper<JobVO>() { public JobVO
//	 * mapRow(ResultSet rs, int rowNum) throws SQLException { JobVO job = new
//	 * JobVO(); job.setJobId(rs.getString(1)); job.setJobTitle(rs.getString(2));
//	 * return job; } }); }
//	 */
//	
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
