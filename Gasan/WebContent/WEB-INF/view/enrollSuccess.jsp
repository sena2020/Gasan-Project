<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<% 
response.setHeader ("Cache-Control", "no-cache"); 
response.setHeader ("Pragma", "No-cache"); 
response.setDateHeader ("Expires", 0); 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>joinSuccess</title>
</head>
<body>

<script>alert('회원 가입에 성공했습니다. :D');  location.href='${pageContext.request.contextPath}/foodList'</script>
</body>
</html>

