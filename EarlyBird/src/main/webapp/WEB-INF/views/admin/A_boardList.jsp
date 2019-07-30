<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
<script type="text/javascript">
function adminUpdate(brd_id){
	$.ajax({
		async:false,
		type:"post",
		url:"${contextPath}/admin/A_adminList",
		data:{"brd_id":brd_id},
		success:function(data){
			$(data).modal();
		}
	});
};

function changeAdmin(){
	var mem_id = null;
	$("input[name=checkbox]:checked").each(function(){
		mem_id=$(this).val();
	});
	
	if (mem_id!=null) {
		$.ajax({
			async:false,
			type:"post",
			url:"${contextPath}/admin/A_boardAdminUpdate",
			data:{"mem_id":mem_id},
			success:function(data){
				$(data).modal();
			}
		});
	}
};
</script>
<!-- body -->
<div class="container">
	<div>
		<h2>
			게시판 관리<small>ver 1.0</small>
		</h2>
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
						<td>${list.large_name }<input type="hidden" id=large
							"${list.brd_id }" value="${list.large_id }"></td>
						<td>${list.category_name }<input type="hidden"
							id="category${list.brd_id }" value="${list.category_id }"></td>
						<td>${list.brd_name }</td>
						<td>${list.brd_id }</td>
						<td>${list.brd_readauthname }<input type="hidden"
							id="readAuth${list.brd_id }" value="${list.brd_readauth }">/${list.brd_writeauthname }<input
							type="hidden" id="writeauth${list.brd_id }"
							value="${list.brd_writeauth }"></td>
						<td>1/1</td>
						<td><a href="A_postSearch?brd_id=${list.brd_id}"><input
								type="button" value="글보기"></a> <a href="#"><input
								type="button" value="공지글"></a>
							<button type="button" onclick="adminUpdate(${list.brd_id});">관리자
								설정</button> <input type="hidden" id="admin${list.brd_id }"
							value="${list.brd_id }"></td>
						<td>${list.brd_exposurename }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!-- end of body -->
<%@include file="A_adminList.jsp"%>
<%@include file="include/A_footer.jsp"%>