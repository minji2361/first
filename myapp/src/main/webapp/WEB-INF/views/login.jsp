<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>

<h2>로그인</h2>
${message}
<form action="/myapp/login.do" method="post">
	<table border=1>
		<tr>
			<th>아이디</th>
			<td><input type=text name="id" /></td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type=text name="pw" /></td>
		</tr>
		<tr> 
			<td><input type=submit value="로그인" /></td>
			<td><a href="/myapp/users/signin"><input type=button value="회원가입" /></a></td>
		</tr>
	</table>
</form>

</body>
</html>