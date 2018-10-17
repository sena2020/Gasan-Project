<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- joinSucess와의 사용유무차이 무엇?? -->

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
<title>joinForm</title>
</head>
<body>
	<article class="container">
		<div class="col-md-12">
			<div class="page-header">
				<h1>
					회원가입 <small>Join Membership</small>
				</h1>
			</div>
			<form class="form-horizontal" method="post">
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputEmail">아이디</label>
					<div class="col-sm-6">
						<input class="form-control" type="text" name="loginId"
							value="${param.loginId }" placeholder="아이디">

						<c:if test="${errors.loginId }">
							<p class="help-block">ID를 입력하세요.</p>
						</c:if>
						<c:if test="${errors.duplicateId }">
							<p class="help-block">이미 사용중인 아이디입니다.</p>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputName">닉네임</label>
					<div class="col-sm-6">
						<input class="form-control" type="text" name="nickname"
							value="${param.nickname }" placeholder="닉네임">
						<c:if test="${errors.nickname }">
							<p class="help-block">이름을 입력하세요.</p>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputPassword">비밀번호</label>
					<div class="col-sm-6">
						<input class="form-control" type="password" name="password"
							value="${param.password }" placeholder="비밀번호">
						<c:if test="${errors.password }">
							<p class="help-block">비밀번호를 입력하세요.</p>
						</c:if>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label" for="inputPasswordCheck">비밀번호
						확인</label>
					<div class="col-sm-6">
						<input class="form-control" type="password" name="confirmPassword"
							value="${param.confirmPassword }" placeholder="비밀번호 확인">
						<c:if test="${errors.confirmPassword }">
							<p class="help-block">비밀번호 확인을 입력하세요.</p>
						</c:if>
						<!-- test가 true면 value를 실행 -->
						<c:if test="${errors.notMatch }">
							<p class="help-block">비밀번호가 확인과 일치하지 않습니다.</p>
						</c:if>
						<!-- JoinRequest에서 입력해준 키 값을 써주면 됨. -->
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-12 text-center">
						<button class="btn btn-primary" type="submit">
							회원가입<i class="fa fa-check spaceLeft"></i>
						</button>
						<button class="btn btn-danger" type="submit">
							가입취소<i class="fa fa-times spaceLeft"></i>
						</button>
					</div>
				</div>
			</form>
			<hr>
		</div>
	</article>
</body>
</html>