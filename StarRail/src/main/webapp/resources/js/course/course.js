$(function() {
		var tripDateStart; // 출발날짜 여행날짜 선택 함수에서 날짜를 고를때
		var tripDateEnd = new Date(); // 끝날짜
		var tripLong = document.getElementsByName("tripLong"); // 체크박스 전용 변수
		var lineCnt = 0;

		// 달력 UI (날짜 선택)
		$('#datepicker').datepicker({
			onSelect : function(dateText) {

				// 삭제
				initDelete();

				tripDateStart = new Date(dateText);

			},
			minDate : 0, // 이전 날짜 선택불가
			showOn : "button",
			buttonImage : "/starrail/resources/images/course/littlecalendar.PNG",
			buttonImageOnly : true,
			showAnim: "slideDown",
			dateFormat: 'yy-mm-dd',
		});

		// 5일권 7일권 선택
		$('.tripLong').click(function() {
			$('#beds-baths-group').empty();
				// 여행 날짜를 선택 안했을 경우 종료
				dateCheck();

				var day = parseInt($(this).val()); // val()는 문자열이기 떄문에 Date의
													// 요일과 더하기 위해서 Int로 변형
				// 날짜 더하기
				tripDateEnd.setDate(tripDateStart.getDate() + day-1);
				
				// 역 출발 일에 가능한 요일들을 추가
				var interval = tripDateEnd.getTime() - tripDateStart.getTime();
				interval = Math.floor(interval / (1000 *  60 * 60 * 24)); // 요일
																			// 간격
																			// 계산
					
				// Number객체를 -> 문자열로 변경후 -> 숫자자료형으로 변경한다
				interval.toString(); // 객체를 문자열로 변경
				for(var i=0; i<=parseInt(interval); i++){
					$('#beds-baths-group').append('<label class="btn btn-default beds-baths beds-baths-'+(i+1)+'">'
							+'<input type="radio" name="days" id="option'+(i+1)+'" autocomplete="off" value="' + (tripDateStart.getFullYear() + '/'+ (tripDateStart.getMonth()+1) + '/' + tripDateStart.getDate()) + '">'
							+'<span class="icon icon-blank-space"></span><span class="beds-baths-word">'
							+(i+1)+'일차</span></label><span class="beds-baths-clearfix"></span>');
					tripDateStart.setDate(tripDateStart.getDate() + 1);
				}
		});
		
		// n일차 선택 ----> 출발역 목록 불러오기
		$(document).on("click",".beds-baths",function(){
			$.ajax({
				type:'post',
				url:'/starrail/course/depList',
				headers: {"Content-type":"application/text"},
				
			
				success:function(result){
					
					$('.departures div.btn-group').empty();
					$.each(result, function(key, value){
						
						$('.departures div.btn-group').append('<label class="btn btn-default">'
								+'<input type="radio" name="depStation" value="'+value.id +'">'
								+'<span>'+value.name+'</span></label>');
						})
				}
			})
		});
		
		
		
		// 출발역 선택 -----> 도착역 목록 불러오기
		$(document).on("click",'.departures div.btn-group label.btn input[type="radio"]',function() {
			$('.arrivals div.btn-group').empty();
			var selectedDep = $(this).parent().find('span').text();
			
			$.ajax({
				type : 'post',
				url : '/starrail/course/arrList',
				dataType : 'json',
				data : {
					"depNode": $(this).val(),
					"depDate": $('input:radio[name=days]:checked').val()
				},
				contenttype : "application/json; charset=utf-8",
			
				success:function(result){
					
					if(result==''){
						alert('선택한 날짜에 '+selectedDep+'역에서 운행하는 노선이 없습니다.');
					} else {
						$.each(result, function(key, value){
							
							$('.arrivals div.btn-group').append('<label class="btn btn-default">'
									+'<input type="radio" name="arrStation" value="'+value.id +'">'
									+'<span>'+value.name+'</span></label>');
							})
					}
				}
			})
		});
		
		
		//도착역 선택----->출발 희망시간 출력
		$(document).on("click", '.arrivals div.btn-group label.btn input[type="radio"]', function(){

			
			var selectedDate = $('input:radio[name=days]:checked').val();
			selectedDate = selectedDate.replace(/\//g,"");
			
			var current = new Date();
			var currDate = current.getFullYear() + ''+(current.getMonth() + 1) + ''+current.getDate();
			
			var startTime = 0;
			
			if(selectedDate==currDate){
				startTime = current.getHours() +1;
			}
			
			for(var i=startTime; i<=24; i++){
				var valNum = '';
				if(i<10){ valNum = '0'+ i; }
				else { valNum = ''+ i; }
				
				$('.departTime').append('<option value="'+ i +'">'+ valNum +':00</option>');
			}


		});
		
		
		//출발 희망 시각 선택 ------> 시간표 출력
		$('.departTime').change(function(){
			$(this).val();
			
			$.ajax({
				type : 'post',
				url : '/starrail/course/trainTime',
				dataType : 'json',
				data : {
					"depNode": $('input:radio[name="depStation"]:checked').val(),
					"depDate": $('input:radio[name=days]:checked').val()
				},
				contenttype : "application/json; charset=utf-8",
			
				/*success:function(result){
					
					if(result==''){
						alert('선택한 날짜에 '+selectedDep+'역에서 운행하는 노선이 없습니다.');
					} else {
						$.each(result, function(key, value){
							
							$('.arrivals div.btn-group').append('<label class="btn btn-default">'
									+'<input type="radio" name="arrStation" value="'+value.id +'">'
									+'<span>'+value.name+'</span></label>');
							})
					}
				}*/
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
						// $('#possibleTime
						// .possibleTime'+index+'').append('<div
						// class="possibleTime" value="' + (item.ss_id)
						// +'"></div>');
					})
				}
			})
		});

		// 경로 저장 로직
		$('#lineSave').click(function() {

			var startStation = $('#startStation option:selected').val();
			var arriveStation = $('#arriveStation option:selected').val();
			var date = $('#startDate option:selected').val();
			var startTime = $('#possibleTime option:selected').val(); 
			

			$('#storeLine').append('<div><input type="text" value="'+ date + " " + startTime + " " + startStation+"역" + " " + arriveStation+"역" +'" name="storeLine">'
					+'<span class = "'+ lineCnt +'"> <img src="../../images/course_images/x.png"></span></div> '); // 경로
																													// 일부
																													// 삭제를
																													// 위해
																													// span
																													// 추가

			
			$('#lineCnt').attr('value',lineCnt); // val("값 입력") 은 input
													// type="text"만 가능한듯
													// "hidden" 은 옆과 같이 실행한다
			lineCnt++; //
		});
		
		// 경로 일부 삭제를 위해 span에 이벤트 걸기

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
			// 체크박스 해제
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
				
				// 체크박스 해제
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


