function changeAllPrice(int1, int2) {
    var mess = "آیا مایلید کلیه محصولاتی که قیمت آنها f1 به قیمت f2 تغییر کند؟";
    jj(mess.replace('f1',int1).replace('f2',int2)).jjDialog_YesNo("changeAllPrice2(" + int1 + "," + int2 + ");");
}
function changeAllPrice2(int1, int2) {
    var param = "";
    param += "do=Pic.changeAllPrice";
    param += "&int1=" + int1;
    param += "&int2=" + int2;
    jj(param).jjAjax2(false);
}
