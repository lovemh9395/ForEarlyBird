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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!--   Core   -->
<%@ include file="include/core.jsp"%>
<!--  end Core -->
<!-- head -->
<%@ include file="include/head.jsp"%>
<!-- end head -->
<style>
ul.dropdown-menu {
	text-align: center;
}

.container {
	width: 1670px;
	display: inline-flex;
	flex-direction: row;
	justify-content: space-around;
	margin: 0px;
}

.thumbnail {
	align: center;
	margin: 0px;
	width: 250px;
	background-size: cover;
	background-repeat: no-repeat;
	background-position: center center;
	background-color: gray;
}

.thumbnail.round {
	border-radius: 10%;
}

.thumbnail.circle {
	border-radius: 100%;
}
</style>
<script>
	function C_recommand(cnt_id) {
		$.ajax({
			async : false,
			type : "post",
			url : "${contextPath}/content/C_recommand",
			data : {
				"cnt_id" : cnt_id
			},
			success : function(data) {
				var a = $(data).find("#main_menu_btn");
				$("#main_menu_btn").load(a);

			}
		})
	}

	function C_view(cnt_id, connectLink) {
		$
				.ajax({
					async : false,
					type : "post",
					url : "${contextPath}/content/C_view",
					data : {
						"cnt_id" : cnt_id,
					},
					success : function(data) {
						window
								.open(connectLink, "connectLink",
										"toolbar=no, menubar=no, scrollbars=no, resizable=yes");
					}
				})
	}
</script>
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
		<div class="row" id="main_menu_btn">
			<div class="container col-md-12">
				<!--  -->
				<div class="col-xs-12 col-sm-6 col-md-2">
					<div align="center" style="paddin-left: 15px">
						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default dropdown-toggle"
								style="align: center;" role="group" data-toggle="dropdown"
								aria-expanded="false">
								게임 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="">이벤트</a></li>
								<li><a href="">업데이트</a></li>
								<li><a href="">사전등록</a></li>
								<li><a href="">출시</a></li>
							</ul>
						</div>
					</div>
					<div>
						<c:forEach items="${list}" var="list">
							<c:if test="${100 < list.brd_id and list.brd_id < 200 }">
								<div class="thumbnail" style="margin-top: 25px">
									<div align="center">
										<a
											onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')">
											<img src="${list.cnt_thumbnail }" alt="..."
											style="width: 100%; background: url('${contextPath}/resources/uploadimage/no_image.jpg') no-repeat center center; background-size: cover;">
										</a>
										<div class="caption">
											<h3>${list.cnt_title }</h3>
										</div>
									</div>
								</div>
								<div class="row" style="margin-left: 180px;">
									<h4>
										<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
									<h4>
										<a href="" onclick='C_recommand("${list.cnt_id }");'> <i
											class="ni ni-favourite-28 text-red"></i>${list.cnt_like }</a>
									</h4>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<!--  -->
				<div class="col-sm-6 col-md-2">
					<div align="center" style="paddin-left: 15px">
						<div class="btn-group">
							<button type="button" class="btn btn-default dropdown-toggle"
								role="group" data-toggle="dropdown" aria-expanded="false">
								영화 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="">개봉예정</a></li>
								<li><a href="">티저영상</a></li>
								<li><a href="">ex1</a></li>
								<li><a href="">ex2</a></li>
							</ul>
						</div>
					</div>
					<c:forEach items="${list}" var="list">
						<c:if test="${200 < list.brd_id and list.brd_id < 300 }">
							<div class="thumbnail" style="margin-top: 25px">
								<div align="center">
									<a
										onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')">
										<img src="${list.cnt_thumbnail }" alt="..."
										style="width: 100%; background: url('thumbnail.jpg') no-repeat center center; background-size: cover;">
									</a>
									<div class="caption">
										<h3>${list.cnt_title }</h3>
									</div>
								</div>
							</div>
							<div class="row" style="margin-left: 180px;">
								<h4>
									<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
								<h4>
									<a href="" onclick='C_recommand("${list.cnt_id }");'> <i
										class="ni ni-favourite-28 text-red"></i>${list.cnt_like }</a>
								</h4>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<!--  -->
				<div class="col-sm-6 col-md-2">
					<div align="center" style="paddin-left: 15px">
						<div class="btn-group">
							<button type="button" class="btn btn-default dropdown-toggle"
								role="group" data-toggle="dropdown" aria-expanded="false">
								드라마 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="">개봉예정</a></li>
								<li><a href="">티저영상</a></li>
								<li><a href="">ex1</a></li>
								<li><a href="">ex2</a></li>
							</ul>
						</div>
					</div>
					<c:forEach items="${list}" var="list">
						<c:if test="${300 < list.brd_id and list.brd_id < 400 }">
							<div class="thumbnail" style="margin-top: 25px">
								<div align="center">
									<a
										onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')"><img
										src="${list.cnt_thumbnail }" alt="..."
										style="width: 100%; background: url('thumbnail.jpg') no-repeat center center; background-size: cover;"></a>
									<div class="caption">
										<h3>${list.cnt_title }</h3>
									</div>
								</div>
							</div>
							<div class="row" style="margin-left: 180px;">
								<h4>
									<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
								<h4>
									<a href="" onclick='C_recommand("${list.cnt_id }");'> <i
										class="ni ni-favourite-28 text-red"></i>${list.cnt_like }</a>
								</h4>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<!--  -->
				<div class="col-sm-6 col-md-2">
					<div align="center" style="paddin-left: 15px">
						<div class="btn-group">
							<button type="button" class="btn btn-default dropdown-toggle"
								role="group" data-toggle="dropdown" aria-expanded="false">
								음악/공연 <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="">음악소식</a></li>
								<li><a href="">공연소식</a></li>
								<li><a href="">음악티저</a></li>
								<li><a href="">ex1</a></li>
							</ul>
						</div>
					</div>
					<c:forEach items="${list}" var="list">
						<c:if test="${400 < list.brd_id and list.brd_id < 500 }">
							<div class="thumbnail" style="margin-top: 25px">
								<div align="center">
									<a
										onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')">
										<img src="${list.cnt_thumbnail }" alt="..."
										style="width: 100%; background: url('thumbnail.jpg') no-repeat center center; background-size: cover;">
									</a>
									<div class="caption">
										<h3>${list.cnt_title }</h3>
									</div>
								</div>
							</div>
							<div class="row" style="margin-left: 180px;">
								<h4>
									<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
								<h4>
									<a href="" onclick='C_recommand("${list.cnt_id }");'> <i
										class="ni ni-favourite-28 text-red"></i>${list.cnt_like }</a>
								</h4>
							</div>
						</c:if>
					</c:forEach>
				</div>
				<!--  -->
				<div class="col-sm-6 col-md-2">
					<div align="center" style="paddin-left: 15px">
						<div class="btn-group">
							<button type="button" class="btn btn-default dropdown-toggle"
								role="group" data-toggle="dropdown" aria-expanded="false">
								IT <span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<li><a href="">IT모바일</a></li>
								<li><a href="">IT자동차</a></li>
								<li><a href="">ITex1</a></li>
								<li><a href="">ITex2</a></li>
							</ul>
						</div>
						<c:forEach items="${list}" var="list">
							<c:if test="${500 < list.brd_id and list.brd_id < 600 }">
								<div class="thumbnail" style="margin-top: 25px">
									<div align="center">
										<a
											onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')">
											<img src="${list.cnt_thumbnail }" alt="..."
											style="width: 100%; background: url('thumbnail.jpg') no-repeat center center; background-size: cover;">
										</a>
										<div class="Portfolio">
											<h3>${list.cnt_title }</h3>
										</div>
									</div>
								</div>
								<div class="row" style="margin-left: 180px;">
									<h4>
										<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
									<h4>
										<a href="" onclick='C_recommand("${list.cnt_id }");'> <i
											class="ni ni-favourite-28 text-red"></i>${list.cnt_like }</a>
									</h4>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
		</div>
		<!-- end body -->
	</div>
</body>
</html>