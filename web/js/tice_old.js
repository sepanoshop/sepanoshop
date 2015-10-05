function rezerv(ClassName) {
    ClassName = ClassName == null ? '' : ClassName;
    $("#sw").html("<div id='pRezervDiv'></div>");
    $("#pRezervDiv").load("formCms/public_rezerv.html", null, function() {
        //        $('#comment_age').focus();
        $('#insert_Rezerv').button().click(function() {
            if (!new jj(new jj("#comment_full_name").jjVal()).length < 1) {
                new jj('لطفا نام خود را وارد کنید').jjDialog();
            } else if (!new jj(new jj("#comment_age").jjVal()).jjIsDigit()) {
                new jj('لطفا سن خود را وارد کنید').jjDialog();
            } else {
                var param = '';
                param += "do=Comment.insert&";
                param += "comment_full_name=" + new jj("#comment_sex").jjVal() + " "
                + new jj("#comment_full_name").jjVal() + " "
                + new jj("#comment_age").jjVal() + " ساله " + "&";
                param += "comment_tell=" + new jj("#comment_tell").jjVal() + "&";
                param += "comment_email=" + new jj("#comment_email").jjVal() + "&";
                param += "comment_title=" + new jj("#comment_title").jjVal() + "&";
                param += "comment_text=" + new jj("#comment_exam").jjVal() + " , " + new jj("#comment_text").jjVal() + "&";
                jj(param).jjAjax2(false, 'Server');
                sw(setting_comment_afted_send_text);
            }
        });
        if (USER_NAME != '') {
            new jj('#comment_full_name').jjVal(USER_NAME + ' ' + USER_FAMILY);
            new jj('#comment_email').jjVal(USER_EMAIL);
            $('#comment_full_name').attr('disabled', 'disabled');
            $('#comment_email').attr('disabled', 'disabled');
        //            $('#comment_tell').focus();
        } else {
        //            $('#comment_full_name').focus();
        }
        new jj('#comment_title').jjVal('رزرو ثبت نام' + (ClassName == '' ? "" : " در " + ClassName));
        $('#comment_title').attr('disabled', 'disabled');
        new jj('#comment_text').jjVal('');
    //        new jj('#comment_text').jjVal('لطفا در شروع به ثبت نام در '
    //            +(ClassName==''?"XXXXXX":ClassName)+
    //            ' به من اطلاع دهید.'
    //            );
    });
    $("#swTitle").html("رزرو");
}
function freeTime() {
    $("#sw").html("<div id='pfreeTimeDiv'></div>");
    $("#pfreeTimeDiv").load("formTice/public_freetime.html", null, function() {
        jj('do=Tice_report.getFreeTimeForTeacherForm&panel=fillFreeTimeLiClickInIndex&text=هم اکنون نیازی به پر نمودن فرم زمان آزاد توسط اساتید نمی باشد').jjAjax2(false, 'Server');

        //        $('#sss_4').focus();
        //        $('#sss_1').html('1');
        //        $('#sss_2').html('2');
        //        $('#sss_3').html('3');
        //        $('#sss_4').html('4');
        //        $('#sss_5').html('5');
        //        $('#sss_6').html('6');
        $('#insert_freetime').button().click(function() {
            var param = '';
            var sat = '';
            sat += (sat == '' ? 'sat: ' : ' and ') + (new jj("#s0_1").jjVal() == '1' ? $('#sss_1').html() : '');
            sat += (new jj("#s0_2").jjVal() == '1' ? ',' + $('#sss_2').html() : '');
            sat += (new jj("#s0_3").jjVal() == '1' ? ',' + $('#sss_3').html() : '');
            sat += (new jj("#s0_4").jjVal() == '1' ? ',' + $('#sss_4').html() : '');
            sat += (new jj("#s0_5").jjVal() == '1' ? ',' + $('#sss_5').html() : '');
            sat += (new jj("#s0_6").jjVal() == '1' ? ',' + $('#sss_6').html() : '');

            var sun = '';
            sun += (sun == '' ? 'sun: ' : ' and ') + (new jj("#s1_1").jjVal() == '1' ? $('#sss_1').html() : '');
            sun += (new jj("#s1_2").jjVal() == '1' ? ',' + $('#sss_2').html() : '');
            sun += (new jj("#s1_3").jjVal() == '1' ? ',' + $('#sss_3').html() : '');
            sun += (new jj("#s1_4").jjVal() == '1' ? ',' + $('#sss_4').html() : '');
            sun += (new jj("#s1_5").jjVal() == '1' ? ',' + $('#sss_5').html() : '');
            sun += (new jj("#s1_6").jjVal() == '1' ? ',' + $('#sss_6').html() : '');

            var mon = '';
            mon += (mon == '' ? 'mon: ' : ' and ') + (new jj("#s2_1").jjVal() == '1' ? $('#sss_1').html() : '');
            mon += (new jj("#s2_2").jjVal() == '1' ? ',' + $('#sss_2').html() : '');
            mon += (new jj("#s2_3").jjVal() == '1' ? ',' + $('#sss_3').html() : '');
            mon += (new jj("#s2_4").jjVal() == '1' ? ',' + $('#sss_4').html() : '');
            mon += (new jj("#s2_5").jjVal() == '1' ? ',' + $('#sss_5').html() : '');
            mon += (new jj("#s2_6").jjVal() == '1' ? ',' + $('#sss_6').html() : '');

            var tue = '';
            tue += (tue == '' ? 'tue: ' : ' and ') + (new jj("#s3_1").jjVal() == '1' ? $('#sss_1').html() : '');
            tue += (new jj("#s3_2").jjVal() == '1' ? ',' + $('#sss_2').html() : '');
            tue += (new jj("#s3_3").jjVal() == '1' ? ',' + $('#sss_3').html() : '');
            tue += (new jj("#s3_4").jjVal() == '1' ? ',' + $('#sss_4').html() : '');
            tue += (new jj("#s3_5").jjVal() == '1' ? ',' + $('#sss_5').html() : '');
            tue += (new jj("#s3_6").jjVal() == '1' ? ',' + $('#sss_6').html() : '');

            var wed = '';
            wed += (wed == '' ? 'wed: ' : ' and ') + (new jj("#s4_1").jjVal() == '1' ? $('#sss_1').html() : '');
            wed += (new jj("#s4_2").jjVal() == '1' ? ',' + $('#sss_2').html() : '');
            wed += (new jj("#s4_3").jjVal() == '1' ? ',' + $('#sss_3').html() : '');
            wed += (new jj("#s4_4").jjVal() == '1' ? ',' + $('#sss_4').html() : '');
            wed += (new jj("#s4_5").jjVal() == '1' ? ',' + $('#sss_5').html() : '');
            wed += (new jj("#s4_6").jjVal() == '1' ? ',' + $('#sss_6').html() : '');

            var thur = '';
            thur += (thur == '' ? 'thur: ' : ' and ') + (new jj("#s5_1").jjVal() == '1' ? $('#sss_1').html() : '');
            thur += (new jj("#s5_2").jjVal() == '1' ? ',' + $('#sss_2').html() : '');
            thur += (new jj("#s5_3").jjVal() == '1' ? ',' + $('#sss_3').html() : '');
            thur += (new jj("#s5_4").jjVal() == '1' ? ',' + $('#sss_4').html() : '');
            thur += (new jj("#s5_5").jjVal() == '1' ? ',' + $('#sss_5').html() : '');
            thur += (new jj("#s5_6").jjVal() == '1' ? ',' + $('#sss_6').html() : '');

            var fri = '';
            fri += (fri == '' ? 'fri: ' : ' and ') + (new jj("#s6_1").jjVal() == '1' ? $('#sss_1').html() : '');
            fri += (new jj("#s6_2").jjVal() == '1' ? ',' + $('#sss_2').html() : '');
            fri += (new jj("#s6_3").jjVal() == '1' ? ',' + $('#sss_3').html() : '');
            fri += (new jj("#s6_4").jjVal() == '1' ? ',' + $('#sss_4').html() : '');
            fri += (new jj("#s6_5").jjVal() == '1' ? ',' + $('#sss_5').html() : '');
            fri += (new jj("#s6_6").jjVal() == '1' ? ',' + $('#sss_6').html() : '');

            var param2 = (sat + '<br/>' + sun + '<br/>' + mon + '<br/>' + tue + '<br/>' + wed + '<br/>' + thur + '<br/>' + fri + '<br/>' + (new jj('#freetomeCommentFiled').jjVal()) + '<br/>');
            while (param2.indexOf('and and') > -1) {
                param2 = param2.replace('and and', '');
            }
            param += "do=Comment.insert&";
            param += "comment_full_name=" + USER_NAME + " " + USER_FAMILY + "&";
            param += "comment_tell=&";
            param += "comment_email=" + USER_EMAIL + "&";
            param += "comment_title=وقت آزاد" + " " + $('#sss_7').html() + "&";
            param += "comment_text=" + param2.replace('and and', '');
            jj(param).jjAjax2(false, 'Server');
            sw(setting_comment_afted_send_text);
        });
    });
}
function eteraz(eterazid) {
    eterazid = eterazid == null ? '' : eterazid;
    $("#sw").html("<div id='pEterazDiv'></div>");
    $("#pEterazDiv").load("formTice/public_eteraz.html", null, function() {
        $('#comment_text').focus();
        new jj("#id").jjVal(eterazid);
        $('#insert_Eteraz').button().click(function() {
            var param = '';
            param += "do=Tice_workbook.sendEterz&";
            param += "id=" + new jj("#id").jjVal() + "&";
            param += "comment_text=" + new jj("#comment_text").jjVal() + "&";
            new jj(param).jjAjax();
            sw(setting_comment_afted_send_text);
        });
    });
}

function TiceEnrolmentPerv() {
    var a = 'استخدام استاد';
    var b = 'استخدام کارمند';
    $("#sw").html("<br/><a onclick='TiceEnrolment();';>" + a + "</a><br/><br/><a onclick='sw(\"$enrolment\");'>" + b + "</a><br/><br/><br/>");
}
function TiceEnrolment() {
    $("#sw").html("<div id='enrolmentDiv2'></div>");
    $("#enrolmentDiv2").load("formCms/public_enrolment2.html", null, function() {
        $("#enrolmentDiv2").before('<div style="direction:ltr">Dear Madam / Sir,<br/>Please read and fill in the following form , meticulously. Your information  here is  supposed to  be correct as  it will  be used  as  the   base  for  being  chosen   as  a teacher. You will be informed about the time of the written exam and interview.</div><br/>');
        new jj('#enrolment3_birthdate').jjCalendarWithYearSelector(1340, 1390);
        new jj('#enrolment3_starting_date').jjCalendarWithYearSelector(1340, 1400);
        new jj('#enrolment3_end_date').jjCalendarWithYearSelector(1340, 1400);
        new jj('#enrolment3_cv').jjSetMaxLength(250);
        $('#insert_enrolmentTice').button().click(function() {
            if (new jj('#enrolment3_name').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('.' + 'لطفا نام خود را وارد کنید');
            } else if (new jj('#enrolment3_name').jjVal().length > 254) {
                $('#errorMessagePublicEnrolment').html('طول فیلد نام بیش از حد طولانی می باشد');
            } else if (new jj('#enrolment3_tell').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا شماره تلفن خود را وارد کنید');
            } else if (new jj('#enrolment3_tell').jjVal().length > 99) {
                $('#errorMessagePublicEnrolment').html('طول فیلد تلفن بیش از حد طولانی می باشد');
            } else if (new jj('#enrolment3_mobile').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا شماره موبایل خود را وارد کنید');
            } else if (new jj('#enrolment3_mobile').jjVal().length > 99) {
                $('#errorMessagePublicEnrolment').html('طول فیلد موبایل بیش از حد طولانی می باشد');
            } else if (new jj('#enrolment3_birthdate').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا سن خود را وارد کنید');
            } else if (new jj('#enrolment3_major').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا رشته تحصیلی خود را وارد کنید');
            } else if (new jj('#enrolment3_major').jjVal().length > 255) {
                $('#errorMessagePublicEnrolment').html('طول فیلد رشته تحصیلی بیش از حد طولانی می باشد');
            } else if (new jj('#enrolment3_university').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا نام دانشگاه را وارد کنید');
            } else if (new jj('#enrolment3_university').jjVal().length > 255) {
                $('#errorMessagePublicEnrolment').html('طول فیلد نام دانشگاه بیش از حد طولانی می باشد');
            } else if (new jj('#enrolment3_starting_date').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا تاریخ شروع دانشگاه را وارد کنید');
            } else if (new jj('#enrolment3_end_date').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا تاریخ اتمام دانشگاه را وارد کنید');
            } else if (new jj('#enrolment3_address').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا آدرس خود را وارد کنید');
            } else if (new jj('#enrolment3_pic').jjVal().length < 1) {
                $('#errorMessagePublicEnrolment').html('لطفا تصویر خود را وارد کنید');
            } else if (new jj('#enrolment3_speaking').jjVal() == '--Select--') {
                $('#errorMessagePublicEnrolment').html('لطفا بخش توانایی خور را مشخص نمایید');
            } else if (new jj('#enrolment3_listening').jjVal() == '--Select--') {
                $('#errorMessagePublicEnrolment').html('لطفا بخش توانایی خور را مشخص نمایید');
            } else if (new jj('#enrolment3_writing').jjVal() == '--Select--') {
                $('#errorMessagePublicEnrolment').html('لطفا بخش توانایی خور را مشخص نمایید');
            } else if (new jj('#enrolment3_reading').jjVal() == '--Select--') {
                $('#errorMessagePublicEnrolment').html('لطفا بخش توانایی خور را مشخص نمایید');
            } else {
                var favoriteGroup = "";
                favoriteGroup += (new jj('#enrolment3_favorite_group1').jjVal() == 1 ? 'Kids (4-10)' : '');
                favoriteGroup += (favoriteGroup == '' ? '' : ', ') + (new jj('#enrolment3_favorite_group2').jjVal() == 1 ? 'Teenagers(11-16)' : '');
                favoriteGroup += (favoriteGroup == '' ? '' : ', ') + (new jj('#enrolment3_favorite_group3').jjVal() == 1 ? 'Adults  ( ≥ 16 )' : '');
                jj("do=Enrolment3.insert&" + new jj("#swEnrolmentFormPublic2").jjSerial() + "&enrolment3_favorite_group=" + favoriteGroup).jjAjax2(false, 'Server');
                sw(setting_comment_afted_send_text);
            }
        });

        // -------------- pic
        $('#enrol_url_file').button().click(function() {
            });
        $('#upload_Enrol').button().click(function() {
            if (new jj('#enrol_url_file').jjVal() != '') {
                $('#upload_Enrol').hide();
                $('#enrol_url_file').hide();
                $('#enrolment3_pic').show()
            }
        });
        $('#enrolment3_pic').button().click(function() {
            $('#upload_Enrol').show();
            $('#enrol_url_file').show();
            $('#enrolment3_pic').hide()
        });
        new jj('#upload_Enrol').jjAjaxFileUpload('#enrol_url_file', '#enrolment3_pic', '#enrol_url_pic_demo', 307200);

        // -------------- file 1
        $('#enrol_url_file2').button().click(function() {
            });
        $('#upload_Enrol2').button().click(function() {
            if (new jj('#enrol_url_file2').jjVal() != '') {
                $('#upload_Enrol2').hide();
                $('#enrol_url_file2').hide();
                $('#enrolment3_file1').show()
            }
        });
        $('#enrolment3_file1').button().click(function() {
            $('#upload_Enrol2').show();
            $('#enrol_url_file2').show();
            $('#enrolment3_file1').hide()
        });
        new jj('#upload_Enrol2').jjAjaxFileUpload2('#enrol_url_file2', '#enrolment3_file1');

        // -------------- file 2
        $('#enrol_url_file3').button().click(function() {
            });
        $('#upload_Enrol3').button().click(function() {
            if (new jj('#enrol_url_file3').jjVal() != '') {
                $('#upload_Enrol3').hide();
                $('#enrol_url_file3').hide();
                $('#enrolment3_file2').show()
            }
        });
        $('#enrolment3_file2').button().click(function() {
            $('#upload_Enrol3').show();
            $('#enrol_url_file3').show();
            $('#enrolment3_file2').hide()
        });
        new jj('#upload_Enrol3').jjAjaxFileUpload2('#enrol_url_file3', '#enrolment3_file2');

    });
    $("#swTitle").html("فرم استخدام");
    return false;

}
function StudentIdCard(studentId, enrolmentId) {
    jj('Print?form=StudentIdCard&stId=' + studentId + '&enId=' + enrolmentId).jjGoNewPage();
}
function goToSmsPage() {
    jj('print/sms_tice2.html').jjGoNewPage();
}
function printAttendance(classId) {
    jj('Print?form=Attendance&id=' + classId).jjGoNewPage();
}
function printAttendance2(classId) {
    jj('Print?form=Attendance2&id=' + classId).jjGoNewPage();
}
function printClassActivity(classId) {
    jj('Print?form=ClassActivity&id=' + classId).jjGoNewPage();
}
function printScoreList(classId) {
    jj('Print?form=ScoreList&id=' + classId).jjGoNewPage();
}
function printStudentIdCard(stdId, enrolId) {
    jj('Print?form=StudentIdCard&stId=' + stdId + "&enId=" + enrolId).jjGoNewPage();
}
function printStKarname(stdId, classId) {
    jj('Print?form=StKarname&stId=' + stdId + "&clId=" + classId).jjGoNewPage();
}
function printStKarnameWithBackground(stdId, classId) {
    jj('Print?form=StKarname&stId=' + stdId + "&clId=" + classId + "&back=2").jjGoNewPage();
}
function printClassInTerm(termId) {
    jj('Print?form=ClassesInTerm&id=' + termId).jjGoNewPage();
}
function printClassInTerm2(termId) {
    jj('Print?form=ClassesInTerm2&id=' + termId).jjGoNewPage();
}
function printTeacherClassInTerm(termId, teacherId) {
    jj('Print?form=TeacherClassInTerm&termId=' + termId + '&teacherId=' + teacherId).jjGoNewPage();
}
function showFormTeacherForClassActivity(classId) {
    jj('do=Tice_teacher.showFormTeacherForClassActivity&id=' + classId).jjAjax2();
}
function showStudentClassActivity(studentId, classId) {
    jj('do=Tice_student.showStudentClassActivity&studentId=' + studentId + "&classId=" + classId).jjAjax2();
}
function serialClassActivity() {
    if (new jj('#dateInClassActivityForTeacher').jjVal().length == 0) {
        jj('ابتدا تاریخ جلسه مورد نظر خود را وارد نمایید').jjDialog();

    } else {
        jj('do=Tice_teacher.saveFormTeacherForClassActivityInComment&' + jj('#showFormTeacherForClassActivity_tbl').jjSerial() + "&name=" + USER_NAME + " " + USER_FAMILY).jjAjax2();
    }
}
function printTeacherSalaryInTerm(termId) {
    jj('Print?form=TeacherSalaryInTerm&id=' + termId).jjGoNewPage();
}
function printFish(fishId) {
    jj('Print?form=TeacherSlary&id=' + fishId).jjGoNewPage();
}
function printTeacherIdCard(teacherId) {
    jj('Print?form=TeacherIdCard&id=' + teacherId).jjGoNewPage();
}
function printAllRezerv() {
    jj('Print?form=printAllRezerv').jjGoNewPage();
}
function printAllFreeTime() {
    jj('Print?form=printAllFreeTime').jjGoNewPage();
}
function printAllEteraz() {
    jj('Print?form=printAllEteraz').jjGoNewPage();
}
function printAllEstekhdam() {
    jj('Print?form=printAllEstekhdam').jjGoNewPage();
}
function printBedehkaran(termId) {
    jj('Print?form=bedehkar&term=' + termId + '&getWhat=bedehkar').jjGoNewPage();
}
function printregistNoTermChart() {
    jj('Print?form=registNoTermChart').jjGoNewPage();
}
function printregistNoTermPriceChart() {
    jj('Print?form=registNoTermPriceChart').jjGoNewPage();
}
function printregistNoLevelChart() {
    jj('Print?form=registNoLevelChart').jjGoNewPage();
}
function printEconomicReport(){
    jj('Print?form=EconomicReport').jjGoNewPage();
}
function printLoyaltyReport(){
    jj('Print?form=LoyaltyReport').jjGoNewPage();
}


function signOutTice() {
    USER_NAME = "";
    USER_FAMILY = "";
    USER_EMAIL = "";
    USER_PASS = "";
    new jj("do=Access_User.signOutTice").jjAjax2(true);
    new jj('#jjLoginExitPanel').jjVal('ورود به سامانه');
    $("#studentPanel").hide();
    $("#teacherPanel").hide();
    $("#downloadPanel").hide();
    sw(setting_default_sw);
}

function showLoginFormTice() {
    if (USER_NAME != '') {
        new jj('کاربر محترم ' + USER_NAME + "&nbsp;" + USER_FAMILY + " آیا مایلید از سیستم خارج شوید؟").jjDialog_YesNo('signOutTice();', true, "خروج");
        return false;
    }
    if ($('#pshowLoginFormDiv').length == 0) {
        $("body").append("<div id='pshowLoginFormDiv'></div>");
        $("#pshowLoginFormDiv").load("formCms/public_only_login.html", null, function() {

            $('#loginBtn').button().click(function() {
                signInTice();
            });
            $('#loginBtn2').button().click(function() {
                signInTice();
            });
            jj("#user_pass1").jjAddEnterKeyListener("signInTice();");
            jj("#user_email1").jjAddEnterKeyListener("signInTice();");
            //            jj("#user_answer").jjAddEnterKeyListener("registInSite();");
            //            jj("#user_birthdate").jjCalendarWithYearSelector(1320, 1380);
            $("#loginRegistForm").dialog({
                autoOpen: false,
                height: 210,
                width: 490,
                modal: true,
                title: "ورود به سامانه دانشجویان و اساتید",
                buttons: {
                    "لغو": function() {
                        $(this).dialog("close");
                    }
                },
                close: function() {
                    $(this).dialog('destroy');
                }
            });
            $("#loginRegistForm").dialog("open");
            return false;
        });
    }
    $("#loginMessagePanel").html("");

    $("#loginRegistForm").dialog({
        autoOpen: false,
        height: 210,
        width: 490,
        modal: true,
        title: "ورود به سامانه دانشجویان و اساتید",
        buttons: {
            "لغو": function() {
                $(this).dialog("close");
            }
        },
        close: function() {
            $(this).dialog('destroy');
        }
    });
    $("#loginRegistForm").dialog("open");
}

function signInTice() {
    if (new jj('#user_email1').jjVal() == '') {
        $("#loginMessagePanel").html("نام یا کد دانشجویی نباید تهی باشد.");
        return false;
    }
    if (new jj('#user_pass1').jjVal() == '') {
        $("#loginMessagePanel").html("نام یا کد دانشجویی نباید تهی باشد.");
        return false;
    }
    jj("do=Access_User.loginUserInPublicTice&panel=" + setting_login_exit_panel.replace("#", "") + "&" + new jj("#loginForm").jjSerial()).jjAjax2(false);
}
function TicePublicStudentShowMyClass() {
    jj("do=Tice_student.TicePublicStudentShowMyClass&panel=sw&id=" + USER_ID).jjAjax2(false);
}
function TicePublicStudentShowMyKarname() {
    jj("do=Tice_student.TicePublicStudentShowMyKarname&panel=sw&id=" + USER_ID).jjAjax2(false);
}
function TicePublicStudentShowMyWeekProgram() {
    jj("do=Tice_student.TicePublicStudentShowMyWeekProgram&panel=sw&id=" + USER_ID).jjAjax2(false);
}
function TicePublicStudentShowForum() {
    jj("do=Tice_student.TicePublicStudentShowForum&panel=sw&id=" + USER_ID).jjAjax2(false);
}
function TicePublicTeacherShowForum() {
    jj("do=Tice_teacher.TicePublicTeacherShowForum&panel=sw&id=" + USER_ID).jjAjax2(false);
}
function TicePublicTeacherShowData() {
    $("#sw").html("<div id='pCommentDiv123'></div>");
    $("#pCommentDiv123").load("formTice/public_ticeDataTeacher.html", null, function() {
        new jj("#ticeTeachers_BirthDate").jjCalendarWithYearSelector(1320, 1380);
        jj("do=Tice_teacher.select&id=" + USER_ID).jjAjax2(false);

        $('#insert_publicEditTeacher').button().click(function() {
            if (new jj('#ticeTeacher_Password1').jjVal() == '') {
                new jj('لطفا جهت اعمال تغییرات مجددا فیلد رمز فعلی را به درستی پر نمایید.').jjDialog();
                return false;
            }
            if (new jj('#ticeTeacher_Password2').jjVal() != '') {
                if (new jj('#ticeTeacher_Password2').jjVal().length < 3) {
                    new jj('درصورت نیاز به تغییر رمز جدید شما باید حداقل سه کاراکتر وارد نمایید').jjDialog();
                    return false;
                }
            }
            if (new jj('#ticeTeacher_Password2').jjVal() != new jj('#ticeTeacher_Password3').jjVal()) {
                new jj('رمز جدید به درستی با تکرار آن همخانی ندارد.').jjDialog();
                return false;
            }
            jj("do=Tice_teacher.publicEdit&id=" + USER_ID + "&" + new jj("#swPublicTecherEditDataForm").jjSerial()).jjAjax2();
            sw(setting_comment_afted_send_text);
        });
    });
}
function TicePublicStudentShowData() {
    $("#sw").html("<div id='pCommentDiv123'></div>");
    $("#pCommentDiv123").load("formTice/public_ticeDataStudent.html", null, function() {
        new jj("#ticeStudents_Birthdate").jjCalendarWithYearSelector(1320, 1380);
        jj("do=Tice_student.select&id=" + USER_ID).jjAjax2(false);

        $('#insert_publicEditStudent').button().click(function() {
            if (new jj('#ticeStudents_Password1').jjVal() == '') {
                new jj('لطفا جهت اعمال تغییرات مجددا فیلد رمز فعلی را به درستی پر نمایید.').jjDialog();
                return false;
            }
            if (new jj('#ticeStudents_Password2').jjVal() != '') {
                if (new jj('#ticeStudents_Password2').jjVal().length < 3) {
                    new jj('درصورت نیاز به تغییر رمز جدید شما باید حداقل سه کاراکتر وارد نمایید').jjDialog();
                    return false;
                }
            }
            if (new jj('#ticeStudents_Password2').jjVal() != new jj('#ticeStudents_Password3').jjVal()) {
                new jj('رمز جدید به درستی با تکرار آن همخانی ندارد.').jjDialog();
                return false;
            }
            jj("do=Tice_student.publicEdit&id=" + USER_ID + "&" + new jj("#swPublicStudentEditDataForm").jjSerial()).jjAjax2();
            sw(setting_comment_afted_send_text);
        });
    });
}
function TicePublicNoTeacherNoStudentShowForum() {
    new jj("do=Tice_student.TicePublicNoTeacherNoStudentShowForum&panel=sw").jjAjax2(true);
}
function ticeForumStudent(forumId) {
    if (jj(forumId).jjIsDigit()) {
        new jj("do=Tice_student.ticeForumStudent&id=" + forumId.toString() + "&panel=sw").jjAjax2(true);
    }
}
;
function ticeForum(forumId) {
    if (jj(forumId).jjIsDigit()) {
        new jj("do=Tice_student.ticeForum&id=" + forumId.toString() + "&panel=sw").jjAjax2(true);
    }
}
;
function ticeForumTeacher(forumId) {
    if (jj(forumId).jjIsDigit()) {
        new jj("do=Tice_teacher.ticeForum&id=" + forumId.toString() + "&panel=sw").jjAjax2(true);
    }
}
;

function TicePublicTeacherShowMyFish() {
    jj("do=Tice_teacher.TicePublicTeacherShowMyFish&panel=sw&id=" + USER_ID).jjAjax2(false);
}
function TicePublicTeacherShowMyWeekProgram() {
    jj("do=Tice_teacher.TicePublicTeacherShowMyWeekProgram&panel=sw&id=" + USER_ID).jjAjax2(false);
}
function voteToPoll(pollId, whichOneRecord) {
    //    alert(pollId+' - '+whichOneRecord);
    if (USER_NAME == '') {
        showLoginFormTice();
        return false;
    } else {
        //        $("#poll_id_is_"+ pollId).hide();
        new jj("do=Tice_poll.voteToPoll&pollId=" + pollId + "&whichRecord=" + whichOneRecord + "&id=" + USER_ID).jjAjax2(true);
    }
}
function sendEmailStudent() {
    new jj("do=Tice_student.SendEmail&email=" + (new jj("#ticeStudents_EmailAddress").jjVal()) + "&text=" + (new jj("#ticeStudents_sms").jjVal())).jjAjax2(true);
}
function sendSmsStudent() {
    new jj("do=Tice_student.SendSms&no1=" + (new jj("#ticeStudents_MobilePhone").jjVal()) + "&no2=" + (new jj("#ticeStudents_HomePhone").jjVal()) + "&text=" + (new jj("#ticeStudents_sms").jjVal())).jjAjax2(true);
}
function sendSmsTeacher() {
    new jj("do=Tice_student.SendSms&no1=" + (new jj("#ticeTeachers_MobilePhone").jjVal()) + "&no2=" + (new jj("#ticeTeachers_HomePhone").jjVal()) + "&text=" + (new jj("#ticeTeachers_Extra2").jjVal())).jjAjax2(true);
}
function sendEmailTeacher() {
    new jj("do=Tice_teacher.SendEmail&email=" + (new jj("#ticeTeachers_Extra1").jjVal()) + "&text=" + (new jj("#ticeTeachers_Extra2").jjVal())).jjAjax2(true);
}
function sendEmailClass() {
    new jj("do=Tice_class.SendEmail&id=" + (new jj("#ticeClass_id").jjVal()) + "&text=" + (new jj("#ticeClass_sms").jjVal())).jjAjax2(true);
}
function sendSmsClass() {
    new jj("do=Tice_class.sendSms&id=" + (new jj("#ticeClass_id").jjVal()) + "&text=" + (new jj("#ticeClass_sms").jjVal())).jjAjax2(true);
}
function sendEmailTerm(id) {
    new jj("do=Tice_term.SendEmail&id=" + id + "&text=" + (new jj("#tice_term_sms").jjVal())).jjAjax2(true);
}
function sendEmailTermTeacher(id) {
    new jj("do=Tice_term.sendEmailTermTeacher&id=" + id + "&text=" + (new jj("#tice_term_sms").jjVal())).jjAjax2(true);
}
function sendSmsTerm(id) {
    new jj("do=Tice_term.SendSms&id=" + id + "&text=" + (new jj("#tice_term_sms").jjVal())).jjAjax2(true);
}
function sendSmsTermTeacher(id) {
    new jj("do=Tice_term.sendSmsTermTeacher&id=" + id + "&text=" + (new jj("#tice_term_sms").jjVal())).jjAjax2(true);
}
function sendSmsToAll() {
    new jj("do=Tice_term.sendSmsToAll&text=" + (new jj("#tice_term_sms").jjVal())).jjAjax2(true);
}
function newEnrolFromStudentTbl(id) {
    new jj("do=Tice_enrol.add_new").jjAjax2(true);
}
function sabteEmkanatForAll() {
    var id = new jj('#ticeClass_id').jjVal();
    if(!new jj(id).jjIsDigit()){
        new jj('کد کلاس یک عدد نمی باشد.').jjDialog();
        return false;
    }
    new jj('آیا مایلید که امکان ثبت نام، اعتراض به کلاس ها و ثبت فعالیت کلاسی جاری برای همه کلاس هایی که در ترم این کلاس هستند به صورت فعلی این فرم تنظیم شوند؟').jjDialog_YesNo('sabteEmkanatForAll2();');
}
function sabteEmkanatForAll2() {
    var ticeClass_registerLock = new jj('#ticeClass_registerLock').jjVal();
    var ticeClass_ProtestPermision = new jj('#ticeClass_ProtestPermision').jjVal();
    var ticeClass_insert_activity_permision = new jj('#ticeClass_insert_activity_permision').jjVal();
    var id = new jj('#ticeClass_id').jjVal();
    new jj("do=Tice_class.sabteEmkanatForAll&ticeClass_registerLock="+ticeClass_registerLock+"&ticeClass_ProtestPermision="
        +ticeClass_ProtestPermision+"&ticeClass_insert_activity_permision="+ticeClass_insert_activity_permision+"&id="+id).jjAjax2(true);
}