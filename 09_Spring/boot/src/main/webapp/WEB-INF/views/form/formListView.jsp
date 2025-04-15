<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>설문게시글목록</title>
  <style>
    #boardList {text-align:center;}
    #boardList>tbody>tr:hover {cursor:pointer;}

    #pagingArea {width:fit-content; margin:auto;}

    #searchForm {
      width:80%;
      margin:auto;
    }
    #searchForm>* {
      float:left;
      margin:5px;
    }
    .select {width:20%;}
    .text {width:53%;}
    .searchBtn {width:20%;}
  </style>
</head>
<body>

<jsp:include page="../common/header.jsp" />

<div class="content">
  <br><br>
  <div class="innerOuter" style="padding:5% 10%;">
    <h2>게시판</h2>
    <br>
    <!-- 로그인 후 상태일 경우만 보여지는 글쓰기 버튼 -->
    <c:if test="${not empty loginUser}">
      <a class="btn btn-secondary" style="float:right;" href="enrollForm.fo">설문등록</a>
      <br>
    </c:if>

    <br>
    <table id="boardList" class="table table-hover" align="center">
      <thead>
      <tr>
        <th>설문번호</th>
        <th>제목</th>
        <th>작성자</th>
        <th>작성일</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="f" items="${list}">
        <c:choose>
          <c:when test="${loginUser.userId != f.formWriter}">
            <tr onclick="window.open('${f.formResponseUrl}', '_blank')">
          </c:when>
          <c:otherwise>
            <tr onclick="window.open('${f.formDashBoardUrl}', '_blank')">
          </c:otherwise>
        </c:choose>
          <td>${f.formNo}</td>
          <td>${f.formTitle}</td>
          <td>${f.formWriter}</td>
          <td>${f.createDate}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>


    <br><br>
  </div>
  <br><br>

</div>

<jsp:include page="../common/footer.jsp" />

</body>
</html>