<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete</title>
</head>
<body>

<h1>정말 삭제하시겠습니까?</h1>
<form action="delete" method=post>
<input type=hidden name=empId value="">
<input type=submit value="삭제">
<input type="button" value="취소" onclick="history.back(-1);">
</form>
</body>
</html>