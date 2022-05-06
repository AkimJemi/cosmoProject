<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.hidden_div{ display: none }
</style>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	게시글을 수정했습니다.
	<br>
	<div class="hidden_div">${ ctxPath = pageContext.request.contextPath }</div>
	<a href=" ${ctxPath}/article/list.do">[게시글 목록보기]</a>
	<a href=" ${ctxPath}/article/read.do?no=${modReq.articleNumber}">
	[게시글 내용보기]</a>
</body>
</html>