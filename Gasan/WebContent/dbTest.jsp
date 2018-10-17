<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.Connection"%>
<%@page import="jdbc.connector.ConnectionProvider"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>dbTest</title>
</head>
<body>


디비 확인.
<%
try(Connection conn=ConnectionProvider.getConnection()){
	out.println("커넥션 성공");
}catch(SQLException e){
	out.println("커넥션실패"+e.getMessage());
}
%>


</body>
</html>