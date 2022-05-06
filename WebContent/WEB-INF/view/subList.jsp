<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<<<<<<< HEAD
=======




>>>>>>> be475a442771350ff55f0f6b9e89b56684a9a29c
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<<<<<<< HEAD
<title>Sub 리스트</title>
<style type="text/css">
.Containner_Main {
	display: flex;
	justify-content: center;
	text-align: center;
	align-items: center;
}
</style>
=======
<title>Insert title here</title>
>>>>>>> be475a442771350ff55f0f6b9e89b56684a9a29c
</head>
<script type="text/javascript">
	
<%if (request.getAttribute("DeleteResult") != null) {
	boolean DeleteResult = (boolean) request.getAttribute("DeleteResult");
	if (DeleteResult == true) {%>
	alert("삭제 성공하였습니다.")
<%} else if (DeleteResult == false) {%>
	alert("삭제 실패하였습니다.")
<%}
}%>
<<<<<<< HEAD
	function autoInsert(num) {
		alert(num);
		location.href = 'subInsert.do?num=' + num;
	}
	function autoInsert(num) {
		alert(num);
		location.href = 'subInsert.do?num=' + num;
	}
</script>
<body class="Containner_Main">
	<form action="subList.do" method="post">
		<table border='1' width="700">
			<tr>
				<td colspan="6" height="50px">
					<input type="button" value="등록" onclick="location.href = 'subRead.do'" />
					<a href="<%=request.getContextPath()%>/index.jsp">[ 목록으로 ]</a> <a href="javascript:void(0)" onclick="autoInsert(5)">[ 자동 추가5 ]</a> <a
						href="javascript:void(0)" onclick="autoInsert(10)">[ 자동 추가10 ]</a> <a href="javascript:void(0)" onclick="autoInsert(15)">[ 자동 추가15 ]</a>
				</td>
			</tr>
			<tr>
				<td colspan="6" height="50px">
					<c:set var="limitSetting" value="currentPage=${paging.currentPage}&startPage=${paging.startPage}" />
					<input type="hidden" name="limit" value="${paging.limit }" /> ${limitSetting }
					<input type="button" value="limit(5)" onclick="location.href = 'subList.do?limit=5&${limitSetting}'" />
					<input type="button" value="limit(10)" onclick="location.href = 'subList.do?limit=10&${limitSetting}'" />
					<input type="button" value="limit(15)" onclick="location.href = 'subList.do?limit=15&${limitSetting}'" />
					<input type="button" value="limit(20)" onclick="location.href = 'subList.do?limit=20&${limitSetting}'" />
				</td>
			</tr>
			<tr>
				<td colspan="6" height="50px">[ total : ${paging.total } , startPage : ${paging.startPage } , currentPage : ${paging.currentPage } , limit :
					${paging.limit } , endPage : ${ paging.endPage}] [ ${ search.search} ]</td>
			</tr>
			<%-- <a href="javascript:void(0)"
					onclick="autoInsert(10, '<%=request.getContextPath()%>', '${articlePage.currentPage }')">[ 자동 추가10 ]</a> <a href="javascript:void(0)"
					onclick="autoInsert(15, '<%=request.getContextPath()%>', '${articlePage.currentPage }')">[ 자동 추가15 ]</a>
		 --%>
			<tr>
				<td colspan="6" height="50px">
					<input type="text" name="search" style="width: 80%; height: 20px; margin: 5px;" value="${ search.search}" />
					<input type="submit" value="검색" width="10px" height="10px" style="width: 70px; height: 30px;" />
				</td>
			</tr>
			<tr>
				<td>호수</td>
				<td>수도세</td>
				<td>전기 기본세</td>
				<td>전기 개인세</td>
				<td>수정</td>
				<td>삭제</td>
			</tr>
			<c:if test="${articlePage.hasNoArticles() }">
				<tr>
					<td>${article.number }</td>
				</tr>
			</c:if>
			<c:forEach var="sub" items="${sub }">
				<tr>
					<td>
						<a href="subRead.do?no=${sub.no }">${sub.no }</a>
					</td>
					<td>
						<c:out value="${sub.water_ind }" />
					</td>
					<td>${sub.ele_basic }</td>
					<td>${sub.ele_ind }</td>
					<td>
						<input type="button" value="수정" onclick="location.href = 'subModify.do?no=${sub.no }'" />
					</td>
					<td>
						<input type="button" value="삭제" onclick="location.href = 'subDelete.do?no=${sub.no }'" />
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="6">
					<c:if test="${articlePage.startPage>5 }">
						<a href="list.do?pageNo=${articlePage.startPage -5 }"> [이전]</a>
					</c:if>
					<c:if test="${paging.total/paging.limit<=5 }">
						<c:forEach var="currentPage" begin="${paging.startPage }" end="${paging.total/paging.limit}">
							<a href="subList.do?currentPage=${ currentPage}&limit=${paging.limit }">[${currentPage }]</a>
						</c:forEach>
					</c:if>
					<c:if test="${paging.endPage>5 }">
						<a href="subList.do?startPage=${ paging.startPage-5}&limit=${paging.limit }&currentPage=${ paging.startPage -1}"> [이전]</a>
					</c:if>
					<c:if test="${paging.total/paging.limit>5 }">
						<c:forEach var="currentPage" begin="${paging.startPage }" end="${ paging.startPage +4 }">
							<a href="subList.do?currentPage=${ currentPage}&limit=${paging.limit }&startPage=${ paging.startPage}">[${currentPage }]</a>
						</c:forEach>
						<a href="subList.do?startPage=${ paging.endPage+1}&limit=${paging.limit }&currentPage=${ paging.endPage +1}">[다음]</a>
					</c:if>
				</td>
			</tr>
			<c:if test="${articlePage.hasArticles()}">
				<tr>
					<td colspan="4">
						<c:if test="${articlePage.startPage>5 }">
							<a href="list.do?pageNo=${articlePage.startPage -5 }"> [이전]</a>
						</c:if>
						<c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage }">
							<a href="list.do?pageNo=${ pNo}">[${pNo }]</a>
						</c:forEach>
						<c:if test="${articlePage.endPage <articlePage.totalPages }">
							<a href="list.do?pageNo=${articlePage.startPage +5 }">[다음]</a>
						</c:if>
					</td>
				</tr>
			</c:if>
		</table>
	</form>
=======
	
	
function autoInsert(num) {
	alert(num);
	location.href = 'subInsert.do?num='+num;
}
</script>
<body>
	<table border='1' width="700">
		<tr>
			<td colspan="6" height="50px">
				<input type="button" value="등록" onclick="location.href = 'subRead.do'" />
				<a href="<%=request.getContextPath()%>/index.jsp">[ 목록으로 ]</a> 
				<a href="javascript:void(0)" onclick="autoInsert(5)">[ 자동 추가5 ]</a> 
				<a href="javascript:void(0)" onclick="autoInsert(10)">[ 자동 추가10 ]</a> 
				<a href="javascript:void(0)" onclick="autoInsert(15)">[ 자동 추가15 ]</a> 
				<%-- <a href="javascript:void(0)"
					onclick="autoInsert(10, '<%=request.getContextPath()%>', '${articlePage.currentPage }')">[ 자동 추가10 ]</a> <a href="javascript:void(0)"
					onclick="autoInsert(15, '<%=request.getContextPath()%>', '${articlePage.currentPage }')">[ 자동 추가15 ]</a>
		 --%>
		 </tr>
		<tr >
			<td colspan="6"  height="50px" >
				<form action="subList.do" method="post">
					<input type="text" name="search" style="width:80%; height:20px; margin:5px;">
					<input type="submit" value="검색" width="10px" height="10px" style="width:70px; height:30px;">
				</form>
			</td>
		</tr>

		<tr>
			<td>호수</td>
			<td>수도세</td>
			<td>전기 기본세</td>
			<td>전기 개인세</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
		<c:if test="${articlePage.hasNoArticles() }">
			<tr>
				<td>${article.number }</td>
			</tr>
		</c:if>
		<c:forEach var="sub" items="${sub }">
			<tr>
				<td>
					<a href="subRead.do?no=${sub.no }">${sub.no }</a>
				</td>
				<%-- href="read.do?no=${article.number }&pageNo=${articlePage.currentPage}">${article.number } 		 --%>
				<td>
					<c:out value="${sub.water_ind }" />
				</td>
				<td>${sub.ele_basic }</td>
				<td>${sub.ele_ind }</td>
				<td>
					<input type="button" value="수정" onclick="location.href = 'subModify.do?no=${sub.no }'" />
				</td>
				<td>
					<input type="button" value="삭제" onclick="location.href = 'subDelete.do?no=${sub.no }'" />
				</td>
			</tr>
		</c:forEach>


		<c:if test="${articlePage.hasArticles()}">
			<tr>
				<td colspan="4">
					<c:if test="${articlePage.startPage>5 }">
						<a href="list.do?pageNo=${articlePage.startPage -5 }"> [이전]</a>
					</c:if>
					<c:forEach var="pNo" begin="${articlePage.startPage }" end="${articlePage.endPage }">
						<a href="list.do?pageNo=${ pNo}">[${pNo }]</a>
					</c:forEach>
					<c:if test="${articlePage.endPage <articlePage.totalPages }">
						<a href="list.do?pageNo=${articlePage.startPage +5 }">[다음]</a>
					</c:if>
				</td>
			</tr>
		</c:if>
	</table>


>>>>>>> be475a442771350ff55f0f6b9e89b56684a9a29c
</body>
</html>