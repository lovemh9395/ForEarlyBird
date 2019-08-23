<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>

<!-- body -->
<div class="container">
	<div class="row">
		<div class="col-4">
			<h2>
				게시판 관리<small>ver 1.0</small>
			</h2>
		</div>
		<div class="col-8" align="right">
			<button type="button" data-toggle="modal"
				data-target="#modal-makeboard">게시판 추가하기</button>
		</div>
	</div>
	<div class="table-responsive">
		<table class="table align-items-center table-flush">
			<thead class="thead">
				<tr>
					<th scope="col" class="text-center">대분류</th>
					<th scope="col" class="text-center">분류</th>
					<th scope="col" class="text-center">게시판명</th>
					<th scope="col" class="text-center">게시판 ID</th>
					<th scope="col" class="text-center">권한(읽기/쓰기)</th>
					<th scope="col" class="text-center">새글/총 글 갯수</th>
															  
					<th scope="col" class="text-center">사용자 표시여부</th>
					<th scope="col" class="text-center">게시판 관리</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${boardList }" var="list">
					<tr>
						<td class="text-center">${list.large_name }<input
							type="hidden" id="large${list.brd_id }" value="${list.large_id }"></td>
						<td class="text-center">${list.category_name }<input
							type="hidden" id="category${list.brd_id }"
							value="${list.category_id }"></td>
						<td class="text-center"><a
							href="A_postList?brd_id=${list.brd_id}"><input type="button"
								value=${list.brd_name }></a></td>
						<td class="text-center">${list.brd_id }</td>
						<td class="text-center">${list.brd_readauthname }<input
							type="hidden" id="readAuth${list.brd_id }"
							value="${list.brd_readauth }"> /${list.brd_writeauthname }<input
							type="hidden" id="writeauth${list.brd_id }"
							value="${list.brd_writeauth }"></td>
						<td class="text-center">${list.brd_newPostNum}/${list.brd_allPostNum}</td>
						<td class="text-center">${list.brd_exposurename }</td>
															   
						<td class="text-center"><input type="button" value="표시/비 표시"
							onclick="changeVisibility(${list.brd_id},${list.brd_exposure});">
							<button type="button" onclick="adminUpdate(${list.brd_id});">관리자
								설정</button> <input type="hidden" id="admin${list.brd_id }"
							value="${list.brd_id }">&nbsp;&nbsp;<input type="button"
							value="x" onclick="deleteBoard(${list.brd_id });"></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div id="formodal" style="display: none"></div>
	</div>
</div>
<!-- end of body -->
<script>
/* function getByteLength(s,b,i,c){
	for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
	return b;
} */
$(document).ready(function() {
	var largeArray = new Array();
	var largeObject = new Object();
	var largeList = ${forlargeList};

	$.each(largeList, function(index, list1) {
		largeObject = new Object();
		largeObject.large_id = list1.id;
		largeObject.large_name = list1.name;
		largeArray.push(largeObject);
	});
	
	var largeSelectBox = $("select[name='large_List']");
	for (var i = 0; i < largeArray.length; i++) {
		largeSelectBox.append("<option value='"+largeArray[i].large_id+"'>"+ largeArray[i].large_name+ "</option>");
	}

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

	$(document).on("change","select[name='large_List']",function() {
		//두번째 셀렉트 박스를 삭제 시킨다.
		var categorySelectBox = $("select[name='category_List']");
		categorySelectBox.children().remove();
		
		//선택한 첫번째 박스의 값을 가져와 일치하는 값을 두번째 셀렉트 박스에 넣는다.
		$("option:selected", this).each(function() {
			var selectValue = $(this).val();
			categorySelectBox.append("<option value=''>소분류</option>");
			for (var i = 0; i < categoryArray.length; i++) {
				if (selectValue == categoryArray[i].large_id) {
					categorySelectBox.append("<option value="+categoryArray[i].category_id+">"+ categoryArray[i].category_name+ "</option>");
				}
			}
		});
	});
});

function adminUpdate(brd_id){
	$.ajax({
		async:false,
		type:"post",
		url:"${contextPath}/admin/A_adminList",
		data:{"brd_id":brd_id},
		success:function(data){
			$("#formodal").html(data);
			$("#formodal").css("display","");
			$("#modal-admin").modal({
				show:true,
				backdrop:false
			});
		}
	});
};


function changeAdmin(mem_userid,brd_id){
	var result = false;
	$.ajax({
		async:false,
		type:"post",
		url:"${contextPath}/admin/A_checkAdminId",
		data:{"brd_id":brd_id ,"mem_userid":mem_userid},
		success:function(data){
			if (data==0) {
				var datum = {"brd_id":brd_id ,"mem_userid":mem_userid}
				makeadmin(datum);
			} else {
				alert("이미 관리자로 선정된 계정입니다.");
			}
		}
	});
}

function makeadmin(datum){
	$.ajax({
		async:false,
		type:"post",
		url:"${contextPath}/admin/A_boardAdminUpdate",
		data:datum,
		success:function(data){
			$("#modal-admin").on("shown.bs.modal", function(){
				$(".modal-backdrop").remove();
			});
			$("#formodal").html(data);
			$("#modal-admin").modal({
				show:true,
				backdrop:false
			});
		}
	});
}

function searchMemberForAdmin(brd_id){
	var brd_id = brd_id;
	var searchFilter = document.getElementById("searchFilter").value;
	var searchkeyword = document.getElementById("searchkeyword").value;
	
	var url = "${contextPath}/admin/A_searchMemberForAdmin";
	var query = {"brd_id":brd_id, "searchFilter":searchFilter ,"searchkeyword":searchkeyword};
	
	if (searchkeyword=="") {
		url = "${contextPath}/admin/A_adminList";
		query = {"brd_id":brd_id};
	}
	
	$.ajax({
		async:false,
		type:"post",
		url:url,
		data:query,
		success:function(data){
			$("#modal-admin").on("shown.bs.modal", function(){
				$(".modal-backdrop").remove();
			});
			$("#formodal").html(data);
			$("#modal-admin").modal({
				show:true,
				backdrop:false
			});
		}
	});
}

function deleteBoard(brd_id){
	if ("${user.level}"==9) {
		if (confirm(brd_id+" 번 게시판을 삭제하시겠습니까?")) {
			$.ajax({
				async:false,
				type:"post",
				url:"${contextPath}/admin/A_leaveBoard",
				data:{"brd_id":brd_id},
				success:function(data){
					location.reload(true);
				}
			});
		}
	} else {
		alert("관리자가 아닙니다.");
	}
}

function changeVisibility(brd_id, brd_exposure){
	var exposure = brd_exposure;
	if (exposure == 1) {
		exposure = 0;
	} else {
		exposure = 1;
	}
	
	$.ajax({
		async:false,
		type:"post",
		url:"${contextPath}/admin/A_changeBoardVisibility",
		data:{"brd_id":brd_id, "brd_exposure":exposure},
		success:function(data){
			var result = data;
			location.reload(true);
		}
	});
	
}
</script>
<%@include file="include/A_footer.jsp"%>
<%@include file="A_boardMake.jsp"%>