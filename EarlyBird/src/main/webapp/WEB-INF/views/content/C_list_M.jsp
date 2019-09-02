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
.container {
	width: 1670px;
	display: inline-flex;
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
	function menu_btn(brd_num) {
		var brd_id =
<%=request.getParameter("brd_id")%>
	;
		$.ajax({
			type : "post",
			url : "${contextPath}/content/menu_btn",
			data : {
				"brd_id" : brd_id,
				"brd_num" : brd_num
			},
			success : function(data) {
				alert(data);
				var b = $(data).find("#menu_btn");
				$("#menu_btn").html(b);
			}
		});
	}

	function C_recommand(cnt_id) {
		var brd_id=<%=request.getParameter("brd_id")%>;
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
			},error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		})
	}
	
	function C_view_M(cnt_id,cnt_connectlink){
		var brd_id=<%=request.getParameter("brd_id")%>;
		$.ajax({
			async:false,
			type:"post",
			url:"${contextPath}/content/C_view",
			data:{"cnt_id":cnt_id,
				  "brd_id":brd_id},
			success:function(data){
				var a = $(data).find("#menu_btn");
				$("#menu_btn").html(a);
				window.open(cnt_connectlink, "connectlink", "width=1200,height=600");
			},error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
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
		<div align="center" style="paddin-left: 35px">
			<div class="btn-group" role="group">
				<button type="button" class="btn btn-default dropdown-toggle"
					style="align: center;" role="group" data-toggle="dropdown"
					aria-expanded="false">
					<c:choose>
						<c:when test="${param.brd_id eq 1 || brd_id eq 1}">게임</c:when>
						<c:when test="${param.brd_id eq 2 || brd_id eq 2}">영화</c:when>
						<c:when test="${param.brd_id eq 3 || brd_id eq 3}">드라마</c:when>
						<c:when test="${param.brd_id eq 4 || brd_id eq 4}">음악/공연</c:when>
						<c:when test="${param.brd_id eq 5 || brd_id eq 5}">IT</c:when>
					</c:choose>
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu" role="menu">
					<c:choose>
						<c:when test="${param.brd_id eq 1 || brd_id eq 1}">
							<li><a href="" id="menu_one" onclick="menu_btn(1)">이벤트</a></li>
							<li><a href="" id="menu_two" onclick="menu_btn(2)">업데이트</a></li>
							<li><a href="" id="menu_thr" onclick="menu_btn(3)">사전등록</a></li>
							<li><a href="" id="menu_for" onclick="menu_btn(4)">출시</a></li>
						</c:when>
						<c:when test="${param.brd_id eq 2 || brd_id eq 2 }">
							<li><a href="" id="menu_one" onclick="menu_btn(1)">개봉예정</a></li>
							<li><a href="" id="menu_two" onclick="menu_btn(2)">티저영상</a></li>
							<li><a href="" id="menu_thr" onclick="menu_btn(3)">ex1</a></li>
							<li><a href="" id="menu_for" onclick="menu_btn(4)">ex2</a></li>
						</c:when>
						<c:when test="${param.brd_id eq 3 || brd_id eq 3 }">
							<li><a href="" id="menu_one" onclick="menu_btn(1)">개봉예정</a></li>
							<li><a href="" id="menu_two" onclick="menu_btn(2)">티저영상</a></li>
							<li><a href="" id="menu_thr" onclick="menu_btn(3)">ex1</a></li>
							<li><a href="" id="menu_for" onclick="menu_btn(4)">ex2</a></li>
						</c:when>
						<c:when test="${param.brd_id eq 4 || brd_id eq 4 }">
							<li><a href="" id="menu_one" onclick="menu_btn(1)">음악소식</a></li>
							<li><a href="" id="menu_two" onclick="menu_btn(2)">공연소식</a></li>
							<li><a href="" id="menu_thr" onclick="menu_btn(3)">음악티저</a></li>
							<li><a href="" id="menu_for" onclick="menu_btn(4)">공연ex1</a></li>
						</c:when>
						<c:when test="${param.brd_id eq 5 || brd_id eq 5}">
							<li><a href="" id="menu_one" onclick="menu_btn(1)">IT모바일</a></li>
							<li><a href="" id="menu_two" onclick="menu_btn(2)">IT자동차</a></li>
							<li><a href="" id="menu_thr" onclick="menu_btn(3)">ITex1</a></li>
							<li><a href="" id="menu_for" onclick="menu_btn(4)">ITex2</a></li>
						</c:when>
					</c:choose>
				</ul>
			</div>
		</div>
		<div class="container col-md-12">
			<div class="row" id="menu_btn">
				<c:forEach items="${list}" var="list">
					<div class="row" style="margin-left: 0px;">
						<div class="thumbnail"
							style="margin-top: 25px; margin-bottom: 30px; margin-left: 46px;">
							<a style="cursor: pointer"
								onclick="C_view_M('${list.cnt_id }','${list.cnt_connectLink }')"><img
								src="${list.cnt_thumbnail }" alt="..."
								style="width: 100%; background: url('${contextPath}/resources/uploadimage/no_image.jpg') no-repeat center center; background-size: cover;"></a>
							<div class="caption">
								<h3>${list.cnt_title }</h3>
							</div>
							<input type="hidden" value="${list.cnt_id }" id="cnt_id">
							<input type="hidden" value="${list.cnt_connectLink }"
								id="cnt_connectlink">
						</div>

						<h4 style="transform: translate(-70px, 0);">
							<i class="ni ni-active-40 text-blue"></i>${list.cnt_hit }</h4>
						<h4 style="transform: translate(-65px, 0);">
							<a style="cursor: pointer;" onclick="C_recommand(${list.cnt_id})">
								<i class="ni ni-favourite-28 text-red" id="C_recommand"></i>${list.cnt_like }</a>
						</h4>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>