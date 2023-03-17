<!--  ch05/gugudan.jsp -->
<%@page contentType="text/html; charset=UTF-8"%>
<body>
<center>
<Style>
.text-gradient-pink-blue {
    background: -webkit-linear-gradient(-70deg, #db469f 0%, #2188ff 100%);
    -webkit-background-clip: text;
    background-clip: text;
    -webkit-text-fill-color: transparent;
    -webkit-box-decoration-break: clone;
    font-size:50px;
    font-wight:900;
    
}
</Style>
<span class="text-gradient-pink-blue">구구단</span>
</h1>
<table border="1" style = "font-size:20px">
	<%for(int i =0;i<10;i++){%>
	<tr align="center">
	    <%  
                    for (int j = 1; j < 10; j++) {
                        %>
		<%	if(i==0) 
		{
			%><th height = "40px" width = "80px" 
			bgcolor=lime font-size="10px"><%=j+"단"%></th> 
		<%
		} else { %>
		<td height = "40px" width = "80px" 
		 bgcolor=yellow align=center>
		 <%= j+"X"+i+"="+j*i %>
		 </td>
		<%                
		}
		%>
		<%                
		}
		%>
	</tr>
	<%
		 }
	 %>
</table>
</center>
</body>