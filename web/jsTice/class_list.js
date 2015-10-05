var tice_class_list = {
    tableName: "Tice_class",
    f_id: "id",
    f_content_id: "content_id",
    f_parent: "content_parent",
    f_content: "content_content",
    f_title: "content_title",
    f_lang: "content_lang",
    loadForm: function() {
        if ($("#swClassListForm").html() == '') {
            $("#swClassListForm").load("formTice/class_list.html", null, function() {
                new jj('#ticeClass_StartingDate').jjCalendarWithYearSelector(1380, 1400);
                new jj('#ticeClass_MidtermExamDate').jjCalendarWithYearSelector(1380, 1400);
                new jj('#ticeClass_FinalExamDate').jjCalendarWithYearSelector(1380, 1400);

                new jj('#ticeClass_Cap').jjSetTextFieldOnlyGetNumber(3);
                new jj('#ticeClass_NoOfSessions').jjSetTextFieldOnlyGetNumber(3);
                new jj('#ticeClass_Fee').jjSetTextFieldOnlyGetNumber();

                $('#ticeClass_sms').keyup(function() {
                    $('#ticeClass_sms_lengh').html(new jj('#ticeClass_sms').jjGetLength() + "/70");
                });

                $("#ticeClass_Pools").keyup(function() {
                    jj('do=Tice_class.checkClassCodeNo&panel=ticeClass_Pools&text=' + new jj("#ticeClass_Pools").jjVal() + "&term=" + new jj("#ticeClass_TermId").jjVal()).jjAjax2(false);
                });
                $("#ticeClass_TermId").change(function() {
                    jj('do=Tice_class.checkClassCodeNo&panel=ticeClass_Pools&text=' + new jj("#ticeClass_Pools").jjVal() + "&term=" + new jj("#ticeClass_TermId").jjVal()).jjAjax2(false);
                });

                $("#ticeClass_TermId").change(function() {
                    new jj("do=" + tice_term.tableName + ".getExamDateForClass&id=" + new jj("#ticeClass_TermId").jjVal()).jjAjax2();
                });
                $("#ticeClass_LevelId").change(function() {
                    new jj("do=" + tice_level.tableName + ".getBookForClass&id=" + new jj("#ticeClass_LevelId").jjVal()).jjAjax2();
                });

                new jj('#sh0_1').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh1_1').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh2_1').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh3_1').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh4_1').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh5_1').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh6_1').jjSetTextFieldOnlyGetNumber(3);

                new jj('#sh0_2').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh1_2').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh2_2').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh3_2').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh4_2').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh5_2').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh6_2').jjSetTextFieldOnlyGetNumber(3);

                new jj('#sh0_3').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh1_3').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh2_3').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh3_3').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh4_3').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh5_3').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh6_3').jjSetTextFieldOnlyGetNumber(3);

                new jj('#sh0_4').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh1_4').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh2_4').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh3_4').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh4_4').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh5_4').jjSetTextFieldOnlyGetNumber(3);
                new jj('#sh6_4').jjSetTextFieldOnlyGetNumber(3);

                $("#cancel_Class").button().click(function(e) {
                    tice_class_list.m_show_tbl();
                    tice_class_list.m_clean();
                });
                $("#cancel_Workbook").button().click(function(e) {
                    $("#class_scoresList_tbl_div").hide();
                });
                $("#class_scoresList_tbl_div").hide();//on load this div makes hiden
                tice_class_list.m_refresh("swClassListTbl");//show list of classes
                manageDropDown.getListLevelForClass();
                manageDropDown.getListLevelForClass2();
                manageDropDown.getListTermForClass();
                manageDropDown.getListTeacherForClass();
                manageDropDown.getListBookForClass();
                manageDropDown.getListRoomForClass();
            });
            $("#swClassListForm").hide();
        }
        ;

        //    $("#swClassListForm").show();
    },
    m_refresh: function(containerId, sortField, tableHeight) {
        var param = "";
        param += "do=Tice_class.refresh";
        param += "&panel=" + (containerId == null ? "swClassListTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        tice_class_list.tabSizeTbl();
    },
    m_refresh_all: function(containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + tice_class_list.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swClassListTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        tice_class_list.tabSizeTbl();
    },
    m_show_form: function() {
        $('#swClassListTbl').hide();
        $('#swClassListForm').show();
        tice_class_list.tabSizeForm();
    },
    m_clean: function() {
        new jj("#swClassListForm").jjFormClean();
        new jj("#" + tice_class_list.f_content_id).jjVal("");
        new jj("#" + tice_class_list.f_title).jjVal("");
        //        new jj("#"+tice_class_list.f_lang).jjVal("1");
        //        new jj("#"+tice_class_list.f_parent).jjVal("0");
        //        new jj(class_content_editor).jjEditorVal("");
        new jj('#ticeClass_NoOfSessions').jjVal("20");
        new jj('#ticeClass_Cap').jjVal("15");
    },
    m_add_new: function() {
        jj("do=" + tice_class_list.tableName + ".add_new").jjAjax2(false);
        tice_class_list.m_show_form();
        tice_class_list.m_clean();
        $('#class_workbook_tbl_div').html('');
        new jj('#ticeClass_registerLock').jjVal("1");
        $('#ticeClass_sms_lengh').html("");
        $('#swClassAll').css('height', 302);
        tice_class_list.heightTab = 302;
    },
    m_show_tbl: function() {
        $('#swClassListTbl').show();
        $('#swClassListForm').hide();
        if ($('#swContentTbl').html() == "") {
            tice_class_list.m_refresh();
        }
        tice_class_list.tabSizeTbl();
        //        $('#refreshClass').dataTable().fnSort( [ [0,'desc'] ] );
    },
    m_insert: function() {
        var valid = tice_class_list.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + tice_class_list.tableName + ".insert";
            param += "&" + new jj('#swClassListForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_class_list.m_show_tbl();
            tice_class_list.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_edit: function() {
        var valid = tice_class_list.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + tice_class_list.tableName + ".edit";
            param += "&" + new jj('#swClassListForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_class_list.m_show_tbl();
            tice_class_list.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_validation: function() {// mohamdad
        if ($("#ticeClass_Pools").css('color') == 'red') {
            return "کلاس کد وارد شده در گذشته استفاده شده است";
        }
        return "";
    },
    m_delete: function(id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_class_list.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function(id) {
        var param = "";
        param += "do=" + tice_class_list.tableName + ".delete";
        param += "&" + tice_class_list.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        tice_class_list.m_show_tbl();
        tice_class_list.m_clean();
    },
    deletePollForClass: function(id) {
        new jj("آیا از حذف نظرسنجی های مربوط به این کلاس اطمینان دارید؟").jjDialog_YesNo('tice_class_list.deletePollForClass_after_question(' + id + ');\n', true, "");
    },
    deletePollForClass_after_question: function(id) {
        var param = "";
        param += "do=" + tice_class_list.tableName + ".deletePollForClass";
        param += "&id=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
    },
    showPollForClass: function(id) {
        jj('Print2?form=showPollForClass&id=' + id).jjGoNewPage();
    },
    m_select: function(id) {
        $("#tabs").tabs({
            selected: 2
        });
        var param = "";
        param += "do=" + tice_class_list.tableName + ".select";
        param += "&" + tice_class_list.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        //        tice_class_list.m_show_form();
        $('#swClassListTbl').hide();
        $('#swClassListForm').show();
        $('#swClassAll').css('height', 710);
        tice_class_list.heightTab = 710;
        $('#ticeClass_sms_lengh').html("");
    },
    m_add_EN: function(id) {
        var param = "";
        param += "do=" + tice_class_list.tableName + ".add_EN";
        param += "&" + tice_class_list.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        new jj("#" + tice_class_list.f_parent).jjVal(id);
        new jj("#" + tice_class_list.f_lang).jjVal("2");
        tice_class_list.m_show_form();
    },
    m_searchTextInTitle: function(text) {
        var param = "";
        param += "do=" + tice_class_list.tableName + ".searchTextInTitle";
        param += "&text=" + (text == null ? "" : text);
        jj(param).jjAjax2(false);
    },
    m_searchTextInAll: function(text) {
        var param = "";
        param += "do=" + tice_class_list.tableName + ".searchTextInAll";
        param += "&text=" + (text == null ? "" : text);
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function() {
        $('#swClassAll').css('height', 520);
        tice_class_list.heightTab = 520;
    },
    tabSizeForm: function() {
        $('#swClassAll').css('height', 302);
        tice_class_list.heightTab = 302;
    },
    heightTab: "520",
    mainTabSetSize: function() {
        $('#swClassAll').css('height', tice_class_list.heightTab);
    },
    sendSmsClass:function() {
        new jj("do=Tice_class.sendSms&Numbers=" + (new jj("#receiver_number").jjVal()) + "&text=" + (new jj("#note").jjVal())).jjAjax2(true);
    },
    previewSmsListClass:function() {
        new jj("do=Tice_class.previewSmsList&id=" + (new jj("#ticeClass_id").jjVal()) + "&text=" + (new jj("#ticeClass_sms").jjVal())).jjAjax2(true);
    }
}
