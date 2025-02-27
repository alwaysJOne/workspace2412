<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Insert title here</title>
    <style>
        .outer{
			background: #4b89fc;
			color: white;
			width: 1000PX;
			margin: auto;
			margin-top: 50px;
			padding: 10px 0px 50px;
		}

        .list-area{
            border: 1px solid white;
            text-align: center;
        }

        .list-area tbody tr:hover{
            background: #72a3ff;
            cursor: pointer;
            font-size: 18px;
        }

        .list-area td, .list-area th{
            border: 1px solid white; 
        }
    </style>
</head>
<body>
    <%@ include file="../common/menubar.jsp" %>
   
    <div class="outer">
        <br>
        <h2 align="center">일반게시판</h2>
        <br>

        <table align="center" class="list-area">
            <thead>
                <th width="70">글번호</th>
                <th width="100">카테고리</th>
                <th width="300">제목</th>
                <th width="100">작성자</th>
                <th width="70">조회수</th>
                <th width="100">작성일</th>
            </thead>
            <tbody>
                <tr>
                    <td>150</td>
                    <td>게임</td>
                    <td>메이플 150 3분 달성법!</td>
                    <td>user01</td>
                    <td>153</td>
                    <td>2025.02.27</td>
                </tr>
                <tr>
                    <td>148</td>
                    <td>게임</td>
                    <td>메이플 150 10분 달성법!</td>
                    <td>user02</td>
                    <td>500</td>
                    <td>2025.02.26</td>
                </tr>
            </tbody>
        </table>

        <br><br>

        <div align="center">
            <button class="btn btn-sm btn-primary">&lt;이전</button>
            <button class="btn btn-sm btn-primary">1</button>
            <button class="btn btn-sm btn-primary">2</button>
            <button class="btn btn-sm btn-primary">3</button>
            <button class="btn btn-sm btn-primary">4</button>
            <button class="btn btn-sm btn-primary">5</button>
            <button class="btn btn-sm btn-primary">다음&gt;</button>
        </div>
    </div>
</body>
</html>