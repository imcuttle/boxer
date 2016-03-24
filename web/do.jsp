<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="xyz.moyuyc.entity.User" %>
<%@ page import="xyz.moyuyc.entity.UserPoint" %>
<%@ page import="xyz.moyuyc.jdbc.UserADO" %>
<%@ page import="xyz.moyuyc.jdbc.UserPointADO" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="xyz.moyuyc.jdbc.UserMsgADO" %>
<%@ page import="xyz.moyuyc.entity.UserMsg" %>
<%--
  Created by IntelliJ IDEA.
  User: Yc
  Date: 2016/3/24
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  String act = request.getParameter("act");

  if(act.equals("login")){
    String username = request.getParameter("name");
    String pwd = request.getParameter("pwd");
    if(username.isEmpty()||pwd.isEmpty()){
      out.print("username or password is empty :(");
      return;
    }
    if(new UserADO().checkUser(new User(username,pwd))){
      session.setAttribute("username",username);
      out.print(1);
    }else{
      out.print("username or password isn't correct :(");
    }
  }else if(act.equals("register")){
    String username = request.getParameter("name");
    String pwd = request.getParameter("pwd");
    String pwd2 = request.getParameter("pwd2");
    if(username.isEmpty()||pwd.isEmpty()||pwd2.isEmpty()){
      out.print("username or password is empty :(");
      return;
    }
    if(!pwd.equals(pwd2)){
      out.print("passwords isn't same :(");
      return;
    }
    if(new UserADO().addUser(new User(username, pwd))){
      out.print("register success! :D");
    }else{
      out.print("username has already existed :(");
    }
  }else if(act.equals("uploadpoint")){
    int lev = Integer.parseInt(request.getParameter("lev"));
    int step = Integer.parseInt(request.getParameter("step"));
    String user = session.getAttribute("username").toString();
    UserPoint up = new UserPoint(user,lev,step);
    UserPointADO userPointADO = new UserPointADO();
    if(!userPointADO.addUserPoint(up)) {
      if(userPointADO.updateUserPoint(up)){
        JSONObject rlt = JSONObject.fromObject(up);
        rlt.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        out.print(rlt);
      }
    }else{
      JSONObject rlt = JSONObject.fromObject(up);
      rlt.put("date",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      out.print(rlt);
    }
  }else if(act.equals("exit")) {
    session.removeAttribute("username");
  }else if(act.equals("say")){
    String content = request.getParameter("content");
    String user = session.getAttribute("username").toString();
    UserMsg msg = new UserMsg(user,content);
    if(new UserMsgADO().addMsg(msg)){
      JSONObject jsonObject = JSONObject.fromObject(msg);
      jsonObject.put("time",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
      out.print(jsonObject);
    }
  }
%>
