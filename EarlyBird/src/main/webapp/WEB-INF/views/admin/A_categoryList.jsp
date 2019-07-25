<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
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
				<form id="largeCategoryForm" method="post" class="form-inline">
					<div class="form-group col-md-auto">
						<br>
						<h4>대분류 추가</h4>
					</div>
					<div class="form-group col-md-auto">
						<input type="text" class="form-control" id="largeCategoryName"
							name="largeCategoryName">
					</div>
					<button type="button" class="btn btn-default"
						id="largeCategoryAddButton">Add</button>
				</form>
			</div>
			<hr>
			<div>
				<jsp:include page="include/A_categoryListTable.jsp">
					<jsp:param value="대분류" name="category" />
				</jsp:include>
			</div>
		</div>

		<!-- 소분류 -->
		<div class="col-md-6">
			<div class="row">
				<form method="post" class="form-inline">
					<div class="form-group col-md-auto">
						<select class="form-control" name="categorykey" id="categorykey">
							<!-- c:foreach 대분류카테고리명 -->
							<option value="update">대분류명</option>
							<!-- 대분류 카테고리명으로 검색 -->
						</select>
					</div>
					<div class="form-group col-md-auto">
						<input type="text" class="form-control" id="categorykeyword"
							name="categorykeyword">
					</div>
					<button type="button" class="btn btn-default" onclick="">Search</button>
				</form>
			</div>
			<hr>
			<div>
				<jsp:include page="include/A_categoryListTable.jsp">
					<jsp:param value="분류" name="category" />
				</jsp:include>
			</div>
		</div>
	</div>
</div>
<!-- end of body -->
<script>
	$(document).ready(function() {
		$("#largeCategoryAddButton").click(function() {
			$("#largeCategoryForm").submit();
		});
	});
</script>
<%@include file="include/A_footer.jsp"%>