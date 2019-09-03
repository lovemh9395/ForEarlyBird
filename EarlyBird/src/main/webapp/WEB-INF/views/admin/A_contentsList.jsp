<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="include/A_header.jsp"%>

<!-- body -->
<div class="container">
	<div>
		<div class="row">
			<div class="col-4">
				<h2>
					컨텐츠 관리<small>ver 1.0</small>
				</h2>
			</div>
			<div class="col-8" align="right">
				<button type="button" id="delContent">선택 컨텐츠 삭제/복구</button>
				<button type="button" id="#">웹 크롤러 작동하기</button>
			</div>
		</div>
		<div class="table-responsive" id="contentsTable">
			<table class="table align-items-center table-flush">
				<colgroup>
					<col style="width: 30px">
					<col style="width: 30px">
					<col style="width: 30px">
					<col style="width: 600px">
					<col style="width: 200px">
					<col style="width: 100px">
					<col style="width: 60px">
				</colgroup>
				<thead class="thead">
					<tr>
						<th scope="col" class="text-center"><input type="checkbox"
							id="allChk" name="allChk" /></th>
						<th scope="col" class="text-center">번호</th>
						<th scope="col" class="text-center">게시판</th>
						<th scope="col" class="text-center">제목</th>
						<th scope="col" class="text-center">조회수/추천수</th>
						<th scope="col" class="text-center">작성시간</th>
						<th scope="col" class="text-center">삭제여부</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${contentsList }" var="list" varStatus="status">
						<tr>
							<td><input type="checkbox" name="chk"
								value="${list.cnt_id }"><input type="hidden"
								id="chk_${list.cnt_id }" value="${list.cnt_del }"></td>
							<td>${list.cnt_id }</td>
							<td>${list.brd_id }</td>
							<td><a
								onclick="popUp('${list.cnt_connectLink}','${list.cnt_title}');">${list.cnt_title }</a></td>
							<td>${list.cnt_hit }/${list.cnt_like }</td>
							<td><fmt:formatDate value="${list.cnt_datetime}"
									pattern="yyyy-MM-dd" /></td>
							<td><c:if test="${list.cnt_del eq 1}">Del</c:if> <c:if
									test="${list.cnt_del eq 0}">No</c:if></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script>
	function popUp(link, title) {
		var url = link;
		var name = title;
		var specs = "width=800,height=auto, toolbar=no, menubar=no, resizeable=yes";
		window.open(url, name, specs);
		return false;
	}

	function getCheckedValue() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			resultArray.push($(this).val());
		});
		return resultArray;
	};

	function isDelWhatChecked() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			var tmp = "#chk_" + $(this).val();
			resultArray.push($(tmp).val());
		});
		return resultArray;
	};

	$(document).ready(function() {
		$(document).on('click', '#allChk', function() {
			$("input[name='chk']").prop("checked", this.checked);
		});

		$(document).on('click', '#delContent', function() {
			var checkedList = getCheckedValue();
			var delList = isDelWhatChecked();

			var query = {
				"checkedList" : checkedList,
				"delList" : delList
			};
			
			if (checkedList[0] == null) {
				alert("항목을 선택하여 주십시오");
			} else {
				if (confirm("선택된 항목을 삭제하시겠습니까?")) {
					$.ajax({
						traditional : true,
						async : false,
						type : "post",
						url : "${contextPath}/admin/A_contentsDelete",
						data : query,
						success : function(data) {
							var e = $(data).find("#contentsTable");
							$("#contentsTable").html(e).trigger("create");
						}
					});
				}
			}
		});
	});
</script>
<%@include file="include/A_footer.jsp"%>