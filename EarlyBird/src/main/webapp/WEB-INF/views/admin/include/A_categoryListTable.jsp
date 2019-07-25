<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row mt-5">
	<div class="col">
		<div class="card bg-default shadow">
			<div class="card-header bg-transparent border-0">
				<h3 class="text-white mb-0"><%=request.getParameter("category")%>
					목록
				</h3>
			</div>
			<div class="table-responsive">
				<table class="table align-items-center table-dark table-flush">
					<thead class="thead-dark">
						<tr>
							<th scope="col"><%=request.getParameter("category")%>코드</th>
							<th scope="col"><%=request.getParameter("category")%>명</th>
							<th scope="col"></th>
						</tr>
					</thead>
					<tbody>
						<!-- c:foreach 시작 -->
						<tr>
							<td>코드</td>
							<td>이름</td>
							<td class="text-right">
								<div class="dropdown">
									<a class="btn btn-sm btn-icon-only text-light" href="#"
										role="button" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> <i class="fas fa-ellipsis-v"></i>
									</a>
									<div
										class="dropdown-menu dropdown-menu-right dropdown-menu-arrow">
										<a class="dropdown-item" href="#">수정</a> <a
											class="dropdown-item" href="#">삭제</a>
									</div>
								</div>
							</td>
						</tr>
						<!-- c:foreach 끝 -->
					</tbody>
				</table>
			</div>
		</div>
	</div>
</div>