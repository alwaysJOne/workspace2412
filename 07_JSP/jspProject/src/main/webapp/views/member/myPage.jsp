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

		.outer table{
			margin: auto;
		}
	</style>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">내 정보</h2>
		<form action="" method="post" id="update-form">
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" name="userId" maxlength="15" readonly value="${loginUser.userId}"></td>
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" maxlength="8" readonly value="${loginUser.userName}"></td>
					<td></td>
				</tr>
				<tr>
					<td>전화번호</td>
					<td><input type="text" name="phone" placeholder="- 포함해서 입력" value="${loginUser.phone}"></td>
					<td></td>
				</tr>
				<tr>
					<td>이메일</td>
					<td><input type="text" name="email" value="${loginUser.email}"></td>
					<td></td>
				</tr>
				<tr>
					<td>주소</td>
					<td><input type="text" name="address" value="${loginUser.address}"></td>
					<td></td>
				</tr>
				<tr>
					<td>관심분야</td>
					<td colspan="2">
						<label>
							<input type="checkbox" name="interest" value="운동">
							운동
						</label>
						<label>
							<input type="checkbox" name="interest" value="등산">
							등산
						</label>
						<label>
							<input type="checkbox" name="interest" value="낚시">
							낚시
						</label>
						<br>
						<label>
							<input type="checkbox" name="interest" value="요리">
							요리
						</label>
						<label>
							<input type="checkbox" name="interest" value="게임">
							게임
						</label>
						<label>
							<input type="checkbox" name="interest" value="영화">
							영화
						</label>
						<label>
							<input type="checkbox" name="interest" value="기타">
							기타
						</label>
					</td>
				</tr>
			</table>
			<script>
				const interest = "${loginUser.interest}"; //"낚시,등산"
				const inputArr = document.querySelectorAll("input[name=interest]");
				
				for(let input of inputArr){
					if(interest.includes(input.value)){
						input.checked = true;
					}
				}
			</script>

			<br><br>

			<div align="center">
				<button type="submit">정보수정</button>
				<button type="button">비밀번호 변경</button>
				<button type="button">회원탈퇴</button>
			</div>
		</form>
	</div>

	<script>
		function checkPwd(){
			const pwd = document.querySelector("#enroll-form input[name=userPwd]").value;
			const pwdCheck = document.querySelector("#enroll-form input[name=userPwdCheck]").value;

			if(pwd !== pwdCheck){
				alert("비밀번호가 일치하지 않습니다.");
				return false;
			}
		}
	</script>
</body>
</html> 