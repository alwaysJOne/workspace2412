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

    .table-container {
      max-height: max-content; /* You can adjust the height */
      overflow-y: auto;
    }

    tbody {
      display: block;
      max-height: 500px; /* Set the height of tbody */
      overflow-y: scroll;
    }

    thead, tbody tr {
      display: table;
      width: 100%;
      table-layout: fixed;
    }
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
                <input type="hidden" name="formDashboardUrl" id="form-dashboard-url">
                <div class="table-container">
                    <table align="center" class="table">
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
                </div>

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
        $.ajax({
          url: "/api/google/forms",
          dataType: "json",
          success: function (result){
            console.log("API 응답 : ", result);
            drawFormTbody(result);
          },
          error: function (err){
            console.log("air ajax 에러발생 : ", err);
          }
        })
      }

      function drawFormTbody(list) {
        const tableBody = document.querySelector("#enrollForm tbody");
        const dashboardInput = document.querySelector("#form-dashboard-url");
        list.forEach((form, index) => {
          const row = document.createElement("tr");

          const radioCell = document.createElement("td");
          row.appendChild(radioCell);

          const radioButton = document.createElement("input");
          radioButton.type = "radio";
          radioButton.name = "formResponseUrl"; // Same name for single selection
          radioButton.value = `https://docs.google.com/forms/d/`+form.id+`/viewform`;
          radioCell.appendChild(radioButton);
          if(index === 0){
            radioButton.checked = true;
            dashboardInput.value = `https://docs.google.com/forms/d/`+form.id+`/edit#responses`;
          }

          radioButton.onchange = function (){
            console.log(form.id)
            dashboardInput.value = `https://docs.google.com/forms/d/`+form.id+`/edit#responses`;
          }

          const nameCell = document.createElement("td");
          nameCell.setAttribute("colspan", "2");
          nameCell.textContent = form.name;
          row.appendChild(nameCell);

          const createdTimeCell = document.createElement("td");
          createdTimeCell.textContent = formatDate(form.createdTime.value);
          row.appendChild(createdTimeCell);

          tableBody.appendChild(row);
        });
      }

      // Function to format date from timestamp
      function formatDate(timestamp) {
        const date = new Date(timestamp);
        const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
        return date.toLocaleDateString('ko-KR', options);
      }
    </script>

</body>
</html>