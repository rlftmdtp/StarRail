<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/expenses/expenses.css">
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- bootstrap에서 받아온 js파일+css파일 사용 하기 위함 -->
<script src="/starrail/resources/bootstrap/js/bootstrap.js"></script>
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/main/header_footer.css">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/bootstrap/css/bootstrap.css">
<script src="/starrail/resources/bootstrap/js/bootstrap.js"></script>


<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
.selected {
	background-color: yellow;
}
</style>
</head>

<body>

	<br>
	<div class="container" style="margin-top: 80px;">
		<div class="row">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">여행 경비 관리</h3>
						<span class="pull-right"> 
						
						</span>
					</div>
					
					<!-- Tab안 -->
					<div class="panel-body">
						<div class="tab-content">

						<!-- 예산경비 등록 폼 -->
								<form action="#" id="expenseslist">
								<div class="hero-widget well well-sm" style="background-color: #FFFFFF; height: 350px;">
           							
									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"
											style="width: 25px;"> <span
											class="glyphicon glyphicon-book" aria-hidden="true"> </span></span>
										<input type="text" class="form-control" id="e_title"
											name="e_title" placeholder="제목"
											aria-describedby="basic-addon1">
									</div>

									<div class="input-group" id="calanderimg">
										<input type="text" id="datepicker_expense" name="e_sdate"
											placeholder="출발일" size="33px;" >

									</div>


									<div class="input-group" style="margin-top: 15px;">
										<input id="checkboxId" type="checkbox" name="tripLong"
											class="tripLong" value="5" onclick="oneclick(this)">5일권&nbsp;&nbsp;
										<input id="checkboxId" type="checkbox" name="tripLong"
											class="tripLong" value="7" onclick="oneclick(this)">7일권
									</div>

									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"
											style="width: 25px;"> <span
											class="glyphicon glyphicon-calendar" aria-hidden="true">
										</span></span> <input type="text" class="form-control" id="endDate"
											name="e_edate" placeholder="도착일"
											aria-describedby="basic-addon1">
									</div>

									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"
											style="width: 25px;"> <span
											class="glyphicon glyphicon-piggy-bank" aria-hidden="true">
										</span></span> <input type="text" class="form-control" id="travelAmount"
											name="e_total" placeholder="예산"
											aria-describedby="basic-addon1" />
									</div>

									<div class="input-group">
										<input type="button" value="저장" id="submit1"
											class="btn btn-primary" data-toggle="modal" onclick="save()">
											&nbsp;&nbsp;
										<input type="button" value="불러오기" id="recall" class="btn btn-primary" data-toggle="modal">
									</div>
									</div>
								</form>
							</div>
			
							<!-- 지출내역 등록하는 카테고리 -->
								<form action="#" id="katelist">
									<div class="hero-widget well well-sm" style="background-color: #FFFFFF;">
								
									<div class="dayButton" id="ddol">
										<!--경비 저장하는 form에서 선택한 일수만큼 버튼 생김 -->
									</div>
								
								<!-- 카테고리 -->
									<div class="btn-toolbar" style="margin-top : 10px; margin-bottom: 10px;">
										<div class="btn-group">
											<button class="btn" data="food"
												style="width: 40px; height: 40px;">
												<img src="/starrail/resources/images/expenses/dinner.png" />
											</button>

											<button class="btn" data="shopping"
												style="width: 40px; height: 40px;">
												<img src="/starrail/resources/images/expenses/shopping.png" />
											</button>

											<button class="btn" data="hotel"
												style="width: 40px; height: 40px;">
												<img src="/starrail/resources/images/expenses/bed.png" />
											</button>

											<button class="btn" data="bus"
												style="width: 40px; height: 40px;">
												<img src="/starrail/resources/images/expenses/bus.png" />
											</button>

											<button class="btn" data="etc"
												style="width: 40px; height: 40px;">
												<img
													src="/starrail/resources/images/expenses/001-speech-bubble.png" />
											</button>
										</div>
									</div>


									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"
											style="width: 30px;"> <span
											class="glyphicon glyphicon-list-alt" aria-hidden="true">
										</span></span> <input type="text" class="form-control" placeholder="항목"
											aria-describedby="basic-addon1" id="ed_katename">
									</div>

									<div class="input-group">
										<span class="input-group-addon" id="basic-addon1"
											style="width: 30px;"> <img
											src="/starrail/resources/images/expenses/money.png"></span> <input
											type="text" class="form-control" id="ed_amount"
											name="ed_amount" placeholder="사용 금액"
											aria-describedby="basic-addon1" />
									</div>

									<div class="input-group">
										<input type="button" value="등록" class="btn btn-primary"
											data-toggle="modal" onclick="expense_save()">
									</div>
									</div>
								</form>
								
								<!-- 지출된 금액 계산해주고 view에 뿌려주는 list -->
								<form action="#" id="amountlist">
									<div class="hero-widget well well-sm" style="background-color: #FF5E00;">
           								<div class="amount_list_content">
           									<!-- 지출 날짜 적용 -->
           								</div>
           									 <ul class="list-group">
                    							<!-- 지출내역 보여지기 -->
               								 </ul>
           								<div class="totallist">
           									<!-- 총사용금액, 총남은돈  -->
           								</div>
           							</div>
								
								
								</form>
							</div>

						<!-- 실시간 도표 -->
							<form action ="#" id="chartlist"></form>
								<div class="hero-widget well well-sm">
           							
           							
           							</div>
						</div>
					</div>
				</div>

	<br>

</body>

<!--5일차,7일차 체크박스 클릭했을 때, 하나만 눌리게 -->
<script type="text/javascript">
	function oneclick(a) {
		var obj = document.getElementsByName("tripLong");
		for (var i = 0; i < obj.length; i++) {
			if (obj[i] != a) {
				obj[i].checked = false;
			} else {
				obj[i].checked = true;
			}
		}
	}
</script>


<!-- 예산 경비 저장 눌렀을 때  -->
<script type="text/javascript">
	function save() {
		alert("등록합니다");
		$.ajax({

					url : '/starrail/expenses/railro_expenses',
					type : 'POST',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify({
						'e_title' : $('#e_title').val(),
						'e_sdate' : $('#datepicker_expense').val(),
						'e_edate' : $('#endDate').val(),
						'e_total' : $('#travelAmount').val()
					}),
					dataType : "text",
					success : function(data) {
						alert("성공");
						alert(data);
						/* 지출경비form에 e_no를 hidden으로 숨겨 누가 지출하는지 알게하기 */
						$('#ddol').append('<input type="hidden" value="' + data + '" class="e_no"/>');
					}
				});
	}
</script>

<!-- 출발일과 체크박스 선택시 도착일 계산 // 함수 datepicker사용 -->
<script>
	$(function() {

		var tripLong = document.getElementsByName("tripLong"); // 체크박스 전용 변수

		// 오는날 달력 선택하기
		$("#datepicker_expense").datepicker({
			onSelect : function(StartDate) {
				$('#datepicker_expense').empty(); //출발역 목록 비우기
				$('#endDate').empty(); //도착역 목록 비우기

				tripDateStart = new Date(StartDate);
				$('input[name="tripLong"]').removeAttr('disabled');
				$('input[name="tripLong"]').prop('checked', false);

			},
			showOn : "button",
			buttonImage : "/starrail/resources/images/expenses/agenda.png",
			buttonImageOnly : true,
			showAnim : "slideDown",
			autoclose : true,
			dateFormat : 'yy-mm-dd',
			minDate : 0
		});

		$('.tripLong').click(
						function() {
							var tripDateStart = new Date();

							var strArr = $('#datepicker_expense').val().split('-');
							/* 날짜계산을 년월일 같이 set해주게되면 12월로 안넘어간다 따로 해주어야함 */
							tripDateStart.setMonth(Number(strArr[1]) - 1);
							tripDateStart.setFullYear(strArr[0]);
							tripDateStart.setDate(strArr[2]);

							var endDate = new Date();

							endDate.setMonth(Number(strArr[1]) - 1);
							endDate.setFullYear(strArr[0]);
							endDate.setDate(tripDateStart.getDate()
									+ Number($(this).val()) - 1);

							/* 일 수 구하는 공식 */
							var interval = endDate.getTime() - tripDateStart.getTime();
							interval = Math.floor(interval / (1000 * 60 * 60 * 24));
							interval.toString();

							alert(interval.toString());

							$('#endDate').attr("value", (endDate.getFullYear() + '-'
													+ (endDate.getMonth() + 1)
													+ '-' + endDate.getDate()));
				
							/* 체크박스를 클릭할때 마다 생기는 버튼값을 지워주고 새로 만들어줌 for문이용하여 출발일기준 일수만큼 더해주기 */
							$('.dayButton').empty();
							for (var i = 0; i <= parseInt(interval); i++) {

								$('.dayButton').append('<input type="button" value='
														+ (i + 1)
														+ '일차  class="ed_date" id="'
														+ (tripDateStart
																.getFullYear()
																+ '/'
																+ (tripDateStart
																		.getMonth() + 1)
																+ '/' + tripDateStart
																.getDate())
														+ '"> </input>');
								tripDateStart.setDate(tripDateStart.getDate() + 1);

							}
						});
	});
</script>

<!-- 지출내역 저장 눌렀을 때 -->
<script type="text/javascript">
	var kategorie;
	var day;

	/* 카테고리중 클릭한 값 가져오기 */
	$('.btn').on('click', function() {
		kategorie = ($(this).attr('data'));
		/* 클릭한 버튼에 이벤트 걸어 뭐 눌렀는지 알려주자 */
		$(this).addClass("selected");
		$(this).siblings().removeClass("selected");
	});

	/* 일차 버튼중 클릭한 날짜 가져오기 */
	$(document).on('click', '.ed_date', function() {
		day = ($(this).attr('id'));
		/* 클릭한 버튼에 이벤트 걸어 뭐 눌렀는지 알려주자 */
		$(this).addClass("selected");
		$(this).siblings().removeClass("selected");
	});

	/* 지출내역 저장 눌렀을 때  DB로 보내고 새로고침*/
	function expense_save() {
		alert("저장합니다");
		var html = "";
		$.ajax({

			url : '/starrail/expenses/railro_amount',
			type : 'POST',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : JSON.stringify({
				'ed_kategorie' : kategorie,
				'ed_katename' : $('#ed_katename').val(),
				'ed_amount' : $('#ed_amount').val(),
				'ed_date' : day,
				'e_no' : $('.e_no').val()
			}),
			dataType : "json",
			success : function(data) {
				alert(data.todayTotal);
				/* 내가 사용한 지출 내역을 가져와 view에 뿌려줌 */
				$('.amount_list_content').append('<p>' + data.ed_date + '</p>');
				$('.amount_list_content').append('<input type="hidden" value="'+data.e_no+'" >');
				
		
				if(data.ed_kategorie == 'food'){
					$('.list-group').append('<li class="list-group-item"><img src ="/starrail/resources/images/expenses/dinner.png">(' 
										+ data.ed_katename + ')&nbsp&nbsp' + data.ed_amount + '</li>');
				}else if(data.ed_kategorie == 'shopping'){
					$('.list-group').append('<li class="list-group-item"><img src ="/starrail/resources/images/expenses/shopping.png">(' 
										+ data.ed_katename + ')&nbsp&nbsp' + data.ed_amount + '</li>');
				}else if(data.ed_kategorie == 'hotel'){
					$('.list-group').append('<li class="list-group-item"><img src ="/starrail/resources/images/expenses/bed.png">(' 
										+ data.ed_katename + ')&nbsp&nbsp' + data.ed_amount + '</li>');
				}else if(data.ed_kategorie == 'bus'){
					$('.list-group').append('<li class="list-group-item"><img src ="/starrail/resources/images/expenses/bus.png">(' 
										+ data.ed_katename + ')&nbsp&nbsp' + data.ed_amount + '</li>');
				}else if(data.ed_kategorie == 'etc'){
					$('.list-group').append('<li class="list-group-item"><img src ="/starrail/resources/images/expenses/001-speech-bubble.png">(' 
										+ data.ed_katename + ')&nbsp&nbsp' + data.ed_amount + '</li>');
				}
					
	
				$('.totallist').append('<p>쓴 돈 : '+ data.todayTotal +'&nbsp&nbsp남은 돈 : '+ data.e_total +'</p>');
								
				
			}
		});
	}
</script>




</html>