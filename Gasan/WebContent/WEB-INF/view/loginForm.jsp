<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!-- c태그 이용을 위해 -->


<% 
response.setHeader ("Cache-Control", "no-cache"); 
response.setHeader ("Pragma", "No-cache"); 
response.setDateHeader ("Expires", 0); 
%>
<!DOCTYPE html>
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>loginForm</title>
<style type="text/css">
.container{margin: 200px;}
.col-md-12{padding-left: 300px;
		padding-right: 150px;
	}
</style>
</head>
<body>
	<article class="container">
		<div class="col-md-12">
			<div class="page-header">
				<h1>
					로그인 <small>Login</small>
				</h1>
			</div>
			<form class="form-horizontal" method="post">
			<c:if test="${errors.idOrPwNotMatch }">아이디와 비번이 일치하지 않습니다.</c:if>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="inputEmail">아이디</label>
					<div class="col-sm-4">
						<input class="form-control" type="text" name="loginId"
							value="${param.loginId }" placeholder="아이디">
						<c:if test="${errors.loginId }">
							<p class="help-block">ID를 입력하세요.</p>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-4 control-label" for="inputPassword">비밀번호</label>
					<div class="col-sm-4">
						<input class="form-control" type="password" name="password"
							value="${param.password }" placeholder="비밀번호">
						<c:if test="${errors.password }">
							<p class="help-block">비밀번호를 입력하세요.</p>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-12 text-center">
						<button class="btn btn-primary" type="submit">
							로그인<i class="fa fa-check spaceLeft"></i>
						</button>
						<button class="btn btn-danger" type="submit">
							돌아가기<i class="fa fa-times spaceLeft"></i>
						</button>
					</div>
				</div>
			</form>
			<hr>
		</div>
	</article>
</body>
</html>