/**
 * Created by nunu on 4/20/17.
 */
// 存放主要的交互代码
//     javascript模块化
var seckill = {

    //封装秒杀相关ajax的url
    URL:{
        getNowTime:function () {
            return "/seckill/time/now";
        },
        getExposureUrl:function (seckillId) {
            return '/seckill/' + seckillId + '/url';
        },
        execution:function (seckillId, md5) {
            return '/seckill/' + seckillId + '/' + md5 + '/execution';
        }
    },
    seckillHandlerKill:function(seckillId,node){
        //开始秒杀逻辑
        //先拿到秒杀地址

        node.hide().html('<button class="button button-3d bg-primary" id="killButton">开始秒杀</button>');

        $.post(seckill.URL.getExposureUrl(seckillId),{},function (result) {
            if(result && result['status']){
                //获取成功
                var exposure = result['data'];
                if(exposure['exposed']){
                    //开启秒杀
                    //获取秒杀地址
                    var md5 = exposure['md5'];
                    var killUrl = seckill.URL.execution(seckillId,md5);
                    console.log("killUrl" + killUrl);

                    //绑定一次点击事件
                    $('#killButton').one('click',function () {
                        //1.先禁用秒杀按钮
                        $(this).addClass('disabled');
                        //2.发送秒杀请求执行秒杀
                        $.post(killUrl,{},function (result) {
                            if(result){
                                var execution = result['data'];
                                var state = execution['state'];
                                var stateInfo = execution['stateInfo'];
                                node.html('<span class="label label-success">' + stateInfo +'</span>');
                            }
                        });
                    });
                    node.show();
                }else{
                    //未开启秒杀
                    var end = exposure['endTime'];
                    var start = exposure['startTime'];
                    var now = exposure['nowTime'];
                    seckill.countdown(seckillId,end,start,now);
                }
            }
        });
    },
    //验证用户登录后开始展示倒计时逻辑
    countdown:function (seckillId, nowTime, startTime, endTime) {
        var seckillBox = $('#seckill-box');
        if(nowTime > endTime){//服务器时间大于秒杀结束时间，秒杀结束
            seckillBox.html("秒杀已结束");
        }else if(nowTime <= startTime){//秒杀还没开始
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime,function (event) {
                var format = event.strftime('秒杀计时： %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
            }).on('finish countdown', function () {
                //倒计时完成，获取秒杀地址，控制显示逻辑，执行秒杀
                seckill.seckillHandlerKill(seckillId,seckillBox);
            })
        }else{ //开始秒杀
            seckill.seckillHandlerKill(seckillId,seckillBox);
        }
    },
    validatePhone:function (phone) {
        if(phone && phone.length == 11 && !isNaN(phone)){
            return true;
        }else{
            return false;
        }
    },
    //详情页秒杀逻辑
    detail:{
        //初始化详情页
        init:function (params) {
            //手机验证和登录 计时交互
            //规划交互流程
            //在cookie中查找手机号
            var killPhone = Cookies.get('killPhone');
            // 验证cookie中的手机号码
            if(!seckill.validatePhone(killPhone)){
                var killPhoneModal = $('#killPhoneModal');
                killPhoneModal.modal({
                    show:true,//显示弹出层
                    backdrop:'static',//锁定窗体
                    keyboard:false//关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhoneKey').val();
                    if(seckill.validatePhone(inputPhone)){
                        // 写入cookie
                        Cookies.set('killPhone',inputPhone,{expires:7,path:'/seckill'});
                        //刷新页面
                        window.location.reload();
                    }else{
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号输出有误！</label>').show(300);
                    }
                });
            }else{//验证手机通过，展示倒计时逻辑

                var seckillId = params['seckillId'];
                var startTime = params['startTime'];
                var endTime = params['endTime'];

                $.get(seckill.URL.getNowTime(),{
                    //参数
                    },
                function (result) {
                    if(result && result['status']){
                        var nowTime = result['data'];
                        seckill.countdown(seckillId,nowTime,startTime,endTime);
                    }else{
                        console.log("resule" + result);
                    }
                });

            }
        }
    }
}