var tice_teacher_fish ={
    tableName : "Tice_teacher_salary",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    PanelHeight:"400px",
    
    loadForm:function(){
        if($("#swTeacherFishForm").html()==''){
            $("#swTeacherFishForm").load("formTice/teacher_fish.html", null, function(){
                $("#cancel_TeacherFish").button().click(function(){
                    tice_teacher_fish.m_clean();
                    tice_teacher_fish.m_show_tbl();
                });
                new jj('#ticeTeacherSalary_Date').jjCalendarWithYearSelector(1380, 1410);
                new jj('#ticeTeacherSalary_CheckDate').jjCalendarWithYearSelector(1380, 1410);
                
                new jj('#ticeTeacherSalary_NoOfClasses').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_NoOfSessions').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_SalaryPerSession').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_SubOfSessions').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_ExtraSessoins').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_TotalSessions').jjSetTextFieldOnlyGetNumber();
                
                $("#ticeTeacherSalary_NoOfClasses").keyup(function(){
                    tice_teacher_fish.calculationSession();
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_NoOfSessions").keyup(function(){
                    tice_teacher_fish.calculationSession();
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_SalaryPerSession").keyup(function(){
                    tice_teacher_fish.calculationSession();
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_SubOfSessions").keyup(function(){
                    tice_teacher_fish.calculationSession();
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_ExtraSessoins").keyup(function(){
                    tice_teacher_fish.calculationSession();
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_TotalSessions").keyup(function(){
                    tice_teacher_fish.calculationSession();
                    tice_teacher_fish.calculationSalary();
                });
                
                $("#ticeTeacherSalary_TotalSalary").keyup(function(){
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_OtherExtra").keyup(function(){
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_PadashExtra").keyup(function(){
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_Enssurance").keyup(function(){
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_Tax").keyup(function(){
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_OtherSubtrack").keyup(function(){
                    tice_teacher_fish.calculationSalary();
                });
                $("#ticeTeacherSalary_FinalTotal").keyup(function(){
                    tice_teacher_fish.calculationSalary();
                });
                //                $("#ticeTeacherSalary_Strings").keyup(function(){
                //                    tice_teacher_fish.calculationSalary();
                //                });
                
                new jj('#ticeTeacherSalary_TotalSalary').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_OtherExtra').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_PadashExtra').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_Enssurance').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_Tax').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_OtherSubtrack').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_FinalTotal').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_Strings').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeTeacherSalary_CheckHowMuch').jjSetTextFieldOnlyGetNumber();
                
                //                tice_teacher_fish.m_refresh();
                manageDropDown.getListTermForTeacherSalari();
                manageDropDown.getListTeacherForSalari();
                //                $("#ticeTeacherSalary_CheckDate").jjCalendarWithYearSelector(1387, 1410);
                $("#ticeTeacherSalary_TermId").change(function(){
                    manageDropDown.getListTeacherForSalari();
                });
                $("#ticeTeacherSalary_TeacherID").change(function(){
                    if($("#ticeTeacherSalary_TermId").val()!=""){//term must be selected befor change teacher
                        tice_teacher_fish.m_get_teacher_list_2();
                        tice_teacher_fish.calculationSession();
                        tice_teacher_fish.calculationSalary();
               
                    }
                })
            });
            tice_teacher_fish.m_show_tbl();
        }        
    },
    m_get_teacher_list_2:function(){
        tice_teacher_fish.selectTeacherAccount($("#ticeTeacherSalary_TeacherID").val(),$("#ticeTeacherSalary_TermId").val());
    },
    selectTeacherAccount:function(teacherId,termId){        
        var param = "";
        if(new jj(teacherId).jjIsDigit() && new jj(termId).jjIsDigit()){
            param += "do="+tice_teacher_fish.tableName+".selectTeacherAccount";
            param += "&teacherId=" +teacherId;
            param += "&termId=" +termId;
            jj(param).jjAjax2();
        }
    },
    m_show_form:function(){
        $('#swTeacherFishTbl').hide();
        $('#swTeacherFishForm').show();
        tice_teacher_fish.tabSizeForm();
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+tice_teacher_fish.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swTeacherFishTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        tice_teacher_fish.tabSizeTbl();
    },
    m_insert:function(){
        //        var valid = tice_teacher_fish.m_validation();
        var param = "";
        param += "do="+tice_teacher_fish.tableName+".insert";
        param += "&"+ new jj('#swTeacherFishForm').jjSerial();
        jj(param).jjAjax2(false);
        tice_teacher_fish.m_show_tbl();
        tice_teacher_fish.m_clean();
    },
    m_select:function(id){
        var param = "";
        param += "do="+tice_teacher_fish.tableName+".select";
        param += "&" + tice_teacher_fish.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        tice_teacher_fish.m_show_form();
        tice_teacher_fish.tabSizeForm();
        $( "#tabs" ).tabs({
            selected:4
        });

    },
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_teacher_fish.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+tice_teacher_fish.tableName+".delete";
        param += "&" + tice_teacher_fish.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        tice_teacher_fish.m_show_tbl();
        tice_teacher_fish.m_clean();
    },
    m_edit:function(){
        if(!tice_teacher_fish.m_valid()){
            return false;
        }
        var valid = tice_teacher_fish.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+tice_teacher_fish.tableName+".edit";
            param += "&"+ new jj('#swTeacherFishForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_teacher_fish.m_show_tbl();
            tice_teacher_fish.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    },
    m_valid:function(){
        if(new jj('#ticeTeacherSalary_TeacherID').jjVal()=="" ){
            $('#errorPanelForeacherFish').html('لطفا فیلد نام استاد را وارد کنید.');
            return false;
        }
        if(new jj('#ticeTeacherSalary_TermId').jjVal()=="" ){
            $('#errorPanelForeacherFish').html('لطفا فیلد نام استاد را وارد کنید.');
            return false;
        }
        return true;
    },
    m_validation:function(){// mohamdad
        //        if(new jj("#content_title").jjVal().length<1){
        //            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
        //        }
        return "";
    },
    m_clean:function(){
        new jj("#swTeacherFishForm").jjFormClean();
       
    },
    m_add_new:function(){
        //        $('#tice_teacher_Status_div').hide();
        jj("do="+tice_teacher_fish.tableName+".add_new").jjAjax2(false);
        tice_teacher_fish.m_show_form();
        tice_teacher_fish.m_clean();
    //        oEditor.execCommand( 'bold');
    },
    m_show_tbl:function(){
        tice_teacher_fish.tabSizeTbl();
        $('#swTeacherFishTbl').show();
        $('#swTeacherFishForm').hide();
        tice_teacher_fish.m_refresh();
    },
    tabSizeTbl: function () {
        $('#swTeacherFish').css('height',520);
        tice_teacher_fish.heightTab = 520;
    },
    tabSizeForm: function () {
        $('#swTeacherFish').css('height',274);
        tice_teacher_fish.heightTab = 274;
    },
    heightTab:"517",
    mainTabSetSize : function () {
        $('#swTeacherFish').css('height',tice_teacher_fish.heightTab);
    },
    calculationSession:function(){
        var i=0;
        i+=parseInt(new jj($("#ticeTeacherSalary_NoOfSessions").val()).jjIsDigit() ? new jj($("#ticeTeacherSalary_NoOfSessions").val()).jjConvertToInt() :0);
        i-=parseInt(new jj($("#ticeTeacherSalary_SubOfSessions").val()).jjIsDigit() ? new jj($("#ticeTeacherSalary_SubOfSessions").val()).jjConvertToInt() :0);
        i+=parseInt(new jj($("#ticeTeacherSalary_ExtraSessoins").val()).jjIsDigit() ? new jj($("#ticeTeacherSalary_ExtraSessoins").val()).jjConvertToInt() :0);
        $("#ticeTeacherSalary_TotalSessions").val(i);
    },
    calculationSalary:function(){
        var i=0;//sessionsNo * priceOfEvrySession
        i+=parseInt($("#ticeTeacherSalary_TotalSessions").val()== "" ? 0:$("#ticeTeacherSalary_TotalSessions").val());
        i*=parseInt($("#ticeTeacherSalary_SalaryPerSession").val()== "" ? 0:$("#ticeTeacherSalary_SalaryPerSession").val());
        $("#ticeTeacherSalary_TotalSalary").val(i);
        i=0;//reset i to 0
        i+=parseInt($("#ticeTeacherSalary_TotalSalary").val()== "" ? 0:$("#ticeTeacherSalary_TotalSalary").val());
        i+=parseInt($("#ticeTeacherSalary_OtherExtra").val()== "" ? 0:$("#ticeTeacherSalary_OtherExtra").val());
        i+=parseInt($("#ticeTeacherSalary_PadashExtra").val()== "" ? 0:$("#ticeTeacherSalary_PadashExtra").val());
        i-=parseInt($("#ticeTeacherSalary_Enssurance").val()== "" ? 0:$("#ticeTeacherSalary_Enssurance").val());
        i-=parseInt($("#ticeTeacherSalary_Tax").val()== "" ? 0:$("#ticeTeacherSalary_Tax").val());
        i-=parseInt($("#ticeTeacherSalary_OtherSubtrack").val()== "" ? 0:$("#ticeTeacherSalary_OtherSubtrack").val());
        $("#ticeTeacherSalary_FinalTotal").val(i);
        $("#ticeTeacherSalary_Strings").val(i);
    }
}
