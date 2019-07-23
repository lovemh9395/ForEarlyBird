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
<body class="">
	<%@ include file="include/admin/A_left_navbar.jsp"%>
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="include/admin/A_main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<div class="nav-wrapper">
			<ul class="nav nav-pills nav-fill flex-column flex-md-row"
				id="tabs-icons-text" role="tablist">
				<li class="nav-item"><a class="nav-link mb-sm-3 mb-md-0 active"
					id="tabs-icons-text-1-tab" data-toggle="tab"
					href="#tabs-icons-text-1" role="tab"
					aria-controls="tabs-icons-text-1" aria-selected="true"><i
						class="ni ni-cloud-upload-96 mr-2"></i>Home</a></li>
				<li class="nav-item"><a class="nav-link mb-sm-3 mb-md-0"
					id="tabs-icons-text-2-tab" data-toggle="tab"
					href="#tabs-icons-text-2" role="tab"
					aria-controls="tabs-icons-text-2" aria-selected="false"><i
						class="ni ni-bell-55 mr-2"></i>Profile</a></li>
				<li class="nav-item"><a class="nav-link mb-sm-3 mb-md-0"
					id="tabs-icons-text-3-tab" data-toggle="tab"
					href="#tabs-icons-text-3" role="tab"
					aria-controls="tabs-icons-text-3" aria-selected="false"><i
						class="ni ni-calendar-grid-58 mr-2"></i>Messages</a></li>
			</ul>
		</div>
		<div class="card shadow">
			<div class="card-body">
				<div class="tab-content" id="myTabContent">
					<div class="tab-pane fade show active" id="tabs-icons-text-1"
						role="tabpanel" aria-labelledby="tabs-icons-text-1-tab"></div>
					<div class="tab-pane fade" id="tabs-icons-text-2" role="tabpanel"
						aria-labelledby="tabs-icons-text-2-tab"></div>
				</div>
			</div>
		</div>
	</div>
	<!-- end of body -->
</body>
<!--   Core   -->
<%@ include file="include/core.jsp"%>
<!--  end Core -->
</html>