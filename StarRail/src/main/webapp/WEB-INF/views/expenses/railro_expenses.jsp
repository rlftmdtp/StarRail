<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/main/header_footer.css">
<link rel='stylesheet prefetch'
	href='http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css'>
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/expenses/expenses.css">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

	<br>
	<div class="container">
		<div class="row">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">여행 경비 관리</h3>
					<span class="pull-right"> <!-- Tabs -->
						<ul class="nav panel-tabs">
							

						</ul>
					</span>
				</div>
				<div class="panel-body">
					<div class="tab-content">
						<form action="railro_expenses" method="POST">
							<div class="input-group">
						  		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-book" aria-hidden="true"></span></span>
						 		<input type="text" class="form-control" id="e_title" name="e_title" placeholder="제목" aria-describedby="basic-addon1">
							</div> 
							
							<div class="input-group"  id="calanderimg">
						 		<input type="text" id="datepicker" name="e_sdate"  placeholder="출발일" >
						 		
							</div>
							
							
							<div class="input-group">
						  		<input id="checkboxId" type="checkbox" name="tripLong" class="tripLong" value="5" onclick="oneclick(this)">5일권
								<input id="checkboxId" type="checkbox" name="tripLong" class="tripLong" value="7" onclick="oneclick(this)">7일권
							</div> 
							
							<div class="input-group">
						  		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span></span>
						 		<input type="text" class="form-control" id="endDate" name="e_edate" placeholder="도착일" aria-describedby="basic-addon1">
							</div> 
							
							<div class="input-group">
						  		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-piggy-bank" aria-hidden="true"></span></span>
						 		<input type="text" class="form-control" id="travelAmount" name="e_total" placeholder="예산" aria-describedby="basic-addon1"/>
							</div> 
							
							<div class="input-group">
								<input type="button" value="저장" id="submit1" class="btn btn-primary" data-toggle="modal" onclick="save()">
						  		
						  	</div> 
							
						</form>

					</div>
					
					<div class = "kategories">
						<form action="/starrail/expenses/railro_amount" method="POST">
							<div class="input-group">
						  		<span class="input-group-addon" id="kategories">
						  		<a href = "#">
						  		<img src = "/starrail/resources/images/expenses/dinner.png"/>
						  		</a></span>
						  		
						  		<span class="input-group-addon" id="kategories">
						  		<a href = "#">
						  		<img src = "/starrail/resources/images/expenses/shopping.png">
						  		</a></span>
						  		
						  		<span class="input-group-addon" id="kategories">
						  		<a href = "#">
						  		<img src = "/starrail/resources/images/expenses/bed.png">
						  		</a></span>
						  		
						  		<span class="input-group-addon" id="kategories">
						  		<a href = "#">
						  		<img src = "/starrail/resources/images/expenses/bus.png">
						  		</a></span>
						  		
						  		<span class="input-group-addon" id="kategories">
						  		<a href = "#">
						  		<span class="glyphicon glyphicon-option-horizontal" aria-hidden="true">
						  		</span></a></span>
						  		
							</div> 
							
							
							<div class="input-group">
						  		<span class="input-group-addon" id="basic-addon1"><span class="glyphicon glyphicon-list-alt" aria-hidden="true"></span></span>
						 		<input type="text" class="form-control" name="e_title" placeholder="항목" aria-describedby="basic-addon1">
							</div> 
							
							<div class="input-group">
						  		<span class="input-group-addon" id="basic-addon1"><img src = "/starrail/resources/images/expenses/money.png"></span>
						 		<input type="text" class="form-control" id="travleAmount" name="e_total" placeholder="사용 금액" aria-describedby="basic-addon1"/>
						 		
				
							</div> 
							
							<div class="input-group">
						  		<input type="submit" value="등록" id="submit2" class="btn btn-primary" data-toggle="modal">	
						  	</div> 
							
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

 <script type="text/javascript">
	function oneclick(a) {
		var obj = document.getElementsByName("tripLong");
		for (var i = 0; i < obj.length; i++) {
			if (obj[i] != a) {
				obj[i].checked = false;
			}else{
				obj[i].checked = true;
			}
		}
	}
</script>

 <script type="text/javascript">
	function save() {
		alert("등록합니다");
		$.ajax({

			url : '/starrail/expenses/railro_expenses',
			type : 'POST',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : JSON.stringify({'e_title' : $('#e_title').val(),
					'e_sdate' : $('#datepicker').val(),
					'e_edate' : $('#endDate').val(),
					'e_total' : $('#travelAmount').val()
			}),
			dataType : "text",
			success : function(data) {
				alert("성공");
				alert(data);
			}
		});
	}
</script> 

<script>
	$(function() {
		
		var tripLong = document.getElementsByName("tripLong"); // 체크박스 전용 변수
		var lineCnt = 0;
		
 		// 오는날 달력 선택하기
			$("#datepicker").datepicker({
				onSelect : function(StartDate) {
		            $('#datepicker').empty();   //출발역 목록 비우기
		            $('#endDate').empty();   //도착역 목록 비우기
					
					tripDateStart = new Date(StartDate);
					$('input[name="tripLong"]').removeAttr('disabled');
		            $('input[name="tripLong"]').prop('checked', false);
		            
			},
			showOn : "button",
			buttonImage : "/starrail/resources/images/expenses/littlecalendar.PNG",
			buttonImageOnly : true,
			showAnim : "slideDown",
			autoclose : true,
			dateFormat : 'yy-mm-dd',
			minDate : 0
			});
 		
				$('.tripLong').click(function(){
					var tripDateStart = new Date();
			         
			         var strArr = $('#datepicker').val().split('-');
			         
			         tripDateStart.setMonth(Number(strArr[1])-1);
			         tripDateStart.setFullYear(strArr[0]);
			         tripDateStart.setDate(strArr[2]);
			         
			         var endDate = new Date();
			         
			         endDate.setMonth(Number(strArr[1])-1);
			         endDate.setFullYear(strArr[0]);
			         endDate.setDate(tripDateStart.getDate()+Number($(this).val())-1);
			         
			         var interval = endDate.getTime() - tripDateStart.getTime();
			         interval = Math.floor(interval / (1000 *  60 * 60 * 24));
			         interval.toString();

			         alert(interval.toString());
			   
						$('#endDate').attr("value", (endDate.getFullYear()+ '-'+ (endDate.getMonth() + 1)+ '-' + endDate.getDate()));
							
						$('.panel-tabs').empty();
				         for(var i=0; i<=parseInt(interval); i++){
				        	 
				        	 $('.panel-tabs').append('<li class="active" value="' 
				        			 + (tripDateStart.getFullYear() 
				        			 + '/'
				        			 + (tripDateStart.getMonth()+1) 
				        			 + '/' 
				        			 + tripDateStart.getDate()) 
				        			 + '"> <a href="#tab' 
				        			 +(i+1)
				        			 +' data-toggle="tab">'
				        			 + (i+1)
				        			 +'일차</a></li>');
				        	 
			 }
		});
	});
</script>




</html>