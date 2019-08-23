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
						<h2>게시판 추가하기</h2>
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
									<label class="radio-inline"> <input type="radio"
										name="RadioRead" value="1"> 비회원
									</label> <label class="radio-inline"> <input type="radio"
										name="RadioRead" value="2"> 미 인증 회원
									</label> <label class="radio-inline"> <input type="radio"
										name="RadioRead" value="3"> 인증 회원
									</label> <label class="radio-inline"> <input type="radio"
										name="RadioRead" value="8"> 관리자
									</label>
								</div>
								<div class="row">
									<p>쓰기 권한 →</p>
									<label class="radio-inline"> <input type="radio"
										name="RadioWrite" value="1"> 비회원
									</label> <label class="radio-inline"> <input type="radio"
										name="RadioWrite" value="2"> 미 인증 회원
									</label> <label class="radio-inline"> <input type="radio"
										name="RadioWrite" value="3"> 인증 회원
									</label> <label class="radio-inline"> <input type="radio"
										name="RadioWrite" value="8"> 관리자
									</label>
								</div>
								<div class="row">
									<div class="col-12 form-group">
										<label class="input-inline" for="brd_name">게시판명 : <input
											type="text" align="center"
											class="form-control form-control-alternative" id="brd_name">
										</label> <input type="button" class="btn" onclick="makeBoard();"
											value="제출">
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

		if (large_id!=null&&category_id!=null&&brd_readauth!=null&&brd_writeauth!=null&&brd_name!=null) {
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
			type : "GET",
			data : query,
			success : function() {
				window.location.reload();
			}
		});
	}
</script>

<!-- end of body -->