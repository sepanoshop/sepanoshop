var cmsNews ={
    tableName : "News",
    f_id :"id",
    f_news_id :"news_id",
    f_parent:"news_parent",
    f_content:"news_content",
    f_title:"news_title",
    f_date:"news_date",
    f_priority:"news_priority",
    f_category_id:"news_category_id",
    f_lang:"news_lang",
    f_category_id_select:"news_category_id_select",
    loadForm:function(){        
        if($("#swNewsForm").html()==''){
            $("#swNewsForm").load("formCms/news.html", null, function(){
                news_content_editor = new jj('#news_content').jjEditor();
                $("#cancel_News").button().click(function(e) {
                    cmsNews.m_clean();
                    cmsNews.m_show_tbl();
                });                
                $("#news_pic").focusout(function(e){
                    var img_adress = $("#news_pic").val() =='' ? "img/news.png": $("#news_pic").val();
                    $("#news_pic_name_preview").attr('src',img_adress);                    
                });
                new jj("#upload_News").jjAjaxFileUploadEditor('#upload_News_file', news_content_editor);
                $("#upload_News_file").button().click(function(){});
                new jj("#news_date").jjCalendar();
            });
        }
    },    
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsNews.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swNewsTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsNews.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swNewsTbl').hide();
        $('#swNewsForm').show();
        cmsNews.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swNewsForm").jjFormClean();
        new jj("#"+cmsNews.f_lang).jjVal("1");
        new jj("#"+cmsNews.f_parent).jjVal("0");
        new jj("#news_visit").jjVal("0");
        
        $("#news_pic_name_preview").attr('src',"img/news.png");
        
        $("#news_visit").removeAttr("disabled");
        new jj("#news_visit_checkbox").jjVal("1");
        
        new jj("#news_likes").jjVal("0");
        $("#news_likes").removeAttr("disabled");
        new jj("#news_likes_checkbox").jjVal("1");
        
        new jj("#news_disLikes").jjVal("0");
        $("#news_disLikes").removeAttr("disabled");
        new jj("#news_disLikes_checkbox").jjVal("1");
        
        new jj("#"+cmsNews.f_priority).jjVal("1");
        new jj(news_content_editor).jjEditorVal("");

    },
    m_add_new:function(){       
        jj("do="+cmsNews.tableName+".add_new").jjAjax2(false);
        cmsNews.m_getCategory();
        cmsNews.m_show_form();
        cmsNews.m_clean();
    },
    m_show_tbl:function(){
        $('#swNewsTbl').show();
        $('#swNewsForm').hide();
        if($('#swNewsTbl').html()==""){
            cmsNews.m_refresh();
        }
        cmsNews.tabSizeTbl();
        $('#refreshNews').dataTable().fnSort( [ [0,'asc'] ] );
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsNews.tableName+".insert";
        param += "&"+ new jj('#swNewsForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsNews.m_show_tbl();
        cmsNews.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsNews.tableName+".edit";
        param += "&"+ new jj('#swNewsForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsNews.m_show_tbl();
        cmsNews.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsNews.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsNews.tableName+".delete";
        param += "&" + cmsNews.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsNews.m_show_tbl();
        cmsNews.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsNews.tableName+".select";
        param += "&" + cmsNews.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsNews.m_show_form();
        cmsNews.m_getCategory(id);
    },
    m_getOneNews:function(id,temp){
        var param = "";
        param += "do="+cmsNews.tableName+".getOneNews";
        param += "&" + cmsNews.f_id +"="+ (id==null ? "" : id);
        param += "&temp="+ (temp==null ? "" : temp);
        jj(param).jjAjax2(false);
    },
    m_getSlider:function(panel){
        var param = "";
        param += "do="+cmsNews.tableName+".getSlider";
        param += "&panel="+ (panel==null ? "newsSlide" : panel);
        jj(param).jjAjax2(false);
    },
    m_getCategory:function(id){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".getOptions";
        param += "&panel=" +  cmsNews.f_category_id_select;
        param += "&id="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsNews.tableName+".add_EN";
        param += "&" + cmsNews.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsNews.f_parent).jjVal(id);
        new jj("#"+cmsNews.f_lang).jjVal("2");
        cmsNews.m_show_form();
    },
    m_add_Ar:function(id){
        var param = "";
        param += "do="+cmsNews.tableName+".add_ar";
        param += "&" + cmsNews.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsNews.f_parent).jjVal(id);
        new jj("#"+cmsNews.f_lang).jjVal("3");
        cmsNews.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swNewsAll').css('height',519);
        cmsNews.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swNewsAll').css('height',463);
        cmsNews.heightTab = 463;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swNewsAll').css('height',cmsNews.heightTab);
    }    
}
// ----------------------------------------------------------------------------
var cmsCategoryNews ={
    tableName : "Category_News",
    f_id :"id",
    f_category_news_id :"category_news_id",
    f_parent:"category_news_parent",
    f_upperNode:"category_news_upperNode",//new in v1.5.0
    f_title:"category_news_title",
    f_lang:"category_news_lang",
    loadForm:function(){
        if($("#swCategoryNewsForm").html()==''){
            $("#swCategoryNewsForm").load("formCms/categoryNews.html", null, function(){
                $("#cancel_CategoryNews").button().click(function(e) {
                    cmsCategoryNews.m_clean();
                    cmsCategoryNews.m_show_tbl();
                });
            });
        }
    },
    /**
     * by mohammad==>>>>>>>>>>>>>>>>
     * only work for div with id like this:"#uperNodeDiv"+id,<br> this function replace whit ""(blank) all "a" tag in which id is ("uperNodeA+?") 
     * @
     * @        
     * @new in v1.5.0 Iransepano
     */
    m_disablechilds:function(id){
        var innerhtml=$("#uperNodeDiv"+id).html().valueOf();     
        innerhtml=innerhtml.replace(/>/gi,">\n");
        innerhtml=innerhtml.replace(/<a.*id=\"uperNodeA.*\">/gi,"");
        innerhtml=innerhtml.replace(/<\/a>/gi,"");
        $("#uperNodeDiv"+id).html(innerhtml);
    },
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swCategoryNewsTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsCategoryNews.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swCategoryNewsTbl').hide();
        $('#swCategoryNewsForm').show();
        cmsCategoryNews.tabSizeForm();
    },
    m_clean:function(){
        new jj("#"+cmsCategoryNews.f_category_news_id).jjVal('');
        new jj("#"+cmsCategoryNews.f_title).jjVal('');
        new jj("#"+cmsCategoryNews.f_parent).jjVal('0');
        new jj("#"+cmsCategoryNews.f_lang).jjVal('1');
        new jj("#"+cmsCategoryNews.f_upperNode).jjVal('0');//new in v 1.5.0
        
    },
    m_add_new:function(){
        
        jj("do="+cmsCategoryNews.tableName+".add_new").jjAjax2(false);
        cmsCategoryNews.m_show_form();
        cmsCategoryNews.m_clean();
        
    },
    m_show_tbl:function(){
        $('#swCategoryNewsTbl').show();
        $('#swCategoryNewsForm').hide();
        
        if($('#swCategoryNewsTbl').html()==""){
            cmsCategoryNews.m_refresh();
        }
        cmsCategoryNews.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".insert";
        param += "&"+ new jj('#swCategoryNewsForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryNews.m_show_tbl();
        cmsCategoryNews.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".edit";
        param += "&"+ new jj('#swCategoryNewsForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryNews.m_show_tbl();
        cmsCategoryNews.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCategoryNews.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".delete";
        param += "&" + cmsCategoryNews.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryNews.m_show_tbl();
        cmsCategoryNews.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".select";
        param += "&" + cmsCategoryNews.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryNews.m_show_form();
    },
    m_select_upper_node:function(upper_id){//new in v 1.5.0
        $("#category_news_upperNode").val(""+upper_id);
        $(".selectedNode").removeClass("selectedNode");        
        $("#uperNodeA"+upper_id).addClass("selectedNode");        
    },
    m_getMenu:function(){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".getMenu";
        jj(param).jjAjax2(false);
    },
    m_getOptions:function(){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".getOptions";
        jj(param).jjAjax2(false);
    } ,
    tabSizeTbl: function () {
        $('#swNewsAll').css('height',519);
        cmsCategoryNews.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swNewsAll').css('height',215);
        cmsCategoryNews.heightTab = 215;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swNewsAll').css('height',cmsCategoryNews.heightTab);
    },
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".add_EN";
        param += "&" + cmsCategoryNews.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsCategoryNews.f_parent).jjVal(id);
        new jj("#"+cmsCategoryNews.f_lang).jjVal("2");
        cmsCategoryNews.m_show_form();
    }, 
    m_add_Ar:function(id){
        var param = "";
        param += "do="+cmsCategoryNews.tableName+".add_Ar";
        param += "&" + cmsCategoryNews.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsCategoryNews.f_parent).jjVal(id);
        new jj("#"+cmsCategoryNews.f_lang).jjVal("3");
        cmsCategoryNews.m_show_form();
    }
}