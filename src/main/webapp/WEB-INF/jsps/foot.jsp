</body>
<script src="/resources/scripts/jquery-3.3.1.min.js" charset="utf-8"></script>
<script LANGUAGE="JavaScript">
    function addComment() {
        var content = document.getElementById("addcomment").innerText;
        // alert(content);
        var entity_id = document.getElementById("zh-question-id").value;
        $.ajax({
            url : "/Forum/comment/add.do",
            type : "post",
            data :  "entity_id="+entity_id+"&content="+content+"&entity_type="+1,
            dataType : "json",
            success : function(data){
                if(data.code == 0) {
                    alert(data.msg);
                    location.href = "/Forum/question/detail/"+entity_id+".do";
                }
                else {
                     alert(data.msg);
                }
            }
        });
    }
    function openwin() {
        document.getElementById("win").style.display="";
        document.getElementById("black").style.display="";
    }
    function openmeswin() {
        document.getElementById("meswin").style.display="";
        document.getElementById("black").style.display="";
    }
    function close(){
        document.getElementById("win").style.display="none";
        document.getElementById("meswin").style.display="none";
        document.getElementById("black").style.display="none";
    }
    function askForumaction() {
        var title = $("#zh-question-suggest-title-content").val();
        // alert(title);
        var content = document.getElementById("zh_question_content").innerText;
        //alert(content);
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
    function addmessage() {
        var toname = $("#zh-message-send-name").val();
        // alert(toname);
        var content = document.getElementById("zh_message_content").innerText;
        //alert(content);
        $.ajax({
            url : "/Forum/message/addmessage.do",
            type : "post",
            data :  "toname="+toname+"&content="+content,
            dataType : "json",
            success : function(data){
                if(data.code == 999) {
                    document.getElementById("zh-question-form-tag-err").innerHTML=data.msg;
                    alert(data.msg);
                    location.href = "/login/tologin.do";
                }
                else {
                    if (data.code == 2)
                    {
                        document.getElementById("zh-question-form-tag-err").innerHTML=data.msg;

                    }
                    else
                    {
                        alert(data.msg);
                        location.href = "/Forum/message/list.do";
                    }

                }
            }
        });
    }
    function like(entityid) {

        var pdata = "entityid="+entityid+"&entitytype="+2;
        //alert(pdata);
        var entitytype = 2;
        $.ajax({
            url : "/Forum/like.do",
            type : "post",
            data : "entityid="+entityid+"&entitytype="+entitytype,
            dataType : "json",
            success : function(data){
                if(data.code == 999) {
                    alert(data.msg);
                    location.href = "/login/tologin.do";
                }
                else {
                    var fcountid = "flikecount"+entityid;
                    //alert(countid);
                    document.getElementById(fcountid).innerHTML=data.msg;

                    var scountid = "slikecount"+entityid;
                    //alert(countid);
                    document.getElementById(scountid).innerHTML=data.msg;

                    var upbuttonid = "likebutton"+entityid;
                    document.getElementById(upbuttonid).className = "up pressed";

                    var downbuttonid = "dislikebutton"+entityid;
                    document.getElementById(downbuttonid).className = "down";
                }
            }
        });

    }
    function dislike(entityid){
        var pdata = "entityid="+entityid+"&entitytype="+2;
       // alert(pdata);
        $.ajax({
            url : "/Forum/dislike.do",
            type : "post",
            data :  pdata,
            dataType : "json",
            success : function(data){
                if(data.code == 999) {
                    alert(data.msg);
                    location.href = "/login/tologin.do";
                }
                else {
                    var fcountid = "flikecount"+entityid;
                    //alert(countid);
                    document.getElementById(fcountid).innerHTML=data.msg;

                    var scountid = "slikecount"+entityid;
                    //alert(countid);
                    document.getElementById(scountid).innerHTML=data.msg;

                    var upbuttonid = "likebutton"+entityid;
                    document.getElementById(upbuttonid).className = "up";

                    var downbuttonid = "dislikebutton"+entityid;
                    document.getElementById(downbuttonid).className = "down pressed";
                }
            }
        });

    }
    function followUser(userid,entityid){
        $.ajax({
            url : "/Forum/followUser.do",
            type : "post",
            data :  "userId="+entityid,
            dataType : "json",
            success : function(data){
                if(data.code == 999) {
                    alert(data.msg);
                    location.href = "/login/tologin.do";
                }
                else {
                    //location.reload();
                    var followercountid = "follower"+entityid;
                    //alert(countid);
                    document.getElementById(followercountid).innerHTML=data.msg+" 粉丝";

                    var followUserid = "followUser"+entityid;
                    document.getElementById(followUserid).onclick = function () { unfollowUser(userid,entityid) };
                    document.getElementById(followUserid).innerHTML = "取消关注";

                   var followeetotalid = "followeetotal"+userid;
                    document.getElementById(followeetotalid).innerHTML = data.msg;


                }
            }
        });

    }
    function unfollowUser(userid,entityid){
        $.ajax({
            url : "/Forum/unfollowUser.do",
            type : "post",
            data : "userId="+entityid,
            dataType : "json",
            success : function(data){
                if(data.code == 999) {
                    alert(data.msg);
                    location.href = "/login/tologin.do";
                }
                else {
                    //location.reload();
                    var followercountid = "follower"+entityid;
                    //alert(countid);
                   document.getElementById(followercountid).innerHTML=data.msg+" 粉丝";


                    var followUserid = "followUser"+entityid;
                    document.getElementById(followUserid).onclick= function () { followUser(userid,entityid) };
                    document.getElementById(followUserid).innerHTML = "关注";

                    var followeetotalid = "followeetotal"+userid;
                    document.getElementById(followeetotalid).innerHTML = data.msg;



                }
            }
        });

    }
    function followQuestion(entityid){
        $.ajax({
            url : "/Forum/followQuestion.do",
            type : "post",
            data : "questionId="+entityid,
            dataType : "json",
            success : function(data){
                if(data.code == 999) {
                    alert(data.msg);
                    location.href = "/login/tologin.do";
                }
                else {
                    //location.reload();
                    var qfollowercountid = "qfollower"+entityid;
                    //alert(countid);
                    document.getElementById(qfollowercountid).innerHTML=data.count;

                    var followQuestionid = "followQuestion"+entityid;
                    document.getElementById(followQuestionid).innerHTML = "<i class='z-icon-follow'></i>取消关注";
                    document.getElementById(followQuestionid).href = "javascript:unfollowQuestion("+entityid+");";


                    $('#divappend').append(" <a id='divappendfollow"+data.id+"' data-tip='p$b$wang-wu-29-54' class='zm-item-link-avatar' href='/Forum/userhome/"+data.id+".do' data-original_title="+data.name+"> <img src="+data.head_url+" class='zm-item-img-avatar'></a>");
                }
            }
        });

    }
    function unfollowQuestion(entityid){
        $.ajax({
            url : "/Forum/unfollowquestion.do",
            type : "post",
            data : "questionId="+entityid,
            dataType : "json",
            success : function(data){
                if(data.code == 999) {
                    alert(data.msg);
                    location.href = "/login/tologin.do";
                }
                else {
                    //location.reload();
                    var qfollowercountid = "qfollower"+entityid;
                    //alert(countid);
                    document.getElementById(qfollowercountid).innerHTML=data.count;

                    var followQuestionid = "followQuestion"+entityid;
                    document.getElementById(followQuestionid).innerHTML = "<i class='z-icon-follow'></i>关注问题";
                    document.getElementById(followQuestionid).href = "javascript:followQuestion("+entityid+");";
                    var appendid = "divappendfollow"+data.id;
                    var elem=document.getElementById(appendid); // 按 id 获取要删除的元素
                    document.getElementById("divappend").removeChild(elem); // 让 “要删除的元素” 的 “父元素” 删除 “要删除的元素”
                }
            }
        });

    }
</script>
</html>