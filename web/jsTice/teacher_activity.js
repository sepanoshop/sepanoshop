var tice_teacher_activity ={
    tableName : "Tice_student",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
        if($("#swTeacherActivityForm").html()==''){
            $("#swTeacherActivityForm").load("formTice/teacher_activity.html", null, function(){
//                $("#student_week_program_btn_search").button().click(function(){
//                    alert(jj("#student_week_program_id_search").jjVal());
//                });
//                  jj("#student_week_program_id_search").jjSetTextFieldOnlyGetNumber();
            });
        }
    },
    m_search:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+ticeStudent.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swStudentListTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        ticeStudent.tabSizeTbl();
    }
}
