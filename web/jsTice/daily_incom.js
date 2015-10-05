var tice_daily_income ={
    tableName : "Tice_daily_incom",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
        if($("#swDailyIncomForm").html()==''){
            $("#swDailyIncomForm").load("formTice/tice_daily_income.html", null, function(){
//                $("#cancel_Incom").button().click(function(){
//                    tice_daily_income.m_show_tbl();
//                    tice_daily_income.m_clean();                    
//                });                
                //                tice_term.getTermList("ticeEnrol_TermId");
                //                  jj("#student_week_program_id_search").jjSetTextFieldOnlyGetNumber();
                tice_daily_income.m_refresh("swDailyIncomTbl",0,400);  
            });                        
            $("#swDailyIncomForm").hide();
        }
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+this.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swDailyIncomtbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        this.tabSizeTbl();        
    }, 
    m_show_form:function(){
        $('#swDailyIncomTbl').hide();
        $('#swDailyIncomForm').show();
        tice_daily_income.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swDailyIncomForm").jjFormClean();
    //        new jj(student_content_editor).jjEditorVal("");
    },
    m_add_new:function(){
        jj("do="+tice_daily_income.tableName+".add_new").jjAjax2(false);
        tice_daily_income.m_show_form();
        tice_daily_income.m_clean();
        $( "#tabs" ).tabs({
            selected:1
        });
    },
    m_show_tbl:function(){
        $('#swDailyIncomTbl').show();
        $('#swDailyIncomForm').hide();
        tice_daily_income.tabSizeTbl();
    },
    m_insert:function(){
        var valid = tice_daily_income.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+tice_daily_income.tableName+".insert";
            param += "&"+ new jj('#swDailyIncomForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_daily_income.m_show_tbl();
            tice_daily_income.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    },
    m_edit:function(){
        var valid = tice_daily_income.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+tice_daily_income.tableName+".edit";
            param += "&"+ new jj('#swDailyIncomForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_daily_income.m_show_tbl();
            tice_daily_income.m_clean();
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
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_daily_income.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+tice_daily_income.tableName+".delete";
        param += "&" + tice_daily_income.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        tice_daily_income.m_show_tbl();
        tice_daily_income.m_clean();
    }, 
    m_select:function(id){
        $( "#tabs" ).tabs({
            selected:1
        }); 
        var param = "";
        param += "do="+tice_daily_income.tableName+".select";
        param += "&" + tice_daily_income.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        tice_daily_income.m_show_form();
    },
    m_select_std:function(stdid){//this function fill student details in enrol fileds
        var param = "";
        param += "do="+tice_daily_income.tableName+".select_std";
        param += "&"+tice_student_list.f_id +"="+stdid ;//f_id is 'id'
        jj(param).jjAjax2(false);
        tice_daily_income.m_show_form();
    },
    m_select_class:function(classid){//this function fill student details in enrol fileds
        var param = "";
        param += "do="+tice_daily_income.tableName+".select_class";
        param += "&"+tice_class_list.f_id +"="+classid ;//f_id is 'id'
        jj(param).jjAjax2(false);
        tice_daily_income.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swDailyIncom').css('height',520);
    },
    tabSizeForm: function () {
        $('#swDailyIncom').css('height',381);
    },
    mainTabSetSize: function () {
    //        var aa = $("#swContent").children();
    //        var bb = 0;
    //        for(i=0; i < aa.length; i++){  
    //            if($(aa[i]).css("display")!='none'){
    //                bb+= new jj($(aa[i]).css("height")).jjConvertToInt() ;
    //            }
    //        }
    //        if(bb==0){
    //            $('#tabs').css('height',572);
    //        }else{
    //            $('#tabs').css('height',bb+44);
    //        }
    },
    m_search:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+ticeStudent.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swDailyIncomTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        ticeStudent.tabSizeTbl();
    }
}
