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
#columns {
	column-width: 300px;
	column-gap: 15px;
}

#columns figure {
	display: inline-block;
	border: 1px solid rgba(0, 0, 0, 0.2);
	margin: 0;
	margin-bottom: 15px;
	padding: 10px;
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);;
}

#columns figure img {
	width: 100%;
}

#columns figure figcaption {
	border-top: 1px solid rgba(0, 0, 0, 0.2);
	padding: 10px;
	margin-top: 11px;
}
</style>
<script>
	function C_recommand(cnt_id) {
		$.ajax({
			async : true,
			type : "post",
			url : "${contextPath}/content/C_recommand",
			data : {
				"cnt_id" : cnt_id
			},
			success : function(data) {
				var a = $(data).find("#main_menu_btn");
				$("#main_menu_btn").html(a);
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);

			}
		})
	}

	function C_view(cnt_id, cnt_connectlink) {
		$.ajax({
			async : false,
			type : "post",
			url : "${contextPath}/content/C_view",
			data : {
				"cnt_id" : cnt_id
			},
			success : function(data) {
				var a = $(data).find("#main_menu_btn");
				$("#main_menu_btn").html(a);
				window.open(cnt_connectlink, "connectlink",
						"width=1200,height=600");
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
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
		<div id="columns">
			<div id="menu_btn">
				<c:forEach items="${list}" var="list">
					<c:if test="${100 < list.brd_id and list.brd_id < 200 }">
						<figure style="margin: 15px; width: 300px;">
							<a style="cursor: pointer"
								onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')"><img
								src="${list.cnt_thumbnail }"></a>
							<figcaption>${list.cnt_title }<div class="row"
									style="align: right; padding-left: 240px; padding-top: 10px;">
									<h4>
										<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
									<h4>
										<a style="cursor: pointer;"
											onclick="C_recommand(${list.cnt_id})"> <i
											class="ni ni-favourite-28 text-red" id="C_recommand"></i>${list.cnt_like }</a>
									</h4>
								</div>
							</figcaption>
						</figure>
					</c:if>
				</c:forEach>
				<c:forEach items="${list}" var="list">
					<c:if test="${200 < list.brd_id and list.brd_id < 300 }">
						<div id="columns">
							<figure style="margin: 15px; width: 300px;">
								<a style="cursor: pointer"
									onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')"><img
									src="${list.cnt_thumbnail }"></a>
								<figcaption>${list.cnt_title }<div class="row"
										style="align: right; padding-left: 240px; padding-top: 10px;">
										<h4>
											<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
										<h4>
											<a style="cursor: pointer;"
												onclick="C_recommand(${list.cnt_id})"> <i
												class="ni ni-favourite-28 text-red" id="C_recommand"></i>${list.cnt_like }</a>
										</h4>
									</div>
								</figcaption>
							</figure>
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${list}" var="list">
					<c:if test="${300 < list.brd_id and list.brd_id < 400 }">
						<div id="columns">
							<figure style="margin: 15px; width: 300px;">
								<a style="cursor: pointer"
									onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')"><img
									src="${list.cnt_thumbnail }"></a>
								<figcaption>${list.cnt_title }<div class="row"
										style="align: right; padding-left: 240px; padding-top: 10px;">
										<h4>
											<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
										<h4>
											<a style="cursor: pointer;"
												onclick="C_recommand(${list.cnt_id})"> <i
												class="ni ni-favourite-28 text-red" id="C_recommand"></i>${list.cnt_like }</a>
										</h4>
									</div>
								</figcaption>
							</figure>
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${list}" var="list">
					<c:if test="${400 < list.brd_id and list.brd_id < 500 }">
						<div id="columns">
							<figure style="margin: 15px; width: 300px;">
								<a style="cursor: pointer"
									onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')"><img
									src="${list.cnt_thumbnail }"></a>
								<figcaption>${list.cnt_title }<div class="row"
										style="align: right; padding-left: 240px; padding-top: 10px;">
										<h4>
											<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
										<h4>
											<a style="cursor: pointer;"
												onclick="C_recommand(${list.cnt_id})"> <i
												class="ni ni-favourite-28 text-red" id="C_recommand"></i>${list.cnt_like }</a>
										</h4>
									</div>
								</figcaption>
							</figure>
						</div>
					</c:if>
				</c:forEach>
				<c:forEach items="${list}" var="list">
					<c:if test="${500 < list.brd_id and list.brd_id < 600 }">
						<div id="columns">
							<figure style="margin: 15px; width: 300px;">
								<a style="cursor: pointer"
									onclick="C_view('${list.cnt_id }','${list.cnt_connectLink }')"><img
									src="${list.cnt_thumbnail }"></a>
								<figcaption>${list.cnt_title }<div class="row"
										style="align: right; padding-left: 240px; padding-top: 10px;">
										<h4>
											<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
										<h4>
											<a style="cursor: pointer;"
												onclick="C_recommand(${list.cnt_id})"> <i
												class="ni ni-favourite-28 text-red" id="C_recommand"></i>${list.cnt_like }</a>
										</h4>
									</div>
								</figcaption>
							</figure>
						</div>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<!-- end body -->
	</div>
</body>
</html>