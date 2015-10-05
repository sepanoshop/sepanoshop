var tice_student_fish ={
    tableName : "Tice_student",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
        if($("#swStudentFishForm").html()==''){
            $("#swStudentFishForm").load("formTice/student_fish.html", null, function(){
                $("#student_fish_btn_search").button().click(function(){
                    alert(jj("#student_fish_search").jjVal());
                });
                  jj("#student_fish_search").jjSetTextFieldOnlyGetNumber();
            });
        }
    },
    m_search:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+ticeStudent_fish.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swStudentListTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        ticeStudent.tabSizeTbl();
    }
}
