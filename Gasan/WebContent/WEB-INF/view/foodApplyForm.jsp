<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="enrollApply" method="post">

		<p>
			<input type="text" name="title" value="${param.title }"
				placeholder="제목">
			<c:if test="${errors.title }">제목을 입력하세요 :></c:if>
		</p>

		<p>
			<textarea rows="5" cols="30" name="content" placeholder="내용">${param.content}</textarea>
			<!--value가 아니라 사이에 값을 넣어야함. -->
		</p>

		<input type="submit" value="등록">
	</form>
</body>
</html>