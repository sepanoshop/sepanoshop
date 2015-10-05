var cmsEnrolment3 = {
    tableName : "Enrolment3",
    f_id :"id",
    loadForm:function(){
        if($("#swEnrolmentForm3").html()==''){
            $("#swEnrolmentForm3").load("formCms/enrolment2.html", null, function(){
                $("#cancel_enrolment3").button().click(function(e) {
                    cmsEnrolment3.m_clean();
                    cmsEnrolment3.m_show_tbl();
                });
            });
        }
    }, 
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsEnrolment3.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swEnrolmentTbl3" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsEnrolment3.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swEnrolmentTbl3').hide();
        $('#swEnrolmentForm3').show();
        cmsEnrolment3.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swEnrolmentForm3").jjFormClean();
       
    },
    m_show_tbl:function(){
        $('#swEnrolmentTbl3').show();
        $('#swEnrolmentForm3').hide();
        cmsEnrolment3.m_refresh();
        cmsEnrolment3.tabSizeTbl();
    },
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsEnrolment3.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsEnrolment3.tableName+".delete";
        param += "&" + cmsEnrolment3.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsEnrolment3.m_show_tbl();
        cmsEnrolment3.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsEnrolment3.tableName+".select";
        param += "&" + cmsEnrolment3.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsEnrolment3.m_show_form();
        
    },
    m_getMenu:function(){
        var param = "";
        param += "do="+cmsEnrolment3.tableName+".getMenu";
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swEnrolment3').css('height',515);
        cmsEnrolment3.heightTab=515;
    },
    tabSizeForm: function () {
        $('#swEnrolment3').css('height',680);
        cmsEnrolment3.heightTab=680;
    },
    heightTab:"790",
    mainTabSetSize : function () {
        $('#swEnrolment3').css('height',cmsEnrolment3.heightTab);
    }
}
