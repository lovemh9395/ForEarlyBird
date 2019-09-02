<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<script>
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
	function M_delete(index) {
			var result = confirm("댓글을 삭제 하시겠습니까?");

			if (result) {
				replydelete(index);
			}
	}
	
</script>
<div class="col-11" align="right">
	<button type="button" class="btn btn-sm btn-primary"
		onclick="M_delete(${status.current.rpl_id});">삭제</button>
</div>
