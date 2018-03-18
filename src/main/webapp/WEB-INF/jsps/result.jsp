<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/path.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN" dropeffect="none" class="js is-AppPromotionBarVisible cssanimations csstransforms csstransitions flexbox no-touchevents no-mobile" style="">
<head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript" async="" src="/resources/scripts/za-0.1.1.min.js" charset="utf-8"></script>
 <script async="" src="/resources/scripts/ga.js" charset="utf-8"></script>
 <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
 <meta name="renderer" content="webkit">
 <meta http-equiv="X-ZA-Response-Id" content="46acde5c53db46f2806ccad726de9826">
 <link rel="shortcut icon" type="images/x-icon" href="/jin.ico" />
 <title>首页 - Forum</title>
 <meta name="apple-itunes-app" content="app-id=432274380">
 <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
 <meta http-equiv="mobile-agent" content="format=html5;url=https://nowcoder.com/">
 <meta id="znonce" name="znonce" content="d3edc464cf014708819feffde7ddd01e">
 <link rel="search" type="application/opensearchdescription+xml" href="/Forum/home.do" title="Forum">
 <link rel="stylesheet" href="/resources/styles/index.css">
 <link rel="stylesheet" href="/resources/styles/letter.css">
 <link rel="stylesheet" href="/resources/styles/result.css">
 <style>
  .zm-item-answer-author-info a.collapse {margin-top: 0}
 </style>
</head>
<body class="zhi page-search">
<div id="black" class="modal-dialog-bg"  aria-hidden="true" style="display:none;opacity: 0.5; width: 1366px; height: 1196px;"></div>
<div id="win" class="modal-wrapper" aria-hidden="true" style="display:none; ">
 <div class="modal-dialog absolute-position" tabindex="0" role="dialog" aria-labelledby=":i" style="width: 550px; left:50%">
  <div class="modal-dialog-title">
   <span class="modal-dialog-title-text" id=":i" role="heading">提问</span>
   <span class="modal-dialog-title-close" role="button" tabindex="0" aria-label="Close"></span>
  </div>
  <div class="modal-dialog-content">
   <div class="zh-add-question-form">
    <form class="js-add-question-form" style="display: block;">
     <div class="zg-section-big clearfix">
      <div id="zm-modal-dialog-info-wrapper"></div>
      <div style="display: none;position: relative;" id="zm-modal-dialog-warnmsg-wrapper">
       <div class="zm-modal-dialog-warnmsg zm-modal-dialog-guide-warn-message zg-r5px"></div>
       <a name="close" title="关闭"  href="javascript:close();" class="zu-global-notify-close" style="display:none">x</a>
       <span class="zm-modal-dialog-guide-title-msg"></span>
      </div>
      <div class="zg-form-text-input add-question-title-form" style="position: relative;">
       <textarea rows="1" class="zg-editor-input zu-seamless-input-origin-element" title="在这里输入问题" id="zh-question-suggest-title-content" aria-label="写下你的问题" placeholder="写下你的问题" role="combobox" aria-autocomplete="list" autocomplete="off" style="height: 22px;"></textarea>
      </div>
     </div>
     <div class="zg-section-big">
      <div class="add-question-section-title">问题说明（可选）：</div>
      <div id="zh-question-suggest-detail-container" class="zm-editable-status-editing">
       <div class="zm-editable-editor-wrap no-toolbar" style="">
        <div class="zm-editable-editor-outer">
         <div class="zm-editable-editor-field-wrap">
          <div id="mock:k" class="zm-editable-editor-field-element editable" g_editable="true" role="textbox" contenteditable="true" style="font-style: italic;">
           <p>
            <span id="zh_question_content" style="font-style: normal;color: #999;">问题背景、条件等详细信息</span></p>
          </div>
         </div>
        </div>
       </div>
      </div>
     </div>
     <div class="zm-command">
      <!-- <span id="zh-question-form-tag-err">至少添加一个话题</span> -->
      <a href="javascript:close();" name="cancel" class="zm-command-cancel">取消</a>
      <a href="javascript:askaction();" name="addq" class="zg-r5px zu-question-form-add zg-btn-blue">发布</a>
      <a name="jumpq" class="zg-r5px zg-btn-blue zu-question-form-jump" style="display:none;">查看问题</a></div>
    </form>
   </div>
  </div>
  <div class="modal-dialog-buttons" style="display: none;"></div>
 </div>
</div>
<div id="meswin" class="modal-wrapper" aria-hidden="true" style="display:none; ">
 <div class="modal-dialog absolute-position" tabindex="0" role="dialog" aria-labelledby=":i" style="width: 550px;left:50%">
  <div class="modal-dialog-title">
   <span class="modal-dialog-title-text" id=":i" role="heading">发送私信</span>
   <span class="modal-dialog-title-close" role="button" tabindex="0" aria-label="Close"></span>
  </div>
  <div class="modal-dialog-content">
   <div class="zh-add-question-form">
    <form class="js-add-question-form" style="display: block;">
     <div class="zg-section-big clearfix">
      <div id="zm-modal-dialog-info-wrapper"></div>
      <div style="display: none;position: relative;" id="zm-modal-dialog-warnmsg-wrapper">
       <div class="zm-modal-dialog-warnmsg zm-modal-dialog-guide-warn-message zg-r5px"></div>
       <a name="close" title="关闭" href="javascript:close();" class="zu-global-notify-close" style="display:none">x</a>
       <span class="zm-modal-dialog-guide-title-msg"></span>
      </div>

      <div class="add-question-section-title">发给：</div>
      <div class="zg-form-text-input add-question-title-form" style="position: relative;">
       <textarea id="zh-message-send-name" rows="1" class="zg-editor-input zu-seamless-input-origin-element" title="在这里输入问题" id="zh-question-suggest-title-content" aria-label="写下你的问题" placeholder="姓名" role="combobox" aria-autocomplete="list" autocomplete="off" style="height: 22px;"></textarea>
      </div>
     </div>
     <div class="zg-section-big">
      <div class="add-question-section-title">内容：</div>
      <div id="zh-question-suggest-detail-container" class="zm-editable-status-editing">
       <div class="zm-editable-editor-wrap no-toolbar" style="">
        <div class="zm-editable-editor-outer">
         <div class="zm-editable-editor-field-wrap">
          <div id="mock:k" class="zm-editable-editor-field-element editable" g_editable="true" role="textbox" contenteditable="true" style="font-style: italic;">
           <p>
            <span id="zh_message_content" style="font-style: normal;color: #999;">私信内容</span></p>
          </div>
         </div>
        </div>
       </div>
      </div>
     </div>
     <div class="zm-command">
      <span id="zh-question-form-tag-err"></span>
      <a href="javascript:close();" name="cancel" class="zm-command-cancel">取消</a>
      <a href="javascript:addmessage();" name="addq" class="zg-r5px zu-question-form-add zg-btn-blue">发布</a>
      <a name="jumpq" class="zg-r5px zg-btn-blue zu-question-form-jump" style="display:none;">查看问题</a></div>
    </form>
   </div>
  </div>
  <div class="modal-dialog-buttons" style="display: none;"></div>
 </div>
</div>
<div role="navigation" class="zu-top">
 <div class="zg-wrap modal-shifting clearfix" id="zh-top-inner">
  <a href="/Forum/home.do" class="zu-top-link-logo" id="zh-top-link-logo" data-za-c="view_home" data-za-a="visit_home" data-za-l="top_navigation_zhihu_logo">Forum</a>
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
      <a href="/Forum/message/list.do" tabindex="-1" id=":2">
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
   <form method="post" action="/Forum/search.do" id="zh-top-search-form" class="zu-top-search-form">
    <input type="hidden" name="type" value="content">
    <label for="keyWord" class="hide-text">Forum搜索</label><input type="text" class="zu-top-search-input" id="keyWord" name="keyWord" autocomplete="off" value="${keyWord}" placeholder="搜索你感兴趣的内容..." role="combobox" aria-autocomplete="list">
    <button type="submit" class="zu-top-search-button" ><span class="hide-text">搜索</span><span class="sprite-global-icon-magnifier-dark"></span></button>
   </form>
  </div>
  <div id="zg-top-nav" class="zu-top-nav">
   <ul class="zu-top-nav-ul zg-clear">
    <li class="zu-top-nav-li current" id="zh-top-nav-home">
     <a class="zu-top-nav-link" href="/Forum/home.do" id="zh-top-link-home" data-za-c="view_home" data-za-a="visit_home" data-za-l="top_navigation_home">首页</a>
    </li>
    <li class="zu-top-nav-li " id="zh-top-nav-explore">
     <a class="zu-top-nav-link" href="/Forum/feed/pull.do">发现</a>
    </li>
    <li class="top-nav-noti zu-top-nav-li ">
     <a class="zu-top-nav-link" href="javascript:openmeswin();" id="zh-top-nav-count-wrap" role="button"><span class="mobi-arrow"></span>消息<span id="zh-top-nav-count" class="zu-top-nav-count zg-noti-number" style="display: none;">0</span></a>
    </li>
   </ul>

   <%--<div class="zu-top-nav-live zu-noti7-popup zg-r5px no-hovercard" id="zh-top-nav-live-new" role="popup" tabindex="0">--%>
   <%--<div class="zu-top-nav-live-inner zg-r5px">--%>
   <%--<div class="zu-top-live-icon">&nbsp;</div>--%>
   <%--<div class="zu-home-noti-inner" id="zh-top-nav-live-new-inner">--%>
   <%--<div class="zm-noti7-popup-tab-container clearfix" tabindex="0" role="tablist">--%>
   <%--<button class="zm-noti7-popup-tab-item message" role="tab">--%>
   <%--<span class="icon">消息</span>--%>
   <%--</button>--%>
   <%--<button class="zm-noti7-popup-tab-item user" role="tab">--%>
   <%--<span class="icon">用户</span>--%>
   <%--</button>--%>
   <%--<button class="zm-noti7-popup-tab-item thanks" role="tab">--%>
   <%--<span class="icon">赞同和感谢</span>--%>
   <%--</button>--%>
   <%--</div>--%>
   <%--</div>--%>
   <%--<div class="zm-noti7-frame-border top"></div>--%>
   <%--<div class="zm-noti7-frame">--%>
   <%--<div class="zm-noti7-content message zh-scroller" style="position: relative; overflow: hidden;">--%>
   <%--<div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">--%>
   <%--<div class="zm-noti7-content-inner">--%>
   <%--<div class="zm-noti7-content-body">--%>
   <%--<div class="zm-noti7-popup-loading">--%>
   <%--<span class="noti-spinner-loading"></span>--%>
   <%--</div>--%>
   <%--</div>--%>
   <%--</div>--%>
   <%--</div></div>--%>
   <%--<div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div>--%>
   <%--</div>--%>
   <%--<div class="zm-noti7-content user zh-scroller" style="display: none; position: relative; overflow: hidden;"><div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">--%>
   <%--<div class="zm-noti7-content-inner">--%>
   <%--<div class="zm-noti7-content-body">--%>
   <%--<div class="zm-noti7-popup-loading">--%>
   <%--<span class="noti-spinner-loading"></span>--%>
   <%--</div>--%>
   <%--</div>--%>
   <%--</div>--%>
   <%--</div></div><div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div></div>--%>
   <%--<div class="zm-noti7-content thanks zh-scroller" style="display: none; position: relative; overflow: hidden;"><div class="zh-scroller-inner" style="height: 100%; width: 150%; overflow: auto;"><div class="zh-scroller-content" style="position: static; display: block; visibility: visible; overflow: hidden; width: 315px; min-height: 100%;">--%>
   <%--<div class="zm-noti7-content-inner">--%>
   <%--<div class="zm-noti7-content-body">--%>
   <%--<div class="zm-noti7-popup-loading">--%>
   <%--<span class="noti-spinner-loading"></span>--%>
   <%--</div>--%>
   <%--</div>--%>
   <%--</div>--%>
   <%--</div></div><div class="zh-scroller-bar-container" style="position: absolute; right: 1px; top: 0px; height: 98px; width: 6px; border: 1px solid rgb(68, 68, 68); opacity: 0; cursor: default; border-radius: 2px; -webkit-user-select: none; background: rgb(102, 102, 102);"><div style="-webkit-user-select: none;"></div></div><div class="zh-scroller-bar" style="position: absolute; right: 2px; top: 2px; opacity: 0.5; width: 6px; border-radius: 3px; cursor: default; -webkit-user-select: none; display: none; background: rgb(0, 0, 0);"></div></div>--%>
   <%--</div>--%>
   <%--<div class="zm-noti7-frame-border bottom"></div>--%>
   <%--<div class="zm-noti7-popup-footer">--%>
   <%--<a href="https://nowcoder.com/notifications" class="zm-noti7-popup-footer-all zg-right">查看全部 »</a>--%>
   <%--<a href="https://nowcoder.com/settings/notification" class="zm-noti7-popup-footer-set" title="通知设置"><i class="zg-icon zg-icon-settings"></i></a>--%>
   <%--</div>--%>
   <%--</div>--%>
   <%--</div>--%>
  </div>
 </div>
</div>


  <div class="zg-wrap zu-main clearfix" role="main">
   <div class="zu-main-content">
    <div class="zu-main-content-inner">
     <ul class="list contents navigable" data-paging="{&quot;next&quot;: &quot;/r/search?q=qiche&amp;type=content&amp;offset=10&quot;}" data-navigable="{&quot;items&quot;: &quot;&gt;.item .answer-item, &gt;.item.article-item&quot;}">
    <c:forEach items="${searchs}" var="vo">
      <li class="item clearfix">
       <div class="title">
        <a target="_blank" href="/Forum/question/detail/${vo.question.id}.do" class="js-title-link">${vo.question.title}</a>
       </div>
       <div class="content">
        <ul class="answers">
         <li class="answer-item clearfix" data-za-module="AnswerItem">
          <div class="entry answer">
           <div class="entry-left hidden-phone">
            <a class="zm-item-vote-count hidden-expanded js-expand js-vote-count" data-bind-votecount="">${vo.followers}</a>
           </div>
           <div class="entry-body">
            <div class="entry-meta">
             <strong class="author-line"><a class="author" href="/Forum/userhome/${vo.user.id}.do" data-tip="p$t$deng-fan-94">${vo.user.name}</a> ，<span class="bio">编辑于 <fmt:formatDate value="${vo.question.created_date}" pattern="yyyy-MM-dd HH:mm:ss"/></span></strong>
            </div>
            <div class="entry-content js-collapse-body" data-author-name="noble6" data-entry-url="/question/42237413/answer/108137941">
             <div class="summary hidden-expanded" style="">
               ${vo.question.content}
              <a class="toggle-expand inline" href="/Forum/question/detail/${vo.question.id}.do">显示全部</a>
             </div>
            </div>
            <div class="actions clearfix js-contentActions">
             <span class="hidden-tablet hidden-desktop"><a href="https://www.nowcoder.com/search?type=content&amp;q=qiche#" class="action-item votenum-mobile zm-item-vote-count js-openVoteDialog"><span data-bind-votecount="">${vo.followers}</span><i class="zg-icon arrow"></i></a></span>
             <a href="https://www.nowcoder.com/search?type=content&amp;q=qiche#" class="action-item zg-follow" data-follow="q:link" data-id="9102803"><i class="z-icon-follow"></i>关注问题</a>
             <a href="#" class="action-item js-toggleCommentBox"><i class="z-icon-comment"></i><span class="label">${vo.question.comment_count} 条评论</span></a>
            </div>
           </div>
          </div>
         </li>
        </ul>
       </div>
       </li>
      </c:forEach>
     </ul>
     <c:if test="${offset != 0}">
     <a href="/Forum/search.do?keyWord=${keyWord}&offset=${offset-10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" style="">上一页</a></div>
    </c:if>
    <a href="/Forum/search.do?keyWord=${keyWord}&offset=${offset+10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" style="">下一页</a></div>
    </div>
   </div>
  </div>
<%@ include file="/WEB-INF/jsps/foot.jsp"%>
