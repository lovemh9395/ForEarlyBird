<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<nav class="navbar navbar-top navbar-expand-md navbar-dark"
	id="navbar-main">
	<div class="container-fluid">
		<!-- Brand -->
		<a class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block"
			href="./index.html">ForEarlyBird</a>
		<!-- Form -->
		<form
			class="navbar-search navbar-search-dark form-inline mr-3 d-none d-md-flex ml-lg-auto">
			<div class="form-group mb-0">
				<div class="input-group input-group-alternative">
					<div class="input-group-prepend">
						<span class="input-group-text"><i class="fas fa-search"></i></span>
					</div>
					<input class="form-control" placeholder="Search" type="text">
				</div>
			</div>
		</form>
		<!-- User -->
		&nbsp;&nbsp;<button type="button" data-toggle="modal" data-target="#myModal">모달 창 열기</button>

&nbsp;&nbsp;<a data-toggle="modal" href="#myModal">모달 창 열기</a>


  <!-- Modal -->

  <div class="modal fade" id="myModal" role="dialog"> <!-- 사용자 지정 부분① : id명 -->

    <div class="modal-dialog">

    

      <!-- Modal content-->

      <div class="modal-content">

        <div class="modal-header">

          <button type="button" class="close" data-dismiss="modal">×</button>

          <h4 class="modal-title">모달 창 타이틀</h4> <!-- 사용자 지정 부분② : 타이틀 -->

        </div>

        <div class="modal-body">

          <p>여기에 필요한 텍스트 메시지 넣기</p> <!-- 사용자 지정 부분③ : 텍스트 메시지 -->

        </div>

        <div class="modal-footer">

          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>

        </div>

      </div>

    </div>

  </div>



		<button type="button" class="btn btn-info"
			onclick="window.open('${contextPath}/member/M_login','window_memberok','width=500,height=890,location=yes,status=no,scrollbars=yes');">로그인</button>
		<a href="${contextPath}/member/M_make"><button
				class="btn btn-info" type="button">회원가입</button></a>
	</div>
</nav>