var tice_class_regist = {
    tableName: "Tice_enrol",
    f_id: "id",
    f_content_id: "content_id",
    f_parent: "content_parent",
    f_content: "content_content",
    f_title: "content_title",
    f_lang: "content_lang",
    loadForm: function () {
        if ($("#swClassRegistForm").html() == '') {
            $("#swClassRegistForm").load("formTice/class_regist.html", null, function () {
                $("#cancel_Enrol").button().click(function () {
                    tice_class_regist.m_show_tbl();
                    tice_class_regist.m_clean();
                });

                //jj("#student_week_program_id_search").jjSetTextFieldOnlyGetNumber();
                new jj('#ticeEnrol_PaymentDate1').jjCalendarWithYearSelector(1387, 1410);
                new jj('#ticeEnrol_PaymentDate2').jjCalendarWithYearSelector(1387, 1410);
                new jj('#ticeEnrol_PaymentDate3').jjCalendarWithYearSelector(1387, 1410);
                new jj('#ticeEnrol_EnrolDate').jjCalendarWithYearSelector(1387, 1410);

                new jj('#ticeEnrol_Payment1').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeEnrol_Payment2').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeEnrol_Payment3').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeEnrol_OtherDebit').jjSetTextFieldOnlyGetNumber();
                new jj('#ticeEnrol_Discount').jjSetTextFieldOnlyGetNumber();
                $('#ticeEnrol_ReceiptNo1').keyup(function () {
                    jj("do=Tice_enrol.checkFishNo&text=" + new jj('#ticeEnrol_ReceiptNo1').jjVal() + "&panel=ticeEnrol_ReceiptNo1").jjAjax2(false);
                });
                $('#ticeEnrol_ReceiptNo2').keyup(function () {
                    jj("do=Tice_enrol.checkFishNo&text=" + new jj('#ticeEnrol_ReceiptNo2').jjVal() + "&panel=ticeEnrol_ReceiptNo2").jjAjax2(false);
                });
                $('#ticeEnrol_ReceiptNo3').keyup(function () {
                    jj("do=Tice_enrol.checkFishNo&text=" + new jj('#ticeEnrol_ReceiptNo3').jjVal() + "&panel=ticeEnrol_ReceiptNo3").jjAjax2(false);
                });
                $("#ticeEnrol_Payment1").keyup(function () {
                    calculateRegistFee();
                });
                $("#ticeEnrol_Payment2").keyup(function () {
                    calculateRegistFee();
                });
                $("#ticeEnrol_Payment3").keyup(function () {
                    calculateRegistFee();
                });
                $("#ticeEnrol_OtherDebit").keyup(function () {
                    calculateRegistFee();
                });
                $("#ticeEnrol_Discount").keyup(function () {
                    calculateRegistFee();
                });

                manageDropDown.getListTermForRegist();
                manageDropDown.getListClassForRegist();
                $("#ticeEnrol_TermId").change(function () {
                    manageDropDown.getListClassForRegist();
                    //                    tice_class_list.getClassList("ticeEnrol_ClassId",$("#ticeEnrol_TermId").val());
                });
                tice_class_regist.getCount("numberOfStudentsInClass", $("#ticeEnrol_ClassId").val());
                $("#ticeEnrol_ClassId").change(function () {
                    tice_class_regist.getCount("numberOfStudentsInClass", $("#ticeEnrol_ClassId").val());
                });
                $("#ticeEnrol_ClassId").click(function () {
                    calculateRegistFee();
                });
                $("#ticeEnrol_TermId").click(function () {
                    calculateRegistFee();
                });
                tice_class_regist.m_refresh("swClassRegistTbl");
                $("#ticeEnrol_StudentId").button().click(function () {
                    if ($("#ticeEnrol_StudentId").val() == "") {
                        tice_student_list.m_show_tbl();
                        tice_student_list.m_show_tbl();//Add student when no student selected 
                    } else {
                        tice_student_list.m_show_form();
                        tice_student_list.m_select($("#ticeEnrol_StudentId").val());//Edit student who her/his std number is in 'ticeEnrol_StudentId' textbox
                        $("#tabs").tabs({
                            selected: 0
                        });
                    }
                });
            });
            $("#swClassRegistForm").hide();
        }
    },
    m_refresh: function (containerId, sortField, tableHeight) {// بصورت پیشفرض سرور در پاسخ ترم های آخر را نشان می دهد
        var param = "";
//        param += "do="+this.tableName+".refreshLast";//بارگزاری جدلو بدون دیتاتیبل
        param += "do=" + this.tableName + ".refreshLast";//بارگزاری جدول با اسکریپت دیتا تیبل
        param += "&panel=" + (containerId == null ? "swClassRegistTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight);
        jj(param).jjAjax2(false);
        tice_class_regist.tabSizeTbl();
    },
    m_refresh_all: function (containerId, sortField, tableHeight) {// برگرداندن همه ثبت نام ها
        var param = "";
        param += "do=" + this.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swClassRegistTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight);
        jj(param).jjAjax2(false);
        tice_class_regist.tabSizeTbl();
    },
    m_show_form: function () {
        $('#swClassRegistTbl').hide();
        $('#swClassRegistForm').show();
        tice_class_regist.tabSizeForm();
    },
    m_clean: function () {
        //        new jj("#swClassRegistForm").jjFormClean();
        $("#ticeEnrol_ClassId").val("");
        $("#ticeEnrol_TermId").val("");
        $("#numberOfStudentsInClass").html("");
    },
    m_add_new: function () {
        jj("do=" + tice_class_regist.tableName + ".add_new").jjAjax2(false);
        tice_class_regist.m_show_form();
        //        if($('#ticeEnrol_ClassId').html()==''){
        //            tice_class_list.getClassList("ticeEnrol_ClassId",$("#ticeEnrol_TermId").val());
        //        }
        $("#errormessageshowForErrorEnrol").html('');
        new jj("#ticeEnrol_StudentId").jjVal("");
        new jj("#ticeEnrol_FirstName").jjVal("");
        new jj("#ticeEnrol_id").jjVal("");
        new jj("#ticeEnrol_LastName").jjVal("");
        new jj("#ticeEnrol_FatherName").jjVal("");
        new jj("#ticeEnrol_ReceiptNo1").jjVal("");
        new jj("#ticeEnrol_ReceiptNo2").jjVal("");
        new jj("#ticeEnrol_ReceiptNo3").jjVal("");
        new jj("#ticeEnrol_PaymentDate1").jjVal("");
        new jj("#ticeEnrol_PaymentDate2").jjVal("");
        new jj("#ticeEnrol_PaymentDate3").jjVal("");
        new jj("#ticeEnrol_ReceiptType1").jjVal("نقد");
        new jj("#ticeEnrol_ReceiptType2").jjVal("نقد");
        new jj("#ticeEnrol_ReceiptType3").jjVal("نقد");
        new jj("#ticeEnrol_OtherDebitComment").jjVal("");
        new jj("#ticeStudents_Comment2").jjVal("");
        new jj("#ticeEnrol_OtherDebit").jjVal("");
        new jj("#ticeEnrol_Payment1").jjVal("");
        new jj("#ticeEnrol_Payment2").jjVal("");
        new jj("#ticeEnrol_Payment3").jjVal("");
        new jj("#ticeEnrol_OtherDebit").jjVal("");
        new jj("#ticeEnrol_Discount").jjVal("");
        new jj("#ticeEnrol_Oprator").jjVal("");
        new jj("#ticeEnrol_EnrolDate").jjVal("");
        new jj("#ticeEnrol_ReceiptNo3").jjVal("");
        new jj("#ticeEnrol_Remaining").jjVal("0");
        //        }
    },
    m_show_tbl: function () {
        $('#swClassRegistTbl').show();
        $('#swClassRegistForm').hide();
        tice_class_regist.tabSizeTbl();
    },
    m_insert: function () {
        if (tice_class_regist.m_isValid()) {
            var param = "";
            param += "do=" + tice_class_regist.tableName + ".insert";
            param += "&" + new jj('#swClassRegistForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_class_regist.m_show_tbl();
            tice_class_regist.m_clean();
        }
    },
    m_isValid: function () {
        if (!new jj(new jj("#ticeEnrol_StudentId").jjVal()).jjIsDigit()) {
            $("#errormessageshowForErrorEnrol").html('لطفا یک دانش آموز را انتخاب کنید.');
            return result;
        }
        if (!new jj(new jj("#ticeEnrol_Remaining").jjVal()).jjIsDigit()) {
            if (confirm('.آیا مطمئن هستید مبالغ را صحیح وارد کرده اید؟ مبلغ منفی بمنزله ی اینست که دانشجو بستانکار است')) {
                return true;
            } else {
                return false;
            }
//            if ($('#ticeEnrol_RemainingDialog').length == 0) {
//                $("body").append("<div id='ticeEnrol_RemainingDialog'>مقدار منفی در باقیمانده به منزله بستانکار شدن دانشجو است</div>");
//            }
//            $("#ticeEnrol_RemainingDialog").dialog({
//                autoOpen: false,
//                height: 410,
//                width: 600,
//                modal: true,
//                title: "مقدار منفی در باقیمانده",
//                buttons: {
//                    "لغو": function () {
//                        $(this).dialog("close");
//                        alert(10);
//                    },
//                    "تایید": function () {
//                        $(this).dialog("close");
//                        alert(20);
//                    }
//                },
//                close: function () {
//                    $(this).dialog('destroy');
//                }
//            });
//            new jj("مقدار منفی یعنی دانشجو طلبکار شده است، آیا ادامه می دهید؟").jjDialog_YesNo('alert("OK");this.temp=1;return true;', false, "");
//            $("#errormessageshowForErrorEnrol").dialog()('مقدار باقیمانده نباید مقدار منفی باشد.');
        }
        return true ;
    },
    m_edit: function () {
        var b = tice_class_regist.m_isValid();
        if (b) {
            var param = "";
            param += "do=" + tice_class_regist.tableName + ".edit";
            param += "&" + new jj('#swClassRegistForm').jjSerial();
            jj(param).jjAjax2(false);
            tice_class_regist.m_show_tbl();
            tice_class_regist.m_clean();
        }else{
            alert("hoooooo");
        }
    },
    m_delete: function (id) {
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_class_regist.m_delete_after_question(' + id + ');\n', true, "");
    },
    m_delete_after_question: function (id) {
        var param = "";
        param += "do=" + tice_class_regist.tableName + ".delete";
        param += "&" + tice_class_regist.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        tice_class_regist.m_show_tbl();
        tice_class_regist.m_clean();
    },
    m_select: function (id) {
        $("#tabs").tabs({
            selected: 1
        });
        var param = "";
        param += "do=" + tice_class_regist.tableName + ".select";
        param += "&" + tice_class_regist.f_id + "=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
        tice_class_regist.m_show_form();
    },
    m_select_std: function (stdid) {//this function fill student details in enrol fileds
        var param = "";
        param += "do=" + tice_class_regist.tableName + ".select_std";
        param += "&" + tice_student_list.f_id + "=" + stdid;//f_id is 'id'
        jj(param).jjAjax2(false);
        tice_class_regist.m_show_form();
    },
    m_select_class: function (classid) {//this function fill student details in enrol fileds
        var param = "";
        param += "do=" + tice_class_regist.tableName + ".select_class";
        param += "&" + tice_class_list.f_id + "=" + classid;//f_id is 'id'
        jj(param).jjAjax2(false);
        tice_class_regist.m_show_form();
    },
    tabSizeTbl: function () {
        $('#swClassRegistAll').css('height', 520);
        tice_class_regist.heightTab = 520;
    },
    tabSizeForm: function () {
        $('#swClassRegistAll').css('height', 658);
        tice_class_regist.heightTab = 658;
    },
    heightTab: "520",
    mainTabSetSize: function () {
        $('#swClassRegistAll').css('height', tice_class_regist.heightTab);
    },
    m_search: function (containerId, sortField, tableHeight) {
        var param = "";
        param += "do=" + ticeStudent.tableName + ".refresh";
        param += "&panel=" + (containerId == null ? "swClassRegistTbl" : containerId);
        param += "&sort=" + (sortField == null ? "0" : sortField);
        param += "&height=" + (tableHeight == null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        ticeStudent.tabSizeTbl();
    },
    getCount: function (answerPanel, classId) {
        var param = "";
        param += "do=" + tice_class_regist.tableName + ".getCount";
        param += "&panel=" + (answerPanel == null ? "numberOfStudentsInClass" : answerPanel);
        param += "&classId=" + (classId == null ? "" : classId);
        jj(param).jjAjax2(false);
    }

}

function calculateRegistFee() {
    var shahrie = new jj("#ticeEnrol_TermPrice").jjVal();
    var intShahrie = 0;
    if (new jj(shahrie).jjIsDigit()) {
        intShahrie = new jj(shahrie).jjConvertToInt();
    }

    var payment1 = new jj("#ticeEnrol_Payment1").jjVal();
    var intPayment1 = 0;
    if (new jj(payment1).jjIsDigit()) {
        intPayment1 = new jj(payment1).jjConvertToInt();
    }

    var payment2 = new jj("#ticeEnrol_Payment2").jjVal();
    var intPayment2 = 0;
    if (new jj(payment2).jjIsDigit()) {
        intPayment2 = new jj(payment2).jjConvertToInt();
    }

    var payment3 = new jj("#ticeEnrol_Payment3").jjVal();
    var intPayment3 = 0;
    if (new jj(payment3).jjIsDigit()) {
        intPayment3 = new jj(payment3).jjConvertToInt();
    }

    var otherDebit = new jj("#ticeEnrol_OtherDebit").jjVal();
    var intOtherDebit = 0;
    if (new jj(otherDebit).jjIsDigit()) {
        intOtherDebit = new jj(otherDebit).jjConvertToInt();
    }

    var discount = new jj("#ticeEnrol_Discount").jjVal();
    var intDiscount = 0;
    if (new jj(discount).jjIsDigit()) {
        intDiscount = new jj(discount).jjConvertToInt();
    }
    var output = intShahrie - intPayment1 - intPayment2 - intPayment3 + intOtherDebit - intDiscount;
    new jj("#ticeEnrol_Remaining").jjVal(output);
}
