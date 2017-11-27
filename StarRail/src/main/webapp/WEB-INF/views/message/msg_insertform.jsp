<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String rec_id = request.getParameter("rec_id");
	String m_id = (String) session.getAttribute("m_id");

	if (rec_id == null) {
		rec_id = "";
	}
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	
</script>

<style type="text/css">
.red {
	color: red;
}

.form-area {
	background-color: #FAFAFA;
	padding: 1px 30px 5px;
	margin: 1px 0px 5px;
	border: 1px solid GREY;
}

.form-control {
	padding: 5px 5px 5px 5px;
}

.form-group {
	margin-top: 10px;
}
</style>

</head>
<body>

	<div class="container">
		<div class="col-md-5">
			<div class="form-area">
				<form action="msg_insertform" method="POST">
					<br style="clear: both">
					<h3 style="margin-bottom: 20px; text-align: center;">Contact
						Form</h3>
					<div class="form-group">
						<input type="text" class="form-control" name="m_id"
							value="<%=m_id%>" placeholder="받는 사람" size="35px" required>
					</div>

					<div class="form-group">
						<textarea class="form-control" type="textarea" id="message"
							name="msg_content" placeholder="Message" maxlength="300" rows="7"
							cols="37"></textarea>
						<span class="help-block">
							<p id="characterLeft" class="help-block ">You have reached
								the limit</p>
						</span>
					</div>

					<div class="form-group">
						<input type="text" class="form-control" id="msg_sendid"
							name="msg_sendid" value="msg_sendid" placeholder="보내는 사람"
							size="35px" required>
					</div>

					<button type="submit" name="submit"
						class="btn btn-primary pull-right" id="submitBtn">Submit
						Form</button>
				</form>
			</div>
		</div>
	</div>
</body>

<script type="text/javascript">
	$(document).ready(function() {
		$('#characterLeft').text('300 characters left');
		$('#message').keydown(function() {
			var max = 300;
			var len = $(this).val().length;
			if (len >= max) {
				$('#characterLeft').text('You have reached the limit');
				$('#characterLeft').addClass('red');
				$('#btnSubmit').addClass('disabled');
			} else {
				var ch = max - len;
				$('#characterLeft').text(ch + ' characters left');
				$('#btnSubmit').removeClass('disabled');
				$('#characterLeft').removeClass('red');
			}
		});
	});
</script>

<script type="text/javascript">
	$("#submitBtn").on('click', function() {
		$.ajax({
			url : '/starrail/message/msg_insertok',
			type : 'POST',
			headers : {
				'Content-Type' : 'application/json'
			},
			dataType : "text",
			success : function(data) {
				window.self.close();
			}
		});
	});
</script>

</html>