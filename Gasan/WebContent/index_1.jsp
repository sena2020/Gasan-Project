<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index/회원게시판 만들기</title>
</head>
<body>
<!-- empty가 아닐 때 -->
<c:if test="${!empty authUser.nickname }">
${authUser.nickname}님, 안녕하세요.:D <a href="logout">[로그아웃하기]</a>
</c:if>

<c:if test="${empty authUser.nickname }">
<script>alert('로그아웃되었습니다.:D');</script> <!-- <a href="join">[회원가입하기]</a><a href="login">[로그인하기]</a> -->
</c:if>
</body>
</html>