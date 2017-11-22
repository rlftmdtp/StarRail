$(function() {
		var tripDateStart; // 출발날짜 여행날짜 선택 함수에서 날짜를 고를때
		var tripDateEnd = new Date(); // 끝날짜
		var tripLong = document.getElementsByName("tripLong"); // 체크박스 전용 변수
		var lineCnt = 0;

		// 달력  UI
		$('#datepicker').datepicker({
			onSelect : function(dateText) {

				//삭제
				initDelete();

				tripDateStart = new Date(dateText);

			},
			minDate : 0, // 이전 날짜 선택불가
			showOn : "button",
			buttonImage : "../../images/course_images/littlecalendar.PNG",
			buttonImageOnly : true,
			showAnim: "slideDown",
			dateFormat: 'yy-mm-dd',
		});

		// 여행 지속 요일기간 선택
		$('.tripLong').click(function() {
					
					//여행 날짜를 선택 안했을 경우 종료
					dateCheck();

					var day = parseInt($(this).val()); // val()는 문자열이기 떄문에 Date의 요일과 더하기 위해서 Int로 변형
					//날짜 더하기
					tripDateEnd.setDate(tripDateStart.getDate() + day);

					// 역 출발 일에 가능한 요일들을 추가
					var interval = tripDateEnd.getTime() - tripDateStart.getTime();
					interval = Math.floor(interval / (1000 *  60 * 60 * 24)); // 요일 간격 계산
					
					// Number객체를 -> 문자열로 변경후 -> 숫자자료형으로 변경한다
					interval.toString(); // 객체를 문자열로 변경
					for(var i=0; i<=parseInt(interval); i++){
						$('#startDate').append('<option value="' + (tripDateStart.getFullYear() + "-" + (tripDateStart.getMonth()+1) + "-" + tripDateStart.getDate()) + '">' + (tripDateStart.getFullYear() + "년" + (tripDateStart.getMonth()+1) + "월" + tripDateStart.getDate() + "일") + '</option>');
						tripDateStart.setDate(tripDateStart.getDate() + 1);
					}
		})

		$('.startStation').click(
				function() {
					
					// 도착역이 뜨지 않도록 리턴값을 확인한다
					if(dateCheck()==false){
						return;
					}
					
					$.ajax({
						type : 'post',
						url : 'course_select.jsp',
						dataType : 'json',
						data : {
							"startStation" : $(this).val()
						},
						success : function(result) {
							$('#arriveStation').empty(); // 기존에 있던 도착역 리스트 삭제
							$.each(result, function(key, value) { // 도착역 추가
								$('#arriveStation').append(
										'<option class="arriveStation" value="'+ value +'">'
												+ value + '</option>');
							})
						}
					})
				});

		// 동적으로 생성된 태그는 이벤트가 인식하지 못하므로 부모에게 이벤트를 걸었음.
		$('#arriveStation')
				.on(
						'click',
						'.arriveStation',
						function() {
							var startStation = $(
									"#startStation option:selected").val();
							$.ajax({
								type : 'post',
								url : 'course_select.jsp',
								dataType : 'json',
								data : {
									"rstartStation" : startStation,
									"arriveStation" : $(this).val()
								},
								success : function(result) {
									$('#startTime').empty(); // 기존에 있던 시간 리스트 삭제
									$.each(result, function(idex, item) { // 도착역 추가
										$('#startTime').append(
												'<option class="startTime" value="'+ item +'">'
														+ item + '</option>');
									})
								}
							})
						});
		
		// 출발 가능한 역 시간 출력
		$('#startTime').on('click','.startTime',function(){
			var startTime = $('#startTime option:selected').val();
			var startStation = $("#startStation option:selected").val();
			var arriveStation = $("#arriveStation option:selected").val();
			$.ajax({
				type:'post',
				url: '../../jsp/course_jsp/course_select.jsp',
				dataType: 'json',
				data : {
					"tt_startStation" : startStation,
					"tt_arriveStation" : arriveStation,	
					"startTime" : startTime},
				
				success : function(result){
					$('#possibleTime').empty();
					$.each(result, function(index,item){
						$('#possibleTime').append('<option class="possibleTime" value="' + (item.tr_id+ item.tt_stime + item.ss_id) +'">' + (item.tr_id+ item.tt_stime) +'</option>');
						//$('#possibleTime .possibleTime'+index+'').append('<div class="possibleTime" value="' + (item.ss_id) +'"></div>');
					})
				}
			})
		});

		//경로 저장 로직
		$('#lineSave').click(function() {

			var startStation = $('#startStation option:selected').val();
			var arriveStation = $('#arriveStation option:selected').val();
			var date = $('#startDate option:selected').val();
			var startTime = $('#possibleTime option:selected').val(); 
			

			$('#storeLine').append('<div><input type="text" value="'+ date + " " + startTime + " " + startStation+"역" + " " + arriveStation+"역" +'" name="storeLine">'
					+'<span class = "'+ lineCnt +'"> <img src="../../images/course_images/x.png"></span></div> '); //경로 일부 삭제를 위해 span 추가

			
			$('#lineCnt').attr('value',lineCnt); // val("값 입력") 은 input type="text"만 가능한듯 "hidden" 은 옆과 같이 실행한다
			lineCnt++; //
		});
		
		//경로 일부 삭제를 위해 span에 이벤트 걸기

		$('#storeLine').on('click', 'span', function(){
			var num = $(this).attr('class');
			$(this).prev().remove();
			$(this).detach();

			lineCnt--;
		})

		// 발권역 정보보기 로직
		$('#issueinfo').click(
				function() {
					
					// 배열로 ajax넘기기
					var issueStations =[];
					$('input[name="storeLine"]').each(function(i){
						issueStations.push($(this).val());
					})

					var benefit = document.getElementById('benefit');
					benefit.value = "";
					$.ajax({
						type : 'post',
						url : 'course_select.jsp',
						dataType : 'json',
						data : {

							"issueStations" : issueStations

						},
						success : function(result) {
							$.each(result, function(index, item) {
								benefit.value += "발권역 " + item.station + " 혜택 "
										+ item.benefit + '\n';
							})
						}
					})
				});
		
		function initDelete(){
			//체크박스 해제
			for (var i = 0; i < tripLong.length; i++) {
				tripLong[i].checked = false;
			}

			$('#startDate').empty(); // 자식을 모두 삭제한다
			$('#arriveStation').empty();
			$('#startTime').empty();
			$('#possibleTime').empty();
		}
		
		function dateCheck(){
			if(tripDateStart == null){
				alert("여행 날짜를 선택하세요");
				
				//체크박스 해제
				for (var i = 0; i < tripLong.length; i++) {
					tripLong[i].checked = false;
				}
				return false;
			}
			else{
				return true;
			}
		}
});


