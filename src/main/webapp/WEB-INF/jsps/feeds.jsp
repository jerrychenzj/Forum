<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/head.jsp"%>
    <!-- 内容分隔-->
    <div class="zg-wrap zu-main clearfix " role="main">
        <div class="zu-main-content">
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

            <c:forEach items="${feeds}" var="vo">
                <c:if test="${vo.type == 0}">
                    <div class="feed-item folding feed-item-hook feed-item-2
                        " feed-item-a="" data-type="a" id="feed-2" data-za-module="FeedItem" data-za-index="">
                        <meta itemprop="ZReactor" data-id="389034" data-meta="{&quot;source_type&quot;: &quot;promotion_answer&quot;, &quot;voteups&quot;: 4168, &quot;comments&quot;: 69, &quot;source&quot;: []}">
                        <div class="feed-item-inner">
                            <div class="avatar">
                                <a title="${vo.info.userName}" data-tip="p$t$amuro1230" class="zm-item-link-avatar" target="_blank" href="/Forum/userhome/${vo.info.userId}.do">
                                    <img src="${vo.info.userHeadUrl}" class="zm-item-img-avatar"></a>
                            </div>
                            <div class="feed-main">
                                <div class="feed-content" data-za-module="AnswerItem">
                                    <meta itemprop="answer-id" content="389034">
                                    <meta itemprop="answer-url-token" content="13174385">
                                    <div class="feed-question-detail-item">
                                        <div class="question-description-plain zm-editable-content"></div>
                                    </div>
                                    <div class="expandable entry-body">
                                        <link itemprop="url" href="/question/19857995/answer/13174385">
                                        <meta itemprop="answer-id" content="389034">
                                        <meta itemprop="answer-url-token" content="13174385">
                                        <div class="zm-item-answer-author-info">
                                            <a class="author-link" data-tip="p$b$amuro1230" target="_blank" href="/Forum/userhome/${vo.info.userId}.do">${vo.info.userName}</a>
                                            <span title="${vo.info.questionTitle}" class="bio"> 在问题
                                                    <h2 class="feed-title"><a class="question_link" target="_blank" href="/Forum/question/detail/${vo.info.questionId}.do">${vo.info.questionTitle}</a></h2>
                                                中点赞了<a href="/Forum/userhome/${commentUserId}.do">${vo.info.commentUserName}</a>的评论，<fmt:formatDate value="${vo.created_date}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${vo.type == 1}">
                    <div class="feed-item folding feed-item-hook feed-item-2
                        " feed-item-a="" data-type="a" id="feed-2" data-za-module="FeedItem" data-za-index="">
                        <meta itemprop="ZReactor" data-id="389034" data-meta="{&quot;source_type&quot;: &quot;promotion_answer&quot;, &quot;voteups&quot;: 4168, &quot;comments&quot;: 69, &quot;source&quot;: []}">
                        <div class="feed-item-inner">
                            <div class="avatar">
                                <a title="${vo.info.userName}" data-tip="p$t$amuro1230" class="zm-item-link-avatar" target="_blank" href="/Forum/userhome/${vo.info.userId}.do">
                                    <img src="${vo.info.userHeadUrl}" class="zm-item-img-avatar"></a>
                            </div>
                            <div class="feed-main">
                                <div class="feed-content" data-za-module="AnswerItem">
                                    <meta itemprop="answer-id" content="389034">
                                    <meta itemprop="answer-url-token" content="13174385">
                                    <div class="feed-question-detail-item">
                                        <div class="question-description-plain zm-editable-content"></div>
                                    </div>
                                    <div class="expandable entry-body">
                                        <link itemprop="url" href="/question/19857995/answer/13174385">
                                        <meta itemprop="answer-id" content="389034">
                                        <meta itemprop="answer-url-token" content="13174385">
                                        <div class="zm-item-answer-author-info">
                                            <a class="author-link" data-tip="p$b$amuro1230" target="_blank" href="/Forum/userhome/${vo.info.userId}.do">${vo.info.userName}</a>
                                            <span title="${vo.info.questionTitle}" class="bio"> 评论了问题
                                                    <h2 class="feed-title"><a class="question_link" target="_blank" href="/Forum/question/detail/${vo.info.questionId}.do">${vo.info.questionTitle}</a></h2>
                                                <fmt:formatDate value="${vo.created_date}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
                <c:if test="${vo.type == 3}">
                    <div class="feed-item folding feed-item-hook feed-item-2
                        " feed-item-a="" data-type="a" id="feed-2" data-za-module="FeedItem" data-za-index="">
                        <meta itemprop="ZReactor" data-id="389034" data-meta="{&quot;source_type&quot;: &quot;promotion_answer&quot;, &quot;voteups&quot;: 4168, &quot;comments&quot;: 69, &quot;source&quot;: []}">
                        <div class="feed-item-inner">
                            <div class="avatar">
                                <a title="${vo.info.userName}" data-tip="p$t$amuro1230" class="zm-item-link-avatar" target="_blank" href="/Forum/userhome/${vo.info.userId}.do">
                                    <img src="${vo.info.userHeadUrl}" class="zm-item-img-avatar"></a>
                            </div>
                            <div class="feed-main">
                                <div class="feed-content" data-za-module="AnswerItem">
                                    <meta itemprop="answer-id" content="389034">
                                    <meta itemprop="answer-url-token" content="13174385">
                                    <div class="feed-question-detail-item">
                                        <div class="question-description-plain zm-editable-content"></div>
                                    </div>
                                    <div class="expandable entry-body">
                                        <link itemprop="url" href="/question/19857995/answer/13174385">
                                        <meta itemprop="answer-id" content="389034">
                                        <meta itemprop="answer-url-token" content="13174385">
                                        <div class="zm-item-answer-author-info">
                                            <a class="author-link" data-tip="p$b$amuro1230" target="_blank" href="/Forum/userhome/${vo.info.userId}.do">${vo.info.userName}</a>
                                            <span title="${vo.info.questionTitle}" class="bio"> 关注了问题
                                                    <h2 class="feed-title"><a class="question_link" target="_blank" href="/Forum/question/detail/${vo.info.questionId}.do">${vo.info.questionTitle}</a></h2>
                                                <fmt:formatDate value="${vo.created_date}" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
                    </div>

                <a href="/Forum/feed/pull.do?offset=${count+10}" id="zh-load-more" data-method="next" class="zg-btn-white zg-r3px zu-button-more" style="">更多</a></div>

            </div>
        </div>
    </div>
<%@ include file="/WEB-INF/jsps/foot.jsp"%>