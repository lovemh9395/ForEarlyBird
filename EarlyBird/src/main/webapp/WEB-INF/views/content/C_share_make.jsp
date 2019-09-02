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
	src="${contextPath }/resources/smarteditor/js/HuskyEZCreator.js"></script>
<style>
.nse_content {
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
		<div>
			<input type="text" class="form-control" placeholder="제목을 입력해주세요." />
			<table style="width: 1670px;">
				<tr>
					<td><textarea name="popContent" id="popContent"
							class="nse_content"></textarea></td>
				</tr>
			</table>
		</div>
		<script type="text/javascript">
			var oEditors = [];
			

			var sLang = "ko_KR"; // 언어 (ko_KR/ en_US/ ja_JP/ zh_CN/ zh_TW), default = ko_KR
			// 추가 글꼴 목록
			//var aAdditionalFontSet = [["MS UI Gothic", "MS UI Gothic"], ["Comic Sans MS", "Comic Sans MS"],["TEST","TEST"]];
			nhn.husky.EZCreator
					.createInIFrame({
						oAppRef : oEditors,
						elPlaceHolder : "popContent", //textarea ID
						sSkinURI : "${contextPath }/resources/smarteditor/SmartEditor2Skin.html", //skin경로
						htParams : {
							bUseToolbar : true, // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseVerticalResizer : true, // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
							bUseModeChanger : true, // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
							//bSkipXssFilter : true,  // client-side xss filter 무시 여부 (true:사용하지 않음 / 그외:사용)
							//aAdditionalFontList : aAdditionalFontSet,  // 추가 글꼴 목록
							fOnBeforeUnload : function() {
								//alert("완료!");
							},
							I18N_LOCALE : sLang
						}, //boolean
						fOnAppLoad : function() {
							//예제 코드
							//oEditors.getById["content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
						},
						fCreator : "createSEditor2"
					});
			function pasteHTML(filepath) {

				// var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
				var sHTML = '<span style="color:#FF0000;"><img src="'+filepath+'"></span>';
				oEditors.getById["popContent"].exec("PASTE_HTML", [ sHTML ]);

			}

			function showHTML() {
				var sHTML = oEditors.getById["popContent"].getIR();
				alert(sHTML);
			}

			function submitContents(elClickedObj) {
				oEditors.getById["popContent"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

				// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.

				try {

					var form2 = document.f;
					if (!form2.name.value) {
						alert("작성자 이름을 입력해 주십시오");
						form2.name.focus();
						return;
					}

					if (!form2.subject.value) {
						alert("글제목을 입력해 주십시오.");
						form2.subject.focus();
						return;
					}

					if (document.getElementById("popContent").value == "<p><br></p>") {
						alert("내용을 입력해 주세요.");
						oEditors.getById["popContent"].exec("FOCUS", []);
						return;
					}

					form2.action = "notice_write_ok.php";
					//elClickedObj.form.submit();
					form2.submit();
				} catch (e) {
					alert(e);
				}
			}

			function setDefaultFont() {
				var sDefaultFont = '궁서';
				var nFontSize = 24;
				oEditors.getById["popContent"].setDefaultFont(sDefaultFont,
						nFontSize);
			}

			function writeReset() {
				document.f.reset();
				oEditors.getById["popContent"].exec("SET_IR", [ "" ]);

			}
		</script>
		<!-- end body -->
	</div>
	<!--   Core   -->
	<%@ include file="../include/core.jsp"%>
	<!--  end Core -->
</body>
</html>