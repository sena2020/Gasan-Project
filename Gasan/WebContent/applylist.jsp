<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<% 
response.setHeader ("Pragma", "No-cache"); 
response.setDateHeader ("Expires", 0); 
response.setHeader ("Cache-Control", "no-cache"); 
%>
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
	margin-left: 60px;
	width: 1100px;
	float: left;
	margin-bottom: 200px;
	height: 800px;
}

.notice {
	border-top-left-radius: .25rem;
	border-top-right-radius: .25rem;
	margin-left: 30px;
	width: 380px;
	float: left;
	height: 400px;
}

.noticeimg {
	width: 90%;
}

.sub {
	
}
.table{text-align: center;}
.applyWrite{float: right;}

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
						<li class="nav-item" style="color: white; margin-top: 10px;"><small>[${authUser.nickname}
								님, 안녕하세요. :D]</small>&nbsp;&nbsp;</li>
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
				<img class=noticeimg src="img/notice.png" width="100%">
			</div>
			<div class="row">

				<div class="total">
					<div class="applyview">
						<!-- 신청 게시판 -->

						<div class="apply">
							<div class="title">
								<h1>신청 게시판</h1>
							</div>
							<table class="table table-hover table-striped">
								<tr>
									
								</tr>
								<tr>
									<th width="80px">번호</th>
									<th width="100px">작성자</th>
									<th width="500px">제목</th>
									<th>조회수</th>
									<th>작성일</th>
								</tr>
								<!-- 게시글이 없을 때 -->
								<c:if test="${!articlePage.hasArticles() }">
									<!-- 아티클페이지 객체 써줘야함!! -->
									<tr>
										<td colspan="5">게시글이 없습니다 :/</td>
									</tr>
								</c:if>
								<!-- 게시글이 있을 때 -->

								<c:forEach var="article" items="${articlePage.artList }">
									<c:set var="wdateStr" value="${article.wdate.toString() }"></c:set>									
									<fmt:parseDate var="parseWdate" value="${wdateStr }" pattern="YYYY-MM-dd'T'HH:mm"></fmt:parseDate>
									<fmt:formatDate var="wdate" value="${parseWdate }" pattern="YYYY-MM-dd HH:mm"></fmt:formatDate>
									<tr>
										<td>${article.applyId }</td>
										<td>${article.loginId }</td>
										<td><a href="readApply?no=${article.applyId }&pageNo=${articlePage.currentPage}">
											<u:pre value="${article.title}" var="title"/>
											${title }
											</a></td>
										<td>${article.views }</td>
										<td>${wdate }</td>
									</tr>
								</c:forEach>

								<!-- 게시글이 있다면 페이지블럭도 표시. -->

								<c:if test="${articlePage.hasArticles() }">
									<tr>

										<td colspan="5">
										<c:if test="${articlePage.startPage >5}">
												<!-- 스타트페이지가 5보다 크다는건 으에엑 다시  -->
												<a href="listApply?pageNo=${articlePage.startPage-5 }">[이전]</a>
											</c:if> <c:forEach var="pNo" begin="${articlePage.startPage }"
												end="${articlePage.endPage }">
												<c:if test="${pNo == articlePage.currentPage}">
													<b><a href="listApply?pageNo=${pNo }">[${pNo}]</a></b>
												</c:if>
												<c:if test="${pNo != articlePage.currentPage}">
													<a href="listApply?pageNo=${pNo }">[${pNo}]</a>
												</c:if>
											</c:forEach> <c:if test="${articlePage.endPage <articlePage.totalPages}">
												<a href="listApply?pageNo=${articlePage.startPage+5 }">[다음]</a>
										</c:if></td>
									</tr>

								</c:if>
							</table>
							<a class="applyWrite" href="${pageContext.request.contextPath}/enrollApply"><input type="button" value="게시글 작성"></a>
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