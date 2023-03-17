<!-- ch05/scriptlet.jsp -->
<%@page import="java.util.Date"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%
		float f = 2.3f; //실수 앞이나 뒤에는 f를 붙여야함
		int a = Math.round(f);
		Date d = new Date();
%>
실수 f의 반올림: <%=a%><br>
날짜와 시간: <%=d%><br>
로컬타입의 날짜와 시간: <%=d.toLocaleString()%>