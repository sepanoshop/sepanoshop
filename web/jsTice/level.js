var tice_level ={
    tableName : "Tice_level",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
        if($("#swLevelForm").html()==''){
            $("#swLevelForm").load("formTice/tice_level.html", null, function(){
                $("#cancel_Level").button().click(function(e) {
                    tice_level.m_clean();
                    tice_level.m_show_tbl();
                });
                
                
                new jj('#tice_level_fail').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_conditional').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_pass').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_good').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_out1').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_out2').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_out3').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_out4').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_out5').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_out6').jjSetTextFieldOnlyGetNumber(3);
                new jj('#tice_level_out7').jjSetTextFieldOnlyGetNumber(3);
                
                $('#btnChangeLevelComponent1').click(function(){
                    if($('#tice_level_des11').css('display')=='none'){
                        $('#tice_level_des11').show();
                        $('#tice_level_des11').attr('name','tice_level_des1');
                        $('#tice_level_des1').hide();
                        $('#tice_level_des1').attr('name','aaa');
                    }else{
                        $('#tice_level_des11').hide();
                        $('#tice_level_des11').attr('name','aaa');
                        $('#tice_level_des1').show();
                        $('#tice_level_des1').attr('name','tice_level_des1');
                    }
                })
                
                $('#btnChangeLevelComponent2').click(function(){
                    if($('#tice_level_des22').css('display')=='none'){
                        $('#tice_level_des22').show();
                        $('#tice_level_des22').attr('name','tice_level_des2');
                        $('#tice_level_des2').hide();
                        $('#tice_level_des2').attr('name','aaa');
                    }else{
                        $('#tice_level_des22').hide();
                        $('#tice_level_des22').attr('name','aaa');
                        $('#tice_level_des2').show();
                        $('#tice_level_des2').attr('name','tice_level_des2');
                    }
                })
                
                $('#btnChangeLevelComponent3').click(function(){
                    if($('#tice_level_des33').css('display')=='none'){
                        $('#tice_level_des33').show();
                        $('#tice_level_des33').attr('name','tice_level_des3');
                        $('#tice_level_des3').hide();
                        $('#tice_level_des3').attr('name','aaa');
                    }else{
                        $('#tice_level_des33').hide();
                        $('#tice_level_des33').attr('name','aaa');
                        $('#tice_level_des3').show();
                        $('#tice_level_des3').attr('name','tice_level_des3');
                    }
                })
                
                $('#btnChangeLevelComponent4').click(function(){
                    if($('#tice_level_des44').css('display')=='none'){
                        $('#tice_level_des44').show();
                        $('#tice_level_des44').attr('name','tice_level_des4');
                        $('#tice_level_des4').hide();
                        $('#tice_level_des4').attr('name','aaa');
                    }else{
                        $('#tice_level_des44').hide();
                        $('#tice_level_des44').attr('name','aaa');
                        $('#tice_level_des4').show();
                        $('#tice_level_des4').attr('name','tice_level_des4');
                    }
                })
                
                $('#btnChangeLevelComponent5').click(function(){
                    if($('#tice_level_des55').css('display')=='none'){
                        $('#tice_level_des55').show();
                        $('#tice_level_des55').attr('name','tice_level_des5');
                        $('#tice_level_des5').hide();
                        $('#tice_level_des5').attr('name','aaa');
                    }else{
                        $('#tice_level_des55').hide();
                        $('#tice_level_des55').attr('name','aaa');
                        $('#tice_level_des5').show();
                        $('#tice_level_des5').attr('name','tice_level_des5');
                    }
                })
                
                $('#btnChangeLevelComponent6').click(function(){
                    if($('#tice_level_des66').css('display')=='none'){
                        $('#tice_level_des66').show();
                        $('#tice_level_des66').attr('name','tice_level_des6');
                        $('#tice_level_des6').hide();
                        $('#tice_level_des6').attr('name','aaa');
                    }else{
                        $('#tice_level_des66').hide();
                        $('#tice_level_des6').attr('name','aaa');
                        $('#tice_level_des6').show();
                        $('#tice_level_des6').attr('name','tice_level_des6');
                    }
                })
                
                $('#btnChangeLevelComponent7').click(function(){
                    if($('#tice_level_des77').css('display')=='none'){
                        $('#tice_level_des77').show();
                        $('#tice_level_des77').attr('name','tice_level_des7');
                        $('#tice_level_des7').hide();
                        $('#tice_level_des7').attr('name','aaa');
                    }else{
                        $('#tice_level_des77').hide();
                        $('#tice_level_des77').attr('name','aaa');
                        $('#tice_level_des7').show();
                        $('#tice_level_des7').attr('name','tice_level_des7');
                    }
                })
                manageDropDown.getListBookForLevel();
            });
            $("#swLevelForm").hide();
            tice_level.m_refresh("swLevelTbl");
            tice_level.getExamNameList("tice_level_des1,tice_level_des2,tice_level_des3,tice_level_des4,tice_level_des5,tice_level_des6,tice_level_des7");
        }
       
    },
    m_refresh:function( containerId,sortField,tableHeight){
        var param = "";
        param += "do="+tice_level.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swLevelTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? PanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        tice_level.tabSizeTbl();
    }, 
    m_show_form:function(){
        $('#swLevelTbl').hide();
        $('#swLevelForm').show();
        tice_level.tabSizeForm();
        
        $('#tice_level_des11').hide();
        $('#tice_level_des11').attr('name','aaa');
        $('#tice_level_des1').show();
        $('#tice_level_des1').attr('name','tice_level_des1');
        
        $('#tice_level_des22').hide();
        $('#tice_level_des22').attr('name','aaa');
        $('#tice_level_des2').show();
        $('#tice_level_des2').attr('name','tice_level_des2');
        
        $('#tice_level_des33').hide();
        $('#tice_level_des33').attr('name','aaa');
        $('#tice_level_des3').show();
        $('#tice_level_des3').attr('name','tice_level_des3');
        
        $('#tice_level_des44').hide();
        $('#tice_level_des44').attr('name','aaa');
        $('#tice_level_des4').show();
        $('#tice_level_des4').attr('name','tice_level_des4');
        
        $('#tice_level_des55').hide();
        $('#tice_level_des55').attr('name','aaa');
        $('#tice_level_des5').show();
        $('#tice_level_des5').attr('name','tice_level_des5');
        
        $('#tice_level_des66').hide();
        $('#tice_level_des66').attr('name','aaa');
        $('#tice_level_des6').show();
        $('#tice_level_des6').attr('name','tice_level_des6');
        
        $('#tice_level_des77').hide();
        $('#tice_level_des77').attr('name','aaa');
        $('#tice_level_des7').show();
        $('#tice_level_des7').attr('name','tice_level_des7');
                    
    },
    m_clean:function(){
        new jj("#swLevelForm").jjFormClean();
       
    },
    m_add_new:function(){
        jj("do="+tice_level.tableName+".add_new").jjAjax2(false);
        tice_level.m_show_form();
        tice_level.m_clean();
    //        oEditor.execCommand( 'bold');
    },
    m_show_tbl:function(){
        $('#swLevelTbl').show();
        $('#swLevelForm').hide();
        if($('#swLevelTbl').html()==""){
            tice_level.m_refresh();
        }
        tice_level.tabSizeTbl();
    //        $('#refreshLevel').dataTable().fnSort( [ [0,'desc'] ] );
    },
    m_insert:function(){
        if(!tice_level.m_valid()){
            return false;
        }
        var valid = tice_level.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+tice_level.tableName+".insert";
            param += "&"+ new jj('#levelDivFormSerial').jjSerial();
            jj(param).jjAjax2(false);
            tice_level.m_show_tbl();
            tice_level.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    },
    m_valid:function(){
        if(new jj('#tice_level_name').jjVal().length<2 ){
            $('#errorPanelForLevelForm').html('لطفا فیلد نام را وارد کنید.');
            return false;
        }
        if( !new jj(new jj('#tice_level_fail').jjVal()).jjIsDigit() ||
            !new jj(new jj('#tice_level_conditional').jjVal()).jjIsDigit() ||
            !new jj(new jj('#tice_level_pass').jjVal()).jjIsDigit()){
            $('#errorPanelForLevelForm').html('ابتدا نمرات مربوط به مردودی ها و ... را وارد کنید.');
            return false;
        }
        return true;
    },
    m_edit:function(){
        if(!tice_level.m_valid()){
            return false;
        }
        var valid = tice_level.m_validation();
        if(valid==""){
            var param = "";
            param += "do="+tice_level.tableName+".edit";
            param += "&"+ new jj('#levelDivFormSerial').jjSerial();
            jj(param).jjAjax2(false);
            tice_level.m_show_tbl();
            tice_level.m_clean();
        }else{
            new jj(valid).jjDialog();   
        }
    },
    m_validation:function(){// mohamdad
        //        if(new jj("#content_title").jjVal().length<1){
        //            return "فیلد عنوان نباید کوچکتر از دو کاراکتر باشد";
        //        }
        return "";
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('tice_level.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+tice_level.tableName+".delete";
        param += "&" + tice_level.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        tice_level.m_show_tbl();
        tice_level.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+tice_level.tableName+".select";
        param += "&" + tice_level.f_id + "=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        tice_level.m_show_form();
    }, 
    tabSizeTbl: function () {
        $('#swLevelAll').css('height',520);
        tice_level.heightTab = 520;
    },
    tabSizeForm: function () {
        $('#swLevelAll').css('height',405);
        tice_level.heightTab = 405;
    },
    heightTab:"520",
    mainTabSetSize : function () {
        $('#swLevelAll').css('height',tice_level.heightTab);
    },
    getLevelList:function(selectPanel){        
        var param = "";
        param += "do="+tice_level.tableName+".getLevelList";
        param += "&panel=" + selectPanel;
        jj(param).jjAjax2(false);
    },
    getExamNameList:function(selectPanel){        
        var param = "";
        param += "do="+tice_level.tableName+".getExamNameList";
        param += "&panel=" + selectPanel;
        jj(param).jjAjax2(true);
    }
    
}
