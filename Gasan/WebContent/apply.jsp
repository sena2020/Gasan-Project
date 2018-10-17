<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Food enroll</title>

<!-- Bootstrap core CSS -->
<link href="./Resource/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./Resource/css/shop-homepage.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Gamja+Flower"
	rel="stylesheet">
<style type="text/css">
body {
	font-family: 'Gamja Flower', cursive;
	font-size: 25px;
}

.navbar {
	height: 10%;
}

.container {
	max-width: 100%;
}

.total {
	height: 100%;
}

.form-group {
	margin-left: 450px;
	margin-top: 3px;
	float: left;
	margin-bottom: 0rem;
}

.search-btn {
	margin-left: 10px;
	float: left;
}

.btn-primary {
	background-color: powderblue;
	color: black;
}

.applyview {
	border-top-left-radius: .25rem;
	border-top-right-radius: .25rem;
	margin-left: 420px;
	width: 75%;
	float: left;
	margin-bottom: 200px;
	height: 800px;
}

.applytitle {
	margin-top: 20px;
	margin-bottom: 70px;
	border-bottom: 1px solid gray;
}

.notice {
	position: fixed;
	border-top-left-radius: .25rem;
	border-top-right-radius: .25rem;
	margin-left: 30px;
	width: 380px;
	float: left;
	height: 350px;
}

.noticeimg {
	width: 90%;
}

.title {
	width: 700px;
	border-top-left-radius: .25rem;
	border-top-right-radius: .25rem;
}

.content {
	margin-top: 10px; border-top-left-radius : .25rem;
	border-top-right-radius: .25rem;
	border-top-left-radius: .25rem;
}

.sub {float: right; margin-right: 40px; font-size : 20px;}
.subcan {float: right; margin-right: 10px; font-size : 20px;}
</style>
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=uyfB07lHyc4uqbvEv_XM&submodules=geocoder"></script>
</head>
<body>
	<!-- fix 된 네비게이션 바 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">
			<!-- 로고 -->
			<div class="logo">
				<a href="${pageContext.request.contextPath}/foodList"><img class="logoimg" src="img/logo.png"></a>
			</div>

			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarResponsive" aria-controls="navbarResponsive"
				aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
						<!-- 검색창 -->
			<form action="foodList" method="post">
				<div class="form-group">
					<input type="text" name="restName" size="85px;" class="form-control"
						placeholder="&nbsp;&nbsp;가게 이름 검색">
				</div>
				<div class="search-btn">
					<input type="submit" class="btn btn-primary" value="search">
				</div>
			</form>
						<!-- 로그인 회원가입 -->
			<div class="collapse navbar-collapse" id="navbarResponsive">
				<ul class="navbar-nav ml-auto">

					<c:if test="${empty authUser.loginId }">
						<li class="nav-item"><a class="nav-link" href="/Gasan/login">Login&nbsp;&nbsp;</a></li>
						<li class="nav-item"><a class="nav-link" href="/Gasan/enroll">Join</a></li>
					</c:if>
					<c:if test="${!empty authUser.loginId }">
						<li class="nav-item" style="color: white; margin-top: 10px;"><small>[${authUser.nickname} 님, 안녕하세요. :D]</small>&nbsp;&nbsp;</li>
						<li class="nav-item"><a class="nav-link" href="/Gasan/logout">Logout&nbsp;&nbsp;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>
	<!-- 음식점 정보 -->
	<div class="container">

		<div class="row main">
			<div class="notice">
				<img class=noticeimg src="img/notice.png" width="100%" >
			</div>
			<div class="row">

				<div class="total">
					<div class="applyview">
						<div class="applytitle">
							<h1>신청게시판 작성</h1>
						</div>
						<div class="applyform">
						<form action="enrollApply" method="post">
							<p>
								제목 &nbsp;&nbsp; <input type="text" class="title" name="title"
									value="${param.title }"><br>
								<c:if test="${errors.title }">제목을 입력하세요.</c:if>
								<c:if test="${errors.titleSize }">제목을 20자 이하로 입력해주세요.</c:if>
							</p>

							<p>
								내용 <br>
								<textarea rows="13" cols="100" class="content" name="content" style="resize: none;">${param.content}</textarea>
								<!--value가 아니라 사이에 값을 넣어야함. -->
								<c:if test="${errors.content }">내용을 입력하세요.</c:if>
							</p>
								<a href="listApply?pageNo=1"><input type="button" value="목록" style="font-size: 20px;"></a>
								<input type="submit" class="sub" value="등록">
								<input class="subcan" type="button" value="취소">
							</form>
						</div>

					</div>
				</div>
			</div>

		</div>
	</div>
	<!-- Footer -->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright</p>
		</div>
		<!-- /.container -->

	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="./Resource/vendor/jquery/jquery.min.js"></script>
	<script src="./Resource/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>