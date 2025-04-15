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
                           <td colspan="3"><input type="text" id="title" class="form-control" name="formTitle" required></td>
                       </tr>
                       <tr>
                           <th><label for="writer">작성자</label></th>
                           <td colspan="3"><input type="text" id="writer" class="form-control" value="${loginUser.userId }" name="formWriter" readonly></td>
                       </tr>
                   </thead>
                    <tbody>

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
        window.onload = function (){
          //구글로부터 form목록을 불러오기위해 우선 accessToken정보가 있는 우리 서버로 요청을 보낸다.
          $.ajax({
            url: "/api/google/forms",
            dataType: "json",
            success: function (result){
              drawFormTbody(result);
            }, error: function () {
                console.log("form ajax 요청 실패");
            }
          })
        }


        function drawFormTbody(list) {

          const tableBody = document.querySelector("#enrollForm tbody");
          for(let i in list) {
            const form = list[i];

            const row = document.createElement("tr");

            const radioCell = document.createElement("td");
            row.appendChild(radioCell);

            const radioButton = document.createElement("input");
            radioButton.type = "radio";
            radioButton.name = "formId";
            radioButton.value = form.id;
            radioCell.appendChild(radioButton);
            if(i == 0){
              radioButton.checked = true;
            }

            const titleCell = document.createElement("td");
            titleCell.setAttribute("colspan", "2");
            titleCell.innerHTML = form.name;
            row.appendChild(titleCell);

            const createdTimeCell = document.createElement("td");
            createdTimeCell.innerHTML = formatDate(form.createdTime.value);
            row.appendChild(createdTimeCell);

            tableBody.appendChild(row);
          }
        }

        function formatDate(timestamp){
            const date = new Date(timestamp);
            return date.getFullYear() + "년 " + (date.getMonth() + 1) + "월 " + date.getDate() + "일";
        }
    </script>
</body>
</html>