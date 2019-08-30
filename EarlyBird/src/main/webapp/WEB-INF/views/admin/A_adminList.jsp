<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="container">
	<h2>
		관리자 설정<small>v1.0</small>
	</h2>
	<div class="panel panel-default">
		<div class="panel-heading">관리자 후보 회원목록 검색</div>
		<div class="panel-body">
			<form method="post" class="form-inline">
				<div class="form-group col-md-auto">
					<select name="skey">
						<option value="allthing">전체</option>
						<option value="mem_userid">아이디</option>
						<option value="mem_username">이름</option>
						<option value="mem_levelname">권한명</option>
						<option value="mem_nickname">닉네임</option>
					</select>
				</div>
				<div class="form-group col-md-auto">
					<input type="text" name="svalue" style="display: none"> <select
						name="svalue" style="display: none">
						<option value="all">전체</option>
						<option value="1">차단 유저</option>
						<option value="2">미 인증 회원</option>
						<option value="3">인증 회원</option>
						<option value="4">탈퇴 회원</option>
						<option value="5">장기 미 접속자</option>
						<option value="6">이용제한 유저</option>
					</select>
				</div>
				<button type="button" id="memberSearch">Search</button>
			</form>
		</div>
	</div>
	<hr>
	<div class="panel panel-default" id="membertable">
		<div class="row">
			<div class="col-2">회원 명단 출력</div>
			<div class="col-10" align="right">
				<button type="button" id="updateAuth">스태프 승급/해제</button>
			</div>
		</div>
		<div class="panel-body">
			<%--테이블 태그 --%>
			<table class="table table-striped">
				<thead>
					<tr>
						<th><input type="checkbox" id="allChk"></th>
						<th>번호</th>
						<th>아이디</th>
						<th>닉네임</th>
						<th>권한</th>
						<th>전화번호</th>
						<th>생년월일</th>
					</tr>
				</thead>
				<tbody>
					<%-- JSTL, EL을 이용한 동적 데이터 출력 --%>
					<c:forEach var="list" items="${memberList}" varStatus="Status">
						<tr>
							<td><input type="checkbox" name="chk" value="${mem_userid}"><input
								type="hidden" name="chk_${list.mem_userid}" value="${list.mem_level}"></td>
							<td>${Status.count}</td>
							<td><a href="${contextPath }/admin/A_memberDetail?mem_userid=${list.mem_userid}<">${list.mem_userid}</a></td>
							<td>${list.mem_nickname}</td>
							<td>${list.mem_levelname}</td>
							<td>${list.mem_phone}</td>
							<td>${list.mem_birthday}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
</div>
<script type="text/javascript">
	function getCheckedValue() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			resultArray.push($(this).val());
		});
		return resultArray;
	};

	function getAuthWhoChecked() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			var tmp = "#chk_" + $(this).val();
			if ($(this).val()==1||$(this).val()==4||$(this).val()==5||$(this).val()==6||$(this).val()==7) {
				return null;
			}
			resultArray.push($(tmp).val());
		});
		return resultArray;
	};

	$(document).ready(function() {
		$(document).on("change", "select[name='skey']", function() {
			var inputbox = $("input[name='svalue']");
			var selectbox = $("select[name='svalue']");
			var selectValue = $(this).val();
			if (selectValue != "allthing") {
				if (selectValue == "mem_levelname") {
					inputbox.val('');
					inputbox.attr('style', "display:none;");
					selectbox.attr('style', "display:inline;");
				} else {
					inputbox.attr('style', "display:inline;");
					selectbox.attr('style', "display:none;");
				}
			} else {
				selectbox.attr('style', "display:none;");
				inputbox.attr('style', "display:none;");
			}
		});

		$(document).on('click', '#allChk', function() {
			$("input[name='chk']").prop("checked", this.checked);
		});

		$(document).on('click', '#memberSearch', function() {
			var selectkey = $("select[name='skey']").val();
			var inputdata = $("input[name='svalue']").val();
			var selectdata = $("select[name='svalue']").val();

			var query = null;
			if (inputdata == "") {
				query = {
					"keytype" : selectkey,
					"keyword" : selectdata
				}
			} else {
				query = {
					"keytype" : selectkey,
					"keyword" : inputdata
				}
			}

			$.ajax({
				async : false,
				type : "post",
				data : query,
				url : "${contextPath}/admin/A_AdminCandidateSearch",
				success : function(data) {
					var e = $(data).find("#membertable");
					$("#membertable").html(e).trigger("create");
				}
			});
		});

		$(document).on('click', '#updateAuth', function() {
			var checkedList = getCheckedValue();
			var AuthList = getAuthWhoChecked();
			var query = {
				"checkedList" : checkedList,
				"authList" : AuthList
			};
			if (checkedList[0] == null||AuthList == null) {
				alert("올바른 항목을 선택하여 주십시오");
			} else {
				if (confirm("선택된 회원의 등급을 변경하시겠습니까?")) {
					$.ajax({
						traditional : true,
						async : false,
						type : "post",
						url : "${contextPath}/admin/A_adminUpdate",
						data : query,
						success : function(data) {
							var e = $(data).find("#membertable");
							$("#membertable").html(e).trigger("create");
						}
					});
				}
			}
		});
	});
</script>
<!-- end of body -->
<%@include file="include/A_footer.jsp"%>