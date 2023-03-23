<!-- ch14/memberProc.jsp -->
<%@page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="mgr" class="ch14.MemberMgr"/>
<jsp:useBean id="bean" class="ch14.MemberBean"/>
<jsp:setProperty property="*" name = "bean"/>
<%
		//회원가입 진행, 실패하면 '가입실패' 알림, member.jsp 응답
		//성공하면 '가입성공' 알림 이후 login.jsp 응답(session에 id값 저장)
		boolean result = mgr.insertMember(bean);
		String msg = "가입실패";
		String url = "member.jsp";
		if(result)
		{
			msg = "가입성공";
			url = "login.jsp";
			//가입과 동시에 로그인 처리
			session.setAttribute("idKey", bean.getId());
		}
%>
<script>
alert("<%=msg%>");
location.href = "<%=url%>";
</script>