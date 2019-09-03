<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="modal fade" id="modal-makeboard" tabindex="-1" role="dialog"
	aria-labelledby="modal-form" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" role="document">
		<div class="modal-content">
			<div class="modal-body p-0">
				<div class="card bg-secondary shadow border-0">
					<div class="card-header px-lg-20 py-lg-20">
						<div class="row">
							<h2 class="col-11">
								게시판
								<c:if test="${empty brd_id}">추가하기</c:if>
								<c:if test="${!empty brd_id}">수정하기</c:if>
								<small>ver1.0</small>
							</h2>
							<div class="col-1" style="float: 'right'">
								<input type="button"
									style="float: 'right'; padding: 1px; margin: 1px; border: 1px;"
									class="btn btn-default" data-dismiss="modal"
									value="&nbsp;X&nbsp;">
							</div>
						</div>
					</div>
					<div class="card-body px-lg-20 py-lg-20">
						<form class="form-horizontal"
							style="background: #FFFFFF; padding: 5px">
							<div class="container">
								<div class="row">
									<label for="large_List"> 대분류 : <select
										name="large_List" id="large_List" style="width: 100px">
											<option value="">대분류</option>
									</select> &nbsp;&nbsp;
									</label> <label for="category_List"> 소분류 : <select
										name="category_List" id="category_List" style="width: 100px">
											<option value="">소분류</option>
									</select>
									</label>
								</div>
								<div class="row">
									<p>읽기 권한 →</p>
									<label class="radio-inline"> <c:if
											test="${brd_readauth eq 1}">
											<input type="radio" name="RadioRead" value="1" checked>
										</c:if> <c:if test="${brd_readauth ne 1}">
											<input type="radio" name="RadioRead" value="1">
										</c:if> 비회원
									</label> <label class="radio-inline"> <c:if
											test="${brd_readauth eq 2}">
											<input type="radio" name="RadioRead" value="2" checked>
										</c:if> <c:if test="${brd_readauth ne 2}">
											<input type="radio" name="RadioRead" value="2">
										</c:if> 미 인증 회원
									</label> <label class="radio-inline"> <c:if
											test="${brd_readauth eq 3}">
											<input type="radio" name="RadioRead" value="3" checked>
										</c:if> <c:if test="${brd_readauth ne 3}">
											<input type="radio" name="RadioRead" value="3">
										</c:if> 인증 회원
									</label><label class="radio-inline"> <c:if
											test="${brd_readauth eq 8}">
											<input type="radio" name="RadioRead" value="8" checked>
										</c:if> <c:if test="${brd_readauth ne 8}">
											<input type="radio" name="RadioRead" value="8">
										</c:if> 관리자
									</label>
								</div>
								<div class="row">
									<p>쓰기 권한 →</p>
									<label class="radio-inline"> <c:if
											test="${brd_writeauth eq 1}">
											<input type="radio" name="RadioWrite" value="1" checked>
										</c:if> <c:if test="${brd_writeauth ne 1}">
											<input type="radio" name="RadioWrite" value="1">
										</c:if> 비회원
									</label> <label class="radio-inline"><c:if
											test="${brd_writeauth eq 2}">
											<input type="radio" name="RadioWrite" value="2" checked>
										</c:if> <c:if test="${brd_writeauth ne 2}">
											<input type="radio" name="RadioWrite" value="2">
										</c:if> 미 인증 회원 </label> <label class="radio-inline"> <c:if
											test="${brd_writeauth eq 3}">
											<input type="radio" name="RadioWrite" value="3" checked>
										</c:if> <c:if test="${brd_writeauth ne 3}">
											<input type="radio" name="RadioWrite" value="3">
										</c:if> 인증 회원
									</label> <label class="radio-inline"> <c:if
											test="${brd_writeauth eq 8}">
											<input type="radio" name="RadioWrite" value="8" checked>
										</c:if> <c:if test="${brd_writeauth ne 8}">
											<input type="radio" name="RadioWrite" value="8">
										</c:if> 관리자
									</label>
								</div>
								<div class="row">
									<div class="col-12 form-group">
										<label class="input-inline" for="brd_name">게시판명 : <input
											type="text" class="form-control form-control-alternative"
											id="brd_name" value="${brd_name }">
										</label>
										<c:if test="${brd_id eq null}">
											<input type="button" class="btn" onclick="makeBoard();"
												value="제출">
										</c:if>
										<c:if test="${brd_id ne null}">
											<input type="button" class="btn"
												onclick="updateBoard(${brd_id});" value="수정">
										</c:if>
									</div>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<script>
	function makeBoard() {
		var large_id = document.getElementById("large_List").value;
		var category_id = document.getElementById("category_List").value;
		var brd_readauth = $("input[name='RadioRead']:checked").val();
		var brd_writeauth = $("input[name='RadioWrite']:checked").val();
		var brd_name = document.getElementById("brd_name").value;

		if (large_id != null && category_id != null && brd_readauth != null
				&& brd_writeauth != null && brd_name != null) {
			doAjax(large_id, category_id, brd_readauth, brd_writeauth, brd_name);
		} else {
			alert("모든 항목을 기입하십시오");
		}

	}
	function doAjax(large_id, category_id, brd_readauth, brd_writeauth,
			brd_name) {
		var query = {
			"large_id" : large_id,
			"category_id" : category_id,
			"brd_readauth" : brd_readauth,
			"brd_writeauth" : brd_writeauth,
			"brd_name" : brd_name
		}
		$.ajax({
			async : false,
			url : "${contextPath}/admin/A_makeBoard",
			type : "post",
			data : query,
			success : function() {
				window.location.reload();
			}
		});
	}

	function updateBoard(brd_id) {
		var large_id = document.getElementById("large_List").value;
		var category_id = document.getElementById("category_List").value;
		var brd_readauth = $("input[name='RadioRead']:checked").val();
		var brd_writeauth = $("input[name='RadioWrite']:checked").val();
		var brd_name = document.getElementById("brd_name").value;

		if (large_id != null && large_id != "" && category_id != null&& category_id != "" && brd_readauth != null && brd_writeauth != null && brd_name != null) {
			updateAjax(brd_id, large_id, category_id, brd_readauth, brd_writeauth,brd_name);
		} else {
			alert("모든 항목을 기입하십시오");
		}

	}
	function updateAjax(brd_id, large_id, category_id, brd_readauth, brd_writeauth,brd_name) {
		var query = {
			"brd_id" : brd_id,
			"large_id" : large_id,
			"category_id" : category_id,
			"brd_readauth" : brd_readauth,
			"brd_writeauth" : brd_writeauth,
			"brd_name" : brd_name
		}
		
		$.ajax({
			async : false,
			url : "${contextPath}/admin/A_updateBoard",
			type : "post",
			data : query,
			success : function() {
				window.location.reload();
			}
		});
	}
	$(document).ready(function(){
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
</script>

<!-- end of body -->
