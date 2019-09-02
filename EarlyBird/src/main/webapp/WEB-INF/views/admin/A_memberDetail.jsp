<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="container">
	<h1>
		회원 작성 글 보기<small>v1.0</small> <input type="hidden" id="idbox"
			value="${mem_userid }">
	</h1>
	<h3>${mem_nickname }(${mem_userid })님의활동기록</h3>
	<div class="row">
		<h5>작성 글 목록</h5>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <select name="postIsDel">
			<option value="all">전체</option>
			<option value="1">삭제 글</option>
			<option value="0">미 삭제글</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="checkedPostDel"
			value="선택항목 일괄 삭제/복구">
	</div>
	<div id="A_memberPostList">
		<%@include file="A_memberPostList.jsp"%>
	</div>
	<br>
	<div class="row">
		<h5>작성 댓글 목록</h5>
		&nbsp;&nbsp;&nbsp;&nbsp; <select name="rplIsDel">
			<option value="all">전체</option>
			<option value="1">삭제 댓글</option>
			<option value="0">미 삭제글</option>
		</select>&nbsp;&nbsp;&nbsp;&nbsp;<input type="button" id="checkedReplyDel"
			value="선택항목 일괄 삭제/복구">
	</div>
	<div id="A_memberReplyList">
		<%@include file="A_memberReplyList.jsp"%>
	</div>
</div>
<!-- end of body -->
<%@include file="include/A_footer.jsp"%>
<script type="text/javascript">
	function getCheckedValueWhatPost() {
		var resultArray = [];
		$("input[name='chkPost']:checked").each(function() {
			resultArray.push($(this).val());
		});
		return resultArray;
	};
	
	function getDelWhoCheckedPost() {
		var resultArray = [];
		$("input[name='chkPost']:checked").each(function() {
			var index = "#chkPost_" + $(this).val();
			resultArray.push($(index).val());
		});
		return resultArray;
	};

	function getCheckedValueWhatReply() {
		var resultArray = [];
		$("input[name='chkReply']:checked").each(function() {
			resultArray.push($(this).val());
		});
		return resultArray;
	};
	
	function getDelWhoCheckedReply() {
		var resultArray = [];
		$("input[name='chkReply']:checked").each(function() {
			var index = "#chkReply_" + $(this).val();
			resultArray.push($(index).val());
		});
		return resultArray;
	};

	$(document).ready(function() {
		$(document).on('click', '#allChkPost', function() {
			$("input[name='chkPost']").prop("checked", this.checked);
		});

		$(document).on('click', '#allChkReply', function() {
			$("input[name='chkReply']").prop("checked", this.checked);
		});
		
		$(document).on('click', '#checkedPostDel', function() {
			var checkedList = getCheckedValueWhatPost();
			var isDelList = getDelWhoCheckedPost();
			var query = {
				"mem_userid" : $("#idbox").val(),
				"checkedList" : checkedList,
				"isDelList" : isDelList,
			};

			if (checkedList[0] == null) {
				alert("올바른 항목을 선택하여 주십시오");
			} else {
				if (confirm("선택된 항목을 변경하시겠습니까?")) {
					$.ajax({
						traditional : true,
						async : false,
						type : "post",
						url : "${contextPath}/admin/A_memberPostListUpdate",
						data : query,
						success : function(data) {
							var e = $(data).find("#posttable");
							$("#posttable").html(e).trigger("create");
						}
					});
				}
			}
		});
		
		$(document).on('click', '#checkedReplyDel', function() {
			var checkedList = getCheckedValueWhatReply();
			var isDelList = getDelWhoCheckedReply();
			var query = {
				"mem_userid" : $("#idbox").val(),
				"checkedList" : checkedList,
				"isDelList" : isDelList,
			};

			if (checkedList[0] == null) {
				alert("올바른 항목을 선택하여 주십시오");
			} else {
				if (confirm("선택된 항목을 변경하시겠습니까?")) {
					$.ajax({
						traditional : true,
						async : false,
						type : "post",
						url : "${contextPath}/admin/A_memberReplyListUpdate",
						data : query,
						success : function(data) {
							console.log("1");
							var e = $(data).find("#replytable");
							$("#replytable").html(e).trigger("create");
						}
					});
				}
			}
		});

		$(document).on("change", "select[name='postIsDel']", function() {
			var query;
			var option = $(this).val();
			if (option == 0 || option == 1) {
				query = {
					"mem_userid" : $("#idbox").val(),
					"post_del" : option
				}
			} else {
				query = {
					"mem_userid" : $("#idbox").val(),
					"post_del" : "none"
				}
			}
			alert(query.mem_userid + " / " + query.post_del);
			$.ajax({
				async : false,
				type : "post",
				data : query,
				url : "${contextPath}/admin/A_memberPostList",
				success : function(data) {
					var e = $(data).find("#A_memberPostList");
					$("#A_memberPostList").html(e).trigger("create");
				}
			});
		});

		$(document).on("change", "select[name='rplIsDel']", function() {
			var query;
			var option = $(this).val();
			if (option == 0 || option == 1) {
				query = {
					"mem_userid" : $("#idbox").val(),
					"rpl_del" : option
				}
			} else {
				query = {
					"mem_userid" : $("#idbox").val(),
					"rpl_del" : "none"
				}
			}
			
			$.ajax({
				async : false,
				type : "post",
				data : query,
				url : "${contextPath}/admin/A_memberReplyList",
				success : function(data) {
					var e = $(data).find("#A_memberReplyList");
					$("#A_memberReplyList").html(e).trigger("create");
				}
			});
		});
	});
</script>