<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="bean" class="ch09.SimpleBean"/>
<!-- =SimpleBean bean = new SimpleBean();-->
<jsp:setProperty property="msg" name="bean"/>
<jsp:setProperty property="cnt" name="bean"/>

<!-- =String msg = request.getParameter("msg");
	int cnt = Integer.parseInt(request.getParameter("cnt"));
	bean.setMsg(msg);
	bean.setCnt(cnt); 랑 같다.  
	==<jsp:setProperty property="*" name="bean"/>
	-->
<h3>SimpleBean2</h3>
msg : <%=bean.getMsg() %><br>
cnt : <%=bean.getCnt()%><br>
msg : <jsp:getProperty property="msg" name="bean"/><br>
cnt : <jsp:getProperty property="cnt" name="bean"/><br>
