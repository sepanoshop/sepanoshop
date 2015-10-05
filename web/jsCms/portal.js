// ----------------------------------------------------------------------------
var cmsPortal = {
    tableName : "Portal",
    f_id : "id",
    loadForm:function(){        
        alert('cmsPortal.loadForm ....');
        if($("#swPortalForm").html()==''){            
            $("#swPortalForm").load("formCms/portal_post.html", null, function(){
                //                jj("#pic_margin").jjSetTextFieldOnlyGetNumber();
                //                jj("#pic_price").jjSetTextFieldOnlyGetNumber();
                $("#cancel_portalPost").button().click(function(e) {
                    cmsPortal.m_clean();
                    cmsPortal.m_show_tbl();
                });
                $("#portal_post_ownerId").change(function(){
                    cmsPortal.m_getPortalTitle(jj('#portal_post_ownerId').jjVal());
                });
                new jj("#portal_post_date").jjCalendar();
                $("#upload_btn_portalPost_pic1").button().click(function(){});
                $("#upload_btn_portalPost_pic2").button().click(function(){});
                $("#upload_btn_portalPost_pic3").button().click(function(){});
                $("#upload_btn_portalPost_pic4").button().click(function(){});
                $("#upload_btn_portalPost_pic5").button().click(function(){});
                // manage on change value on pic name input text filed
                // jjAjaxFileUpload(a,b,c) do every thing is need
                new jj('#upload_btn_portalPost_pic1').jjAjaxFileUpload('upload_portalPost_pic1', '#portal_post_pic1', '#preview_portalPost_pic1');
                $('#portal_post_pic1').keyup(function(){
                    $('#preview_portalPost_pic1').attr('src','upload/'+$('#portal_post_pic1').val());
                    if($('#portal_post_pic1').val()==''){
                        $('#preview_portalPost_pic1').attr('src','img/preview.jpg');
                    }
                });                  
                new jj('#upload_btn_portalPost_pic2').jjAjaxFileUpload('upload_portalPost_pic2', '#portal_post_pic2', '#preview_portalPost_pic2');
                $('#portal_post_pic2').keyup(function(){
                    $('#preview_portalPost_pic2').attr('src','upload/'+$('#portal_post_pic2').val());
                    if($('#portal_post_pic2').val()==''){
                        $('#preview_portalPost_pic2').attr('src','img/preview.jpg');
                    }
                });                  
                new jj('#upload_btn_portalPost_pic3').jjAjaxFileUpload('upload_portalPost_pic3', '#portal_post_pic3', '#preview_portalPost_pic3');
                $('#portal_post_pic3').keyup(function(){
                    $('#preview_portalPost_pic3').attr('src','upload/'+$('#portal_post_pic3').val());
                    if($('#portal_post_pic3').val()==''){
                        $('#preview_portalPost_pic3').attr('src','img/preview.jpg');
                    }
                });                  
                new jj('#upload_btn_portalPost_pic4').jjAjaxFileUpload('upload_portalPost_pic4', '#portal_post_pic4', '#preview_portalPost_pic4');
                $('#portal_post_pic4').keyup(function(){
                    $('#preview_portalPost_pic4').attr('src','upload/'+$('#portal_post_pic4').val());
                    if($('#portal_post_pic4').val()==''){
                        $('#preview_portalPost_pic4').attr('src','img/preview.jpg');
                    }
                });                  
                new jj('#upload_btn_portalPost_pic5').jjAjaxFileUpload('upload_portalPost_pic5', '#portal_post_pic5', '#preview_portalPost_pic5');
                $('#portal_post_pic5').keyup(function(){
                    $('#preview_portalPost_pic5').attr('src','upload/'+$('#portal_post_pic5').val());
                    if($('#portal_post_pic5').val()==''){
                        $('#preview_portalPost_pic5').attr('src','img/preview.jpg');
                    }
                });
                cmsPortal.changeButtonActivate();//for change select/option to input text
            });
        }        
        cmsPortal.m_getValuesList();
    },  
    changeButtonActivate:function(){//for change select/option to input text
      $('#btnChangeValueComponent1').click(function(){
                    if($('#portal_post_val11').css('display')=='none'){
                        $('#portal_post_val11').show();
                        $('#portal_post_val11').attr('name','portal_post_val1');
                        $('#portal_post_val1').hide();
                        $('#portal_post_val1').attr('name','aaa');
                        $('#btnChangeValueComponent1').val("*");
                    }else{
                        $('#portal_post_val11').hide();
                        $('#portal_post_val11').attr('name','aaa');
                        $('#portal_post_val1').show();
                        $('#portal_post_val1').attr('name','portal_post_val1');
                        $('#btnChangeValueComponent1').val("+");
                    }
                })
                
                $('#btnChangeValueComponent2').click(function(){
                    if($('#portal_post_val22').css('display')=='none'){
                        $('#portal_post_val22').show();
                        $('#portal_post_val22').attr('name','portal_post_val2');
                        $('#portal_post_val2').hide();
                        $('#portal_post_val2').attr('name','aaa');
                        $('#btnChangeValueComponent2').val("*");
                    }else{
                        $('#portal_post_val22').hide();
                        $('#portal_post_val22').attr('name','aaa');
                        $('#portal_post_val2').show();
                        $('#portal_post_val2').attr('name','portal_post_val2');
                        $('#btnChangeValueComponent2').val("+");
                    }
                })
                
                $('#btnChangeValueComponent3').click(function(){
                    if($('#portal_post_val33').css('display')=='none'){
                        $('#portal_post_val33').show();
                        $('#portal_post_val33').attr('name','portal_post_val3');
                        $('#portal_post_val3').hide();
                        $('#portal_post_val3').attr('name','aaa');
                        $('#btnChangeValueComponent3').val("*");
                    }else{
                        $('#portal_post_val33').hide();
                        $('#portal_post_val33').attr('name','aaa');
                        $('#portal_post_val3').show();
                        $('#portal_post_val3').attr('name','portal_post_val3');
                        $('#btnChangeValueComponent3').val("+");
                    }
                })
                
                $('#btnChangeValueComponent4').click(function(){
                    if($('#portal_post_val44').css('display')=='none'){
                        $('#portal_post_val44').show();
                        $('#portal_post_val44').attr('name','portal_post_val4');
                        $('#portal_post_val4').hide();
                        $('#portal_post_val4').attr('name','aaa');
                        $('#btnChangeValueComponent4').val("*");
                    }else{
                        $('#portal_post_val44').hide();
                        $('#portal_post_val44').attr('name','aaa');
                        $('#portal_post_val4').show();
                        $('#portal_post_val4').attr('name','portal_post_val4');
                        $('#btnChangeValueComponent4').val("+");
                    }
                })
                
                $('#btnChangeValueComponent5').click(function(){
                    if($('#portal_post_val55').css('display')=='none'){
                        $('#portal_post_val55').show();
                        $('#portal_post_val55').attr('name','portal_post_val5');
                        $('#portal_post_val5').hide();
                        $('#portal_post_val5').attr('name','aaa');
                        $('#btnChangeValueComponent5').val("*");
                    }else{
                        $('#portal_post_val55').hide();
                        $('#portal_post_val55').attr('name','aaa');
                        $('#portal_post_val5').show();
                        $('#portal_post_val5').attr('name','portal_post_val5');
                        $('#btnChangeValueComponent5').val("+");
                    }
                })
                
                $('#btnChangeValueComponent6').click(function(){
                    if($('#portal_post_val66').css('display')=='none'){
                        $('#portal_post_val66').show();
                        $('#portal_post_val66').attr('name','portal_post_val6');
                        $('#portal_post_val6').hide();
                        $('#portal_post_val6').attr('name','aaa');
                        $('#btnChangeValueComponent6').val("*");
                    }else{
                        $('#portal_post_val66').hide();
                        $('#portal_post_val6').attr('name','aaa');
                        $('#portal_post_val6').show();
                        $('#portal_post_val6').attr('name','portal_post_val6');
                        $('#btnChangeValueComponent6').val("+");
                    }
                })
                
                $('#btnChangeValueComponent7').click(function(){
                    if($('#portal_post_val77').css('display')=='none'){
                        $('#portal_post_val77').show();
                        $('#portal_post_val77').attr('name','portal_post_val7');
                        $('#portal_post_val7').hide();
                        $('#portal_post_val7').attr('name','aaa');
                        $('#btnChangeValueComponent7').val("*");
                    }else{
                        $('#portal_post_val77').hide();
                        $('#portal_post_val77').attr('name','aaa');
                        $('#portal_post_val7').show();
                        $('#portal_post_val7').attr('name','portal_post_val7');
                        $('#btnChangeValueComponent7').val("+");
                    }
                })
                $('#btnChangeValueComponent8').click(function(){
                    if($('#portal_post_val88').css('display')=='none'){
                        $('#portal_post_val88').show();
                        $('#portal_post_val88').attr('name','portal_post_val8');
                        $('#portal_post_val8').hide();
                        $('#portal_post_val8').attr('name','aaa');
                        $('#btnChangeValueComponent8').val("*");
                    }else{
                        $('#portal_post_val88').hide();
                        $('#portal_post_val88').attr('name','aaa');
                        $('#portal_post_val8').show();
                        $('#portal_post_val8').attr('name','portal_post_val8');
                        $('#btnChangeValueComponent8').val("+");
                    }
                })
                $('#btnChangeValueComponent9').click(function(){
                    if($('#portal_post_val99').css('display')=='none'){
                        $('#portal_post_val99').show();
                        $('#portal_post_val99').attr('name','portal_post_val9');
                        $('#portal_post_val9').hide();
                        $('#portal_post_val9').attr('name','aaa');
                        $('#btnChangeValueComponent9').val("*");
                    }else{
                        $('#portal_post_val99').hide();
                        $('#portal_post_val99').attr('name','aaa');
                        $('#portal_post_val9').show();
                        $('#portal_post_val9').attr('name','portal_post_val9');
                        $('#btnChangeValueComponent9').val("+");
                    }
                })
                $('#btnChangeValueComponent10').click(function(){
                    if($('#portal_post_val1010').css('display')=='none'){
                        $('#portal_post_val1010').show();
                        $('#portal_post_val1010').attr('name','portal_post_val10');
                        $('#portal_post_val10').hide();
                        $('#portal_post_val10').attr('name','aaa');
                        $('#btnChangeValueComponent10').val("*");
                    }else{
                        $('#portal_post_val1010').hide();
                        $('#portal_post_val1010').attr('name','aaa');
                        $('#portal_post_val10').show();
                        $('#portal_post_val10').attr('name','portal_post_val10');
                        $('#btnChangeValueComponent10').val("+");
                    }
                })  
    },
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsPortal.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swPortalTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsPortal.tabSizeTbl();
        alert('cmsPortal.m_refresh()!!!!!!!!!!!!!!');
    },
    m_show_form:function(){
        $('#swPortalTbl').hide();
        $('#swPortalForm').show();
        cmsPortal.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swPortalForm").jjFormClean();
        $("#preview_portalPost_pic1").attr('src',"img/preview.jpg");
        $("#preview_portalPost_pic2").attr('src',"img/preview.jpg");
        $("#preview_portalPost_pic3").attr('src',"img/preview.jpg");
        $("#preview_portalPost_pic4").attr('src',"img/preview.jpg");
        $("#preview_portalPost_pic5").attr('src',"img/preview.jpg");
        $("#portal_post_visit").removeAttr("disabled");
        new jj("#portal_post_visit_checkbox").jjVal("1");
        
        new jj("#portal_post_like").jjVal("0");
        $("#portal_post_like").removeAttr("disabled");
        new jj("#portal_post_like_checkbox").jjVal("1");
        
        new jj("#portal_post_dislike").jjVal("0");
        $("#portal_post_dislike").removeAttr("disabled");
        new jj("#portal_post_dislike_checkbox").jjVal("1");
        
        alert('cmsPortal.m_clean();');
    //در اینجا می شود اگر عکسی آپلود کرده بود را پاک کرد
    //        new jj("#"+cmsPortal.f_gallery_id_select).jjVal('1');
    //        new jj("#"+cmsPortal.f_lang).jjVal('1');
    //        new jj("#"+cmsPortal.f_parent).jjVal('0');
    //        $("#pic_pic_name_preview").attr('src','img/preview.jpg');
    },
    m_add_new:function(){
        jj("do="+cmsPortal.tableName+".add_new").jjAjax2(false);
        cmsPortal.m_show_form();
        cmsPortal.m_clean();
        cmsPortal.m_getValuesList();
    },
    m_show_tbl:function(){
        $('#swPortalTbl').show();
        $('#swPortalForm').hide();
        
        if($('#swPortalTbl').html()==""){
            cmsPortal.m_refresh();
        }
        cmsPortal.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsPortal.tableName+".insert";
        param += "&" +new jj("#swPortalForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsPortal.m_show_tbl();
        cmsPortal.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsPortal.tableName+".edit";
        param += "&" + new jj("#swPortalForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsPortal.m_show_tbl();
        cmsPortal.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsPortal.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsPortal.tableName+".delete";
        param += "&" + cmsPortal.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsPortal.m_show_tbl();
        cmsPortal.m_clean();
    },
    m_select:function(id){
        cmsPortal.m_show_form();
        cmsPortal.m_getValuesList(id);
        var param = "";
        param += "do="+cmsPortal.tableName+".select";
        param += "&" + cmsPortal.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        alert("cmsPortal.m_select(id);")
    },
    tabSizeTbl: function () {
        $('#swPortalAll').css('height',519);
        cmsPortal.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swPicAll').css('height',120);
        cmsPortal.heightTab = 120;
    },
    heightTab:"120",
    mainTabSetSize : function () {
        $('#swPortalAll').css('height',cmsPortal.heightTab);
    },
    /*
     *send a commant to server for fill "selcet" and "option" distinct from database
     *[id] is for selected option [selecte=xxx}
     * use in forms ( for example portal_post.html in cms)
     */
    m_getValuesList:function(id){
        cmsPortal.getVal1List(id);
        cmsPortal.getVal2List(id);
        cmsPortal.getVal3List(id);
        cmsPortal.getVal4List(id);
        cmsPortal.getVal5List(id);
        cmsPortal.getPortalUsersList(id);// List of avalable portal
    },
    m_getPortalTitle:function(id){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".getPortalTitle";
        param += "&panel=" +  "portal_post_ownerName";
        param += "&id="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    getPortalUsersList:function(id){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".getPortalUsersList";
        param += "&panel=" +  "portal_post_ownerId";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    getVal1List:function(id){
        var param = "";
        param += "do="+cmsPortal.tableName+".get_val1List";
        param += "&panel=" +  "portal_post_val1";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    getVal2List:function(id){
        var param = "";
        param += "do="+cmsPortal.tableName+".get_val2List";
        param += "&panel=" +  "portal_post_val2";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    getVal3List:function(id){
        var param = "";
        param += "do="+cmsPortal.tableName+".get_val3List";
        param += "&panel=" +  "portal_post_val3";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    getVal4List:function(id){
        var param = "";
        param += "do="+cmsPortal.tableName+".get_val4List";
        param += "&panel=" +  "portal_post_val4";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    getVal5List:function(id){
        var param = "";
        param += "do="+cmsPortal.tableName+".get_val5List";
        param += "&panel=" +  "portal_post_val5";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    }
}
// ---------------------------------------------------------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------------------
// ---------------------------   cmsPortalUser  -----------------------------------------------------------------------------------------
// ---------------------------------------------------------------------------------------------------------------------------------------
var cmsPortalUser = {
    tableName : "PortalUser",
    f_id :"id",
    //    f_category_gallery_id :"category_gallery_id",
    //    f_parent:"category_gallery_parent",
    //    f_title:"category_gallery_title",
    //    f_lang:"category_gallery_lang",
    loadForm:function(){
        alert("cmsPortalUser.loadform();...");
        if($("#swPortalUsersForm").html()==''){
            $("#swPortalUsersForm").load("formCms/portal_users.html", null, function(){
                $("#cancel_portalUser").button().click(function(e) {
                    alert("$(\"#cancel_portalUser\").button().click() in cmsPortalUser.loadform();");
                    cmsPortalUser.m_clean();
                    cmsPortalUser.m_show_tbl();
                //                cmsPortalUser.m_show_form();
                });
                // manage on change value on pic name input text filed
                // jjAjaxFileUpload(a,b,c) do every thing is need
                new jj('#upload_btn_portalUser_pic1').jjAjaxFileUpload('upload_portalUser_pic', '#portal_user_pic', '#preview_portalUser_pic');
                $('#portal_user_pic').keyup(function(){
                    $('#preview_portalUser_pic').attr('src','upload/'+$('#portal_user_pic').val());
                    if($('#portal_user_pic').val()==''){
                        $('#preview_portalUser_pic').attr('src','img/preview.jpg');
                    }
                }); 
                //for change select/option to input text
                $('#btnChangeValComponent1').click(function(){
                    if($('#portal_user_val55').css('display')=='none'){
                        $('#portal_user_val55').show();
                        $('#portal_user_val55').attr('name','portal_user_val5');
                        $('#portal_user_val5').hide();
                        $('#portal_user_val5').attr('name','aaa');
                        $('#btnChangeValComponent1').val("*");
                    }else{
                        $('#portal_user_val55').hide();
                        $('#portal_user_val55').attr('name','aaa');
                        $('#portal_user_val5').show();
                        $('#portal_user_val5').attr('name','portal_user_val5');
                        $('#btnChangeValComponent1').val("+");
                    }
                })
                $('#btnChangeValComponent2').click(function(){
                    if($('#portal_user_val66').css('display')=='none'){
                        $('#portal_user_val66').show();
                        $('#portal_user_val66').attr('name','portal_user_val6');
                        $('#portal_user_val6').hide();
                        $('#portal_user_val6').attr('name','aaa');
                        $('#btnChangeValComponent2').val("*");
                    }else{
                        $('#portal_user_val66').hide();
                        $('#portal_user_val66').attr('name','aaa');
                        $('#portal_user_val6').show();
                        $('#portal_user_val6').attr('name','portal_user_val6');
                        $('#btnChangeValComponent2').val("+");
                    }
                })
                $("#upload_btn_portalUser_pic1").button().click(function(){});
                $("#portal_user_UserId").change(function(){
                    cmsPortalUser.m_getUsersInfo(jj('#portal_user_UserId').jjVal());
                });
                portal_user_content_editor = new jj('#portal_user_content').jjEditor();
                cmsPortalUser.m_getUsersCode();
            });
        }                
    },  
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swPortalUsersTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsPortalUser.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swPortalUsersTbl').hide();
        $('#swPortalUsersForm').show();
        cmsPortalUser.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swPortalUsersForm").jjFormClean();
        $("#preview_portalUser_pic").attr('src','img/preview.jpg');
        new jj(portal_user_content_editor).jjEditorVal("");//Notice slector is an object that created at the end of loadForm(){};
    },
    m_add_new:function(){
        alert("cmsPortalUser.m_add_new();");
        jj("do="+cmsPortalUser.tableName+".add_new").jjAjax2(false);
        cmsPortalUser.m_show_form();
        cmsPortalUser.m_clean();
        cmsPortalUser.m_getUsersCode();
        cmsPortalUser.getVal5List();
        cmsPortalUser.getVal6List();
    },
    m_getUsersCode:function(id){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".getUsersCode";
        param += "&panel=" +  "portal_user_UserId";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    m_getUsersInfo:function(id){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".getUsersInfo";
        param += "&panel=" + "portal_user_UserId";
        param += "&userId="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    m_show_tbl:function(){
        $('#swPortalUsersTbl').show();
        $('#swPortalUsersForm').hide();
        alert("cmsPortalUser.m_show_tbl();")
        if($('#swPortalUsersTbl').html()==""){
            cmsPortalUser.m_refresh();
        }
        cmsPortalUser.tabSizeTbl();
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".insert";
        param += "&" +new jj("#swPortalUsersForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsPortalUser.m_show_tbl();
        cmsPortalUser.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".edit";
        param += "&" +new jj("#swPortalUsersForm").jjSerial();
        jj(param).jjAjax2(false);
        cmsPortalUser.m_show_tbl();
        cmsPortalUser.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsPortalUser.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".delete";
        param += "&" + cmsPortalUser.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsPortalUser.m_show_tbl();
        cmsPortalUser.m_clean();
    }, 
    m_select:function(id){
        cmsPortalUser.m_getValuesList(id);
        var param = "";
        param += "do="+cmsPortalUser.tableName+".select";
        param += "&" + cmsPortalUser.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsPortalUser.m_show_form();
    }, 
    /*
     *send a commant to server for fill "selcet" and "option" distinct from database
     *[id] is for selected option [selecte=xxx}
     * use in forms ( for example portal_post.html in cms)
     */
    m_getValuesList:function(id){
        cmsPortalUser.getVal5List(id);
        cmsPortalUser.getVal6List(id);      
    },
    getVal5List:function(id){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".get_val5List";
        param += "&panel=" +  "portal_user_val5";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    getVal6List:function(id){
        var param = "";
        param += "do="+cmsPortalUser.tableName+".get_val6List";
        param += "&panel=" +  "portal_user_val6";
        param += "&selected="+ (id==null?"":id);
        jj(param).jjAjax2(false);
    },
    tabSizeTbl: function () {
        $('#swPortalAll').css('height',519);
        cmsPortalUser.heightTab = 519;
    },
    tabSizeForm: function () {
        $('#swPortalAll').css('height',115);
    //        cmsPortalUser.heightTab = 115;
    },
    heightTab:"519",
    mainTabSetSize : function () {
        $('#swPortalAll').css('height',cmsPortalUser.heightTab);
    }
}
