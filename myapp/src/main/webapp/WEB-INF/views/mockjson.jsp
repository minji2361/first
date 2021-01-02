<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mock Data</title>
</head>
<body>
<div class="wrap"></div>
</body>
<script>
//페이지 로드
$(function(){
	//ajax 실행
	$.ajax({
		//json파일 경로
		url : "/myapp/resources/json/MOCK_DATA.json",
		//데이타 타입 json
		dataType : "json",
		success : function(data){
			if(data.length>0){
				//테이블 생성 -> html <table> 테그
				var tb = $("<table />");
				for(var i in data){
					//jquery변수는 앞에 $를 붙여주어야 한다. (규칙!!)
					//반복문으로 data 한 줄씩 변수명에 넣어서 불러오기
					var $id = data[i].id;
					var $first_name = data[i].first_name;
					var $last_name = data[i].last_name;
					var $email = data[i].email;
					//table 태그 안에 tr, td, th 생성 방법
					var row = $("<tr />").append(
						$("<td />").text($id),
						$("<td />").text($first_name),
						$("<td />").text($last_name),
						$("<td />").text($email)
					);
					//데이블과 tr, td, th 붙이기
					tb.append(row);
				}
				//body 안에 생성된 div안에 데이블 붙이기
				$(".wrap").append(tb);
			}
		}
	});
});
</script>
</html>















