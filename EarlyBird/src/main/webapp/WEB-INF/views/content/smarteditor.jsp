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
<script type="text/javascript"
	src="/resources/smarteditor/js/HuskyEZCreator.js" charset="utf-8"></script>
<style>
.smarteditor {
	width: 1650px;
	height: 700px;
}
</style>
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
		<form action="${contextPath }/content/C_share_maker" method="post"
			id="frm">
			<input type="text" id="title" name="title" class="form-control" placeholder="제목을 입력해주세요">
			<textarea name="smarteditor" id="smarteditor" rows="10" cols="100"
				class="smarteditor" style="width: 1650px; height: 700px;"></textarea>
			<input type="button" id="savebutton" value="서버전송" />
		</form>


		<!-- end body -->
	</div>
	<!--   Core   -->
	<script>
		$(function() {
			//전역변수선언
			var editor_object = [];

			nhn.husky.EZCreator.createInIFrame({
				oAppRef : editor_object,
				elPlaceHolder : "smarteditor",
				sSkinURI : "/resources/smarteditor/SmartEditor2Skin.html",
				htParams : {
					// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseToolbar : true,
					// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
					bUseVerticalResizer : true,
					// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
					bUseModeChanger : true,
				}
			});

			//전송버튼 클릭이벤트
			$("#savebutton").click(
					function() {
						//id가 smarteditor인 textarea에 에디터에서 대입
						editor_object.getById["smarteditor"].exec(
								"UPDATE_CONTENTS_FIELD", []);

						// 이부분에 에디터 validation 검증

						//폼 submit
						$("#frm").submit();
					})
		})
	</script>
	<%@ include file="../include/core.jsp"%>
	<!--  end Core -->
</body>
</html>