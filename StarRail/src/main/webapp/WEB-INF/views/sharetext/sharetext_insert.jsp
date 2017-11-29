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
</head>
<body>
<form role="form" method="POST">
<table class="table table-bordered table-hover" style="text-align: center; border: 1px solid #dddddd">
	<div class="box-body">
		<div class="form-group">
			<label for="exampleInputEmail1">여행일수</label> 
			<!-- <input type="text"
				name='sh_subject' class="form-control" placeholder="여행일수를 선택하세요.">-->
						<select name="sh_subject">
							<option value="  ">여행기간 선택</option>
								<option value="[5일]">[5일]</option>
								<option value="[7일]">[7일]</option>
							</select>
		</div>

		<div class="form-group">
			<label for="exampleInputEmail1">제목</label> 
			<input type="text"
				name='sh_title' class="form-control" placeholder="제목을 입력해주세요.">
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">코스</label> 
			<input type="text"
				name='c_id' class="form-control" placeholder="코스 드루와">
		</div>
		<div class="form-group">
			<label for="exampleInputPassword1">내용</label>
			<textarea class="form-control" name="sh_content" rows="3"
				placeholder="내용을 입력해주세요."></textarea>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">아이디</label> 
			<input type="text"
				name="m_id" class="form-control" value='${login.m_id }' readonly>
		</div>
		<div class="form-group">
			<label for="exampleInputEmail1">비밀번호</label> 
			<input type="text"
				name="sh_pw" class="form-control" placeholder="비밀번호를 입력해주세요.">
		</div>
		
		
	</div>
	<!-- /.box-body -->

	<div class="box-footer">
		<button type="submit" class="btn btn-primary">저장</button>
	</div>
	</table>
</form>

</body>
</html>
