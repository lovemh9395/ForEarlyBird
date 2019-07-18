<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!-- head -->
<%@ include file="../include/head.jsp"%>
<!-- end head -->
<body class="">
	<!-- side bar -->
	<%@ include file="../include/left_navbar.jsp"%>
	<!-- end side bar -->
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="../include/main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="../include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<form>
			<div class="col-md-3" align="center">
				<label for="exampleInputPassword1"></label>
				<div class="form-group">
					<input type="email" class="form-control" id="mem_userid"
						placeholder="아이디를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_password"
						placeholder="암호를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_password2"
						placeholder="암호를 재입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_username"
						placeholder="이름을 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_phone"
						placeholder="전화번호를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="생일을 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="성별을 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="우편번호를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="주소를 입력하세요">
				</div>
				<div class="form-group">
					<input type="password" class="form-control" id="mem_birthday"
						placeholder="상세주소를 입력하세요">
				</div>
				<div class="form-group">
					<label for="exampleInputFile">파일 업로드</label> <input type="file"
						id="exampleInputFile">
					<p class="help-block">여기에 블록레벨 도움말 예제</p>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox"> 이메일 수신여부
					</label>
				</div>
				<div class="checkbox">
					<label> <input type="checkbox"> 정보 공개 여부
					</label>
					<br>
					<button type="submit" class="btn btn-default">제출</button>
				</div>
			</div>
		</form>
		<!-- end body -->
	</div>
	<!--   Core   -->
	<%@ include file="../include/core.jsp"%>
	<!--  end Core -->
</body>
</html>