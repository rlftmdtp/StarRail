<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style>
#modDiv {
	width: 300px;
	height: 100px;
	background-color: gray;
	position: absolute;
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
}

.pagination {
  width: 100%;
}

.pagination li{
  list-style: none;
  float: left; 
  padding: 3px; 
  border: 1px solid blue;
  margin:3px;  
}

.pagination li a{
  margin: 3px;
  text-decoration: none;  
}

</style>

<script type="text/javascript">
var bno = "";

getAllList();

function getAllList() {

	$.getJSON(	"/replies/all/" + sh_no, function(data) {
		//console.log(data.length);
		var str = "";

		$(data).each(	function() {
					str += "<li data-sr_no='"+this.sr_no+"' class='replyLi'>"
				    		+ this.sr_no
							+ ":"
							+ this.sr_content
							+ "<button>MOD</button></li>";
			});

		$("#replies").html(str);
	});
}
</script>
</head>
<body>
	<!-- <div id='modDiv' style="display: none;">
		<div class='modal-title'></div>
		<div>
			<input type='text' id='sr_content'>
		</div>
		<div>
			<button type="button" id="replyModBtn">수정</button>
			<button type="button" id="replyDelBtn">삭제</button>
			<button type="button" id='closeBtn'>닫기</button>
		</div>
	</div>

	<h2>Ajax Test Page</h2>

	<div>
		<div>
			REPLYER <input type='text' name='replyer' id='newReplyWriter'>
		</div>
		<div>
			REPLY TEXT <input type='text' name='replytext' id='newReplyText'>
		</div>
		<button id="replyAddBtn">ADD REPLY</button>
	</div>

 -->

	<ul id="replies">
	</ul>
	
	<ul class='pagination'>
	</ul>	
</body>
</html>