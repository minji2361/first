<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- javascript pdf 293쪽부터~ -->

<!DOCTYPE html>
<html>
<head>
<style>
#loadingImage{
position : absolute;
left : 50%;
top : 50%;
background : #ffffff;
}
</style>
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
				<button id="check" type="button">중복검사</button>
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
			<th><input type=submit value="회원가입" id="submit" />
			<input type=button value="취소" /><th>
		</tr>
	</table>
	<div id="loadingImage">
		<img src="/myapp/resources/images/loading.gif" />
	</div>
</form>

<script>
	$(function(){
		$("#loadingImage").hide();
		var ck = false;
		$("#check").on("click", function(){
			if($("#userid").val()){
				$.ajax({
					url : "/myapp/users/check",
					type : "post",
					data : {userid : $("#userid").val()},
					dataType : "text",
					success : function(result){
						if(result){
							//id 중복 안됨 => true
							alert("사용 가능한 아이디입니다.")
							$("#check").remove();
							$("#userid").attr("readonly", true);
							ck = !ck;
						}else{
							//id 중복
							alert("아이디가 중복됩니다.");
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
		$(document).ajaxStart(function(){
			$("#loadingImage").show();
			setTimeout(function(){}, 2000);
		})
		$(document).ajaxStop(function(){
			$("#loadingImage").hide();
		})
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