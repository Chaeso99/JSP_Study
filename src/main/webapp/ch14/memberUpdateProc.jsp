<%@page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="mgr" class="ch14.MemberMgr"/>
<jsp:useBean id="bean" class="ch14.MemberBean"/>
<jsp:setProperty property="*" name="bean"/>
<%
		boolean result = mgr.updateMember(bean);
		if(result){
%>
		<script>
			alert("회원정보 수정 완료");
			location.href = "login.jsp";
		</script>
<%}else{%>
		<script>
			alert("회원정보 수정 실패");
			history.back();
		</script>
<%}%>