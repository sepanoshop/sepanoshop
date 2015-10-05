var cmsEnrolment = {
    tableName : "Enrolment",
    f_id :"id",
    loadForm:function(){
        if($("#swEnrolmentForm").html()==''){
            $("#swEnrolmentForm").load("formCms/enrolment.html", null, function(){
                $("#cancel_enrolment").button().click(function(e) {
                    cmsEnrolment.m_clean();
                    cmsEnrolment.m_show_tbl();
                });
            });
        }
    }, 
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsEnrolment.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swEnrolmentTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsEnrolment.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swEnrolmentTbl').hide();
        $('#swEnrolmentForm').show();
        cmsEnrolment.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swEnrolmentForm").jjFormClean();
       
    },
    m_show_tbl:function(){
        $('#swEnrolmentTbl').show();
        $('#swEnrolmentForm').hide();
        cmsEnrolment.m_refresh();
        cmsEnrolment.tabSizeTbl();
    },
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsEnrolment.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsEnrolment.tableName+".delete";
        param += "&" + cmsEnrolment.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsEnrolment.m_show_tbl();
        cmsEnrolment.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsEnrolment.tableName+".select";
        param += "&" + cmsEnrolment.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsEnrolment.m_show_form();
        
    },
    m_getMenu:function(){
        var param = "";
        param += "do="+cmsEnrolment.tableName+".getMenu";
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swEnrolment').css('height',515);
        cmsEnrolment.heightTab=515;
    },
    tabSizeForm: function () {
        $('#swEnrolment').css('height',418);
        cmsEnrolment.heightTab=418;
    },
    heightTab:"515",
    mainTabSetSize : function () {
        $('#swEnrolment').css('height',cmsEnrolment.heightTab);
    }
}
