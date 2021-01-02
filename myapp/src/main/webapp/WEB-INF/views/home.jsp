<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h3>${message}</h3>
<br><br>
<a href="emp/count"><button>1. 사원의 수</button></a>
<form action="emp/count">
출력하려는 부서 번호를 입력하세요 : <input type="text" name="deptId" />  
<!-- EmpController에 value="deptId"와 동일한 name으로 --> 
<input type="submit" value="검색" />
</form><br>
<form action="emp/deptlist">
목록을 출력하려는 부서 번호를 출력하세요: <input type="text" name="deptId">
<input type="submit" value="검색">
</form>
<br>
<form action="emp/namelist">
2. 이름 검색 : <input type="text" name="name" /> &nbsp;&nbsp;
<input type="submit" value="검색" />
</form>
<br>
<form action="emp/empidlist">
출력하려는 사원의 아이디를 입력하세요: <input type="text" name="empId" />
<input type="submit" value="검색" />
</form>
<br>
<a href="emp/list"><button>3. 사원 목록</button></a><br><br>
<form action="logout.do" method="post">
<input type=submit value="4.로그아웃" />
</form><br>
</body>
</html>






