<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>book_desc.jsp</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="content-type" content="text/html;charset=utf-8">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/adminjsps/admin/css/book/desc.css'/>">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/jquery/jquery.datepick.css'/>">
<script type="text/javascript"
	src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

<script type="text/javascript"
	src="<c:url value='/adminjsps/admin/js/book/desc.js'/>"></script>

<script type="text/javascript">

$(function() {
	$("#box").attr("checked", false);
	$("#formDiv").css("display", "none");
	$("#show").css("display", "");	
	
	// 操作和显示切换
	$("#box").click(function() {
		if($(this).attr("checked")) {
			$("#show").css("display", "none");
			$("#formDiv").css("display", "");
		} else {
			$("#formDiv").css("display", "none");
			$("#show").css("display", "");		
		}
	});
});

function setMethod(method){
	var ele = document.getElementById("method");
	ele.value = method;
}
</script>
</head>

<body>
	<div>
		<img src="<c:url value='/${book.image }'/>" border="0" />
	</div>
	${book }
	<form style="margin:20px;" id="form" action="<c:url value='/admin/AdminBookServlet'/>" method="post">
		<input type="hidden" name="method" id="method" /> 
		<input type="hidden" name="bid" value="${book.bid }"/>
		<input type="hidden" name="image" value="${book.image }"/>
		图书名称:<input	type="text" name="bname" value="${book.bname }" /><br /> 
		图书价格:<input type="text" name="price" value="${book.price }" />元<br /> 
		图书作者:<input type="text" name="author" value="${book.author }" /><br />
		图书分类:<select style="width:150px;height:20px;" name="cid">
			<c:forEach items="${categoryList }" var="c">
				<option value="${c.cid }"
					<c:if test="${c.cid eq book.category.cid }">selected="selected"</c:if>>${c.cname }
				</option>
			</c:forEach>

		</select> <br /> 
		<input type="submit" onclick="setMethod('delete')" value="删除" /> 
		<input type="submit" onclick="setMethod('edit')" value="编辑" />

	</form>
</body>
</html>
