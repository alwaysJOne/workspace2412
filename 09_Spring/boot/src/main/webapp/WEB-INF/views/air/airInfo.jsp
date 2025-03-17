<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <jsp:include page="../common/header.jsp" />

    <div class="content">
      <br><br>
      <div class="innerOuter">
        <h2>실시간 대기오염 정보</h2>
        <br>

        지역 :
        <select id="location">
          <option>서울</option>
          <option>경기</option>
          <option>대전</option>
          <option>대구</option>
          <option>부산</option>
          <option>제주</option>
        </select>

        <button class="btn btn-sm btn-primary" onclick="getAirStatus()">해당지역 대기오염 정보</button>

        <table id="air-info" class="table" align="center">
          <thead>
            <tr>
              <th>측정소명</th>
              <th>측정일시</th>
              <th>통합대기환경수치</th>
              <th>미세먼지(PM10) 농도</th>
              <th>일산화탄소 농도</th>
              <th>이산화질소 농도</th>
              <th>오존 지수</th>
            </tr>
          </thead>
          <tbody>
            <%-- 공공데이터로부터 받아온 데이터  --%>
          </tbody>
        </table>
      </div>
    </div>
    <script>
      function getAirStatus(){
        //지역명을 포함해서 데이터 요청
        const location = document.querySelector("#location").value;
        <%--const serviceKey = "${airServiceKey}";--%>
        <%--getAirInfo({--%>
        <%--  location: location,--%>
        <%--  serviceKey: serviceKey--%>
        <%--}, drawAirInfo);--%>

        getAirAPI({
          location: location,
          count: 100
        }, drawAirInfo);
      }

      //서버를 통해 요청
      function getAirAPI(data, callback){
        $.ajax({
          url: "/api/air",
          data: data,
          dataType: "json",
          success: function (result){
            console.log("API 응답 : ", result);
            callback(result);
          },
          error: function (err){
            console.log("air ajax 에러발생 : ", err);
          }
        })
      }

      //클라이언트에서 직접 요청
      function getAirInfo(data, callback){
        $.ajax({
          url: "http://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty",
          data: {
            //api-key는 외부로 노출시 api요청이 남용될 가능성이 있으므로 server에서 랜더링시 넣어주도록 하자
            // serviceKey: "8h1yVur/0C1jzB+QnTVFLYlJk9Gfckktb8MFl25lzXaujB8wU9qgmg5abLreCLJnE2J/OK4DW5bqq0p4FPIEqw==",
            serviceKey: data.serviceKey,
            returnType: "json",
            sidoName: data.location,
            numOfRows: 100
          },
          dataType: "json",
          success: function (result){
            console.log("API 응답 : ", result);
            callback(result);
          },
          error: function (err){
            console.log("air ajax 에러발생 : ", err);
          }
        })
      }

      function drawAirInfo(airInfo) {
        const items = airInfo.response.body.items;

        let info = "";
        for (let air of items) {
          info += "<tr>"
                  + "<td>" + air.stationName + "</td>"
                  + "<td>" + air.dataTime + "</td>"
                  + "<td>" + air.khaiValue + "</td>"
                  + "<td>" + air.pm10Value + "</td>"
                  + "<td>" + air.coValue + "</td>"
                  + "<td>" + air.no2Value + "</td>"
                  + "<td>" + air.o3Grade + "</td>"
                + "</tr>";
        }

        const tbody = document.querySelector("#air-info tbody");
        tbody.innerHTML = info;
      }
    </script>
</body>
</html>
