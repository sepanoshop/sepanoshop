CKEDITOR.editorConfig = function( config )
{
    // Define changes to default configuration here. For example:
    config.language = 'fa';
    config.uiColor = '#dfe3f6';
    //          config.toolbar = 'Basic';
    //    CKEDITOR.config.toolbar_Basic = [ [ 'Source', '-', 'Bold', 'Italic' ] ];
    config.font_defaultLabel = 'Arial';
//    config.toolbar_Full =
//    [{
//        name: 'document', 
//        items : [ 'Source','-','NewPage','DocProps','Preview','Print','-','Templates' ]
//    },
//    {
//        name: 'tools', 
//        items : [ 'Maximize', 'ShowBlocks' ]
//    },{
//        name: 'clipboard', 
//        items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ]
//    },{
//        name: 'editing', 
//        items : [ 'Find','Replace','-' ]
//    },{
//        name: 'links', 
//        items : [ 'Link' ]
//    },{
//        name: 'insert', 
//        items : [ 'Image','Table','HorizontalRule','SpecialChar','PageBreak']
//    },{
//        name: 'image', 
//        items : [ 'Table','HorizontalRule','SpecialChar','PageBreak']
//    }, {
//        name: 'colors', 
//        items : [ 'TextColor','BGColor' ]
//    },{
//        name: 'basicstyles', 
//        items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript' ]
//    },{
//        name: 'styles', 
//        items : [ 'Font','FontSize','Format' ]//Styles
//    }, {
//        name: 'paragraph', 
//        items : [ 'NumberedList','BulletedList',
//        '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ]
////    }, {
////        name: 'paragraph2', 
////        items : [ 'MyButton' ]
//    }
//    
//    ]
    config.toolbar_Full =
    [{
        name: 'document', 
        items : [ 'Source','-','Save','NewPage','DocProps','Preview','Print','-','Templates' ]
    },{
        name: 'clipboard', 
        items : [ 'Cut','Copy','Paste','PasteText','PasteFromWord','-','Undo','Redo' ]
    },{
        name: 'editing', 
        items : [ 'Find','Replace','-','SelectAll','-','SpellChecker', 'Scayt' ]
    },{
        name: 'forms', 
        items : [ 'Form', 'Checkbox', 'Radio', 'TextField', 'Textarea', 'Select', 'Button', 'ImageButton', 
        'HiddenField' ]
    },'/',{
        name: 'basicstyles', 
        items : [ 'Bold','Italic','Underline','Strike','Subscript','Superscript','-','RemoveFormat' ]
    },{
        name: 'paragraph', 
        items : [ 'NumberedList','BulletedList','-','Outdent','Indent','-','Blockquote','CreateDiv',
        '-','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','BidiLtr','BidiRtl' ]
    },{
        name: 'links', 
        items : [ 'Link','Unlink','Anchor' ]
    },{
        name: 'insert', 
        items : [ 'Image','Flash','Table','HorizontalRule','Smiley','SpecialChar','PageBreak','Iframe' ]
    },'/',{
        name: 'styles', 
        items : [ 'Styles','Format','Font','FontSize' ]
    },{
        name: 'colors', 
        items : [ 'TextColor','BGColor' ]
    }, {
        name: 'tools', 
        items : [ 'Maximize', 'ShowBlocks','-','About' ]
    }
    ]
};
