var tice_company_detail ={
    tableName : "Tice_school",
    f_id :"id",
    f_content_id :"content_id",
    f_parent:"content_parent",
    f_content:"content_content",
    f_title:"content_title",
    f_lang:"content_lang",
    loadForm:function(){
        if($("#swCompanyDetailForm").html()==''){
            $("#swCompanyDetailForm").load("formTice/company_detail.html", null, function(){
                $("#cancel_CompanyDetail").button().click(function(e) {
                    tice_company_detail.m_clean();
                    tice_company_detail.m_select();
                });
                $("#printAllRezerv").button().click(function(e) {
                    printAllRezerv();
                });
                $("#printAllFreeTime").button().click(function(e) {
                    printAllFreeTime();
                });
                $("#printAllEteraz").button().click(function(e) {
                    printAllEteraz();
                });
                $("#printAllEstekhdam").button().click(function(e) {
                    printAllEstekhdam();
                });
                tice_company_detail.m_select();
                jj("do=Tice_term.getTermListInReport&panel=" +"noRegist_from,noRegist,mardoodha,absents,mashrootiha,ghabool,exelentStd,bedehkar,takhfif").jjAjax2(false);
                new jj('do=Tice_report.getLastTerm').jjAjax();
                new jj('do=Tice_report.getLastTermForLogin').jjAjax();
                new jj('do=Tice_report.getPollQuestion').jjAjax();
                new jj('do=Tice_report.getMavaredeClassActivity').jjAjax();
                //                tice_term.getTermList("noRegist");
                //                tice_term.getTermList("mardoodha");
                //                tice_term.getTermList("mashrootiha");
                //                tice_term.getTermList("ghabool");
                //                tice_term.getTermList("exelentStd");
                //                tice_term.getTermList("bedehkar");
                //                tice_term.getTermList("takhfif");
                $("#mashrootiha_btn").button().click(function(e) {
                    new jj('do=Tice_report.refresh&term='+(new jj("#mashrootiha").jjVal()+'&getWhat=mashroot')).jjAjax();
                });
                $("#absents_btn").button().click(function(e) {
                    new jj('do=Tice_report.refresh&term='+(new jj("#absents").jjVal()+'&getWhat=absents')).jjAjax();
                });
                $("#mardoodha_btn").button().click(function(e) {
                    new jj('do=Tice_report.refresh&term='+(new jj("#mardoodha").jjVal()+'&getWhat=mardood')).jjAjax();
                });
                $("#ghabool_btn").button().click(function(e) {
                    new jj('do=Tice_report.refresh&term='+(new jj("#ghabool").jjVal()+'&getWhat=ghabool')).jjAjax();
                });
                $("#exelentStd_btn").button().click(function(e) {
                    new jj('do=Tice_report.refresh&term='+(new jj("#exelentStd").jjVal()+'&getWhat=exelentStd')).jjAjax();
                });
                $("#absents_print_btn").button().click(function(e) {
                    new jj('Print2?form=refresh&term='+(new jj("#absents").jjVal()+'&getWhat=absents')).jjGoNewPage();
                });
                $("#mashrootiha_print_btn").button().click(function(e) {
                    new jj('Print2?form=refresh&term='+(new jj("#mashrootiha").jjVal()+'&getWhat=mashroot')).jjGoNewPage();
                });
                $("#mardoodha_print_btn").button().click(function(e) {
                    new jj('Print2?form=refresh&term='+(new jj("#mardoodha").jjVal()+'&getWhat=mardood')).jjGoNewPage();
                });
                $("#ghabool_print_btn").button().click(function(e) {
                    new jj('Print2?form=refresh&term='+(new jj("#ghabool").jjVal()+'&getWhat=ghabool')).jjGoNewPage();
                });
                $("#exelentStd_print_btn").button().click(function(e) {
                    new jj('Print2?form=refresh&term='+(new jj("#exelentStd").jjVal()+'&getWhat=exelentStd')).jjGoNewPage();
                });
                $("#takhfif_print_btn").button().click(function(e) {
                    new jj('Print2?form=refreshBedehkar&term='+(new jj("#takhfif").jjVal()+'&getWhat=takhfif')).jjGoNewPage();
                });
                $("#noRegist_btn").button().click(function(e) {
                    new jj('do=Tice_report.noRegistInNextTerm&fromTerm='+(new jj("#noRegist_from").jjVal()
                    +'&toTerm='+(new jj("#noRegist").jjVal())+'&getWhat=noRegist')).jjAjax();
                });
                $("#noRegist_print_btn").button().click(function(e) {
                    new jj('Print2?form=refreshNoRegist&fromTerm='+(new jj("#noRegist_from").jjVal())+"&toTerm="+(new jj("#noRegist").jjVal()+'&getWhat=noRegist')).jjGoNewPage();
                });
                $("#bedehkar_btn").button().click(function(e) {
                    new jj('do=Tice_report.refreshBedehkar&term='+(new jj("#bedehkar").jjVal()+'&getWhat=bedehkar')).jjAjax();
                });
                $("#lastTerm_btn").button().click(function(e) {
                    new jj('do=Tice_report.setLastTerm&term='+(new jj("#lastTerm").jjVal())).jjAjax();
                });
                $("#lastTermForLogin_btn").button().click(function(e) {
                    new jj('do=Tice_report.setlastTermForLogin&term='+(new jj("#lastTermForLogin").jjVal())).jjAjax();
                });
                $("#setFreeTime_btn").button().click(function(e) {
                    var param = 'do=Tice_report.setFreeTime';
                    param += '&t1='+(new jj("#setFreeTimt_1").jjVal());
                    param += '&t2='+(new jj("#setFreeTimt_2").jjVal());
                    param += '&t3='+(new jj("#setFreeTimt_3").jjVal());
                    param += '&t4='+(new jj("#setFreeTimt_4").jjVal());
                    param += '&t5='+(new jj("#setFreeTimt_5").jjVal());
                    param += '&t6='+(new jj("#setFreeTimt_6").jjVal());
                    param += '&t7='+(new jj("#setFreeTimt_7").jjVal());
                    new jj(param).jjAjax();
                });
                $("#setPollToFile_btn").button().click(function(e) {
                    var param = 'do=Tice_report.setPollToFile&';
                    param += (new jj("#pollQuestionInClassForm").jjSerial());
                    new jj(param).jjAjax();
                });
                $("#bedehkar_print_btn").button().click(function(e) {
                    printBedehkaran((new jj("#bedehkar").jjVal()))
                });
//                $("#bedehkar_print_btn_all").button().click(function(e) {
//                    jj('Print?form=bedehkar_all').jjGoNewPage();
//                });
                $("#takhfif_btn").button().click(function(e) {
                    new jj('do=Tice_report.refreshBedehkar&term='+(new jj("#takhfif").jjVal()+'&getWhat=takhfif')).jjAjax();
                });
                $("#same_userPass_btn").button().click(function(e) {
                    new jj('do=Tice_report.getSameUserPass').jjAjax();
                });
                $("#same_userFamily_btn").button().click(function(e) {
                    new jj('do=Tice_report.getSame_userFamily').jjAjax();
                });
                $("#mavaredeClassActivity_btn").button().click(function(e) {
                    new jj('do=Tice_report.setMavaredeClassActivity'+"&text="+new jj('#mavaredeClassActivity').jjVal()).jjAjax();
                });
                new jj("#birth_month").jjSetTextFieldOnlyGetNumber(2);
                new jj("#birth_day").jjSetTextFieldOnlyGetNumber(2);
                $("#birth_btn").button().click(function(e) {
                    var birth =new jj("#birth_month").jjVal().length==1?"0"+new jj("#birth_month").jjVal():new jj("#birth_month").jjVal();
                    birth += new jj("#birth_day").jjVal().length==1?"0"+new jj("#birth_day").jjVal():new jj("#birth_day").jjVal();
                    new jj('do=Tice_report.birth&birth='+(birth)).jjAjax();
                });
                $("#registNoChart_btn").button().click(function(e) {
                    printregistNoTermChart();
                });
                $("#registNoLevelChart_btn").button().click(function(e) {
                    printregistNoLevelChart();
                });
                $("#registNoTermPriceChart_btn").button().click(function(e) {
                    printregistNoTermPriceChart();
                });
                //By Md
                $("#report_economic_btn").button().click(function(e) {
                    printEconomicReport();
                });
                $("#report_loyal_std_btn").button().click(function(e) {
                    printLoyaltyReport();
                });
                // get All List
                $("#print2AllStudent").button().click(function(e) {
                    new jj('Print2?form=print2AllStudent').jjGoNewPage();
                });
                $("#print2AllEnrol").button().click(function(e) {
                    new jj('Print2?form=print2AllEnrol').jjGoNewPage();
                });
                $("#print2AllClass").button().click(function(e) {
                    new jj('Print2?form=print2AllClass').jjGoNewPage();
                });
                $("#print2AllTeacher").button().click(function(e) {
                    new jj('Print2?form=print2AllTeacher').jjGoNewPage();
                });
                $("#print2AllFish").button().click(function(e) {
                    new jj('Print2?form=print2AllFish').jjGoNewPage();
                });
                $("#print2AllLevel").button().click(function(e) {
                    new jj('Print2?form=print2AllLevel').jjGoNewPage();
                });
                $("#print2AllTerm").button().click(function(e) {
                    new jj('Print2?form=print2AllTerm').jjGoNewPage();
                });
                $("#print2AllRoom").button().click(function(e) {
                    new jj('Print2?form=print2AllRoom').jjGoNewPage();
                });
                $("#print2AllBook").button().click(function(e) {
                    new jj('Print2?form=print2AllBook').jjGoNewPage();
                });
                $("#print2AllWorkBook").button().click(function(e) {
                    new jj('Print2?form=print2AllWorkBook').jjGoNewPage();
                });
                //By Md
                new jj("#smsDateFrom").jjCalendar();
                new jj("#smsDateTo").jjCalendar();
                $("#DeleteSms_btn").button().click(function(e) {
                    var msg="ایا از حذف پیامک ها دراین بازه زمانی اطمینان دارید؟";
                    if(new jj("#smsDateFrom").jjVal()=="" && new jj("#smsDateTo").jjVal()==""
                        && new jj("#smsType").jjVal()==0){
                        msg="آیا تمام پیامک ها حذف شوند؟";
                    }
                    if(confirm(msg)){ 
                        var param = 'do=Tice_sms.delete';
                        param += '&subject='+(new jj("#smsType").jjVal());
                        param += '&smsDateFrom='+(new jj("#smsDateFrom").jjVal());  
                        param += '&smsDateTo='+(new jj("#smsDateTo").jjVal());
                        new jj(param).jjAjax();
                    }
            });
                $("#PrintSmsReport_btn").button().click(function(e) {
                    printSmsReport();
                });  
            });
        }
    },
    m_select:function(){
        var param = "";
        param += "do="+tice_company_detail.tableName+".select";
        param += "&" + tice_company_detail.f_id + "=" + (1);
        jj(param).jjAjax2(false);
    },
    m_clean:function(){
    //        new jj("#swCompanyDetailForm").jjFormClean();
       
    },
    m_edit:function(){
        var param = "";
        param += "do="+tice_company_detail.tableName+".edit";
        param += "&"+ new jj('#swCompanyDetailForm').jjSerial();                
        jj(param).jjAjax2(false);
          
    }
}