
// ----------------------------------------------------------------------------
var cmsPic = {
    tableName : "Pic",
    f_id : "id",
    f_pic_id : "pic_id",
    f_gallery_id : "gallery_id",
    f__title : "pic_title",
    f_pic : "pic_pic",
    f_category_id_select:"pic_gallery_id_select",
    f_lang:"pic_lang",
    f_parent:"pic_parent",
    loadForm:function(){
        if($("#swPicForm").html()==''){            
            $("#swPicForm").load("formCms/pic.html", null, function(){
                jj("#pic_margin").jjSetTextFieldOnlyGetNumber();
                jj("#pic_price").jjSetTextFieldOnlyGetNumber();
                $("#cancel_Pic").button().click(function(e) {
                    cmsPic.m_clean();
                    cmsPic.m_show_tbl();
                });
                $("#sendPic").button().click(function(){});
                $("#pic_pic_file").button().click(function(){});
                new jj('#sendPic').jjAjaxFileUpload('pic_pic_file', '#pic_pic_name', '#pic_pic_name_preview');
                $('#pic_pic_name').keyup(function(){
                    $('#pic_pic_name_preview').attr('src','upload/'+$('#pic_pic_name').val());
                    if($('#pic_pic_name').val()==''){
                        $('#pic_pic_name_preview').attr('src','img/preview.jpg');
                    }
                });              
            });
        }
    },  
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsPic.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swPicTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsPic.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swPicTbl').hide();
        $('#swPicForm').show();
        cmsPic.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swPicForm").jjFormClean();
        new jj("#"+cmsPic.f_gallery_id_select).jjVal('1');
        new jj("#"+cmsPic.f_lang).jjVal('1');
        new jj("#"+cmsPic.f_parent).jjVal('0');
        $("#pic_pic_name_preview").attr('src','img/preview.jpg');
    },
    m_add_new:function(){
        jj("do="+cmsPic.tableName+".add_new").jjAjax2(false);
        cmsPic.m_show_form();
        cmsPic.m_clean();
        cmsPic.m_getCategory();
    },
    m_show_tbl:function(){
        $('#swPicTbl').show();
        $('#swPicForm').hide();
        
        if($('#swPicTbl').html()==""){
            cmsPic.m_refresh();
        }
        cmsPic.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsPic.tableName+".insert";
        param += "&" +new jj("#swPicForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsPic.m_show_tbl();
        cmsPic.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsPic.tableName+".edit";
        param += "&" + new jj("#swPicForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsPic.m_show_tbl();
        cmsPic.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsPic.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsPic.tableName+".delete";
        param += "&" + cmsPic.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsPic.m_show_tbl();
        cmsPic.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsPic.tableName+".select";
        param += "&" + cmsPic.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsPic.m_show_form();
        cmsPic.m_getCategory(id);
    },
    m_getMenu:function(){
        var param = "";
        param += "do="+cmsPic.tableName+".getMenu";
        jj(param).jjAjax2(false);
    },
    m_getCategory:function(id){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".getOptions";
        param += "&panel=" +  cmsPic.f_category_id_select;
        param += "&id="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    }, 
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsPic.tableName+".add_EN";
        param += "&" + cmsPic.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsPic.f_parent).jjVal(id);
        new jj("#"+cmsPic.f_lang).jjVal("2");
        cmsPic.m_show_form();
    } ,
    m_add_Ar:function(id){
        var param = "";
        param += "do="+cmsPic.tableName+".add_Ar";
        param += "&" + cmsPic.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsPic.f_parent).jjVal(id);
        new jj("#"+cmsPic.f_lang).jjVal("3");
        cmsPic.m_show_form();
    } ,
    tabSizeTbl: function () {
        $('#swPicAll').css('height',519);
        cmsPic.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swPicAll').css('height',198);
        cmsPic.heightTab = 198;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swPicAll').css('height',cmsPic.heightTab);
    }
}
// ----------------------------------------------------------------------------
var cmsCategoryGallery = {
    tableName : "Category_Gallery",
    f_id :"id",
    f_category_gallery_id :"category_gallery_id",
    f_parent:"category_gallery_parent",
    f_title:"category_gallery_title",
    f_lang:"category_gallery_lang",
    loadForm:function(){
        if($("#swCategoryGalleryForm").html()==''){
            $("#swCategoryGalleryForm").load("formCms/categoryGallery.html", null, function(){
                $("#cancel_CategoryGallery").button().click(function(e) {
                    cmsCategoryGallery.m_clean();
                    cmsCategoryGallery.m_show_tbl();
                });
            });
        }
    },  
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swCategoryGalleryTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsCategoryGallery.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swCategoryGalleryTbl').hide();
        $('#swCategoryGalleryForm').show();
        cmsCategoryGallery.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swCategoryGalleryForm").jjFormClean();
        new jj("#"+cmsCategoryGallery.f_parent).jjVal('0');
        new jj("#"+cmsCategoryGallery.f_lang).jjVal('1');
    },
    m_add_new:function(){
        jj("do="+cmsCategoryGallery.tableName+".add_new").jjAjax2(false);
        cmsCategoryGallery.m_show_form();
        cmsCategoryGallery.m_clean();
        
    },
    m_show_tbl:function(){
        $('#swCategoryGalleryTbl').show();
        $('#swCategoryGalleryForm').hide();
        
        if($('#swCategoryGalleryTbl').html()==""){
            cmsCategoryGallery.m_refresh();
        }
        cmsCategoryGallery.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".insert";
        param += "&" +new jj("#swCategoryGalleryForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryGallery.m_show_tbl();
        cmsCategoryGallery.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".edit";
        param += "&" +new jj("#swCategoryGalleryForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryGallery.m_show_tbl();
        cmsCategoryGallery.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCategoryGallery.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".delete";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryGallery.m_show_tbl();
        cmsCategoryGallery.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".select";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryGallery.m_show_form();
    }, 
    m_getMenu:function(){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".getMenu";
        jj(param).jjAjax2(false);
    },
    m_getOptions:function(){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".getOptions";
        jj(param).jjAjax2(false);
    },
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".add_EN";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsCategoryGallery.f_parent).jjVal(id);
        new jj("#"+cmsCategoryGallery.f_lang).jjVal("2");
        cmsCategoryGallery.m_show_form();
    },
    m_add_Ar:function(id){
        var param = "";
        param += "do="+cmsCategoryGallery.tableName+".add_Ar";
        param += "&" + cmsCategoryGallery.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsCategoryGallery.f_parent).jjVal(id);
        new jj("#"+cmsCategoryGallery.f_lang).jjVal("3");
        cmsCategoryGallery.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swPicAll').css('height',519);
        cmsCategoryGallery.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swPicAll').css('height',115);
        cmsCategoryGallery.heightTab = 115;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swPicAll').css('height',cmsCategoryGallery.heightTab);
    }
}
