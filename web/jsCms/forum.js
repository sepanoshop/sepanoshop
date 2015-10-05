var cmsForum ={
    tableName : "Forum",
    f_id : "id",
    f_Forum_id : "forum_id",
    f_date : "forum_date",
    f_content : "forum_content",
    f_creator : "forum_creator",
    f_category_id : "forum_category_id",
    f_category_id_select : "forum_category_id_select",
    loadForm:function(){
        if($("#swForumForm").html()==''){
            $("#swForumForm").load("formCms/forum.html", null, function(){
                $("#cancel_Forum").button().click(function(e) {
                    cmsForum.m_clean();
                    cmsForum.m_show_tbl();
                });
                $('#forum_url_file').button().click(function(){});
                $('#forum_url').button().click(function(){
                    $('#upload_Forum').show();
                    $('#forum_url_file').show();
                    $('#forum_url').hide();
                });
                $('#upload_Forum').button().click(function(){
                    if(new jj('#forum_url_file').jjVal()!=''){
                        $('#upload_Forum').hide();
                        $('#forum_url_file').hide();
                        $('#forum_url').show()
                    }
                });
                new jj("#upload_Forum").jjAjaxFileUpload2("#forum_url_file", "#forum_url");
                forum_content_editor = new jj('#forum_content').jjEditor();
            });
        }
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsForum.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swForumTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsForum.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swForumTbl').hide();
        $('#swForumForm').show();
        cmsForum.tabSizeForm();
        $('#upload_Forum').show();
        $('#forum_url_file').show();
        $('#forum_url').hide();
    },
    m_clean:function(){
        new jj("#swForumForm").jjFormClean();
        new jj("#"+cmsForum.f_creator).jjVal('1');
        new jj("#"+cmsForum.f_category_id_select).jjVal('1');
        new jj(forum_content_editor).jjEditorVal("");

    },
    m_add_new:function(){
        jj("do="+cmsForum.tableName+".add_new").jjAjax2(false);
        cmsForum.m_show_form();
        cmsForum.m_clean();
        cmsForum.m_getCategory();
        $('#forum_creator_full').html('');
    },
    m_show_tbl:function(){
        $('#swForumTbl').show();
        $('#swForumForm').hide();
        
        if($('#swForumTbl').html()==""){
            cmsForum.m_refresh();
        }
        cmsForum.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsForum.tableName+".insert";
        param += "&" + new jj("#swForumForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsForum.m_show_tbl();
        cmsForum.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsForum.tableName+".edit";
        param += "&" + new jj("#swForumForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsForum.m_show_tbl();
        cmsForum.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsForum.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsForum.tableName+".delete";
        param += "&" + cmsForum.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsForum.m_show_tbl();
        cmsForum.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsForum.tableName+".select";
        param += "&" + cmsForum.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsForum.m_show_form();
        cmsForum.m_getCategory(id);
    },  
    m_getCategory:function(id){
        var param = "";
        param += "do="+cmsCategoryForum.tableName+".getOptions";
        param += "&panel=" +  cmsForum.f_category_id_select;
        param += "&id="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swForumAll').css('height',519);
        cmsForum.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swForumAll').css('height',457);
        cmsForum.heightTab = 457;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swForumAll').css('height',cmsForum.heightTab);
    }
}
// ----------------------------------------------------------------------------
var cmsCategoryForum ={
    tableName : "Category_Forum",
    f_id : "id",
    f_category_forum_id : "category_forum_id",
    f_title : "category_forum_title",
    f_creator : "category_forum_creator",
    loadForm:function(){
        if($("#swCategoryForumForm").html()==''){
            $("#swCategoryForumForm").load("formCms/categoryForum.html", null, function(){
                $("#cancel_CategoryForum").button().click(function(e) {
                    cmsCategoryForum.m_clean();
                    cmsCategoryForum.m_show_tbl();
                });
            });
        }
    }, 
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsCategoryForum.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swCategoryForumTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsCategoryForum.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swCategoryForumTbl').hide();
        $('#swCategoryForumForm').show();
        cmsCategoryForum.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swCategoryForumForm").jjFormClean();
    },
    m_add_new:function(){
        jj("do="+cmsCategoryForum.tableName+".add_new").jjAjax2(false);
        cmsCategoryForum.m_show_form();
        cmsCategoryForum.m_clean();
    },
    m_show_tbl:function(){
        $('#swCategoryForumTbl').show();
        $('#swCategoryForumForm').hide();
        
        if($('#swCategoryForumTbl').html()==""){
            cmsCategoryForum.m_refresh();
        }
        cmsCategoryForum.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsCategoryForum.tableName+".insert";
        param += "&" + new jj("#swCategoryForumForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryForum.m_show_tbl();
        cmsCategoryForum.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsCategoryForum.tableName+".edit";
        param += "&" + new jj("#swCategoryForumForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryForum.m_show_tbl();
        cmsCategoryForum.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCategoryForum.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsCategoryForum.tableName+".delete";
        param += "&" + cmsCategoryForum.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryForum.m_show_tbl();
        cmsCategoryForum.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsCategoryForum.tableName+".select";
        param += "&" + cmsCategoryForum.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryForum.m_show_form();
        
    },
    tabSizeTbl: function () {
        $('#swForumAll').css('height',519);
        cmsCategoryForum.heightTab=519;
    },
    tabSizeForm: function () {
        $('#swForumAll').css('height',118);
        cmsCategoryForum.heightTab=118;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swForumAll').css('height',cmsCategoryForum.heightTab);
    }
}
