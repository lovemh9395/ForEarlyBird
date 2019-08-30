<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
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
									<option value="mem_userid">아이디</option>
									<option value="mem_nickname">닉네임</option>
									<option value="mem_levelname">권한명</option>
								</select>&nbsp;&nbsp;&nbsp; <input type="text" placeholder="키워드 입력"
									id="searchkeyword" style="width: 200px">
								&nbsp;&nbsp;&nbsp;<input type="button" class="btn btn-default"
									onclick="searchMemberForAdmin(${brd_id});" data-dismiss="modal"
									data-backdrop="false" value="검색">
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
										<th scope="col"></th>
									</tr>
								</thead>
								<div style="width: 746px" class="row">
									<div class="col-2">현재 관리자</div>
									<div class="col-auto">
										<c:forEach items="${adminNicknames }" var="list">
											<c:out value="${list }  " />
										</c:forEach>
									</div>
								</div>
								<tbody>
									<c:forEach items="${adminList }" var="list" varStatus="status">
										<tr>
											<td>${status.count }</td>
											<td>${list.mem_userid }</td>
											<td>${list.mem_nickname }</td>
											<td>${list.mem_levelname }</td>
											<td><input type="button" style="float: right"
												class="btn btn-default"
												onclick="changeAdmin('${list.mem_userid}', ${brd_id});"
												data-backdrop="false" value="추가"></td>
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