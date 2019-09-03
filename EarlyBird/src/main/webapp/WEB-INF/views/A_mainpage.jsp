<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
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
<!DOCTYPE html>
<html lang="en">
<!-- head -->
<%@ include file="include/head.jsp"%>
<!-- end head -->
<%@ include file="include/core.jsp"%>
<body class="">
	<%@ include file="admin/include/A_left_navbar.jsp"%>
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="admin/include/A_main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<%@include file="admin/A_dashboard/A_carddashboard.jsp"%>
		<%@include file="admin/A_dashboard/A_todayhit_dashboard.jsp"%>
		<%@include file="admin/A_dashboard/A_dashboardtotalorders.jsp"%>
		<%@include file="admin/A_dashboard/A_pagevisits.jsp"%>
		<%@include file="admin/A_dashboard/A_gender_dashboard.jsp"%>
		<%@include file="include/main_footer.jsp"%>
		<!-- end of body -->
</body>
<!--   Core   -->
<%@ include file="include/core2.jsp"%>
<!--  end Core -->
</html>