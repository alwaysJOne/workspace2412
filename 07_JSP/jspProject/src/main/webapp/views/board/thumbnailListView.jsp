<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .outer {
            background: #4b89fc;
            color: white;
            width: 1000PX;
            margin: auto;
            margin-top: 50px;
            padding: 10px 0px 50px;
        }
        .list-area{
            max-width: 850px;
            min-height: 500px;
            margin: auto;
        }

        .thumbnail{
            display: inline-block;
            border: 1px solid white;
            width: 250px;
            padding: 12px;
            margin: 14px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
        <br>
        <h2 align="center">사진게시판</h2>
        <br>

        <div class="list-area">
            <div class="thumbnail" align="center">
                <img width="200px" height="150px" src="/jp/resources/board-upfile/kh_174071454643638776.jpg" alt="썸네일이미지">
                <p>
                    <span>No. 25 안녕하세요</span><br>
                    조회수 : 200
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img width="200px" height="150px" src="/jp/resources/board-upfile/kh_174071454643638776.jpg" alt="썸네일이미지">
                <p>
                    <span>No. 25 안녕하세요</span><br>
                    조회수 : 200
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img width="200px" height="150px" src="/jp/resources/board-upfile/kh_174071454643638776.jpg" alt="썸네일이미지">
                <p>
                    <span>No. 25 안녕하세요</span><br>
                    조회수 : 200
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img width="200px" height="150px" src="/jp/resources/board-upfile/kh_174071454643638776.jpg" alt="썸네일이미지">
                <p>
                    <span>No. 25 안녕하세요</span><br>
                    조회수 : 200
                </p>
            </div>
            <div class="thumbnail" align="center">
                <img width="200px" height="150px" src="/jp/resources/board-upfile/kh_174071454643638776.jpg" alt="썸네일이미지">
                <p>
                    <span>No. 25 안녕하세요</span><br>
                    조회수 : 200
                </p>
            </div>
        </div>
	</div>
</body>
</html>