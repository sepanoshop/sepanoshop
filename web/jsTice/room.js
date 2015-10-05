var cmsTiceRoom ={
    tableName : "Tice_rooms",
    loadForm:function(){
        if($("#swRoomForm").html()==''){
            $("#swRoomForm").load("formTice/tice_room.html", null, function(){
                $("#cancel_Room").button().click(function(e) {
                    cmsTiceRoom.m_clean();
                    cmsTiceRoom.m_show_tbl();
                });
            });
            cmsTiceRoom.m_refresh();
        }
    },
    m_refresh:function(containerId, sortField, tableHeight){
        var param = "";
        param += "do="+cmsTiceRoom.tableName+".refresh";
        param += "&panel=" + (containerId==null ? "swRoomTbl" : containerId);
        param += "&sort=" + (sortField==null ? "0" : sortField);
        param += "&height=" + (tableHeight==null ? innerPanelHeight : tableHeight);
        jj(param).jjAjax2(false);
        cmsTiceRoom.tabSizeTbl();
    },
    m_show_form:function(){
        $('#swRoomTbl').hide();
        $('#swRoomForm').show();
        cmsTiceRoom.tabSizeForm();
    },
    m_clean:function(){
        new jj("#swRoomForm").jjFormClean();
    },
    m_add_new:function(){
        jj("do="+cmsTiceRoom.tableName+".add_new").jjAjax2(false);
        cmsTiceRoom.m_show_form();
        cmsTiceRoom.m_clean();
    },
    m_show_tbl:function(){
        $('#swRoomTbl').show();
        $('#swRoomForm').hide();
        if($('#swRoomTbl').html()==""){
            cmsTiceRoom.m_refresh();
        }
        cmsTiceRoom.tabSizeTbl();
//        $('#refreshTiceRoom').dataTable().fnSort( [ [0,'desc'] ] );
    },
    m_insert:function(){
        var param = "";
        param += "do="+cmsTiceRoom.tableName+".insert";
        param += "&"+ new jj('#swRoomForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsTiceRoom.m_show_tbl();
        cmsTiceRoom.m_clean();
    },
    m_edit:function(){
        var param = "";
        param += "do="+cmsTiceRoom.tableName+".edit";
        param += "&"+ new jj('#swRoomForm').jjSerial();
        jj(param).jjAjax2(false);
        cmsTiceRoom.m_show_tbl();
        cmsTiceRoom.m_clean();
    }, 
    m_delete:function(id){
        new jj("آیا از حذف این رکورد اطمینان دارید؟").jjDialog_YesNo('cmsTiceRoom.m_delete_after_question('+id+');\n', true, "");
    }, 
    m_delete_after_question:function(id){
        var param = "";
        param += "do="+cmsTiceRoom.tableName+".delete";
        param += "&id=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsTiceRoom.m_show_tbl();
        cmsTiceRoom.m_clean();
    }, 
    m_select:function(id){
        var param = "";
        param += "do="+cmsTiceRoom.tableName+".select";
        param += "&id=" + (id==null ? "" : id);
        jj(param).jjAjax2(false);
        cmsTiceRoom.m_show_form();
    } ,
    
    tabSizeTbl: function () {
        $('#swRoomAll').css('height',478);
        cmsTiceRoom.heightTab = 478;
    },
    tabSizeForm: function () {
        $('#swRoomAll').css('height',100);
        cmsTiceRoom.heightTab = 100;
    },
    heightTab:"480",
    mainTabSetSize : function () {
        $('#swRoomAll').css('height',cmsTiceRoom.heightTab);
    }
}
