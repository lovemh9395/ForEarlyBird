<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<%@include file="include/A_header.jsp"%>
<!-- body -->
<div class="container">
	<input type=hidden id="hiddenToday" value=""> <input
		type="hidden" id="hiddenTomorrow" value="">
	<div>
		<div>
			<h1>게시물 관리</h1>
		</div>
	</div>
	<div class="section">
		<div class="table-responsive">
			<table class="table align-items-center table-flush">
				<colgroup>
					<col style="width: auto;">
					<col style="width: auto;">
					<col style="width: auto;">
					<col style="width: auto;">
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">기간</th>
						<td>
							<div class="row">
								<div class="col-md-1">
									<input type="button" value="오늘" style="width: 50px"
										onclick="setToday()">
								</div>
								<div class="col-md-1">
									<input type="button" value="3일" style="width: 50px"
										onclick="set3_days_ago()">
								</div>
								<div class="col-md-1">
									<input type="button" value="7일" style="width: 50px"
										onclick="set7_days_ago()">
								</div>
								<div class="col-md-1">
									<input type="button" value="1개월" style="width: 50px"
										onclick="set1_month_ago()">
								</div>
								&nbsp;&nbsp;
								<div class="col-md-auto">
									<input type="date" id="dateFrom" value="">
								</div>
								<div class="col-md-auto">
									<input type="date" id="dateTo" value="">
								</div>
							</div>
						</td>
						<th scope="row">삭제 여부</th>
						<td><select id="post_notice" name="post_notice">
								<option value="-1">전체</option>
								<option value="0">일반글</option>
								<option value="1">삭제글</option>
						</select></td>
					</tr>
					<tr>
						<th scope="row">게시판 선택</th>
						<td><select id="large_List" name="large_List">
								<option value="0">대분류 전체</option>
						</select> <select id="category_List" name="category_List">
								<option value="0">소분류 전체</option>
						</select> <select id='brd_List' name="brd_List">
								<option value="0">전체목록</option>
						</select></td>
						<th scope="row">공지글 여부</th>
						<td><select id="post_del" name="post_del">
								<option value="-1">전체</option>
								<option value="1">공지글</option>
								<option value="0">비 공지글</option>
						</select></td>
					</tr>
					<tr>
						<th scope="row">게시글 찾기</th>
						<td><select id="keywordTypeForSearchPost"
							name="keywordTypeForSearchPost">
								<option value='allthing'>전체</option>
								<option value='post_title'>제목</option>
								<option value='post_content'>내용</option>
								<option value='mem_nickname'>닉네임</option>
								<option value='mem_userid'>아이디</option>
								<option value='post_ip'>아이피</option>
						</select> <input type="text" id="keywordForSearchPost"
							name="keywordForSearchPost" value="" placeholder="검색어를 입력하여 주십시오"
							style="width: 300px; display: none;" /></td>
						<th scope="row"></th>
						<td><div>
								<input type="button" value=" 검색 " onclick="searchPostList()">
							</div></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</div>
<div id="postListTable">
	<%@include file="A_postList.jsp"%>
</div>
<!-- end of body -->
<script>
	function makeLargeList() {
		var largeArray = new Array();
		var largeObject = new Object();
		var largeList = ${forlargeList};

		$.each(largeList, function(index, list1) {
			largeObject = new Object();
			largeObject.large_id = list1.id;
			largeObject.large_name = list1.name;
			largeArray.push(largeObject);
		});
		return largeArray;
	};

	function makeCategoryList() {
		var categoryArray = new Array();
		var categoryObject = new Object();
		var categoryList = ${forcategoryList};
		$.each(categoryList, function(index, list2) {
			categoryObject = new Object();
			categoryObject.large_id = list2.large_id;
			categoryObject.category_id = list2.category_id;
			categoryObject.category_name = list2.category_name;
			categoryArray.push(categoryObject);
		});
		return categoryArray;
	};

	function makeBrdList() {
		var brdArray = new Array();
		var brdObject = new Object();
		var brdList = ${forbrdList};
		$.each(brdList, function(index, list3) {
			brdObject = new Object();
			brdObject.brd_id = list3.brd_id;
			brdObject.brd_name = list3.brd_name;
			brdObject.large_id = list3.large_id;
			brdObject.category_id = list3.category_id;
			brdArray.push(brdObject);
		});
		return brdArray;
	};
	
	function getCheckedValue() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			resultArray.push($(this).val());
		});
		return resultArray;
	};

	function isNoticeWhatChecked() {
		var resultArray = [];
		$("input[name='chk']:checked").each(function() {
			var tmp = "#chk_" + $(this).val();
			resultArray.push($(tmp).val());
		});
		return resultArray;
	};

	function setToday() {
		$("#dateFrom").val($("#hiddenToday").val());
	};

	function set3_days_ago() {
		var day3 = getDate(0, 3);
		$("#dateFrom").val(day3);
	};

	function set7_days_ago() {
		var day7 = getDate(0, 7);
		$("#dateFrom").val(day7);
	};

	function set1_month_ago() {
		var month1 = getDate(1, 0);
		$("#dateFrom").val(month1);
	};

	function getDate(i, j) {
		var now = new Date();
		var year = now.getFullYear();
		var mon = (now.getMonth() + 1 - i) > 9 ? '' + (now.getMonth() + 1 - i)
				: '0' + (now.getMonth() + 1 - i);
		var day = (now.getDate() - j) > 9 ? '' + (now.getDate() - j) : '0'
				+ (now.getDate() - j);
		var today = year + '-' + mon + '-' + day;
		return today;
	};

	function searchPostList() {
		var dateFrom = $("#dateFrom").val();
		var dateTo = $("#hiddenTomorrow").val();
		var large_id = $("#large_List").val();
		var category_id = $("#category_List").val();
		var brd_id = $("#brd_List").val();
		var post_notice = $("#post_notice").val();
		var post_del = $("#post_del").val();
		var keywordType = $("#keywordTypeForSearchPost").val();
		if ($("#keywordForSearchPost").val() == "") {
			var keyword = "&NotAtAll&";
		} else {
			var keyword = $("#keywordForSearchPost").val();
		}

		var query = {
			"dateFrom" : dateFrom,
			"dateTo" : dateTo,
			"large_id" : large_id,
			"category_id" : category_id,
			"brd_id" : brd_id,
			"post_notice" : post_notice,
			"keywordType" : keywordType,
			"keyword" : keyword,
			"post_del" : post_del
		};

		$.ajax({
			async : false,
			type : "post",
			url : "${contextPath}/admin/A_postSearch",
			data : query,
			success : function(data) {
				var e = $(data).find("#postListTable");
				$("#postListTable").html(e).trigger("create");
			}
		});
	};
	
	$(document).ready(function() {
		$(document).on("change","select[name='keywordTypeForSearchPost']",function() {
			var inputbox = $("input[name='keywordForSearchPost']");
			var selectValue = $(this).val();
			if (selectValue != "allthing") {
				inputbox.attr('style',"width: 300px;display:inline;");
			} else {
				inputbox.attr('style',"width: 300px;display:none;");
			}
		});

		var largeArray = makeLargeList();
		var categoryArray = makeCategoryList();
		var brdArray = makeBrdList();

		//largeSelectBox 만들기
		var largeSelectBox = $("select[name='large_List']");
		for (var i = 0; i < largeArray.length; i++) {
			largeSelectBox.append("<option value='"+largeArray[i].large_id+"'>"+ largeArray[i].large_name+ "</option>");
		}

		//categorySelectBox 만들기
		$(document).on("change","select[name='large_List']",function() {
			//두번째 셀렉트 박스를 삭제 시킨다.
			var brdSelectBox = $("select[name='brd_List']");
			brdSelectBox.children().remove();
			brdSelectBox.append("<option value='0'>전체목록</option>");
			var categorySelectBox = $("select[name='category_List']");
			categorySelectBox.children().remove();

			//선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
			$("option:selected", this).each(function() {
				var selectValue = $(this).val();
				categorySelectBox.append("<option value='0'>소분류 전체</option>");
				for (var i = 0; i < categoryArray.length; i++) {
					if (selectValue == categoryArray[i].large_id) {
						categorySelectBox.append("<option value="+categoryArray[i].category_id+">"+ categoryArray[i].category_name+ "</option>");
					}
				}
			});
		});

		//brdSelectBox 만들기
		$(document).on("change","select[name='category_List']",function() {
			//세번째 셀렉트 박스를 삭제 시킨다.
			var brdSelectBox = $("select[name='brd_List']");
			brdSelectBox.children().remove();

			//선택한 두번째 박스의 값을 가져와 일치하는 값을 세번째 셀렉트 박스에 넣는다.
			$("option:selected", this).each(function() {
				var selectValue = $(this).val();
				brdSelectBox.append("<option value='0'>전체목록</option>");
				for (var i = 0; i < brdArray.length; i++) {
					if (selectValue == brdArray[i].category_id) {
						brdSelectBox.append("<option value="+brdArray[i].brd_id+">"+ brdArray[i].brd_name+ "</option>");
					}
				}
			});
		});

		var today = getDate(0, 0);
		var oMG = getDate(1, 0);
		var tomorrow = getDate(0, -1);
		$("#hiddenToday").val(today);
		$("#hiddenTomorrow").val(tomorrow);
		$("#dateFrom").val(oMG);
		$("#dateTo").val(today);

		$(document).on('click','#allChk',function() {
			$("input[name='chk']").prop("checked",this.checked);
		});

		$(document).on('click','#deleteWhatSelected',function() {
			var checkedList = getCheckedValue();
			var brd_id =<%=request.getParameter("brd_id")%>;

			var query = {
				"checkedList" : checkedList,
				"brd_id" : brd_id
			};
			if (confirm("선택된 항목을 삭제하시겠습니까?")) {
				$.ajax({
					traditional : true,
					async : false,
					type : "post",
					url : "${contextPath}/admin/A_postDelete",
					data : query,
					success : function(data) {
						var e = $(data).find("#postListTable");
						$("#postListTable").html(e).trigger("create");
					}
				});
			}
		});

		$(document).on('click','#reViewWhatSelected',function() {
			var checkedList = getCheckedValue();
			var brd_id =<%=request.getParameter("brd_id")%>;

			var query = {
				"checkedList" : checkedList,
				"brd_id" : brd_id
			};
			if (confirm("선택된 항목을 복구하시겠습니까?")) {
				$.ajax({
					traditional : true,
					async : false,
					type : "post",
					url : "${contextPath}/admin/A_postReView",
					data : query,
					success : function(data) {
						var e = $(data).find("#postListTable");
						$("#postListTable").html(e).trigger("create");
					}
				});
			}
		});

		$(document).on('click','#changeNoticeParamWhatSelected',function() {
			var checkedList = getCheckedValue();
			var NoticeOrNot = isNoticeWhatChecked();
			var brd_id =<%=request.getParameter("brd_id")%>;

			var query = {
				"checkedList" : checkedList,
				"brd_id" : brd_id,
				"NoticeOrNot" : NoticeOrNot
			};
			if (confirm("선택된 항목을 변경하시겠습니까?")) {
				$.ajax({
					traditional : true,
					async : false,
					type : "post",
					url : "${contextPath}/admin/A_postUpdate",
					data : query,
					success : function(data) {
						var e = $(data).find("#postListTable");
						$("#postListTable").html(e).trigger("create");
					}
				});
			}
		});
	});
</script>
<%@include file="include/A_footer.jsp"%>