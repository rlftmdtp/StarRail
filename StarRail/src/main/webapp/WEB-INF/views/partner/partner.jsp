<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Star Raile Partner</title>
<link
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans:400,700"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="/starrail/resources/bootstrap/css/bootstrap.css?ver=1">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/main/header_footer.css?ver=1">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/partner/partner.css?ver=1">

<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="/starrail/resources/bootstrap/js/bootstrap.js"></script>
<script src="/starrail/resources/js/partner/partner.js"></script>


<script type="text/javascript">
	
</script>

</head>
<body>


	<!-- body영역 시작 -->
	<div class="container">
		<div class="row">

			<!-- 코스이름 띄우기 시작 -->
			<div class='col-md-7'>
				<h2>My Course</h2>
				&nbsp;&nbsp;
				<h4 id="courseName">코스를 선택해 주세요</h4>
			</div>
			<!-- 코스 이름 띄우기 끝 -->
		</div>


		<!-- 썸네일 시작 -->
		<div class='row'>
			<div class='col-md-6'>
				<div class="carousel slide media-carousel" id="media">

					<!-- 대표 코스 이미지 시작 -->
					<div class="col-md-12" id="largeimg">
						<a class="thumbnail" href="#"><img alt=""
							src="/starrail/resources/images2/partner/map.PNG"
							style="width: 300px; height: 300px;"></a>
					</div>
					<!-- 대표 코스 이미지 끝 -->


					<!-- 썸네일 코스 이미지 시작 -->
					<div class="carousel-inner">

						<div class="item  active">
							<div class="row">

								<c:forEach items="${courseVO}" var="CourseVO">
									<div class="col-md-4" id="thumnail">
										<a class="thumbnail"
											href="/starrail/resources/images2/partner/test_map.PNG"
											data-name="${CourseVO.c_name}" data="${CourseVO.c_id}"> <img
											alt="" src="/starrail/resources/images2/partner/test_map.PNG">
											<span>${CourseVO.c_name}</span>
										</a>
									</div>
								</c:forEach>


								<!-- <div class="col-md-4">
	                <a class="thumbnail" href="#"><img alt="" src="http://placehold.it/150x150"></a>
	              </div>
	              
	              <div class="col-md-4">
	                <a class="thumbnail" href="#"><img alt="" src="http://placehold.it/150x150"></a>
	              </div>     -->

							</div>
						</div>
					</div>
					<!-- 썸네일 코스 이미지 끝 -->

					<!-- prev / next 버튼 -->
					<a data-slide="prev" href="#media" class="left carousel-control"
						style="margin-top: 360px;">‹</a> <a data-slide="next"
						href="#media" class="right carousel-control"
						style="margin-top: 360px;">›</a>
				</div>
			</div>



			<!-- 코스선택 시 나오는 일정 시작 -->
			<div class="col-md-6">
				<div class="panel panel-danger">
					<div class="panel-heading">
						<h3>My Schedule</h3>
					</div>

					<div class="panel-body">
						<div id="scedule-button"></div>
					</div>

					<div id="schedule_deatil">
						<form action="">
							<div id="courseDetailInfo">
								<input type="hidden" name="cd_id1" id="cd_id1">
								<input type="hidden" name="cd_id2" id="cd_id2">
								<input type="hidden" name="cd_id3" id="cd_id3">
								<input type="hidden" name="cd_id4" id="cd_id4">
								<input type="hidden" name="cd_id5" id="cd_id5">
								<input type="hidden" name="cd_id6" id="cd_id6">
								<input type="hidden" name="cd_id7" id="cd_id7">
							</div>
							
							<br>
							<table class="table" id="scheduleTable" width="200"
								cellpadding="5" cellspacing="2" align="center"
								style="table-layout: fixed; word-break: break-all;">
								<thead>
									<tr>
										<th>일정</th>
										<th>출발역</th>
										<th>도착역</th>
										<th>날짜</th>
									</tr>
								</thead>
								<tbody>
								</tbody>
								<tfoot>
									<tr><td colspan="4" align="right"><button type="submit" class="btn btn-danger" id="partnerSearch">동반자 검색</button></td></tr>
								</tfoot>
							</table>
						</form>
					</div>
				</div>


			</div>
			<!-- 코스선택 시 나오는 일정 끝 -->




		</div>
		<!-- 썸네일 끝 -->





		<!-- 일정 선택 시 동반자 리스트 출력하는 곳 시작 -->
		<div id="partner-Info"></div>
		<!-- 일정 선택 시 동반자 리스트 출력하는 곳 끝 -->

	</div>
	<!-- body영역 끝-->



</body>
</html>