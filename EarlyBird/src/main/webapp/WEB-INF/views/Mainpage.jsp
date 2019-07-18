<!--

=========================================================
* Argon Dashboard - v1.1.0
=========================================================

* Product Page: https://www.creative-tim.com/product/argon-dashboard
* Copyright 2019 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://github.com/creativetimofficial/argon-dashboard/blob/master/LICENSE.md)

* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. -->
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
<%@ include file="include/head.jsp"%>
<!-- end head -->

<body class="">
	<!-- side bar -->
	<%@ include file="include/left_navbar.jsp"%>
	<!-- end side bar -->
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="include/main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<div class="col-md-4 col-md-offset-4">
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-expanded="false">
				게임 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#"></a></li>
				<li><a href="#"></a></li>
				<li><a href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>
		</div>
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-expanded="false">
				영화 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#"></a></li>
				<li><a href="#"></a></li>
				<li><a href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>
		</div>
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-expanded="false">
				드라마 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#"></a></li>
				<li><a href="#"></a></li>
				<li><a href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>
		</div>
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-expanded="false">
				음악/공연 <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#"></a></li>
				<li><a href="#"></a></li>
				<li><a href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>
		</div>
		<div class="btn-group">
			<button type="button" class="btn btn-default dropdown-toggle"
				data-toggle="dropdown" aria-expanded="false">
				IT <span class="caret"></span>
			</button>
			<ul class="dropdown-menu" role="menu">
				<li><a href="#"></a></li>
				<li><a href="#"></a></li>
				<li><a href="#">Something else here</a></li>
				<li class="divider"></li>
				<li><a href="#">Separated link</a></li>
			</ul>
		</div>
		</div>
		<!-- end body -->
	</div>
	<!--   Core   -->
	<%@ include file="include/core.jsp"%>
	<!--  end Core -->
</body>
</html>