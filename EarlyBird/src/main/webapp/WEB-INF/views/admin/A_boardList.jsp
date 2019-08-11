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
			<button type="button" data-toggle="modal" data-target="#modal-makeboard">게시판 추가하기</button>
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
					<th scope="col" class="text-center">게시판 관리</th>
					<th scope="col" class="text-center">사용자 표시여부</th>
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
						<td class="text-center">${list.brd_name }</td>
						<td class="text-center">${list.brd_id }</td>
						<td class="text-center">${list.brd_readauthname }<input
							type="hidden" id="readAuth${list.brd_id }"
							value="${list.brd_readauth }"> /${list.brd_writeauthname }<input
							type="hidden" id="writeauth${list.brd_id }"
							value="${list.brd_writeauth }"></td>
						<td class="text-center">1/1</td>
						<td class="text-center"><a
							href="A_postSearch?brd_id=${list.brd_id}"><input
								type="button" value="글보기"></a> <a href="#"><input
								type="button" value="표시/비 표시"></a>
							<button type="button" onclick="adminUpdate(${list.brd_id});">관리자
								설정</button> <input type="hidden" id="admin${list.brd_id }"
							value="${list.brd_id }"></td>
						<td class="text-center">${list.brd_exposurename }</td>
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
</script>
<%@include file="include/A_footer.jsp"%>
<%@include file="A_boardMake.jsp"%>