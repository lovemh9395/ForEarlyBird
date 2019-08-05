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
						<div class="row">
							<h2 class="col-11">
								관리자설정 <small>ver1.0</small>
							</h2>
							<div class="col-1" style="float: 'right'">
								<input type="button"
									style="float: 'right'; padding: 1px; margin: 1px; border: 1px;"
									class="btn btn-default" data-dismiss="modal"
									value="&nbsp;X&nbsp;">
							</div>
						</div>
						<div style="padding: 1px; margin: 1px; border: 1px;">
							<div class="input-group">
								<select name="searchFilter" id="searchFilter"
									style="width: 100px">
									<option value="mem_id">아이디</option>
									<option value="mem_nickname">닉네임</option>
									<option value="mem_levelname">권한</option>
								</select>&nbsp;&nbsp;&nbsp;<input type="text" placeholder="검색할 키워드 입력"
									id="searchMemberForAdmin">&nbsp;&nbsp;&nbsp;<input
									type="button" class="btn btn-default" value="검색">
							</div>
						</div>
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
								<div style="width: 746px" class="row">
									<div class="col-2">현재 관리자</div>
									<div class="col-2">admin</div>
									<div class="col-8">
										<input type="button" style="float: right"
											class="btn btn-default" id="changebutton" onclick="changeAdmin()" value="변경">
									</div>
								</div>
								<tbody>
									<c:forEach items="${adminList }" var="list">
										<tr>
											<td>${list.index }</td>
											<td>${list.mem_id }</td>
											<td>${list.mem_nickname }</td>
											<td>${list.mem_levelname }</td>
											<td><input type="checkbox" name="checkbox" data-dismiss="modal" value="${list.mem_id }"></td>
										</tr>
									</c:forEach>
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