<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body onload="init()">
    <jsp:include page="common/header.jsp" />
    <div class="content">
        <br><br>

        <div class="innerOuter">
            <h4>게시글 Top 5</h4>
            <br>
            <table id="top5-board-list" class="table table-hover" align="center">
                <thead>
                    <tr>
                        <th>글번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>조회수</th>
                        <th>작성일</th>
                        <th>첨부파일</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td colspan="6" rowspan="4" align="center">
                            <div class="spinner-border text-primary"></div >
                        </td>
                    </tr>
                </tbody>
            </table>

        </div>
    </div>
    <script>
        function init(){
            //서버로부터 조회수가 높은 게시글 5개를 조회해서 가져오기(ajax)
            //tbody요소에 추가

            getTopBoardList({
                "order" : "count",
                "limit" : 5
            },drawBoardList);
        }

        function getTopBoardList(data, callback){
            $.ajax({
                url: "/api/board/topn",
                data: data,
                success: callback,
                error: function (err){
                    console.log("topn ajax실패")
                }
            })
        }

        function moveDetail(boardNo){
            location.href = "/detail.bo?bno=" + boardNo;
        }

        function drawBoardList(boardList) {
            const boardBody = document.querySelector("#top5-board-list tbody");

            let str = "";
            for (const b of boardList) {
                str += ("<tr onclick='moveDetail(" + b.boardNo + ")'>"
                            + "<td>" + b.boardNo + "</td>"
                            + "<td>" + b.boardTitle + "</td>"
                            + "<td>" + b.boardWriter + "</td>"
                            + "<td>" + b.count + "</td>"
                            + "<td>" + b.createDate + "</td>"
                            + "<td>" + (b.originName != null ? "★" : "") + "</td>" +
                        "</tr>")
            }

            boardBody.innerHTML = str;
        }
    </script>
    <jsp:include page="common/footer.jsp"/>
</body>
</html>
