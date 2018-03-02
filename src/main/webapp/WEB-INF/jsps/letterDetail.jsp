<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" class="is-AppPromotionBarVisible cssanimations csstransforms csstransitions flexbox no-touchevents no-mobile">
    <head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>读《Web 全栈工程师的自我修养》 - web开发的愚人之旅 - 牛客网</title>
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <meta name="keywords" content="读《Web 全栈工程师的自我修养》">
    <meta name="description" content="阅读影浅分享的读《Web 全栈工程师的自我修养》，就在牛客网。">

    <link rel="stylesheet" type="text/css" href="/resources/styles/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/styles/font-awesome.min.css">

    <link rel="stylesheet" media="all" href="/resources/styles/letter.css">

    <script src="/resources/scripts/hm.js"></script>
    <script src="/resources/scripts/detail.js"></script>

    <script type="text/javascript" src="/resources/scripts/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/scripts/jquery.qrcode.min.js"></script>
        <script LANGUAGE="JavaScript">
            function openwin() {
                document.getElementById("win").style.display="";
            }
            function closeLogin(){
                document.getElementById("win").style.display="none";
            }
            function askaction() {
                var title = $("#zh-question-suggest-title-content").val();
                // alert(title);
                var content = $("#question_content").html();
                // alert(content);
                $.ajax({
                    url : "/Forum/question/addquestion.do",
                    type : "post",
                    data :  "title="+title+"&content="+content,
                    dataType : "json",
                    success : function(data){
                        if(data.code == 999) {
                            alert(data.msg);
                            location.href = "/login/tologin.do";
                        }
                        else {
                            if (data.code == 0)
                                alert(data.msg);
                            else
                                alert(data.msg);
                            location.href = "/Forum/home.do";
                        }
                    }
                });
            }
        </script>

    </head>
    <body class="zhi ">
    <div role="navigation" class="zu-top">
        <div class="zg-wrap modal-shifting clearfix" id="zh-top-inner">
            <a href="/" class="zu-top-link-logo" id="zh-top-link-logo" data-za-c="view_home" data-za-a="visit_home" data-za-l="top_navigation_zhihu_logo">Forum</a>
            <div class="top-nav-profile">
                <c:if test="${empty user}">
                    <ul class="topnav-noauth clearfix">
                        <li>
                            <a href="/login/tologin.do">注册/登录</a>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${not empty user}">
                    <a href="/Forum/userhome/${user.id}.do" class="zu-top-nav-userinfo " id=":0" role="button" aria-haspopup="true" aria-activedescendant="">
                        <span class="name">${user.name}</span>
                        <img class="Avatar" src="${user.head_url}" srcset="https://pic1.zhimg.com/da8e974dc_xs.jpg 2x" alt="${user.name}">
                        <span id="zh-top-nav-new-pm" class="zg-noti-number zu-top-nav-pm-count" style="visibility:hidden" data-count="0">
                    </span>
                    </a>
                    <ul class="top-nav-dropdown" id="top-nav-profile-dropdown" aria-labelledby=":0">
                        <li>
                            <a href="/Forum/userhome/${user.id}.do" tabindex="-1" id=":1">
                                <i class="zg-icon zg-icon-dd-home"></i>我的主页
                            </a>
                        </li>
                        <li>
                            <a href="/Forum/message.do" tabindex="-1" id=":2">
                                <i class="zg-icon zg-icon-dd-pm"></i>私信
                                <span id="zh-top-nav-pm-count" class="zu-top-nav-pm-count zg-noti-number" style="visibility:hidden" data-count="0">
                    </span>
                            </a>
                        </li>
                        <li>
                            <a href="/login/logout.do" tabindex="-1" id=":4">
                                <i class="zg-icon zg-icon-dd-logout"></i>退出
                            </a>
                        </li>
                    </ul>
                </c:if>
            </div>
            <button class="zu-top-add-question" id="zu-top-add-question" onclick="openwin()">提问</button>
            <div role="search" id="zh-top-search" class="zu-top-search">
                <form method="GET" action="https://nowcoder.com/search" id="zh-top-search-form" class="zu-top-search-form">
                <input type="hidden" name="type" value="content">
                <label for="q" class="hide-text">牛客搜索</label><input type="text" class="zu-top-search-input" id="q" name="q" autocomplete="off" value="" placeholder="搜索你感兴趣的内容..." role="combobox" aria-autocomplete="list">
                <button type="submit" class="zu-top-search-button"><span class="hide-text">搜索</span><span class="sprite-global-icon-magnifier-dark"></span></button>
                </form>
            </div>
            <div id="zg-top-nav" class="zu-top-nav">
                <ul class="zu-top-nav-ul zg-clear">
                    <li class="zu-top-nav-li current" id="zh-top-nav-home">
                    <a class="zu-top-nav-link" href="https://nowcoder.com/" id="zh-top-link-home" data-za-c="view_home" data-za-a="visit_home" data-za-l="top_navigation_home">首页</a>
                    </li>
                    <li class="zu-top-nav-li " id="zh-top-nav-explore">
                    <a class="zu-top-nav-link" href="https://nowcoder.com/explore">发现</a>
                    </li>
                    <li class="top-nav-noti zu-top-nav-li ">
                    <a class="zu-top-nav-link" href="javascript:;" id="zh-top-nav-count-wrap" role="button"><span class="mobi-arrow"></span>消息<span id="zh-top-nav-count" class="zu-top-nav-count zg-noti-number" style="display: none;">0</span></a>
                    </li>
                </ul>
                <div class="zu-top-nav-live zu-noti7-popup zg-r5px no-hovercard" id="zh-top-nav-live-new" role="popup" tabindex="0">
                    <div class="zu-top-nav-live-inner zg-r5px">
                        <div class="zu-top-live-icon">&nbsp;</div>
                        <div class="zu-home-noti-inner" id="zh-top-nav-live-new-inner">
                            <div class="zm-noti7-popup-tab-container clearfix" tabindex="0" role="tablist">
                            <button class="zm-noti7-popup-tab-item message" role="tab">
                            <span class="icon">消息</span>
                            </button>
                            <button class="zm-noti7-popup-tab-item user" role="tab">
                            <span class="icon">用户</span>
                            </button>
                            <button class="zm-noti7-popup-tab-item thanks" role="tab">
                            <span class="icon">赞同和感谢</span>
                            </button>
                            </div>
                        </div>
                        <div class="zm-noti7-frame-border top"></div>
                        <div class="zm-noti7-frame">
                            <div class="zm-noti7-content message zh-scroller" style="position: relative; overflow: hidden;">
                                <div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">
                                <div class="zm-noti7-content-inner">
                                <div class="zm-noti7-content-body">
                                <div class="zm-noti7-popup-loading">
                                <span class="noti-spinner-loading"></span>
                                </div>
                                </div>
                                </div>
                                </div></div>
                                <div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div>
                            </div>
                            <div class="zm-noti7-content user zh-scroller" style="display: none; position: relative; overflow: hidden;"><div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">
                            <div class="zm-noti7-content-inner">
                            <div class="zm-noti7-content-body">
                            <div class="zm-noti7-popup-loading">
                            <span class="noti-spinner-loading"></span>
                            </div>
                            </div>
                            </div>
                            </div></div><div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div></div>
                            <div class="zm-noti7-content thanks zh-scroller" style="display: none; position: relative; overflow: hidden;"><div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">
                            <div class="zm-noti7-content-inner">
                            <div class="zm-noti7-content-body">
                            <div class="zm-noti7-popup-loading">
                            <span class="noti-spinner-loading"></span>
                            </div>
                            </div>
                            </div>
                            </div></div><div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div></div>
                        </div>
                        <div class="zm-noti7-frame-border bottom"></div>
                        <div class="zm-noti7-popup-footer">
                            <a href="https://nowcoder.com/notifications" class="zm-noti7-popup-footer-all zg-right">查看全部 »</a>
                            <a href="https://nowcoder.com/settings/notification" class="zm-noti7-popup-footer-set" title="通知设置"><i class="zg-icon zg-icon-settings"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="zu-global-notify" id="zh-global-message" style="display:none">
        <div class="zg-wrap">
            <div class="zu-global-nitify-inner">
            <a class="zu-global-notify-close" href="javascript:;" title="关闭" name="close">x</a>
            <span class="zu-global-notify-icon"></span>
            <span class="zu-global-notify-msg"></span>
            </div>
        </div>
    </div>
    <div id="main">
        <div class="zg-wrap zu-main clearfix ">
            <ul class="letter-chatlist">
<li id="msg-item-4009580">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">今天 15:28</p>
<a href="javascript:void(0);" id="del-link" name="4009580">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?toCommentId=141625">"二维数组中的查找"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3993979">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-23 20:52</p>
<a href="javascript:void(0);" id="del-link" name="3993979">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?toCommentId=141384">"二维数组中的查找"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3969636">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-22 20:12</p>
<a href="javascript:void(0);" id="del-link" name="3969636">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?toCommentId=141023">"二维数组中的查找"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3951814">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-22 10:23</p>
<a href="javascript:void(0);" id="del-link" name="3951814">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/655a43d702cd466093022383c24a38bf?toCommentId=140827">"回文串"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3891213">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-17 21:25</p>
<a href="javascript:void(0);" id="del-link" name="3891213">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?toCommentId=139813">"二维数组中的查找"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3887263">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-17 17:09</p>
<a href="javascript:void(0);" id="del-link" name="3887263">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?toCommentId=139749">"二维数组中的查找"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3873768">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-16 21:53</p>
<a href="javascript:void(0);" id="del-link" name="3873768">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?toCommentId=139559">"二维数组中的查找"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3855466">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-15 22:23</p>
<a href="javascript:void(0);" id="del-link" name="3855466">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/655a43d702cd466093022383c24a38bf?toCommentId=139271">"回文串"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3844040">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-15 11:30</p>
<a href="javascript:void(0);" id="del-link" name="3844040">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?toCommentId=139031">"二维数组中的查找"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
<li id="msg-item-3838847">
<a class="list-head">
<img alt="头像" src="http://static.nowcoder.com/images/head/notify.png">
</a>
<div class="tooltip fade right in">
<div class="tooltip-arrow"></div>
<div class="tooltip-inner letter-chat clearfix">
<div class="letter-info">
<p class="letter-time">06-14 21:58</p>
<a href="javascript:void(0);" id="del-link" name="3838847">删除</a>
</div>
<p class="chat-content">
你收藏的问题<a class="msg-view-all" href="/questionTerminal/abc3fe2ce8e146608e868a70efebf62e?toCommentId=138905">"二维数组中的查找"</a>
有了新的回答，去看看对自己是否有用吧。（不想再收到此类提醒？<a class="msg-view-all" href="/profile/account#setNotification">点击设置</a>
）
</p>
</div>
</div>
</li>
</ul>

        </div>
        <script type="text/javascript">
          $(function(){

            // If really is weixin
            $(document).on('WeixinJSBridgeReady', function() {

              $('.weixin-qrcode-dropdown').show();

              var options = {
                "img_url": "",
                "link": "http://nowcoder.com/j/wt2rwy",
                "desc": "",
                "title": "读《Web 全栈工程师的自我修养》"
              };

              WeixinJSBridge.on('menu:share:appmessage', function (argv){
                WeixinJSBridge.invoke('sendAppMessage', options, function (res) {
                  // _report('send_msg', res.err_msg)
                });
              });

              WeixinJSBridge.on('menu:share:timeline', function (argv) {
                WeixinJSBridge.invoke('shareTimeline', options, function (res) {
                  // _report('send_msg', res.err_msg)
                });
              });

              // $(window).on('touchmove scroll', function() {
              //   if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
              //     $('div.backdrop').show();
              //     $('div.share-help').show();
              //   } else {
              //     $('div.backdrop').hide();
              //     $('div.share-help').hide();
              //   }
              // });

            });

          })
        </script>
    </div>
    <div id="zh-footer" class="zh-footer">
<div class="content zg-wrap clearfix">
<ul>

<li><a href="https://liukanshan.zhihu.com" target="_blank">刘看山</a></li>

<li><a href="/question/19581624" target="_blank">牛客指南</a></li>
<li><a href="javascript:;" id="js-feedback-button">建议反馈</a></li>

<li><a href="/app" target="_blank">移动应用</a></li>
<li><a href="/careers">加入牛客</a></li>
<li><a href="/terms" target="_blank">牛客协议</a></li>
<li><a href="/contact">联系我们</a></li>

</ul>

<span class="copy">© 2016 牛客</span>

</div>
</div>

  <div id="quick-download">
    <button type="button" class="close-link btn-link" data-toggle="modal" data-target="#quick-download-app-modal"><i class="fa fa-times-circle"></i></button>

    <a class="download-link" href="http://nowcoder.com/download">
      <h3>牛客网</h3>
      <h4>程序员的首选学习分享平台</h4>
      <button type="button" class="btn btn-info btn-sm">下载 APP</button>
    </a>

    <div class="modal fade" id="quick-download-app-modal" tabindex="-1" role="dialog">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">提示</div>
          <div class="modal-body">
            <div class="checkbox">
              <label class="i-checks">
                <input id="already-installed" type="checkbox"><i></i> 我已安装了牛客网App，不再显示
              </label>
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-block btn-default" id="close-quick-download-app-modal">关 闭</button>
          </div>
        </div>
      </div>
    </div>
  </div>


</body></html>