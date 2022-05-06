<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<%
String readOnly = "readOnly";
if (request.getAttribute("readOnly") != null && (Boolean) request.getAttribute("readOnly") == false) {
	readOnly = "";
}
%>
<html>
<head>
<meta charset="UTF-8">
<title>수도세, 전기세 페이지</title>
<style>
.Container_Main {
	display: flex;
	height: 500px;
	width: 100%;
	text-align: center;
	justify-content: center;
	flex-direction: center;
	align-items: center;
}

.Container_Box_Main {
	font-size: 40px;
}

.Container_Box {
	display: flex;
	justify-content: space-between;
	margin: 5px;
}
</style>
<%
if (request.getAttribute("SubSuccess") != null) {
	String SubSuccess = (String) request.getAttribute("SubSuccess");
	if (SubSuccess.equals("insert")) {
%>
<script type="text/javascript">
alert( ${subReq.no } + "호실 추가 완료");
</script>
<%
} else if (SubSuccess.equals("update")) {
%>
<script type="text/javascript">
alert( ${sub.no } + "호실 수정 완료");
</script>
<%
}
}
%>
<%
if (request.getAttribute("NumExist") != null) {
%>
<script type="text/javascript">
alert( ${subReq.no } + "호실은 이미 존재합니다.");
</script>
<%
}
%>


</head>
<body>
	<c:if test="${errors.ele_ind  || errors.water_ind  ||errors.ele_basic ||errors.ele_ind  }">
		<script type="text/javascript">
			alert("빈 칸 없이 입력해주세요");
		</script>
	</c:if>
	<input type="hidden" value="${ ctxPath = pageContext.request.contextPath }" />
	${errors.no } || ${errors.water_ind } || ${errors.ele_basic } || ${errors.ele_ind } ||<%=request.getAttribute("readOnly")%>
	//
	<%=readOnly%>
	<form method="post" action="subRead.do">
		<div class="Container_Main">
			<div class="Container_Inner">
				<div class="Container_Box_Main">수도세</div>
				<div class="Container_Box">
					<div class="Container_label">호수 :</div>
					<input type="text" name="no" value="${subReq.no }" <%=readOnly%> />

				</div>
				<div class="Container_Box">
					<div class="Container_label">수도세 :</div>
					<input type="text" name="water_ind" value="${subReq.water_ind }" <%=readOnly%> />

				</div>
				<div class="Container_Box">
					<div class="Container_label">전기 기본세 :</div>
					<input type="text" name="ele_basic" value="${subReq.ele_basic }" <%=readOnly%> />

				</div>
				<div class="Container_Box">
					<div class="Container_label">전기 개인세 :</div>
					<input type="text" name="ele_ind" value="${subReq.ele_ind }" <%=readOnly%> />
				</div>
				<div class="Container_label">
					<%
					if (readOnly.equals("readOnly")) {
					%>
					<input type="button" value="추가 등록" onclick="location.href = 'subRead.do'" />
					<input type="button" value="수정" onclick="location.href = 'subModify.do?no=${subReq.no }'" />
					<%
					} else {
					%>
					<input type="submit" value="등록" />
					<%
					}
					%>
					<input type="button" value="돌아가기" onclick="location.href = 'subList.do'" />
				</div>
			</div>
		</div>
	</form>
</body>
</html>