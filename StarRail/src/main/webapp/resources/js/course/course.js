$(function() {
		var lineCnt = 0;
		var check=false;
		var setStartDay;
		
		// 달력 UI (날짜 선택)
		$('#datepicker').datepicker({
			onSelect : function(dateText) {
				
				// 삭제
				$('#beds-baths-group').empty();	//n일차 버튼 비우기
				$('.departures div.btn-group').empty();	//출발역 목록 비우기
				$('.arrivals div.btn-group').empty();	//도착역 목록 비우기
				$('.trainListTable tbody').empty();	//열차 시간표 비우기
				$('.departTime').empty();	//출발희망시간 비우기
				$('.departTime').append('<option>--------------</option>');	//출발희망시간 디폴트옵션 재추가
				$('.addingBtn .btn-outline-success').prop('disabled', true);	//일정추가버튼 비활성화

				setStartDay = new Date(dateText);
				$('input[name="tripLong"]').removeAttr('disabled');
				$('input[name="tripLong"]').prop('checked', false);
				

			},
			minDate : 0, // 이전 날짜 선택불가
			showOn : "button",
			buttonImage : "/starrail/resources/images/course/littlecalendar.PNG",
			buttonImageOnly : true,
			showAnim: "slideDown",
			dateFormat: 'yy-mm-dd',
		});

		// 5일권 7일권 선택
		
		$('.tripLong').click(function(){

			$('#beds-baths-group').empty();	//n일차 버튼 비우기
			$('.departures div.btn-group').empty();	//출발역 목록 비우기
			$('.arrivals div.btn-group').empty();	//도착역 목록 비우기
			$('.trainListTable tbody').empty();	//열차 시간표 비우기
			$('.departTime').empty();	//출발희망시간 비우기
			$('.departTime').append('<option>--------------</option>');	//출발희망시간 디폴트옵션 재추가
			$('.addingBtn .btn-outline-success').prop('disabled', true);	//일정추가버튼 비활성화
			
			var startDay = new Date();
			
			var strArr = $('#datepicker').val().split('-');
			startDay.setDate(strArr[2],Number(strArr[1])-1,strArr[0]);
			
			
			var endDay = new Date();
			endDay.setDate(Number(strArr[2])+Number($(this).val())-1,Number(strArr[1])-1,strArr[0]);
			
			var interval = endDay.getTime() - startDay.getTime();
			interval = Math.floor(interval / (1000 *  60 * 60 * 24));
			interval.toString();
		
			for(var i=0; i<=parseInt(interval); i++){
				
				$('#beds-baths-group').append('<label class="btn btn-default beds-baths beds-baths-'+(i+1)+'">'
						+'<input type="radio" name="days" id="option'+(i+1)+'" autocomplete="off" value="' + (startDay.getFullYear() + '/'+ (startDay.getMonth()+1) + '/' + startDay.getDate()) + '">'
						+'<span class="icon icon-blank-space"></span><span class="beds-baths-word">'
						+(i+1)+'일차</span></label><span class="beds-baths-clearfix"></span>');
				startDay.setDate(startDay.getDate() + 1);
			}
			
		});

		
		// n일차 선택 ----> 출발역 목록 불러오기
		$(document).on("click",".beds-baths",function(){
			$('.departures div.btn-group').empty();	//출발역 목록 비우기
			$('.arrivals div.btn-group').empty();	//도착역 목록 비우기
			$('.trainListTable tbody').empty();	//열차 시간표 비우기
			$('.departTime').empty();	//출발희망시간 비우기
			$('.departTime').append('<option>--------------</option>');	//출발희망시간 디폴트옵션 재추가
			$('.addingBtn .btn-outline-success').prop('disabled', true);	//일정추가버튼 비활성화
			
			
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
			
			$('.arrivals div.btn-group').empty();	//도착역 목록 비우기
			$('.trainListTable tbody').empty();	//열차 시간표 비우기
			$('.departTime').empty();	//출발희망시간 비우기
			$('.departTime').append('<option>--------------</option>');	//출발희망시간 디폴트옵션 재추가
			$('.addingBtn .btn-outline-success').prop('disabled', true);	//일정추가버튼 비활성화
			
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
			
			$('.trainListTable tbody').empty();	//열차 시간표 비우기
			$('.departTime').empty();	//출발희망시간 비우기
			$('.departTime').append('<option>--------------</option>');	//출발희망시간 디폴트옵션 재추가
			$('.addingBtn .btn-outline-success').prop('disabled',true);	//일정추가버튼 비활성화
			
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
		$('.departTime').click(function(){
			
			
			$('.trainListTable tbody').empty();	//열차 시간표 비우기
			$('.addingBtn .btn-outline-success').prop('disabled', true);	//일정추가버튼 비활성화
			
			
			$('.trainListTable tbody').empty();
			$.ajax({
				type : 'post',
				url : '/starrail/course/trainTime',
				dataType : 'json',
				data : {
					"depNode": $('input:radio[name="depStation"]:checked').val(),	//출발역ID
					"depDate": $('input:radio[name=days]:checked').val(),	//출발일
					"arrNode": $('input:radio[name="arrStation"]:checked').val(),	//도착역ID
					"selectedTime": $('.departTime option:selected').val()
				},
				contenttype : "application/json; charset=utf-8",
			
				success:function(result){
					
					if(result==''){
						alert('선택한 시간에 운행하는 노선이 없습니다.');
					} else {
						$.each(result, function(key, value){
							
							$('.trainListTable tbody').append('<tr>'+'<td>'+ value.trainType +'</td>'
																	+'<td class="SelDepTime">'+ value.depTime+'</td>'
																	+'<td><input type="radio" name="selectedTrain" value="'+value.arrTime+'"></td>'
																	+'</tr>');
						})
					}
				}
			})
			
		});
		
		
		//탑승 열차 선택 -----> 일정 추가 버튼 활성화
		$(document).on('click', 'input:radio[name="selectedTrain"]', function(){
			$('.addingBtn .btn-outline-success').removeAttr('disabled');
			
		});
		
		
		//일정 추가 버튼 클릭 -----> 일정 세부에 추가
		$('button.addBtn').click(function(){
			var selectedDate = $('input:radio[name=days]:checked').val();	//선택한 날짜(n일차)
			selectedDate = selectedDate.replace(/\//g,"-");	//yyyy-MM-dd 형태로
			var selectedDep =$('input:radio[name="depStation"]:checked').parent().find('span').text();	//선택한 출발역
			var selectedArr = $('input:radio[name="arrStation"]:checked').parent().find('span').text();	//선택한 도착역
			var selectedDepTime = $('input:radio[name="selectedTrain"]:checked').parent().prev().text();	//선택한 열차의 출발시간
			var selectedArrTime = $('input:radio[name="selectedTrain"]:checked').val();	//선택한 열차의 도착시간
			
			if($('h4[sDate="'+ selectedDate +'"]').length<=0){
				$('#couresDetailView .uls')
				.append('<h4 class="date" sDate="'+selectedDate+'">'+selectedDate+'</h4><ul name="'+ selectedDate +'"></ul>');
			}
			
			$('ul[name="'+ selectedDate +'"]')
			.append('<li>'+selectedDep+'('+selectedDepTime+') -->'+ selectedArr +'('+ selectedArrTime +')&emsp;&emsp;'
					+'<span class="delSchedule"><img src="/starrail/resources/images/course/x.png"></span></li>');
			
		});
		
		//일정 일부 삭제 (x 버튼 클릭 ----> 삭제)
		$(document).on('click', 'span[class="delSchedule"]', function(){
			
			var thisDate = $(this).parent().parent().attr('name');
			
			if($('ul[name="'+ thisDate +'"] > li').length<=1){
				$('ul[name="'+ thisDate +'"]').prev().remove();
				$('ul[name="'+ thisDate +'"]').remove();
			} else {
				$(this).parent().remove();
			}
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
		

		
		function dateCheck(){
			if(tripDateStart == null){
				alert("여행 날짜를 선택하세요");
				return false;
				
			}
			else{
				return true;
			}
		}
});


