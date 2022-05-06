<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글쓰기</title>
</head>
<body>




	<form action="write.do" method="post">
		<p>d
		제목 : <br/><input type="text" name="title" value="${param.title }">
		<c:if test="${errors.title }">제목을 입력하세요.</c:if>
		</p>
		<p>
		내용 : <br/>
		<textarea name="content" rows="5" cols="30">${param.content}</textarea>
		<c:if test="${errors.newPwd }">새 암호를 입력하세요.</c:if>
		</p>
		<input type="submit" value="새글 등록">
	</form>
</body>
</html>