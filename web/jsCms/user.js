var cmsUser ={
    tableName : "Access_User",
    f_id : "id",
    f_user_id : "user_id",
    f_pass : "user_pass",
    f_name : "user_name",
    f_family : "user_family",
    f_email : "user_email",
    f_isActive : "user_is_active",
    f_registDate : "user_createDate",
    f_birthdate : "user_birthdate",
    f_question : "user_question",
    f_answer : "user_answer",
    f_no1 : "user_no1",
    f_no2 : "user_no2",
    f_parent : "user_parent",
    loadForm:function(){
        if($("#swUserForm").html()==''){
            $("#swUserForm").load("formCms/user.html", null, function(){
                new jj('#user_birthdate').jjCalendarWithYearSelector(1310, 1410);
                $("#cancel_User").button().click(function(e) {
                    cmsUser.m_clean();
                    cmsUser.m_show_tbl();
                });
            });
        }
    }, 
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsUser.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swUserTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsUser.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swUserTbl').hide();
        $('#swUserForm').show();
        cmsUser.tabSizeForm();
    },
    m_clean:function(){
        new jj("#"+cmsUser.f_user_id).jjVal('');
        new jj("#"+cmsUser.f_pass).jjVal('');
        new jj("#"+cmsUser.f_name).jjVal('');
        new jj("#"+cmsUser.f_family).jjVal('');
        new jj("#"+cmsUser.f_email).jjVal('');
        new jj("#"+cmsUser.f_isActive).jjVal('');
        new jj("#"+cmsUser.f_registDate).jjVal('');
        new jj("#"+cmsUser.f_birthdate).jjVal('');
        new jj("#"+cmsUser.f_question).jjVal('');
        new jj("#"+cmsUser.f_answer).jjVal('');
        new jj("#"+cmsUser.f_no1).jjVal('');
        new jj("#"+cmsUser.f_no2).jjVal('');
        new jj("#"+cmsUser.f_parent).jjVal('');
    },
    m_add_new:function(){
        jj("do="+cmsUser.tableName+".add_new").jjAjax2(false);
        cmsUser.m_show_form();
        cmsUser.m_clean();
        cmsUser.m_getGroups();
    },
    m_show_tbl:function(){
        $('#swUserTbl').show();
        $('#swUserForm').hide();
        
        if($('#swUserTbl').html()==""){
            cmsUser.m_refresh();
        }
        cmsUser.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsUser.tableName+".insert";
        param += "&" + jj('#swUser').jjSerial();
        jj(param).jjAjax2(false);
        cmsUser.m_show_tbl();
        cmsUser.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsUser.tableName+".edit";
        param += "&" + jj('#swUser').jjSerial();
        jj(param).jjAjax2(false);
        cmsUser.m_show_tbl();
        cmsUser.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsUser.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsUser.tableName+".delete";
        param += "&" + cmsUser.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsUser.m_show_tbl();
        cmsUser.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsUser.tableName+".select";
        param += "&" + cmsUser.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsUser.m_show_form();
        cmsUser.m_getGroups(id);
        
    },
    
    m_getGroups:function(id){
        var param = "";
        param += "do="+ cmsGroup.tableName+".getCheckboxList";
        param += "&panel=group_checkbox_list";
        param += "&"+cmsUser.f_user_id+"="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    }, 
    tabSizeTbl: function () {
        $('#swAccessAll').css('height',519);
        cmsUser.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swAccessAll').css('height',353);
        cmsUser.heightTab = 353;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swAccessAll').css('height',cmsUser.heightTab);
    }
}
function loginToCMS (){
    new jj("do=Access_User.login&"+(new jj("#swLoginForm").jjSerial())).jjAjax2();
}
