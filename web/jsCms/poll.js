var cmsPoll ={
    tableName : "Poll",
    f_id : "id",
    f_poll_id : "poll_id",
    f_qu : "poll_qu",
    f_an1 : "poll_an1",
    f_an2 : "poll_an2",
    f_an3 : "poll_an3",
    f_an4 : "poll_an4",
    f_an5 : "poll_an5",
    f_an6 : "poll_an6",
    f_lang : "poll_lang",
    f_parent : "poll_parent",
    loadForm:function(){
        if($("#swPollForm").html()==''){
            $("#swPollForm").load("formCms/poll.html", null, function(){
                $("#cancel_Poll").button().click(function(e) {
                    cmsPoll.m_clean();
                    cmsPoll.m_show_tbl();
                });
            });
        }
    }, 
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsPoll.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swPollTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsPoll.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swPollTbl').hide();
        $('#swPollForm').show();
        cmsPoll.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swPollForm").jjFormClean();
        
        new jj("#"+cmsPoll.f_lang).jjVal('1');
        new jj("#"+cmsPoll.f_parent).jjVal('0');
    },
    m_add_new:function(){
        jj("do="+cmsPoll.tableName+".add_new").jjAjax2(false);
        cmsPoll.m_show_form();
        cmsPoll.m_clean();
        $('#CategoryPoll_show').html('');
    },
    m_show_tbl:function(){
        $('#swPollTbl').show();
        $('#swPollForm').hide();
        
        if($('#swPollTbl').html()==""){
            cmsPoll.m_refresh();
        }
        cmsPoll.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsPoll.tableName+".insert";
        param += "&" + new jj("#swPollForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsPoll.m_show_tbl();
        cmsPoll.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsPoll.tableName+".edit";
        param += "&" + new jj("#swPollForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsPoll.m_show_tbl();
        cmsPoll.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsPoll.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do=Category_Poll.delete";
        param += "&id=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsPoll.tableName+".select";
        param += "&" + cmsPoll.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsPoll.m_show_form();
        
    },
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsPoll.tableName+".add_EN";
        param += "&" + cmsPoll.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsPoll.f_parent).jjVal(id);
        new jj("#"+cmsPoll.f_lang).jjVal("2");
        cmsPoll.m_show_form();
    },
    m_add_Ar:function(id){
        var param = "";
        param += "do="+cmsPoll.tableName+".add_ar";
        param += "&" + cmsPoll.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsPoll.f_parent).jjVal(id);
        new jj("#"+cmsPoll.f_lang).jjVal("3");
        cmsPoll.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swPollAll').css('height',519);
        cmsPoll.heightTab=519;
    },
    tabSizeForm: function () {
        $('#swPollAll').css('height',260);
        cmsPoll.heightTab=260;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swPollAll').css('height',cmsPoll.heightTab);
    }
}
// ----------------------------------------------------------------------------
var cmsCategoryPoll = {
    tableName : "Category_Poll",
    f_id :"id",
    f_which :"category_poll_which",
    f_user_id:"category_poll_user_id",
    f_answer:"category_poll_answer",
    loadForm:function(){
        if($("#swPollForm").html()==''){
        }
    },   
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsCategoryPoll.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swCategoryPollTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsCategoryPoll.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swCategoryPollTbl').hide();
        $('#swCategoryPollForm').show();
        cmsCategoryPoll.tabSizeForm();
    },
    m_clean:function(){
        new jj('#swCategoryPollForm').jjFormClean();
    },
    m_add_new:function(){
        jj("do="+cmsCategoryPoll.tableName+".add_new").jjAjax2(false);
        cmsCategoryPoll.m_show_form();
        cmsCategoryPoll.m_clean();
        
    },
    m_show_tbl:function(){
        $('#swCategoryPollTbl').show();
        $('#swCategoryPollForm').hide();
        
        if($('#swCategoryPollTbl').html()==""){
            cmsCategoryPoll.m_refresh();
        }
        cmsCategoryPoll.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsCategoryPoll.tableName+".insert";
        param += "&"+ new jj('#swCategoryPollForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryPoll.m_show_tbl();
        cmsCategoryPoll.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsCategoryPoll.tableName+".edit";
        param += "&"+ new jj('#swCategoryPollForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryPoll.m_show_tbl();
        cmsCategoryPoll.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCategoryPoll.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsCategoryPoll.tableName+".delete";
        param += "&" + cmsCategoryPoll.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryPoll.m_show_tbl();
        cmsCategoryPoll.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsCategoryPoll.tableName+".select";
        param += "&" + cmsCategoryPoll.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryPoll.m_show_form();
        
    },
    tabSizeTbl: function () {
        $('#swPollAll').css('height',514);
        cmsCategoryPoll.heightTab=514;
    },
    tabSizeForm: function () {
        $('#swPollAll').css('height',260);
        cmsCategoryPoll.heightTab=260;
    },
    heightTab:"514",
    mainTabSetSize : function () {
        $('#swPollAll').css('height',cmsCategoryPoll.heightTab);
    }
}
