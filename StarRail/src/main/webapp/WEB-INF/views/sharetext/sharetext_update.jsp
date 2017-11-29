<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
</head>
<body>
<form role="form" method="post">

	<div class="box-body">
		
		<!-- 글번호 -->
		<div class="form-group">
			<label for="exampleInputEmail1">SH_NO</label> <input type="text"
				name='sh_no' class="form-control" value="${shareTextVO.sh_no}"
				readonly="readonly">
		</div>

		<!-- 여행일수 -->
		<div class="form-group">
			<label for="exampleInputEmail1">여행일수</label> <input type="text"
				name='sh_subject' class="form-control" value="${shareTextVO.sh_subject}" readonly="readonly">
		</div>
		<!-- 제목 -->
		<div class="form-group">
			<label for="exampleInputEmail1">제목</label> <input type="text"
				name='sh_title' class="form-control" value="${shareTextVO.sh_title}">
		</div>
		
		<!-- 코스정보 -->
		<div class="form-group">
			<label for="exampleInputPassword1">코스</label>
			<textarea class="form-control" name="c_id" rows="3"  readonly="readonly">${shareTextVO.c_id}</textarea>
		</div>
		
		<!-- 내용 -->
		<div class="form-group">
			<label for="exampleInputPassword1">내용</label>
			<textarea class="form-control" name="sh_content" rows="3">${shareTextVO.sh_content}</textarea>
		</div>
		
		<!-- 작성자 -->
		<div class="form-group">
			<label for="exampleInputEmail1">작성자</label> <input
				type="text" name="writer" class="form-control"
				value="${shareTextVO.m_id}" readonly="readonly">
		</div>
	</div>
	<!-- /.box-body -->
</form>


<div class="box-footer">
	<button type="submit" class="btn btn-primary">저장</button>
	<button type="submit" class="btn btn-warning">취소</button>
</div>

<script>
	$(document).ready(function() {

		var formObj = $("form[role='form']");

		console.log(formObj);

		$(".btn-warning").on("click", function() {
			self.location = "/starrail/sharetext/listAll";
		});

		$(".btn-primary").on("click", function() {
			formObj.submit();
		});

	});
</script>
</body>

</html>




