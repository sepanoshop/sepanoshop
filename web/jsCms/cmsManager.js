var innerPanelHeight = 393;
var PanelHeight = 435;
// ----------------------------------------------------------------------------

function loadFormsCms() {
    $("#swLoginForm").load("formCms/login.html", null, function () {
        jj('#login_pass').jjAddEnterKeyListener('loginToCMS();');
        new jj("#user_birthdate").jjCalendarWithYearSelector(1320, 1380);
        $("#loginBtn").button().click(function (e){
            loginToCMS();
        });
        jj("#login_email").jjVal('a');
        jj("#login_pass").jjVal('a');
        loginToCMS();
    });

}
function tblMethod(tblCode) {
    if (tblCode == "1") {
        cmsContent.m_add_new();
    } else if (tblCode == "2") {
        cmsNews.m_add_new();
    } else if (tblCode == "3") {
        cmsCategoryForum.m_add_new();
    } else if (tblCode == "4") {
        cmsCategoryGallery.m_add_new();
    } else if (tblCode == "5") {
        cmsCategoryNews.m_add_new();
    } else if (tblCode == "6") {
        cmsCategoryPoll.m_add_new();
    } else if (tblCode == "7") {
        cmsCategoryProducts.m_add_new();
    } else if (tblCode == "9") {
        cmsForum.m_add_new();
    } else if (tblCode == "10") {
        cmsGroup.m_add_new();
    } else if (tblCode == "11") {
        cmsPermission.m_add_new();
    } else if (tblCode == "12") {
        cmsPic.m_add_new();
    } else if (tblCode == "13") {
        cmsPoll.m_add_new();
    } else if (tblCode == "14") {
        cmsProduct.m_add_new();
    } else if (tblCode == "15") {
        cmsUser.m_add_new();
    } else if (tblCode == "21") {
        cmsTiceBook.m_add_new();
    } else if (tblCode == "22") {
        cmsTiceRoom.m_add_new();
    } else if (tblCode == "16") {
        cmsFactor.m_add_new();
    } else if (tblCode == "17") {
        cmsCustomer.m_add_new();
    } else if (tblCode == "18") {
        cmsSMS.m_add_new();
    } else if (tblCode == "19") {
        cmsPortal.m_add_new();
    } else if (tblCode == "20") {
        cmsPortalUser.m_add_new();
    }

}


function manageTabOnClickCms() {
    // click on pages
    $("#ContentTabA").click(function (e) {
        cmsContent.loadForm();
        if ($('#swContent').css('display') != 'none') {
            cmsContent.m_show_tbl();
        }
        ;
        //        cmsContent.m_refresh();
    });
    $("#NewsTabA").click(function (e) {
        cmsNews.loadForm();
        if ($('#newsTab1').css('display') != 'none') {
            cmsNews.m_refresh();
            //            $( "#swNewsAll" ).tabs({
            //                selected:0
            //            });
        } else if ($('#newsTab2').css('display') != 'none') {
            cmsCategoryNews.m_show_tbl();
            //            $( "#swNewsAll" ).tabs({
            //                selected:1
            //            });
        }
        cmsNews.mainTabSetSize();
    });
    $("#PicTabA").click(function (e) {
        cmsPic.loadForm();
        if ($('#swPicTbl').html() == '') {
            cmsPic.m_refresh();
        }
        ;
        cmsPic.mainTabSetSize();
    });
    $("#PortalTabA").click(function (e) {
        cmsPortal.loadForm();
        if ($('#swPortalTbl').html() == '') {
            cmsPortal.m_refresh();
        }
        ;
        cmsPortal.mainTabSetSize();
    });
    $("#swCommentTabA").click(function (e) {
        cmsComment.loadForm();
        if ($('#swCommentTbl').css('display') != 'none') {
            if ($('#swCommentTbl').html() == '') {
                cmsComment.m_refresh();
            }
            ;
        }
        ;
        cmsComment.m_refresh();
    });
    $("#ProductTabA").click(function (e) {
        cmsProduct.loadForm();
        cmsFactor.loadForm();
        cmsCustomer.loadForm();
        if ($('#swProduct1Tbl').css('display') != 'none') {
            cmsFactor.m_show_tbl();
        }
        ;
    });
    $("#PollTabA").click(function (e) {
        cmsPoll.loadForm();
        cmsPoll.mainTabSetSize();
        if ($('#PollTab1').css('display') != 'none') {
            if ($('#swPollTbl').html() == '') {
                cmsPoll.m_refresh();
            }
            ;
        } else if ($('#PollTab2').css('display') != 'none') {
            if ($('#swCategoryPollTbl').html() == '') {
                cmsCategoryPoll.m_refresh();
            }
            ;
        }
        ;
    });
    $("#SmsTabA").click(function (e) {
        cmsSMS.loadForm();
        cmsSMS.mainTabSetSize();
        if ($('#SMSTab1').css('display') != 'none') {
            if ($('#swSMSTbl').html() == '') {
                cmsSMS.m_refresh();
            }
            ;
        } else if ($('#SMSTab2').css('display') != 'none') {
            if ($('#swCategorySMSTbl').html() == '') {
                cmsCategorySMS.m_refresh();
            }
            ;
        }
        ;
    });
    $("#CategoryForumTabA").click(function (e) {
        cmsCategoryForum.loadForm();
        cmsForum.mainTabSetSize();
        if ($('#CategoryForumTab1').css('display') != 'none') {
            if ($('#swCategoryForumTbl').html() == '') {
                cmsCategoryForum.m_refresh();
            }
            ;
        } else if ($('#CategoryForumTab2').css('display') != 'none') {
            if ($('#swForumTbl').html() == '') {
                cmsForum.m_refresh();
            }
            ;
        }
        ;
    });
    $("#UserTabA").click(function (e) {
        cmsUser.loadForm();
        cmsUser.mainTabSetSize();
        if ($('#UserTab1').css('display') != 'none') {
            cmsUser.m_show_tbl();
            //            $( "#swAccessAll" ).tabs({
            //                selected:0
            //            });
        } else if ($('#UserTab2').css('display') != 'none') {
            cmsGroup.m_show_tbl();
            $("#swAccessAll").tabs({
                selected: 1
            });
        }
        ;
    });
    $("#swEnrolmentTabA").click(function (e) {
        cmsEnrolment.loadForm();
        cmsEnrolment.m_refresh();
    });
    $("#swEnrolmentTabA3").click(function (e) {
        cmsEnrolment3.loadForm();
        cmsEnrolment3.m_refresh();
    });

}

function printComment(id) {
    jj('Print?form=printComment&id=' + id).jjGoNewPage();
}
function printEnrolment3(id) {
    jj('Print?form=printEnrolment3&id=' + id).jjGoNewPage();
}
function printFactor(id) {
    jj('Print?form=printFactor&id=' + id).jjGoNewPage();
}