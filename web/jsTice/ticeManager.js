var innerPanelHeight = 393;
var PanelHeight = 435;
// ----------------------------------------------------------------------------
function loadFormsManagerTice() {

    $("#swLoginForm").load("formCms/login.html", null, function() {
        jj('#login_pass').jjAddEnterKeyListener('loginToTice();');
        new jj("#user_birthdate").jjCalendarWithYearSelector(1320, 1380);


        $("#loginBtn").button().click(function(e) {
            loginToTice();
        // =========================================================
        //            loginToCMS();
        //            alert((new jj("#login_email").jjVal()).toString().toLowerCase());
        //            if((new jj("#login_email").jjVal()).toString().toLowerCase()=="dr_hashemi_sm%40yahoo.com" && new jj("#login_pass").jjVal()=="10203040"){
        //                $('#LoginTab').hide();
        //                $('#StudentTab').show();
        //                $('#ClassRegist').show();
        //                $('#ClassTab').show();
        //                $('#TeacherTab').show();
        //                $('#TeacherSalaryTab').show();
        //                $('#TermTab').show();
        //                $('#RoomTab').show();
        //                $('#BookTab').show();
        //                $('#CompanyTab').show();
        //                tice_student_list.loadForm(); 
        //                $( "#tabs" ).tabs({
        //                    selected:0
        //                });
        //            }else{
        //                alert('Email or Password is not currenct.');
        //            }
        });
    // jj("#login_email").jjVal('behnam.khani@yahoo.com');
    // jj("#login_email").jjVal('mehrdad@gmail.com');
    //        jj("#login_email").jjVal('milad.jamalzadeh@yahoo.com');
    //        jj("#login_pass").jjVal('123456');
    //            loginToCMS();

    });
}
function tblMethod(tblCode) {
    if (tblCode == "100") {
        tice_student_list.m_add_new();
    } else if (tblCode == "600") {
        tice_student_list.m_refresh_all();
    } else if (tblCode == "101") {
        tice_class_regist.loadForm();
        //        tice_class_regist.m_add_new();        
        tice_class_regist.m_select_std($("#ticeStudents_id").val());
        //        tice_class_regist.m_select_std($("#ticeStudents_id").val());
        $("#tabs").tabs({
            selected: 1
        });
    } else if (tblCode == "102") {
        tice_term.m_add_new();
    } else if (tblCode == "110") {
        tice_class_regist.m_add_new();
        tice_class_regist.m_clean();
        $("#tabs").tabs({
            selected: 1
        });
    } else if (tblCode == "610") {
        tice_class_regist.m_refresh_all();
    } else if (tblCode == "130") {
        tice_teacher_list.m_add_new();
    } else if (tblCode == "103") {
        tice_teacher_list.m_add_new();
    } else if (tblCode == "104") {
        tice_teacher_list.m_add_new();
    } else if (tblCode == "120") {
        tice_class_list.m_add_new();
    } else if (tblCode == "620") {
        tice_class_list.m_refresh_all();
    } else if (tblCode == "107") {
        tice_level.m_add_new();
    } else if (tblCode == "21") {
        cmsTiceBook.m_add_new();
    } else if (tblCode == "22") {
        cmsTiceRoom.m_add_new();
    } else if (tblCode == "155") {
        tice_class_regist.m_add_new();
        tice_class_regist.m_select_class($("#ticeClass_id").val());
        $("#tabs").tabs({
            selected: 1
        });

    } else if (tblCode == "157") {
        tice_teacher_fish.m_add_new();
    //        tice_teacher_fish.m_select_class($("#ticeClass_id").val());
    } else if (tblCode == "158") { //newByMohammad **********************************
        $("#tabs").tabs({
            selected: 3
        });
        $("#swTeacherAll").tabs({
            selected: 1
        });
        tice_teacher_fish.m_clean();
        tice_teacher_fish.m_add_new();
    //newByMohammad **********************************
    }
}
function manageTabOnClickTice() {
//    $("#StudentTabA").click(function(e) {
//        tice_student_list.mainTabSetSize();        
//        if($('#PicStudent1').css('display')!='none'){
//            tice_student_list.m_show_tbl();
//            $( "#swStudentAll" ).tabs({
//                selected:0
//            });
//        } else if($('#StudentTab2').css('display')!='none'){
//            cmsCategoryGallery.m_show_tbl();
//            $( "#swStudentAll" ).tabs({
//                selected:1
//            });
//        };
//    });
//    $("#swBookTabA").click(function(e) {
//        cmsTiceBook.loadForm();
//        cmsTiceBook.m_refresh();
//    //        cmsTiceBook.m_show_form();
//    });
//    $("#swRoomTabA").click(function(e) {
//        cmsTiceRoom.loadForm();
//        cmsTiceRoom.m_refresh();
//    //        cmsTiceRoom.m_show_form();
//    });
//    $("#UserTabA").click(function(e) {
//        cmsGroup.loadForm();
//        cmsUser.loadForm();
//        cmsUser.mainTabSetSize();
//        if($('#UserTab1').css('display')!='none'){
//            cmsUser.m_show_tbl();
//            $( "#swAccessAll" ).tabs({
//                selected:0
//            });
//        } else if($('#UserTab2').css('display')!='none'){
//            cmsGroup.m_show_tbl();
//            $( "#swAccessAll" ).tabs({
//                selected:1
//            });
//        };
//    });

//    $("#swCommentTabA").click(function(e) {
//        cmsComment.loadForm();
//        if($('#swCommentTbl').css('display')!='none'){
//            if($('#swCommentTbl').html()==''){
//                cmsComment.m_refresh();
//            };
//        };
//        cmsComment.m_refresh();
//    });
}
function loginToTice() {
    new jj("do=Access_User.loginTice&" + (new jj("#swLoginForm").jjSerial())).jjAjax2();
}
function getlistEnrolInTerm(termId) {
    $("#tabs").tabs({
        selected: 1
    });
    new jj("do=Tice_enrol.refresh&id=" + termId).jjAjax2();
}
function getlistClassInTermAndShowTab(termId) {
    $("#tabs").tabs({
        selected: 2
    });
    new jj("do=Tice_class.refresh2&id=" + termId).jjAjax2();
}
function registStudentTbl(id) {
    tice_class_regist.loadForm();
    tice_class_regist.m_select_std(id);
    $("#tabs").tabs({
        selected: 1
    })
}