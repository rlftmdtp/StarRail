$(function() {

	// --------------------- 지도 초기 설정 -------------------------------
	var GREEN_FACTORY = new naver.maps.LatLng(37.3595953, 127.1053971);
	var location = GREEN_FACTORY; // 역을 담을 좌표 변수
	
	var tbody = $('#foodList');
	var info2 = $('.info2');
	
	// 전체 목록 ( 맛집 ) 
	var markerFoodList = [];
	var infoFoodList = [];
	var chartFoodList = [];
	
	// 임시 목록
	var tempChartFoodList = [];
	
	// 숙박
	var stayLatLatlngs = [];
	
	// 관광지
	var tourLatlngs = [];
	
	// 지도 설정
	var map = new naver.maps.Map('map', {
		center : GREEN_FACTORY,
		zoom : 12
	})
	
	// 반경 원 범위 설정
	var circle = new naver.maps.Circle({
		map : map,
		center : location,
		radius : 300, // 단위 m
		fillColor : 'crimson',
		fillOpacity : 0.3
	})
	
	// 반경 길이를 나타내는 직선
	var radiusLine = new naver.maps.Polyline({
		map : map,
		path : [location,location.destinationPoint(0, 300)],
	    strokeWeight: 5
	})
	
	// 지도 줌 제한 하기
	map.setOptions({
		minZoom : 10,
		maxZoom : 12
	});
	
	// 가게 정보창
	var infoWindow = new naver.maps.InfoWindow({
		anchorSkew : true
	});
	
	var infoRadius = new naver.maps.InfoWindow({
		anchorSkew : true,
		content : 300 + 'm'
	});
	infoRadius.open(map,location);
	// --------------------------------------------

	// 코스를 누르면 역들이 로드된다.
	$('.course').on('click', '.courseButton', function() {
		var c_id = $(this).val();
		
		alert('누른 코스는 ' + c_id);
		$('#stationButtons').empty();
		
		$.getJSON("/starrail/maprest/coursedetail/" + c_id, function(stations){
			
			for(var i=0; i<stations.length; i++){
				$('#stationButtons').append('<button type="submit" value="' +stations[i] +'" class="btn btn-default stationButton">' + stations[i] +'</button>');
			}
		})
	});

	// 역 버튼을 누르면 해당 지역의 관광지 정보가 로드된다
	$('.station').on('click','.stationButton', function() {

		var station = $(this).val();

		// 지도 API가 누른 역으로 뜨게 만든다.
		// 데이터베이스와 통신하여 그 역의 좌표를 가져온다
		$.getJSON("/starrail/maprest/stationXY/" + station, function(coord) {
			alert("DB에 있는 x좌표 " + coord.s_x);
			
			location = new naver.maps.LatLng(coord.s_x, coord.s_y);

			// 그 역으로 이동
			map.panTo(location);

			getTourList(station);
		})
	});
	
		// 반경 변경시 원의 범위 변경
		$('#mapScope').on('keydown', function(e){
			var keyCode = e.which;
			
			if(keyCode === 13){ // 엔터 입력시
				draw($('#mapScope').val());
				updateMarkers();
			}
		});
		$('#submit').on('click', function(e){
			e.preventDefault();
			draw($('#mapScope').val());
			updateMarkers();
		})
		
		//--------------------------------------- 함수들 ---------------------------------
		function getTourList(station){ // 반경까지 전달해서 service에서 처리를 해야하나 ...?
			$.getJSON("/starrail/maprest/tourlist/" + station, function(data) {
				
				var foodLatlngs = [];
								
				//-------------------- 푸드 -----------------
				$(data.foodList).each(function(number){
					// 검색 API에서 얻은 좌표는 TM128(카텍좌표계) 이므로 지도 API에서
					// 사용하기 위해서는 LatLng좌표로 변경해야 한다.
					
					// number객체를 => naver.maps.Point객체로 변경 후 => fromTM128ToLatLng(naver.maps.Point객체)로 이용한다
					var tm128= new naver.maps.Point(this.mapx,this.mapy);
					var latlng = naver.maps.TransCoord.fromTM128ToLatLng(tm128);
					foodLatlngs.push(latlng);
					
					// Info 정보창 생성
					var contentString = [
						 '<div class="iw_inner">',
					        '   <h3>'+this.title+'</h3>',
					        '   <p>' + this.address +'<br />',
					        '       '+ this.category +'<br />',
					        '       '+ this.telephone +'<br />',
					        '       '+ this.description +'<br />',
					        '       <a href="'+ this.link +'" target="_blank">'+ this.link +'</a>',
					        '   </p>',
					        '</div>'
					].join('') // join함수는 배열을 문자열로 바꾼다.
					infoFoodList.push(contentString);
					
					/*
					var tbody = $('#foodList'); 
					tbody.empty();
					//-- 추가 : 지도위에 있는 것도 지워야 한다.
					tbody.append('<tr> <th scope="row">'+(number+1)+ '</th> <td>' + this.title + '</td> </tr>');
					*/
					
					var chartString = [
						'<td>' + this.title + '</td>',
						'</tr>'
					].join('');
					chartFoodList.push(chartString);
				})
				
				// 마커정보생성
				for(var i=0; i<foodLatlngs.length; i++){
					var icon = {
							url : "/starrail/resources/images/map/food32.png"
					}
					
					var marker = new naver.maps.Marker({
						position : foodLatlngs[i],
						map : map,
						icon : icon
					});
					
					marker.set('seq', i); //ecma6 문법
					
					markerFoodList.push(marker);
					
					marker.addListener('mouseover', onMouseOver);
					marker.addListener('mouseout', onMouseOut);
				}
				
				draw(300);
				updateMarkers();
			});
		}
		
		/*
		 * ( $.ajax({ type : 'post', url : '/starrail/maprest/getTourList',
		 * dataType : 'text', headers : { "Content-type" : "application/text" },
		 * data : station, success : function(result) { alert(result); } });
		 */

		
		// 그래프에 데이터 도출
		function getDataLab(){
			$.getJSON("/starrail/maprest/datalab/"+station, function(data){
				  alert("결과과돌아왔습니다"); 
				  });
		}
		  
		 
		
		function draw(radius){
			// 영역 만큼 원과 직선을 그린다
						circle.setOptions({
							map : map,
							center : location,
							radius : radius, // 단위 m
							fillColor : 'crimson',
							fillOpacity : 0.3
						});
						var point = location.destinationPoint(0, radius);
						radiusLine.setOptions({
							path : [location, point]
						})
						infoRadius.setContent(radius + 'm');
						infoRadius.open(map,location);
		}
		
		// 원의 경계 영역에 있는 지역만 뜬다.
		function updateMarkers(){
			
			var circleBounds = circle.getBounds();
			// 기존에 있던 목록들을 삭제한다.
			tempChartFoodList = [];
			tbody.empty();
			
			for(var i=0; i < markerFoodList.length; i++){
				var marker = markerFoodList[i];
				var position = marker.getPosition();
				
				if(circleBounds.hasLatLng(position)){
					// 범위 안에 존재하면 chartFoodList에서 꺼내서 넣어야 한다
					tempChartFoodList.push(chartFoodList[i]); 
					showMarker(marker);
				}
				else{
					hideMarker(marker);
				}
			}
			
			for(var i=0; tempChartFoodList.length; i++){
				tbody.append('<tr> <th scope="row">' + (i +1) + '</th>' + tempChartFoodList.pop(i)); // 차트 목록에 추가
			}
		}
		
		// -- 위도 경도를 이용한 좌표간의 거리 구하기 공식 함수
		/*
		function distance(circleRadius) {  
			
			for(var i=0; i<markerFoodList.length; i++)
			{
				var marker = markerFoodList[i];
				var position = marker.getPosition();
				
				 var p = 0.017453292519943295;    // Math.PI / 180
				 var c = Math.cos;
				 var a = 0.5 - c((position.lat() - location.lat()) * p)/2 + 
				          c(location.lat() * p) * c(position.lat() * p) * 
				          (1 - c((position.lng() - location.lng()) * p))/2;

				 var distance = 1000 * 12742 * Math.asin(Math.sqrt(a)); // 2 * R; R = 6371 km
			
				 if(distance.toFixed(0) < circleRadius){
					 showMarker(marker);
				 }
				 else{
					 hideMarker(marker);
				 }
			}
			}
			*/
		//------------------------------------------
		function showMarker(marker) {
		    if (marker.getMap()) return;
		    marker.setMap(map);
		}

		function hideMarker(marker) {

		    if (!marker.getMap()) return;
		    marker.setMap(null);
		}
		
		// 마커위에 마우스 이벤트 함수
		function onMouseOver(e){
			var marker = e.overlay;
			var seq = marker.get('seq');
			
			marker.setIcon({
				url : "/starrail/resources/images/map/food64.png"
			})
			
			// 정보창의 내용을 바꾸고 정보창을 띄운다
			infoWindow.setContent(infoFoodList[seq]);
			infoWindow.open(map, marker);
			
			// 1.지도 하단오른쪽에 상세한 가게정보 로드
			info2.empty();
			info2.append(infoFoodList[seq]);
			
			// 2.차트에서 보여주기
			// 3.제일 하단에 그래프 보여주기
		}
		function onMouseOut(e){
			var marker = e.overlay
			var seq = marker.get('seq');
			
			marker.setIcon({
				url : "/starrail/resources/images/map/food32.png"
			})
			
			infoWindow.close(map, marker);
			infoRadius.open(map,location);
		}
		
});
