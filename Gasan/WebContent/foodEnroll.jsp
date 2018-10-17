<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
response.setHeader ("Pragma", "No-cache"); 
response.setDateHeader ("Expires", 0); 
response.setHeader ("Cache-Control", "no-cache"); 
%>
<jsp:useBean id="now" class="java.util.Date" />
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
	margin-left: 200px;
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

.btn {
	background-color: white;
	border: 1px solid;
}

.restview {
	border-top-left-radius: .25rem;
	border-top-right-radius: .25rem;
	margin-left: 100px;
	width: 70%;
	float: left;
	height: 1000px;
}

.resttitle {
	padding-bottom: 10px;
	border-bottom: 1px solid gray;
}

.reviewtitle {
	margin-top: 20px;
	margin-bottom: 20px;
	border-top: 1px solid gray;
	border-bottom: 1px solid gray;
}

.review {
	border-top-left-radius: .25rem;
	border-top-right-radius: .25rem;
	margin-top: 20px;
	margin-left: 100px;
	width: 70%;
	float: left;
	height: 800px;
	margin-bottom: 200px;
}

.reviewId {
	font-size: 25px;
}

.reviewDate {
	font-size: 25px;
	float: right;
}

.restimg {
	margin-top: 30px;
	margin-right: 30px;
	float: left;
	width: 695px;
	height: 395px;
}

.restmap {
	margin-top: 30px;
	margin-left: 50px;
}

.information {
	margin-top: 50px;
	font-size: 30px;
}

.reviewform {
	margin-left: 20px;
	margin-top: 20px;
	margin-bottom: 75px;
}

.writerName {
	width: 10%;
}

.content {
	width: 62%;
	word-break: break-all;
}

.star {
	width: 10%;
}

.wdate {
	width: 18%;
}
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
						<li class="nav-item"><a class="nav-link" href="/Gasan/logout">Logout&nbsp;&nbsp;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</nav>

	<!-- 음식점 정보 -->
	<div class="container">

		<div class="row main">
			<div class="row">

				<div class="total">
					<div class="restview">
						<div class="resttitle">
							<h1>${food.restName }&nbsp;&nbsp;&nbsp;&nbsp;${food.scoreAvr }
								<img src="img/star1.png">
							</h1>
						</div>
						<img class="restimg" src="poster/${food.restName }.jpg">
						<div id="map" class="restmap" style="width: 400px; height: 400px;"></div>

						<!-- 맵 -->
						<script type="text/javascript">
							var pointerX = '${food.pointerX}'
							var pointerY = '${food.pointerY}'
							var mapOptions = {
								center : new naver.maps.LatLng(pointerX,
										pointerY),
								zoom : 10
							}
							var map = new naver.maps.Map('map', mapOptions);

							var marker = new naver.maps.Marker({
								position : new naver.maps.LatLng(pointerX,
										pointerY),
								map : map,
								icon : {
									url : './img/marker1-1.png',
									size : new naver.maps.Size(40, 55),
									scaledSize : new naver.maps.Size(40, 55),
									origin : new naver.maps.Point(0, 0),
									anchor : new naver.maps.Point(18, 53)
								}
							});

							naver.maps.Event.addListener(map, 'click',
									function(e) {
										//marker.setPosition(e.coord);
										//markerX.value = marker.position.x;
										//markerY.value = marker.position.y;
									});
						</script>

						<div class="information">
							<table>
								<tr>
									<td>▶ 연락처 : ${food.tel }</td>
								</tr>
								<tr>
									<td>▶ 주소 : ${food.addr }</td>
								</tr>
								<tr>
									<td>▶ 사이트 주소 : <a href="${food.site}">[바로가기]</a>
									</td>
								</tr>
								<tr>
									<td>▶ 영업시간 : ${food.restHour }</td>
								</tr>
								<tr>
									<td>▶ 카테고리 : ${food.category }</td>
								</tr>
							</table>
							<a style="margin-top: 30px; margin-right : 50px; float: right; font-size: 20px;" href="${pageContext.request.contextPath}/foodList"><input type="button" value="목록"></a>
						</div>
					</div>
					<div class="review">
						<div class="reviewtitle">
							<h1>Review</h1>
						</div>

						<c:if test="${authUser != null }">
							<div class=reviewform>
								<form action="commentEnroll" method="post">
									<span class="reviewId">아이디 : ${authUser.loginId}</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type=hidden name=foodId value='${food.foodId}'>
									<input type=hidden name=score value=5>
									<div class="btn-group">
										<button type="button" class="btn btn-default dropdown-toggle"
											data-toggle="dropdown" aria-expanded="false">
											<img id="selectedImg" src="img/star5.png"> <span
												class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li onclick="changeImg(5)"><img src="img/star5.png"></li>
											<li onclick="changeImg(4)"><img src="img/star4.png"></li>
											<li onclick="changeImg(3)"><img src="img/star3.png"></li>
											<li onclick="changeImg(2)"><img src="img/star2.png"></li>
											<li onclick="changeImg(1)"><img src="img/star1.png"></li>
										</ul>
									</div>
									<script>
										function changeImg(number) {
											selectedImg.src = "img/star"
													+ number + ".png";
											document.getElementsByName("score")[0].value = number
										}
									</script>
									<span class="reviewDate"><fmt:formatDate value="${now}"
											pattern="yyyy-MM-dd" /></span> <br> <br>
									<textarea name="comm" cols="105" rows="5" placeholder="리뷰작성" style="resize: none;"></textarea>
									<br> <br>
									<p align="right">
										<input type="submit" value="등록">&nbsp; <input
											type="submit" value="취소">
									</p>
								</form>
							</div>
						</c:if>
						<div class="reviewtable">
							<table class="table table-hover table-striped">
								<tr>
									<td class="writerName">작성자</td>
									<td class="content">내용</td>
									<td class="star">별점</td>
									<td class="wdate">작성일</td>
								</tr>

								<c:forEach var="comment" items="${commentPage.commentList }">
									<tr>
										<td class="writerName">${comment.loginId }</td>
										<td class="content">${comment.comm }</td>
										<td class="star"><img src="img/star${comment.score}.png"></td>
										<td class="wdate">
									<c:set var="udateStr" value="${comment.udate.toString() }"></c:set>									
									<fmt:parseDate var="parseUdate" value="${udateStr }" pattern="YYYY-MM-dd'T'HH:mm"></fmt:parseDate>
									<fmt:formatDate var="udate" value="${parseUdate }" pattern="YYYY-MM-dd HH:mm"></fmt:formatDate>
									${udate}&nbsp;&nbsp;
										<c:if test="${comment.loginId == authUser.loginId }">
												<a
													href="${pageContext.request.contextPath }/commentDelete?commNo=${comment.commentId}&pageNo=${food.foodId}"
													style="color: red; float: right;">X</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
								<tr>
									<td colspan="4" style="text-align: center;">
										<c:forEach begin="${commentPage.startPage }" end="${commentPage.endPage }" var="page">
											<c:if test="${page != 0 }"><a href="${pageContext.request.contextPath}/foodView?no=${food.foodId }&page=${page }">${page }</a></c:if>
											<c:if test="${page != commentPage.endPage }">&nbsp;|</c:if>
										</c:forEach>
									</td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>

	<!-- Footer -->
<!-- 	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright</p>
		</div>
		/.container

	</footer>
 -->
 
	<!-- Bootstrap core JavaScript -->
	<script src="./Resource/vendor/jquery/jquery.min.js"></script>
	<script src="./Resource/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

</body>
</html>