var cmsTiceBook ={
    tableName : "Tice_books",
    loadForm:function(){
        if($("#swBookForm").html()==''){
            $("#swBookForm").load("formTice/tice_book.html", null, function(){
                $("#cancel_Book").button().click(function(e) {
                    cmsTiceBook.m_clean();
                    cmsTiceBook.m_show_tbl();
                });
                cmsTiceBook.m_refresh("swBookTbl");
            });
        }
    },
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsTiceBook.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swBookTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsTiceBook.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swBookTbl').hide();
        $('#swBookForm').show();
        cmsTiceBook.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swBookForm").jjFormClean();
    },
    m_add_new:function(){
        jj("do="+cmsTiceBook.tableName+".add_new").jjAjax2(false);
        cmsTiceBook.m_show_form();
        cmsTiceBook.m_clean();
    },
    m_show_tbl:function(){
        $('#swBookTbl').show();
        $('#swBookForm').hide();
        if($('#swBookTbl').html()==""){
            cmsTiceBook.m_refresh();
        }
        cmsTiceBook.tabSizeTbl();
//        $('#refreshTiceBook').dataTable().fnSort( [ [0,'asc'] ] );
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsTiceBook.tableName+".insert";
        param += "&"+ new jj('#swBookForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsTiceBook.m_show_tbl();
        cmsTiceBook.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsTiceBook.tableName+".edit";
        param += "&"+ new jj('#swBookForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsTiceBook.m_show_tbl();
        cmsTiceBook.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsTiceBook.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsTiceBook.tableName+".delete";
        param += "&id=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsTiceBook.m_show_tbl();
        cmsTiceBook.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsTiceBook.tableName+".select";
        param += "&id=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsTiceBook.m_show_form();
    } ,
    
    tabSizeTbl: function () {
        $('#swBookAll').css('height',478);
        cmsTiceBook.heightTab = 478;
    },
    tabSizeForm: function () {
        $('#swBookAll').css('height',100);
        cmsTiceBook.heightTab = 100;
    },
    heightTab:"480",
    mainTabSetSize : function () {
        $('#swBookAll').css('height',cmsTiceBook.heightTab);
    }
}
