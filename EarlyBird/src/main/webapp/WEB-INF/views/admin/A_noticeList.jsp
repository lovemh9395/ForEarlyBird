<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="container">
	<div>
		<h2>
			공지사항 관리<small>ver 1.0</small>
		</h2>
	</div>
	<div class="row">
		<div class="col-md-auto">
			<a href="${contextPath }/admin/A_noticeMake"><input type="button"
				value="공지등록"></a>
		</div>
		<div class="col-md-auto">
			<input type="button" value="공지글 숨기기/복구" id="deleteWhatSelected">
		</div>
		<div class="col-md-auto">
			<input type="button" value="공지 수정" onclick="updateNotice()">
		</div>
		<div class="col-md-auto">
			<input type="button" value="공지 선정 해제" id="dropWhatSelected">
		</div>
	</div>
	<div class="table-responsive" id="noticeTable">
		<table class="table align-items-center table-flush">
			<thead>
				<tr>
					<th scope="col"><input type="checkbox" id="allChk"
						class="allChk" /></th>
					<th scope="col">번호</th>
					<th scope="col">분류</th>
					<th scope="col">제목</th>
					<th scope="col">표시/미표시</th>
					<th scope="col">작성자</th>
					<th scope="col">작성일</th>
					<th scope="col">조회수</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${noticeList }" var="list" varStatus="status">
					<tr>
						<td><input type="checkbox" name="chk"
							value="${list.post_id }"> <input type="hidden"
							id="chk_${list.post_id }" value="${list.post_del }"> <input
							type="hidden" id="chkNotice_${list.post_id }" value="${list.post_notice }">
						<td>${list.post_id }</td>
						<!-- 글 번호 -->
						<td><c:if test="${list.brd_id ==0 }">공지사항</c:if> <c:if
								test="${list.brd_id !=0 }">${list.category_name}</c:if></td>
						<!-- 대분류 -->
						<td class="left"><a
							href="${contextPath }/admin/A_noticeDetail?post_id=${list.post_id }">${list.post_title }</a></td>
						<!-- 제목 -->
						<td><c:if test="${list.post_del == 0 }">표시</c:if> <c:if
								test="${list.post_del == 1 }">No</c:if> <!-- 공지여부 -->
						<td>${list.mem_nickname }/${list.mem_userid }</td>
						<!-- 작성자 아이디-->
						<td>${list.post_simpletime }</td>
						<!-- 작성일 -->
						<td class="right">${list.post_hit }</td>
						<!-- 조회 -->
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<script>
	function getCheckedValue() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			resultArray.push($(this).val());
		});
		return resultArray;
	};

	function isDeleteWhatChecked() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			var tmp = "#chk_" + $(this).val();
			resultArray.push($(tmp).val());
		});
		return resultArray;
	};

	function isNoticeWhatChecked() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			var tmp = "#chkNotice_" + $(this).val();
			resultArray.push($(tmp).val());
		});
		return resultArray;
	};

	function updateNotice() {
		var checkedList = getCheckedValue();
		if (checkedList[1] != null) {
			alert("하나의 항목만 선택하여 주십시오");
		} else if (checkedList[0] == null) {
			alert("항목을 선택하여 주십시오");
		} else {
			if (confirm("선택한 항목을 수정하시겠습니까?")) {
				var e = checkedList[0];
				window.location.href = "${contextPath}/admin/A_noticeUpdate?post_id="
						+ e;
			}
		}
	}

	$(document).ready(function() {
		$(document).on('click', '#allChk', function() {
			$("input[name='chk']").prop("checked", this.checked);
		});

		$(document).on('click', '#deleteWhatSelected', function() {
			var checkedList = getCheckedValue();
			var NoticeOrNot = isDeleteWhatChecked();

			var query = {
				"brd_id" : 0,
				"checkedList" : checkedList,
				"NoticeOrNot" : NoticeOrNot
			};
			if (checkedList[0] == null) {
				alert("항목을 선택하여 주십시오");
			} else {
				if (confirm("선택된 항목을 변경하시겠습니까?")) {
					$.ajax({
						traditional : true,
						async : false,
						type : "post",
						url : "${contextPath}/admin/A_noticeDelete",
						data : query,
						success : function(data) {
							var e = $(data).find("#noticeTable");
							$("#noticeTable").html(e).trigger("create");
						}
					});
				}
			}
		});

		$(document).on('click', '#dropWhatSelected', function() {
			var checkedList = getCheckedValue();
			var NoticeOrNot = isNoticeWhatChecked();
			var query = {
				"brd_id" : 0,
				"checkedList" : checkedList,
				"NoticeOrNot" : NoticeOrNot
			};
			if (checkedList[0] == null) {
				alert("항목을 선택하여 주십시오");
			} else {
				if (confirm("선택된 항목을 변경하시겠습니까?")) {
					$.ajax({
						traditional : true,
						async : false,
						type : "post",
						url : "${contextPath}/admin/A_noticeDrop",
						data : query,
						success : function(data) {
							var e = $(data).find("#noticeTable");
							$("#noticeTable").html(e).trigger("create");
						}
					});
				}
			}
		});
	});
</script>
<!-- end of body -->
<%@include file="include/A_footer.jsp"%>
