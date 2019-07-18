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
	<!-- end side bar -->
	<div class="main-content">
		<!-- main_header -->
		<!-- end main header -->
		<!-- Header -->
		<!-- end Header -->
		<!-- body -->
		<form>
				<div class="form-group">
					<label for="exampleInputEmail1">이메일 주소</label> <input type="email"
						class="form-control" id="exampleInputEmail1"
						placeholder="이메일을 입력하세요">
				</div>
				<div class="form-group">
					<label for="exampleInputPassword1">암호</label> <input
						type="password" class="form-control" id="exampleInputPassword1"
						placeholder="암호">
				</div>
				<div align="center">
					<button type="submit" class="btn btn-default">로그인</button>
				</div>
		</form>
		<!-- end body -->
	</div>
	<!--   Core   -->
	<!--  end Core -->
</body>
</html>