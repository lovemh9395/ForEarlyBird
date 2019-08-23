<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<div class="container-fluid mt--7">
	<div class="row">
		<div class="col-xl-8 mb-5 mb-xl-0">
			<div class="card bg-gradient-default shadow">
				<div class="card-header bg-transparent">
					<div class="row align-items-center">
						<div class="col">
							<h6 class="text-uppercase text-light ls-1 mb-1">Overview</h6>
							<h2 class="text-white mb-0">방문자 수</h2>
						</div>
						<div class="col">
							<ul class="nav nav-pills justify-content-end">
								<li class="nav-item mr-2 mr-md-0" data-toggle="chart"
									data-target="#chart-sales"
									data-update='{"data":{"datasets":[{"data":[0, 20, 10, 30, 15, 40, 20, 60, 60]}]}}'
									data-prefix="$" data-suffix="k"><a href="#"
									class="nav-link py-2 px-3 active" data-toggle="tab"> <span
										class="d-none d-md-block">Month</span> <span class="d-md-none">M</span>
								</a></li>
								<li class="nav-item" data-toggle="chart"
									data-target="#chart-sales"
									data-update='{"data":{"datasets":[{"data":[3, 16, 45, 70, 20, 20, 20, 40, 40]}]}}'
									data-prefix="$" data-suffix="k"><a href="#"
									class="nav-link py-2 px-3" data-toggle="tab"> <span
										class="d-none d-md-block">Week</span> <span class="d-md-none">W</span>
								</a></li>
							</ul>
						</div>
					</div>
				</div>
				<div class="card-body">
					<!-- Chart -->
					<div class="chart">
						<!-- Chart wrapper -->
						<canvas id="chart-sales" class="chart-canvas"></canvas>
					</div>
				</div>
			</div>
		</div>