
var LANGUAGE =  "fa" ;
var SERVLET_NAME = "Server" ;
var USER_NAME = "" ;
var USER_FAMILY = "" ;
var USER_EMAIL = "" ;
var USER_PASS = "" ;
var USER_ID = "" ;

//back and foreward buttom(v1.5.0)>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
$(window).on("popstate", function(e) {
    $('#maincontent').html(stack[window.location]);
});
var stateObj = {
    name: "jjStateObj"
};
var stack={};
//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
// --------------------------------------------------------------------------- //
/**
 * JJ Package <br/>
 * This is one javaScript package that help us to use JQuery plugin and javaScript for all client side action. <br/>
 * @package Arvin 
 * @version 1.1 
 * @since 1391.6.28 
 * @author Milad Jamalzadeh (+98)(0913-2686053) 
 * @author Amir NajafiFar   (+98)(0913-1292830) 
 * @copyright Copyright (c) 1391, Arvin Software Development Inc.
 */
var jj = function (selector){
    
    this.selector = selector;
    /**
     * selector can be "fa" or "en"
     * @param src is String, 
     * @example jj("fa").jjSetLanguage(); 
     */
    this.jjSetLanguage = function () {
        LANGUAGE = this.selector.toLowerCase();
        new jj("myLang="+LANGUAGE).jjAjax2();
    }
    this.jjIsLanguage_fa = function () {
        return LANGUAGE=="fa";
    }
    this.jjIsLanguage_en = function () {
        return LANGUAGE=="en";
    }
    /**
     * selector is name of servlet,
     * @param src is String, 
     * @example jj("Server").jjSetServletName(); 
     */
    jjSetServletName = function () {
        SERVLET_NAME = this.selector;
    }
    /**
     * return value from servlet must be a javascript code,
     * selector is String of parameter for send to servlet,
     * @param isCacheable is boolean (defualt: true)
     * @param SERVLET_NAME is servlet name (defualt: 'Server')
     * @example jj("username=milad&password=123").jjAjax(); 
     * jj("username=milad&password=123").jjAjax(false); 
     * jj("username=milad&password=123").jjAjax(false,'../Server'); 
     */
    this.jjAjax = function (isCacheable,servletName) {
        $.ajax( {
            url:(servletName==null) ? SERVLET_NAME : servletName,
            type:"POST",
            cache: isCacheable==null?true:isCacheable ,
            data: this.selector, 
            beforeSend: function () {
                $('html, body').css("cursor", "wait");
                new jj().jjDialogWaitShow();
            }, 
            complete: function () {
                $('html, body').css("cursor", "auto"); 
                new jj().jjDialogWaitHide();
            },
            success: function (data) {
                new jj(data).jjRun();
                $('html, body').css("cursor", "auto"); 
                new jj().jjDialogWaitHide();
            },
            error: function () {
                $('html, body').css("cursor", "auto"); 
                new jj().jjDialogWaitHide();
                if(LANGUAGE=="fa"){
                    new jj("در انجام عملیات مشکلی پیش آمده است.").jjDialog();
                }else{
                    new jj("accur a problem in opration").jjDialog();
                }
            }
        });    
    }
    /**
     * return value from servlet must be a javascript code,
     * selector is String of parameter for send to servlet,
     * @param isCacheable is boolean (defualt: true)
     * @param SERVLET_NAME is servlet name (defualt: 'Server')
     * @example jj("username=milad&password=123").jjAjax2(); 
     * jj("username=milad&password=123").jjAjax2(false); 
     * jj("username=milad&password=123").jjAjax2(false,'../Server'); 
     */
    this.jjAjax2 = function (isCacheable,servletName) {
        if (sessionStorage.getItem("is_reloaded")) alert('Reloaded!');
        var newURL= "Server2?" + this.selector;
        $.ajax( {
            url:(servletName==null) ? SERVLET_NAME : servletName,
            type:"POST",
            cache: isCacheable==null?true:isCacheable ,
            data: this.selector, 
            beforeSend: function () {
                if(newURL.indexOf("&panel=sw")>0){ //can add=> ||newURL.indexOf("&panel=swRight&")
                    stack[window.location]=$('#maincontent').html();
                    var newTitle=newURL.replace(/Server2.*&text=/, '');
                    newTitle=newTitle.replace(/&panel=.*jj=1/, "")
                    document.title=newTitle;
                } 
            }, 
            complete: function () {
                $('html, body').css("cursor", "auto"); 
            },
            success: function (data){
                new jj(data).jjRun();
                $('html, body').css("cursor", "auto");                
                if(newURL.indexOf("&panel=sw")>0){//( if "&panel=sw" is not in url it not worked)
                } 
            },
            error: function () {
                $('html, body').css("cursor", "auto"); 
                if(LANGUAGE=="fa"){
                    new jj("در انجام عملیات مشکلی پیش آمده است.").jjDialog();
                }else{
                    new jj("accur a problem in opration").jjDialog();
                }
            }
        });    
    }
    
    /**
     * return key=value from selector
     * selector is form component selector
     * @param key is String
     * @example jj("#userName").jjVal(); return =>  "Milad"
     * jj("#userName").jjVal("milad"); set "Milad" to "userName" field
     */
    this.jjVal = function (value) {
        if(value==null){
            if($(this.selector).attr('type') == 'checkbox' ){
                return (($(this.selector).is(':checked')?1:0));
            }else if($(this.selector).attr('type') == 'radio' ){
                return (($(this.selector).is(':checked')?1:0));
            }else{
                var val = encodeURIComponent($(this.selector).val());
                //                var val = $(this.selector).val();
                return (new jj(new jj(val).jjTrim()).jjGetUtf8());
            }
        }else{
            if($(this.selector).attr("type")=="checkbox"){
                $(this.selector).attr("checked",(value=="1" || value=="true"));
            }else{
                //                while(value.indexOf('^star^')>-1){
                //                    value = value.replace('^star^', '/**');
                //                }
                $(this.selector).val(value);
            }
        }
    },
    //    this.jjVal = function  (value) {
    //        if(value==null){
    //            if($(this.selector).attr('type') == 'checkbox' ){
    //                return (($(this.selector).is(':checked')?1:0));
    //            }else if($(this.selector).attr('type') == 'radio' ){
    //                return (($(this.selector).is(':checked')?1:0));
    //            }else{
    //                //                var val = encodeURIComponent($(this.selector).val());
    //                var val = $(this.selector).val();
    //                return (new jj(new jj(val).jjTrim()).jjGetUtf8());
    //            }
    //        }else{
    //            if($(this.selector).attr("type")=="checkbox"){
    //                $(this.selector).attr("checked",(value=="1" || value=="true"));
    //            }else{
    //                while(value.indexOf('^star^')>-1){
    //                    value = value.replace('^star^', '/**');
    //                }
    //                $(this.selector).val(value);
    //            }
    //        }
    //    }
    
    /**
     * selector is editor component
     * @param value is object
     * @example jj("#name").jjSetEditorValue("Milad");
     */
    this.jjEditorVal = function  (value) {
        if(value==null){
            //            var val = this.selector.getData();
            //            var val = this.selector.getData();
            var val = encodeURIComponent(this.selector.getData());
            val = (new jj(new jj(val).jjTrim()).jjGetUtf8());
            return val;
        }else{
            this.selector.setData(value);
            if(value==""){
                this.selector.setData("<p style='font-family:Tahoma;font-size:10pt'></p>");
            }
        }
    }
    //    this.jjEditorVal = function  (value) {
    //        if(value==null){
    //            var val = this.selector.getData();
    //            var val = this.selector.getData();
    //            //            var val = encodeURIComponent(this.selector.getData());
    //            val = (new jj(new jj(val).jjTrim()).jjGetUtf8());
    //            return val;
    //        }else{
    //            this.selector.setData(value);
    //            if(value==""){
    //                this.selector.setData("<p style='font-family:Tahoma;font-size:10pt'></p>");
    //            }
    //        }
    //    }
    
    this.jjFormatNumber = function (){
        var src = this.selector+"";
        if(src.length<4){
            return src;
        } else if(src.length==4){
            return src.substring(0, 1)+","+src.substring(1, 4);
        } else if(src.length==5){
            return src.substring(0, 2)+","+src.substring(2, 5);
        } else if(src.length==6){
            return src.substring(0, 3)+","+src.substring(3, 6);
        } else if(src.length==7){
            return src.substring(0, 1)+","+src.substring(1, 4)+","+src.substring(4, 7);
        } else if(src.length==8){
            return src.substring(0, 2)+","+src.substring(2, 5)+","+src.substring(5, 8);
        } else if(src.length==9){
            return src.substring(0, 3)+","+src.substring(3, 6)+","+src.substring(6, 9);
        } else if(src.length==10){
            return src.substring(0, 1)+","+src.substring(1, 4)+","+src.substring(4, 7)+","+src.substring(7, 10);
        } else if(src.length==11){
            return src.substring(0, 2)+","+src.substring(2, 5)+","+src.substring(5, 8)+","+src.substring(8, 11);
        } else if(src.length==12){
            return src.substring(0, 3)+","+src.substring(3, 6)+","+src.substring(6, 9)+","+src.substring(9, 12);
        }        
    },
    /**
     * This method replace some charachter to other <br>
     * @param selector is String
     * @return convert selector to codec string
     */
    this.jjGetUtf8 = function (){
        var src = this.selector;
        src = (src==null?"":src);
        var replacor = "^*#~";
        // '
        while(src.indexOf("\\'")>-1){
            src = src.replace("\\'",replacor);
        }
        while(src.indexOf("\'")>-1){
            src = src.replace("\'",replacor);
        }
        while(src.indexOf("'")>-1){
            src =  src.replace("'",replacor);
        }
        while(src.indexOf(replacor)>-1){
            src =  src.replace(replacor,"\'");
        }
        // "
        while(src.indexOf('\\"')>-1){
            src = src.replace('\\"',replacor);
        }
        while(src.indexOf('\"')>-1){
            src = src.replace('\"',replacor);
        }
        while(src.indexOf('"')>-1){
            src =  src.replace('"',replacor);
        }
        while(src.indexOf(replacor)>-1){
            src =  src.replace(replacor,"\"");
        }
        // \n
        while(src.indexOf("\n", 0)>-1){
            src =  src.replace("\n","");
        }
        while(src.indexOf('%3C')>-1){
            src = src.replace('%3C','<');
        }
        while(src.indexOf('%3E')>-1){
            src = src.replace('%3E','>' );
        }
        while(src.indexOf('%2F')>-1){
            src = src.replace('%2F','/');
        }
        while(src.indexOf('%0A')>-1){
            src = src.replace('%0A', '');
        }
        while(src.indexOf('%09')>-1){
            src = src.replace('%09', '');
        }
        while(src.indexOf('%0D')>-1){
            src = src.replace('%0D', '<br/>');
        }
        return (src);
    }
    this.serialVaiable = "";
    /**
     * return key=value from selector
     * selector is form component selector
     * @param key is String
     * @example jj("#form1").jjSerial();
     * jj("#div1").jjSerial("milad");
     */
    this.jjSerial = function  () {
        var param = "";
        var com_checkbox  = $(this.selector+ " input:checkbox");
        var com_number  = $(this.selector+ " input:[type='number']");
        var com_hidden  = $(this.selector+ " input:hidden");
        var com_text  = $(this.selector+ " input:text");
        var com_radio  = $(this.selector+ " input:radio");
        var com_password  = $(this.selector+ " input:password");
        var com_select  = $(this.selector+ " select");
        var com_textarea  = $(this.selector+ " textarea");
        
        for(var i = 0 ; i < com_hidden.length;i++){
            if(com_hidden[i].name!=""){
                param += (param==""?"":"&")+ com_hidden[i].name + "=" + jj("#" + com_hidden[i].id).jjVal();
            }
        }
        for(var i = 0 ; i < com_text.length;i++){
            if(com_text[i].name!=""){
                param += (param==""?"":"&")+ com_text[i].name + "=" + jj("#"+ com_text[i].id).jjVal();
            }
        }
        for(var i = 0 ; i < com_checkbox.length;i++){
            if(com_checkbox[i].name!=""){
                param += (param==""?"":"&")+ com_checkbox[i].name + "=" + jj("#"+ com_checkbox[i].id).jjVal();
            }
        }
        for(var i = 0 ; i < com_number.length;i++){
            if(com_number[i].name!=""){
                param += (param==""?"":"&")+ com_number[i].name + "=" + jj("#"+ com_number[i].id).jjVal();
            }
        }
        for(var i = 0 ; i < com_radio.length;i++){
            if(com_radio[i].name!=""){
                if(jj("#"+ com_radio[i].id).jjVal()==1){
                    param += (param==""?"":"&")+ com_radio[i].name + "=" +  com_radio[i].id;
                }
            }
        }
        for(var i = 0 ; i < com_select.length;i++){
            if(com_select[i].name!=""){
                param += (param==""?"":"&")+ com_select[i].name + "=" + jj("#"+ com_select[i].id).jjVal();
            }
        }
        for(var i = 0 ; i < com_password.length;i++){
            if(com_password[i].name!=""){
                param += (param==""?"":"&")+ com_password[i].name + "=" + jj("#"+com_password[i].id).jjVal();
            }
        }
        for(var i = 0 ; i < com_textarea.length;i++){
            if(com_textarea[i].name!=""){
                //                alert(com_textarea[i].style.visibility);
                if(com_textarea[i].style.visibility!="hidden"){
                    param += (param==""?"":"&")+ com_textarea[i].name + "=" + jj("#"+com_textarea[i].id).jjVal();
                }else{
                    param += (param==""?"":"&")+ com_textarea[i].name + "=" ;
                    new jj('serialVaiable=(jj(CKEDITOR.instances.'+com_textarea[i].id+').jjEditorVal())').jjRun()+"-";
                    param+= serialVaiable;
                }
            }
        }
        return param;
    }
    this.jjFormClean = function  () {
        var com_checkbox  = $(this.selector+ " input:checkbox");
        var com_text  = $(this.selector+ " input:text");
        var com_radio  = $(this.selector+ " input:radio");
        var com_password  = $(this.selector+ " input:password");
        var com_file  = $(this.selector+ " input:file");
        var com_select  = $(this.selector+ " select");
        var com_textarea  = $(this.selector+ " textarea");
        
        for(var i = 0 ; i < com_text.length;i++){
            if(com_text[i].name!=""){
                jj("#" + com_text[i].id).jjVal('');
            }
        }
        for(var i = 0 ; i < com_checkbox.length;i++){
            if(com_checkbox[i].name!=""){
                jj("#" + com_checkbox[i].id).jjVal('');
            }
        }
        for(var i = 0 ; i < com_radio.length;i++){
            if(com_radio[i].name!=""){
                jj("#" + com_radio[i].id).jjVal('');
            }
        }
        for(var i = 0 ; i < com_select.length;i++){
            if(com_select[i].name!=""){
            //                $("#" + com_select[i].id + " option:first").attr('selected','selected'); 
            }
        }
        for(var i = 0 ; i < com_password.length;i++){
            if(com_password[i].name!=""){
                jj("#" + com_password[i].id).jjVal('');
            }
        }
        for(var i = 0 ; i < com_textarea.length;i++){
            if(com_textarea[i].name!=""){
                jj("#" + com_textarea[i].id).jjVal('');
            }
        }
        for(var i = 0 ; i < com_file.length;i++){
            if(com_file[i].name!=""){
                jj("#" + com_file[i].id).jjVal('');
            }
        }
    }
    
    /**
     * selector is String
     * @example jj(" text ").jjTrim(); return => "text"
     */
    this.jjTrim = function (){
        this.selector = $.trim(this.selector);
        var l = 0;
        var r = this.selector.length -1;
        var m = 0;
        var n = this.selector.length -1;
        while(l < this.selector.length && [l] == '+'){
            l++;
        }
        while(r > l && this.selector[r] == '+'){
            r-=1;
        }
        while(m < this.selector.length && [m] == ' '){
            m++;
        }
        while(n > m && this.selector[n] == ' '){
            n -= 1;
        }
        return this.selector.substring(m, n + 1);
    }
    /**
     * @param selector is String or url
     * @param value is String
     * @return value of key (if not exist return null)
     * @example jj(windows.location.toString()).jjGetParam('tbl');
     */
    this.jjGetParam = function (key) {
        if(this.selector.indexOf("?")>0){
            var param = this.selector.substring( this.selector.indexOf("?")+1, this.selector.length);
            if(param.indexOf('&')>-1){
                var allparam = param.split('&');
                for(var i = 0; i < allparam.length;i++){
                    if(allparam[i].indexOf('=')>-1){
                        var keyValue = allparam[i].split('=');
                        if(key == keyValue[0]){
                            return keyValue[1]
                        };
                    }
                }
            }else{
                if(param.indexOf('=')>-1){
                    return param.split('=')[1];
                }
            }
        }
        return null;
    }
    
    /**
     * <br/>
     * @param selector is Url for change location
     */
    this.jjGo = function () {
        window.location.replace(this.selector);
    };
    /**
     * <br/>
     * @param selector is Url for change location
     */
    this.jjGoNewPage = function () {
        window.open(this.selector,'PAGE','menubar=yes,location=yes,resizable=yes,scrollbars=yes,status=yes');
    }
    /**
     * <br/>
     * @param selector is String
     * @param prefix is String
     * @return boolean : return true if selector start width prefix 
     */
    this.jjIsStartWith = function (prefix) {
        if(this.selector.length<=prefix.length){
            return false;
        }
        if(this.selector.substr(0, prefix.length) == prefix){
            return true;
        }
        return false;
    }
    
    /**
     * This method return true if selector endWidth suffix <br>
     * @param selector is String 
     * @param suffix is String 
     * @example jj("abcdef").jjIsEndsWith("f"); => return true
     */
    this.jjIsEndsWith = function(suffix) {
        return this.selector.indexOf(suffix, this.selector - suffix.length) !== -1;
    };
    
    /**
     * return true if selector is number<br/>
     * @param selector is string<br/>
     * @return return true if selector is contain text parameter<br/>
     * @example jj("abcdef").jjIsContain("cde"); => return true
     * jj("abcdef").jjIsContain("ced"); => return false
     */   
    this.jjIsContain = function (text){
        return(this.selector.indexOf(text)!=-1)
    }
    
    
    
    /**
     * This method radious corner of div <br/>
     * you must import 
     *  <br/> src="js/jquery/jquery.corner.js" <br/>
     * sll sample is in http://jquery.malsup.com/corner/
     * @param selector is DIV tag selector
     * @param color is String (default value = white)
     * @param radiousSize is int (default value = 15px)
     * @example jj('divId').jjSetCornerRadius();
     * jj('divId').jjSetCornerRadius('FFF');
     * jj('divId').jjSetCornerRadius('FFF',50);
     */
    this.jjSetCornerRadius = function (radiousSize,color){
        color =(color==null)?000:color;
        radiousSize =(radiousSize==null)?15:radiousSize;
        $(this.selector).corner('cc:'+color+' '+radiousSize+'px');
    }
    
    
    
    /**
     * @param selector is div selector,
     * @param fontColor is color (default: #ffffff),
     * @example  jj("#WeatherDiv").jjSetWeather();
     */
    this.jjSetWeather = function (fontColor){
        $(this.selector).weatherfeed(['IRXX0003'],
        {
            unit: 'c',
            image: true,
            highlow: true,
            wind: true,
            link: false,
            showerror: true
        });
        $(this.selector).css('font-family','Tahoma');
        $(this.selector).css('font-size','10pt');
        $(this.selector).css('direction','rtl');
        $(this.selector).css('color',fontColor==null?"#ffffff":fontColor);
    }
    /**
     * This method run one javascript statement or method <br/>
     * @param selector is javascript,
     * @example jj("alert('llll');").run();
     */
    this.jjRun = function (){
        try{            
            eval(this.selector);
        }catch(e){
            new jj(this.selector).jjDialog();
        }
    }
    
    /**
     * This method copy text to clipboard <br/>
     * @param selector is text,
     * @example jj("milad-jamalzade").jjCopyToClipboard();
     */
    this.jjCopyToClipboard = function() {     //for IE ONLY!  
        if (window.clipboardData) {
            window.clipboardData.setData('Text',this.selector);
            return true;
        } else {        
            var clipboarddiv = document.getElementById('divclipboardswf');         
            if (!clipboarddiv) {
                clipboarddiv = document.createElement('div');
                clipboarddiv.setAttribute("name", "divclipboardswf");
                clipboarddiv.setAttribute("id", "divclipboardswf");
                document.body.appendChild(clipboarddiv);
            }
            clipboarddiv.innerHTML = '<embed src="clipboard.swf" FlashVars="clipboard=' 
            + this.selector + '" width="0" height="0" type="application/x-shockwave-flash"></embed>';
        //            clipboarddiv.innerHTML = '<embed src="clipboard.swf" FlashVars="clipboard=' 
        //            + encodeURIComponent(this.selector) + '" width="0" height="0" type="application/x-shockwave-flash"></embed>';
        } 
        return false;
    }
    
    
    /**
     * This method get clipboard <br>
     * @param selector is text,
     * @example jj().jjGetClipboard();
     */
    this.jjGetClipboard = function() {     //for IE ONLY!  
        if (window.clipboardData) {
            return window.clipboardData.getData();
        } 
        return null;
    }
    
    /**
     * This method convert String to int <br/>
     * @param string, is String for convert to int
     * @return int,
     * @example jj("421.56").jjConvertToInt();
     */
    this.jjConvertToInt = function () {
        return window.parseFloat(this.selector,10);
    }
    
    /**
     * This method load one JS file and run method after that <br/>
     * @param jsPath is String, for load
     */
    this.jjLoadJS = function () {
        $.getScript(this.selector, function() {
            
            }); 
    }
    
    /**
     * This method load refresh page every "timeForRefresh" <br/>
     * @param selector is second count
     * @example  jj(1000).jjPageRefreshTimer();
     */
    this.jjPageRefreshTimer = function () {
        window.setTimeout('location.reload()', this.convertToInt()*1000); 
    }
    
    /**
     * This method sum 2 time together <br/>
     * @param selector is time1, 
     * @param time2 is time2,
     * @return String time, 
     * @example jj("4:30").jjSumTime("5:10");
     */
    this.jjSumTime = function (time2) {
        var t1 = this.selector.split(":"); 
        var t2 = time2.split(":"); 
        var hourSum = window.parseInt(t1[0],10) +window.parseInt(t2[0],10);
        var minSum = window.parseInt(t1[1],10) +window.parseInt(t2[1],10);
        var t1min = (minSum % 60);
        var nhmin2 = (minSum / 60)+"";
        var dotindex2 = nhmin2.indexOf(".", 0);
        if(dotindex2>-1){
            nhmin2 = nhmin2.substr(0,dotindex2 );
        }
        hourSum +=window.parseInt(nhmin2,10);
        return ((hourSum<10?"0"+hourSum:hourSum)+":"+(t1min<10?"0"+t1min:t1min)) ;
    }
    
    /**
     * This method use JQuery Calendar component <br/>
     * you must import:
     * <br/> src="js/calendar/jquery.ui.datepicker-cc.js"
     * <br/> src="js/calendar/jquery.ui.datepicker-cc-fa.js"
     * <br/> src="js/calendar/jquery.ui.datepicker-cc.js"
     * <br/> src="js/calendar/calendar.all.js"
     * <br/> src="js/calendar/calendar.js"
     * @param selector is textfield selector; <br/>
     * @example jj("#textfieldTagId").jjCalendar(); 
     */
    this.jjCalendar = function  () {
        $(this.selector).css('text-align','left');
        //        $(this.selector).keypress(function (event) {
        //            event.returnValue = false;
        //            return false;
        //        });
        $(this.selector).datepicker(
        // --------------------------------------------------------------- //
        // { // Show number of week
        //   showWeek: true 
        // }
        // --------------------------------------------------------------- //
        { // Format:
            dateFormat: 'yy/mm/dd' // or "dd/mm/yy" or "d MM yy" or "DD، d MM yy" or  "'ruz' d MM 'saal' yy"
        }
        // --------------------------------------------------------------- //
        // { // Fill other field
        //   altField: '#datepicker2',
        //   altFormat: "'ruz' d MM 'saal' yy"
        // }
        // --------------------------------------------------------------- //
        // { // Show Button's
        //  showButtonPanel: true
        // }
        // --------------------------------------------------------------- //
        // { // Change to needed size for textfield for show all data 
        //   autoSize: true
        // }
        // --------------------------------------------------------------- //
        // { // Use dropdown for year or month
        //   changeMonth: true,
        //	 changeYear: true,
        //    yearRange: "1380:1390"
        // }
        // --------------------------------------------------------------- //
        // {  // Show calendar by click on one photo
        //   showOn: 'button',
        //	 buttonImage: 'styles/images/calendar.png',
        //	 buttonImageOnly: true
        // }
        // --------------------------------------------------------------- //
        // { Show 3 Months together
        //    numberOfMonths: 3,
        //	  showButtonPanel: true
        // }
        // --------------------------------------------------------------- //
        // { // disable some of day's
        //    beforeShowDay: function(date) {
        //      if (date.getDay() == 5)
        //       return [false, '', 'this is Holiday'];
        //       return [true];
        //    }
        //}
        // --------------------------------------------------------------- //
        // { // Set default Date
        //   defaultDate: new JalaliDate(1361, 4, 10)
        // }
        // --------------------------------------------------------------- //
        // { // Set min date and max date
        //   minDate: '-3d',
        //	 maxDate: '+1w +2d'
        // }
        // --------------------------------------------------------------- //
        // { // Set mid date by other calendar component
        //    onSelect: function(dateText, inst) 
        //    {
        //	  $('#datepicker2').datepicker('option', 'minDate', new JalaliDate(inst['selectedYear']
        //	  , inst['selectedMonth'], inst['selectedDay']));
        //	  }
        // }
        // --------------------------------------------------------------- //
        // { // Use Miladi calendar
        //     regional: ''
        // }
        // --------------------------------------------------------------- //
        // { // Use Ghamari calendar
        //  // <script type="text/javascript" src="plugin/calendar/js/jquery.ui.datepicker-cc-ar.js"></script>
        //    regional: 'ar'
        // }
        // --------------------------------------------------------------- //
        );
    }
    
    /**
     * This method use JQuery Calendar component <br/>
     * you must import:
     * <br/> src="js/calendar/jquery.ui.datepicker-cc.js"
     * <br/> src="js/calendar/jquery.ui.datepicker-cc-fa.js"
     * <br/> src="js/calendar/jquery.ui.datepicker-cc.js"
     * <br/> src="js/calendar/calendar.all.js"
     * <br/> src="js/calendar/calendar.js"
     * @param selector is textfield String,
     * @example jj("#textfieldTagId").jjSetCalendarTextFieldWithYearSelector(1350,1400);
     */
    this.jjCalendarWithYearSelector = function  (minYear,maxYear) {
        $(this.selector).css('text-align','left');
        $(this.selector).datepicker(
        { // Format:
            dateFormat: 'yy/mm/dd', // or "dd/mm/yy" or "d MM yy" or "DD، d MM yy" or  "'ruz' d MM 'saal' yy"
            changeYear: true,
            yearRange: minYear+":"+maxYear
        }
        );
    //        $(this.selector).keypress(function (event) {
    //            event.returnValue = false;
    //            return false;
    //        });
    }
    
    this.jjSetTree = function (tree) {
        $(this.selector).html("<a href='?#'>Close</a> | <a href='?#'>Open</a>"
            + "<ul id=\"tree\" style=\"text-align: left\" dir=\"rtl\">"
            + tree
            //            + "<li><a>index</a>"
            //            + " <ul>"
            //            + "       <li><a>A</a>"
            //            + "             <ul>"
            //            + "                     <li><a>A2</a>"
            //            + "                     </li>"
            //            + "             </ul>"
            //            + "       </li>"
            //            + " </ul>"
            //            + "</li>"
            + "</ul>"
            ).treeview({
            collapsed: false,
            animated: "medium", 
            control: this.selector, 
            persist: "location"
        });
    }
    /**
     * This method use for miror effect <br/>
     * you must import 
     * <br/> src="js/jquery/reflection.js"
     * @param selector is pic Tag selector,
     * @param height is int (1-100) (default: 20)
     * @param opacity is int (1-100) (default: 60)
     * @example jj("#imageId").jjSetMiror(); 
     * jj("#imageId").jjSetMiror(20,60); 
     */
    this.jjSetMiror = function (height,opacity) {
        height = height == null ? 20 : height;
        opacity = opacity == null ? 60 : opacity;
        if($(this.selector).attr('class')!=null){
            if($(this.selector).attr('class').indexOf('reflect')<0){
                $(this.selector).addClass("reflect rheight"+height+ " ropacity"+opacity);
            }
        }else{
            $(this.selector).attr("class","reflect rheight"+height+ " ropacity"+opacity);
        }
        if($(this.selector).attr('width')==null){
            if($(this.selector).css('width')!=null){
                $(this.selector).attr('width',$(this.selector).css('width'));                
            }
        }else{
            if($(this.selector).css('width')==null){
                $(this.selector).css('width',$(this.selector).attr('width'));     
            }
        }
        if($(this.selector).attr('height')==null){
            if($(this.selector).css('height')!=null){
                $(this.selector).attr('height',$(this.selector).css('height'));                
            }
        }else{
            if($(this.selector).css('height')==null){
                $(this.selector).css('height',$(this.selector).attr('height'));     
            }
        }
    }
    
    
    /**
     * This method use FCK Editor to your textArea <br/>
     * you must import  
     * <br/> src="../js/jquery/jquery-1.7.2.min.js"
     * <br/> src="../js/jquery/jquery-ui-1.8.21.custom.min.js" 
     * <br/> src="../js/jj2.js" 
     * <br/> src="../js/editor/ckeditor.js" <br/>
     * @return Editor
     * @param selector is text Area Tag Id
     * @param configJsFilePath is String, and is path of js file for config CK_Editor (like: "js/myEditorConfig.js")
     * @example jj("#textAreaId").jjEditor("myEditorConfig.js"); 
     * ("#textAreaId").jjEditor(); 
     */
    this.jjEditor = function  (configJsFilePath) {
        //        window.CKEDITOR_BASEPATH = 'js/editor';
        var editor =  CKEDITOR.replace(this.selector.replace("#", ""),{
            customConfig: configJsFilePath==null ? "myEditorConfig.js" : configJsFilePath
        } );
        CKEDITOR.on( 'dialogDefinition', function( ev )
        {
            // Take the dialog name and its definition from the event
            // data.
            var dialogName = ev.data.name;
            var dialogDefinition = ev.data.definition;
            
            // Check if the definition is from the dialog we're
            // interested on (the "Link" dialog).
            if ( dialogName == 'link' )
            {
                // Get a reference to the "Link Info" tab.
                var infoTab = dialogDefinition.getContents( 'info' );
                
                
                // Remove the "Link Type" combo and the "Browser
                // Server" button from the "info" tab.
                infoTab.remove( 'linkType' );
                infoTab.remove( 'browse' );
                
                // Set the default value for the URL field.
                var urlField = infoTab.get( 'url' );
                urlField['default'] = 'www.example.com';
                
                // Remove the "Target" tab from the "Link" dialog.
                dialogDefinition.removeContents( 'target' );
                
                // Rewrite the 'onFocus' handler to always focus 'url' field.
                dialogDefinition.onFocus = function()
                {
                    var urlField = this.getContentElement( 'info', 'url' );
                    urlField.select();
                };
            }
        });
        
        
        // Listen for the "pluginsLoaded" event, so we are sure that the
        // "dialog" plugin has been loaded and we are able to do our
        // customizations.
        editor.on( 'pluginsLoaded', function( ev )
        {
            // If our custom dialog has not been registered, do that now.
            if ( !CKEDITOR.dialog.exists( 'myDialog' ) )
            {
                // We need to do the following trick to find out the dialog
                // definition file URL path. In the real world, you would simply
                // point to an absolute path directly, like "/mydir/mydialog.js".
                var href = document.location.href.split( '/' );
                href.pop();
                CKEDITOR.dialog.add( 'myDialog', function( editor )
                {
                    return {
                        title : 'ارسال فایل به سرور',
                        minWidth : 400,
                        minHeight : 100,
                        onOk:function(){
                            editor.setData(editor.getData()+"<img src='upload/koala_small.jpg'/>");
                        },  
                        onLoad:function(){
                            this.commitContent();
                        },
                        onHide:function(){
                            if(CKEDITOR.env.ie)this.getParentEditor().document.getBody().$.contentEditable='true';
                        },
                        contents : [
                        {
                            id : 'tab1',
                            label : 'First Tab',
                            title : 'First Tab',
                            elements :[
                            {
                                id : 'input1',
                                type : 'file',
                                label : 'انتخاب فایل',
                                width: '50%'
                            }, {
                                id : 'input2',
                                name : 'input2',
                                type : 'text',
                                label : 'نام فایل',
                                width: '50%'
                            }, {
                                id : 'input3',
                                type : 'button',
                                label : 'ارسال',
                                onClick:function(){
                                    alert(this.getDialog().getValueOf('tab1','input1'));
                                //                                    this.getDialog().setValueOf('tab1','input2', 'aaaaaaaaa');
                                //                                    this.getContentElement('tab1','input2').setValue('aaaaaaaaa');
                                }
                            }
                            ]
                        }
                        ]
                    };
                } );
                href = href.join( '/' );
                
                // Finally, register the dialog.
                CKEDITOR.dialog.add( 'myDialog', href );
            }
            
            // Register the command used to open the dialog.
            editor.addCommand( 'myDialogCmd', new CKEDITOR.dialogCommand( 'myDialog' ) );
            
            // Add the a custom toolbar buttons, which fires the above
            // command..
            editor.ui.addButton( 'MyButton',
            {
                label : 'آپلود',
                command : 'myDialogCmd'
            } );
        });
        return editor;
    }
    
    /**
     * This method use google map <br/>
     * you must import src="http://maps.google.com/maps/api/js?sensor=true"
     * @param selector is tag selector,
     * @param ZoomSize is int,
     * @param X is int
     * @param Y is int,
     * @param Title is String,
     * @example jj("#tagId").jjSetGoogleMap(15,32.624141,51.625166,'Hotel Aseman'); 
     */
    this.jjSetGoogleMap = function (ZoomSize,X,Y,Title) { 
        try {
            // in this site
            // http://maps.google.com
            // you must right click and click on "what's heare"
            var latlng = new google.maps.LatLng(X,Y); 
            var myOptions = { 
                zoom: ZoomSize, 
                center: latlng, 
                mapTypeId: google.maps.MapTypeId.ROADMAP ,
                mapTypeControl: true, 
                mapTypeControlOptions: {
                    style: google.maps.MapTypeControlStyle.DROPDOWN_MENU
                },
                navigationControl: true, 
                navigationControlOptions: { 
                    style: google.maps.NavigationControlStyle.ZOOM_PAN  
                } 
            }; 
            //            alert($("#"+tagID).html());
            var Map = new google.maps.Map(document.getElementById(this.selector.replace("#","")), myOptions); 
            new google.maps.Marker({ 
                position: latlng,  
                map: Map,  
                title:Title 
            });
        } catch (e) {
            alert(e);
        }
    } 
    
    /**
     * This method set mouse cursor like hand, in mouse over for tagId  <br/>
     * @param tagId is String, one or more than one tagID<br/>
     * @sample: jj().jjSetMouseOverHandCursor("#linkId1","#linkId2","#linkId3");
     */
    this.jjSetMouseOverHandCursor =  function  () {
        $(this.selector).css("cursor","pointer");
        for(i=0; i < arguments.length; i++){  
            $(arguments[i]).css("cursor","pointer");
        }  
    }
    
    /**
     * This method get mouse location (X,Y) <br/>
     * @param selector is tag selector, one or more than one tag selector<br/>
     * @example $("body").mousemove(function(MouseEvent){
     * var mousePoint = jj("").getMouseLocation(MouseEvent);
     * var left = mousePoint.x + "px";
     * var top = mousePoint.y + "px";
     * alert(left + " - " + top );
     * });
     */ 
    this.jjGetMouseLocation = function (MouseEvent) {
        if (!MouseEvent) {
            MouseEvent = event
        };
        var x = -1;
        var y = -1;
        if (MouseEvent.x) {
            x = MouseEvent.x;
            y = MouseEvent.y;
        } else if (MouseEvent.clientX) {
            x = MouseEvent.clientX;
            y = MouseEvent.clientY;
        } else if (MouseEvent.pageX) {
            x = MouseEvent.pageX;
            y = MouseEvent.pageY;
        }
        var res = new Object();
        res.x = x;
        res.y = y;
        return res;
    }
    
    //    this.showWaitGifOnMouseDefault = function (gifAddress) {
    //        this.showWaitGifOnMouse(gifAddress , 32);
    //    }
    //    this.divTooltip = null;
    //    
    //    /**
    //     * This method add one photo file near the mouse <br/>
    //     * @param imgSrc is String, this is src of image<br/>
    //     * @param size is int, this is size for width and height<br/>
    //     * @sample: <br/>
    //     * jj('').showWaitGifOnMouse("img/wait4.gif" , 32);<br/>
    //     */
    //    this.showWaitGifOnMouse = function (gifAddress, size) {
    //        if(this.divTooltip==null){
    //            $("p").last().after("<div id=\"tooltip_div_Mouse123321\" style=\"display: 'none'\"><img src=\"" + gifAddress + "\" width=\"" + size + "px\" /></div>");
    //            this.divTooltip = $("#tooltip_div_Mouse123321");
    //            this.divTooltip.css("background-color","#ffffff" );
    //            this.divTooltip.css("position","absolute").css("z-index","1001").css("filter","alpha(opacity=90)").css("opacity","0.9");
    //        }
    //        $("body").mousemove(function(e){ 
    //            var mousePos = this.getMouseLocation(e);
    //            this.divTooltip  =$("#tooltip_div_Mouse123321");
    //            this.divTooltip.css("left", (mousePos.x + 8) + "px");
    //            this.divTooltip.css("top",(mousePos.y + 170) + "px");
    //            this.divTooltip.css("display","" );
    //        });     
    //    
    //    }
    
    /**
     * This method create one key-value in one cookie <br/>
     * @param selector key is String,<br/>
     * @param value is int, <br/>
     * @example jj("email").jjCookieSave("Milad.jamalzadeh@yahoo.com");
     */
    this.jjCookieSave = function (value) {
        date = new Date();
        document.cookie = this.selector + "=" + escape(value) + "; expires=Fri, 31 Dec 2090 23:59:59 GMT;";
    }
    
    /**
     * This method delete  <br/>
     * @param selector key is String, <br/>
     * @example jj("email").jjCookieDelete();
     */
    this.jjCookieDelete = function () {
        document.cookie = this.selector + "=; expires=Fri, 21 Dec 1976 04:31:24 GMT;";
    }
    
    /**
     * This method get value of this cookie <br/>
     * @param selector is key is String, <br/>
     * @example jj("email").jjCookieGet();
     */
    this.jjCookieGet = function () {
        // cookies are separated by semicolons
        var aCookie = document.cookie.split("; expires")[0];
        var bCookie = aCookie.split("; ");
        for (var i = 0; i < bCookie.length; i++) {
            // a name/value pair (a crumb) is separated by an equal sign
            var src =  bCookie[i].split("=");
            if(src.length>0){
                if (this.selector == src[0]){
                    if(src[1]==null){
                        return "";
                    }
                    var s = src[1];
                    while(s.indexOf("%u")>-1  || s.indexOf("%20", 0)>-1 ){
                        s=s.replace("%u0627", "ا").replace("%u0628", "ب").replace("%u067E", "پ")
                        .replace("%u0622", "آ").replace("%u0621", "ء").replace("%u0623", "أ").replace("%u0625", "إ")
                        .replace("%u0624", "ؤ").replace("%u064A", "ي").replace("%u0629", "ة").replace("%u0640", "ـ")
                        .replace("%u062A", "ت").replace("%u062B", "ث").replace("%u062C", "ج").replace("%u0686", "چ")
                        .replace("%u062D", "ح").replace("%u062E", "خ").replace("%u062F", "د").replace("%u0630", "ذ")
                        .replace("%u0631", "ر").replace("%u0632", "ز").replace("%u0698", "ژ").replace("%u0633", "س")
                        .replace("%u0634", "ش").replace("%u0635", "ص").replace("%u0636", "ض").replace("%u0637", "ط")
                        .replace("%u0638", "ظ").replace("%u0639", "ع").replace("%u063A", "غ").replace("%u0641", "ف")
                        .replace("%u0642", "ق").replace("%u06A9", "ک").replace("%u06AF", "گ").replace("%u0644", "ل")
                        .replace("%u0645", "م").replace("%u0646", "ن").replace("%u0648", "و").replace("%u0647", "ه")
                        .replace("%u06CC", "ی").replace("%20", " ");
                    }
                    return s;
                }
            }
        }
        // a cookie with the requested name does not exist
        return "";
    }
    
    /**
     * This method don't lets to user write string on TextField <br>
     * @param selector is TextFeildTagID selector; <br/>
     * @param maxLength is int (default: 10) <br/>
     */
    this.jjSetMaxLength = function  (maxLength) {
        maxLength = maxLength==null ? 10 : maxLength-1;
        var selector = this.selector;
        $(this.selector).keypress(function (event) {
            var keynum;
            if(window.event){ // IE
                keynum = event.keyCode
            } else if(event.which){ // Netscape/Firefox/Opera
                keynum = event.which
            }
            if(new jj(selector).jjVal().toString().length > maxLength){
                if(keynum == undefined){
                    event.returnValue = true;
                    return true;
                }else{
                    if(keynum==8){
                        event.returnValue = true;
                        return true;
                    }else{
                        event.returnValue = false;
                        return false;
                    }
                }
            }
        })
    },
    
    /**
     * This method get only number in the textfield <br/>
     * @param selector is TextFeildTagID selector; <br/>
     * @param maxLength is int (default: 10) <br/>
     * @example jj("#TextFeildTagID").setTextFieldOnlyGetNumber();
     */
    this.jjSetTextFieldOnlyGetNumber = function  (maxLength) {
        maxLength = maxLength==null ? 10 : maxLength - 1;
        var selector =this.selector;
        $(this.selector).keypress(function (event) {
            var keynum;
            if(window.event){ // IE
                keynum = event.keyCode
            } else if(event.which){ // Netscape/Firefox/Opera
                keynum = event.which
            }
            if(new jj(selector).jjVal().toString().length > maxLength){
                if(keynum == undefined){
                    event.returnValue = true;
                    return true;
                }else{
                    if(keynum==8){
                        event.returnValue = true;
                        return true;
                    }else{
                        event.returnValue = false;
                        return false;
                    }
                }
            }
            if (keynum  < 48 || keynum  > 57 ) {//|| keynum==8 || keynum!=undefined
                if (event.preventDefault && keynum!=8){
                    event.preventDefault();
                } else{
                    event.returnValue = false;
                }
            }
        })
    }
    /**
     * This method get only numbers and dote in the textfield <br/>
     * @param selector is textFieldTagId selector; <br/>
     * @example jj("#TextFeildTagID").jjSetTextFieldOnlyGetNumberAndDot();
     */
    this.jjSetTextFieldOnlyGetNumberAndDot = function  () {
        $(this.selector).keypress(function (event) {
            var keynum
            if(window.event){ // IE
                keynum = event.keyCode
            } else if(event.which){ // Netscape/Firefox/Opera
                keynum = event.which
            }
            if(new jj(this.selector).jjVal().toString().length>10){
                if(keynum == undefined){
                    event.returnValue = true;
                    return true;
                }
                else{
                    if(keynum==8){
                        event.returnValue = true;
                        return true;
                    }else{
                        event.returnValue = false;
                        return false;
                    }
                }
            }
            if(keynum!=46){
                if (keynum  < 48 || keynum  > 57) {//|| keynum==8 || keynum!=undefined
                    if (event.preventDefault && keynum!=8){
                        event.preventDefault();
                    } else{
                        // BEEP
                        event.returnValue = false;
                    }
                }
            }
        })
    }
    
    
    this.jjAddEnterKeyListener = function (method) {
        if(method==null){
            return false;
        
        }
        $(this.selector).keypress(function (event) {
            var keynum;
            if(window.event){ // IE
                keynum = event.keyCode
            } else if(event.which){ // Netscape/Firefox/Opera
                keynum = event.which
            }
            if(keynum == undefined){
                event.returnValue = true;
                return true;
            }else{
                if(keynum==13){
                    new jj(method).jjRun();
                    event.returnValue = true;
                    return true;
                }else{
                    event.returnValue = true;
                    return true;
                }
            }
        })
    },
    /**
     * return true if selector is number<br/>
     * @param selector is string<br/>
     * @return return true if selector is number<br/>
     * @example jj("1234").jjIsDigit();  
     */  
    this.jjIsDigit = function (){
        if( this.selector.length <1){
            return false;
        }
        var ValidChars = "0123456789";
        var IsNumber=true;
        for (i = 0; i < this.selector.length && IsNumber == true; i++)    {
            if (ValidChars.indexOf(this.selector.charAt(i)) == -1)        {
                return false;
            }
        }
        return true;
    }
    /**
     * return true if selector is number<br/>
     * @param selector is string<br/>
     * @return return true if selector is number<br/>
     * @example jj("1234.567").jjIsFloat();  
     */   
    this.jjIsFloat = function (){
        if( this.selector.length <1){
            return false;
        }
        var ValidChars = ".0123456789";
        var IsNumber=true;
        for (i = 0; (i < this.selector.length) && (IsNumber == true); i++)    {
            if (ValidChars.indexOf(this.selector.charAt(i)) == -1)        {
                return false;
            }
        }
        return true;
    }
    
    
    /**
     * This method return true if textField is empty<br/>
     * @param selector is textField selector<br/>
     * @return return true if textField is empty<br/>
     * @example jj("#TextFieldId").jjIsTextFieldEmpty()
     */ 
    this.jjIsTextFieldEmpty = function () {
        var tbx = $(this.selector).val();
        return !(tbx!=null && tbx != "");
    }
    
    /**
     * This method return true if Component is not empty<br/>
     * @return return true if Component is empty<br/>
     */
    this.jjIsComponentEmpty = function() {
        var nl, i;
        var ComponentTagId = $(this.selector);
        if (ComponentTagId.nodeName == 'SELECT' && ComponentTagId.selectedIndex < 1){
            return true;
        }else if (ComponentTagId.type == 'checkbox' && !ComponentTagId.checked){
            
            return true;
        }else if (ComponentTagId.type == 'radio') {
            for (i=0, nl = ComponentTagId.form.elements; i<nl.length; i++) {
                if (nl[i].type == "radio" && nl[i].name == ComponentTagId.name && nl[i].checked)
                    return false;
            }
            
            return true;
        }else{
            return new RegExp('^\\s*$').test(ComponentTagId.nodeType == 1 ? ComponentTagId.value : ComponentTagId);
        }
    
    }
    
    /**
     * This method return true if selector isUrl</br>
     * @param selector is textfeild selector,</br>
     * @return return true if selector is Url</br>
     * @example jj("http://www.arvin.com").jjIsUrl(),
     */
    this.jjIsUrl = function() {
        return  new RegExp('^(news|telnet|nttp|file|http|ftp|https)://[-A-Za-z0-9\\.]+\\/?.*$').test(this.selector);
    }
    
    /**
     * This method return true if value of textField1TagId and textField2TagId is equal<br/>
     * @param selector is textfeild selector and selector2 is textfeild selector,
     * @return return true if value of textField1TagId and textField2TagId is equal<br/>
     * @example jj("#textField1TagId").jjIsEqual("#textField2TagId");
     */
    this.jjIsEqual = function (selector2) {
        var tbx1 = $(this.selector).val();
        var tbx2 = $(selector2).val();
        return (tbx1 != null && tbx2 != null && tbx1 == tbx2) ? true : false;
    }
    
    
    /**
     * This method return true if selector is Email<br>
     * @param selector is textfeild selector,</br> 
     * @return return true if  sText is Email<br>
     * @example jj("amir.najafi@yahoo.com").jjIsEmail(),
     */
    this.jjIsEmail = function() {
        return  new RegExp('^[-!#$%&\'*+\\./0-9=?A-Z^_`a-z{|}~]+@[-!#$%&\'*+\\/0-9=?A-Z^_`a-z{|}~]+\.[-!#$%&\'*+\\./0-9=?A-Z^_`a-z{|}~]+$').test(this.selector);
    }   
    /**
     * This method return true if value in textFieldTagId is persian date<br/>
     * @param selector is date ,
     * @return return true if value in textFieldTagId is persian date<br/>
     * @example jj("1390/12/29").jjIsDateIr()
     */
    this.jjIsDateIr = function () {
        var p = this.selector.split('/');
        if (p == null || p.length != 3) return false;
        var year = new jj(p[0]).jjConvertToInt();
        var month = new jj(p[1]).jjConvertToInt();
        var day = new jj(p[2]).jjConvertToInt();
        if (year == "NaN" || month == "NaN" || day == "NaN"){
            return false
        };
        if (year < 1300 || year > 1500) {
            return false
        };
        if (month < 1 || month > 12) {
            return false
        };
        if (month >= 1 && month <= 6) {
            if (day < 1 || day > 31) {
                return false
            };
        } else {
            if (day < 1 || day > 30) {
                return false
            };
        }
        return true;
    }
    /**
     * This method return true if selector is date <br/>
     * @param selector is date String,<br/>
     * @param template is String, (like: "yy/m/dd") <br/>
     * @return return true if selector is date <br/>
     * @example jj("1390/12/29").isDateEn("yy/m/dd");
     * jj("1390/12/29").jjIsDateEn();
     */
    this.jjIsDateEn = function (template) {
        try{
            template = template==null?'yy/m/dd':template;
            template = template==""?'yy/m/dd':template;
            jQuery.datepicker.parseDate( template, this.selector);
            return true;
        } catch(e){
            return false;
        }
    }
    /**
     * This method set visible false row of table if that row is not contain searchString <br/>
     * @param selector is tableTag selector, (like: "tableID") <br/>
     * @param whichColumnNo is int, serach on wich column do<br/>
     * @param searchString is String,<br/>
     */
    this.jjSetTableFilter = function  (searchString, whichColumnNo) {
        if(whichColumnNo==null || whichColumnNo ==""){
            this.setTableFilterAll(searchString)         
            return;
        }
        var tbl = document.getElementById(this.selector.replace("#", ""));
        for(var j=1;j<tbl.rows.length;j++) {
            var cellText = "";
            if(tbl.rows[j].cells.length > whichColumnNo && 0 < whichColumnNo){
                cellText = tbl.rows[j].cells[whichColumnNo].innerHTML   ;
            }
            if(tbl.rows[j].cells[whichColumnNo]!=null){
                // tbl.rows[j].display =  '' ;
                tbl.rows[j].style.display = ( cellText.toString().toLowerCase().indexOf(searchString.toLowerCase())>-1) ? '' : 'none';
            }
        }
    }
    /**
     * This method set visible false row of table if that row is not contain searchString <br/>
     * @param selector is tableTagId is String, (like: "tableID") <br/>
     * @param searchString is String,<br/>
     */
    this.jjSetTableFilterAll = function  (searchString) {
        var tbl = document.getElementById(this.selector.replace("#", ""));
        for(var j=1;j<tbl.rows.length;j++) {
            var cellText = tbl.rows[j].innerHTML   ;
            tbl.rows[j].style.display = ( cellText.toString().toLowerCase().indexOf(searchString.toLowerCase())>-1) ? '' : 'none';
        }
    }
    
    /**
     * This method set backgroun color to table in 3 color <br/>
     * @param selector tableTagID is String, (like: "tableID") <br/>
     * @param headerRowColor is String, background of header of table (like: "#C0C0C0") <br/>
     * @param unSelectedRowColor1 String, background of odd row of table (like: "#C0C0C0") <br/>
     * @param unSelectedRowColor2 String, background of even row of table (like: "#C0C0C0") <br/>
     * @param columnCount is int, this is number of column count<br/>
     */
    this.jjSetTableCellColor = function  (headerRowColor,unSelectedRowColor1,unSelectedRowColor2,columnCount) {
        var allCells =$(this.selector).attr("cells");
        var counter = 0;
        for (var i = 0; i < allCells.length; i++){
            if(i<columnCount){
                if(headerRowColor!=null || headerRowColor!=""){
                    allCells[i].setAttribute("bgColor",headerRowColor);
                }
            } else{
                if(columnCount>counter){
                    if(unSelectedRowColor1!=null || unSelectedRowColor1!=""){
                        allCells[i].setAttribute("bgColor",unSelectedRowColor1);
                    }
                }else{
                    if(unSelectedRowColor2!=null || unSelectedRowColor2!=""){
                        allCells[i].setAttribute("bgColor",unSelectedRowColor2);
                    }
                    counter=counter==(columnCount*2)-1?-1:counter;    
                }
                counter+=1;
            }
        }    
    }
    
    /**
     * This method set backgroun color to table in 3 color <br/>
     * @param tableTagId is Selector String, (like: "tableID") <br/>
     * @param selectedRowColor is String, background of selected row of table (like: "#C0C0C0") <br/>
     */
    this.jjAddClickListenerToTable = function  (selectedRowColor) {
        $(this.selector +" tr").click(function (event) {
            var cellData = [];
            if($(this).attr("id")!="TR_HEADER"){
                var cells = $(this).attr("cells");
                $_TABLE_CELL_COLOR();
                for (var i = 0; i < cells.length; i++){
                    cellData.push(cells[i].innerHTML);
                    cells[i].setAttribute("bgColor",selectedRowColor);
                }
            }
        });
    }
    
    /** This method clean all data in table<br/>
     * @param selector is table Tagselector <br/>
     * @example jj("tableID").jjSetTableClean();
     */
    this.jjSetTableClean = function  () {
        $(this.selector).empty();
        $(this.selector).removeData();
    }
    
    this.WaitDiv = null ;
    /**
     * This method unlock page <br/>
     * @example jj().jjDialogWaitShow();
     */    
    this.jjDialogWaitShow = function(){
        if($("#jjDialogWait").attr("id")== undefined ){
            $("body").after("<div id=\"jjDialogWait\" style=\"font-family: tahoma;font-size: 1pt;width: 0px;height: 0px;\"></div>");
        }
        if(this.WaitDiv==null){
            this.WaitDiv = $("#jjDialogWait");
        }
        $(".ui-dialog-titlebar").hide();
        $(".ui-dialog-titlebar-close").hide();
        $(".ui-dialog").hide();
        $(".ui-dialog .ui-dialog-titlebar").hide();
        $(".ui-dialog .ui-dialog-title").hide(); 
        $(".ui-dialog .ui-dialog-titlebar-close").hide();
        $(".ui-dialog .ui-dialog-titlebar-close span").hide();
        $(".ui-dialog .ui-dialog-titlebar-close:hover, .ui-dialog .ui-dialog-titlebar-close:focus").hide();
        $(".ui-dialog .ui-dialog-content").hide();
        $(".ui-dialog .ui-dialog-buttonpane").hide();
        $(".ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset").hide();
        $(".ui-dialog .ui-dialog-buttonpane button").hide();
        $(".ui-dialog .ui-resizable-se").hide();
        $(".ui-draggable .ui-dialog-titlebar").hide();
        $(this.WaitDiv).parent().children().children('.ui-dialog-titlebar-close').hide();
        $(this.WaitDiv).dialog({
            modal:true,        
            height: 0 ,
            width: 0,
            marginLeft: 0,
            marginTop: 0,
            marginRight: 0,
            marginBottun: 0,
            opacity: 0.1,
            position: [0,0],
            top: 10,
            right: 0,
            stack: false,
            dialogClass: 'alert',
            resizable: false,
            show: "fade",
            showTitle:false,
            title:LANGUAGE=="fa"?"لطفا منتظر بمانید...":"...Wait" ,
            dialogClass: 'no-close'
        });
    
    } ,
    
    /**
     * This method unlock page <br/>
     * @example jj().jjDialogWaitHide();
     */    
    this.jjDialogWaitHide = function(){
        if($("#jjDialogWait").attr("id")== undefined ){
            $("body").after("<div id=\"jjDialogWait\" style=\"font-family: tahoma;font-size: 1pt;width: 0px;height: 0px;\"></div>");
        }
        if(this.WaitDiv==null){
            this.WaitDiv = $("#jjDialogWait");
        };
        //        $(".ui-dialog-titlebar").hide();
        //        $(".ui-dialog-titlebar-close").hide();
        //        $(".ui-dialog").hide();
        //        $(".ui-dialog .ui-dialog-titlebar").hide();
        //        $(".ui-dialog .ui-dialog-title").hide(); 
        //        $(".ui-dialog .ui-dialog-titlebar-close").hide();
        //        $(".ui-dialog .ui-dialog-titlebar-close span").hide();
        //        $(".ui-dialog .ui-dialog-titlebar-close:hover, .ui-dialog .ui-dialog-titlebar-close:focus").hide();
        //        $(".ui-dialog .ui-dialog-content").hide();
        //        $(".ui-dialog .ui-dialog-buttonpane").hide();
        //        $(".ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset").hide();
        //        $(".ui-dialog .ui-dialog-buttonpane button").hide();
        //        $(".ui-dialog .ui-resizable-se").hide();
        //        $(".ui-draggable .ui-dialog-titlebar").hide();
        //        $(this.WaitDiv).parent().hide();
        //        $(this.WaitDiv).parent().children().hide();
        //        $(this.WaitDiv).parent().children().children().hide();
        //        $(this.WaitDiv).parent().children().children('.ui-dialog-titlebar-close').hide();
        $(this.WaitDiv).dialog("close");
    },
    /**
     * This method show one modal dialog to user <br/>
     * @param selector is messege for show
     * @param isModal is boolean (default: true)
     * @param myTitle is String (default: "")
     * @param dialogHeight is int (default: 150)
     * @param dialogWidth is int (default: 460)
     * @example jj("Hello").jjDialog();
     * @example jj("Hello").jjDialog(false,"Title",150,460);
     */
    this.jjDialog = function(isModal,myTitle,dialogHeight,dialogWidth){
        myTitle = myTitle == null ? "" : myTitle;
        dialogHeight = dialogHeight == null ? 150 : dialogHeight;
        dialogWidth = dialogWidth == null ? 460 : dialogWidth;
        isModal = isModal == null ? true : isModal;
        var messege = this.selector;
        if($("#messageDiv").attr("id")== undefined ){
            $("body").after("<div id=\"messageDiv\" style=\"display: none\"></div>");
        }
        $(".ui-dialog-titlebar").show();
        $(".ui-dialog-titlebar-close").show();
        $(".ui-dialog").show();
        $(".ui-dialog .ui-dialog-titlebar").show();
        $(".ui-dialog .ui-dialog-title").show(); 
        $(".ui-dialog .ui-dialog-titlebar-close").show();
        $(".ui-dialog .ui-dialog-titlebar-close span").show();
        $(".ui-dialog .ui-dialog-titlebar-close").show();
        //        $(".ui-dialog .ui-dialog-titlebar-close").show();
        //        $(".ui-dialog .ui-dialog-titlebar-close:hover, .ui-dialog .ui-dialog-titlebar-close:focus").show();
        $(".ui-dialog .ui-dialog-content").show();
        $(".ui-dialog .ui-dialog-buttonpane").show();
        $(".ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset").show();
        $(".ui-dialog .ui-dialog-buttonpane button").show();
        $(".ui-dialog .ui-resizable-se").show();
        $(".ui-draggable .ui-dialog-titlebar").show();
        if(LANGUAGE=="fa"){
            $("#messageDiv").html(messege).css("text-align","right");
            $("#messageDiv").dialog({
                modal:isModal,        
                title: myTitle,
                height: dialogHeight ,
                width: dialogWidth ,
                position:'center',
                buttons:{
                    "بستن": function() {
                        $(this).dialog("close");
                    }
                },
                open: function(){
                    $("#jjDialogWait").dialog("close");
                },
                close: function(){
                    jj().jjDialogWaitHide();
                }
            });
        }else{
            $(messageDiv).html(messege).css("text-align","left").dialog({
                modal:isModal,        
                title: myTitle,
                height: dialogHeight ,
                width: dialogWidth ,
                position:'center',
                buttons:{
                    "OK": function() {
                        $(this).dialog("close");
                    }
                },
                open: function(){
                    $("#jjDialogWait").dialog("close");
                },
                close: function(){
                    jj().jjDialogWaitHide();
                }
            });
        }
    }
    
    
    /**
     * This method show one modal dialog to user <br/>
     * @param selector is messege for show
     * @param statement is javaScript string for YES button onClick  
     * @param myTitle is String,  (default: "") 
     * @param dialogHeight is int (default: 150)
     * @param dialogWidth is int, (default: 460)
     * @param isModal is boolean, (default: true)
     * @example jj("Are You Sure?").jjDialog_YesNo();
     * @example jj("Are You Sure?").jjDialog_YesNo("alert('OK');",false,"Title",150,460);
     */
    this.jjDialog_YesNo = function(statement,isModal,myTitle,dialogHeight,dialogWidth){
        statement = statement==null ? "" : statement;
        myTitle = myTitle == null ? "" : myTitle;
        dialogHeight = dialogHeight == null ? 150 : dialogHeight;
        dialogWidth = dialogWidth == null ? 460 : dialogWidth;
        isModal = isModal == null ? true : isModal;
        var messege = this.selector;
        if($("#messageDiv").attr("id")== undefined ){
            $("body").after("<div id='messageDiv' style='display: block'></div>");
        }
        $(".ui-dialog-titlebar").show();
        $(".ui-dialog-titlebar-close").show();
        $(".ui-dialog").hide();
        $(".ui-dialog .ui-dialog-titlebar").show();
        $(".ui-dialog .ui-dialog-title").show(); 
        $(".ui-dialog .ui-dialog-titlebar-close").show();
        $(".ui-dialog .ui-dialog-titlebar-close span").show();
        //        $(".ui-dialog .ui-dialog-titlebar-close:hover, .ui-dialog .ui-dialog-titlebar-close:focus").show();
        $(".ui-dialog .ui-dialog-content").show();
        $(".ui-dialog .ui-dialog-buttonpane").show();
        $(".ui-dialog .ui-dialog-buttonpane .ui-dialog-buttonset").show();
        $(".ui-dialog .ui-dialog-buttonpane button").show();
        $(".ui-dialog .ui-resizable-se").show();
        $(".ui-draggable .ui-dialog-titlebar").show();
        this.statement=statement;
        if(LANGUAGE=="fa"){
            $("#messageDiv").html(messege).css("text-align","right").dialog({
                modal:isModal,        
                title: myTitle,
                height: dialogHeight ,
                width: dialogWidth ,
                position:'center',
                dialogClass: 'alert' ,
                //                 autoOpen: false,
                buttons:{
                    "بله": function() {
                        $(this).dialog("close");
                        eval(statement);
                    },
                    "انصراف": function() {
                        $(this).dialog("close");
                        $('html, body').css("cursor", "auto"); 
                    }
                },
                open: function(){
                //                    $("#jjDialogWait").dialog("close");
                },
                close: function(){
                    new   jj('').jjDialogWaitHide();
                }
            });
            $( "#messageDiv" ).dialog( "open" );
        }else{
            $("#messageDiv").html(messege).css("text-align","left").dialog({
                modal:isModal,        
                title: myTitle,
                height: dialogHeight ,
                width: dialogWidth ,
                position:'center',
                dialogClass: 'alert' ,
                buttons:{
                    "Yes": function() {
                        $(this).dialog("close");
                        eval(statement);
                    },
                    "Cancel": function() {
                        $(this).dialog("close");
                        $('html, body').css("cursor", "auto"); 
                    }
                } ,
                open: function(){
                    $("#jjDialogWait").dialog("close");
                },
                close: function(){
                    new   jj('').jjDialogWaitHide();
                }
            });
        }
    } 
    
    /**
     * @param selector is field name for show
     * @param fieldName_en is String, english field name (default: "") 
     * @param length is int max value
     *  @example jj("Name").jjDialogMaxLengthAlert(20); => dialog: "Name length must be less than 20 char"
     */
    this.jjDialogMaxLengthAlert = function  (length , fieldName_en) {
        if(length==null){
            return false;
        }
        if(LANGUAGE=="fa"){
            new jj( 
                "طول "
                + this.selector
                +" نباید بیشتر از "
                +length
                +" حرف باشد"
                ).jjDialog();
        }else{
            fieldName_en = (fieldName_en==null)?"":fieldName_en;
            new jj( fieldName_en+" length must be less than "+length+" char").jjDialog();
        }
    }
    /**
     * @param selector is div selector For Slide
     * @example jj("slide").jjSlide(800,250);
     * jj("slide").jjSlide();
     * <link rel="stylesheet" type="text/css" href="js/slide/coin-slider.css" />
     * <script type="text/javascript" src="js/slide/slider.js"></script>
     * <div id="slide">
     * <a href="#"><img src="images/slide1.jpg" alt="slide1" /><span></span></a>
     * <a href="#"><img src="images/slide2.jpg" alt="slide1" /><span></span></a>
     * <a href="#"><img src="images/slide3.jpg" alt="slide1" /><span></span></a>
     * </div>
     */
    this.jjSlide = function  (s_width , s_height, s_delay) {
        s_width = (s_width==null)?800:s_width;
        s_height = (s_height==null)?250:s_height;
        s_delay = (s_delay==null)?5000:s_delay;
        $(this.selector).coinslider({
            width:s_width,
            height:s_height,
            delay:s_delay,
            opacity:1,
            spw:7,
            sph:5,
            sDelay:30,
            titleSpeed:500,
            effect:'',
            navigation:true,
            links:true,
            hoverPause:true
        });
    //        $('.slider, .menu_nav, .content_resize, .fbg_resize, .content .sidebar h2, .content .mainbar h2').css({
    //            "border-radius":"5px", 
    //            "-moz-border-radius":"5px", 
    //            "-webkit-border-radius":"5px"
    //        });
    
    // RADIUS BOX
    //        $('.cs-title').css({
    //            "border-radius":"0 0 0px 8px", 
    //            "-moz-border-radius":"0 0 8px 8px", 
    //            "-webkit-border-radius":"0 0 8px 8px"
    //        });
    
    
    }
    /**
     * @param selector is field name for show
     * @param fieldName_en is String, english field name (default: "") 
     * @param length is int min value
     *  @example jj("Name").jjDialogMinLengthAlert(20); => dialog: "Name length must be larger than 20 char"
     */
    this.jjDialogMinLengthAlert = function  (length, fieldName_en) {
        if(length==null){
            return false;
        }
        if(LANGUAGE=="fa"){
            new jj( 
                "طول "
                + this.selector
                +" نباید کمتر از "
                + length
                +" حرف باشد"
                ).jjDialog();
        }else{
            fieldName_en = (fieldName_en==null)?"":fieldName_en;
            new jj(fieldName_en+" length must be larger than "+length+" char").jjDialog();
        }
    }
    /**
     * @param selector is field name for show
     * @param fieldName_en is String, english field name (default: "") 
     * @param length is int min value
     * @example jj("Name").jjDialogEqualLengthAlert(20); => dialog: "Name length have 20 char"
     */
    this.jjDialogEqualLengthAlert = function  (length, fieldName_en) {
        if(length==null){
            return false;
        }
        if(LANGUAGE=="fa"){
            new jj( 
                "طول "
                + this.selector
                +" باید "
                + length
                +" حرف باشد"
                ).jjDialog();
        }else{
            fieldName_en = (fieldName_en == null) ? "" : fieldName_en;
            new jj(fieldName_en + " length must have " + length + " char").jjDialog();
        }
    }
    /**
     * @param selector is id for go scrol there for show
     * @example jj("sw").jjScrolForShow();"
     */
    this.jjScrolForShow = function (){
        var el = document.getElementById(this.selector.replace("#", ""));
        el.scrollIntoView(true);
    }
    /**
     * @param selector is text input selector
     * @example jj("#searchTextInputId").jjSetDefaultValueToTxt("Search...");"
     */
    this.jjSetDefaultValueToTxt = function (defaultText){
        var thisSelector = this.selector;
        new jj(thisSelector).jjVal(defaultText);
        var defaultText2 = new jj(thisSelector).jjVal();
        $(this.selector).click(function(){
            if ( new jj(thisSelector).jjVal()==defaultText2) {
                $(thisSelector).val('');
            }
        });
        $(this.selector).blur(function(){
            if (new jj(thisSelector).jjVal()=='') {
                $(thisSelector).val(defaultText);
            }
        });
    };
    /**
     * @param selector is button for send
     * @example jj("#btnSendId").jjAjaxFileUpload('inputFileId','#inputTextId','#viewImgId');
     * <script type="text/javascript" src="js/jquery/ajaxfileupload.js"></script>
     */
    this.jjAjaxFileUpload = function (inputFileId,inputTextSelector,viewImgSelector){
        $(this.selector).click(function(){
            if( $("#"+inputFileId.replace("#", "")).val()==""){
                new jj("ابتدا  فایلی را انتخاب نمایید.").jjDialog();
                return;
            }
            $.ajaxFileUpload({
                url :'UploadServlet',
                secureuri:false,
                fileElementId:inputFileId.replace("#", ""),
                dataType: 'JSON',
                cache: false ,
                success: function(data){
                    if(data!=null){
                        data = data.replace("<PRE>", '').replace("</PRE>", '').replace("<pre>", '').replace("</pre>", '').replace("upload/", '').replace("Upload/", '');
                        data = data.replace("/", '').replace("/", '').replace("\\", '');
                    }else{
                        new jj('فایل به درستی ارسال نشد.').jjDialog();
                    }
                    //                    var address = data.split('/');
                    //                    var picName = address[address.length-1];
                    $("#"+inputFileId.replace("#", "")).val('');                    
                    if(data!=""){
                        if(data!="big"){
                            $(inputTextSelector).val(data);
                            if(viewImgSelector!=null){
                                $(viewImgSelector).attr('src','upload/'+data);
                            }
                        }else{
                            new jj('حجم فایل شما بیش اندازه بزرگ می باشد.').jjDialog();
                        }
                    }else{
                        new jj('فایل به درستی ارسال نشد.').jjDialog();
                    }
                    
                }
            });
        });
    
    };
    /**
     * @param selector is button for send
     * @example jj("#btnSendId").jjAjaxFileUpload('inputFileId','#inputTextId','#viewImgId');
     * <script type="text/javascript" src="js/jquery/ajaxfileupload.js"></script>
     */
    this.jjAjaxFileUpload2 = function (inputFileId,inputTextSelector,viewImgSelector){
        var btn = this.selector;
        $(btn).click(function(){
            if( $("#"+inputFileId.replace("#", "")).val()==""){
                new jj(".ابتدا فایلی را انتخاب نمایید").jjDialog();
            }else{
                alert("++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                $.ajaxFileUpload({
                    url :'UploadServlet2',
                    secureuri:false,
                    fileElementId:inputFileId.replace("#", ""),
                    dataType: 'JSON',
                    cache: false ,
                    success: function(data){
                        data = data==null?"":data;
                        data = data.replace("<PRE>", '').replace("</PRE>", '').replace("<pre>", '').replace("</pre>", '').replace("upload/", '').replace("Upload/", '');
                        data = data.replace("/", '').replace("/", '').replace("\\", '');
                        if(data!=""){
                            $("#"+inputFileId.replace("#", "")).val('');
                            if(data!="big"){
                                $(inputTextSelector).val(data);
                                if(viewImgSelector!=null){
                                    $(viewImgSelector).attr('src','upload/'+data);
                                }
                            }else{
                                $(inputTextSelector).val('');
                                $(inputTextSelector).hide();
                                $(btn).show();
                                $("#"+inputFileId.replace("#", "")).show();
                                new jj('.حجم فایل شما بیش اندازه بزرگ می باشد').jjDialog();
                            }
                        }else{
                            new jj('.فایل به درستی ارسال نشد').jjDialog();
                            $(inputTextSelector).val('');
                            $(inputTextSelector).hide();
                            $(btn).show();
                            $("#"+inputFileId.replace("#", "")).show();
                            new jj('.حجم فایل شما بیش اندازه بزرگ می باشد').jjDialog();
                        }
                    }
                });
            }
        });
    
    };
    /**
         * @param selector is button for send
         * @example jj("#btnSendId").jjAjaxFileUpload('inputFileId','#inputTextId','#viewImgId');
         * <script type="text/javascript" src="js/jquery/ajaxfileupload.js"></script>
         */
    this.jjAjaxFileUploadEditor = function (inputFileId,Editor){
        $(this.selector).button().click(function(){
            if( $("#"+inputFileId.replace("#", "")).val()==""){
                new jj("ابتدا  فایلی را انتخاب نمایید.").jjDialog();
                return;
            }
            $.ajaxFileUpload({
                url :'UploadServlet',
                secureuri:false,
                fileElementId:inputFileId.replace("#", ""),
                dataType: 'JSON',
                cache: false ,
                success: function(data){
                    if(data!=null){
                        data = data.replace("<PRE>", '').replace("</PRE>", '').replace("<pre>", '').replace("</pre>", '').replace("upload/", '').replace("Upload/", '');
                        data = data.replace("/", '').replace("/", '').replace("\\", '');
                    }
                    if(data==null || data==undefined ){
                        new jj('فایل به درستی ارسال نشد.').jjDialog();
                    }
                    var address = data.split('/');
                    var picName = address[address.length-1];
                    $("#"+inputFileId.replace("#", "")).val('');
                    if(picName!=""){
                        if(picName!="big"){
                            Editor.setData(Editor.getData() + "<img style='width: 200px; float: top' src='upload/"+picName+"' />");
                        }else{
                            new jj('حجم فایل شما بیش اندازه بزرگ می باشد.').jjDialog();
                        }
                    }else{
                        new jj('فایل به درستی ارسال نشد.').jjDialog();
                    }
                }
            });
        });
    
    },
    
 
    this.jjCheckPoll = function(id){
        var isBieng = new jj("vote-" + id ).jjCookieGet();
        if(isBieng == "checked"){
            $("#vote-" + id + "-1").hide();
            $("#vote-" + id + "-2").hide();
            $("#vote-" + id + "-3").hide();
            $("#vote-" + id + "-4").hide();
            $("#vote-" + id + "-5").hide();
            $("#vote-" + id + "-6").hide();
            $("#vote-" + id + "-7").hide();
            $("#vote-" + id + "-8").hide();
            $("#vote-" + id + "-9").hide();
            $("#vote-" + id + "-10").hide();
            $("#vote-" + id + "-11").hide();
            $("#vote-" + id + "-12").hide();
        }
    },
    this.jjVote = function(vote_id ,check_id){
        if(new jj("#vote-"+vote_id +"-"+check_id).jjVal()==1){
            new jj("do=Category_Poll.insert&category_poll_answer="+check_id+"&category_poll_user_id=1&category_poll_which="+vote_id).jjAjax2();
        }
        new jj("vote-" + vote_id ).jjCookieSave('checked');
        new jj("").jjCheckPoll(vote_id);
    }
    
    return this;
}