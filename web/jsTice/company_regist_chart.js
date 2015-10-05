var tice_company_regist_chart ={
    tableName : "Tice_student",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
        if($("#swCompanyRegistChartForm").html()==''){
            $("#swCompanyRegistChartForm").load("formTice/company_regist_chart.html", null, function(){
                $("#btnRegistCountChart").button().click(function(){
                    });
                $("#btnRegistAmountChart").button().click(function(){
                    });
            //                  jj("#student_week_program_id_search").jjSetTextFieldOnlyGetNumber();
            });
        }
    }
}
