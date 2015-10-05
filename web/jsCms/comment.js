var cmsComment = {
    tableName : "Comment",
    f_id :"id",
    f_comment_id :"comment_id",
    f_text:"comment_text",
    f_full_name:"comment_full_name",
    f_email:"comment_email",
    f_date:"comment_date",
    f_tell:"comment_tell",
    f_title:"comment_title",
    f_answer:"comment_answer",
    loadForm:function(){
        if($("#swCommentForm").html()==''){
            $("#swCommentForm").load("formCms/comment.html", null, function(){
                $("#cancel_Comment").button().click(function(e) {
                    cmsComment.m_clean();
                    cmsComment.m_show_tbl();
                });
                $("#sendEmailBtn").button().click(function(e) {
                    sendEmailForComment();
                });
                
            });
        }
    }, 
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsComment.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swCommentTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsComment.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swCommentTbl').hide();
        $('#swCommentForm').show();
        cmsComment.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swCommentForm").jjFormClean();
       
    },
    m_show_tbl:function(){
        $('#swCommentTbl').show();
        $('#swCommentForm').hide();
        cmsComment.m_refresh();
        cmsComment.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsComment.tableName+".insert";
        param += "&" + new jj("#swCommentForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsComment.m_show_tbl();
        cmsComment.m_clean();
    }, 
    m_edit:function(){
        var param = "";
        param += "do="+cmsComment.tableName+".edit";
        param += "&" + new jj("#swCommentForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsComment.m_show_tbl();
        cmsComment.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsComment.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsComment.tableName+".delete";
        param += "&" + cmsComment.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsComment.m_show_tbl();
        cmsComment.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsComment.tableName+".select";
        param += "&" + cmsComment.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsComment.m_show_form();
        
    },
    m_getMenu:function(){
        var param = "";
        param += "do="+cmsComment.tableName+".getMenu";
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swComment').css('height',515);
        cmsComment.heightTab=515;
    },
    tabSizeForm: function () {
        $('#swComment').css('height',270);
        cmsComment.heightTab=270;
    },
    heightTab:"515",
    mainTabSetSize : function () {
        $('#swComment').css('height',cmsComment.heightTab);
    }
}
function sendEmailForComment (){
    new jj("do=Comment.sendAnswer&"+(new jj("#swCommentForm").jjSerial())).jjAjax2();
}
