var tice_term = {
    tableName: "Tice_term",
    f_id: "id",
    f_content_id: "content_id",
    f_parent: "content_parent",
    f_content: "content_content",
    f_title: "content_title",
    f_lang: "content_lang",
    loadForm: function() {
        if ($("#swTermForm").html() == '') {
            $("#swTermForm").load("formTice/tice_term.html", null, function() {
                new jj('#tice_term_start_date').jjCalendarWithYearSelector(1380, 1400);
                new jj('#tice_term_mid_date').jjCalendarWithYearSelector(1380, 1400);
                new jj('#tice_term_end_date').jjCalendarWithYearSelector(1380, 1400);
                $("#cancel_Term").button().click(function(e) {
                    tice_term.m_clean();
                    tice_term.m_show_tbl();
                });

                $('#tice_term_sms').keyup(function() {
                    $('#tice_term_sms_lengh').html(new jj('#tice_term_sms').jjGetLength() + "/70");
                });
            });
            $("#swTermForm").hide();
            this.m_refresh();
        }

    },
    m_refresh: function(containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + tice_term.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swTermTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        tice_term.tabSizeTbl();
    },
    m_show_form: function() {
        $('#swTermTbl').hide();
        $('#swTermForm').show();
        tice_term.tabSizeForm();
    },
    m_clean: function() {
        new jj("#swTermForm").jjFormClean();

    },
    m_show_tbl: function() {
        $('#swTermTbl').show();
        $('#swTermForm').hide();
        tice_term.m_refresh();
        tice_term.tabSizeTbl();
        //        $('#refreshTerm').dataTable().fnSort( [ [0,'desc'] ] );
    },
    m_add_new: function() {
        jj("do=" + tice_term.tableName + ".add_new").jjAjax2(false);
        tice_term.m_show_form();
        tice_term.m_clean();
        $('#tice_term_Status_div').hide();
        //        oEditor.execCommand( 'bold');

        $('#swTermAll').css('height', 200);
        tice_term.heightTab = 200;
        $('#tice_term_sms_lengh').html("");
    },
    m_insert: function() {
        var valid = tice_term.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + tice_term.tableName + ".insert";
            param += "&" + new jj('#swTermForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_term.m_show_tbl();
            tice_term.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_edit: function() {
        var valid = tice_term.m_validation();
        if (valid == "") {
            var param = "";
            param += "do=" + tice_term.tableName + ".edit";
            param += "&" + new jj('#swTermForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_term.m_show_tbl();
            tice_term.m_clean();
        } else {
            new jj(valid).jjDialog();
        }
    },
    m_validation: function() {// mohamdad
        if (new jj("#content_title").jjVal().length < 1) {
            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
        }
        return "";
    },
    m_delete: function(id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_term.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function(id) {
        var param = "";
        param += "do=" + tice_term.tableName + ".delete";
        param += "&" + tice_term.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        tice_term.m_show_tbl();
        tice_term.m_clean();
    },
    m_select: function(id) {
        var param = "";
        param += "do=" + tice_term.tableName + ".select";
        param += "&" + tice_term.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        tice_term.m_show_form();
        $('#tice_term_Status_div').show();
        $('#tice_term_sms_lengh').html("");
    },
    tabSizeTbl: function() {
        $('#swTermAll').css('height', 520);
        tice_term.heightTab = 520;
    },
    tabSizeForm: function() {
        $('#swTermAll').css('height', 508);
        tice_term.heightTab = 508;
    },
    heightTab: "520",
    mainTabSetSize: function() {
        $('#swTermAll').css('height', tice_term.heightTab);
    },
    getTermList: function(selectPanel) {
        var param = "";
        param += "do=" + tice_term.tableName + ".getTermList";
        param += "&panel=" + selectPanel;
        jj(param).jjAjax2(false);
    },
    jjTableSearchInClass: function(termId) {
        var param = "";
        param += "do=" + tice_term.tableName + ".getTermForShowInClass";
        param += "&id=" + termId;
        jj(param).jjAjax2(false);
        $("#tabs").tabs({
            selected: 2
        });
    }
}
