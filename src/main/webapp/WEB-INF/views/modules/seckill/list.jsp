<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>秒杀列表页</title>
    <%@include file="/WEB-INF/views/include/tag.jsp" %>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading text-center">
            <h1>秒杀商品列表</h1>
        </div>
        <div class="panel-body">
            <table class="table">
                <thead >
                <tr >
                    <th>名称</th>
                    <th>库存</th>
                    <th>开始时间</th>
                    <th>结束时间</th>
                    <th>创建时间</th>
                    <th>详情</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="sk" items="${list}">
                    <tr>
                        <td>${sk.seckillName}</td>
                        <td>${sk.seckillNumber}</td>
                        <td>
                            <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td>
                            <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                        </td>
                        <td><a class="button button-glow button-rounded button-highlight" href="/seckill/${sk.seckillId}/details"
                               target="_blank">link</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
