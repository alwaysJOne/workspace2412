<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
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
        //서버로부터 조회수가 높은 게시글 5개를 조회해서 가져오기(ajax)
        //tbody요소에 추가
    </script>
    <jsp:include page="common/footer.jsp" />
</body>
</html>
