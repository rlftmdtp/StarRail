$(function() {

	/* Prev, Next 버튼 이벤트 */
	$(document).ready(function() {
		$('#media').carousel({
			pause : true,
			interval : false,
		});
	});

	
	
	/* 썸네일 이미지 클릭시 대표 이미지 바뀜 + ajax로 일정 가져오고 버튼 생성 시작 */
	var dataIndex = null;
	var cd_id = null;
	var c_id = null;
	var cd_start = null;
	var cd_stime = null;
	var cd_end = null;
	var cd_etime = null;
	
	$("#thumnail a").click(
			function(e) {
				e.preventDefault();

				var largeImgPath = $(this).attr("href");
				var largeImgName = $(this).attr("data-name");
				var $c_id = $(this).attr("data");	


				// 대표 이미지를 바꾸고 코스 이름을 동적으로 바꿔준다
				$("#largeimg img").attr({
					src : largeImgPath
				});
				$("#courseName").text(largeImgName);
				
				// ajax사용해서 클릭한 썸네일에 해당하는 일정정보를 비동기로 가져오기
				$.ajax({
					url : "/starrail/partner/scheduleSearch",
					type : "post",
					data : {
						c_id : $c_id
					},
					dataType : "json",
					success : function(data) {
						/* $("#scedule-button").remove(); */
						if (data != null) {
							var str = "";
							var input = "";
							$("#scedule-button").empty();
							$("#scheduleTable tbody").empty();
							
							$(data).each(	function(index,	item) {
								/* 나중에 coursedetail정보 쓰기 위함 */
								dataIndex = index + 1;
								cd_id = this.cd_id;
								c_id = this.c_id;
								cd_start = this.cd_start;
								cd_stime = this.cd_stime;
								cd_end = this.cd_end;
								cd_etime = this.cd_etime;
								
								/* <button class="btn btn-danger">Danger</button>*/
								schedule = "<button  data-index = '"+dataIndex+"'class='btn btn-danger' value='' name='off' id='sDetail"+ dataIndex
												+ "' data='"
												+ this.cd_id
												+ "'>"
												+ (index + 1)
												+ "일차</button>&nbsp;&nbsp;&nbsp;";
								
								scheduleDetail = "<tr><td>"
														+ dataIndex
														+ "일차</td><td>"
														+ cd_start
														+ "</td><td>"
														+ cd_end
														+ "</td><td>"
														+ cd_stime.substring(0,	10)
														+ "</td></tr>";
								$(	"#scedule-button")	.append(schedule);
								$("#scheduleTable tbody").append(scheduleDetail);
								})	}
								else {
									alert("실패");
									}
								}
				});						
				
				
				
				var cd_id_List = [];
/*				 버튼 클릭 시 일정표의 색깔이 바뀜 시작 				
				 1일차 버튼 */
				$(document).on("click", "#sDetail1", function() {	
					if($(this).attr('name') == 'off'){ /*클릭 시 테이블 색상 변화주기*/
						$("tr:eq(1)").css({
							'background-color' : '#FFEAEA'
						});
						$(this).attr('name','on');
						$("#cd_idInput").attr('name','on');
						
					}else{                                /*클릭 취소시 색상변화 없애기*/
						$("tr:eq(1)").css({
							'background-color' : '#ffffff'
						});
						$(this).attr('name','off');    
						$("#cd_idInput").attr('name','off');    
					}					
				})
				
				 /*2일차 버튼 */
				$(document).on("click", "#sDetail2", function() {
					if($(this).attr('name') == 'off'){
						$("tr:eq(2)").css({
							'background-color' : '#FFEAEA'
						});
						$(this).attr('name','on');
					}
					else{
						$("tr:eq(2)").css({
							'background-color' : '#ffffff'
						});
						$(this).attr('name','off');
					}		
				})
				
				/* 3일차 버튼 */
				$(document).on("click", "#sDetail3", function() {
					if($(this).attr('name') == 'off'){
						$("tr:eq(3)").css({
							'background-color' : '#FFEAEA'
						});
						$(this).attr('name','on');
					}else{
						$("tr:eq(3)").css({
							'background-color' : '#ffffff'
						});
						$(this).attr('name','off');
					}		
				})
				
				 /*4일차 버튼 */
				$(document).on("click", "#sDetail4", function() {
					if($(this).attr('name') == 'off'){
						$("tr:eq(4)").css({
							'background-color' : '#FFEAEA'
						});
						$(this).attr('name','on');
					}else{
						$("tr:eq(4)").css({
							'background-color' : '#ffffff'
						});
						$(this).attr('name','off');
					}		
				})
				
				 /*5일차 버튼 */
				$(document).on("click", "#sDetail5", function() {
					if($(this).attr('name') == 'off'){
						$("tr:eq(5)").css({
							'background-color' : '#FFEAEA'
						});
						$(this).attr('name','on');
					}else{
						$("tr:eq(5)").css({
							'background-color' : '#ffffff'
						});
						$(this).attr('name','off');
					}		
				})
				
				 /*6일차 버튼 */
				$(document).on("click", "#sDetail6", function() {
					if($(this).attr('name') == 'off'){
						$("tr:eq(6)").css({
							'background-color' : '#FFEAEA'
						});
						$(this).attr('name','on');
					}else{
						$("tr:eq(6)").css({
							'background-color' : '#ffffff'
						});
						$(this).attr('name','off');
					}		
				})
				
				 /*7일차 버튼 */
				$(document).on("click", "#sDetail7", function() {
					if($(this).attr('name') == 'off'){
						$("tr:eq(7)").css({
							'background-color' : '#FFEAEA'
						});
						$(this).attr('name','on');
					}else{
						$("tr:eq(7)").css({
							'background-color' : '#ffffff'
						});
						$(this).attr('name','off');
					}		
				})
				/* 버튼 클릭 시 일정표의 색깔이 바뀜 끝 */
	});
	
	
	
	/* 동반자 찾기 검색버튼 클릭 시작 */
	   var list = new Object();
	   $("#partnerSearch").on('click', function () {
	      alert("동반자 찾기 검색 버튼 클릭");
	      
	       $('button[name=on]').each(function(index){
	         list["cd_id"+index] = $(this).attr('data');
	        // list.put("cd_idaa", "aa");
	    	  
	       });
	       alert(list.cd_id);
	       
	       //$.ajaxSettings.traditional = true;
	      $.ajax({
	         url : "/starrail/partner/partnerSearch",
	         type : "post",
	         headers:{
	            "ContentType" : "application/json",
	            "X-HTTP-Method-Override" : "POST"
	         },
	         data : {"list":list},
	         dataType : "text",         
	         success : function(data) {   
	            alert("dd");
	         }
	      });   
	      
	   })
	   /* 동반자 찾기 검색버튼 클릭 끝 */
	
	/* 썸네일 이미지 클릭시 대표 이미지 바뀜 + ajax로 일정 가져오고 버튼 생성 끝 */

})