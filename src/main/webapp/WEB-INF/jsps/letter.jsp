<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsps/head.jsp"%>
    <div id="main">
        <div class="zg-wrap zu-main clearfix ">
            <ul class="letter-list">
                <c:forEach items="${maps}" var="map" >
                <li id="conversation-item-10004_622873">
                <a class="letter-link" href="/Forum/message/conversationDetail.do?conversation_id=${map.message.conversation_id}">
                </a>
                <div class="letter-info">
                <span class="l-time"><fmt:formatDate value="${map.message.created_date}" pattern="yyyy-MM-dd HH:mm:ss"/></span>
                <div class="l-operate-bar">
                <%--<a href="javascript:void(0);" class="sns-action-del" data-id="10004_622873">--%>
                <%--删除--%>
                <%--</a>--%>
                <a href="/Forum/message/conversationDetail.do?conversation_id=${map.message.conversation_id}">
                共${map.message.id}条会话
                </a>
                </div>
                </div>
                <div class="chat-headbox">
                <span class="msg-num">
                ${map.unreadcount}
                </span>
                <a class="list-head">
                <img alt="头像" src="${map.user.head_url}">
                </a>
                </div>
                <div class="letter-detail">
                <a title="联系人" class="letter-name level-color-1">
                ${map.user.name}
                </a>
                <p class="letter-brief">
                    ${map.message.content}
                </p>
                </div>
                </li>
                </c:forEach>
                </ul>

        </div>
    </div>

<%@ include file="/WEB-INF/jsps/foot.jsp"%>