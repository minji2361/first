<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- SELECT DISTINCT manager_id FROM employees; 중복 제거 sql -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee View</title>
</head>
<body>
<h1>사원 정보</h1>
<table>
	<tr>
		<td>사원번호</td>
		<td>${emp.employeeId}</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${emp.firstName}</td>
	</tr>
	<tr>
		<td>성</td>
		<td>${emp.lastName}</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td>${emp.email}</td>
	</tr>
	<tr>
		<td>연락처</td>
		<td>${emp.phoneNumber}</td>
	</tr>
	<tr>
		<td>입사일</td>
		<td>${emp.hireDate}</td>
	</tr>
	<tr>
		<td>직무</td>
		<td>${emp.jobTitle}(${emp.jobId})</td>
	</tr>
	<tr>
		<td>금여</td>
		<td>${emp.salary}</td>
	</tr>
	<tr>
		<td>보너스율</td>
		<td>${emp.commissionPct}</td>
	</tr>
	<tr>
		<td>메니저</td>
		<td>${emp.managerName}(${emp.managerId})</td>	
	</tr>
	<tr>
		<td>부서</td>	
		<td>${emp.departmentName}(${emp.departmentId})</td>	
	</tr>
</table>
	<a href="update/${emp.employeeId}">정보 수정</a>&nbsp;&nbsp;
	<a href="delete?empId=${emp.employeeId}">정보 삭제</a>
</body>
</html>
















