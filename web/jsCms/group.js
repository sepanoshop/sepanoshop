var cmsGroup ={
    tableName : "Access_Group",
    f_id : "id",
    f_group_id : "group_id",
    f_title : "group_title",
    f_des : "group_des",
    f_c : "group_c",
    loadForm:function(){
        if($("#swGroupForm").html()==''){
            $("#swGroupForm").load("formCms/group.html", null, function(){
                $("#cancel_Group").button().click(function(e) {
                    cmsGroup.m_clean();
                    cmsGroup.m_show_tbl();
                });
                addCheckboxFunction()
            });
        }
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+cmsGroup.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swGroupTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsGroup.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swGroupTbl').hide();
        $('#swGroupForm').show();
        cmsGroup.tabSizeForm();
    },
    m_clean:function(){
        new jj("#"+cmsGroup.f_group_id).jjVal('');
        new jj("#"+cmsGroup.f_title).jjVal('');
        new jj("#"+cmsGroup.f_des).jjVal('');
        for(var i = 1; i < 61;i++){
            new jj("#C"+(i<10?"0"+i:i)).jjVal('');
        }
    },
    m_add_new:function(){
        jj("do="+cmsGroup.tableName+".add_new").jjAjax2(false);
        cmsGroup.m_show_form();
        cmsGroup.m_clean();
        
    },
    m_show_tbl:function(){
        $('#swGroupTbl').show();
        $('#swGroupForm').hide();
        
        if($('#swGroupTbl').html()==""){
            cmsGroup.m_refresh();
        }
        cmsGroup.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsGroup.tableName+".insert";
        param += "&" + cmsGroup.f_title + "=" + new jj("#"+cmsGroup.f_title).jjVal();
        param += "&" + cmsGroup.f_des + "=" + new jj("#"+cmsGroup.f_des).jjVal();
        for(var i = 1; i < 81;i++){
            //            new jj("#"+cmsGroup.f_c+(i<10?"0"+i:i)).jjVal('');
            var row = (i<10?"0"+i:i);
            param += "&" +cmsGroup.f_c + row + "=" + new jj("#C"+row).jjVal();
        }
        jj(param).jjAjax2(false);
        cmsGroup.m_show_tbl();
        cmsGroup.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsGroup.tableName+".edit";
        param += "&" + cmsGroup.f_id + "=" + new jj("#"+cmsGroup.f_group_id).jjVal();
        param += "&" + cmsGroup.f_title + "=" + new jj("#"+cmsGroup.f_title).jjVal();
        param += "&" + cmsGroup.f_des + "=" + new jj("#"+cmsGroup.f_des).jjVal();
        for(var i = 1; i < 81;i++){
            //            new jj("#"+cmsGroup.f_c+(i<10?"0"+i:i)).jjVal('');
            var row = (i<10?"0"+i:i);
            param += "&" +cmsGroup.f_c + row + "=" + new jj("#C"+row).jjVal();
        }
        jj(param).jjAjax2(false);
        cmsGroup.m_show_tbl();
        cmsGroup.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsGroup.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsGroup.tableName+".delete";
        param += "&" + cmsGroup.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsGroup.m_show_tbl();
        cmsGroup.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+cmsGroup.tableName+".select";
        param += "&" + cmsGroup.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsGroup.m_show_form();
        new jj("#"+cmsGroup.f_group_id).jjVal(id);
        new jj("#"+cmsGroup.f_id).jjVal(id);
    },
    tabSizeTbl: function () {
        $('#swAccessAll').css('height',519);
        cmsGroup.heightTab=519;
    },
    tabSizeForm: function () {
        $('#swAccessAll').css('height',839);
        cmsGroup.heightTab=829;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swAccessAll').css('height',cmsGroup.heightTab);
    }
}
function addCheckboxFunction(){
    $("#C01").change(function(e) {
        new jj("#C02").jjVal(new jj("#C01").jjVal());
        new jj("#C03").jjVal(new jj("#C01").jjVal());
        new jj("#C04").jjVal(new jj("#C01").jjVal());
        new jj("#C05").jjVal(new jj("#C01").jjVal());
    });
    $("#C02").change(function(e) {
        if(new jj("#C02").jjVal()=='1'){
            new jj("#C01").jjVal(true);
        }
    });
    $("#C03").change(function(e) {
        if(new jj("#C03").jjVal()=='1'){
            new jj("#C01").jjVal(true);
        }
    });
    $("#C04").change(function(e) {
        if(new jj("#C04").jjVal()=='1'){
            new jj("#C01").jjVal(true);
        }
    });
    $("#C05").change(function(e) {
        if(new jj("#C05").jjVal()=='1'){
            new jj("#C01").jjVal(true);
        }
    });
    $("#C06").change(function(e) {
        new jj("#C07").jjVal(new jj("#C06").jjVal());
        new jj("#C08").jjVal(new jj("#C06").jjVal());
        new jj("#C09").jjVal(new jj("#C06").jjVal());
        new jj("#C10").jjVal(new jj("#C06").jjVal());
    });
    $("#C07").change(function(e) {
        if(new jj("#C07").jjVal()=='1'){
            new jj("#C06").jjVal(true);
        }
    });
    $("#C08").change(function(e) {
        if(new jj("#C08").jjVal()=='1'){
            new jj("#C06").jjVal(true);
        }
    });
    $("#C09").change(function(e) {
        if(new jj("#C09").jjVal()=='1'){
            new jj("#C06").jjVal(true);
        }
    });
    $("#C10").change(function(e) {
        if(new jj("#C10").jjVal()=='1'){
            new jj("#C06").jjVal(true);
        }
    });
    $("#C11").change(function(e) {
        new jj("#C12").jjVal(new jj("#C11").jjVal());
        new jj("#C13").jjVal(new jj("#C11").jjVal());
        new jj("#C14").jjVal(new jj("#C11").jjVal());
    });
    $("#C12").change(function(e) {
        if(new jj("#C12").jjVal()=='1'){
            new jj("#C11").jjVal(true);
        }
    });
    $("#C13").change(function(e) {
        if(new jj("#C13").jjVal()=='1'){
            new jj("#C11").jjVal(true);
        }
    });
    $("#C14").change(function(e) {
        if(new jj("#C14").jjVal()=='1'){
            new jj("#C11").jjVal(true);
        }
    });
    $("#C15").change(function(e) {
        new jj("#C16").jjVal(new jj("#C15").jjVal());
        new jj("#C17").jjVal(new jj("#C15").jjVal());
        new jj("#C18").jjVal(new jj("#C15").jjVal());
    });
    $("#C16").change(function(e) {
        if(new jj("#C16").jjVal()=='1'){
            new jj("#C15").jjVal(true);
        }
    });
    $("#C17").change(function(e) {
        if(new jj("#C17").jjVal()=='1'){
            new jj("#C15").jjVal(true);
        }
    });
    $("#C18").change(function(e) {
        if(new jj("#C18").jjVal()=='1'){
            new jj("#C15").jjVal(true);
        }
    });
    $("#C19").change(function(e) {
        new jj("#C20").jjVal(new jj("#C19").jjVal());
        new jj("#C21").jjVal(new jj("#C19").jjVal());
        new jj("#C22").jjVal(new jj("#C19").jjVal());
        new jj("#C23").jjVal(new jj("#C19").jjVal());
    });
    $("#C20").change(function(e) {
        if(new jj("#C20").jjVal()=='1'){
            new jj("#C19").jjVal(true);
        }
    });
    $("#C21").change(function(e) {
        if(new jj("#C21").jjVal()=='1'){
            new jj("#C19").jjVal(true);
        }
    });
    $("#C22").change(function(e) {
        if(new jj("#C22").jjVal()=='1'){
            new jj("#C19").jjVal(true);
        }
    });
    $("#C23").change(function(e) {
        if(new jj("#C23").jjVal()=='1'){
            new jj("#C19").jjVal(true);
        }
    });
    $("#C24").change(function(e) {
        new jj("#C25").jjVal(new jj("#C24").jjVal());
        new jj("#C26").jjVal(new jj("#C24").jjVal());
        new jj("#C27").jjVal(new jj("#C24").jjVal());
        new jj("#C28").jjVal(new jj("#C24").jjVal());
    });
    $("#C25").change(function(e) {
        if(new jj("#C25").jjVal()=='1'){
            new jj("#C24").jjVal(true);
        }
    });
    $("#C26").change(function(e) {
        if(new jj("#C26").jjVal()=='1'){
            new jj("#C24").jjVal(true);
        }
    });
    $("#C27").change(function(e) {
        if(new jj("#C27").jjVal()=='1'){
            new jj("#C24").jjVal(true);
        }
    });
    $("#C28").change(function(e) {
        if(new jj("#C28").jjVal()=='1'){
            new jj("#C24").jjVal(true);
        }
    });
    $("#C29").change(function(e) {
        new jj("#C30").jjVal(new jj("#C29").jjVal());
        new jj("#C31").jjVal(new jj("#C29").jjVal());
        new jj("#C32").jjVal(new jj("#C29").jjVal());
    });
    $("#C30").change(function(e) {
        if(new jj("#C30").jjVal()=='1'){
            new jj("#C29").jjVal(true);
        }
    });
    $("#C31").change(function(e) {
        if(new jj("#C31").jjVal()=='1'){
            new jj("#C29").jjVal(true);
        }
    });
    $("#C32").change(function(e) {
        if(new jj("#C32").jjVal()=='1'){
            new jj("#C29").jjVal(true);
        }
    });
    $("#C33").change(function(e) {
        new jj("#C34").jjVal(new jj("#C33").jjVal());
        new jj("#C35").jjVal(new jj("#C33").jjVal());
        new jj("#C36").jjVal(new jj("#C33").jjVal());
    });
    $("#C34").change(function(e) {
        if(new jj("#C34").jjVal()=='1'){
            new jj("#C33").jjVal(true);
        }
    });
    $("#C35").change(function(e) {
        if(new jj("#C35").jjVal()=='1'){
            new jj("#C33").jjVal(true);
        }
    });
    $("#C36").change(function(e) {
        if(new jj("#C36").jjVal()=='1'){
            new jj("#C33").jjVal(true);
        }
    });
    $("#C37").change(function(e) {
        new jj("#C38").jjVal(new jj("#C37").jjVal());
        new jj("#C39").jjVal(new jj("#C37").jjVal());
        new jj("#C40").jjVal(new jj("#C37").jjVal());
        new jj("#C41").jjVal(new jj("#C37").jjVal());
    });
    $("#C38").change(function(e) {
        if(new jj("#C38").jjVal()=='1'){
            new jj("#C37").jjVal(true);
        }
    });
    $("#C39").change(function(e) {
        if(new jj("#C39").jjVal()=='1'){
            new jj("#C37").jjVal(true);
        }
    });
    $("#C40").change(function(e) {
        if(new jj("#C40").jjVal()=='1'){
            new jj("#C37").jjVal(true);
        }
    });
    $("#C41").change(function(e) {
        if(new jj("#C41").jjVal()=='1'){
            new jj("#C37").jjVal(true);
        }
    });
    $("#C42").change(function(e) {
        new jj("#C43").jjVal(new jj("#C42").jjVal());
        new jj("#C44").jjVal(new jj("#C42").jjVal());
        new jj("#C45").jjVal(new jj("#C42").jjVal());
        new jj("#C46").jjVal(new jj("#C42").jjVal());
    });
    $("#C43").change(function(e) {
        if(new jj("#C43").jjVal()=='1'){
            new jj("#C42").jjVal(true);
        }
    });
    $("#C44").change(function(e) {
        if(new jj("#C44").jjVal()=='1'){
            new jj("#C42").jjVal(true);
        }
    });
    $("#C45").change(function(e) {
        if(new jj("#C45").jjVal()=='1'){
            new jj("#C42").jjVal(true);
        }
    });
    $("#C46").change(function(e) {
        if(new jj("#C46").jjVal()=='1'){
            new jj("#C42").jjVal(true);
        }
    });
    $("#C47").change(function(e) {
        new jj("#C48").jjVal(new jj("#C47").jjVal());
        new jj("#C49").jjVal(new jj("#C47").jjVal());
        new jj("#C50").jjVal(new jj("#C47").jjVal());
    });
    $("#C48").change(function(e) {
        if(new jj("#C48").jjVal()=='1'){
            new jj("#C47").jjVal(true);
        }
    });
    $("#C49").change(function(e) {
        if(new jj("#C49").jjVal()=='1'){
            new jj("#C47").jjVal(true);
        }
    });
    $("#C50").change(function(e) {
        if(new jj("#C50").jjVal()=='1'){
            new jj("#C47").jjVal(true);
        }
    });
    $("#C51").change(function(e) {
        new jj("#C52").jjVal(new jj("#C51").jjVal());
        new jj("#C53").jjVal(new jj("#C51").jjVal());
        new jj("#C54").jjVal(new jj("#C51").jjVal());
        new jj("#C55").jjVal(new jj("#C51").jjVal());
    });
    $("#C52").change(function(e) {
        if(new jj("#C52").jjVal()=='1'){
            new jj("#C51").jjVal(true);
        }
    });
    $("#C53").change(function(e) {
        if(new jj("#C53").jjVal()=='1'){
            new jj("#C51").jjVal(true);
        }
    });
    $("#C54").change(function(e) {
        if(new jj("#C54").jjVal()=='1'){
            new jj("#C51").jjVal(true);
        }
    });
    $("#C55").change(function(e) {
        if(new jj("#C55").jjVal()=='1'){
            new jj("#C51").jjVal(true);
        }
    });
    $("#C56").change(function(e) {
        new jj("#C57").jjVal(new jj("#C56").jjVal());
        new jj("#C58").jjVal(new jj("#C56").jjVal());
        new jj("#C59").jjVal(new jj("#C56").jjVal());
        new jj("#C60").jjVal(new jj("#C56").jjVal());
    });
    $("#C57").change(function(e) {
        if(new jj("#C57").jjVal()=='1'){
            new jj("#C56").jjVal(true);
        }
    });
    $("#C58").change(function(e) {
        if(new jj("#C58").jjVal()=='1'){
            new jj("#C56").jjVal(true);
        }
    });
    $("#C59").change(function(e) {
        if(new jj("#C59").jjVal()=='1'){
            new jj("#C56").jjVal(true);
        }
    });
    $("#C60").change(function(e) {
        if(new jj("#C60").jjVal()=='1'){
            new jj("#C56").jjVal(true);
        }
    });
    $("#C62").change(function(e) {
        if(new jj("#C62").jjVal()=='1'){
            new jj("#C61").jjVal(true);
        }
    });
}
