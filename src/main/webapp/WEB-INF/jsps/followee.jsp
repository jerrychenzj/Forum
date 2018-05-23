<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/head.jsp"%>
    <div id="main">
        <div class="zg-wrap zu-main clearfix ">
            <div class="zm-profile-section-wrap zm-profile-followee-page">
<div class="zm-profile-section-head">
    <span  class="zm-profile-section-name"><a href="/Forum/userhome/${curUser.id}.do">${curUser.name}</a> 关注了 <strong id="followeetotal${curUser.id}">${followeeCount}</strong> 人</span>
<a class="zg-right zg-link-litblue-normal zm-profile-answer-page-return" href="/Forum/userhome/${curUser.id}.do">返回个人主页</a>
</div>
<div class="zm-profile-section-list">
<div id="zh-profile-follows-list">

<c:forEach items="${followees}" var="followee">
<div class="zh-general-list clearfix" data-init="{&quot;params&quot;: {&quot;offset&quot;: 0, &quot;order_by&quot;: &quot;created&quot;, &quot;hash_id&quot;: &quot;1511065e20b8ce6e99a64565cd2cc1e4&quot;}, &quot;nodename&quot;: &quot;ProfileFolloweesListV2&quot;}">
<div class="zm-profile-card zm-profile-section-item zg-clear no-hovercard">
<div class="zg-right">
    <c:if test="${followee.followed}">
<button  id="followUser${followee.user.id}" data-follow="m:button" data-id="e61f6ff403018cab810c63a660146c35" class="zg-btn zg-btn-unfollow zm-rich-follow-btn small nth-0" onclick="unfollowUser(${curUser.id},${followee.user.id})">取消关注</button>
    </c:if>
    <c:if test="${!followee.followed}">
 <button  id="followUser${followee.user.id}" data-follow="m:button" data-id="a183b32632088ad4cb0ebf944e555eed" class="zg-btn zg-btn-follow zm-rich-follow-btn small nth-0" onclick="followUser(${curUser.id},${followee.user.id})">关注他</button>
    </c:if>
</div>
<a title="${followee.user.name}" data-tip="p$t$buaabarty" class="zm-item-link-avatar" href="/people/buaabarty">
<img src="${followee.user.head_url}" class="zm-item-img-avatar">
</a>
<div class="zm-list-content-medium">
<h2 class="zm-list-content-title"><a data-tip="p$t$buaabarty" href="/Forum/userhome/${followee.user.id}.do" class="zg-link" title="${followee.user.name}">${followee.user.name}</a></h2>

<%--<div class="zg-big-gray">计蒜客教研首席打杂</div>--%>
<div class="details zg-gray">
 <a id="follower${followee.user.id}" target="_blank" href="/Forum/followers/${followee.user.id}.do" class="zg-link-gray-normal">${followee.followers} 粉丝</a>
/
    <a target="_blank" href="/Forum/followees/${followee.user.id}.do" class="zg-link-gray-normal">${followee.followees} 关注</a>
/
<a target="_blank" href="JavaScript:void()" class="zg-link-gray-normal">${followee.commentCount} 回答</a>
/
<a target="_blank" href="JavaScript:void()" class="zg-link-gray-normal">${followee.likecount} 赞同</a>
</div>

</div>
</div>
</div>
</c:forEach>
 <c:if test="${offset != 0}">
   <a href="/Forum/followees/${curUser.id}.do?offset=${offset-10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" >上一页</a>
  </c:if>
<a href="/Forum/followees/${curUser.id}.do?offset=${offset+10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" >下一页</a>
</div>
</div>
</div>
        </div>
    </div>
<%@ include file="/WEB-INF/jsps/foot.jsp"%>