var cmsFactor ={
    tableName : "Factor",
    f_id :"id",
    loadForm:function(){
        if($("#swProduct1Form").html()==''){
            $("#swProduct1Form").load("formAccount/faktor.html", null, function(){
                //                var  content_content_editor = new jj('#content_html').jjEditor();
                addListenerFactorForm();
                
            //                new jj("#upload_Content").jjAjaxFileUploadEditor('#upload_Content_file', content_content_editor);
            //                cmsFactor.m_refresh();
            });
        }
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsFactor.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swProduct1Tbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=400";
        jj(param).jjAjax2(false);
        cmsFactor.tabSizeTbl();
    }, 
    m_refresh2:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsFactor.tableName+".refresh2";
        param += "&panel=" + (containerId==null ? "swProduct4Tbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=400";
        jj(param).jjAjax2(false);
        cmsFactor.tabSizeTbl();
    }, 
    m_refresh3:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsFactor.tableName+".refresh3";
        param += "&panel=" + (containerId==null ? "swProduct5Tbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=400";
        jj(param).jjAjax2(false);
        cmsFactor.tabSizeTbl();
    }, 
    m_refresh4:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsFactor.tableName+".refresh4";
        param += "&panel=" + (containerId==null ? "swProduct6Tbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=400";
        jj(param).jjAjax2(false);
        cmsFactor.tabSizeTbl();
    }, 
    m_refresh5:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsFactor.tableName+".refresh5";
        param += "&panel=" + (containerId==null ? "swProduct7Tbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=400";
        jj(param).jjAjax2(false);
        cmsFactor.tabSizeTbl();
    }, 
    
    m_show_form:function(){
        $('#swProduct1Tbl').hide();
        $('#swProduct1Form').show();
        cmsFactor.tabSizeForm();
    },
    
    m_clean:function(){
        new jj("#Factor_form_in_cms").jjFormClean();
    //        new jj("#"+cmsFactor.f_title).jjVal("");
    //        new jj("#"+cmsFactor.f_lang).jjVal("1");
    //        new jj("#"+cmsFactor.f_parent).jjVal("0");
    //        new jj(content_content_editor).jjEditorVal("");
    },
    m_add_new:function(){
        jj("do="+cmsFactor.tableName+".add_new").jjAjax2(false);
        cmsFactor.m_show_form();
        cmsFactor.m_clean();
        //        oEditor.execCommand( 'bold');
        $('#trProduct2').hide('');
        $('#trProduct3').hide('');
        $('#trProduct4').hide('');
        $('#trProduct5').hide('');
        $('#trProduct6').hide('');
        $('#trProduct7').hide('');
        $('#trProduct8').hide('');
        $('#trProduct9').hide('');
        $('#trProduct10').hide('');
        $('#trProduct11').hide('');
        $('#trProduct12').hide('');
        $('#trProduct13').hide('');
        $('#trProduct14').hide('');
        $('#trProduct15').hide('');
        $('#swProduct1').css('height',215);
        cmsFactor.heightTab = 215;
        $('#account_factor_cust_name').focus();

    },
    
    m_show_tbl:function(){
        $('#swProduct1Tbl').show();
        $('#swProduct1Form').hide();
        if($('#swProduct1Tbl').html()==""){
            cmsFactor.m_refresh();
        }
        cmsFactor.tabSizeTbl();
    },
    m_insert:function(){
        var valid = cmsFactor.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+cmsFactor.tableName+".insert";
            param += "&"+ new jj('#swProduct1Form').jjSerial();
            jj(param).jjAjax2(false);
            cmsFactor.m_show_tbl();
            cmsFactor.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    },
    m_edit:function(){
        var valid = cmsFactor.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+cmsFactor.tableName+".edit";
            param += "&"+ new jj('#swProduct1Form').jjSerial();
            jj(param).jjAjax2(false);
            cmsFactor.m_show_tbl();
            cmsFactor.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    }, 
    m_validation:function(){// mohamdad
//        if(new jj("#content_title").jjVal().length<1){
//            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
//        }
        return "";
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsFactor.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsFactor.tableName+".delete";
        param += "&" + cmsFactor.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsFactor.m_show_tbl();
        cmsFactor.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsFactor.tableName+".select";
        param += "&" + cmsFactor.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsFactor.m_show_form();
        $('#swProduct1').css('height',580);
        cmsFactor.heightTab = 580;
        $( "#swProductAll" ).tabs({selected:0});

    }, 
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsFactor.tableName+".add_EN";
        param += "&" + cmsFactor.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsFactor.f_parent).jjVal(id);
        new jj("#"+cmsFactor.f_lang).jjVal("2");
        cmsFactor.m_show_form();
    }, 
    m_searchTextInTitle:function(text){
        var param = "";
        param += "do="+cmsFactor.tableName+".searchTextInTitle";
        param += "&text=" + (text==null ? "" : text);
        jj(param).jjAjax2(false);
    }, 
    m_searchTextInAll:function(text){
        var param = "";
        param += "do="+cmsFactor.tableName+".searchTextInAll";
        param += "&text=" + (text==null ? "" : text);
        jj(param).jjAjax2(false);
    },
    
    tabSizeTbl: function () {
        $('#swProduct1').css('height',470);
        cmsFactor.heightTab = 470;
    },
    tabSizeForm: function () {
        $('#swProduct1').css('height',375);
        cmsFactor.heightTab = 375;
    },
    heightTab:"470",
    mainTabSetSize : function () {
        $('#swProduct1').css('height',cmsFactor.heightTab);
    }
}

function calculateSumFactor(){
    var sum1 = new jj(new jj("#account_factor_pr_sum_1").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_1").jjVal()).jjConvertToInt():0;
    var sum2 = new jj(new jj("#account_factor_pr_sum_2").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_2").jjVal()).jjConvertToInt():0;
    var sum3 = new jj(new jj("#account_factor_pr_sum_3").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_3").jjVal()).jjConvertToInt():0;
    var sum4 = new jj(new jj("#account_factor_pr_sum_4").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_4").jjVal()).jjConvertToInt():0;
    var sum5 = new jj(new jj("#account_factor_pr_sum_5").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_5").jjVal()).jjConvertToInt():0;
    var sum6 = new jj(new jj("#account_factor_pr_sum_6").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_6").jjVal()).jjConvertToInt():0;
    var sum7 = new jj(new jj("#account_factor_pr_sum_7").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_7").jjVal()).jjConvertToInt():0;
    var sum8 = new jj(new jj("#account_factor_pr_sum_8").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_8").jjVal()).jjConvertToInt():0;
    var sum9 = new jj(new jj("#account_factor_pr_sum_9").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_9").jjVal()).jjConvertToInt():0;
    var sum10 = new jj(new jj("#account_factor_pr_sum_10").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_10").jjVal()).jjConvertToInt():0;
    var sum11 = new jj(new jj("#account_factor_pr_sum_11").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_11").jjVal()).jjConvertToInt():0;
    var sum12 = new jj(new jj("#account_factor_pr_sum_12").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_12").jjVal()).jjConvertToInt():0;
    var sum13 = new jj(new jj("#account_factor_pr_sum_13").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_13").jjVal()).jjConvertToInt():0;
    var sum14 = new jj(new jj("#account_factor_pr_sum_14").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_14").jjVal()).jjConvertToInt():0;
    var sum15 = new jj(new jj("#account_factor_pr_sum_15").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_sum_15").jjVal()).jjConvertToInt():0;
    var summ = sum1+sum2+sum3+sum4+sum5+sum6+sum7+sum8+sum9+sum10+sum11+sum12+sum13+sum14+sum15;
    new jj("#account_factor_sum").jjVal(new jj(summ).jjFormatNumber()+"");
    var discount = new jj(new jj("#account_factor_discount").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_discount").jjVal()).jjConvertToInt():0;
    var pay = new jj(new jj("#account_factor_pay").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pay").jjVal()).jjConvertToInt():0;
    new jj('#account_factor_remainder').jjVal(new jj(summ-discount-pay).jjFormatNumber());
}

function calculateSum1(){
    var count = new jj(new jj("#account_factor_pr_count_1").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_1").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_1").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_1").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_1").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum2(){
    var count = new jj(new jj("#account_factor_pr_count_2").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_2").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_2").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_2").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_2").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum3(){
    var count = new jj(new jj("#account_factor_pr_count_3").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_3").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_3").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_3").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_3").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum4(){
    var count = new jj(new jj("#account_factor_pr_count_4").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_4").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_4").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_4").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_4").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum5(){
    var count = new jj(new jj("#account_factor_pr_count_5").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_5").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_5").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_5").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_5").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum6(){
    var count = new jj(new jj("#account_factor_pr_count_6").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_6").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_6").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_6").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_6").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum7(){
    var count = new jj(new jj("#account_factor_pr_count_7").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_7").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_7").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_7").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_7").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum8(){
    var count = new jj(new jj("#account_factor_pr_count_8").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_8").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_8").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_8").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_8").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum9(){
    var count = new jj(new jj("#account_factor_pr_count_9").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_9").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_9").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_9").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_9").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum10(){
    var count = new jj(new jj("#account_factor_pr_count_10").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_10").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_10").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_10").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_10").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum11(){
    var count = new jj(new jj("#account_factor_pr_count_11").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_11").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_11").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_11").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_11").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum12(){
    var count = new jj(new jj("#account_factor_pr_count_12").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_12").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_12").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_12").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_12").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum13(){
    var count = new jj(new jj("#account_factor_pr_count_13").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_13").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_13").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_13").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_13").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum14(){
    var count = new jj(new jj("#account_factor_pr_count_14").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_14").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_14").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_14").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_14").jjVal(fee * count);
    calculateSumFactor();
}
function calculateSum15(){
    var count = new jj(new jj("#account_factor_pr_count_15").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_count_15").jjVal()).jjConvertToInt():0;
    var fee = new jj(new jj("#account_factor_pr_fee_15").jjVal()).jjIsDigit() ? new jj(new jj("#account_factor_pr_fee_15").jjVal()).jjConvertToInt():0;
    new jj("#account_factor_pr_sum_15").jjVal(fee * count);
    calculateSumFactor();
}
function addListenerFactorForm(){

    $("#account_factor_cust_name").keyup(function(){
        jj('do=Factor.searchCustomer&panel=searchValue&text='+new jj("#account_factor_cust_name").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_1").keyup(function(){
        jj('do=Product.searchProduct&panel=pr1&text='+new jj("#account_factor_pr_name_1").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_2").keyup(function(){
        jj('do=Product.searchProduct&panel=pr2&text='+new jj("#account_factor_pr_name_2").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_3").keyup(function(){
        jj('do=Product.searchProduct&panel=pr3&text='+new jj("#account_factor_pr_name_3").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_4").keyup(function(){
        jj('do=Product.searchProduct&panel=pr4&text='+new jj("#account_factor_pr_name_4").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_5").keyup(function(){
        jj('do=Product.searchProduct&panel=pr5&text='+new jj("#account_factor_pr_name_5").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_6").keyup(function(){
        jj('do=Product.searchProduct&panel=pr6&text='+new jj("#account_factor_pr_name_6").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_7").keyup(function(){
        jj('do=Product.searchProduct&panel=pr7&text='+new jj("#account_factor_pr_name_7").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_8").keyup(function(){
        jj('do=Product.searchProduct&panel=pr8&text='+new jj("#account_factor_pr_name_8").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_9").keyup(function(){
        jj('do=Product.searchProduct&panel=pr9&text='+new jj("#account_factor_pr_name_9").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_10").keyup(function(){
        jj('do=Product.searchProduct&panel=pr10&text='+new jj("#account_factor_pr_name_10").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_11").keyup(function(){
        jj('do=Product.searchProduct&panel=pr11&text='+new jj("#account_factor_pr_name_11").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_12").keyup(function(){
        jj('do=Product.searchProduct&panel=pr12&text='+new jj("#account_factor_pr_name_12").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_13").keyup(function(){
        jj('do=Product.searchProduct&panel=pr13&text='+new jj("#account_factor_pr_name_13").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_14").keyup(function(){
        jj('do=Product.searchProduct&panel=pr14&text='+new jj("#account_factor_pr_name_14").jjVal()).jjAjax2(false);
    });
    $("#account_factor_pr_name_15").keyup(function(){
        jj('do=Product.searchProduct&panel=pr15&text='+new jj("#account_factor_pr_name_15").jjVal()).jjAjax2(false);
    });
                
    new jj('#account_factor_cust_name').jjAddEnterKeyListener('setCustToFactor(-1);');
    new jj('#account_factor_pr_name_1').jjAddEnterKeyListener('setProductToFactor(-1,1);');
    new jj('#account_factor_pr_name_2').jjAddEnterKeyListener('setProductToFactor(-1,2);');
    new jj('#account_factor_pr_name_3').jjAddEnterKeyListener('setProductToFactor(-1,3);');
    new jj('#account_factor_pr_name_4').jjAddEnterKeyListener('setProductToFactor(-1,4);');
    new jj('#account_factor_pr_name_5').jjAddEnterKeyListener('setProductToFactor(-1,5);');
    new jj('#account_factor_pr_name_6').jjAddEnterKeyListener('setProductToFactor(-1,6);');
    new jj('#account_factor_pr_name_7').jjAddEnterKeyListener('setProductToFactor(-1,7);');
    new jj('#account_factor_pr_name_8').jjAddEnterKeyListener('setProductToFactor(-1,8);');
    new jj('#account_factor_pr_name_9').jjAddEnterKeyListener('setProductToFactor(-1,9);');
    new jj('#account_factor_pr_name_10').jjAddEnterKeyListener('setProductToFactor(-1,10);');
    new jj('#account_factor_pr_name_11').jjAddEnterKeyListener('setProductToFactor(-1,11);');
    new jj('#account_factor_pr_name_12').jjAddEnterKeyListener('setProductToFactor(-1,12);');
    new jj('#account_factor_pr_name_13').jjAddEnterKeyListener('setProductToFactor(-1,13);');
    new jj('#account_factor_pr_name_14').jjAddEnterKeyListener('setProductToFactor(-1,14);');
    new jj('#account_factor_pr_name_15').jjAddEnterKeyListener('setProductToFactor(-1,15);');
                
    $("#cancel_Factor").button().click(function(e) {
        cmsFactor.m_clean();
        cmsFactor.m_show_tbl();
    });
    $("#delet_btn_Product_1").click(function(e) {
        deleteOneProductFromFactor(1);
    });
    $("#delet_btn_Product_2").click(function(e) {
        deleteOneProductFromFactor(2);
    });
    $("#delet_btn_Product_3").click(function(e) {
        deleteOneProductFromFactor(3);
    });
    $("#delet_btn_Product_4").click(function(e) {
        deleteOneProductFromFactor(4);
    });
    $("#delet_btn_Product_5").click(function(e) {
        deleteOneProductFromFactor(5);
    });
    $("#delet_btn_Product_6").click(function(e) {
        deleteOneProductFromFactor(6);
    });
    $("#delet_btn_Product_7").click(function(e) {
        deleteOneProductFromFactor(7);
    });
    $("#delet_btn_Product_8").click(function(e) {
        deleteOneProductFromFactor(8);
    });
    $("#delet_btn_Product_9").click(function(e) {
        deleteOneProductFromFactor(9);
    });
    $("#delet_btn_Product_10").click(function(e) {
        deleteOneProductFromFactor(10);
    });
    $("#delet_btn_Product_11").click(function(e) {
        deleteOneProductFromFactor(11);
    });
    $("#delet_btn_Product_12").click(function(e) {
        deleteOneProductFromFactor(12);
    });
    $("#delet_btn_Product_13").click(function(e) {
        deleteOneProductFromFactor(13);
    });
    $("#delet_btn_Product_14").click(function(e) {
        deleteOneProductFromFactor(14);
    });
    $("#delet_btn_Product_15").click(function(e) {
        deleteOneProductFromFactor(15);
    });
    
    new jj("#account_factor_date").jjCalendar();

    new jj('#account_factor_discount').jjSetTextFieldOnlyGetNumber();
    new jj('#account_factor_pay').jjSetTextFieldOnlyGetNumber();
               
    for( var i = 1 ; i<16 ; i++){
        new jj('#account_factor_pr_sum_'+i).jjSetTextFieldOnlyGetNumber();
    }
    for( var i = 1 ; i<16 ; i++){
        new jj('#account_factor_pr_count_'+i).jjSetTextFieldOnlyGetNumber();
    }
    for( var i = 1 ; i<16 ; i++){
        new jj('#account_factor_pr_fee_'+i).jjSetTextFieldOnlyGetNumber();
    }
    for( var i = 1 ; i<16 ; i++){
        $("#delet_btn_"+i).click(function(){
            calculateSumFactor();
        });
    }
                
               
    $("#account_factor_pr_count_1").keyup(function(){
        calculateSum1();
    });
    $("#account_factor_pr_fee_1").keyup(function(){
        calculateSum1();
    });
               
    $("#account_factor_pr_count_2").keyup(function(){
        calculateSum2();
    });
    $("#account_factor_pr_fee_2").keyup(function(){
        calculateSum2();
    });
               
    $("#account_factor_pr_count_3").keyup(function(){
        calculateSum3();
    });
    $("#account_factor_pr_fee_3").keyup(function(){
        calculateSum3();
    });
               
    $("#account_factor_pr_count_4").keyup(function(){
        calculateSum4();
    });
    $("#account_factor_pr_fee_4").keyup(function(){
        calculateSum4();
    });
               
    $("#account_factor_pr_count_5").keyup(function(){
        calculateSum5();
    });
    $("#account_factor_pr_fee_5").keyup(function(){
        calculateSum5();
    });
               
    $("#account_factor_pr_count_6").keyup(function(){
        calculateSum6();
    });
    $("#account_factor_pr_fee_6").keyup(function(){
        calculateSum6();
    });
               
    $("#account_factor_pr_count_7").keyup(function(){
        calculateSum7();
    });
    $("#account_factor_pr_fee_7").keyup(function(){
        calculateSum7();
    });
               
    $("#account_factor_pr_count_8").keyup(function(){
        calculateSum8();
    });
    $("#account_factor_pr_fee_8").keyup(function(){
        calculateSum8();
    });
               
    $("#account_factor_pr_count_9").keyup(function(){
        calculateSum9();
    });
    $("#account_factor_pr_fee_9").keyup(function(){
        calculateSum9();
    });
               
    $("#account_factor_pr_count_10").keyup(function(){
        calculateSum10();
    });
    $("#account_factor_pr_fee_10").keyup(function(){
        calculateSum10();
    });
               
    $("#account_factor_pr_count_11").keyup(function(){
        calculateSum11();
    });
    $("#account_factor_pr_fee_11").keyup(function(){
        calculateSum11();
    });
               
    $("#account_factor_pr_count_12").keyup(function(){
        calculateSum12();
    });
    $("#account_factor_pr_fee_12").keyup(function(){
        calculateSum12();
    });
               
    $("#account_factor_pr_count_13").keyup(function(){
        calculateSum13();
    });
    $("#account_factor_pr_fee_13").keyup(function(){
        calculateSum13();
    });
               
    $("#account_factor_pr_count_14").keyup(function(){
        calculateSum14();
    });
    $("#account_factor_pr_fee_14").keyup(function(){
        calculateSum14();
    });
               
    $("#account_factor_pr_count_15").keyup(function(){
        calculateSum15();
    });
    $("#account_factor_pr_fee_15").keyup(function(){
        calculateSum15();
    });
    $("#account_factor_discount").keyup(function(){
        calculateSum15();
    });
    $("#account_factor_pay").keyup(function(){
        calculateSum15();
    });
    $("#account_factor_pr_count_1").blur(function(){
        if($("#account_factor_pr_count_1").val()==""){
            $("#account_factor_pr_count_1").val(1);
            calculateSum1();
        }
    });
    $("#account_factor_pr_count_2").blur(function(){
        if($("#account_factor_pr_count_2").val()==""){
            $("#account_factor_pr_count_2").val(1);
            calculateSum2();
        }
    });
    $("#account_factor_pr_count_3").blur(function(){
        if($("#account_factor_pr_count_3").val()==""){
            $("#account_factor_pr_count_3").val(1);
            calculateSum3();
        }
    });
    $("#account_factor_pr_count_4").blur(function(){
        if($("#account_factor_pr_count_4").val()==""){
            $("#account_factor_pr_count_4").val(1);
            calculateSum4();
        }
    });
    $("#account_factor_pr_count_5").blur(function(){
        if($("#account_factor_pr_count_5").val()==""){
            $("#account_factor_pr_count_5").val(1);
            calculateSum5();
        }
    });
    $("#account_factor_pr_count_6").blur(function(){
        if($("#account_factor_pr_count_6").val()==""){
            $("#account_factor_pr_count_6").val(1);
            calculateSum6();
        }
    });
    $("#account_factor_pr_count_7").blur(function(){
        if($("#account_factor_pr_count_7").val()==""){
            $("#account_factor_pr_count_7").val(1);
            calculateSum7();
        }
    });
    $("#account_factor_pr_count_8").blur(function(){
        if($("#account_factor_pr_count_8").val()==""){
            $("#account_factor_pr_count_8").val(1);
            calculateSum8();
        }
    });
    $("#account_factor_pr_count_9").blur(function(){
        if($("#account_factor_pr_count_9").val()==""){
            $("#account_factor_pr_count_9").val(1);
            calculateSum9();
        }
    });
    $("#account_factor_pr_count_10").blur(function(){
        if($("#account_factor_pr_count_10").val()==""){
            $("#account_factor_pr_count_10").val(1);
            calculateSum10();
        }
    });
    $("#account_factor_pr_count_11").blur(function(){
        if($("#account_factor_pr_count_11").val()==""){
            $("#account_factor_pr_count_11").val(1);
            calculateSum11();
        }
    });
    $("#account_factor_pr_count_12").blur(function(){
        if($("#account_factor_pr_count_12").val()==""){
            $("#account_factor_pr_count_12").val(1);
            calculateSum12();
        }
    });
    $("#account_factor_pr_count_13").blur(function(){
        if($("#account_factor_pr_count_13").val()==""){
            $("#account_factor_pr_count_13").val(1);
            calculateSum13();
        }
    });
    $("#account_factor_pr_count_14").blur(function(){
        if($("#account_factor_pr_count_14").val()==""){
            $("#account_factor_pr_count_14").val(1);
            calculateSum14();
        }
    });
    $("#account_factor_pr_count_15").blur(function(){
        if($("#account_factor_pr_count_15").val()==""){
            $("#account_factor_pr_count_15").val(1);
            calculateSum15();
        }
    });
 
    new jj('#account_factor_date').jjAddEnterKeyListener('$(\'#account_factor_code\').focus();');
    new jj('#account_factor_code').jjAddEnterKeyListener('$(\'#account_factor_cust_name\').focus();');
    new jj('#account_factor_cust_name').jjAddEnterKeyListener('$(\'#account_factor_pr_name_1\').focus();');
    
    new jj('#account_factor_pr_name_1').jjAddEnterKeyListener('$(\'#account_factor_pr_count_1\').focus();');
    
    new jj('#account_factor_pr_count_1').jjAddEnterKeyListener('$(\'#account_factor_pr_name_2\').focus();');
    new jj('#account_factor_pr_name_2').jjAddEnterKeyListener('$(\'#account_factor_pr_count_2\').focus();');
    
    new jj('#account_factor_pr_count_2').jjAddEnterKeyListener('$(\'#account_factor_pr_name_3\').focus();');
    new jj('#account_factor_pr_name_3').jjAddEnterKeyListener('$(\'#account_factor_pr_count_3\').focus();');
    
    new jj('#account_factor_pr_count_3').jjAddEnterKeyListener('$(\'#account_factor_pr_name_4\').focus();');
    new jj('#account_factor_pr_name_4').jjAddEnterKeyListener('$(\'#account_factor_pr_count_4\').focus();');
    
    new jj('#account_factor_pr_count_4').jjAddEnterKeyListener('$(\'#account_factor_pr_name_5\').focus();');
    new jj('#account_factor_pr_name_5').jjAddEnterKeyListener('$(\'#account_factor_pr_count_5\').focus();');
    
    new jj('#account_factor_pr_count_5').jjAddEnterKeyListener('$(\'#account_factor_pr_name_6\').focus();');
    new jj('#account_factor_pr_name_6').jjAddEnterKeyListener('$(\'#account_factor_pr_count_6\').focus();');
    
    new jj('#account_factor_pr_count_6').jjAddEnterKeyListener('$(\'#account_factor_pr_name_7\').focus();');
    new jj('#account_factor_pr_name_7').jjAddEnterKeyListener('$(\'#account_factor_pr_count_7\').focus();');
    
    new jj('#account_factor_pr_count_7').jjAddEnterKeyListener('$(\'#account_factor_pr_name_8\').focus();');
    new jj('#account_factor_pr_name_8').jjAddEnterKeyListener('$(\'#account_factor_pr_count_8\').focus();');
    
    new jj('#account_factor_pr_count_8').jjAddEnterKeyListener('$(\'#account_factor_pr_name_9\').focus();');
    new jj('#account_factor_pr_name_9').jjAddEnterKeyListener('$(\'#account_factor_pr_count_9\').focus();');
    
    new jj('#account_factor_pr_count_9').jjAddEnterKeyListener('$(\'#account_factor_pr_name_10\').focus();');
    new jj('#account_factor_pr_name_10').jjAddEnterKeyListener('$(\'#account_factor_pr_count_10\').focus();');
    
    new jj('#account_factor_pr_count_10').jjAddEnterKeyListener('$(\'#account_factor_pr_name_11\').focus();');
    new jj('#account_factor_pr_name_11').jjAddEnterKeyListener('$(\'#account_factor_pr_count_11\').focus();');
    
    new jj('#account_factor_pr_count_11').jjAddEnterKeyListener('$(\'#account_factor_pr_name_12\').focus();');
    new jj('#account_factor_pr_name_12').jjAddEnterKeyListener('$(\'#account_factor_pr_count_12\').focus();');
    
    new jj('#account_factor_pr_count_12').jjAddEnterKeyListener('$(\'#account_factor_pr_name_13\').focus();');
    new jj('#account_factor_pr_name_13').jjAddEnterKeyListener('$(\'#account_factor_pr_count_13\').focus();');
    
    new jj('#account_factor_pr_count_13').jjAddEnterKeyListener('$(\'#account_factor_pr_name_14\').focus();');
    new jj('#account_factor_pr_name_14').jjAddEnterKeyListener('$(\'#account_factor_pr_count_14\').focus();');
    
    new jj('#account_factor_pr_count_14').jjAddEnterKeyListener('$(\'#account_factor_pr_name_15\').focus();');
    new jj('#account_factor_pr_name_15').jjAddEnterKeyListener('$(\'#account_factor_pr_count_15\').focus();');
    
    new jj('#account_factor_pr_count_15').jjAddEnterKeyListener('$(\'#account_factor_discount\').focus();');
    new jj('#account_factor_discount').jjAddEnterKeyListener('$(\'#account_factor_pay\').focus();');
    new jj('#account_factor_pay').jjAddEnterKeyListener('$(\'#account_factor_comment\').focus();');
    new jj('#account_factor_comment').jjAddEnterKeyListener('$(\'#insert_Factor_new\').focus();');
}
function setCustToFactor(custId){
    if(custId=='-1'){
        custId = new jj('#account_factor_cust_id').jjVal();
    }
    if(new jj(custId).jjIsDigit()){
        jj('do=Factor.setCustToFactor&id='+custId).jjAjax2(false);
    }
    $('#searchValue').html('');
    $('#account_factor_pr_name_1').focus();

}
function deleteOneProductFromFactor(rowNo){
    for( var i = rowNo ; i<16 ; i++){
        new jj('#account_factor_pr_id_'+i).jjVal($('#account_factor_pr_id_'+(i+1)).val());
        new jj('#account_factor_pr_name_'+i).jjVal($('#account_factor_pr_name_'+(i+1)).val());
        new jj('#account_factor_pr_count_'+i).jjVal($('#account_factor_pr_count_'+(i+1)).val());
        new jj('#account_factor_pr_unit_'+i).jjVal($('#account_factor_pr_unit_'+(i+1)).val());
        new jj('#account_factor_pr_fee_'+i).jjVal($('#account_factor_pr_fee_'+(i+1)).val());
        new jj('#account_factor_pr_sum_'+i).jjVal($('#account_factor_pr_sum_'+(i+1)).val());
    }
    new jj('#account_factor_pr_id_15').jjVal("");
    new jj('#account_factor_pr_name_15').jjVal("");
    new jj('#account_factor_pr_count_15').jjVal("");
    new jj('#account_factor_pr_unit_15').jjVal("");
    new jj('#account_factor_pr_fee_15').jjVal("");
    new jj('#account_factor_pr_sum_15').jjVal("");
    calculateSumFactor();
}
function setProductToFactor(productId,panelId){
    if(productId=='-1'){
        var a = new jj('#account_factor_pr_id_'+panelId).jjVal();
        if(new jj(a).jjIsDigit()){
            productId = a
        }else{
            return false;
        }
    }
    if(new jj(productId).jjIsDigit()){
        jj('do=Product.setProductToFactor&id='+productId+'&panel='+panelId).jjAjax2(false);
    }
    //    $('#account_factor_pr_count_'+panelId).html('1');
    $('#account_factor_pr_count_'+panelId).focus();
    $('#pr'+panelId).html('');
    $('#trProduct'+(new jj(panelId).jjConvertToInt()+1)).show('');
    var height = 215;
    for( var i = 2 ; i<16 ; i++){
        if($('#trProduct'+i).css('display')!='none'){
            height+=26;
        }
    }
    $('#swProduct1').css('height',height);
    cmsFactor.heightTab = height;
}
