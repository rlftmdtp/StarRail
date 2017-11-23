$(function(){
	
	// 역 버튼을 누르면 해당 지역의 관광지 정보가 로드된다
	$('.station').click(function(){
		
		var station = $(this).val();
		
		$.getJSON("/starrail/maprest/tourlist/" + station, function(data){
			alert("결과과돌아왔습니다");
		});
		/*(
		$.ajax({
			type : 'post',
			url : '/starrail/maprest/getTourList',
			dataType : 'text',
			headers : {
				"Content-type" : "application/text"
			},
			data : station,
			success : function(result) {
				alert(result);
			}
		});
		*/
	});
});