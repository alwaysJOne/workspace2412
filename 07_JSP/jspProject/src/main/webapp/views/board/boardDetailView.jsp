<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
            border: 1px solid white;
            text-align: center;
        }

        .list-area td, .list-area th{
            border: 1px solid white; 
        }

		.list-area select, .list-area input, .list-area textarea{
			width: 100%;
			box-sizing: border-box;
			background: #ffffff;
    		color: black;
		}
	</style>
</head>

<body>
	<%@ include file="../common/menubar.jsp" %>

	<div class="outer">
		<br>
		<h2 align="center">일반게시글 상세보기</h2>
		<br>

		
        <table align="center" class="list-area">
            <tr>
                <th width="70">카테고리</th>
                <td width="70">${board.categoryName}</td>
                <th width="70">제목</th>
                <td width="350">${board.boardTitle }</td>
            </tr>
            <tr>
                <th>작성자</th>
                <td>${board.userId}</td>
                <th>작성일</th>
                <td>${board.createDate}</td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                   <p style="height: 200px;">
                        ${board.boardContent}
                   </p>
                </td>
            </tr>
            <tr>
                <th>첨부파일</th>
                <td colspan="3">
                    <c:choose>
                    	<c:when test="${empty attachment}">
                    		첨부파일이 없습니다.
                    	</c:when>
                    	<c:otherwise>
                    		<a download="${attachment.originName }" 
                    		   href="${pageContext.request.contextPath}/${attachment.filePath}${attachment.changeName}">
                    		   ${attachment.originName}
                    		</a>
                    	</c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>

        <br>

        <div align="center">
            <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/list.bo?cpage=1">목록가기</a>
            <c:if test="${loginUser != null && loginUser.userId == board.userId}">
	            <a class="btn btn-sm btn-primary" href="${pageContext.request.contextPath}/updateForm.bo?bno=${board.boardNo}">수정하기</a>
	            <a class="btn btn-sm btn-primary">삭제하기</a>
            </c:if>
        </div>

	</div>
</body>

</html>