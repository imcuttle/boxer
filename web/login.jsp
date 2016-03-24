<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/3/24
  Time: 18:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Object user = session.getAttribute("username");
  if(user==null){%>
<jsp:include page="WEB-INF/t/login.html"></jsp:include>
<%}else{
  response.sendRedirect("game.jsp");
}%>
