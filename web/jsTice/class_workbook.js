var tice_workbook ={
    tableName : "Tice_workbook",//.java
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
    //there is no indipendent '.html' file for workbook 
    //all in class_list.js
    },
    m_search:function(containerId,sortField,tableHeight,searchId){
    //        var param = "";
    //        param += "do="+tice_workbook.tableName+".select";
    //        param += "&ticeWorkbook_ClassId="+(searchId==null ? "0" : searchId);
    //        param += "&panel=" + (containerId==null ? "swClassWorkbookTbl" : containerId);
    //        param += "&sort=" + (sortField==null ? "0" : sortField);
    //        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
    //        jj(param).jjAjax2(false);
    //        tice_workbook.tabSizeTbl();
    },
    //    m_show_form:function(){
    //        $('#swClassWorkbookTbl').hide();
    //        $('#swClassWorkbookForm').show();
    //        tice_workbook.tabSizeForm();
    //    },
    m_clean:function(){
        new jj("#class_scoresList_tbl_div").jjFormClean();
    //        new jj("#"+tice_workbook.f_content_id).jjVal("");
    //        new jj("#"+tice_workbook.f_title).jjVal("");
    //        new jj("#"+tice_workbook.f_lang).jjVal("1");
    //        new jj("#"+tice_workbook.f_parent).jjVal("0");
    //        new jj(student_content_editor).jjEditorVal("");
    },    
    //    m_show_tbl:function(){
    //        $('#swClassWorkbookTbl').show();
    //        $('#swClassWorkbookForm').hide();
    //        if($('#swContentTbl').html()==""){
    //            tice_workbook.m_refresh();
    //        }
    //        tice_workbook.tabSizeTbl();
    //    },
    m_validation:function(){
        return "";
    },
    
    m_edit:function(workbookId){
        var valid = tice_workbook.m_validation();
        
        var score1 = new jj('#ticeWorkbook_Score1').jjVal();
        var index1 =score1.indexOf(".");
        score1 = index1==-1?(new jj(score1).jjIsDigit()?new jj(score1).jjConvertToInt():0):new jj(score1.substring(0, index1)).jjConvertToInt() ;
        var out1 = $('#ticeWorkbook_Out1').html().replace("/", "").replace(" ", "");
        out1 = new jj(out1).jjIsDigit()?new jj(out1).jjConvertToInt():0;
        
        var score2 = new jj('#ticeWorkbook_Score2').jjVal();
        var index2 =score2.indexOf(".");
        score2 = index2==-1?(new jj(score2).jjIsDigit()?new jj(score2).jjConvertToInt():0):new jj(score2.substring(0, index2)).jjConvertToInt() ;
        var out2 = $('#ticeWorkbook_Out2').html().replace("/", "").replace(" ", "");
        out2 = new jj(out2).jjIsDigit()?new jj(out2).jjConvertToInt():0;
        
        var score3 = new jj('#ticeWorkbook_Score3').jjVal();
        var index3 =score3.indexOf(".");
        score3 = index3==-1?(new jj(score3).jjIsDigit()?new jj(score3).jjConvertToInt():0):new jj(score3.substring(0, index3)).jjConvertToInt() ;
        var out3 = $('#ticeWorkbook_Out3').html().replace("/", "").replace(" ", "");
        out3 = new jj(out3).jjIsDigit()?new jj(out3).jjConvertToInt():0;
        
        var score4 = new jj('#ticeWorkbook_Score4').jjVal();
        var index4 =score4.indexOf(".");
        score4 = index4==-1?(new jj(score4).jjIsDigit()?new jj(score4).jjConvertToInt():0):new jj(score4.substring(0, index4)).jjConvertToInt() ;
        var out4 = $('#ticeWorkbook_Out4').html().replace("/", "").replace(" ", "");
        out4 = new jj(out4).jjIsDigit()?new jj(out4).jjConvertToInt():0;
        
        var score5 = new jj('#ticeWorkbook_Score5').jjVal();
        var index5 =score5.indexOf(".");
        score5 = index5==-1?(new jj(score5).jjIsDigit()?new jj(score5).jjConvertToInt():0):new jj(score5.substring(0, index5)).jjConvertToInt() ;
        var out5 = $('#ticeWorkbook_Out5').html().replace("/", "").replace(" ", "");
        out5 = new jj(out5).jjIsDigit()?new jj(out5).jjConvertToInt():0;
        
        var score6 = new jj('#ticeWorkbook_Score6').jjVal();
        var index6 =score6.indexOf(".");
        score6 = index6==-1?(new jj(score6).jjIsDigit()?new jj(score6).jjConvertToInt():0):new jj(score6.substring(0, index6)).jjConvertToInt() ;
        var out6 = $('#ticeWorkbook_Out6').html().replace("/", "").replace(" ", "");
        out6 = new jj(out6).jjIsDigit()?new jj(out6).jjConvertToInt():0;
        
        var score7 = new jj('#ticeWorkbook_Score7').jjVal();
        var index7 =score7.indexOf(".");
        score7 = index7==-1?(new jj(score7).jjIsDigit()?new jj(score7).jjConvertToInt():0):new jj(score7.substring(0, index7)).jjConvertToInt() ;
        var out7 = $('#ticeWorkbook_Out7').html().replace("/", "").replace(" ", "");
        out7 = new jj(out7).jjIsDigit()?new jj(out7).jjConvertToInt():0;
        
        var isValid = true;
//        alert(score1+'>'+out1);
//        alert(score2+'>'+out2);
//        alert(score3+'>'+out3);
//        alert(score4+'>'+out4);
//        alert(score5+'>'+out5);
//        alert(score6+'>'+out6);
//        alert(score7+'>'+out7);
        if(score1>out1){
            isValid=  false;
        }
        if(score2>out2){
            isValid=  false;
        }
        if(score3>out3){
            isValid=  false;
        }
        if(score4>out4){
            isValid=  false;
        }
        if(score5>out5){
            isValid=  false;
        }
        if(score6>out6){
            isValid=  false;
        }
        if(score7>out7){
            isValid=  false;
        }
        if(!isValid){
            new jj ('نمرات وارد شده نباید بزرگتر از بارم آن باشد.').jjDialog();
            return false;
        }
        if(valid==""){
            var param = "";
            param += "do="+tice_workbook.tableName+".edit";
            param += "&"+tice_workbook.f_id+"="+workbookId;
            param += "&"+ new jj('#class_scoresList_tbl_div').jjSerial();
            
            jj(param).jjAjax2(false);
        //            tice_workbook.m_show_tbl();
        //            tice_workbook.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    }, 
    m_select:function(workbookId){                       
        $("#class_scoresList_tbl_div").show();
        var param = "";
        param += "do="+tice_workbook.tableName+".select"; 
        param +="&id="+workbookId;
        jj(param).jjAjax2(false);         
    },    
    tabSizeTbl: function () {
        $('#swClassWorkbook').css('height',520);
    },
    tabSizeForm: function () {
        $('#swClassWorkbook').css('height',381);
    },
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_workbook.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+tice_workbook.tableName+".deleteRow";
        param += "&id=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("پس از حذف درصورتی که دوباره به اطلاعات کلاس برگردید، سطر حذف شده با مقادیر تهی باز گردانده می شود.").jjDialog();
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
    }
}
