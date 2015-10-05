var cmsSMS ={
    tableName : "SMS",
    f_id : "id",
    f_sms_id : "sms_id",
    f_qu : "sms_qu",
    f_an1 : "sms_an1",
    f_an2 : "sms_an2",
    f_an3 : "sms_an3",
    f_an4 : "sms_an4",
    f_an5 : "sms_an5",
    f_an6 : "sms_an6",
    f_lang : "sms_lang",
    f_parent : "sms_parent",
    loadForm:function(){
        if($("#swSMSForm").html()==''){
            $("#swSMSForm").load("formCms/SMS.html", null, function(){
                $("#sms_text").keyup(function(){
                    cmsSMS.smsLeftChar('sms_text', 'dummyID_SendSMS1_MsgBox_lblLeft', 'dummyID_SendSMS1_MsgBox_lblSms','dummyID_SendSMS1_MsgBox_lblMax', null);
                });
                $("#cancel_SMS").button().click(function(e) {
                    cmsSMS.m_clean();
                    cmsSMS.m_show_tbl();
                });
            });
        }
    }, 
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsSMS.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swSMSTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsSMS.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swSMSTbl').hide();
        $('#swSMSForm').show();
        cmsSMS.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swSMSForm").jjFormClean();
        
        new jj("#"+cmsSMS.f_lang).jjVal('1');
        new jj("#"+cmsSMS.f_parent).jjVal('0');
    },
    m_add_new:function(){
        jj("do="+cmsSMS.tableName+".add_new").jjAjax2(false);
        cmsSMS.m_show_form();
        cmsSMS.m_clean();
        $('#SettingSMS_show').html('');
    },
    m_show_tbl:function(){
        $('#swSMSTbl').show();
        $('#swSMSForm').hide();
        
        if($('#swSMSTbl').html()==""){
            cmsSMS.m_refresh();
        }
        cmsSMS.tabSizeTbl();
    },
    m_insert:function(){//send sms
        var param = "";
        var valid = cmsSMS.MobileNumbersValidate("sms_nubmers");
        if (valid == ""){            
            param += "do="+cmsSMS.tableName+".insert";
            param += "&" + new jj("#swSMSForm").jjSerial();
            jj(param).jjAjax2(false);
            cmsSMS.m_show_tbl();
            cmsSMS.m_clean();
        }else {
            new jj(valid).jjDialog();
        }                        
    },
    m_edit:function(){
        newval=$("#sms_comment").val()+"-ارسال مجدد";
        $("#sms_comment").val(newval);
        cmsSMS.m_insert();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsSMS.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsSMS.tableName+".delete";
        param += "&id=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsSMS.tableName+".select";
        param += "&" + cmsSMS.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsSMS.m_show_form();
        
    },
    m_add_EN:function(id){
        var param = "";
        param += "do="+cmsSMS.tableName+".add_EN";
        param += "&" + cmsSMS.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsSMS.f_parent).jjVal(id);
        new jj("#"+cmsSMS.f_lang).jjVal("2");
        cmsSMS.m_show_form();
    },
    m_add_Ar:function(id){
        var param = "";
        param += "do="+cmsSMS.tableName+".add_ar";
        param += "&" + cmsSMS.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#"+cmsSMS.f_parent).jjVal(id);
        new jj("#"+cmsSMS.f_lang).jjVal("3");
        cmsSMS.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swSMSAll').css('height',519);
        cmsSMS.heightTab=519;
    },
    tabSizeForm: function () {
        $('#swSMSAll').css('height',260);
        cmsSMS.heightTab=260;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swSMSAll').css('height',cmsSMS.heightTab);
    },
    // تشخیص یونیکد بودن متن
    isUnicode:function(str) {
        var letters = [];
        for (var i = 1; i <= str.length; i++) {
            letters[i] = str.substring((i - 1), i);
            if (letters[i].charCodeAt() > 255) {
                return true;
            }
        }
        return false;
    },
    
    // محاسبه تعداد کاراکتر های باقیمانده پیامک
    smsCount:1,
    smsLeftChar:function(txtSms, lblLeft, lblSms, lblMax, txtSign) {
        var smsBody = $('#' + txtSms).val(); //+ $('#' + txtSign).val();

        var isPersian = cmsSMS.isUnicode(smsBody);
        var maxLen = 0;
        var msgLen = smsBody.length;
        var currentLen = msgLen;

        var charLeft = 0;

        if (isPersian) {
            maxLen = 70;
            $('#' + txtSms).css({
                'direction': 'rtl'
            });
        }
        else {
            maxLen = 160;
            $('#' + txtSms).css({
                'direction': 'ltr'
            });
        }

        if (currentLen > maxLen) {

            if (msgLen > maxLen) {
                msgLen -= maxLen;
            }

            if ((msgLen % maxLen) != 0) {
                smsCount = parseInt(Math.floor(currentLen / maxLen)) + 1;

            }
            else {
                smsCount = parseInt(currentLen / maxLen);
            }

        }
        else {
            smsCount = 1;
        }

        $('#' + lblLeft).html(maxLen - msgLen);
        $('#' + lblSms).html(smsCount);
        $('#' + lblMax).html(maxLen);

    },
    //چک کردن شماره موبایل ها
    MobileNumbersValidate:function(NumberContainerId){
        var nubnersStr= $("#"+NumberContainerId).val();
        var filter = /([(+989)(09)(9)]+[0-9]{9},{0,1})/ig;
        if ($.trim(nubnersStr).length == 0) {
            return "شماره گیرندگان پیامک را وارد کنید";
        }else{
            nubnersStr=nubnersStr.replace(filter,"");
            nubnersStr=nubnersStr.replace(/\s/ig,"");
            nubnersStr=$.trim(nubnersStr);
            if(nubnersStr.length>0){
                return "در وارد کردن شماره ها دقت کنید\n\
<br>\n\
هر شماره را با  , جدا کنید"
            ;            
            }else{
                return "";
            }
        }
    }


    
}
// ----------------------------------------------------------------------------
var cmsSettingSMS = {
    tableName : "Setting_SMS",
    f_id :"id",
    f_which :"category_SMS_which",
    f_user_id:"category_SMS_user_id",
    f_answer:"category_SMS_answer",
    loadForm:function(){
        if($("#swSettingSMSForm").html()==''){
            $("#swSettingSMSForm").load("formCms/SMSConfig.html", null, function(){
                alert("loadform");
//                $("#cancel_SMS").button().click(function(e) {
//                    cmsSMS.m_clean();
//                    cmsSMS.m_show_tbl();
//                });
            });
        }
    },   
    m_show_form:function(){
        $('#swSettingSMSForm').show();
        cmsSettingSMS.tabSizeForm();
    },
    m_clean:function(){
        new jj('#swSettingSMSForm').jjFormClean();
    },
    m_add_new:function(){
        jj("do="+cmsSettingSMS.tableName+".add_new").jjAjax2(false);
        cmsSettingSMS.m_show_form();
        cmsSettingSMS.m_clean();
        
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsSettingSMS.tableName+".insert";
        param += "&"+ new jj('#swSettingSMSForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsSettingSMS.m_show_tbl();
        cmsSettingSMS.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsSettingSMS.tableName+".edit";
        param += "&"+ new jj('#swSettingSMSForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsSettingSMS.m_show_tbl();
        cmsSettingSMS.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsSettingSMS.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsSettingSMS.tableName+".delete";
        param += "&" + cmsSettingSMS.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsSettingSMS.m_show_tbl();
        cmsSettingSMS.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsSettingSMS.tableName+".select";
        param += "&" + cmsSettingSMS.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsSettingSMS.m_show_form();
        
    },
    tabSizeTbl: function () {
        $('#swSMSAll').css('height',514);
        cmsSettingSMS.heightTab=514;
    },
    tabSizeForm: function () {
        $('#swSMSAll').css('height',260);
        cmsSettingSMS.heightTab=260;
    },
    heightTab:"514",
    mainTabSetSize : function () {
        $('#swSMSAll').css('height',cmsSettingSMS.heightTab);
    }
}
