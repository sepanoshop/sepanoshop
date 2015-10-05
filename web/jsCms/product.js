var cmsProduct ={
    tableName : "Product",
    f_id : "id",
    f_title : "product_title",
    //By Md
    f_category_id_select:"account_product_category_select",
    f_priority:"account_product_priority",
    f_parent:"account_product_parent",
    f_date:"account_product_date",
    f_lang:"account_product_lang",
    loadForm:function(){
        if($("#swProduct2Form").html()==''){
            $("#swProduct2Form").load("formAccount/product.html", null, function(){
               account_products_content_editor = new jj('#account_products_content').jjEditor();
                $("#cancel_Product").button().click(function(e) {
                    cmsProduct.m_clean();
                    cmsProduct.m_show_tbl();
                });
                //By Md
                $("#account_product_pic").focusout(function(e){
                    var img_adress = $("#account_product_pic").val() =='' ? "img/news.png": $("#account_product_pic").val();
                    $("#account_product_pic_name_preview").attr('src',img_adress);                    
                });
                 new jj("#upload_Products").jjAjaxFileUploadEditor('#upload_Products_file', account_products_content_editor);
                  new jj("#account_product_date").jjCalendar();
            //                product_content_editor = new jj('#product_content').jjEditor();
            });
        }
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsProduct.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swProduct2Tbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsProduct.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swProduct2Tbl').hide();
        $('#swProduct2Form').show();
        cmsProduct.tabSizeForm();
    },
    m_clean:function(){
        new jj("#account_cust_name").jjVal("");
        new jj("#account_cust_famil").jjVal("");
        new jj("#account_cust_ful_name").jjVal("");
        new jj("#account_cust_code").jjVal("");
        new jj("#account_cust_birth").jjVal("");
        new jj("#account_cust_tell").jjVal("");
        new jj("#account_cust_mob").jjVal("");
        new jj("#account_cust_fax").jjVal("");
        new jj("#account_cust_val1").jjVal("");
        new jj("#account_cust_val2").jjVal("");
        new jj("#account_cust_val3").jjVal("");
        new jj("#account_cust_val4").jjVal("");
        new jj("#account_cust_val5").jjVal("");
        new jj("#account_cust_val6").jjVal("");
        new jj("#account_cust_val7").jjVal("");
        new jj("#account_cust_val8").jjVal("");
        new jj("#account_cust_val9").jjVal("");
        new jj("#account_cust_val10").jjVal("");
        new jj("#account_cust_val11").jjVal("");
        new jj("#account_cust_val12").jjVal("");
        new jj("#account_cust_val13").jjVal("");
        new jj("#account_cust_val14").jjVal("");
        new jj("#account_cust_val15").jjVal("");
        new jj("#account_cust_val16").jjVal("");
        new jj("#account_cust_val17").jjVal("");
        new jj("#account_cust_val18").jjVal("");
        new jj("#account_cust_val19").jjVal("");
        new jj("#account_cust_val20").jjVal("");
        //By Md
        new jj(account_products_content_editor).jjEditorVal("");
        new jj("#account_products_abstract").jjVal("");
        $("#account_product_pic_name_preview").attr('src',"img/news.png");
        new jj("#"+cmsProduct.f_priority).jjVal("1");
        new jj("#"+cmsProduct.f_parent).jjVal("0");
        
        
        new jj("#account_product_visit").jjVal("0");
        $("#account_product_visit").removeAttr("disabled");
        new jj("#account_product_visit_checkbox").jjVal("1");
        
        new jj("#account_product_like").jjVal("0");
        $("#account_product_like").removeAttr("disabled");
        new jj("#account_product_like_checkbox").jjVal("1");
        
        new jj("#account_product_dislike").jjVal("0");
        $("#account_product_dislike").removeAttr("disabled");
        new jj("#account_product_dislike_checkbox").jjVal("1");
        
        new jj("#"+cmsProduct.f_lang).jjVal("1")
    },
    m_add_new:function(){
        jj("do="+cmsProduct.tableName+".add_new").jjAjax2(false);
        cmsProduct.m_getCategory();//By Md
        cmsProduct.m_show_form();
        cmsProduct.m_clean();
        $('#account_product_name').focus();
    },
    m_show_tbl:function(){
        $('#swProduct2Tbl').show();
        $('#swProduct2Form').hide();
        
        if($('#swProduct2Tbl').html()==""){
            cmsProduct.m_refresh();
        }
        cmsProduct.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsProduct.tableName+".insert";
        param += "&"+ new jj('#swProduct2Form').jjSerial();
        jj(param).jjAjax2(false);
        cmsProduct.m_show_tbl();
        cmsProduct.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsProduct.tableName+".edit";
        param += "&"+ new jj('#swProduct2Form').jjSerial();
        jj(param).jjAjax2(false);
        cmsProduct.m_show_tbl();
        cmsProduct.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsProduct.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsProduct.tableName+".delete";
        param += "&" + cmsProduct.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsProduct.m_show_tbl();
        cmsProduct.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsProduct.tableName+".select";
        param += "&" + cmsProduct.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsProduct.m_show_form();
        cmsProduct.m_getCategory(id);//By Md
    },
    tabSizeTbl: function () {
        $('#swProduct2').css('height',463);
        cmsProduct.heightTab = 463;
    },
    tabSizeForm: function () {
        $('#swProduct2').css('height',315);
        cmsProduct.heightTab = 315;
    },
    heightTab:"463",
    mainTabSetSize : function () {
        $('#swProduct2').css('height',cmsProduct.heightTab);
    },
    
    //By Md
    m_getCategory:function(id){
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".getOptions";
        param += "&panel=" +  cmsProduct.f_category_id_select;
        param += "&id="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsProduct.tableName+".add_EN";
        param += "&" + cmsProduct.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsProduct.f_parent).jjVal(id);
        new jj("#"+cmsProduct.f_lang).jjVal("2");
        cmsProduct.m_show_form();
    },
    m_add_Ar:function(id){
        var param = "";
        param += "do="+cmsProduct.tableName+".add_ar";
        param += "&" + cmsProduct.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsProduct.f_parent).jjVal(id);
        new jj("#"+cmsProduct.f_lang).jjVal("3");
        cmsProduct.m_show_form();
    }
}


// ----------------------------------------------------------------------------
//By Md
var cmsCategoryProducts ={
    tableName : "Category_Product",
    f_id :"id",
    f_category_product_id :"category_product_id",
    f_parent:"category_product_parent",
    f_upperNode:"category_product_upperNode",//new in v1.5.0
    f_title:"category_product_title",
    f_lang:"category_product_lang",
    loadForm:function(){
        if($("#swCategoryProductsForm").html()==''){
            $("#swCategoryProductsForm").load("formCms/categoryProduct.html", null, function(){
                $("#cancel_CategoryProduct").button().click(function(e) {
                    cmsCategoryProducts.m_clean();
                    cmsCategoryProducts.m_show_tbl();
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
        param += "do="+cmsCategoryProducts.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swCategoryProductsTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsCategoryProducts.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swCategoryProductsTbl').hide();
        $('#swCategoryProductsForm').show();
        cmsCategoryProducts.tabSizeForm();
    },
    m_clean:function(){
        new jj("#"+cmsCategoryProducts.f_category_product_id).jjVal('');
        new jj("#"+cmsCategoryProducts.f_title).jjVal('');
        new jj("#"+cmsCategoryProducts.f_parent).jjVal('0');
        new jj("#"+cmsCategoryProducts.f_lang).jjVal('1');
        new jj("#"+cmsCategoryProducts.f_upperNode).jjVal('0');//new in v 1.5.0
        
    },
    m_add_new:function(){                
        jj("do="+cmsCategoryProducts.tableName+".add_new").jjAjax2(false);
        cmsCategoryProducts.m_show_form();
        cmsCategoryProducts.m_clean();
        
    },
    m_show_tbl:function(){
        $('#swCategoryProductsTbl').show();
        $('#swCategoryProductsForm').hide();
        
        if($('#swCategoryProductsTbl').html()==""){
            cmsCategoryProducts.m_refresh();
        }
        cmsCategoryProducts.tabSizeTbl();
    },
    m_insert:function(){           
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".insert";
        param += "&"+ new jj('#swCategoryProductsForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_tbl();
        cmsCategoryProducts.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".edit";
        param += "&"+ new jj('#swCategoryProductsForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_tbl();
        cmsCategoryProducts.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCategoryProducts.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".delete";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_tbl();
        cmsCategoryProducts.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".select";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCategoryProducts.m_show_form();
    },
    m_select_upper_node:function(upper_id){//new in v 1.5.0
        $("#category_product_upperNode").val(""+upper_id);
        $(".selectedNode").removeClass("selectedNode");        
        $("#uperNodeA"+upper_id).addClass("selectedNode");        
    },
    m_getMenu:function(){
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".getMenu";
        jj(param).jjAjax2(false);
    },
    m_getOptions:function(){
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".getOptions";
        jj(param).jjAjax2(false);
    } ,
    tabSizeTbl: function () {
        $('#swProductAll').css('height',519);
        cmsCategoryProducts.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swProductAll').css('height',215);
        cmsCategoryProducts.heightTab = 215;
    },
    heightTab:"519",
    mainTabSetSize : function () {      
        $('#swProductAll').css('height',cmsCategoryProducts.heightTab);
    },
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".add_EN";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsCategoryProducts.f_parent).jjVal(id);
        new jj("#"+cmsCategoryProducts.f_lang).jjVal("2");
        cmsCategoryProducts.m_show_form();
    }, 
    m_add_Ar:function(id){        
        var param = "";
        param += "do="+cmsCategoryProducts.tableName+".add_Ar";
        param += "&" + cmsCategoryProducts.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsCategoryProducts.f_parent).jjVal(id);
        new jj("#"+cmsCategoryProducts.f_lang).jjVal("3");
        cmsCategoryProducts.m_show_form();
    }
}
