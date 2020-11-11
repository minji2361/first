<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- javascript pdf 293쪽부터~ -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>회원가입</title>
</head>
<body>

<h1>회원 가입</h1>

<form action="/myapp/users/insert" method="post" enctype="multipart/form-data">
	<table border=1>
		<tr>
			<th>아이디</th>
			<td>
				<input type=text name="userid" id="userid"/>
				<button id="check">중복 검사</button>
			</td>
		</tr>
		<tr>
			<th>비밀번호</th>
			<td><input type=text name="password" /></td>
		</tr>
		<tr>
			<th>이름</th>
			<td><input type=text name="name" /></td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td><input type=text name="tel" /></td>
			<!-- pattern="^[0][1][0-9](-|\s)\d{4}$" 전화번호에 패턴 넣고 싶을 때 사용 -->
		</tr>
		<tr>
			<th>프로필 이미지</th>
			<td><input type=file name="file" /></td>
		</tr>
		<tr>
			<td><input type=submit value="회원가입" id="submit" /><td>
			<td><input type=button value="취소" /></td>
		</tr>
	</table>
</form>

<script>
	$(function(){
		var ck = false;
		$("#check").on("click", function(){
			if($("#userid").val()){
				$.ajax({
					url : "check",
					type : "post",
					data : $("#userid").val(),
					dataType : "text",
					success : function(check){
						if(check){
							//id 중복 안됨 => true
							alert("사용할 수 있는 아이디입니다.")
							$("#check").remove();
							$("#userid").attr("readonly", true);
							ck = !ck;
						}else{
							//id 중복
							alert("아이디가 중복이 됩니다.");
						}
						return false;
					}, 
					error : function(){
						alert("ajax에 문제가 있습니다.");
						return false;
					}
				});
			}else{
				//입력 안했을 시
				alert("아이디를 입력해 주세요.");
				return false;
			}
		});
		$("#submit").on("click", function(){
			if(ck){
				
			}else{
				alert("중복검사가 먼저 진행되어야 합니다.");
				return false;
			}
		});
	});
</script>

</body>
</html>