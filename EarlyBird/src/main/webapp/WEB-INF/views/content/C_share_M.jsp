<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<!--   Core   -->
<%@ include file="../include/core.jsp"%>
<!--  end Core -->
<!-- head -->
<%@ include file="../include/head.jsp"%>
<!-- end head -->
<style>
#columns {
	column-width: 350px;
	column-gap: 15px;
}

#columns figure {
	display: inline-block;
	border: 1px solid rgba(0, 0, 0, 0.2);
	margin: 0;
	margin-bottom: 15px;
	padding: 10px;
	box-shadow: 2px 2px 5px rgba(0, 0, 0, 0.5);;
}

#columns figure img {
	width: 100%;
}

#columns figure figcaption {
	border-top: 1px solid rgba(0, 0, 0, 0.2);
	padding: 10px;
	margin-top: 11px;
}
</style>
<script>
	$(document).ready(function(){
		$("#C_share_M").click(function(){
			location.href="${contextPath}/content/C_share_make";
		})
	})
</script>
<body class="">
	<!-- side bar -->
	<%@ include file="../include/left_navbar.jsp"%>
	<!-- end side bar -->
	<div class="main-content">
		<!-- main_header -->
		<%@ include file="../include/main_navbar.jsp"%>
		<!-- end main header -->
		<!-- Header -->
		<%@ include file="../include/main_header.jsp"%>
		<!-- end Header -->
		<!-- body -->
		<br>
		<button type="button" class="btn btn-block btn-info" id="C_share_M">글 쓰기</button>
		<br>
		<div id="columns">
			<figure>
				<img
					src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/cinderella.jpg">
				<figcaption>Cinderella wearing European fashion of the
					mid-1860’s</figcaption>
			</figure>

			<figure>
				<img src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/rapunzel.jpg">
				<figcaption>Rapunzel, clothed in 1820’s period fashion</figcaption>
			</figure>

			<figure>
				<img src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/belle.jpg">
				<figcaption>Belle, based on 1770’s French court fashion</figcaption>
			</figure>

			<figure>
				<img src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/mulan_2.jpg">
				<figcaption>Mulan, based on the Ming Dynasty period</figcaption>
			</figure>

			<figure>
				<img
					src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/sleeping-beauty.jpg">
				<figcaption>Sleeping Beauty, based on European fashions
					in 1485</figcaption>
			</figure>

			<figure>
				<img
					src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/pocahontas_2.jpg">
				<figcaption>Pocahontas based on 17th century Powhatan
					costume</figcaption>
			</figure>

			<figure>
				<img
					src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/snow-white.jpg">
				<figcaption>Snow White, based on 16th century German
					fashion</figcaption>
			</figure>

			<figure>
				<img src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/ariel.jpg">
				<figcaption>Ariel wearing an evening gown of the 1890’s</figcaption>
			</figure>

			<figure>
				<img src="//s3-us-west-2.amazonaws.com/s.cdpn.io/4273/tiana.jpg">
				<figcaption>
					Tiana wearing the <i>robe de style</i> of the 1920’s
				</figcaption>
			</figure>
		</div>
		<!-- end body -->
	</div>
	<!--   Core   -->
	<%@ include file="../include/core.jsp"%>
	<!--  end Core -->
</body>
</html>