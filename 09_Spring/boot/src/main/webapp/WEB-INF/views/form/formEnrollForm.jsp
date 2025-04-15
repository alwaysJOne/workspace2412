<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 작성</title>
<style>
	#enrollForm>table {width:100%;}
	#enrollForm>table * {margin:5px;}
</style>
</head>
<body>

	<jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>설문 작성하기</h2>
            <br>

            <form id="enrollForm" method="post" action="insert.fo">
                <table align="center">
                   <thead>
                       <tr>
                           <th><label for="title">제목</label></th>
                           <td><input type="text" id="title" class="form-control" name="formTitle" required></td>
                       </tr>
                       <tr>
                           <th><label for="writer">작성자</label></th>
                           <td><input type="text" id="writer" class="form-control" value="${loginUser.userId }" name="boardWriter" readonly></td>
                       </tr>
                   </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="radio">
                            </td>
                            <td colspan="2">
                                점심메뉴 설문
                            </td>
                            <td>
                                2025년 4월 15일
                            </td>
                        </tr>
                    </tbody>
                </table>
                <br>

                <div align="center">
                    <button type="submit" class="btn btn-primary">등록하기</button>
                    <button type="reset" class="btn btn-danger">취소하기</button>
                </div>
            </form>
        </div>
        <br><br>
 
    </div>
    
    <jsp:include page="../common/footer.jsp" />

    <script>
        window.onload
    </script>
</body>
</html>