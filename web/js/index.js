// Server 
// Servlet name for response to user request
var setting_server = "Server";

// main content
// this page 'on page loading' show to user automaticaly
var setting_default_sw = "صفحه اصلی";
//var setting_default_sw = "خانه"; 

// search
// this is id of
var setting_search_input_id = "#jjSearchInput";
var setting_search_input_id_btn = "#jjSearchBtn";

// public comment form setting
var setting_comment_main_panel = "#sw";
var setting_comment_afted_send_text = setting_default_sw;

// news slider
var setting_news_slider = "#jjSliderNews";
// you must add this style to page
// <script type="text/javascript" src="js/slide/slider.js"></script>
// p#controls { margin:0; padding:0; position:relative; }
// #prevBtn { display:block; margin:0; overflow:hidden; width:16px; height:16px; position:absolute; left:-30px; top:-140px; }
// #nextBtn { display:block; margin:0; overflow:hidden; width:16px; height:16px; position:absolute; left: 650px; top:-140px; }
// #prevBtn a { display:block; width:16px; height:16px; background:url(js/slide/prev4.png) no-repeat 0 0; }
// #nextBtn a { display:block; width:16px; height:16px; background:url(js/slide/next4.png) no-repeat 0 0; }

// pic slider
var setting_flash_slider_panel = "#jjSliderFlash";
var setting_flash_slider_default_PicLink = "<a href='http://www.adobe.com/go/getflashplayer'><img src='upload/defualtPic.jpg' style='width: 150;height: 290'  alt='Get Adobe Flash player' /></a>";
var setting_flash_slider_panel_default_height = 290;
var setting_flash_slider_panel_default_width = 950;
// pic slider
var setting_pic_slider_panel = "#jjSliderPic";
var setting_pic_slider_responsive_panel = "#jjSliderPicResponsive";
var setting_pic_slider_delay = "6000";
// you must add this style to page
// <script type="text/javascript" src="js/slide/slider.js"></script>
// <script type="text/javascript" src="js/slider2/easySlider1.5.js"></script>
// .cs-buttons { display:block; margin:-32px 0 0; padding:0; font-size:0px; float:left;}
// .cs-buttons a { margin:0 2px; width:20px; height:20px; float:left; color:#fff; text-indent:-10000px; background:url(js/slide/slide_p.png) no-repeat center center;}
// .cs-buttons a.cs-active { color:#fff; background-image:url(js/slide/slide_a.png);}

//
var setting_login_exit_panel = "#jjLoginExitPanel";
// -----------------------------------------------------------------
function initCms(lang) {
    lang = lang == null ? "fa" : lang.toString().toLowerCase();
    jj(setting_server).jjSetServletName();
    if (lang == 'en') {
        $("#sw").css('direction', 'ltr');
        $("#sw").css('text-align', 'left');
    }
    if (lang == 'fa') {
        $("#sw").css('direction', 'rtl');
        $("#sw").css('text-align', 'justify');
    }
    if (lang == 'ar') {
        $("#sw").css('direction', 'rtl');
        $("#sw").css('text-align', 'justify');
    }
    jj(lang).jjSetLanguage();
    if (setting_search_input_id != "") {
        if ($(setting_search_input_id).length > 0) {
            search(setting_search_input_id);
        }
    }
    if (setting_default_sw != "") {
        sw(setting_default_sw);
    }
}
function jjNewsSlider(newsSliderDivId){
        if(!(newsSliderDivId == null || newsSliderDivId == "")){
        setting_news_slider = newsSliderDivId;
    }
        if (setting_news_slider != "") {
        if ($(setting_news_slider).length > 0) {
            newsSlider2(setting_news_slider);
        }
    }
}
function jjSerchBtnInit(searchDivId){
    if(!(searchDivId === null || searchDivId === "")){
        setting_search_input_id_btn = searchDivId;
    }
    if (setting_search_input_id_btn !== "") {
        if ($(setting_search_input_id_btn).length > 0) {
            $(setting_search_input_id_btn).click(function () {
                if ($(setting_search_input_id).length > 0) {
                    var text = new jj(setting_search_input_id).jjVal();
                    if (text.length > 2) {
                        searchAction(text)
                    }
                }
            });
        }
    }
}
function jjAutoSlider(selector) {
    try {
        if (selector != null) {
            setting_flash_slider_panel = selector;
        }
        if (setting_flash_slider_panel != "") {
            if (setting_flash_slider_default_PicLink != "") {
                if ($(setting_flash_slider_panel).html() == "") {
                    $(setting_flash_slider_panel).html(setting_flash_slider_default_PicLink);
                }
            }
            if ($(setting_flash_slider_panel).length > 0) {
                var flashvars = {};
                flashvars.xml = "config.xml";
                if (LANGUAGE == 'en') {
                    flashvars.xml = "config_en.xml";
                }
                if (LANGUAGE == 'ar') {
                    flashvars.xml = "config_ar.xml";
                }
                flashvars.font = "font.swf";
                var attributes = {};
                attributes.wmode = "transparent";
                attributes.id = "slider";
                var panelWidth = $(setting_flash_slider_panel).css('width').replace("px", "", 0);
                var panelHeight = $(setting_flash_slider_panel).css('height').replace("px", "", 0);
                panelWidth = panelWidth == null ? setting_flash_slider_panel_default_width : panelWidth;
                panelHeight = panelHeight == null ? setting_flash_slider_panel_default_height : panelHeight;
                panelHeight = panelHeight == "404" ? setting_flash_slider_panel_default_height : panelHeight;
                swfobject.embedSWF("js/cu3er.swf", setting_flash_slider_panel.replace("#", ""), panelWidth, panelHeight, "9", "expressInstall.swf", flashvars, attributes);
            } else if (setting_pic_slider_panel != "" && $(setting_pic_slider_panel).length > 0)
            {
                picSlider(setting_pic_slider_panel, setting_pic_slider_delay);
            }
            else if (setting_pic_slider_responsive_panel != "" && $(setting_pic_slider_responsive_panel).length > 0)
            {
                picSlipprySlider(setting_pic_slider_responsive_panel, setting_pic_slider_delay);
            }
        }
    } catch (e) {
        setting_pic_slider_panel = setting_flash_slider_panel;
        if (setting_pic_slider_panel != "") {
            if ($(setting_pic_slider_panel).length > 0) {
                picSlider(setting_pic_slider_panel, setting_pic_slider_delay);
            }
        }
    }
}

var swArray = new Array();
function sw(titleTextOrId) {
    // ------------------  add request to Array for history --------------------
    swArray.push(titleTextOrId);
    // ------------------  clean value in titleTextOrId ------------------------
    titleTextOrId = titleTextOrId.toString().toLowerCase();
    while (titleTextOrId.indexOf("\n") > -1) {
        titleTextOrId = titleTextOrId.replace("\n", "");
    }
    while (titleTextOrId.indexOf("</span>") > -1) {
        titleTextOrId = titleTextOrId.replace("</span>", "");
    }
    while (titleTextOrId.indexOf("<span>") > -1) {
        titleTextOrId = titleTextOrId.replace("<span>", "");
    }
    titleTextOrId = new jj(titleTextOrId).jjTrim();

    // ------------------  switch sw and slider --------------------------------
    //    if(titleTextOrId=="خانه"){
    //        $('#sw').hide();
    //        $('#jjSliderPic').show();
    //        $('#jjSliderNews').show();
    //    }else{
    //        $('#sw').show();
    //        $('#jjSliderPic').hide();
    //        $('#jjSliderNews').hide();
    //    }

    // ------------------  append 'comment form' to sw -------------------------
    if (titleTextOrId.toString().toLowerCase() == "$comment") {
        $("#sw").append("<div id='pCommentDiv'></div>");
        var commentPage = LANGUAGE == 'fa' ? 'public_comment_fa.html' : (LANGUAGE == 'en' ? 'public_comment_en.html' : 'public_comment_ar.html');
        $("#pCommentDiv").load("formCms/" + commentPage, null, function () {
            $('#insert_Comment').button().click(function () {
                if ($('#comment_full_name').val() == '') {
                    $('#comment_full_name').css("border", "red dashed");
                } else if ($('#comment_email').val() == '') {
                    $('#comment_full_name').css("border", "none");//if previus time was red and know intered
                    $('#comment_email').css("border", "red dashed");
                } else if ($('#comment_text').val() == '') {
                    $('#comment_email').css("border", "none");//if previus time was red and know intered
                    $('#comment_text').css("border", "red dashed");
                } else {
                    jj("do=Comment.insert&" + new jj("#swCommentForm").jjSerial()).jjAjax2(false, 'Server');
                    sw(setting_comment_afted_send_text);
                }
            });
            if (USER_NAME != '') {
                new jj('#comment_full_name').jjVal(USER_NAME + ' ' + USER_FAMILY);
                new jj('#comment_email').jjVal(USER_EMAIL);
                $('#comment_full_name').attr('disabled', 'disabled');
                $('#comment_email').attr('disabled', 'disabled');
                $('#comment_tell').focus();
            } else {
                $('#comment_full_name').focus();
            }
        });
        $("#swTitle").html("تماس با ما");
        swRightClear();
        return false;
    }
    // ------------------  append 'comment form' to sw -------------------------
    if (titleTextOrId.toString().toLowerCase() == "$comment2") {
        $("#sw").html("<div id='pCommentDiv'></div>");
        var commentPage = LANGUAGE == 'fa' ? 'public_comment_fa.html' : 'public_comment_en.html';
        $("#pCommentDiv").load("formCms/" + commentPage, null, function () {
            $('#insert_Comment').button().click(function () {
                jj("do=Comment.insert&" + new jj("#swCommentForm").jjSerial()).jjAjax2(false, 'Server');
                sw(setting_comment_afted_send_text);
            });
            if (USER_NAME != '') {
                new jj('#comment_full_name').jjVal(USER_NAME + ' ' + USER_FAMILY);
                new jj('#comment_email').jjVal(USER_EMAIL);
                $('#comment_full_name').attr('disabled', 'disabled');
                $('#comment_email').attr('disabled', 'disabled');
                $('#comment_tell').focus();
            } else {
                $('#comment_full_name').focus();
            }
        });
        $("#swTitle").html("تماس با ما");
        swRightClear();
        return false;
    }
    // ------------------  show login form dialog ------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$login") {
        if (USER_NAME == '') {
            showLoginForm();
        } else {
            new jj('کاربر محترم ' + USER_NAME + "&nbsp;" + USER_FAMILY + " آیا مایلید از سیستم خارج شوید؟").jjDialog_YesNo('signOut();', true, "خروج");
        }
        return false;
    }

    // ------------------  show enrolment form ---------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$enrolment") {
        $("#sw").append("<div id='enrolmentDiv'></div>");
        var enrolPage = LANGUAGE == 'fa' ? 'public_enrolment.html' : 'public_enrolment_en.html';
        $("#enrolmentDiv").load("formCms/" + enrolPage, null, function () {
            new jj('#enrolment_date').jjCalendarWithYearSelector(1340, 1380);
            $('#insert_enrolment').button().click(function () {
                jj("do=Enrolment.insert&" + new jj("#swEnrolmentFormPublic").jjSerial()).jjAjax2(false, 'Server');
                sw(setting_comment_afted_send_text);
            });

            $('#enrol_url_file').button().click(function () {
            });
            $('#upload_Enrol').button().click(function () {
                if (new jj('#enrol_url_file').jjVal() != '') {
                    $('#upload_Enrol').hide();
                    $('#enrol_url_file').hide();
                    $('#enrolment_pic').show()
                }
            });
            $('#enrolment_pic').button().click(function () {
                $('#upload_Enrol').show();
                $('#enrol_url_file').show();
                $('#enrolment_pic').hide()
            });
            new jj('#upload_Enrol').jjAjaxFileUpload('#enrol_url_file', '#enrolment_pic', '#enrol_url_pic_demo');

            $('#enrol_url_file2').button().click(function () {
            });
            $('#upload_Enrol2').button().click(function () {
                if (new jj('#enrol_url_file2').jjVal() != '') {
                    $('#upload_Enrol2').hide();
                    $('#enrol_url_file2').hide();
                    $('#enrolment_file').show()
                }
            });
            $('#enrolment_file').button().click(function () {
                $('#upload_Enrol2').show();
                $('#enrol_url_file2').show();
                $('#enrolment_file').hide()
            });
            new jj('#upload_Enrol2').jjAjaxFileUpload2('#enrol_url_file2', '#enrolment_file');
        });
        $("#swTitle").html("فرم استخدام");
        swRightClear();
        return false;
    }
    // ------------------  show news -------------------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$news") {
        swGetNews();
        swRightNewsMenu("swRight");
        return false;
    }

    // ------------------  show forum categorys --------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$forum") {
        new jj("do=Category_Forum.getList&panel=sw").jjAjax2(true);
        $("#swTitle").html("انجمن ها");
        swRightClear();
        return false;
    }

    // ------------------  show polls ------------------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$poll") {
        $("#swTitle").html("نظر سنجی ها");
        swRightClear();
        new jj("do=Poll.getList&panel=sw").jjAjax2(true);
        return false;
    }

    // ------------------  show pic gallery ------------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$gallery") {
        $("#swTitle").html("گالری تصاویر");
        swRightClear();
        swGetGallery();
        return false;
    }
    // ------------------  show pic gallery ------------------------------------
    if (titleTextOrId.toString().toLowerCase() == "$product") {
        $("#swTitle").html("محصولات");
        swGetProducts();
        swRightProductMenu("swRight");
        return false;
    }
    // ------------------  get data from content table  ------------------------
    new jj("do=Content.sw&text=" + titleTextOrId.toString() + "&panel=sw&title=swTitle&jj=1").jjAjax2(true);
    swRightClear();

    /*
     *@augments if menu dosnt need , it must be empty
     **/
}
;
function swRightClear() {
    $("#swRight").html("");
}


function showLoginForm() {
    if ($('#pshowLoginFormDiv').length == 0) {
        $("body").append("<div id='pshowLoginFormDiv'></div>");
        $("#pshowLoginFormDiv").load("formCms/public_login.html", null, function () {
            $('#loginBtn').button().click(function () {
                signIn();
            });
            $('#registBtn').button().click(function () {
                registInSite();
            });

            jj("#user_pass1").jjAddEnterKeyListener("signIn();");
            jj("#user_email1").jjAddEnterKeyListener("signIn();");
            jj("#user_answer").jjAddEnterKeyListener("registInSite();");
            jj("#user_birthdate").jjCalendarWithYearSelector(1320, 1380);
            $("#loginRegistForm").dialog({
                autoOpen: false,
                height: 450,
                width: 790,
                modal: true,
                title: "ورود - ثبت نام",
                buttons: {
                    "لغو": function () {
                        $(this).dialog("close");
                    }
                },
                close: function () {
                    $(this).dialog('destroy');
                }
            });
            $("#loginRegistForm").dialog("open");
            return false;
        });
    }
    $("#loginRegistForm").dialog({
        autoOpen: false,
        height: 450,
        width: 790,
        modal: true,
        title: "ورود - ثبت نام",
        buttons: {
            "لغو": function () {
                $(this).dialog("close");
            }
        },
        close: function () {
            $(this).dialog('destroy');
        }
    });
    $("#loginRegistForm").dialog("open");
}
function changeLang(lang) {
    jj(lang).jjSetLanguage();
    if (swArray.length > 0) {
        sw(swArray[swArray.length - 1]);
    }
}
var someStringInSw = "$comment,$login,$enrolment,$news,$forum,$gallery";
function refreshLastSwParameter() {
    if (swArray.length > 2) {
        refreshLastSwParameter3();
        return false;
    }
    if (swArray.length > 1) {
        refreshLastSwParameter2();
        return false;
    }
    if (swArray.length > 0) {
        refreshLastSwParameter1();
        return false;
    }
}
function refreshLastSwParameter1() {
    if (someStringInSw.indexOf(swArray[swArray.length - 1]) < 0) {
        sw(swArray[swArray.length - 1]);
    }
}
function refreshLastSwParameter2() {
    if (someStringInSw.indexOf(swArray[swArray.length - 1]) < 0) {
        sw(swArray[swArray.length - 1]);
    }
}
function refreshLastSwParameter3() {
    if (someStringInSw.indexOf(swArray[swArray.length - 2]) < 0) {
        sw(swArray[swArray.length - 2]);
    }
}
function signIn() {
    if (new jj('#user_email1').jjVal() == '') {
        $("#user_email1").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل یا رمز عبور نباید تهی باشد.");
        return false;
    } else if (new jj('#user_pass1').jjVal() == '') {
        $("#user_email1").css("border", "none");
        $("#user_pass1").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل یا رمز عبور نباید تهی باشد.");
        return false;
    }
    $("#user_email1").css("border", "none");
    $("#user_pass1").css("border", "none");
    jj("do=Access_User.loginUser&panel=" + setting_login_exit_panel.replace("#", "") + "&" + new jj("#loginForm").jjSerial()).jjAjax2(false);
}
function registInSite() {
    if (new jj('#user_email').jjVal() == '') {
        $("#registMessagePanel").html("ایمیل یا رمز عبور نباید تهی باشد.");
        return false;
    }
    if (new jj('#user_pass').jjVal() == '') {
        $("#registMessagePanel").html("ایمیل یا رمز عبور نباید تهی باشد.");
        return false;
    }
    if (new jj('#user_name').jjVal() == '') {
        $("#registMessagePanel").html("لطفا نام خود را وارد کنید.");
        return false;
    }
    if (new jj('#user_family').jjVal() == '') {
        $("#registMessagePanel").html("لطفا نام خانوادگی خود را وارد کنید.");
        return false;
    }
    if (new jj('#user_answer').jjVal() == '') {
        $("#registMessagePanel").html("لطفا جواب سوال خود را وارد کنید.");
        return false;
    }
    if (new jj('#user_pass_2').jjVal() != new jj('#user_pass').jjVal()) {
        $("#registMessagePanel").html("رمز عبور و تکرار آن باید یکی باشند.");
        return false;
    }
    jj("do=Access_User.registUser&panel=" + setting_login_exit_panel.replace("#", "") + "&" + new jj("#registForm").jjSerial()).jjAjax2(false);
}
function signOut() {
    USER_NAME = "";
    USER_FAMILY = "";
    USER_EMAIL = "";
    USER_PASS = "";
    new jj("do=Access_User.signOut").jjAjax2(true);
    $("#jjLoginExitPanel").html('ورود / ثبت نام');
}
function swGetNewsCategory() {
    new jj("do=News.getList&panel=sw&id=0&jj=1").jjAjax2(true);//id=0=> top news(slider + priority 2)
}
function swGetForumCategory() {
    new jj("do=Category_Forum.getList&panel=sw").jjAjax2(true);
}
function swVoteToPoll(pollId, whichOneRecord) {
    if (USER_NAME == '') {
        sw('$login');
        return false;
    } else {
        new jj("do=Poll.voteToPoll&pollId=" + pollId + "&whichRecord=" + whichOneRecord).jjAjax2(true);
    }
}
function swGetGallery(galleryId) {
    new jj("do=Pic.getGallery&panel=sw&id=" + galleryId).jjAjax2(true);
    swRightClear();
}
function swGetProducts(productId) {
    var tempid = (productId) ? productId : "0";
    new jj("do=Product.getList&panel=sw&id=" + tempid + "&jj=1").jjAjax2(true);

}
function getOneproduct(newsId) {
    if (jj(newsId).jjIsDigit()) {
        new jj("do=Product.getOneProduct&id=" + newsId.toString() + "&panel=swTopproductDiv").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(3);

    }
}
;
function swRightProductMenu(panel) {
    var panel = (panel) ? panel : "swRight";
    new jj("do=Category_Product.getHierarchyDiv&panel=" + panel + "&id=0&jj=1").jjAjax2(true);
}
function swRightNewsMenu(panel) {
    var panel = (panel) ? panel : "swRight";
    new jj("do=Category_News.getHierarchyDiv&panel=" + panel + "&id=0&jj=1").jjAjax2(true);
}

function picDialog(address, title) {
    //    new jj(address).jjDialog(true,' ',10,750);
    //    new jj("<img  src="+address+" style='width:98%'/>").jjDialog(true,title,window.screen.height*90/100,"98%");
    //    alert(title);
    new jj("<img  src=" + address + " style='width:100%'/>").jjDialog(true, title, 650, 750);
}
function swGetNews(newsCategoryId) {
    var catId = (newsCategoryId) ? newsCategoryId : "0";
    new jj("do=News.getList&id=" + catId + "&panel=sw&jj=1").jjAjax2(true);
}
function newsDisLike(newsId) {
    var temp = $("#swTopNewsDiv").html();
    temp = temp.replace("onclick=\"newsLike(" + newsId + ");\"", "");
    temp = temp.replace("onclick=\"newsDisLike(" + newsId + ");\"", "");
    $("#swTopNewsDiv").html(temp);
    new jj("do=News.newsDisLike&id=" + newsId).jjAjax2(true);
    jj("سپاس از همکاری شما.نظر شما بزودی در سیستم اعمال می شود...").jjDialog();
}
;
function newsLike(newsId) {
    var temp = $("#swTopNewsDiv").html();
    temp = temp.replace("onclick=\"newsLike(" + newsId + ");\"", "");
    temp = temp.replace("onclick=\"newsDisLike(" + newsId + ");\"", "");
    $("#swTopNewsDiv").html(temp);
    new jj("do=News.newsLike&id=" + newsId).jjAjax2(true);
    jj("سپاس از همکاری شما.نظر شما بزودی در سیستم اعمال می شود...").jjDialog();
}
;

//By Md
function productDisLike(productId) {
    jj("سپاس از همکاری شما.نظر شما بزودی در سیستم اعمال می شود...").jjDialog();
    var temp = $("#swTopproductDiv").html();
    temp = temp.replace("onclick=\"productDisLike(" + productId + ");\"", "");
    temp = temp.replace("onclick=\"productLike(" + productId + ");\"", "");
    $("#swTopproductDiv").html(temp);
    new jj("do=Product.productDisLike&id=" + productId).jjAjax2(true);
}
;

//By Md
function productLike(productId) {
    jj("سپاس از همکاری شما.نظر شما بزودی در سیستم اعمال می شود...").jjDialog();
    var temp = $("#swTopproductDiv").html();
    temp = temp.replace("onclick=\"productLike(" + productId + ");\"", "");
    temp = temp.replace("onclick=\"productDisLike(" + productId + ");\"", "");
    $("#swTopproductDiv").html(temp);
    new jj("do=Product.productLike&id=" + productId).jjAjax2(true);
}
;


function swNews(newsId) {
    if (jj(newsId).jjIsDigit()) {
        new jj("do=News.sw&id=" + newsId.toString() + "&panel=sw").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(3);

    }
}
;
/*
 *Only use when div whit "id=swTopNewsDiv" is avalable in DOM( when getList() in serverside has ben caled)
 **/
function getOneNews(newsId) {
    if (jj(newsId).jjIsDigit()) {
        new jj("do=News.getOneNews&id=" + newsId.toString() + "&panel=swTopNewsDiv&jj=1").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(3);

    }
}
;
function swNews2(newsId) {
    if (jj(newsId).jjIsDigit()) {
        new jj("do=News.swPishro&id=" + newsId.toString() + "&panel=sw").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(5);

    }
}
;

function swForum(forumId) {
    if (jj(forumId).jjIsDigit()) {
        new jj("do=Forum.getList&id=" + forumId.toString() + "&panel=sw").jjAjax2(true);
    }
}
;
function swPic(titleTextOrId) {
    var panelSelector = "sw";
    if (jj(titleTextOrId).jjIsDigit()) {
        new jj("do=Pic.getGallery&id=" + titleTextOrId.toString() + "&panel=" + panelSelector + "&title=swTitle").jjAjax2(true);
    } else {
        var text = (titleTextOrId == null) ? "جاوارا" : new jj(titleTextOrId.toString()).jjTrim();
        while (text.indexOf("\n") > -1) {
            text = text.replace("\n", "");
        }
        new jj("do=Pic.sw&text=" + text + "&panel=" + panelSelector + "&title=swTitle").jjAjax2(true);
    }
}
;

function searchAction(text, panelSelector) {
    panelSelector = (panelSelector == null) ? "sw" : panelSelector;
    new jj("do=Content.searchTextInAll&text=" + text + "&panel=" + panelSelector + "&title=swTitle").jjAjax2(true);
}
/**
 * This method don't lets to user write string on TextField <br>
 * @param selector is TextFeildTagID selector; <br/>
 * @param maxLength is int (default: 10) <br/>
 */
function search(searchtxtSelector) {
    if (LANGUAGE == 'fa') {
        jj(searchtxtSelector).jjSetDefaultValueToTxt("... جستجو");
    } else {
        jj(searchtxtSelector).jjSetDefaultValueToTxt("Search...");
    }
    var panelSelector = (panelSelector == null) ? "sw" : panelSelector;
    $(searchtxtSelector).keypress(function (event) {
        var keynum;
        if (window.event) { // IE
            keynum = event.keyCode
        } else if (event.which) { // Netscape/Firefox/Opera
            keynum = event.which
        }
        if (keynum == undefined) {
            event.returnValue = true;
            return true;
        } else {
            if (keynum == 13) {
                var text = new jj(searchtxtSelector).jjVal();
                if (text.length > 2) {
                    searchAction(text)
                }
                event.returnValue = true;
                return true;
            }
        }
    })
}
function newsSlider2(panel) {//static fixed absolute inherit relative
    var panelWidth = $(panel).css('width');
    var panelHeight = $(panel).css('height');
    if ($(panel).css('direction') != 'ltr') {
        alert("div by id=" + panel + " must be direction:ltr ")
    }
    var param = "";
    param += "do=News.getSlider2";
    param += "&panel=" + panel.replace("#", "");
    param += "&width=" + (panelWidth != null ? panelWidth : 600);
    param += "&height=" + (panelHeight != null ? panelHeight : 300);
    new jj(param).jjAjax2();
}
function picSlider(panel, delay) {
    var panelWidth = $(panel).css('width');
    var panelHeight = $(panel).css('height');
    var param = "";
    param += "do=Pic.getPicSlider";
    param += "&panel=" + panel.replace("#", "");
    param += "&delay=" + delay;
    param += "&width=" + (panelWidth != null ? panelWidth : 600);
    param += "&height=" + (panelHeight != null ? panelHeight : 300);
    new jj(param).jjAjax2();
}
function picSlipprySlider(panel, delay) {
    var panelWidth = $(panel).css('width');
    var panelHeight = $(panel).css('height');
    var param = "";
    param += "do=Pic.getPicSlipprySlider";
    param += "&panel=" + panel.replace("#", "");
    param += "&delay=" + delay;
    param += "&width=" + (panelWidth != null ? panelWidth : 600);
    param += "&height=" + (panelHeight != null ? panelHeight : 300);
    new jj(param).jjAjax2();
}

function swAddForumCategory() {
    if (USER_NAME == '') {
        //        new jj('جهت افزودن یک بحث جدید احتیاج به لاگین و یا ثبت نام می باشد. آیا مایل باشید؟').jjDialog_YesNo("sw('$login');", true, 'نیاز به لاگین')
        sw('$login');
    } else {
        $("#sw").load("formCms/public_add_forum_cat.html", null, function () {
            $('#insert_ForumCat').button().click(function () {
                jj("do=Category_Forum.insertByUser&" + new jj("#swCategoryForumForm").jjSerial()).jjAjax2(false, 'Server');
                sw('$forum');
            });
            $('#Cansel_ForumCat').button().click(function () {
                sw('$forum');
            });
        });
    }
}
var swAddForumCommentCounter = 0;
function swAddForumComment(forum_cat_id) {
    if (USER_NAME == '') {
        //        new jj('جهت افزودن یک کامنت جدید احتیاج به لاگین و یا ثبت نام می باشد. آیا مایل باشید؟').jjDialog_YesNo("sw('$login');", true, 'نیاز به لاگین')
        sw('$login');
    } else {
        swAddForumCommentCounter += 1;
        $("#sw").load("formCms/public_add_forum_comment.html", null, function () {
            $('#forum_url_file').button().click(function () {
            });
            $('#upload_Forum').button().click(function () {
                if (new jj('#forum_url_file').jjVal() != '') {
                    $('#upload_Forum').hide();
                    $('#forum_url_file').hide();
                    $('#forum_url').show()
                }
            });
            $('#forum_url').button().click(function () {
                $('#upload_Forum').show();
                $('#forum_url_file').show();
                $('#forum_url').hide()
            });
            //    
            new jj("#upload_Forum").jjAjaxFileUpload2("#forum_url_file", "#forum_url");
            $('#forum_content').attr('id', 'forum_content' + swAddForumCommentCounter);
            forum_content_editor = new jj('#forum_content' + swAddForumCommentCounter).jjEditor();
            new jj(forum_content_editor).jjEditorVal("");
            $('#insert_Forum').button().click(function () {
                new jj('do=Forum.insertByUser&forum_creator=' + USER_ID + '&' + new jj('#swForumForm').jjSerial()
                        + '&forum_category_id=' + forum_cat_id).jjAjax2();
            });
            $('#cancel_Forum').button().click(function () {
                swGetForum(forum_cat_id)
            });
        });
        $("#swTitle").html("افزودن کامنت به انجمن");
    }
}

function swGetForum(forum_cat_id) {
    new jj("do=Forum.getList&id=" + forum_cat_id + "&panel=sw").jjAjax2(true);
}
function ForumForm_fa(forum_id) {
    $("#sw").html(getForumForm_fa(forum_id));
    $("#swTitle").html("افزودن کامنت");
}
function ForumCategoryForm_fa() {
    $("#sw").html(getForumCategoryForm_fa());
    $("#swTitle").html("افزودن انجمن (موضوع بحث)");
}
function swTab(tabId) {
    tabId = tabId == null ? 0 : tabId;
    for (var i = 0; i < 20; i++) {
        if ($("#ll" + i).hasClass("active")) {
            $("#ll" + i).removeClass("active")
        }
    }
    $("#ll" + tabId).addClass("active");
}
function vote(vote_id, check_id) {
    //    alert(vote_id +"-"+check_id);
    //    document.write(vote_id +"-"+check_id);
    if (new jj("#vote-" + vote_id + "-" + check_id).jjVal() == 1) {
        $("#vote-" + vote_id + "-1").hide();
        $("#vote-" + vote_id + "-2").hide();
        $("#vote-" + vote_id + "-3").hide();
        $("#vote-" + vote_id + "-4").hide();
        $("#vote-" + vote_id + "-5").hide();
        $("#vote-" + vote_id + "-6").hide();
        new jj("do=Category_Poll.insert&category_poll_answer=" + check_id + "&category_poll_user_id=1&category_poll_which=" + vote_id).jjAjax2();
    }
    //    alert(new jj("#poll"+vote_id).jjSerial());
    //    alert(new jj("#gr1").jjVal());
    //    alert(new jj("#GroupRadio"+vote_id).jjSerial());
    //    alert(new jj("#poll"+vote_id).jjSerial());
    $('#vote-' + vote_id + '-1').hide();
    $('#vote-' + vote_id + '-2').hide();
    $('#vote-' + vote_id + '-3').hide();
    $('#vote-' + vote_id + '-4').hide();
    $('#vote-' + vote_id + '-5').hide();
    $('#vote-' + vote_id + '-6').hide();
    new jj("vote").jjCookieSave('#vote-' + vote_id + '-1___' + '#vote-' + vote_id + '-2___' + '#vote-' + vote_id + '-3___' + '#vote-' + vote_id + '-4___' + '#vote-' + vote_id + '-5___' + '#vote-' + vote_id + '-6___');
}
//
/* Accordion Menu v2013.3.18. Copyright www.menucool.com */
function toggleList(id) {
    if ($("#uperNodeDiv" + id).hasClass("closedList")) {
        $("#uperNodeDiv" + id).removeClass("closedList");
        $("#uperNodeDiv" + id).addClass("openedList");
    } else if ($("#uperNodeDiv" + id).hasClass("openedList")) {
        $("#uperNodeDiv" + id).removeClass("openedList");
        $("#uperNodeDiv" + id).addClass("closedList");
    }
    function makeSelected(id) {
        $("#uperNodeDiv" + id).addClass("current");
    }

}
//############## Portal ########################################################
function addNewPortalPost(portalId){
    portalPostLoadForm();
    loadPortalPostforEdit(portalId);
}
function PortalUserLogin(portalId) {
    if (new jj('#portal_user_UserName').jjVal() == '') {
        $("#portal_user_UserName").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل یا رمز عبور نباید تهی باشد.");
        return false;
    } else if (new jj('#portal_user_pass').jjVal() == '') {
        $("#portal_user_UserName").css("border", "none");
        $("#portal_user_pass").css("border", "red dashed");
        $("#loginMessagePanel").html("ایمیل یا رمز عبور نباید تهی باشد.");
        return false;
    }
    $("#portal_user_UserName").css("border", "none");
    $("#portal_user_pass").css("border", "none");
    jj("do=Access_User.loginPortalUser&panel=" + setting_login_exit_panel.replace("#", "") + "&id=" + portalId + "&" + new jj("#loginForm").jjSerial()).jjAjax2(false);
}

function loadPortalPostforEdit(portalId) {//load list of all posts whit edit btn
    portalPostLoadForm();
    new jj("do=Portal.loadPortalPostforEdit&id=" + portalId + "&panel=swLeft&jj=1").jjAjax2(true);
}
// Portal and portalUsers
function swGetPortalPost(postId) {
    alert("swGetPortalPost([postId])");
    if (jj(postId).jjIsDigit()) {
        new jj("do=Portal.getPortalPost&id=" + postId.toString() + "&panel=sw&jj=1").jjAjax2(true);
        //        $('#sliderPanel').hide();
        //        $('#bodyPanel').show();
        //        $('#sw').show();
        //        swTab(3);
    }
}

function insertPostByUserPortal() {
    var param = "";
    param += "do=Portal.insertByPortalUser";
    param += "&" + new jj("#newPostForm").jjSerial();//#newPostForm is if post_form.html
    jj(param).jjAjax2(false);
    portalFormClean();
}
function editPostByUserPortal(postId) {
    var param = "";
    param += "do=Portal.editPostByUserPortal";
    param += "&" + new jj("#newPostForm").jjSerial();//#newPostForm is if post_form.html
    jj(param).jjAjax2(false);
    portalFormClose();
}
function deleteByPortalUser(postId) {
    var param = "";
    param += "do=Portal.deleteByPortalUser";
    param += "&id=" + postId;
    jj(param).jjAjax2(false);
    $("#swForm").hide('slow', function () {
        portalFormClean();
    });
}
function selectPostforEdit(postId) {
    portalPostLoadForm();
    var param = "";
    param += "do=Portal.selectPostforEdit";
    param += "&id=" + postId;
    jj(param).jjAjax2(false);
}

function portalFormClose() {
    $("#swForm").html("");
}
function portalFormClean() {
    new jj("#newPostForm").jjFormClean();
    $("#preview_portalPost_pic1").attr('src', "img/preview.jpg");
    $("#preview_portalPost_pic2").attr('src', "img/preview.jpg");
    $("#preview_portalPost_pic3").attr('src', "img/preview.jpg");
    $("#preview_portalPost_pic4").attr('src', "img/preview.jpg");
    $("#preview_portalPost_pic5").attr('src', "img/preview.jpg");
//در اینجا می شود اگر عکسی آپلود کرده بود را پاک کرد
//        new jj("#"+cmsPortal.f_gallery_id_select).jjVal('1');
//        new jj("#"+cmsPortal.f_lang).jjVal('1');
//        new jj("#"+cmsPortal.f_parent).jjVal('0');
//        $("#pic_pic_name_preview").attr('src','img/preview.jpg');
}

function cleanFormAfterEdit(portalid){
    $("#swForm").html() != "";
    portalPostLoadForm();
    loadPortalPostforEdit(portalid);
}
function portalPostLoadForm() {
    if (($("#swForm").html() != "")) {
        $("#swForm").show();
        portalFormClean();
    } else {
        $("#swForm").load("images_takrod/post_form.html", null, function () {
            PortalFormGetValuesList();
            portalFormClean();
            $('#btnChangeValueComponent1').click(function () {
                if ($('#portal_post_val11').css('display') == 'none') {
                    $('#portal_post_val11').show();
                    $('#portal_post_val11').attr('name', 'portal_post_val1');
                    $('#portal_post_val1').hide();
                    $('#portal_post_val1').attr('name', 'aaa');
                    $('#btnChangeValueComponent1').val("*");
                } else {
                    $('#portal_post_val11').hide();
                    $('#portal_post_val11').attr('name', 'aaa');
                    $('#portal_post_val1').show();
                    $('#portal_post_val1').attr('name', 'portal_post_val1');
                    $('#btnChangeValueComponent1').val("+");
                }
            })

            $('#btnChangeValueComponent2').click(function () {
                if ($('#portal_post_val22').css('display') == 'none') {
                    $('#portal_post_val22').show();
                    $('#portal_post_val22').attr('name', 'portal_post_val2');
                    $('#portal_post_val2').hide();
                    $('#portal_post_val2').attr('name', 'aaa');
                    $('#btnChangeValueComponent2').val("*");
                } else {
                    $('#portal_post_val22').hide();
                    $('#portal_post_val22').attr('name', 'aaa');
                    $('#portal_post_val2').show();
                    $('#portal_post_val2').attr('name', 'portal_post_val2');
                    $('#btnChangeValueComponent2').val("+");
                }
            })

            $('#btnChangeValueComponent3').click(function () {
                if ($('#portal_post_val33').css('display') == 'none') {
                    $('#portal_post_val33').show();
                    $('#portal_post_val33').attr('name', 'portal_post_val3');
                    $('#portal_post_val3').hide();
                    $('#portal_post_val3').attr('name', 'aaa');
                    $('#btnChangeValueComponent3').val("*");
                } else {
                    $('#portal_post_val33').hide();
                    $('#portal_post_val33').attr('name', 'aaa');
                    $('#portal_post_val3').show();
                    $('#portal_post_val3').attr('name', 'portal_post_val3');
                    $('#btnChangeValueComponent3').val("+");
                }
            })

            $('#btnChangeValueComponent4').click(function () {
                if ($('#portal_post_val44').css('display') == 'none') {
                    $('#portal_post_val44').show();
                    $('#portal_post_val44').attr('name', 'portal_post_val4');
                    $('#portal_post_val4').hide();
                    $('#portal_post_val4').attr('name', 'aaa');
                    $('#btnChangeValueComponent4').val("*");
                } else {
                    $('#portal_post_val44').hide();
                    $('#portal_post_val44').attr('name', 'aaa');
                    $('#portal_post_val4').show();
                    $('#portal_post_val4').attr('name', 'portal_post_val4');
                    $('#btnChangeValueComponent4').val("+");
                }
            })

            $('#btnChangeValueComponent5').click(function () {
                if ($('#portal_post_val55').css('display') == 'none') {
                    $('#portal_post_val55').show();
                    $('#portal_post_val55').attr('name', 'portal_post_val5');
                    $('#portal_post_val5').hide();
                    $('#portal_post_val5').attr('name', 'aaa');
                    $('#btnChangeValueComponent5').val("*");
                } else {
                    $('#portal_post_val55').hide();
                    $('#portal_post_val55').attr('name', 'aaa');
                    $('#portal_post_val5').show();
                    $('#portal_post_val5').attr('name', 'portal_post_val5');
                    $('#btnChangeValueComponent5').val("+");
                }
            })

            $('#btnChangeValueComponent6').click(function () {
                if ($('#portal_post_val66').css('display') == 'none') {
                    $('#portal_post_val66').show();
                    $('#portal_post_val66').attr('name', 'portal_post_val6');
                    $('#portal_post_val6').hide();
                    $('#portal_post_val6').attr('name', 'aaa');
                    $('#btnChangeValueComponent6').val("*");
                } else {
                    $('#portal_post_val66').hide();
                    $('#portal_post_val6').attr('name', 'aaa');
                    $('#portal_post_val6').show();
                    $('#portal_post_val6').attr('name', 'portal_post_val6');
                    $('#btnChangeValueComponent6').val("+");
                }
            })

            $('#btnChangeValueComponent7').click(function () {
                if ($('#portal_post_val77').css('display') == 'none') {
                    $('#portal_post_val77').show();
                    $('#portal_post_val77').attr('name', 'portal_post_val7');
                    $('#portal_post_val7').hide();
                    $('#portal_post_val7').attr('name', 'aaa');
                    $('#btnChangeValueComponent7').val("*");
                } else {
                    $('#portal_post_val77').hide();
                    $('#portal_post_val77').attr('name', 'aaa');
                    $('#portal_post_val7').show();
                    $('#portal_post_val7').attr('name', 'portal_post_val7');
                    $('#btnChangeValueComponent7').val("+");
                }
            })
            $('#btnChangeValueComponent8').click(function () {
                if ($('#portal_post_val88').css('display') == 'none') {
                    $('#portal_post_val88').show();
                    $('#portal_post_val88').attr('name', 'portal_post_val8');
                    $('#portal_post_val8').hide();
                    $('#portal_post_val8').attr('name', 'aaa');
                    $('#btnChangeValueComponent8').val("*");
                } else {
                    $('#portal_post_val88').hide();
                    $('#portal_post_val88').attr('name', 'aaa');
                    $('#portal_post_val8').show();
                    $('#portal_post_val8').attr('name', 'portal_post_val8');
                    $('#btnChangeValueComponent8').val("+");
                }
            })
            $('#btnChangeValueComponent9').click(function () {
                if ($('#portal_post_val99').css('display') == 'none') {
                    $('#portal_post_val99').show();
                    $('#portal_post_val99').attr('name', 'portal_post_val9');
                    $('#portal_post_val9').hide();
                    $('#portal_post_val9').attr('name', 'aaa');
                    $('#btnChangeValueComponent9').val("*");
                } else {
                    $('#portal_post_val99').hide();
                    $('#portal_post_val99').attr('name', 'aaa');
                    $('#portal_post_val9').show();
                    $('#portal_post_val9').attr('name', 'portal_post_val9');
                    $('#btnChangeValueComponent9').val("+");
                }
            })
            $('#btnChangeValueComponent10').click(function () {
                if ($('#portal_post_val1010').css('display') == 'none') {
                    $('#portal_post_val1010').show();
                    $('#portal_post_val1010').attr('name', 'portal_post_val10');
                    $('#portal_post_val10').hide();
                    $('#portal_post_val10').attr('name', 'aaa');
                    $('#btnChangeValueComponent10').val("*");
                } else {
                    $('#portal_post_val1010').hide();
                    $('#portal_post_val1010').attr('name', 'aaa');
                    $('#portal_post_val10').show();
                    $('#portal_post_val10').attr('name', 'portal_post_val10');
                    $('#btnChangeValueComponent10').val("+");
                }
            });
            //Upload bottons ==>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            new jj('#upload_btn_portalPost_pic1').jjAjaxFileUpload('upload_portalPost_pic1', '#portal_post_pic1', '#preview_portalPost_pic1');
            $('#portal_post_pic1').keyup(function () {
                $('#preview_portalPost_pic1').attr('src', 'upload/' + $('#portal_post_pic1').val());
                if ($('#portal_post_pic1').val() == '') {
                    $('#preview_portalPost_pic1').attr('src', 'img/preview.jpg');
                }
            });
            new jj('#upload_btn_portalPost_pic2').jjAjaxFileUpload('upload_portalPost_pic2', '#portal_post_pic2', '#preview_portalPost_pic2');
            $('#portal_post_pic2').keyup(function () {
                $('#preview_portalPost_pic2').attr('src', 'upload/' + $('#portal_post_pic2').val());
                if ($('#portal_post_pic2').val() == '') {
                    $('#preview_portalPost_pic2').attr('src', 'img/preview.jpg');
                }
            });
            new jj('#upload_btn_portalPost_pic3').jjAjaxFileUpload('upload_portalPost_pic3', '#portal_post_pic3', '#preview_portalPost_pic3');
            $('#portal_post_pic3').keyup(function () {
                $('#preview_portalPost_pic3').attr('src', 'upload/' + $('#portal_post_pic3').val());
                if ($('#portal_post_pic3').val() == '') {
                    $('#preview_portalPost_pic3').attr('src', 'img/preview.jpg');
                }
            });
            new jj('#upload_btn_portalPost_pic4').jjAjaxFileUpload('upload_portalPost_pic4', '#portal_post_pic4', '#preview_portalPost_pic4');
            $('#portal_post_pic4').keyup(function () {
                $('#preview_portalPost_pic4').attr('src', 'upload/' + $('#portal_post_pic4').val());
                if ($('#portal_post_pic4').val() == '') {
                    $('#preview_portalPost_pic4').attr('src', 'img/preview.jpg');
                }
            });
            new jj('#upload_btn_portalPost_pic5').jjAjaxFileUpload('upload_portalPost_pic5', '#portal_post_pic5', '#preview_portalPost_pic5');
            $('#portal_post_pic5').keyup(function () {
                $('#preview_portalPost_pic5').attr('src', 'upload/' + $('#portal_post_pic5').val());
                if ($('#portal_post_pic5').val() == '') {
                    $('#preview_portalPost_pic5').attr('src', 'img/preview.jpg');
                }
            });
            //Upload bottons <<<<<<<<<<<<<<<<<<<<<<=========
            new jj("do=Portal.add_new_InPortal").jjAjax2(false);
            //cancel btm===>>>>
            $("#portalPost_cancelBtn").click(function () {
                $("#swForm").hide('slow', function () {
                    portalFormClean();
                });
            });
        }
        )
    };
    function PortalFormGetValuesList(id) {
        PortalFormGetVal1List(id);
        PortalFormGetVal2List(id);
        PortalFormGetVal3List(id);
        PortalFormGetVal4List(id);
        PortalFormGetVal5List(id);
    }
    function PortalFormGetVal1List(id) {
        var param = "";
        param += "do=Portal.get_val1List";
        param += "&panel=" + "portal_post_val1";
        param += "&selected=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
    }
    function PortalFormGetVal2List(id) {
        var param = "";
        param += "do=Portal.get_val2List";
        param += "&panel=" + "portal_post_val2";
        param += "&selected=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
    }
    function PortalFormGetVal3List(id) {
        var param = "";
        param += "do=Portal.get_val3List";
        param += "&panel=" + "portal_post_val3";
        param += "&selected=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
    }
    function PortalFormGetVal4List(id) {
        var param = "";
        param += "do=Portal.get_val4List";
        param += "&panel=" + "portal_post_val4";
        param += "&selected=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
    }
    function PortalFormGetVal5List(id) {
        var param = "";
        param += "do=Portal.get_val5List";
        param += "&panel=" + "portal_post_val5";
        param += "&selected=" + (id == null ? "" : id);
        jj(param).jjAjax2(false);
    }
    
}
function portalUserLoadForm() {
    alert("222222222333ddd33333");
}
