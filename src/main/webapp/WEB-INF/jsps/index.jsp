<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/head.jsp"%>
    <!-- 内容分隔-->
    <div class="zg-wrap zu-main clearfix " role="main">
        <div class="zu-main-content">
            <c:if test="${not empty fromuser}">
                <div class="zh-general-list clearfix" data-init="{&quot;params&quot;: {&quot;offset&quot;: 0, &quot;order_by&quot;: &quot;created&quot;, &quot;hash_id&quot;: &quot;1511065e20b8ce6e99a64565cd2cc1e4&quot;}, &quot;nodename&quot;: &quot;ProfileFolloweesListV2&quot;}">
                    <div class="zm-profile-card zm-profile-section-item zg-clear no-hovercard">
                        <div class="zg-right">
                            <c:if test="${info.followed}">
                                <button id="followUser${fromuser.id}" data-follow="m:button" data-id="e61f6ff403018cab810c63a660146c35" class="zg-btn zg-btn-unfollow zm-rich-follow-btn small nth-0" onclick="unfollowUser(${info.id},${fromuser.id})">取消关注</button>
                            </c:if>
                            <c:if test="${!info.followed && info.id != fromuser.id}">
                                <button id="followUser${fromuser.id}" data-follow="m:button" data-id="a183b32632088ad4cb0ebf944e555eed" class="zg-btn zg-btn-follow zm-rich-follow-btn small nth-0"  onclick="followUser(${info.id},${fromuser.id})">关注他</button>
                            </c:if>
                        </div>
                        <a title="${fromuser.name}" data-tip="p$t$buaabarty" class="zm-item-link-avatar" href="/Forum/userhome/${fromuser.id}.do">
                            <img src="${fromuser.head_url}" class="zm-item-img-avatar">
                        </a>
                        <div class="zm-list-content-medium">
                            <h2 class="zm-list-content-title"><a data-tip="p$t$buaabarty" href="https://www.nowcoder.com/people/buaabarty" class="zg-link" title="${fromuser.name}">${fromuser.name}</a></h2>

                            <div class="details zg-gray">
                                <a id="follower${fromuser.id}" target="_blank" href="/Forum/followers/${fromuser.id}.do" class="zg-link-gray-normal">${info.followers} 粉丝</a>
                                /
                                <a target="_blank" href="/Forum/followees/${fromuser.id}.do" class="zg-link-gray-normal">${info.followees} 关注</a>
                                /
                                <a target="_blank" href="#" class="zg-link-gray-normal">${info.commentCount} 回答</a>
                                /
                                <a target="_blank" href="#" class="zg-link-gray-normal">${info.likecount} 赞同</a>
                            </div>

                        </div>
                    </div>
                </div>
                <br>
            </c:if>
            <div class="zu-main-content-inner">
                <div class="zg-section" id="zh-home-list-title">
                    <i class="zg-icon zg-icon-feedlist"></i>最新动态
                    <input type="hidden" id="is-topstory">
                    <span class="zg-right zm-noti-cleaner-setting" style="list-style:none">
                        <c:if test="${empty user.id}">
                             <a href="/login/tologin.do" class="zg-link-gray-normal">
                            <i class="zg-icon zg-icon-settings"></i>设置</a>
                        </c:if>
                        <c:if test="${not empty user.id}">
                             <a href="/Forum/followees/${user.id}.do" class="zg-link-gray-normal">
                            <i class="zg-icon zg-icon-settings"></i>设置</a>
                        </c:if>
                       </span>
                </div>

                <div class="zu-main-feed-con navigable" data-feedtype="topstory" id="zh-question-list" data-widget="navigable" data-navigable-options="{&quot;items&quot;:&quot;&gt; .zh-general-list .feed-content&quot;,&quot;offsetTop&quot;:-82}">
                    <a href="javascript:;" class="zu-main-feed-fresh-button" id="zh-main-feed-fresh-button" style="display:none"></a>
                    <div id="js-home-feed-list" class="zh-general-list topstory clearfix" data-init="{&quot;params&quot;: {}, &quot;nodename&quot;: &quot;TopStory2FeedList&quot;}" data-delayed="true" data-za-module="TopStoryFeedList">

            <c:forEach items="${vos}" var="vo">
                        <div class="feed-item folding feed-item-hook feed-item-2
                        " feed-item-a="" data-type="a" id="feed-2" data-za-module="FeedItem" data-za-index="">
                            <meta itemprop="ZReactor" data-id="389034" data-meta="{&quot;source_type&quot;: &quot;promotion_answer&quot;, &quot;voteups&quot;: 4168, &quot;comments&quot;: 69, &quot;source&quot;: []}">
                            <div class="feed-item-inner">
                                <div class="avatar">
                                    <a title="${vo.user.name}" data-tip="p$t$amuro1230" class="zm-item-link-avatar" target="_blank" href="/Forum/userhome/${vo.user.id}.do">
                                        <img src="${vo.user.head_url}" class="zm-item-img-avatar"></a>
                                </div>
                                <div class="feed-main">
                                 <%--   <div class="feed-source" data-za-module="FeedSource">热门回答，来自
                                        <a href="https://nowcoder.com/topic/19562033" data-tip="t$t$19562033" data-token="19562033" data-topicid="3946" target="_blank">人际交往</a>
                                        <a data-follow="t:link" href="javascript:;" class="zg-follow zu-autohide follow-topic zu-edit-button" data-id="3946">关注话题</a></div>--%>
                                    <div class="feed-content" data-za-module="AnswerItem">
                                        <meta itemprop="answer-id" content="389034">
                                        <meta itemprop="answer-url-token" content="13174385">
                                        <h2 class="feed-title">
                                            <a class="question_link" target="_blank" href="/Forum/question/detail/${vo.question.id}.do">${vo.question.title}</a></h2>
                                        <div class="feed-question-detail-item">
                                            <div class="question-description-plain zm-editable-content"></div>
                                        </div>
                                        <div class="expandable entry-body">
                                           <link itemprop="url" href="/Forum/question/detail/${vo.question.id}.do">
                                            <meta itemprop="answer-id" content="389034">
                                            <meta itemprop="answer-url-token" content="13174385">
                                            <div class="zm-item-vote">
                                                <a  id="qfollower${vo.question.id}"  class="zm-item-vote-count js-expand js-vote-count" href="javascript:;" data-bind-votecount="">${vo.followers}</a></div>
                                            <div class="zm-votebar" data-za-module="VoteBar">
                                                <button class="up" aria-pressed="false" title="赞同">
                                                    <i class="icon vote-arrow"></i>
                                                    <span class="count">${vo.followers}</span>
                                                    <span class="label sr-only">赞同</span></button>
                                                <button class="down" aria-pressed="false" title="反对，不会显示你的姓名">
                                                    <i class="icon vote-arrow"></i>
                                                    <span class="label sr-only">反对，不会显示你的姓名</span></button>
                                            </div>
                                            <div class="zm-item-answer-author-info">
                                                <a class="author-link" data-tip="p$b$amuro1230" target="_blank" href="/Forum/userhome/${vo.user.id}.do">${vo.user.name}</a>
                                                <span title="${vo.question.title}" class="bio"> ，<fmt:formatDate value="${vo.question.created_date}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
                                            <div class="zm-item-vote-info" data-votecount="4168" data-za-module="VoteInfo">
                                                <span class="voters text">
                                                    <a href="#" class="more text">
                                                        <span class="js-voteCount">${vo.followers}</span>&nbsp;人赞同</a></span>
                                            </div>
                                            <div class="zm-item-rich-text expandable js-collapse-body" data-resourceid="123114" data-action="/answer/content" data-author-name="${vo.user.name}" data-entry-url="/question/19857995/answer/13174385">
                                                <div class="zh-summary summary clearfix">${vo.question.content}</div>

                                            </div>
                                        </div>
                                        <div class="feed-meta">
                                            <div class="zm-item-meta answer-actions clearfix js-contentActions">
                                                <div class="zm-meta-panel">
                                                    <c:if test="${! vo.followed}">
                                                    <a id="followQuestion${vo.question.id}" data-follow="q:link" class="follow-link zg-follow meta-item"  href="javascript:followQuestion(${vo.question.id});" id="sfb-123114">
                                                        <i class="z-icon-follow"></i>关注问题</a>
                                                    </c:if>
                                                    <c:if test="${vo.followed}">
                                                        <a id="followQuestion${vo.question.id}" data-follow="q:link" class="follow-link zg-follow meta-item" href="javascript:unfollowQuestion(${vo.question.id});" id="sfb-123114">
                                                            <i class="z-icon-follow"></i>取消关注</a>
                                                    </c:if>
                                                    <a href="#" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox">
                                                        <i class="z-icon-comment"></i> ${vo.question.comment_count}条评论</a>



                                                    <button class="meta-item item-collapse js-collapse">
                                                        <i class="z-icon-fold"></i>收起</button>
                                                </div>
                                            </div>
                                            <a href="#" class="ignore zu-autohide" name="dislike" data-tip="s$b$不感兴趣"></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="undo-dislike-options" data-item_id="2">此内容将不会在动态中再次显示
                                <span class="zg-bull">•</span>
                                <a href="#" class="meta-item revert">撤销</a>
                                <a href="#" class="ignore zu-autohide close"></a>
                            </div>
                        </div>
            </c:forEach>
                    </div>

                    <c:if test="${empty fromuser}">
                    <c:if test="${offset != 0}">
                    <a href="/Forum/home.do?offset=${offset-10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" style="">上一页</a></div>
                    </c:if>
                    <a href="/Forum/home.do?offset=${offset+10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" style="">下一页</a></div>
                    </c:if>
                <c:if test="${not empty fromuser}">
                    <c:if test="${offset != 0}">
                        <a href="/Forum/userhome/${fromuser.id}.do?offset=${offset+10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" style="">上一页</a></div>
                    </c:if>
                <a href="/Forum/userhome/${fromuser.id}.do?offset=${offset+10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" style="">下一页</a></div>
            </c:if>
            </div>
        </div>
    </div>
<%@ include file="/WEB-INF/jsps/foot.jsp"%>