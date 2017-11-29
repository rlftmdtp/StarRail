<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/main/header_footer.css">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/reservation/reservation.css?ver=1">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery-1.12.4.js"></script>
<script src="//code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<style type="text/css">
.container{
width:80%;
}
input[type=text] { border:dotted 3px blue}
.table table-bordered table-hover{
width: 100%;
}
</style>
<title>StarRail ReservaionPage</title>
</head>
<body>
	<c:if test="${not empty login }">
	<div class="container">
		<form method="post" action="Reservation_view">
			<table class="table table-bordered table-hover"
				style="text-align: center; border: 2px solid #dddddd">
				<thead>
					<tr align="center">
						<th colspan="2"><h4 align="center">예약 페이지</h4>
					</tr>
				</thead>
				<tbody>

				<!-- *************   출발일 선택   ************* -->
					<tr>
						<td style="width: 210px;"><h5>출발일 선택(*)</h5></td>
						<td >
							<input type="text" id="datepicker_end" readonly name="res_rtstime">

							<script type="text/javascript">
								$("#datepicker_end")
										.datepicker(
												{
													showOn : "button",
													buttonImage : "/starrail/resources/images/reservation/ccc.png",
													buttonImageOnly : true,
													showAnim : "slideDown",
													autoclose : true,
													dateFormat : 'yy-mm-dd',
													minDate : 0,
													onClose : function(
															selectedDate) {
														// 종료일(toDate) datepicker가 닫힐때
														// 시작일(fromDate)의 선택할수있는 최대 날짜(maxDate)를 선택한 종료일로 지정 
														$("#fromDate")
																.datepicker(
																		"option",
																		"maxDate",
																		selectedDate);
													},
													onSelect : function(
															dateText) { // 달력에서 선택한 값과 시간을 합쳐 endDate로 저장하기
														end_date = dateText;
														$("#total_Edate").val(
																end_date);
													}

												});
							</script>
					</td>
				</tr>
				
				<!-- *************   여행일수 (5일or7일)  ************* -->
				<tr>
					<td style="width: 210px;"><h5>여행일수(*)</h5></td>
					<td >
						<div class="form-group" style="text-align: center; margin: 0 auto;">
						<label class="btn btn-primary active"> 
							<input type="radio" name="sh_subject"  value="5일" checked>5일&nbsp;&nbsp;&nbsp; 
							<input type="radio" name="sh_subject"  value="7일">7일
						</label>

						</div>
					</td>
				</tr>
				
				<!-- *************   여행종료날짜  ************* -->
				<tr>
					<td style="width: 210px;"><h5>여행종료일</h5></td>
					<td></td>
				</tr>
				
				<!-- *************   발권역 선택  ************* -->
					<tr>
						<td style="width: 210px;"><h5>발권역 선택(*)</h5></td>
						<td >
							<select name="i_name" id="cusSelectbox">
										<option> --- 발권역 선택 --- </option>
										<option value="서울">서울</option>
										<option value="용산">용산</option>
										<option value="청량리">청량리</option>
										<option value="정동진">정동진</option>
										<option value="제천">제천</option>
										<option value="영월">영월</option>
										<option value="단양">단양</option>
										<option value="충주">충주</option>
										<option value="부산">부산</option>
										<option value="동대구">동대구</option>
										<option value="순천">순천</option> 			
														
								</select>
						</td> 
						
					</tr>

					<!-- *************   기차 좌석 선택  ************* -->
					<tr>
						<td style="width: 210px;"><h5>기차 좌석 선택</h5></td>
						<td >
							<div class="form-group"
								style="text-align: center; margin: 0 auto;">
								<label class="btn btn-primary active"> 
									<input type="text" id="trainseat" >
										
										
									
								</label>
							</div>
						</td>
					</tr>
					<!--<tr>
						 <td>
							<div class="container">

								<a class="btn icon-btn btn-success" href="#"><span
									class="glyphicon btn-glyphicon glyphicon-plus img-circle text-success"></span>Add</a>

							</div>
						</td> 
					</tr>-->

					<!-- *************   버튼선택   ************* -->

					<tr align="center" >
						
						<td colspan="2">
						<input type=button value="등록" OnClick="javascript:writeCheck();"> 
						<input type=button value="취소" OnClick="javascript:history.back(-1)">
						
					</tr>

				</tbody>
			</table>
		</form>
	</div>
</c:if>
<c:if test="${empty login }">
<center>

<h5> 로그인 상태가 아닙니다. </h5><br>
<h5> 본 서비스를 이용하시려면 로그인을 해주시기 바랍니다. </h5>

 <div class="container">    
        
    <div id="loginbox" class="mainbox col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3"> 

        
        <div class="panel panel-default" >
            <div class="panel-heading">
                <div class="panel-title text-center">Star Rail</div>
            </div>     

            <div class="panel-body" >

                <form action="/starrail/main/loginPost" name="form" id="form" class="form-horizontal" method="POST">
                   
                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                        
                        <!-- ID -->
                        <input id="user" type="text" class="form-control" name="m_id">                                        
                    </div>

                    <div class="input-group">
                        <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                        
                        <!-- Password -->
                        <input id="password" type="password" class="form-control" name="m_pw">
                    </div>                                                                  

                    <div class="form-group">
                        <!-- Button -->
                        <div class="col-sm-12 controls">
                            <button type="submit" class="btn btn-primary pull-right"><i class="glyphicon glyphicon-log-in"></i> Log in</button>                          
                        </div>
                    </div>
                </form>     

            </div>                     
        </div>  
    </div>
</div>

<div id="particles"></div>


</center>
</c:if>
</body>
</html>