var tice_teacher_list ={
    tableName : "Tice_teacher",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
        if($("#swTeacherListForm").html()==''){            
            $("#swTeacherListForm").load("formTice/teacher_list.html", null, function(){                   

                $("#cancel_Teacher").button().click(function(e) {                        
                    tice_teacher_list.m_show_tbl();
                    tice_teacher_list.m_clean();
                });
                new jj('#ticeTeachers_BirthDate').jjCalendarWithYearSelector(1320, 1390);
                new jj('#ticeTeachers_Payment').jjSetTextFieldOnlyGetNumber();  
                new jj('#ticeTeachers_IdCardNo').jjSetTextFieldOnlyGetNumber();  
                // -------------- pic
                $('#upload_Teacher_pic').button().click(function(){});
                $('#upload_Teacher_btn').button().click(function(){
                    if(new jj('#upload_Teacher_pic').jjVal()!=''){
                        $('#upload_Teacher_btn').hide();
                        $('#upload_Teacher_pic').hide();
                        $('#ticeTeacher_PicName').show()
                    }
                });
                $('#ticeTeacher_PicName').button().click(function(){
                    $('#upload_Teacher_btn').show();
                    $('#upload_Teacher_pic').show();
                    $('#ticeTeacher_PicName').hide()
                });
                new jj('#upload_Teacher_btn').jjAjaxFileUpload('#upload_Teacher_pic', '#ticeTeacher_PicName', '#teacher_img_pic',307200);

            });
            $("#swTeacherListForm").hide();
            this.m_refresh("swTeacherListTbl");//show list of teachers
        };       
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+tice_teacher_list.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swTeacherListTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        tice_teacher_list.tabSizeTbl();
    }, 
    m_show_form:function(){
        $('#swTeacherListTbl').hide();
        $('#swTeacherListForm').show();
        tice_teacher_list.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swTeacherListForm").jjFormClean();
        new jj("#"+tice_teacher_list.f_content_id).jjVal("");
        new jj("#"+tice_teacher_list.f_title).jjVal("");
        new jj("#"+tice_teacher_list.f_lang).jjVal("1");
        new jj("#"+tice_teacher_list.f_parent).jjVal("0");
    //        new jj(teacher_content_editor).jjEditorVal("");
    },
    m_add_new:function(){
        $('#tice_teacher_Status_div').hide();
        jj("do="+tice_teacher_list.tableName+".add_new").jjAjax2(false);
        tice_teacher_list.m_show_form();
        tice_teacher_list.m_clean();
        $('#upload_Teacher_btn').show();
        $('#upload_Teacher_pic').show();
        $('#ticeTeacher_PicName').hide();
        $('#teacher_img_pic').attr('src','img/user.png');
        
        $('#swTeacherAll').css('height',383);
        tice_teacher_list.heightTab = 383;
    },
    m_show_tbl:function(){
        $('#tice_teacher_Status_div').show();
        $('#swTeacherListTbl').show();
        $('#swTeacherListForm').hide();
        if($('#swContentTbl').html()==""){
            tice_teacher_list.m_refresh();
        }
        tice_teacher_list.tabSizeTbl();
    },
    m_insert:function(){
        var valid = tice_teacher_list.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+tice_teacher_list.tableName+".insert";
            param += "&"+ new jj('#swTeacherListForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_teacher_list.m_show_tbl();
            tice_teacher_list.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    },
    m_edit:function(){
        var valid = tice_teacher_list.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+tice_teacher_list.tableName+".edit";
            param += "&"+ new jj('#swTeacherListForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_teacher_list.m_show_tbl();
            tice_teacher_list.m_clean();
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
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_teacher_list.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+tice_teacher_list.tableName+".delete";
        param += "&" + tice_teacher_list.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        tice_teacher_list.m_show_tbl();
        tice_teacher_list.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+tice_teacher_list.tableName+".select";
        param += "&" + tice_teacher_list.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        tice_teacher_list.m_show_form();
        tice_teacher_list.tabSizeForm();
         $( "#tabs" ).tabs({
            selected:3
        });
    }, 
    m_searchTextInTitle:function(text){
        var param = "";
        param += "do="+tice_teacher_list.tableName+".searchTextInTitle";
        param += "&text=" + (text==null ? "" : text);
        jj(param).jjAjax2(false);
    }, 
    m_searchTextInAll:function(text){
        var param = "";
        param += "do="+tice_teacher_list.tableName+".searchTextInAll";
        param += "&text=" + (text==null ? "" : text);
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swTeacherAll').css('height',520);
        tice_teacher_list.heightTab = 520;
    },
    tabSizeForm: function () {
        $('#swTeacherAll').css('height',875);
        tice_teacher_list.heightTab = 875;
    },
    heightTab:"520",
    mainTabSetSize : function () {
        $('#swTeacherAll').css('height',tice_teacher_list.heightTab);
    }
}
