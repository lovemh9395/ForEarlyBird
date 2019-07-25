<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="include/A_header.jsp"%>
		<!-- body -->
		<div class="container">
			<h2>
				회원 관리<small>v1.0</small>
			</h2>
			<div class="panel panel-default">
				<div class="panel-heading">회원 정보 입력</div>
				<div class="panel-body">
					<form method="post" class="form-inline">
						<div class="form-group col-md-auto">
							<select class="form-control" name="skey" id="skey">
								<option value="all">전체</option>
								<option value="name">이메일</option>
								<option value="phone">이름</option>
								<option value="email">닉네임</option>
							</select>
						</div>
						<div class="form-group col-md-auto">
							<input type="text" class="form-control" id="svalue" name="svalue">
						</div>
						<button type="button" class="btn btn-default" onclick="">Search</button>
					</form>
				</div>
			</div>
			<div class="panel panel-default">
				<form action="" method="post">
					<div class="row">
						<div class="col-md-auto">회원 명단 출력</div>
						<div class="col-md-auto">
							
						</div>
					</div>
					<div class="panel-body">
						<%--테이블 태그 --%>
						<table class="table table-striped">
							<thead>
								<tr>
									<th>번호</th>
									<th>차단여부 <button type="button" class="btn btn-default" onclick="">일괄
								차단</button></th>
									<th>이름</th>
									<th>전화번호</th>
									<th>이메일</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>1</td>
									<td><input type="checkbox" name="chk_black" value="1"></td>
									<td>John</td>
									<td>Doe</td>
									<td>'the man'</td>
								</tr>
								<tr>
									<td>2</td>
									<td><input type="checkbox" name="chk_black" value="1"></td>
									<td>Mary</td>
									<td>Moe</td>
									<td>'the woman'</td>
								</tr>
								<%-- JSTL, EL을 이용한 동적 데이터 출력 --%>
								<c:forEach var="mem" items="${list}">
									<tr>
										<td>${mem.id}</td>
										<td><input type="checkbox" name="chk_black"
											checked="checked" value="${mem.id}"></td>
										<td>${mem.userid}</td>
										<td>${mem.username}</td>
										<td>${mem.nickname}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</form>
			</div>
		</div></div>
		<!-- end of body -->
<%@include file="include/A_footer.jsp"%>