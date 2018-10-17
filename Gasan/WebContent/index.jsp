<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>

<% 
response.setHeader ("Cache-Control", "no-cache"); 
response.setHeader ("Pragma", "No-cache"); 
response.setDateHeader ("Expires", 0); 
%>
<!DOCTYPE html>
<html>
<head>

<!-- Bootstrap core CSS -->
<link href="./Resource/vendor/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom styles for this template -->
<link href="./Resource/css/shop-homepage.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Gamja+Flower"
	rel="stylesheet">
<script type="text/javascript"
	src="https://openapi.map.naver.com/openapi/v3/maps.js?clientId=uyfB07lHyc4uqbvEv_XM&submodules=geocoder"></script>
<style type="text/css">
body {
	padding-top: 100px;
	font-family: 'Gamja Flower', cursive;
	font-size: 25px;
}

.carousel-item {
	height: 400px;
}

.main {
	flex-wrap: unset;
}

.navbar {
	height: 10%;
}

.container {
	max-width: 100%;
}

.carousel {
	margin-top: 0 !important;
	width: 100%;
}

.row {
	height: 100%;
}

.list-group {
	width: 80%;
}

.col-lg-3 {
	height: 1500px;
	max-width: 20%;
	margin-left: 20px;
	float: left;
}

.h-100 {
	height: 550px !important;
}

.food {
	width: 75%;
}

.apply {
	float: left;
	width: 75%;
}

.middle {
	width: 100%;
}

.card {
	width: 350px;
	float: left;
	margin-right: 15px;
	margin-top: 20px;
}

.title {
	float: left;
	padding-top: 100px;
	padding-bottom: 30px;
}

.keyword {
	color: gray;
}

a:link {
	text-decoration: none;
}

a:hover {
	color: black;
}

.cate {
	color: gray;
}

.applylist {
	color: gray;
}

.foodlist {
	float: right;
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

.d-block {
	margin-left: calc(50% - 560px);
}

.apply {
	margin-bottom: 60px;
}

.table {
	text-align: center;
}
</style>
<script type="text/javascript">
	/*function readApply(id) {
		location.href = 'readApply?no=' + id + '&pageNo=${articlePage.currentPage}';
	} */
	function deleteUser() {
		if(confirm("탈퇴 하시겠습니까?")) {
			if(confirm("정말?")) {
				if(confirm("한번 더 생각해봐")) {
					location.href = '${pageContext.request.contextPath}/deleteUser';
				}
			}
		}
	}
</script>
</head>

<body>

	<!--fix header  창 -->
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
		<div class="container">

			<!-- 로고 -->
			<div class="logo">
				<a href="${pageContext.request.contextPath}/foodList"><img
					class="logoimg" src="img/logo.png"></a>
			</div>

			<!-- 검색창 -->
			<form action="foodList" method="post">
				<div class="form-group">
					<input type="text" name="restName" size="85px;"
						class="form-control" placeholder="&nbsp;&nbsp;가게 이름 검색">
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
						<li class="nav-item"><a class="nav-link" style="cursor: pointer;" onclick="deleteUser()">Withdrawal&nbsp;&nbsp;</a></li>
						<li class="nav-item"><a class="nav-link" href="/Gasan/logout">Logout&nbsp;&nbsp;</a></li>
					</c:if>
				</ul>
			</div>

		</div>
	</nav>

	<!-- 메인페이지 -->
	<div class="container">

		<div class="row main">
			<div class="row">

				<!-- 메인페이지 메인사진 -->
				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active">
							<!-- <img class="d-block img-fluid" src="img/map.PNG"> -->

							<div id="map" class="d-block"
								style="width: 1078px; height: 400px;"></div>
							<script type="text/javascript">
								var mapOptions = {
									center : new naver.maps.LatLng(37.4804517,
											126.8828481),
									zoom : 12
								}

								var map = new naver.maps.Map('map', mapOptions);

								var marker = new naver.maps.Marker(
										{
											position : new naver.maps.LatLng(
													37.4805059, 126.8828481),
											map : map,
											icon : {
												url : './img/marker1-1.png', //50, 68 크기의 원본 이미지
												size : new naver.maps.Size(40,
														55),
												scaledSize : new naver.maps.Size(
														40, 55),
												origin : new naver.maps.Point(
														0, 0),
												anchor : new naver.maps.Point(
														18, 53)
											}
										});

								naver.maps.Event.addListener(map, 'click',
										function(e) {
											marker.setPosition(e.coord);
											markerX.value = marker.position.x;
											markerY.value = marker.position.y;
										});
							</script>
							<input type="hidden" id="markerX"> <input type="hidden"
								id="markerY">
						</div>
					</div>
				</div>

				<!-- 카테고리 -->
				<div class="col-lg-3">
					<h1 class="my-4">Category</h1>
					<div class="list-group">
						<c:if test="${param.category == '한식' }">
							<b><a style="color: black;" href="${pageContext.request.contextPath}/foodList?category=한식&page=1" class="list-group-item">Korean food</a></b>
						</c:if>
						<c:if test="${param.category != '한식' }">
							<a href="${pageContext.request.contextPath}/foodList?category=한식&page=1" class="list-group-item">Korean food</a>
						</c:if>
						<c:if test="${param.category == '양식' }">
							<b><a style="color: black;" href="${pageContext.request.contextPath}/foodList?category=양식&page=1" class="list-group-item">American food</a></b>
						</c:if>
						<c:if test="${param.category != '양식' }">
							<a href="${pageContext.request.contextPath}/foodList?category=양식&page=1" class="list-group-item">American food</a>
						</c:if>
						<c:if test="${param.category == '중식' }">
							<b><a style="color: black;" href="${pageContext.request.contextPath}/foodList?category=중식&page=1" class="list-group-item">Chinese food</a></b>
						</c:if>
						<c:if test="${param.category != '중식' }">
							<a href="${pageContext.request.contextPath}/foodList?category=중식&page=1" class="list-group-item">Chinese food</a>
						</c:if>
						<c:if test="${param.category == '일식' }">
							<b><a style="color: black;" href="${pageContext.request.contextPath}/foodList?category=일식&page=1" class="list-group-item">Japanese food</a></b>
						</c:if>
						<c:if test="${param.category != '일식' }">
							<a href="${pageContext.request.contextPath}/foodList?category=일식&page=1" class="list-group-item">Japanese food</a>
						</c:if>
						<c:if test="${param.category == '기타' }">
							<b><a style="color: black;" href="${pageContext.request.contextPath}/foodList?category=기타&page=1" class="list-group-item">ETC</a></b>
						</c:if>
						<c:if test="${param.category != '기타' }">
							<a href="${pageContext.request.contextPath}/foodList?category=기타&page=1" class="list-group-item">ETC</a>
						</c:if>
					</div>

					<!-- 공지 -->
					<div class="list-group">
						<img class="noticeimg" src="img/main notice.png">
					</div>
				</div>

				<!-- 음식점 -->
				<div class="food">
					<div class="titlefood">
						<h1>Restaurant</h1>
					</div>
					<c:if test="${pageContext.request.method == 'POST'}">
						<span style="color: magenta;">★검색어 : ${param.restName }★</span><br>
					</c:if>
					<c:if test="${pageContext.request.method == 'GET'}">
					<div class="cate">
						<c:if test="${param.order == 'id'}">
							<b><a class="keyword" style="color: black;" href="${pageContext.request.contextPath}/foodList?order=id&page=1&category=${param.category}">최근등록순</a></b>
						</c:if>
						<c:if test="${param.order != 'id'}">
							<a class="keyword" href="${pageContext.request.contextPath}/foodList?order=id&page=1&category=${param.category}">최근등록순</a>
						</c:if>
						&nbsp;&nbsp;|&nbsp;
						<c:if test="${param.order == 'reviews'}">
							<b><a class="keyword" style="color: black;" href="${pageContext.request.contextPath}/foodList?order=reviews&page=1&category=${param.category}">최다 리뷰순</a></b>
						</c:if>
						<c:if test="${param.order != 'reviews'}">
							<a class="keyword" href="${pageContext.request.contextPath}/foodList?order=reviews&page=1&category=${param.category}">최다 리뷰순</a>
						</c:if>
						&nbsp;&nbsp;|&nbsp; 
						<c:if test="${param.order == 'star'}">
							<b><a class="keyword" style="color: black;" href="${pageContext.request.contextPath}/foodList?order=star&page=1&category=${param.category}">별점 높은순</a></b>
						</c:if> 
						<c:if test="${param.order != 'star'}">
							<a class="keyword" href="${pageContext.request.contextPath}/foodList?order=star&page=1&category=${param.category}">별점 높은순</a>
						</c:if>
						&nbsp;&nbsp;|&nbsp;
						<c:if test="${param.order == 'views'}">
							<b><a class="keyword" style="color: black;" href="${pageContext.request.contextPath}/foodList?order=views&page=1&category=${param.category}">조회순</a></b>
						</c:if> 
						<c:if test="${param.order != 'views'}">
							<a class="keyword" href="${pageContext.request.contextPath}/foodList?order=views&page=1&category=${param.category}">조회순</a>
						</c:if>
					</div>
					</c:if>

					<!-- 음식 리스트 -->

					<c:if test="${foodPage.foodList.isEmpty()}">
						<img alt="" src="img/searchfailcuma.png" width="700px"
							style="margin-left: 200px; margin-top: 100px;">
					</c:if>
					<c:forEach var="food" items="${foodPage.foodList}">

						<div class="card h-100">
							<a
								href="${pageContext.request.contextPath}/foodView?no=${food.foodId}&page=1"><img
								class="card-img-top" src="poster/${food.restName}.jpg" alt=""></a>
							<div class="card-body">
								<h4 class="card-title">
									<a
										href="${pageContext.request.contextPath}/foodView?no=${food.foodId}&page=1">${food.restName}</a>
								</h4>
								<p class="card-text">
									주소 : ${food.addr }<br> 연락처 : ${food.tel }<br>
								</p>
							</div>
							<div class="card-footer">
								&#9733;
								<c:if test="${(food.scoreAvr*10)%10 == 0 }">
									<fmt:formatNumber value="${food.scoreAvr}" />
								</c:if>
								<c:if test="${(food.scoreAvr*10)%10 != 0 }">
									<fmt:formatNumber value="${food.scoreAvr}" pattern=".0" />
								</c:if>
							</div>
						</div>
					</c:forEach>
					
					<div
						style="clear: both; width: 75%; text-align: center; padding-top: 40px;">
						<c:forEach begin="${foodPage.startPage }" end="${foodPage.endPage }" var="page">
							<c:if test="${page != 0 }">
								<c:if test="${foodPage.currentPage == page }">
									<b><a href="${pageContext.request.contextPath}/foodList?order=${param.order }&page=${page }&category=${param.category}">${page }</a></b>
								</c:if>
								<c:if test="${foodPage.currentPage != page }">
									<a href="${pageContext.request.contextPath}/foodList?order=${param.order }&page=${page }&category=${param.category}">${page }</a>
								</c:if>
							</c:if>
							<c:if test="${page != foodPage.endPage }">&nbsp;|</c:if>
						</c:forEach>
					</div>
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
								<fmt:parseDate var="parseWdate" value="${wdateStr }"
									pattern="YYYY-MM-dd'T'HH:mm"></fmt:parseDate>
								<fmt:formatDate var="wdate" value="${parseWdate }"
									pattern="YYYY-MM-dd HH:mm"></fmt:formatDate>
								<tr>
									<td>${article.applyId }</td>
									<td>${article.loginId }</td>
									<td onclick="readApply(${article.applyId})">
									<u:pre var="title" value="${article.title }"/>
									<a href="readApply?no=${article.applyId }&pageNo=${articlePage.currentPage}">${title}</a></td>
									<td>${article.views }</td>
									<td>${wdate }</td>
								</tr>
							</c:forEach>
						</table>
						<div class="foodlist">
							<a class="applylist"
								href="${pageContext.request.contextPath}/listApply">더보기 ++</a>
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
	</footer>

	<!-- Bootstrap core JavaScript -->
	<script src="vendor/jquery/jquery.min.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>

</html>
