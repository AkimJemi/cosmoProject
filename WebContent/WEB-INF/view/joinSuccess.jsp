<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.hidden_div{ display: none }
</style>
<meta charset="UTF-8">
<title>가입완료</title>
</head>
<body>
${param.name }님, 회원가입에 성공했습니다.
<div class="hidden_div">${ ctxPath = pageContext.request.contextPath }</div>
	<a href=" ${ctxPath}/index.jsp">[메인으로가기]</a>
</body>
</html>