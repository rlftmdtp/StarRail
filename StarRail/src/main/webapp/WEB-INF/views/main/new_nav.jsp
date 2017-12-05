<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 반응형 웹 하기 위함 -->
<meta name="viewport" content="width=divice-width" , initial-scale="1">
<title>coding booster</title>
<script src="http://code.jquery.com/jquery-3.1.1.min.js"></script>
<!-- bootstrap에서 받아온 js파일+css파일 사용 하기 위함 -->
<script src="/starrail/resources/bootstrap/js/bootstrap.js"></script>


<link rel="stylesheet" type="text/css"   href="/starrail/resources/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="/starrail/resources/css/main/header_footer.css">
</head>

<body>

<!--  header영역 시작 -->
   <div class="container1">

      <nav class="navbar navbar-default">
         <div class="container-fluid1" >
            <div class="navbar-header1">
               <button type="button" class="navbar-toggle collapsed"
                           data-target="#bs-example-navbar-collapse-1" aria-expanded="flase"
                              data-toggle="collapse">
                  <span class="sr-only"></span> 
               <!-- 반응형 웹 ==> 페이지 크기를 줄였을 때 메뉴 세줄로 나타나게 하는 코드 -->
                  <span class="icon-bar"></span> 
                  <span class="icon-bar"></span> 
                  <span class="icon-bar"></span>
               </button>
               <a href="#" class="navbar-brand">Star Rail</a>
            </div>
         
            <div class="collapse navbar-collapse1"   id="bs-example-navbar-collapse-1">
               <!-- 메인 메뉴 -->
               <ul class="nav navbar-nav1">
                  <li class="active">
                     <a href="#">소개<span class="sr-only"></span></a>
                  </li>

                  <li><a href="/starrail/map/search">지도 추천 페이지</a></li>
                  <li><a href="#">동행</a></li>

                  <li><a href="#">추천</a></li>
                  <li><a href="#">플래너 공유</a></li>
                  <li><a href="#">여행 후기</a></li>
                  <li><a href="#">여행 경비</a></li>
                  
                  
                  <!--  메뉴 눌렀을때 밑에 리스트 달아야 하면 이 코드 사용하기
                  <li class="dropdown">
                     <a href="#" class="dropdown-toggle"   data-toggle="dropdown" role="button" aria-haspopup="true"    aria-expanded="flase">
                        강의<span class="caret"></span>
                     </a>
                     <ul class="dropdown-menu">
                        <li><a href="#">C언어</a></li>
                        <li><a href="#">A언어</a></li>
                        <li><a href="#">B언어</a></li>
                        <li><a href="#">D언어</a></li>
                     </ul>
                  </li> -->
                  
                  
               </ul>
            
               <!-- 검색창 필요하면 이 코드 사용
               <form class="navbar-form navbar-right">
                  <div class="form-group">
                     <input type="text" class="form-control" placeholder="내용을 입력하세요.">
                  </div>
                  <button type="submit" class="btn btn-default">검색</button>
               </form>-->
            
               <!-- 접속메뉴 -->
               <ul class="nav navbar-nav navbar-right">
                  <li class="dropdown">
                     <a id="a" href="#"   class="dropdown-toggle" data-toggle="dropdown" role="button"
                           aria-haspopup="true" aria-expanded="flase">접속하기<span   class="caret"></span></a>
                     <ul class="dropdown-menu">
                        <li><a href="/starrail/main/login">로그인</a></li>
                        <li><a href="#">회원가입</a></li>
                     </ul>
                  </li>
                  <li><a href="#">예약하기</a></li>
                  <li><a href="#">마이 홈</a></li>
               </ul>            
            </div>
         </div>
      </nav>
   </div>
<!--  header영역 끝 -->


</body>
</html>