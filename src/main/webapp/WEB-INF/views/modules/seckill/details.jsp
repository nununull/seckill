<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>秒杀商品详情页</title>
    <%@include file="/WEB-INF/views/include/tag.jsp"%>
    <%@include file="/WEB-INF/views/include/head.jsp" %>
</head>
<body>
   <div class="container">
       <div class="panel-default">
           <div class="panel-heading text-center">
               <h1>秒杀商品详情页</h1>
           </div>
           <div class="panel-body text-center">
               <h2 class="text-danger">
                   <!-- 显示time图标 -->
                   <span class="glyphicon glyphicon-time"></span>
                   <!-- 展示倒计时 -->
                   <span class="glyphicon" id="seckill-box"></span>
               </h2>
           </div>
       </div>
   </div>
   <!-- 登录弹出层 -->
    <div class="modal fade" id="killPhoneModal">
        <!-- 弹出层模式 -->
        <div class="modal-dialog">
            <!-- 弹出层内容 -->
            <div class="modal-content">
                <!-- 弹出层头部 -->
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>
                    </h3>
                </div>
                <!-- 弹出层主要内容body部分 -->
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="killPhone" id="killPhoneKey"
                            placeholder="请填写手机号-。-！" class="form-control">
                        </div>
                    </div>
                </div>
                <!-- 弹出层底部footer -->
                <div class="modal-footer">
                    <%-- 验证信息 --%>
                    <span id="killPhoneMessage" class="glyphicon"></span>
                    <button type="button" id="killPhoneBtn" class="btn btn-success">
                        <span class="glyphicon glyphicon-phone"></span>
                        submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
<script src="/resources/script/seckill.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function () {
        seckill.detail.init({
            seckillId:${details.seckillId},
            startTime:${details.startTime.time},
            endTime:${details.endTime.time}
        });
    });
</script>
</html>
