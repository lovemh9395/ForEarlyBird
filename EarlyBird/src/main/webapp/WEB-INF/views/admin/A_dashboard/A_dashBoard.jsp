<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!-- head -->
<%@ include file="../../include/head.jsp"%>
<!-- end head -->
<!--   Core   -->
<%@ include file="../../include/core.jsp"%>
<!--  end Core -->
<body class="">
	<%@ include file="../include/A_left_navbar.jsp"%>
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="../include/A_main_navbar.jsp"%>
		<!-- end main header -->
		<!-- body -->
		<%@include file="A_carddashboard.jsp"%>
		<%@include file="A_todayhit_dashboard.jsp"%>
		<%@include file="A_dashboardtotalorders.jsp"%>
		<%@include file="A_pagevisits.jsp"%>
		<%@include file="A_gender_dashboard.jsp"%>
		<%@include file="../../include/main_footer.jsp" %>
		<!-- end of body -->
		<%@include file="../include/A_footer.jsp"%>