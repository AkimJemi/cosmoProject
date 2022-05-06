<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
성공
<%=request.getAttribute("subReq.no") %>
<%=request.getAttribute("subReq.ele_basic") %>
<%=request.getAttribute("subReq.ele_ind") %>
</body>
</html>