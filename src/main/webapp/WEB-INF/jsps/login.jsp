<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" class="is-AppPromotionBarVisible cssanimations csstransforms csstransitions flexbox no-touchevents no-mobile">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="apple-itunes-app" content="app-id=432274380">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="description" content="一个真实的网络问答社区，帮助你寻找答案，分享知识。">
    <meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <title>Forum - 与世界分享你的知识、经验和见解</title>
    <link rel="dns-prefetch" href="">
    <link rel="stylesheet" href="/resources/styles/login.css">
    <meta name="google-site-verification" content="FTeR0c8arOPKh8c5DYh_9uu98_zJbaWw53J-Sch9MTg">
    <meta name="baidu-site-verification" content="KPFppAFoYF4Kkdv9">
    <meta property="qc:admins" content="00544670776201056375">
    <link rel="canonical" href="">
    <meta id="znonce" name="znonce" content="5b6d120cad4b45f3ad5a8683c30594c0">
    <!--[if lt IE 9]>
    <script src="https://static.zhihu.com/static/components/respond/dest/respond.min.js"></script>
    <link href="https://static.zhihu.com/static/components/respond/cross-domain/respond-proxy.html" id="respond-proxy" rel="respond-proxy" />
    <link href="/static/components/respond/cross-domain/respond.proxy.gif" id="respond-redirect" rel="respond-redirect" />
    <script src="/static/components/respond/cross-domain/respond.proxy.js"></script>
    <![endif]-->
    <script type="text/javascript" async="" src="/resources/scripts/za-0.1.1.min.js"></script>
    <script async="" src="https://ssl.google-analytics.com/ga.js"></script>
    <script src="/resources/scripts/instant.e7a17de6.js"></script>
    <script type="text/javascript">

        function regfc(){
            var form = document.getElementById("regloginform");
            form.action="/login/reg.do";
            form.submit();
        }
        function loginfc(){
            var form = document.getElementById("regloginform");
            form.action="/login/loginaction.do";
            form.submit();
        }
    </script>
    </head>

<body class="zhi  no-auth">
    <div class="index-main">
        <div class="index-main-body">
            <div class="index-header">
                <h1 class="logo hide-text"><img src="/resources/images/res/nk.png" alt=""></h1>
                <h2 class="subtitle">
                    ${empty msg ?  '与世界分享你的知识、经验和见解': msg}
                </h2>
            </div>

            <div class="desk-front sign-flow clearfix sign-flow-simple">
                <div class="view view-signin" data-za-module="SignInForm" style="display: block;">
                    <form id="regloginform"   method="post">
                        <input type="hidden" name="_xsrf" value="21aa1c8d254df2899b23ab9afbd62a53">
                        <div class="group-inputs">
                            <div class="email input-wrapper">
                                <input type="text" name="name" aria-label="手机号或邮箱" placeholder="手机号或邮箱" required="">
                            </div>
                            <div class="input-wrapper">
                                <input type="password" name="password" aria-label="密码" placeholder="密码" required="">
                            </div>
                            <div class="input-wrapper captcha-module" data-type="en">
                                <input id="captcha" name="" placeholder="验证码" required="" data-rule-required="true" data-msg-required="请填写验证码" class="is-ignoreValidation">
                                <div class="captcha-container">
                                    <img class="js-refreshCaptcha captcha" width="120" height="30" data-tip="s$t$看不清楚？换一张" alt="验证码">
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="next" value="${next}">
                        <div class="button-wrapper command clearfix">
                            <button class="sign-button submit" id="login" onclick="loginfc()">登录</button>
                            <button class="sign-button submit" id="reg" onclick="regfc()">注册</button>
                        </div>
                        <div class="signin-misc-wrapper clearfix">
                            <label class="remember-me">
                                <input type="checkbox" name="rememberme" checked="" value="false"> 记住我
                            </label>
                            <a class="unable-login" href="#">无法登录?</a>
                        </div>
                    </form>
                </div>
                <div class="view view-signup selected" data-za-module="SignUpForm" style="display: none;">
                    <form class="zu-side-login-box" action="" id="sign-form-1" autocomplete="off" novalidate="novalidate">
                        <input type="password" hidden="">
                        <input type="hidden" name="_xsrf" value="21aa1c8d254df2899b23ab9afbd62a53">
                        <div class="group-inputs">
                            <div class="name input-wrapper">
                                <input required="" type="text" name="fullname" aria-label="姓名" placeholder="姓名">
                            </div>
                            <div class="email input-wrapper">
                                <input required="" type="text" class="account" name="phone_num" aria-label="手机号（仅支持中国大陆）" placeholder="手机号（仅支持中国大陆）">
                            </div>
                            <div class="input-wrapper">
                                <input required="" type="password" name="password" aria-label="密码" placeholder="密码（不少于 6 位）">
                            </div>
                            <div class="Captcha input-wrapper" data-type="cn" data-za-module="Captcha">
                                <div class="Captcha-operate">
                                    <input type="hidden" name="captcha" required="" data-rule-required="true" data-msg-required="请点击图中倒立的文字" value="">
                                    <input type="hidden" name="captcha_type" value="cn" required="">
                                    <label class="Captcha-prompt">请点击图中倒立的文字</label>
                                    <span class="Captcha-refresh js-refreshCaptcha sprite-index-icon-refresh"></span>
                                </div>
                                <div class="Captcha-imageConatiner">
                                    <img class="Captcha-image" alt="验证码" src="/resources/images/res/captcha.gif">
                                </div>
                            </div>
                        </div>
                        <div class="button-wrapper command">
                            <button class="sign-button submit" type="submit">注册牛客</button>
                        </div>
                    </form>
                    <div class="QRCode">
                        <button class="QRCode-toggleButton">
                            <span class="sprite-index-icon-qrcode"></span>
                            <span class="QRCode-toggleButtonText ">下载牛客 App</span>
                        </button>
                        <div class="QRCode-card">
                            <div class="QRCode-image"></div>
                            <div class="sprite-index-icon-arrow"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>