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
<%@ include file="../include/core.jsp"%>
<!--  end Core -->
<!-- head -->
<%@ include file="../include/head.jsp"%>
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

.modal-backdrop {
	z-index: -1;
}
</style>
<script>
	function C_share_recommand(cnt_id) {
		var brd_id =
<%=request.getParameter("brd_id")%>
	;
		$.ajax({
			async : true,
			type : "post",
			url : "${contextPath}/content/C_recommand",
			data : {
				"cnt_id" : cnt_id,
				"brd_id" : brd_id
			},
			success : function(data) {
				var a = $(data).find("#menu_btn");
				$("#menu_btn").html(a);
			},
			error : function(request, status, error) {
				alert("code:" + request.status + "\n" + "message:"
						+ request.responseText + "\n" + "error:" + error);
			}
		})
	}

	function C_share_view_M(cnt_id, cnt_connectlink) {
		var brd_id =
<%=request.getParameter("brd_id")%>
	;
		$.ajax({
			async : false,
			type : "post",
			url : "${contextPath}/content/C_view",
			data : {
				"cnt_id" : cnt_id,
				"brd_id" : brd_id
			},
			success : function(data) {
				var a = $(data).find("#menu_btn");
				$("#menu_btn").html(a);
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
		<br>
		<button type="button" class="btn btn-block btn-info" id="C_share_M"
			data-toggle="modal" data-target="#modal_share">글 쓰기</button>
		<br>
		<div id="columns">
			<c:forEach items="${list}" var="list">
				<figure>
					<a style="cursor: pointer"
						onclick="C_share_view_M('${list.cnt_id }','${list.cnt_connectLink }')"><img
						src="${contextPath }/shareimage/${list.cnt_thumbnail }"></a>
					<figcaption>${list.cnt_title }<div class="row"
							style="align: right; padding-left: 240px; padding-top: 10px;">
							<h4>
								<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
							<h4>
								<a style="cursor: pointer;"
									onclick="C_share_recommand(${list.cnt_id})"> <i
									class="ni ni-favourite-28 text-red" id="C_recommand"></i>${list.cnt_like }</a>
							</h4>
						</div>
					</figcaption>
				</figure>
			</c:forEach>
		</div>
		<!-- end body -->
	</div>
</body>
<%@ include file="C_share_make.jsp"%>
</html>