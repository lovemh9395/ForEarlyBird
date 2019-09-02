<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@include file="include/A_header.jsp"%>
<script>
$(document).ready(function(){
	$("#largeCategoryAddButton").click(function(){
		var large_name = $("#largeCategoryName").val()
		if (large_name!="") {
			$.ajax({
				async:false,
				type:"post",
				url:"${contextPath}/admin/A_largeCategoryMake",
				data:{"large_name":large_name},
				success:function(data){
					if (data==1) {
						alert("success");
					} else if (data<0){
						alert("fail");
					}
					window.location.reload();
				}
			});
		} else {
			alert("올바른 값을 입력하여 주십시오");
		};
	});
	
	$("#CategoryAddButton").click(function(){
		var large_id = document.getElementById("large_id").value;
		var category_name = document.getElementById("category_name").value;
		if (category_name!="") {
			$.ajax({
				type:"post",
				url:"${contextPath}/admin/A_categoryMake",
				data:{"large_id":large_id ,"category_name":category_name},
				success:function(data){
					if (data==1) {
						alert("success");
					} else if (data<0){
						alert("fail");
					}
					window.location.reload();
				}
			});
		} else {
			alert("올바른 값을 입력하여 주십시오");
		}
	});
});


function largeDelete(large_id){
	var childNumID = "";
	if (large_id<=9) {
		childNumID = "childNum0"+large_id;
	} else { 
		childNumID = "childNum"+large_id;
	}
	var childNum = document.getElementById(childNumID).value;
	
	if (childNum!=0) {
		$("#modal-notificationChildCategoryExist").modal();
	} else {
		$.ajax({
			async:false,
			type:"post",
			url:"${contextPath}/admin/A_largeCategoryDelete",
			data:{"large_id":large_id},
			success:function(data){
				if (data==1) {
					alert("success");
				} else if (data<0){
					alert("fail");
				}
				window.location.reload();
			}
		});
	}
};

 
 
function smallDelete(index){
	var categoryID = "categoryID"+index;
	var category_id = document.getElementById(categoryID).value;
	var boardNumID = "childBoardNum"+index;
	var boardNum = document.getElementById(boardNumID).value;
	if (boardNum!=0) {
		$("#modal-notificationChildBoardCategoryExist").modal();
	} else {
		$.ajax({
			async:false,
			type:"post",
			url:"${contextPath}/admin/A_categoryDelete",
			data:{"category_id":category_id},
			success:function(data){
				if (data==1) {
					alert("success");
				} else if (data<0){
					alert("fail");
				}
				window.location.reload();
			}
		});
	}
};
</script>
<!-- body -->
<!-- Dark table -->
<div class="container">
	<!-- 대분류 -->
	<h2>
		카테고리 관리 <small>ver 1.0</small>
	</h2>
	<div class="row">
		<div class="col-md-6">
			<div>
				<form name="largeCategoryForm" method="post" class="form-inline"
					action="#">
					<div class="form-group col-md-auto">
						<br>
						<h4>대분류 추가</h4>
					</div>
					<div class="form-group col-md-auto">
						<input type="text" class="form-control" id="largeCategoryName"
							name="largeCategoryName">
					</div>
					<button type="button" class="btn btn-default"
						id="largeCategoryAddButton">추가</button>
				</form>
			</div>
			<hr>
			<div id="largeCategoryListDIV">
				<div class="row mt-5">
					<div class="col">
						<div class="card bg-default shadow">
							<div class="card-header bg-transparent border-0">
								<h3 class="text-white mb-0">대분류 목록</h3>
							</div>
							<div class="table-responsive">
								<table class="table align-items-center table-dark table-flush">
									<colgroup>
										<col style="width: 20px">
										<col style="width: 300px">
										<col style="width: 20px">
									</colgroup>
									<thead class="thead-dark">
										<tr>
											<th scope="col">대분류코드</th>
											<th scope="col">대분류명</th>
											<th scope="col">하위 소분류 갯수</th>
											<th scope="col"></th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items='${largeList }' var="list">
											<tr>
												<td>${list.id }</td>
												<td>${list.name }</td>
												<td align="center">${list.childNum }<input
													type="hidden" id="childNum${list.id }"
													value="${list.childNum }">
												</td>
												<td><button class="btn btn-default"
														style="padding: 1px; margin: 1px; border: 1px;"
														onclick="largeDelete(${list.id });">삭제</button></td>
											</tr>
										</c:forEach>
										<!-- c:foreach 끝 -->
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 소분류 -->
		<div class="col-md-6">
			<div class="row">
				<form name="categoryForm" method="post" class="form-inline"
					action="A_categoryMake">
					<div class="form-group col-md-auto">
						<select class="form-control" name="large_id" id="large_id">
							<c:forEach items='${largeList }' var="list">
								<option value="${list.id }">${list.name }</option>
							</c:forEach>
							<!-- 대분류 카테고리명으로 검색 -->
						</select>
					</div>
					<div class="form-group col-md-auto">
						<input type="text" class="form-control" id="category_name"
							name="category_name">
					</div>
					<button type="button" class="btn btn-default"
						id="CategoryAddButton">추가</button>
				</form>
			</div>
			<hr>
			<div id="categoryListDIV">
				<div class="row mt-5">
					<div class="col">
						<div class="card bg-default shadow">
							<div class="card-header bg-transparent border-0">
								<h3 class="text-white mb-0">분류목록</h3>
							</div>
							<div class="table-responsive">
								<table class="table align-items-center table-dark table-flush">
									<thead class="thead-dark">
										<tr>
											<th scope="col">분류코드</th>
											<th scope="col">대분류명</th>
											<th scope="col">분류명</th>
											<th scope="col">게시판수</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items='${smallList }' var="list">
											<tr>
												<td>${list.id }<input type="hidden"
													id="categoryID${list.index }" value="${list.id }"></td>
												<td>${list.large_name }
												<td>${list.name }</td>
												<td>${list.boardNum }<input type="hidden"
													id="childBoardNum${list.index }" value="${list.boardNum }">
													&nbsp;&nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<button class="btn btn-default"
														style="padding: 1px; margin: 1px; border: 1px;"
														onclick="smallDelete(${list.index });">삭제</button></td>
											</tr>
										</c:forEach>
										<!-- c:foreach 끝 -->
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- end of body -->

<%@include file="include/A_footer.jsp"%>
<%@include file="A_categoryDelete.jsp"%>
