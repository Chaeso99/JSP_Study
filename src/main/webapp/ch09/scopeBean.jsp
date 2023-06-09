<!-- ch09/scopeBean.jsp -->
<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="pbean" class="ch09.ScopeBean" scope="page"/>
<jsp:useBean id="sbean" class="ch09.ScopeBean" scope="session"/> 
<!-- 
	scope가 session이면 id가 동일한 객체가 있는지 없는지 검색
	-> 없으면 새롭게 생성하지만 
	-> 있으면 그 객체를 재사용
	-> 그러나 세션이 종료 또는 제거가 되면 안에 있는 객체도 같이 없어짐.
	-> scope="session" 밑에 코드가 많이 사용
 -->
 <% session.setAttribute("sbean", sbean); %>
 <jsp:setProperty property="num" name="pbean"
 value="<%=pbean.getNum()+10 %>"/>
  <jsp:setProperty property="num" name="sbean"
 value="<%=sbean.getNum()+10 %>"/>
 
 <h1>Scope Bean</h1>
 pbean num값 : <jsp:getProperty property="num" name="pbean"/><p>
 sbean num값 : <jsp:getProperty property="num" name="sbean"/><p>
 <a href="scopeBean2.jsp">세션종료</a>