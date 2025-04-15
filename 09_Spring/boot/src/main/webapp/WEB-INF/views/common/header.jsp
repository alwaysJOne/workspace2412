<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	
	<!-- JavaScript -->
    <script src="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/alertify.min.js"></script>

    <!-- CSS -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/alertify.min.css"/>
    <!-- Default theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/default.min.css"/>
    <!-- Semantic UI theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/semantic.min.css"/>
    <!-- Bootstrap theme -->
    <link rel="stylesheet" href="//cdn.jsdelivr.net/npm/alertifyjs@1.13.1/build/css/themes/bootstrap.min.css"/>


    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <style>
        div{box-sizing: border-box;}
        #header{
            width:80%;
            height:100px;
            padding-top: 20px;
            margin:auto;
        }
        #header>div{width:100%; margin-bottom:10px}
        #header_1{height:40%;}
        #header_2{height:60%;}

        #header_1>div{
            height:100%;
            float:left;
        }
        #header_1_left{width:150px;position:relative;}
        #header_1_center{width:calc(100% - 420px);}
        #header_1_right{width:270px;}

        #header_1_left>img{height:80%;position:absolute;margin:auto;top:0;bottom:0;right: 0;left:0;}
        #header_1_right{text-align: right; line-height:35px; font-size: 12px;}
        #header_1_right>a{margin: 5px;}
        #header_1_right>a:hover{cursor: pointer;}

        #header_2>ul{width:100%; height:100%; list-style-type: none; margin: auto; padding:0;}
        #header_2>ul>li{float:left; width:25%; height:100%; line-height: 55px; text-align:center;}
        #header_2>ul>li a{text-decoration: none; color:black; font-size: 18px; font-weight: 900;}

        #header_2{border-top:1px solid lightgray}

        #header a{text-decoration:none; color:black}

        /* 세부 페이지마다 공통적으로 유지할 style */
        .content{
            background-color:rgb(247, 245, 245);
            width:80%;
            margin:auto;
        }
        .innerOuter{
            border:1px solid lightgray;
            width:80%;
            margin:auto;
            padding:5% 10%;
            background:white;
        }

        .gsi-material-button {
          -moz-user-select: none;
          -webkit-user-select: none;
          -ms-user-select: none;
          -webkit-appearance: none;
          background-color: WHITE;
          background-image: none;
          border: 1px solid #747775;
          -webkit-border-radius: 4px;
          border-radius: 4px;
          -webkit-box-sizing: border-box;
          box-sizing: border-box;
          color: #1f1f1f;
          cursor: pointer;
          font-family: 'Roboto', arial, sans-serif;
          font-size: 14px;
          height: 40px;
          letter-spacing: 0.25px;
          outline: none;
          overflow: hidden;
          padding: 0 12px;
          position: relative;
          text-align: center;
          -webkit-transition: background-color .218s, border-color .218s, box-shadow .218s;
          transition: background-color .218s, border-color .218s, box-shadow .218s;
          vertical-align: middle;
          white-space: nowrap;
          width: auto;
          max-width: 400px;
          min-width: min-content;
        }

        .gsi-material-button .gsi-material-button-icon {
          height: 20px;
          margin-right: 12px;
          min-width: 20px;
          width: 20px;
        }

        .gsi-material-button .gsi-material-button-content-wrapper {
          -webkit-align-items: center;
          align-items: center;
          display: flex;
          -webkit-flex-direction: row;
          flex-direction: row;
          -webkit-flex-wrap: nowrap;
          flex-wrap: nowrap;
          height: 100%;
          justify-content: space-between;
          position: relative;
          width: 100%;
        }

        .gsi-material-button .gsi-material-button-contents {
          -webkit-flex-grow: 1;
          flex-grow: 1;
          font-family: 'Roboto', arial, sans-serif;
          font-weight: 500;
          overflow: hidden;
          text-overflow: ellipsis;
          vertical-align: top;
        }

        .gsi-material-button .gsi-material-button-state {
          -webkit-transition: opacity .218s;
          transition: opacity .218s;
          bottom: 0;
          left: 0;
          opacity: 0;
          position: absolute;
          right: 0;
          top: 0;
        }

        .gsi-material-button:disabled {
          cursor: default;
          background-color: #ffffff61;
          border-color: #1f1f1f1f;
        }

        .gsi-material-button:disabled .gsi-material-button-contents {
          opacity: 38%;
        }

        .gsi-material-button:disabled .gsi-material-button-icon {
          opacity: 38%;
        }

        .gsi-material-button:not(:disabled):active .gsi-material-button-state,
        .gsi-material-button:not(:disabled):focus .gsi-material-button-state {
          background-color: #303030;
          opacity: 12%;
        }

        .gsi-material-button:not(:disabled):hover {
          -webkit-box-shadow: 0 1px 2px 0 rgba(60, 64, 67, .30), 0 1px 3px 1px rgba(60, 64, 67, .15);
          box-shadow: 0 1px 2px 0 rgba(60, 64, 67, .30), 0 1px 3px 1px rgba(60, 64, 67, .15);
        }

        .gsi-material-button:not(:disabled):hover .gsi-material-button-state {
          background-color: #303030;
          opacity: 8%;
        }
    </style>
</head>
<body>

	<c:if test="${ not empty alertMsg}">
		<script>
			alert("${alertMsg}");
		</script>
		<c:remove var="alertMsg" scope="session"/>
	</c:if>

	<div id="header">
        <div id="header_1">
            <div id="header_1_left">
                <img src="https://www.iei.or.kr/resources/images/common/top_logo_s.jpg" alt="" onclick="location.href='${ pageContext.servletContext.contextPath }';">
            </div> 
            <div id="header_1_center"></div>
            <div id="header_1_right">
	            <c:choose>
	            	<c:when test="${empty loginUser}">
		                <!-- 로그인 전 -->
		                <a href="enrollForm.me">회원가입</a>
		                <a data-toggle="modal" data-target="#loginModal">로그인</a> 
	            	</c:when>
	                <c:otherwise>
		                <!-- 로그인 후 -->
		                <label>${loginUser.userName}님 환영합니다</label> &nbsp;&nbsp;
		                <a href="myPage.me">마이페이지</a>
		                <a href="logout.me">로그아웃</a>
	                </c:otherwise>
	            </c:choose>         
            </div>
        </div>
        <div id="header_2">
            <ul>
                <li><a href="list.fo">설문게시판</a></li>
                <li><a href="main.air">대기오염정보</a></li>
                <li><a href="list.bo">자유게시판</a></li>
                <li><a href="">사진게시판</a></li>
            </ul>
        </div>
    </div>

    <!-- 로그인 클릭 시 뜨는 모달  -->
    <div class="modal fade" id="loginModal">
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Login</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button> 
            </div>

            <form action="login.me" method="post">
                <!-- Modal Body -->
                <div class="modal-body">
                    <label for="userId" class="mr-sm-2">ID :</label>
                    <input type="text" class="form-control mb-2 mr-sm-2" placeholder="Enter ID" id="userId" name="userId"> 
                    <input type="checkbox" name="saveId" id="saveId" checked>
                    <label for="saveId" class="mr-sm-2">아이디저장</label>
                    <br>
                    <label for="userPwd" class="mr-sm-2">Password:</label>
                    <input type="password" class="form-control mb-2 mr-sm-2" placeholder="Enter password" id="userPwd" name="userPwd">
                </div>
                
                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="button" class="gsi-material-button" onclick="googleLoginClick()">
                        <div class="gsi-material-button-state"></div>
                        <div class="gsi-material-button-content-wrapper">
                            <div class="gsi-material-button-icon">
                                <svg version="1.1" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 48 48" xmlns:xlink="http://www.w3.org/1999/xlink" style="display: block;">
                                    <path fill="#EA4335" d="M24 9.5c3.54 0 6.71 1.22 9.21 3.6l6.85-6.85C35.9 2.38 30.47 0 24 0 14.62 0 6.51 5.38 2.56 13.22l7.98 6.19C12.43 13.72 17.74 9.5 24 9.5z"></path>
                                    <path fill="#4285F4" d="M46.98 24.55c0-1.57-.15-3.09-.38-4.55H24v9.02h12.94c-.58 2.96-2.26 5.48-4.78 7.18l7.73 6c4.51-4.18 7.09-10.36 7.09-17.65z"></path>
                                    <path fill="#FBBC05" d="M10.53 28.59c-.48-1.45-.76-2.99-.76-4.59s.27-3.14.76-4.59l-7.98-6.19C.92 16.46 0 20.12 0 24c0 3.88.92 7.54 2.56 10.78l7.97-6.19z"></path>
                                    <path fill="#34A853" d="M24 48c6.48 0 11.93-2.13 15.89-5.81l-7.73-6c-2.15 1.45-4.92 2.3-8.16 2.3-6.26 0-11.57-4.22-13.47-9.91l-7.98 6.19C6.51 42.62 14.62 48 24 48z"></path>
                                    <path fill="none" d="M0 0h48v48H0z"></path>
                                </svg>
                            </div>
                            <span class="gsi-material-button-contents">Sign in</span>
                            <span style="display: none;">Sign in with Google</span>
                        </div>
                    </button>
                    <button type="submit" class="btn btn-primary">로그인</button>
                    <button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
                </div> 
            </form>
            </div>
        </div>
    </div>
    <script>
        function googleLoginClick(){
            //https://accounts.google.com/o/oauth2/v2/auth로 요청을 보내야한다.
            //client_id와같은 필수값이 필요하여 서버로부터 가져와서 요청을 보내겠다.
            $.ajax({
              url: "/api/config/google/login",
              success: function (res){
                console.log(res);
                const url = 'https://accounts.google.com/o/oauth2/v2/auth?client_id='
                + res.clientId
                + "&redirect_uri=" + res.redirectUrl
                + "&response_type=code"
                + "&scope=email profile https://www.googleapis.com/auth/drive";

                location.href = url;

              }, error: function (err){
                console.log("login config info ajax 실패")
              }
            })
        }
    </script>

    <br clear="both">
</body>
</html>