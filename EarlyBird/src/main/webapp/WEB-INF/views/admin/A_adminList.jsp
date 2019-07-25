<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- body -->
<div class="modal fade" id="modal-admin" tabindex="-1" role="dialog"
	aria-labelledby="modal-form" aria-hidden="true">
	<div class="modal-dialog modal- modal-dialog-centered modal-lg"
		role="document">
		<div class="modal-content">
			<div class="modal-body p-0">
				<div class="card bg-secondary shadow border-0">
					<div class="card-header px-lg-20 py-lg-20">
						<h2>
							관리자설정 <small>ver1.0</small>
						</h2>
						<form role="form" id="adminMake" action="A_adminMake"
							method="post">
							<div class="form-group"
								style="padding: 1px; margin: 1px; border: 1px;">
								<div class="input-group">
									<select class="form-control" name="searchFilter"
										id="searchFilter">
										<option value="all">전체</option>
										<option value="id">아이디</option>
										<option value="nickname">닉네임</option>
									</select> <input class="form-control" placeholder="검색할 키워드 입력"
										type="text" id="searchAdmin" name="searchAdmin"> <input
										type="button" class="btn btn-default" value="검색">
								</div>
							</div>
						</form>
					</div>
					<div class="card-body px-lg-20 py-lg-20">
						<div class="table-responsive">
							<table class="table align-items-center table-dark table-flush">
								<thead class="thead-dark">
									<tr>
										<th scope="col">번호</th>
										<th scope="col">ID</th>
										<th scope="col">닉네임</th>
										<th scope="col">현재권한</th>
										<th scope="col">checkbox</th>
									</tr>
								</thead>
								<div class="row" style="width:750px">
									<div class="col-auto">현재 관리자</div>
									<div class="col-auto">admin</div>
									<div class="col-auto" style="width: 550px">
										<input type="button" style="float: right" class="btn btn-default" id="changebutton" value="변경">
									</div>
								</div>
								<tbody>
									<tr>
										<td>1</td>
										<td>admin@admin.admin</td>
										<td>admin</td>
										<td>관리자</td>
										<td><input type="checkbox" value="1"></td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- end of body -->