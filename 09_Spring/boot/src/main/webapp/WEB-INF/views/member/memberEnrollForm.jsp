<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

	<!-- 메뉴바 -->
    <jsp:include page="../common/header.jsp" />

    <div class="content">
        <br><br>
        <div class="innerOuter">
            <h2>회원가입</h2>
            <br>

            <form action="insert.me" method="post" id="enrollForm">
                <div class="form-group">
                    <label for="userId">* ID : </label> 
                    <input type="text" class="form-control" id="userId" placeholder="Please Enter ID" name="userId" onkeyup="idCheck(this)" required>
                    <div id="checkResult" style="font-size:0.7em; display:none;"></div>
					<br>
                    <label for="userPwd">* Password : </label>
                    <input type="password" class="form-control" id="userPwd" placeholder="Please Enter Password" name="userPwd" required> <br>

                    <label for="checkPwd">* Password Check : </label>
                    <input type="password" class="form-control" id="checkPwd" placeholder="Please Enter Password" required> <br>

                    <label for="userName">* Name : </label>
                    <input type="text" class="form-control" id="userName" placeholder="Please Enter Name" name="userName" required> <br>

                    <label for="email"> &nbsp; Email : </label>
                    <input type="text" class="form-control" id="email" placeholder="Please Enter Email" name="email"> <br>

                    <label for="age"> &nbsp; Age : </label>
                    <input type="number" class="form-control" id="age" placeholder="Please Enter Age" name="age"> <br>

                    <label for="phone"> &nbsp; Phone : </label>
                    <input type="tel" class="form-control" id="phone" placeholder="Please Enter Phone (-없이)" name="phone"> <br>
                    
                    <label for="address"> &nbsp; Address : </label>
                    <input type="text" class="form-control" id="address" placeholder="Please Enter Address" name="address"> <br>
                    
                    <label> &nbsp; Gender : </label> &nbsp;&nbsp;
                    <input type="radio" id="Male" value="M" name="gender" checked>
                    <label for="Male">남자</label> &nbsp;&nbsp;
                    <input type="radio" id="Female" value="F" name="gender">
                    <label for="Female">여자</label> &nbsp;&nbsp;
                </div> 
                <br>
                <div class="btns" align="center">
                    <button type="submit" class="btn btn-primary" disabled>회원가입</button>
                    <button type="reset" class="btn btn-danger">초기화</button>
                </div>
            </form>
        </div>
        <br><br>   
     

    </div>

    <!-- 푸터바 -->
    <jsp:include page="../common/footer.jsp" />

    <script>
        window.onload = function (){
          //url의 쿼리파라미터 값을 가져온다.
          const params = new URLSearchParams(location.search);

          const memberId = params.get("memberId");
          if(memberId != null){
            const idInput = document.querySelector("#enrollForm #userId");
            idInput.value = memberId;
            idCheck(idInput);
          }
        }
        let eventFlag;
        function idCheck(idInput){
            const id = idInput.value;

            //id.trim() -> 공백제거
            if(id.trim().length >= 5){
                clearTimeout(eventFlag); //아직 실행되지않은 setTimeout 취소
                eventFlag = setTimeout(function (){
                                getIdCheck({checkId : id}, drawIdCheckText)
                            },500)
            } else {
                document.querySelector("#checkResult").style.display = "none";
                document.querySelector("#enrollForm button[type='submit']").disabled = true;
            }
        }

        function getIdCheck(data, callback){
            $.ajax({
                url: "/api/member/id",
                data: data,
                success: function (res){
                    callback(res);
                }, error: function (){
                    console.log("아이디 중복체크 ajax 실패");
                }
            })
        }

        function drawIdCheckText(isCheck){
            const submitBtn = document.querySelector("#enrollForm button[type='submit']");
            const checkResult = document.querySelector("#checkResult");
            checkResult.style.display = "block";
            if(isCheck === "NNNNN"){
                checkResult.style.color = "red";
                checkResult.innerText = "이미 사용중인 아이디입니다.";
                submitBtn.disabled = true;
            } else {
                checkResult.style.color = "green";
                checkResult.innerText = "사용 가능한 아이디입니다.";
                submitBtn.disabled = false;
            }
        }
    </script>
    
</body>
</html>