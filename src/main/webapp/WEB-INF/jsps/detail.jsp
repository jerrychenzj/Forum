<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/head.jsp"%>
    <div class="zu-global-notify zu-global-notify-info" id="zh-question-redirect-info" style="display:none"></div>
    <div class="zu-global-notify zu-global-notify-info" id="zh-question-notification-summary" style="display:none;">
        <div class="zg-wrap">
            <div class="zu-global-nitify-inner">
                <span class="zu-global-notify-icon"></span>
                <ul class="zu-question-notify-wrap"></ul>
            </div>
        </div>
    </div>
    <div class="zg-wrap zu-main clearfix with-indention-votebar" itemscope="" itemtype="http://schema.org/Question" id="zh-single-question-page" data-urltoken="36301524" role="main">
        <div class="zu-main-content">
              <c:if test="${not empty info}">
                <div class="zh-general-list clearfix" data-init="{&quot;params&quot;: {&quot;offset&quot;: 0, &quot;order_by&quot;: &quot;created&quot;, &quot;hash_id&quot;: &quot;1511065e20b8ce6e99a64565cd2cc1e4&quot;}, &quot;nodename&quot;: &quot;ProfileFolloweesListV2&quot;}">
                    <div class="zm-profile-card zm-profile-section-item zg-clear no-hovercard">
                        <div class="zg-right">
                            <c:if test="${info.followed}">
                                <button id="followUser${fromuser.id}" data-follow="m:button" data-id="e61f6ff403018cab810c63a660146c35" class="zg-btn zg-btn-unfollow zm-rich-follow-btn small nth-0" onclick="unfollowUser(${info.id},${fromuser.id})">取消关注</button>
                            </c:if>
                            <c:if test="${!info.followed}">
                                <button id="followUser${fromuser.id}" data-follow="m:button" data-id="a183b32632088ad4cb0ebf944e555eed" class="zg-btn zg-btn-follow zm-rich-follow-btn small nth-0"  onclick="followUser(${info.id},${fromuser.id})">关注他</button>
                            </c:if>
                        </div>
                        <a title="${fromuser.name}" data-tip="p$t$buaabarty" class="zm-item-link-avatar" href="/Forum/userhome/${fromuser.id}.do">
                            <img src="${fromuser.head_url}" class="zm-item-img-avatar">
                        </a>
                        <div class="zm-list-content-medium">
                            <h2 class="zm-list-content-title"><a data-tip="p$t$buaabarty" href="https://www.nowcoder.com/people/buaabarty" class="zg-link" title="${fromuser.name}">${fromuser.name}</a></h2>

                                <%--<div class="zg-big-gray">计蒜客教研首席打杂</div>--%>
                            <div class="details zg-gray">
                                <a id="follower${fromuser.id}" target="_blank" href="/Forum/followers/${fromuser.id}.do" class="zg-link-gray-normal">${info.followers} 粉丝</a>
                                /
                                <a target="_blank" href="/Forum/followees/${fromuser.id}.do" class="zg-link-gray-normal">${info.followees} 关注</a>
                                /
                                <a target="_blank" href="JavaScript:void()" class="zg-link-gray-normal">${info.commentCount} 回答</a>
                                /
                                <a target="_blank" href="JavaScript:void()" class="zg-link-gray-normal">${info.likecount} 赞同</a>
                            </div>

                        </div>
                    </div>
                </div>
                <br>
              </c:if>
            <div class="zu-main-content-inner">
                <meta itemprop="isTopQuestion" content="false">
                <meta itemprop="visitsCount" content="402">

                <div id="zh-question-title" data-editable="true" class="zm-editable-status-normal">
                    <h2 class="zm-item-title">

                    <span class="zm-editable-content">${question.title}</span>

                    </h2>
                </div>
                <div id="zh-question-detail" class="zm-item-rich-text zm-editable-status-normal" data-resourceid="6727688" data-action="/question/detail">
                    <div class="zm-editable-content">${question.content}</div>
                </div>
                <div class="zm-side-section">
                    <div class="zm-side-section-inner" id="zh-question-side-header-wrap">
                        <c:if test="${! followed}">
                            <a id="followQuestion${question.id}" data-follow="q:link" class="follow-button zg-follow zg-btn-green"  href="javascript:followQuestion(${question.id});" id="sfb-123114">
                                <i class="z-icon-follow"></i>关注问题</a>
                        </c:if>
                        <c:if test="${followed}">
                            <a id="followQuestion${question.id}" data-follow="q:link" class="follow-button zg-follow zg-btn-green" href="javascript:unfollowQuestion(${question.id});" id="sfb-123114">
                                <i class="z-icon-follow"></i>取消关注</a>
                        </c:if>
                        <div class="goog-menu goog-menu-vertical" role="menu" aria-haspopup="true" style="-webkit-user-select: none; visibility: visible; left: 92px; top: 33px; display: none;">
                            <div class="goog-menuitem" role="menuitem" id=":8" style="-webkit-user-select: none;">
                                <div class="goog-menuitem-content" style="-webkit-user-select: none;">使用匿名身份</div></div>
                            <div class="goog-menuitem" role="menuitem" id=":9" style="-webkit-user-select: none;">
                                <div class="goog-menuitem-content" style="-webkit-user-select: none;">问题重定向</div></div>
                        </div>
                        <div class="zh-question-followers-sidebar">
                            <div  class="zg-gray-normal">
                                <a  href="">
                                    <strong id="qfollower${question.id}">${followercount}</strong></a>人关注该问题</div>
                            <div id="divappend" class="list zu-small-avatar-list zg-clear">
                                <c:forEach items="${followers}" var="follower">
                                <a id="divappendfollow${follower.userid}" data-tip="p$b$wang-wu-29-54" class="zm-item-link-avatar" href="/Forum/userhome/${follower.userid}.do" data-original_title="${follower.name}">
                                    <img src="${follower.head_url}" class="zm-item-img-avatar"></a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="zh-question-answer-wrap" data-pagesize="10" class="zh-question-answer-wrapper navigable" data-widget="navigable" data-navigable-options="{&quot;items&quot;: &quot;&gt;.zm-item-answer&quot;}" data-init="{&quot;params&quot;: {&quot;url_token&quot;: 36301524, &quot;pagesize&quot;: 10, &quot;offset&quot;: 0}, &quot;nodename&quot;: &quot;QuestionAnswerListV2&quot;}">
                <c:forEach items="${maps}" var="map">
                    <div tabindex="-1" class="zm-item-answer  zm-item-expanded" itemprop="topAnswer" itemscope="" itemtype="http://schema.org/Answer" data-aid="22162611" data-atoken="66862039" data-collapsed="0" data-created="1444310527" data-deleted="0" data-helpful="1" data-isowner="0" data-copyable="1" data-za-module="AnswerItem">
                        <link itemprop="url" href="">
                        <meta itemprop="answer-id" content="22162611">
                        <meta itemprop="answer-url-token" content="66862039">
                        <a class="zg-anchor-hidden" name="answer-22162611"></a>
                        <div class="zm-votebar goog-scrollfloater" data-za-module="VoteBar">
                         <c:if test="${map.likestatus > 0}">
                            <button id="likebutton${map.comment.id}" class="up pressed" aria-pressed="true" title="赞同" onclick="like(${map.comment.id})">
                                <i class="icon vote-arrow"></i>
                                <span id="flikecount${map.comment.id}" class="count">${map.likecount}</span>
                                <span class="label sr-only">赞同</span></button>
                            <button id="dislikebutton${map.comment.id}" class="down" aria-pressed="false" title="反对，不会显示你的姓名" onclick="dislike(${map.comment.id})">
                                <i class="icon vote-arrow"></i>
                                <span class="label sr-only">反对，不会显示你的姓名</span></button>
                         </c:if>
                            <c:if test="${map.likestatus < 0}">
                                <button id="likebutton${map.comment.id}" class="up" aria-pressed="false" title="赞同" onclick="like(${map.comment.id})">
                                    <i class="icon vote-arrow"></i>
                                    <span id="flikecount${map.comment.id}" class="count">${map.likecount}</span>
                                    <span class="label sr-only">赞同</span></button>
                                <button id="dislikebutton${map.comment.id}" class="down pressed" aria-pressed="true" title="反对，不会显示你的姓名" onclick="dislike(${map.comment.id})">
                                    <i class="icon vote-arrow"></i>
                                    <span class="label sr-only">反对，不会显示你的姓名</span></button>
                            </c:if>
                            <c:if test="${map.likestatus == 0}">
                                <button id="likebutton${map.comment.id}" class="up" aria-pressed="false" title="赞同" onclick="like(${map.comment.id})">
                                    <i class="icon vote-arrow"></i>
                                    <span id="flikecount${map.comment.id}" class="count">${map.likecount}</span>
                                    <span class="label sr-only">赞同</span></button>
                                <button id="dislikebutton${map.comment.id}" class="down" aria-pressed="false" title="反对，不会显示你的姓名" onclick="dislike(${map.comment.id})">
                                    <i class="icon vote-arrow"></i>
                                    <span class="label sr-only">反对，不会显示你的姓名</span></button>
                            </c:if>
                        </div>
                        <div class="answer-head">
                            <div class="zm-item-answer-author-info">
                                <a class="zm-item-link-avatar avatar-link" href="" target="_blank" data-tip="p$t$yingxiaodao">
                                    <img src="${map.user.head_url}" class="zm-list-avatar avatar"></a>
                                <a class="author-link" data-tip="p$t$yingxiaodao" target="_blank" href="">${map.user.name}</a>
                                </div>
                            <div class="zm-item-vote-info" data-votecount="28" data-za-module="VoteInfo">
                                <span class="voters text">
                                    <a href="" class="more text">
                                        <span id="slikecount${map.comment.id}" class="js-voteCount">${map.likecount}</span>&nbsp;人赞同</a></span>
                            </div>
                        </div>
                        <div class="zm-item-rich-text expandable js-collapse-body" data-resourceid="6727688" data-action="/answer/content" data-author-name="${map.user.name}" data-entry-url="/question/36301524/answer/66862039">
                            <div class="zm-editable-content clearfix">
                                ${map.comment.content}
                            </div>
                        </div>
                        <a class="zg-anchor-hidden ac" name="22162611-comment"></a>
                        <div class="zm-item-meta answer-actions clearfix js-contentActions">
                            <div class="zm-meta-panel">
                                <a itemprop="url" class="answer-date-link meta-item" target="_blank" href="">发布于 <fmt:formatDate value="${map.comment.created_date}" pattern="yyyy-MM-dd HH:mm:ss"/></a>
                                <a href="" name="addcomment" class="meta-item toggle-comment js-toggleCommentBox">
                                    <i class="z-icon-comment"></i> 0 条评论</a>

                                <button class="item-collapse js-collapse" style="transition: none;">
                                    <i class="z-icon-fold"></i>收起</button>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                </div>
                <div id="zh-question-collapsed-link" class="zu-question-collap-title" style="display:none">
                    <a href="javascript:;" name="expand" class="zg-link-gray" id="zh-question-collapsed-switcher">
                        <span id="zh-question-collapsed-num">0</span>个回答被折叠</a>（
                    <a target="_blank" href="" class="zg-link-gray">为什么？</a>）</div>
                <div id="zh-question-collapsed-wrap" class="zh-question-answer-wrapper" style="display:none"></div>
                <a name="draft"></a>
                 <form action="/Forum/comment/add/{1}.do" method="post">
                     <input id="zh-question-id" type="hidden" name="entity_id" value="${question.id}" />
                <div id="zh-question-answer-form-wrap" class="zh-question-answer-form-wrap">
                    <div class="zm-editable-tip zu-answer-form-disabled-wrap" style="display: none;"></div>
                    <div class="zm-editable-content" style="display: none;">
                    </div>
                    <div class="zm-editable-editor-wrap" style="">
                        <div class="zh-answer-form clearfix">
                            <c:if test="${not empty user}">
                            <a href="/Forum/userhome/${user.id}.do" title="${user.name}" class="zm-item-link-avatar" data-tip="p$t$hu-yuan-24-48">
                                <img src="${user.head_url}" class="zm-list-avatar"></a>
                            </c:if>
                                <div>
                                <div class="zu-answer-form-title">
                                    <a href="/Forum/userhome/${user.id}.do" title="${user.name}" data-tip="p$t$hu-yuan-24-48">${user.name}</a>
                                </div>
                            </div>
                        </div>

                        <div class="zm-editable-editor-outer">
                            <div class="zm-editable-toolbar-container goog-scrollfloater">
                                <div class="goog-toolbar goog-toolbar-horizontal" role="toolbar" tabindex="-1" style="-webkit-user-select: none;">
                                    <div class="goog-inline-block goog-toolbar-button" title="写作模式" role="button" id="toggleFullScreen" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-max" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-inline-block goog-toolbar-button" title="粗体 (ctrl+b)" role="button" aria-pressed="false" id="+bold" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-bold" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-inline-block goog-toolbar-button" title="斜体 (ctrl+i)" role="button" aria-pressed="false" id="+italic" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-italic" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-inline-block goog-toolbar-button" title="下划线 (ctrl+u)" role="button" aria-pressed="false" id="+underline" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-underline" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-toolbar-separator goog-toolbar-separator-disabled goog-inline-block" aria-disabled="true" role="separator" id=":m" style="-webkit-user-select: none;">&nbsp;</div>
                                    <div class="goog-inline-block goog-toolbar-button" title="引用内容" role="button" aria-pressed="false" id="+blockquote" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-blockquote" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-inline-block goog-toolbar-button" title="插入代码" role="button" aria-pressed="false" id="+code" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-code" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-inline-block goog-toolbar-button" title="有序列表" role="button" aria-pressed="false" id="+insertOrderedList" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-insertOrderedList" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-inline-block goog-toolbar-button" title="无序列表" role="button" aria-pressed="false" id="+insertUnorderedList" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-insertUnorderedList" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-inline-block goog-toolbar-button" title="插入公式" role="button" id="+equation" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-equation" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-toolbar-separator goog-toolbar-separator-disabled goog-inline-block" aria-disabled="true" role="separator" id=":n" style="-webkit-user-select: none;">&nbsp;</div>
                                    <div class="goog-inline-block goog-toolbar-button" title="插入图片" role="button" id="image" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-image" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-inline-block goog-toolbar-button" title="插入视频" role="button" id="video" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-video" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="goog-toolbar-separator goog-toolbar-separator-disabled goog-inline-block" aria-disabled="true" role="separator" id=":o" style="-webkit-user-select: none;">&nbsp;</div>
                                    <div class="goog-inline-block goog-toolbar-button" title="清除格式" role="button" id="+removeFormat" style="-webkit-user-select: none;">
                                        <div class="goog-inline-block goog-toolbar-button-outer-box" style="-webkit-user-select: none;">
                                            <div class="goog-inline-block goog-toolbar-button-inner-box" style="-webkit-user-select: none;">
                                                <div class="tr-icon tr-removeFormat" style="-webkit-user-select: none;"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="tr-spinner spinner-gray" style="display: none;"></div>
                                </div>
                            </div>
                            <div class="zm-editable-editor-field-wrap">
                                <div id="mock:f" class="zm-editable-editor-field-element editable" g_editable="true" role="textbox" contenteditable="true" style="font-style: italic;">
                                    <p id="addcomment">
                                        <span   style="font-style: normal;color: #999;">写回答…</span></p>
                                </div>
                                <div class="editable-dropzone">
                                    <div class="row">
                                        <div class="cell">
                                            <div>拖拽至此处上传</div></div>
                                    </div>
                                </div>
                            </div>
                            <div class="mention-popup" style="display: none;">
                                <div class="writing-bg">
                                    <input role="combobox" aria-autocomplete="list" autocomplete="off"></div>
                                <div class="tip">想用 @ 提到谁？</div></div>
                        </div>
                        <div class="zm-command clearfix">
                            <span class=" zg-right">
                                <a class="submit-button zg-btn-blue" name="save" href="javascript:addComment()">发布回答</a></span>
                            <div class="draft-controls zg-right ">
                                <span class="draft-saved-info" style="display: none;">
                                    <a class="draft-clear-button goog-inline-block" href="question/36301524#" data-tip="s$b$删除草稿">
                                        <i class="zg-icon zg-icon-bin"></i>
                                        <span class="hide-text">清除草稿</span></a>
                                    <span class="draft-saved-time"></span>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
                 </form>

            </div>
        </div>
    </div>
<%@ include file="/WEB-INF/jsps/foot.jsp"%>