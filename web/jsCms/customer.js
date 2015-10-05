var cmsCustomer ={
    tableName : "Customer",
    f_id :"id",
    loadForm:function(){
        if($("#swProduct3Form").html()==''){
            $("#swProduct3Form").load("formAccount/customer.html", null, function(){
                //                var content_content_editor = new jj('#content_html').jjEditor();
                new jj('#account_cust_birth').jjCalendarWithYearSelector(1310, 1410);

                $("#cancel_Customer").button().click(function(e) {
                    cmsCustomer.m_clean();
                    cmsCustomer.m_show_tbl();
                });
            //                new jj("#upload_Content").jjAjaxFileUploadEditor('#upload_Content_file', content_content_editor);
            //                $("#upload_Content_file").button().click(function(){});
            //                cmsCustomer.m_refresh();
            });
        }
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsCustomer.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swProduct3Tbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=400" ;
        jj(param).jjAjax2(false);
        cmsCustomer.tabSizeTbl();
    }, 
    
    m_show_form:function(){
        $('#swProduct3Tbl').hide();
        $('#swProduct3Form').show();
        cmsCustomer.tabSizeForm();
    },
    
    m_clean:function(){
        new jj("#account_cust_email").jjVal("");
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
    },
    m_add_new:function(){
        jj("do="+cmsCustomer.tableName+".add_new").jjAjax2(false);
        cmsCustomer.m_show_form();
        cmsCustomer.m_clean();
        $('#account_cust_ful_name').focus();

    //        oEditor.execCommand( 'bold');
    },
    m_show_tbl:function(){
        $('#swProduct3Tbl').show();
        $('#swProduct3Form').hide();
        if($('#swProduct3Tbl').html()==""){
            cmsCustomer.m_refresh();
        }
        cmsCustomer.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsCustomer.tableName+".insert";
        param += "&"+ new jj('#swProduct3Form').jjSerial();
        jj(param).jjAjax2(false);
        cmsCustomer.m_show_tbl();
        cmsCustomer.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsCustomer.tableName+".edit";
        param += "&"+ new jj('#swProduct3Form').jjSerial();
        jj(param).jjAjax2(false);
        cmsCustomer.m_show_tbl();
        cmsCustomer.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsCustomer.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsCustomer.tableName+".delete";
        param += "&" + cmsCustomer.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCustomer.m_show_tbl();
        cmsCustomer.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsCustomer.tableName+".select";
        param += "&" + cmsCustomer.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsCustomer.m_show_form();
    }, 
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsCustomer.tableName+".add_EN";
        param += "&" + cmsCustomer.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsCustomer.f_parent).jjVal(id);
        new jj("#"+cmsCustomer.f_lang).jjVal("2");
        cmsCustomer.m_show_form();
    }, 
    m_searchTextInTitle:function(text){
        var param = "";
        param += "do="+cmsCustomer.tableName+".searchTextInTitle";
        param += "&text=" + (text==null ? "" : text);
        jj(param).jjAjax2(false);
    }, 
    m_searchTextInAll:function(text){
        var param = "";
        param += "do="+cmsCustomer.tableName+".searchTextInAll";
        param += "&text=" + (text==null ? "" : text);
        jj(param).jjAjax2(false);
    },
    
    tabSizeTbl: function () {
        $('#swProduct3').css('height',470);
        cmsCustomer.heightTab = 470;
    },
    tabSizeForm: function () {
        $('#swProduct3').css('height',337);
        cmsCustomer.heightTab = 337;
    },
    heightTab:"470",
    mainTabSetSize : function () {
        $('#swProduct3').css('height',cmsCustomer.heightTab);
    }
}
