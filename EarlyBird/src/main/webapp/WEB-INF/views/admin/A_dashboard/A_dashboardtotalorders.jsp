<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
		<div class="col-xl-4">
			<div class="card shadow">
				<div class="card-header bg-transparent">
					<div class="row align-items-center">
						<div class="col">
							<h6 class="text-uppercase text-muted ls-1 mb-1">Performance</h6>
							<h2 class="mb-0">Total orders</h2>
						</div>
					</div>
				</div>
				<div class="card-body">
					<!-- Chart -->
					<div class="chart">
						<canvas id="chart-orders" class="chart-canvas"></canvas>
					</div>
				</div>
			</div>
		</div>
	</div>