<%@ page import="xyz.moyuyc.entity.UserMsg" %>
<%@ page import="xyz.moyuyc.entity.UserPoint" %>
<%@ page import="xyz.moyuyc.entity.UserPoints" %>
<%@ page import="xyz.moyuyc.jdbc.UserPointADO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<%@ page import="xyz.moyuyc.jdbc.UserMsgADO" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/3/24
  Time: 18:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  Object user = session.getAttribute("username");
  if(user==null){
    response.sendRedirect("register.jsp");
  }else{%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Boxer Game</title>
  <link rel="stylesheet" href="css/style.css">
  <script src="js/head_load.js"></script>
  <script src="js/game.js"></script>
</head>
<body>
<div class="container">
  <nav class="navbar navbar-default navbar-tall navbar-full" role="navigation">
    <div class="navbar-header">
      <a class="navbar-brand" href="/">Boxer Game</a>
    </div>
    <a class="navbar-brand pull-right text-primary" href="#">Game</a>
  </nav>
</div>
<div class="container">
  <label class="label label-default">Best Performance: </label>
  <label id="best" style="margin-left: 10px" class="text-danger">
  <%UserPointADO userPointADO = new UserPointADO();SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
  UserPoint userPoint = userPointADO.getUserPoints(user.toString());%>
  <%if(userPoint!=null){%>
  <span> Level: <%=userPoint.getPoint()%>, Step: <%=userPoint.getStep()%>, Time: <%=simpleDateFormat.format(userPoint.getDate())%></span>
  <%}%>
  </label>
  <div class="pull-right">
    <button data-toggle="modal" data-target="#howPlay" class="btn btn-success" >How to play?</button>
    <button id="btn-exit" class="btn btn-danger ">Exit</button>
  </div>
  <div class="modal fade" id="howPlay" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title" id="myModalLabel">How to Play?</h4>
        </div>
        <div class="modal-body">
          <div class="text-center"><img src="imgs/howplay.gif" /></div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="container-fluid" style="margin-top: 100px;margin-bottom: 100px">
  <div id="boxer-container"></div>
  <h4 class="text-center">Step: <span id="step" class="text-primary">0</span></h4>
</div>
<div class="container">
  <div class="col-md-6">
    <label class="label label-default">TopList</label>
    <%UserPoints userPoints = userPointADO.getTopUserPoints(10);
      for (int i = 0;i<userPoints.size();i++){
        userPoint = userPoints.get(i);
    %>
    <h5>#<%=i+1%> Name: <%=userPoint.getUsername()%>,Level: <%=userPoint.getPoint()%>, Step: <%=userPoint.getStep()%>, Date: <%=simpleDateFormat.format(userPoint.getDate())%></h5>
      <%}%>
  </div>
  <div class="col-md-6">
    <label class="label label-default">SaySomething</label>
    <div class="input-group input-group-md" style="margin:10px 0 10px;">
      <span class="input-group-addon" id="sizing-addon1"><%=user%></span>
      <input type="text" id="input-say" class="form-control" placeholder="SaySomething..." aria-describedby="sizing-addon1">
      <span class="input-group-btn">
        <button class="btn btn-default" id="btn-say" type="button">Say!</button>
      </span>
    </div>
    <div id="say-container">
    <%UserMsgADO userMsgADO = new UserMsgADO();
      List<UserMsg> msgs = userMsgADO.getAllMsgs();
      for (int i = 0;i<msgs.size();i++){
        UserMsg msg = msgs.get(i);
    %>
    <div><h5 style="display:inline-block;margin:0;margin-left: 2px;" class="text-primary"><%=msg.getUsername()%></h5><time style="vertical-align:middle;margin-left:20px; "><%=simpleDateFormat.format(msg.getTime())%>
    </time>

        <blockquote><%=msg.getContent()%></blockquote>

    </div>
    <%}%>
    </div>
  </div>
</div>

</body>
</html>
<%}%>