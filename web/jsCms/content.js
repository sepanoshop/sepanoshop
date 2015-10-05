var cmsContent ={
    tableName : "Content",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
        if($("#swContentForm").html()==''){
            $("#swContentForm").load("formCms/content.html", null, function(){
                content_content_editor = new jj('#content_html').jjEditor();
                $("#cancel_Content").button().click(function(e) {
                    cmsContent.m_clean();
                    cmsContent.m_show_tbl();
                });
                new jj("#upload_Content").jjAjaxFileUploadEditor('#upload_Content_file', content_content_editor);
                $("#upload_Content_file").button().click(function(){});
                cmsContent.m_refresh();
            });
        }
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsContent.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swContentTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsContent.tabSizeTbl();
    }, 
    
    m_show_form:function(){
        $('#swContentTbl').hide();
        $('#swContentForm').show();
        cmsContent.tabSizeForm();
    },
    
    m_clean:function(){
        new jj("#"+cmsContent.f_content_id).jjVal("");
        new jj("#"+cmsContent.f_title).jjVal("");
        new jj("#"+cmsContent.f_lang).jjVal("1");
        new jj("#"+cmsContent.f_parent).jjVal("0");
        new jj(content_content_editor).jjEditorVal("");
    },
    m_add_new:function(){
        jj("do="+cmsContent.tableName+".add_new").jjAjax2(false);
        cmsContent.m_show_form();
        cmsContent.m_clean();
    //        oEditor.execCommand( 'bold');

    },
    
    m_show_tbl:function(){
        $('#swContentTbl').show();
        $('#swContentForm').hide();
        if($('#swContentTbl').html()==""){
            cmsContent.m_refresh();
        }
        cmsContent.tabSizeTbl();
    },
    m_insert:function(){
        var valid = cmsContent.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+cmsContent.tableName+".insert";
            param += "&"+ new jj('#swContentForm').jjSerial();
            jj(param).jjAjax2(false);
            cmsContent.m_show_tbl();
            cmsContent.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    },
    m_edit:function(){
        var valid = cmsContent.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+cmsContent.tableName+".edit";
            param += "&"+ new jj('#swContentForm').jjSerial();
            jj(param).jjAjax2(false);
            cmsContent.m_show_tbl();
            cmsContent.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    }, 
    m_validation:function(){// mohamdad
        if(new jj("#content_title").jjVal().length<1){
            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
        }
        return "";
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsContent.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsContent.tableName+".delete";
        param += "&" + cmsContent.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsContent.m_show_tbl();
        cmsContent.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsContent.tableName+".select";
        param += "&" + cmsContent.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsContent.m_show_form();
    }, 
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsContent.tableName+".add_EN";
        param += "&" + cmsContent.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsContent.f_parent).jjVal(id);
        new jj("#"+cmsContent.f_lang).jjVal("2");
        cmsContent.m_show_form();
    }, 
    m_add_Ar:function(id){
        var param = "";
        param += "do="+cmsContent.tableName+".add_Ar";
        param += "&" + cmsContent.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsContent.f_parent).jjVal(id);
        new jj("#"+cmsContent.f_lang).jjVal("3");
        cmsContent.m_show_form();
    }, 
    m_searchTextInTitle:function(text){
        var param = "";
        param += "do="+cmsContent.tableName+".searchTextInTitle";
        param += "&text=" + (text==null ? "" : text);
        jj(param).jjAjax2(false);
    }, 
    m_searchTextInAll:function(text){
        var param = "";
        param += "do="+cmsContent.tableName+".searchTextInAll";
        param += "&text=" + (text==null ? "" : text);
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swContent').css('height',520);
    },
    tabSizeForm: function () {
        $('#swContent').css('height',378);
    }
//    mainTabSetSize: function () {
////        var aa = $("#swContent").children();
////        var bb = 0;
////        for(i=0; i < aa.length; i++){  
////            if($(aa[i]).css("display")!='none'){
////                bb+= new jj($(aa[i]).css("height")).jjConvertToInt() ;
////            }
////        }
////        if(bb==0){
////            $('#tabs').css('height',572);
////        }else{
////            $('#tabs').css('height',bb+44);
////        }
//    }
}
