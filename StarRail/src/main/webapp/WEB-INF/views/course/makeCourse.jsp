<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
	
	<!-- 코스 페이지 css -->
	<link rel="stylesheet" type="text/css" href="/starrail/resources/css/course/coursePage.css">
	
	
	
	<!-- 달력 ui -->
	<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
	<!-- 달력 js -->
	<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!-- 코스페이지 js -->
	<link type="text/javascript" href="/starrail/resources/js/course/course.js">
	



<title>Course</title>
</head>
<body>
	<div id="courseWrap">
		
		<!-- 여행 기간 설정 -->
		<div id="periodSetting">출발일 선택&nbsp;<input type="text" id="datepicker" size="10">&nbsp;&nbsp;
		</div>
		<!-- 여행 기간 설정 closing -->
		
		
		<!-- n일차 선택 -->
		<div id="dateSetting">dateSetting
			<!-- 여기에 동적으로 'n일차' 버튼 추가 -->
		</div>
		<!-- n일차 선택 closing -->
		
		
		<!-- 출발 도착역 선택 -->
		<div id="stationSetting">
		
			<!-- 출발역 리스트 -->
			<div class="arrivals">ARRIVALS
			</div>
			<!-- 출발역 리스트 closing -->
			
			<!-- 도착역 리스트 -->
			<div class="departures">departures
			</div>
			<!-- 도착역 리스트 -->
		
		</div>
		<!-- 출발 도착역 선택 closing -->
		
		
		<!-- 열차시간표 조회/선택, 지도 표시, 일정 리스트 출력 -->
		<div id = "courseSetting">
		
			<!-- 열차시간표 조회/선택 -->
			<div id="trainSetting">
				
				<!-- 출발 희망 시간 선택 -->
				<div class="hopingTime">
					<h4>출발 시간</h4>
					<select class="arrivingTime">
						<option>--------------</option>
					</select>
					&nbsp;시
				</div>
				<!-- 출발 희망 시간 선택 closing -->
				
				<!-- 열차 시간표 -->
				<h4 class="trainTimeTableTitle">탑승 가능 열차</h4>
				<div class="trainTimeTable">
					<table class="trainListTable">
						<thead>
							<tr>
								<th class="th1">열차 종류</th>
								<th class="th2">탑승 시간</th>
								<th class="th3">선택</th>
							</tr>
						</thead>
						<tbody>
							<!-- 여기에 동적으로 열차시간표 tr, td 추가 -->
						</tbody>
					</table>
				</div>
				<!-- 열차 시간표 closing -->
				
				<!-- 일정추가버튼영역 -->
				<div class="addingBtn">addingBtn
				</div>
				<!-- 일정추가버튼영역 closing -->
		
			</div>
			<!-- 열차시간표 조회/선택 closing -->
			
			<!-- 지도 표시 -->
			<div id="mapArea">mapArea
			</div>
			<!-- 지도 표시 closing -->
			
			<!-- 일정 리스트 -->
			<div id="couresDetailView">
				<h4 class="title">일정 세부</h4>
				<div class="uls">
					<h4 class="date" sDate="yyyy-MM-dd">yyyy-MM-dd</h4>
					<ul>
						<li>test</li>
					</ul>
					<h4 class="date" sDate="yyyy-MM-dd">yyyy-MM-dd</h4>
					<ul>
						<li>test</li>
					</ul>
				</div>
			</div>
			<!-- 일정 리스트 closing -->
			
		</div>
		<!-- 열차시간표 조회/선택, 지도 표시, 일정 리스트 출력 closing -->
		
		
		<!-- 발권역 선택 -->
		<div id="issueSetting">issueSetting
		</div>
		<!-- 발권역 선택 closing -->
		
		
		<!-- 전체 코스 저장 버튼 영역 -->
		<div id="allSavingBtn">allSavingBtn
		</div>
		<!-- 전체 코스 저장 버튼 영역 closing -->
	
	</div>
	<!-- courseWrap closing -->
</body>
</html>