<%@page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="mgr" class="ch16.PollMgr"/>
<jsp:useBean id="plBean" class="ch16.PollListBean"/>
<jsp:useBean id="piBean" class="ch16.PollItemBean"/>
<jsp:setProperty property="*" name="plBean"/>
<jsp:setProperty property="*" name="piBean"/>

<%
		boolean result = mgr.insertPoll(plBean, piBean);
		String msg = "설문추가 실패";
		String url = "poolInsert.jsp";
		if(result){
			msg = "설문추가 성공";
			url = "pollList.jsp";
		}
%>
<script>
	alert("<%=msg%>");
	location.href = "<%=url%>";
</script>