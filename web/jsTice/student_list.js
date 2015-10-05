var tice_student_list = {
    tableName: "Tice_student",
    f_id: "id",
    f_content_id: "content_id",
    f_parent: "content_parent",
    f_content: "content_content",
    f_title: "content_title",
    f_lang: "content_lang",
    loadForm: function() {
        if ($("#swStudentListForm").html() == '') {
            $("#swStudentListForm").load("formTice/tice_student.html", null, function() {
                new jj('#ticeStudents_Birthdate').jjCalendarWithYearSelector(1310, 1410);
                new jj('#ticeStudents_RegistratinDate').jjCalendarWithYearSelector(1310, 1410);

                new jj('#ticeStudents_PlacementTest').jjSetTextFieldOnlyGetNumber(3);
                new jj('#ticeStudents_IDNumber').jjSetTextFieldOnlyGetNumber();

                $("#cancel_Student").button().click(function(e) {
                    tice_student_list.m_show_tbl();
                    tice_student_list.m_clean();
                });
                $('#ticeStudents_LastName').keyup(function() {
                    if (new jj('#ticeStudents_LastName').jjVal().length > 12) {
                        jj("do=Tice_student.checkFishNo&text=" + new jj('#ticeStudents_LastName').jjVal() + "&panel=studentBingInDatabase").jjAjax2(false);
                    }
                });
                $('#ticeStudents_sms').keyup(function() {
                    $('#ticeStudents_sms_lengh').html(new jj('#ticeStudents_sms').jjGetLength() + "/70");
                });

                $('#ticeStudents_FatherName').blur(function() {
                    $("#studentBingInDatabase").html('');
                });
                // -------------- pic
                $('#upload_Student_pic').button().click(function() {
                    });
                $('#upload_Student_btn').button().click(function() {
                    if (new jj('#upload_Student_pic').jjVal() != '') {
                        $('#upload_Student_btn').hide();
                        $('#upload_Student_pic').hide();
                        $('#ticeStudents_PicName').show()
                    }
                });
                $('#ticeStudents_PicName').button().click(function() {
                    $('#upload_Student_btn').show();
                    $('#upload_Student_pic').show();
                    $('#ticeStudents_PicName').hide()
                });
                new jj('#upload_Student_btn').jjAjaxFileUpload('#upload_Student_pic', '#ticeStudents_PicName', '#student_img_pic', 307200);
                manageDropDown.getListLevelForStudent();
            });
            $("#swStudentListForm").hide();
            this.m_refresh("swStudentListTbl");//show list of students
        }
    ;
    //$("#swStudentListForm").show();
    },
    m_refresh: function(containerId, sortField, tableHeight) {
        var param = "";
//      param += "do=" + tice_student_list.tableName + ".refreshLast";
        param += "do=" + tice_student_list.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swStudentListTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        tice_student_list.tabSizeTbl();
    },
    m_refresh_all: function(containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + tice_student_list.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swStudentListTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        tice_student_list.tabSizeTbl();
    },
    m_show_form: function() {
        $('#swStudentListTbl').hide();
        $('#swStudentListForm').show();
        tice_student_list.tabSizeForm();
    },
    m_clean: function() {
        new jj("#swStudentListForm").jjFormClean();
    },
    m_printIdCard: function(id) {
        new jj("Print?form=TeacherSlary&id=" + id).jjGoNewPage();
    },
    m_add_new: function() {
        $('#studentBingInDatabase').html('');
        $('#tice_student_Status_div').hide();
        jj("do=" + tice_student_list.tableName + ".add_new").jjAjax2(false);
        tice_student_list.m_show_form();
        tice_student_list.m_clean();
        $('#upload_Student_btn').show();
        $('#upload_Student_pic').show();
        $('#ticeStudents_PicName').hide();
        $('#student_img_pic').attr('src', 'img/user.png');

        $('#swStudentAll').css('height', 400);
        tice_student_list.heightTab = 400;
        $('#ticeStudents_sms_lengh').html("");
    },
    m_show_tbl: function() {
        $('#swStudentListTbl').show();
        $('#tice_student_Status_div').show();//this div was hided in 'm_add_new()'
        $('#swStudentListForm').hide();
        if ($('#swStudentListTbl').html() == "") {
            tice_student_list.m_refresh();
        }
        tice_student_list.tabSizeTbl();
        $("#tabs").tabs({
            selected: 0
        });
    },
    m_insert: function() {
        var valid = tice_student_list.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + tice_student_list.tableName + ".insert";
            param += "&" + new jj('#swStudentListForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_student_list.m_show_tbl();
            tice_student_list.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_edit: function() {
        var valid = tice_student_list.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + tice_student_list.tableName + ".edit";
            param += "&" + new jj('#swStudentListForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_student_list.m_show_tbl();
            tice_student_list.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_validation: function() {// mohamdad
        if (new jj("#ticeStudents_LastNameEng").jjVal().length < 1) {
            return "لطفا نام خانوادگی انگلیسی را وارد کنید";
        }
        if (new jj("#ticeStudents_FirstName").jjVal().length < 1) {
            return "لطفا نام را وارد کنید";
        }
        if (new jj("#ticeStudents_LastName").jjVal().length < 1) {
            return "لطفا فامیل دانشجو را وارد کنید";
        }
        return "";
    },
    m_delete: function(id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_student_list.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function(id) {
        var param = "";
        param += "do=" + tice_student_list.tableName + ".delete";
        param += "&" + tice_student_list.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        tice_student_list.m_show_tbl();
        tice_student_list.m_clean();
    },
    m_select: function(id) {
        $('#studentBingInDatabase').html('');
        $('#tice_student_Status_div').show();
        var param = "";
        param += "do=" + tice_student_list.tableName + ".select";
        param += "&" + tice_student_list.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        tice_student_list.m_show_form();
        $("#tabs").tabs({
            selected: 0
        });
        $("#studentBingInDatabase").html('');
        $('#ticeStudents_sms_lengh').html("");
    },
    tabSizeTbl: function() {
        $('#swStudentAll').css('height', 520);
        tice_student_list.heightTab = 520;
    },
    tabSizeForm: function() {
        $('#swStudentAll').css('height', 1160);
        tice_student_list.heightTab = 1160;
    },
    heightTab: "520",
    mainTabSetSize: function() {
        $('#swStudentAll').css('height', tice_student_list.heightTab);
    },
    m_getSexCategory: function(id) {
        var param = "";
        param += "do=" + cmsCategoryNews.tableName + ".getOptions";
        param += "&panel=" + cmsNews.f_category_id_select;
        param += "&id=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
    },
    m_print: function() {
        alert("Alert in : -={ tice_student_list.m_print() }=-");
    },
    previewSmsStudent:function(id) {
        new jj("do=Tice_student.previewSms&id=" + id + "&text=" + (new jj("#ticeStudents_sms").jjVal())).jjAjax2(true);    
    },
    sendSmsStudent:function() {
        new jj("do=Tice_student.sendSms&Numbers=" + (new jj("#receiver_number").jjVal()) + "&text=" + (new jj("#note").jjVal())).jjAjax2(true);
    }
}