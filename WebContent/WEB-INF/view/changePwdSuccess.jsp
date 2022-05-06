<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.hidden_div{ display: none }
</style>
<meta charset="UTF-8">
<title>암호변경</title>
</head>
<body>
암호를 변경했습니다.
<div class="hidden_div">${ ctxPath = pageContext.request.contextPath }</div>
	<a href=" ${ctxPath}/index.jsp">[메인으로가기]</a>
	
</body>
</html>