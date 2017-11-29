<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/main/header_footer.css">
<link rel="stylesheet" type="text/css"
	href="/starrail/resources/css/sharetext/sharetext.css">
</head>


<body>

<!-- 히든으로 글번호 가져오기 -->
<form role="form"  method="post">
	<input type='hidden' name='sh_no' value="${shareTextVO.sh_no}">


<div class="box-body">
	<!-- 여행일수 -->
	<div class="form-group">
		<label for="exampleInputEmail1">여행일수</label> <input type="text"
			 class="form-control"
			value="${shareTextVO.sh_subject}" readonly="readonly">
			<select></select>
	</div>
	
	<!-- 제목 -->
	<div class="form-group">
		<label for="exampleInputEmail1">제목</label> <input type="text"
			name='sh_title' class="form-control" value="${shareTextVO.sh_title}"
			readonly="readonly">
	</div>
	
	<!-- 코스정보  -->
	<div class="form-group">
		<label for="exampleInputPassword1">코스</label>
		<textarea class="form-control" name="c_id" rows="3"
			readonly="readonly">${shareTextVO.c_id}</textarea>
	</div>
	
	<!-- 내용 -->
	<div class="form-group">
		<label for="exampleInputPassword1">내용</label>
		<textarea class="form-control" name="content" rows="3"
			readonly="readonly">${shareTextVO.sh_content}</textarea>
	</div>
	
	<!-- 작성자 -->
	<div class="form-group">
		<label for="exampleInputEmail1">작성자</label> <input type="text"
			name="writer" class="form-control" value="${shareTextVO.m_id}"
			readonly="readonly">
	</div>
	
	<!-- 작성날짜 -->
	<div class="form-group">
		<label for="exampleInputEmail1">작성날짜</label> <input type="text"
			name="sh_date" class="form-control" value="${shareTextVO.sh_date}"
			pattern="yyyy-MM-dd" readonly="readonly">pattern="yyyy-MM-dd"
	</div>
</div>

	<!-- 버튼 -->
</form>
	<div class="box-footer">
	<c:if test="${login.m_id==shareTextVO.m_id }">
		<button type="submit" class="btn btn-warning">수정</button>
		<button type="submit" class="btn btn-danger">삭제</button>
	</c:if>
		<button type="submit" class="btn btn-primary">목록</button>
	</div>

<br><br><br>

<!-- <div>
	<div>
		작성자 <input type="text" name="replyer" id="newReplyWriter">
	</div>
	<div>
		내용 <input type="text" name="sr_content" id="newReplyText">
	</div>
	<button id="replyaddBtn">추가</button>
</div> -->


<c:if test="${not empty login }">
  <div class="form-group">
    <label for="exampleInputEmail1">내용</label>
    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="내용을 입력해주세요.">
  </div>
  <div class="form-group">
    <label for="exampleInputPassword1">작성자</label>
    <input type="text" class="form-control" id="exampleInputPassword1" value="${login.m_id}" readonly>
  </div>
 <button type="submit" class="btn btn-default btn-lg btn-block">추가</button>
</c:if>
<c:if test="${empty login }">
	<div class="box-body">
		<div><a href="javascript:goLogin();">로그인이 필요합니다. <br> 로그인해주세요.</a></div>
	</div>
</c:if>




<script type="text/javascript">

$(function() {

	var formObj = $("form[role='form']");

	console.log(formObj);

	$(".btn-warning").on("click", function() {
		formObj.attr("action", "/starrail/sharetext/sharetext_update");
		formObj.attr("method", "get");
		formObj.submit();
	});

	$(".btn-danger").on("click", function() {
		formObj.attr("action", "/starrail/sharetext/remove");
		formObj.submit();
	});

	$(".btn-primary").on("click", function() {
		self.location = "/starrail/sharetext/listAll";
	});
	
	$("#replyaddBtn").on("click", function(){
		
		var replyer = $("#newReplyWriter").val();
		var sr_content =$("#newReplyText").val();
		
		$.ajax({
			type : 'post',
			url : '/replies',
			headers : {
				"content-type" : "application/json",
				"X-HTTP-Method-Override":"POST"
			},
			dateType : 'text',
			data : JSON.stringify({
				sh_no : sh_no,
				replyer : replyer,
				sr_content : sr_content
			}),
			success : function(result){
				if(result == 'SUCCESS'){
					alert("등록되었습니다.");
					getAllList();
				}
			}
			
		
		
		});
	});

});

</script>
</body>
</html>





