<!--  /ch05/declaration1.jsp -->
<%@page contentType="text/html; charset=UTF-8"%>
<%!
		int one;
		int two = 1;
		public int plus(){
			int three = one + two;
			return three;
		}
%>
<%
	int a;
	int b = 1;
	//int c = a + b;
%>
<%=plus()%>