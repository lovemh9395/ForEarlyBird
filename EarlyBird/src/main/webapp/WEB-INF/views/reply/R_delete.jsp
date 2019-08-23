<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
	/* $(document).ready(function(){
		$("#deleteReply").click(function(){
			
			$.ajax({
				type:"post",
				url:"${contextPath}/reply/R_delete",
				success:function(data){
					alert(data);
				}
			})
		})
	}) */
	
	function replydelete(index){
		var post_id = <%=request.getParameter("post_id")%>;
		$.ajax({
			async:false,
			type:"post",
			url:"${contextPath}/reply/R_delete",
			data:{'rpl_id':index,
				  'post_id':post_id},
			success:function(data){
				$("#R_list").html(data);
			}
		})
	}
</script>
		<div class="col-11" align="right">
			<button type="button" class="btn btn-sm btn-primary" onclick = "replydelete(${status.current.rpl_id});">삭제</button>
		</div>
