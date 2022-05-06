<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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

</head>
<body>
	<form method="post" action="subModify.do">
		<div class="Container_Main">
			<div class="Container_Inner">
				<div class="Container_Box_Main">수도세</div>
				<div class="Container_Box">
					<div class="Container_label">호수 :</div>
					<input type="text" name="no" value="${sub.no }" readonly />

				</div>
				<div class="Container_Box">
					<div class="Container_label">수도세 :</div>
					<input type="text" name="water_ind" value="${sub.water_ind }" />

				</div>
				<div class="Container_Box">
					<div class="Container_label">전기 기본세 :</div>
					<input type="text" name="ele_basic" value="${sub.ele_basic }" />

				</div>
				<div class="Container_Box">
					<div class="Container_label">전기 개인세 :</div>
					<input type="text" name="ele_ind" value="${sub.ele_ind }" />
				</div>
				<input type="hidden" value="${ ctxPath = pageContext.request.contextPath }" />
				<input type="submit" value="수정" />
				<input type="button" value="돌아가기" onclick="location.href = 'subList.do'" />
			</div>
		</div>
	</form>
</body>
</html>