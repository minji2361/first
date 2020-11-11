<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  

<!-- 수정은 view로 돌려서 수정 완료까지 해오기 -->
<!-- 지금까지 조회(목록), 검색(사원 아이디, 이름, 부서 등), 수정, 추가 까지 -->

<!DOCTYPE html>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Emp ${message}</title>
</head>
<body>
<h1>사원 정보${message eq "insert" ? "입력" : "수정"}</h1>
<c:choose>
<c:when test="${message eq 'insert'}">
<form:form action="./${message}" method="post" modelAttribute="emp" enctype="multipart/form-data">
	<table border=1>
		<tr>
			<th>사원번호</th>
			<td>
				<form:input path="employeeId" />
				<form:errors path="employeeId" />
				<button id="check">중복 검사</button>
			</td>
		</tr>
		<tr>
			<th>이름</th>

			<td>
				<form:input path="firstName" />
				<form:errors path="firstName" />
			</td>
		</tr>
		<tr>
			<th>성</th>
			<td>
				<form:input path="lastName" />
				<form:errors path="lastName" />
			</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>
				<form:input path="email" />
				<form:errors path="email" />
			</td>
		</tr>
		<tr>
			<th>연락처</th>
			<td>
				<form:input path="phoneNumber" />
				<form:errors path="phoneNumber" />
			</td>
		</tr>
		<tr>
			<th>입사일</th>
			<td>
				<form:input path="hireDate" type="date" required="required" />
				<form:errors path="hireDate" />
			</td>
		</tr>
		<tr>
			<th>직무</th>
			<td>
				<form:select path="jobId" >
				<c:forEach var="job" items="${jobList}">
				<option value="${job.jobId}">${job.jobTitle}</option>
				</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr>
			<th>급여</th>
			<td>
				<form:input path="salary" />
				<form:errors path="salary" />
			</td>
		</tr>
		<!-- 보너스율을 (min)최소값 0부터 (max)최대값 0.95로 (step)0.05씩 상승을 표현할 때 아래처럼 표현할 수 있다.-->
		<tr>
			<th>보너스율</th>
			<td>
				<form:input path="commissionPct" type="number" min="0" max="0.95" step="0.05" />
				<form:errors path="commissionPct" />
			</td>
		</tr>
		<tr>
			<th>매니저</th>
			<td>
				<form:select path="managerId">
				<c:forEach var="man" items="${manList}">
				<option value="${man.managerId}">${man.managerName}</option>
				</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr>
			<th>부서</th>
			<td>
				<form:select path="departmentId" >
				<c:forEach var="dept" items="${deptList}">
				<option value="${dept.departmentId}">${dept.departmentName}</option>
				</c:forEach>
				</form:select>
			</td>
		</tr>
		<tr>
			<th>프로필사진(5Mb 이하)</th>
			<td><input type=file name=file></td>
		</tr>
		<tr>
			<!-- html에서는 ""를 붙이든 안 붙이든 상관 없다! -->
			<td><input type=submit value="입력" id="submit"><input type=reset value=취소></td>
		</tr>
	</table>
	</form:form>
</c:when>
<c:otherwise>
	<form action="./${message}" method=post>
	<table border=1>
		<tr>
			<th>Employee_id</th>
			<td><input type=text name=employeeId required value="${emp.employeeId}" ${empty emp ? "" : "readonly"}></td>
		</tr>
		<tr>
			<th>First_name</th>
			<td><input type=text name=firstName value="${emp.firstName}"></td>
		</tr>
		<tr>
			<th>Last_name</th>
			<td><input type=text name=lastName required value="${emp.lastName}"></td>
		</tr>
		<tr>
			<th>Email</th>
			<td><input type=text name=email required value="${emp.email}" ${empty emp ? "" : "readonly"}></td>
		</tr>
		<tr>
			<th>Phone_number</th>
			<td><input type=text name=phoneNumber value="${emp.phoneNumber}"></td>
			</tr>
		<tr>
			<th>Hire_date</th>
			<td><input type=date name=hireDate value="${emp.hireDate}"></td>
		</tr>
		<tr>
			<th>Job_id</th>
			<td>
				<select name=jobId>
				<c:forEach var="job" items="${jobList}">
				<option value="${job.jobId}" ${emp.jobId eq job.jobId ? "selected" : ""}>${job.jobTitle}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Salary</th>
			<td><input type=text name=salary value="${emp.salary}"></td>
		</tr>
		<tr>
			<th>Commission_pct</th>
			<td><input type=number name=commissionPct step=0.05 min=0 max=0.95 value="${emp.commissionPct}"></td>
		</tr>
		<tr>
			<th>Manager_id</th>
			<td>
				<select name=managerId>
				<c:forEach var="man" items="${manList}">
				<option value="${man.managerId}" ${emp.managerId eq man.managerId ? "selected" : "" }>${man.managerName}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>Department_id</th>
			<td>
				<select name=departmentId>
				<c:forEach var="dept" items="${deptList}">
				<option value="${dept.departmentId}" ${emp.departmentId eq dept.departmentId ? "selected" : "" }>${dept.departmentName}</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<th>프로필사진(5Mb 이하)</th>
			<td><input type=file name=file value="${empPic}"></td>
		</tr>
		<tr>
			<th colspan=2><input type=submit value="수정">
			<input type=reset value="취소"></th>
		</tr>
	</table>
	</form>
</c:otherwise>
</c:choose>

<script>
$(function(){
	var ck = false;
	$("#check").on("click", function(){
		if($("#employeeId").val()){
			$.ajax({
				url : "check",
				type : "post",
				data : $("#employeeId").val(),
				dataType : "text",
					//결과 타입
				success : function(check){
					if(check){
						//true 중복 안되었다
						alert("사원번호가 중복되지 않습니다.");
						$("#check").remove();
						$("#employeeId").attr("readonly", true);
						//속성 attribute
						ck = !ck;
					}else{
						//중복이 될 때
						alert("사원번호가 중복이 됩니다.");	
					}
					return false;
				},
				error : function(){
					alert("ajax에 문제가 있습니다.");
					return false;
				}
			});
		}else{
			alert("사원번호를 입력해 주세요.");
			//important!!
			return false;
			//false 안하면 경고창에 확인 누르면 메인페이지로 돌아가 버린다!!!
		}
	});
	//function check(){}
	//함수의 값을 보여줌
	$("#submit").on("click", function(){
		if(ck){
			
		}else{
			alert("중복검사가 먼저 진행되어야 합니다.");
			return false;
		}
	});
	//함수의 코드를 저장
});
</script>

</body>
</html>





























