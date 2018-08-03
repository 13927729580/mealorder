<%@ page language="java" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"> 
    
    <title>KY点餐系统后台管理</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
    <table width="80%" height="80%" align="center" border="0" cellpadding="0" cellspacing="0">
    	<thead>
    		<tr height="45px;">
    			<th style="padding-top: 10px;">
    				<!--<h3>管理</h3> 
    				<h2>KY点餐系统后台管理</h2>   -->
    				<img alt="KY点餐系统后台管理" src="res/img/top_logo.png"></img> 
    				<br>				
    				
    			</th>
    			<th >
    			    |<a href="<%=basePath %>orderLogManage/queryLogList" target="_blank">点菜管理</a>|
    				<a href="<%=basePath %>manageServlet/listOrder" target="_blank">菜单查询</a>|
    				<!-- 
    				<a href="<%=basePath %>manageServlet/listVideo" target="mainframe">视频查询</a>
    				 -->
					|<a href="addOrder.jsp" target="_blank">添加新菜</a>|
					<!-- <a href="addVideo.jsp" target="mainframe">添加视频</a> -->
				</th>
    		</tr>
    	</thead>
    	<tbody>
    		<tr>
    			<td colspan="2">&nbsp;
					<!-- 
					<iframe height="100%" width="98%" name="mainframe"></iframe>
					 -->
					 <img alt="kitchen" style="width:75%;height:75%"  src="res/img/kitchen.jpg"></img> 
				</td>
    		</tr>
    	</tbody>
    </table>
  </body>
  
</html>

