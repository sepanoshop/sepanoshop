/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

/**
 *
 * @author Milad
 */
public class jjChart {

    private static long YYYY, m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11, m12,
            n1, n2, n3, n4, n5, n6, n7, n8, n9, n10, n11, n12,
            n13, n14, n15, n16, n17, n18, n19, n20, n21, n22, n23, n24,
            n25, n26, n27, n28, n29, n30, n31;

    /**
     *
     * get HTML Chart
     */
    public static void doShowChart(int year, long m1, long m2, long m3, long m4, long m5, long m6, long m7, long m8, long m9, long m10, long m11, long m12,
            long n1, long n2, long n3, long n4, long n5, long n6, long n7, long n8, long n9, long n10, long n11, long n12) {

        YYYY = year;
        jjChart.m1 = m1;
        jjChart.m2 = m2;
        jjChart.m3 = m3;
        jjChart.m4 = m4;
        jjChart.m5 = m5;
        jjChart.m6 = m6;
        jjChart.m7 = m7;
        jjChart.m8 = m8;
        jjChart.m9 = m9;
        jjChart.m10 = m10;
        jjChart.m11 = m11;
        jjChart.m12 = m12;
        jjChart.n1 = n1;
        jjChart.n2 = n2;
        jjChart.n3 = n3;
        jjChart.n4 = n4;
        jjChart.n5 = n5;
        jjChart.n6 = n6;
        jjChart.n7 = n7;
        jjChart.n8 = n8;
        jjChart.n9 = n9;
        jjChart.n10 = n10;
        jjChart.n11 = n11;
        jjChart.n12 = n12;
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    long sum = jjChart.m1 + jjChart.m2 + jjChart.m3 + jjChart.m4 + jjChart.m5 + jjChart.m6 + jjChart.m7
                            + jjChart.m8 + jjChart.m9 + jjChart.m10 + jjChart.m11 + jjChart.m12;
                    String html = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=windows-1252'><title>Benco Chart</title></head><body topmargin='5' leftmargin='5' rightmargin='5' bottommargin='5' marginwidth='5' marginheight='5'><BR>"
                            + "<p align='center'><font face='Tahoma' size='4'>YYYY<span lang='fa'> &#1606;&#1605;&#1608;&#1583;&#1575;&#1585;&#1587;&#1575;&#1604;&#1575;&#1606;&#1607;</span><br></font><br></p><TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 align=center>"
                            + "<TR bgcolor=lightgrey> <TH width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#000099'> <font face='Tahoma' color='#FFFFFF' size='2'>&#1605;&#1575;&#1607;</font>"
                            + "</TH> <TH width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#000099'> <span lang='fa'><font face='Tahoma' color='#FFFFFF'>&#1583;&#1585;&#1589;&#1583; &#1601;&#1585;&#1608;&#1588;</font></span></TH>"
                            + " <TH width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#000099'> <font face='Tahoma' color='#FFFFFF'><span lang='fa'>&#1601;&#1585;&#1608;&#1588;</span></font></TH> "
                            + "<TH width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#000099'> <font face='Tahoma' color='#FFFFFF'><span lang='fa'>&#1607;&#1586;&#1740;&#1606;&#1607;</span></font>"
                            + "</TH> <TH width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#000099'> <font face='Tahoma' color='#FFFFFF'><span lang='fa'>&#1587;&#1608;&#1583;</span></font></TH>"
                            + "</TR> <TR>  <TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'> <span lang='fa'><font face='Tahoma' size='2'>&#1601;&#1585;&#1608;&#1585;&#1583;&#1740;&#1606;"
                            + "</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'> <TABLE> <TR>  <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gs/s.gif' width=DD01 height=1>"
                            + "</font></TD>  <TD><font size='2' face='Times New Roman'>PP01%</font></TD>  </TR> </TABLE>  </TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>RR01</font></TD>"
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>KK01</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'>"
                            + "<font face='Times New Roman'>ZZ01</font></TD> </TR> <TR>  <TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'> <span lang='fa'>"
                            + "<font face='Tahoma' size='2'>&#1575;&#1585;&#1583;&#1740;&#1576;&#1607;&#1588;&#1578;</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'> "
                            + "<TABLE> <TR>  <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD02 height=1></font></TD>  <TD><font size='2' face='Times New Roman'>PP02%</font></TD></TR></TABLE>  </TD>  "
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>RR02</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> "
                            + "<font face='Times New Roman'>KK02</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>ZZ02</font></TD> </TR>  <TR>"
                            + "<TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'> <span lang='fa'><font face='Tahoma' size='2'>&#1582;&#1585;&#1583;&#1575;&#1583;</font></span></TD> "
                            + "<TD valign=middle width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'> <TABLE>  <TR> <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD03 height=1></font>"
                            + "</TD>  <TD><font size='2' face='Times New Roman'>PP03%</font></TD>  </TR> </TABLE>  </TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>RR03</font></TD>"
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>KK03</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'>"
                            + "<font face='Times New Roman'>ZZ03</font></TD> </TR> <TR>  <TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'> <span lang='fa'>"
                            + "<font face='Tahoma' size='2'>&#1578;&#1740;&#1585;</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'> <TABLE> <TR>  "
                            + "<TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD04 height=1></font></TD>  <TD><font size='2' face='Times New Roman'>PP04%</font></TD>  </TR> </TABLE>  </TD>  "
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>RR04</font></TD>  "
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>KK04</font></TD>"
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>ZZ04</font></TD> </TR> "
                            + "<TR>  <TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'> <span lang='fa'>"
                            + "<font face='Tahoma' size='2'>&#1605;&#1585;&#1583;&#1575;&#1583;</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>"
                            + "<TABLE> <TR>  <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD05 height=1></font></TD>  <TD><font size='2' face='Times New Roman'>PP05%</font></TD>  </TR> </TABLE>  </TD>  "
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>RR05</font></TD>  "
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>KK05</font></TD>"
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>ZZ05</font></TD> "
                            + "</TR> <TR>  <TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>"
                            + "<span lang='fa'><font face='Tahoma' size='2'>&#1588;&#1607;&#1585;&#1740;&#1608;&#1585;</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>"
                            + "<TABLE> <TR>  <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD06 height=1></font></TD>  <TD><font size='2' face='Times New Roman'>PP06%</font></TD>  </TR> </TABLE>  </TD>"
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>RR06</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>"
                            + " <font face='Times New Roman'>KK06</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>ZZ06</font></TD> </TR> <TR>"
                            + "<TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'> <span lang='fa'><font face='Tahoma' size='2'>&#1605;&#1607;&#1585;</font></span></TD>  <TD valign=middle width='520' height='30'"
                            + " style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'> <TABLE> <TR>  <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD07 height=1></font></TD>  <TD><font size='2' "
                            + "face='Times New Roman'>PP07%</font></TD>  </TR> </TABLE>  </TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>RR07</font></TD>  <TD align=center width='150'"
                            + " height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>KK07</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>ZZ07</font></TD> </TR> <TR> "
                            + " <TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'> <span lang='fa'><font face='Tahoma' size='2'>&#1570;&#1576;&#1575;&#1606;</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: "
                            + "1px; border-top-width: 1px; border-bottom-width: 1px'> <TABLE> <TR>  <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD08 height=1></font></TD>  <TD><font size='2' face='Times New Roman'>PP08%</font></TD>  </TR> </TABLE>  </TD>  <TD align=center "
                            + "width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>RR08</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>KK08</font></TD>  <TD align=center"
                            + " width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>ZZ08</font></TD> </TR> <TR>  <TD align='center' width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'> <span lang='fa'><font"
                            + " face='Tahoma' size='2'>&#1570;&#1584;&#1585;</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'> <TABLE> <TR>  <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' "
                            + "width=DD09 height=1></font></TD>  <TD><font size='2' face='Times New Roman'>PP09%</font></TD>  </TR> </TABLE>  </TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>RR09</font></TD>  <TD align=center width='150'"
                            + " height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>KK09</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px'> <font face='Times New Roman'>ZZ09</font></TD> </TR> <TR>  <TD align='center'"
                            + " width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'> <span lang='fa'><font face='Tahoma' size='2'>&#1583;&#1740;</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: 1px;"
                            + " border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <TABLE> <TR> <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD10 height=1></font></TD>  <TD><font size='2' face='Times New Roman'>PP10%</font></TD>  </TR> </TABLE>  "
                            + "</TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>RR10</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' "
                            + "bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>KK10</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>ZZ10</font></TD> </TR> <TR>  <TD align='center' width='100' "
                            + "height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'> <span lang='fa'><font face='Tahoma' size='2'>&#1576;&#1607;&#1605;&#1606;</font></span></TD>  <TD valign=middle width='520' height='30' style='border-left-width: 1px;"
                            + " border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'> <TABLE> <TR><TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD11 height=1></font></TD>  <TD><font size='2' face='Times New Roman'>PP11%</font></TD>  </TR></TABLE>  "
                            + "</TD><TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>RR11</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' "
                            + "bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>KK11</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>ZZ11</font></TD> </TR> <TR>  <TD align='center' width='100'"
                            + " height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom: 1px solid #000000' bgcolor='#99CCFF' dir='ltr'> <span lang='fa'><font face='Tahoma' size='2'>&#1575;&#1587;&#1601;&#1606;&#1583;</font></span></TD>  <TD valign=middle width='520' height='30' "
                            + "style='border-left-width: 1px; border-top-width: 1px; border-bottom: 1px solid #000000' bgcolor='#99CCFF' dir='ltr'> <TABLE> <TR> <TD bgcolor=#000099 height='2px'><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD12 height=1></font></TD>  <TD><font size='2' "
                            + "face='Times New Roman'>PP12%</font></TD>  </TR> </TABLE>  </TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px; border-bottom: 1px solid #000000' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>RR12</font></TD>  "
                            + "<TD align=center width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px; border-bottom: 1px solid #000000' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>KK12</font></TD>  <TD align=center width='150' height='30' style='border-right: 1px solid #000000;"
                            + " border-top-width: 1px; border-bottom: 1px solid #000000' bgcolor='#99CCFF' dir='ltr'> <font face='Times New Roman'>ZZ12</font></TD> </TR></TABLE></body></html>";

                    html = html.replace("KK01", String.valueOf(jjChart.n1));
                    html = html.replace("KK02", String.valueOf(jjChart.n2));
                    html = html.replace("KK03", String.valueOf(jjChart.n3));
                    html = html.replace("KK04", String.valueOf(jjChart.n4));
                    html = html.replace("KK05", String.valueOf(jjChart.n5));
                    html = html.replace("KK06", String.valueOf(jjChart.n6));
                    html = html.replace("KK07", String.valueOf(jjChart.n7));
                    html = html.replace("KK08", String.valueOf(jjChart.n8));
                    html = html.replace("KK09", String.valueOf(jjChart.n9));
                    html = html.replace("KK10", String.valueOf(jjChart.n10));
                    html = html.replace("KK11", String.valueOf(jjChart.n11));
                    html = html.replace("KK12", String.valueOf(jjChart.m12));
                    html = html.replace("RR01", String.valueOf(jjChart.m1));
                    html = html.replace("RR02", String.valueOf(jjChart.m2));
                    html = html.replace("RR03", String.valueOf(jjChart.m3));
                    html = html.replace("RR04", String.valueOf(jjChart.m4));
                    html = html.replace("RR05", String.valueOf(jjChart.m5));
                    html = html.replace("RR06", String.valueOf(jjChart.m6));
                    html = html.replace("RR07", String.valueOf(jjChart.m7));
                    html = html.replace("RR08", String.valueOf(jjChart.m8));
                    html = html.replace("RR09", String.valueOf(jjChart.m9));
                    html = html.replace("RR10", String.valueOf(jjChart.m10));
                    html = html.replace("RR11", String.valueOf(jjChart.m11));
                    html = html.replace("RR12", String.valueOf(jjChart.m12));
                    html = html.replace("ZZ01", String.valueOf(jjChart.m1 - jjChart.n1));
                    html = html.replace("ZZ02", String.valueOf(jjChart.m2 - jjChart.n2));
                    html = html.replace("ZZ03", String.valueOf(jjChart.m3 - jjChart.n3));
                    html = html.replace("ZZ04", String.valueOf(jjChart.m4 - jjChart.n4));
                    html = html.replace("ZZ05", String.valueOf(jjChart.m5 - jjChart.n5));
                    html = html.replace("ZZ06", String.valueOf(jjChart.m6 - jjChart.n6));
                    html = html.replace("ZZ07", String.valueOf(jjChart.m7 - jjChart.n7));
                    html = html.replace("ZZ08", String.valueOf(jjChart.m8 - jjChart.n8));
                    html = html.replace("ZZ09", String.valueOf(jjChart.m9 - jjChart.n9));
                    html = html.replace("ZZ10", String.valueOf(jjChart.m10 - jjChart.n10));
                    html = html.replace("ZZ11", String.valueOf(jjChart.m11 - jjChart.n11));
                    html = html.replace("ZZ12", String.valueOf(jjChart.m12 - jjChart.n12));


                    long max = jjNumber.getMax(jjChart.m1, jjChart.m2, jjChart.m3, jjChart.m4, jjChart.m5, jjChart.m6, jjChart.m7, jjChart.m8, jjChart.m9, jjChart.m10, jjChart.m11, jjChart.m12);
                    if (max == 0) {
                        max = 1;
                    }
                    html = html.replace("PP01", String.valueOf(jjChart.m1 * 100 / max));
                    html = html.replace("PP02", String.valueOf(jjChart.m2 * 100 / max));
                    html = html.replace("PP03", String.valueOf(jjChart.m3 * 100 / max));
                    html = html.replace("PP04", String.valueOf(jjChart.m4 * 100 / max));
                    html = html.replace("PP05", String.valueOf(jjChart.m5 * 100 / max));
                    html = html.replace("PP06", String.valueOf(jjChart.m6 * 100 / max));
                    html = html.replace("PP07", String.valueOf(jjChart.m7 * 100 / max));
                    html = html.replace("PP08", String.valueOf(jjChart.m8 * 100 / max));
                    html = html.replace("PP09", String.valueOf(jjChart.m9 * 100 / max));
                    html = html.replace("PP10", String.valueOf(jjChart.m10 * 100 / max));
                    html = html.replace("PP11", String.valueOf(jjChart.m11 * 100 / max));
                    html = html.replace("PP12", String.valueOf(jjChart.m12 * 100 / max));


                    html = html.replace("DD01", String.valueOf(((jjChart.m1 * 500) / max)));
                    html = html.replace("DD02", String.valueOf(((jjChart.m2 * 500) / max)));
                    html = html.replace("DD03", String.valueOf(((jjChart.m3 * 500) / max)));
                    html = html.replace("DD04", String.valueOf(((jjChart.m4 * 500) / max)));
                    html = html.replace("DD05", String.valueOf(((jjChart.m5 * 500) / max)));
                    html = html.replace("DD06", String.valueOf(((jjChart.m6 * 500) / max)));
                    html = html.replace("DD07", String.valueOf(((jjChart.m7 * 500) / max)));
                    html = html.replace("DD08", String.valueOf(((jjChart.m8 * 500) / max)));
                    html = html.replace("DD09", String.valueOf(((jjChart.m9 * 500) / max)));
                    html = html.replace("DD10", String.valueOf(((jjChart.m10 * 500) / max)));
                    html = html.replace("DD11", String.valueOf(((jjChart.m11 * 500) / max)));
                    html = html.replace("DD12", String.valueOf(((jjChart.m12 * 500) / max)));
                    html = html.replace("YYYY", String.valueOf(YYYY));
                    jjFileTxt.write(jjOsInfo.getProgramAddress() + "\\Chart\\Year.html", html, false, false);
                    jjOsInfo.doCmdOpenFileInOneProgram(jjOsInfo.getProgramAddress() + "\\Chart\\Year.html");
                } catch (Exception ex) {
                    jjDialog.error_Message(null, "doShowChart()", ex.getMessage());
                    return;
                }
            }
        });
        t.start();
    }
    /**
     *
     * get HTML Chart
     */
//    public static void doShowChart(int year, int month, long m1, long m2, long m3, long m4, long m5, long m6, long m7, long m8, long m9, long m10, long m11, long m12,
//            long n1, long n2, long n3, long n4, long n5, long n6, long n7, long n8, long n9, long n10, long n11, long n12) {
//
//        YYYY = year;
//        m1 = m1;
//        m2 = m2;
//        m3 = m3;
//        m4 = m4;
//        m5 = m5;
//        m6 = m6;
//        m7 = m7;
//        m8 = m8;
//        m9 = m9;
//        m10 = m10;
//        m11 = m11;
//        m12 = m12;
//        n1 = n1;
//        n2 = n2;
//        n3 = n3;
//        n4 = n4;
//        n5 = n5;
//        n6 = n6;
//        n7 = n7;
//        n8 = n8;
//        n9 = n9;
//        n10 = n10;
//        n11 = n11;
//        n12 = n12;
//        Thread t = new Thread(new Runnable() {
//
//            @Override
//            public void run() {
//                try {
//                    long sum = m1 + m2 + m3 + m4 + m5 + m6 + m7
//                            + m8 + m9 + m10 + m11 + m12;
//                    String html = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=windows-1252'><title>Benco Chart</title></head><body topmargin='5' leftmargin='5' rightmargin='5' bottommargin='5' marginwidth='5' marginheight='5'><BR><p align='center'><font face='Tahoma' size='4'>YYYY<span lang='fa'> &#1606;&#1605;&#1608;&#1583;&#1575;&#1585; &#1601;&#1585;&#1608;&#1588; &#1585;&#1608;&#1586;&#1607;&#1575;&#1740;</span><br></font><br></p><TABLE BORDER=0 CELLSPACING=0 CELLPADDING=2 align=center> <TR bgcolor=lightgrey>  <TH width='100' height='30' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#000099'>  <span lang='fa'><font face='Tahoma' size='2' color='#FFFFFF'>&#1585;&#1608;&#1586;</font></span></TH>  <TH width='520' height='30' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#000099'>  <span lang='fa'><font face='Tahoma' color='#FFFFFF'>&#1583;&#1585;&#1589;&#1583; &#1601;&#1585;&#1608;&#1588;</font></span></TH>  <TH width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#000099'>  <span lang='fa'><font face='Tahoma' color='#FFFFFF'>&#1601;&#1585;&#1608;&#1588;</font></span></TH>  <TH width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#000099'>  <span lang='fa'><font face='Tahoma' color='#FFFFFF'>&#1607;&#1586;&#1740;&#1606;&#1607;</font></span></TH>  <TH width='150' height='30' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#000099'>  <span lang='fa'><font face='Tahoma' color='#FFFFFF'>&#1587;&#1608;&#1583;</font></span></TH> </TR>  <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'>  <font face='Tahoma' size='2'>MM01</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD01 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP01%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>RR01</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>KK01</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>ZZ01</font></TD>  </TR>  <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF'>  <font face='Tahoma' size='2'>MM02</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD02 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP02%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF'>  <font face='Times New Roman'>RR02</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF'>  <font face='Times New Roman'>KK02</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF'>  <font face='Times New Roman'>ZZ02</font></TD>  </TR>  <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'>  <font face='Tahoma' size='2'>MM03</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'> <TABLE>  <TR>   <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD03 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP03%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>RR03</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>KK03</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>ZZ03</font></TD>  </TR>  <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM04</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD04 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP04%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR04</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK04</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ04</font></TD>  </TR>  <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' dir='ltr'>  <font face='Tahoma' size='2'>MM05</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD05 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP05%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' dir='ltr'>  <font face='Times New Roman'>RR05</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' dir='ltr'>  <font face='Times New Roman'>KK05</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' dir='ltr'>  <font face='Times New Roman'>ZZ05</font></TD>  </TR>  <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM06</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD06 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP06%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR06</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK06</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ06</font></TD>  </TR>  <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'>  <font face='Tahoma' size='2'>MM07</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD07 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP07%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>RR07</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>KK07</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>ZZ07</font></TD>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF'>  <font face='Tahoma' size='2'>MM08</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD08 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP08%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF'>  <font face='Times New Roman'>RR08</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF'>  <font face='Times New Roman'>KK08</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF'>  <font face='Times New Roman'>ZZ08</font></TD>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px'>  <font face='Tahoma' size='2'>MM09</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD09 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP09%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>RR09</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>KK09</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px'>  <font face='Times New Roman'>ZZ09</font></TD>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM10</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD10 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP10%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR10</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK10</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ10</font></TD>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom-width: 1px' dir='ltr'>  <font face='Tahoma' size='2'>MM11</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom-width: 1px' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD11 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP11%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' dir='ltr'>  <font face='Times New Roman'>RR11</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' dir='ltr'>  <font face='Times New Roman'>KK11</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px' dir='ltr'>  <font face='Times New Roman'>ZZ11</font></TD>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM12</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD12 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP12%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR12</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK12</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ12</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM13</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD13 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP13%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR13</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK13</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ13</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM14</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD14 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP14%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR14</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK14</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ14</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM15</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD15 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP15%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR15</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK15</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ15</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM16</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD16 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP16%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR16</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK16</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ16</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM17</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD17 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP17%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR17</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK17</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ17</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM18</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD18 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP18%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR18</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK18</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ18</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM19</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD19 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP19%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR19</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK19</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ19</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM20</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD20 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP20%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR20</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK20</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ20</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM21</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD21 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP21%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR21</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK21</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ21</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM22</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD22 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP22%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR22</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK22</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ22</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM23</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD23 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP23%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR23</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK23</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ23</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM24</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD24 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP24%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR24</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK24</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ24</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM25</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD25 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP25%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR25</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK25</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ25</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM26</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD26 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP26%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR26</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK26</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ26</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM27</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD27 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP27%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR27</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK27</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ27</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM28</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD28 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP28%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR28</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK28</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ28</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Tahoma' size='2'>MM29</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD29 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP29%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>RR29</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>KK29</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' dir='ltr'>  <font face='Times New Roman'>ZZ29</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Tahoma' size='2'>MM30</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD30 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP30%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>RR30</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>KK30</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; ' bgcolor='#99CCFF' dir='ltr'>  <font face='Times New Roman'>ZZ30</font></TD>  </TR>  </TR>    <TR>   <TD align='center' width='100' height='20' style='border-left: 1px solid #000000; border-top-width: 1px; border-bottom: 1px solid #000000' dir='ltr'>  <font face='Tahoma' size='2'>MM31</font></TD>   <TD valign=middle width='520' height='20' style='border-left-width: 1px; border-top-width: 1px; border-bottom: 1px solid #000000' dir='ltr'>    <TABLE>     <TR>      <TD bgcolor=#000099><font size='1' face='Times New Roman'><IMG SRC='gifs/s.gif' width=DD31 height=1></font></TD>     <TD><font size='2' face='Times New Roman'>PP31%</font></TD>    </TR>    </TABLE>   </TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; border-bottom: 1px solid #000000' dir='ltr'>  <font face='Times New Roman'>RR31</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; border-bottom: 1px solid #000000' dir='ltr'>  <font face='Times New Roman'>KK31</font></TD>   <TD align=center width='150' height='20' style='border-right: 1px solid #000000; border-top-width: 1px; border-bottom: 1px solid #000000' dir='ltr'>  <font face='Times New Roman'>ZZ31</font></TD>  </TR></TABLE><br></body></html>";
//                        long m01 = 0;
//                        long m02 = 0;
//                        long m03 = 0;
//                        long m04 = 0;
//                        long m05 = 0;
//                        long m06 = 0;
//                        long m07 = 0;
//                        long m08 = 0;
//                        long m09 = 0;
//                        long m10 = 0;
//                        long m11 = 0;
//                        long m12 = 0;
//                        long m13 = 0;
//                        long m14 = 0;
//                        long m15 = 0;
//                        long m16 = 0;
//                        long m17 = 0;
//                        long m18 = 0;
//                        long m19 = 0;
//                        long m20 = 0;
//                        long m21 = 0;
//                        long m22 = 0;
//                        long m23 = 0;
//                        long m24 = 0;
//                        long m25 = 0;
//                        long m26 = 0;
//                        long m27 = 0;
//                        long m28 = 0;
//                        long m29 = 0;
//                        long m30 = 0;
//                        long m31 = 0;
//                        // ======= //
//                        long k01 = 0;
//                        long k02 = 0;
//                        long k03 = 0;
//                        long k04 = 0;
//                        long k05 = 0;
//                        long k06 = 0;
//                        long k07 = 0;
//                        long k08 = 0;
//                        long k09 = 0;
//                        long k10 = 0;
//                        long k11 = 0;
//                        long k12 = 0;
//                        long k13 = 0;
//                        long k14 = 0;
//                        long k15 = 0;
//                        long k16 = 0;
//                        long k17 = 0;
//                        long k18 = 0;
//                        long k19 = 0;
//                        long k20 = 0;
//                        long k21 = 0;
//                        long k22 = 0;
//                        long k23 = 0;
//                        long k24 = 0;
//                        long k25 = 0;
//                        long k26 = 0;
//                        long k27 = 0;
//                        long k28 = 0;
//                        long k29 = 0;
//                        long k30 = 0;
//                        long k31 = 0;
//                        // =======
//                        DefaultTableModel dtm = myJframe.a.Select(db.ghabz.tbl_name, "", "");
//
//                        for (int i = 0; i < dtm.getRowCount(); i++) {
//                            if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/01")) {
//                                m01 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/02")) {
//                                m02 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/03")) {
//                                m03 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/04")) {
//                                m04 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/05")) {
//                                m05 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/06")) {
//                                m06 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/07")) {
//                                m07 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/08")) {
//                                m08 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/09")) {
//                                m09 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/10")) {
//                                m10 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/11")) {
//                                m11 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/12")) {
//                                m12 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/13")) {
//                                m13 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/14")) {
//                                m14 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/15")) {
//                                m15 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/16")) {
//                                m16 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/17")) {
//                                m17 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/18")) {
//                                m18 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/19")) {
//                                m19 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/20")) {
//                                m20 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/21")) {
//                                m21 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/22")) {
//                                m22 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/23")) {
//                                m23 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/24")) {
//                                m24 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/25")) {
//                                m25 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/26")) {
//                                m26 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/27")) {
//                                m27 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/28")) {
//                                m28 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/29")) {
//                                m29 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/30")) {
//                                m30 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm.getValueAt(i, 5).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/31")) {
//                                m31 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            }
//                        }
////            if (m01 == 0 && m02 == 0 && m03 == 0 && m04 == 0 && m05 == 0 && m06 == 0 && m07 == 0 && m08 == 0 && m09 == 0 && m10 == 0 && m11 == 0 && m12 == 0 && m13 == 0 && m14 == 0 && m15 == 0 && m16 == 0 && m17 == 0 && m18 == 0 && m19 == 0 && m20 == 0 && m21 == 0 && m22 == 0 && m23 == 0 && m24 == 0 && m25 == 0 && m26 == 0 && m27 == 0 && m28 == 0 && m29 == 0 && m30 == 0 && m31 == 0) {
////                this.setVisible(false);
////                JOptionPane.showMessageDialog(myJframe, "در این ماه هیچ درآمدی حاصل نشده است.");
////                this.dispose();
////            }
//                        DefaultTableModel dtm2 = myJframe.a.Select(db.hazine.tbl_name, "", "");
//                        for (int i = 0; i < dtm2.getRowCount(); i++) {
//                            if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/01")) {
//                                k01 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/02")) {
//                                k02 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/03")) {
//                                k03 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/04")) {
//                                k04 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/05")) {
//                                k05 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/06")) {
//                                k06 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/07")) {
//                                k07 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/08")) {
//                                k08 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/09")) {
//                                k09 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/10")) {
//                                k10 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/11")) {
//                                k11 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/12")) {
//                                k12 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/13")) {
//                                k13 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/14")) {
//                                k14 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/15")) {
//                                k15 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/16")) {
//                                k16 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/17")) {
//                                k17 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/18")) {
//                                k18 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/19")) {
//                                k19 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/20")) {
//                                k20 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/21")) {
//                                k21 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/22")) {
//                                k22 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/23")) {
//                                k23 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/24")) {
//                                k24 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/25")) {
//                                k25 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/26")) {
//                                k26 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/27")) {
//                                k27 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/28")) {
//                                k28 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/29")) {
//                                k29 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/30")) {
//                                k30 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            } else if (dtm2.getValueAt(i, 3).toString().startsWith(y1.getSelectedItem().toString() + "/" + m1.getSelectedItem().toString() + "/31")) {
//                                k31 += Integer.parseInt(dtm.getValueAt(i, 2).toString());
//                            }
//                        }
//                        // =================
//                        long mm01_02 = Math.max(m01, m02);
//                        long mm03_04 = Math.max(m03, m04);
//                        long mm05_06 = Math.max(m05, m06);
//                        long mm07_08 = Math.max(m07, m08);
//                        long mm09_10 = Math.max(m09, m10);
//                        long mm11_12 = Math.max(m11, m12);
//                        long mm13_14 = Math.max(m13, m14);
//                        long mm15_16 = Math.max(m15, m16);
//                        long mm17_18 = Math.max(m17, m18);
//                        long mm19_20 = Math.max(m19, m20);
//                        long mm21_22 = Math.max(m21, m22);
//                        long mm23_24 = Math.max(m23, m24);
//                        long mm25_26 = Math.max(m25, m26);
//                        long mm27_28 = Math.max(m27, m28);
//                        long mm29_30 = Math.max(m29, m30);
//                        long mm31 = Math.max(m31, 0);
//                        // ============================
//                        long mmA = Math.max(mm01_02, mm03_04);
//                        long mmB = Math.max(mm05_06, mm07_08);
//                        long mmC = Math.max(mm09_10, mm11_12);
//                        long mmD = Math.max(mm13_14, mm15_16);
//                        long mmE = Math.max(mm17_18, mm19_20);
//                        long mmF = Math.max(mm21_22, mm23_24);
//                        long mmG = Math.max(mm25_26, mm27_28);
//                        long mmH = Math.max(mm29_30, mm31);
//                        // ============================
//                        long mmmA = Math.max(mmA, mmB);
//                        long mmmB = Math.max(mmC, mmD);
//                        long mmmC = Math.max(mmE, mmF);
//                        long mmmD = Math.max(mmG, mmH);
//                        // ============================
//                        long mmmmA = Math.max(mmmA, mmmB);
//                        long mmmmB = Math.max(mmmC, mmmD);
//                        // ============================
//                        long SSS = Math.max(mmmmA, mmmmB);
//                        String year = y1.getSelectedItem().toString();
//                        String month = m1.getSelectedItem().toString();
//                        html = html.replace("YYYY", year + "/" + month);
//                        if (SSS == 0) {
//                            SSS = 1;
//                        }
//                        html = html.replace("PP01", String.valueOf(((m01 * 100) / SSS)));
//                        html = html.replace("PP02", String.valueOf(((m02 * 100) / SSS)));
//                        html = html.replace("PP03", String.valueOf(((m03 * 100) / SSS)));
//                        html = html.replace("PP04", String.valueOf(((m04 * 100) / SSS)));
//                        html = html.replace("PP05", String.valueOf(((m05 * 100) / SSS)));
//                        html = html.replace("PP06", String.valueOf(((m06 * 100) / SSS)));
//                        html = html.replace("PP07", String.valueOf(((m07 * 100) / SSS)));
//                        html = html.replace("PP08", String.valueOf(((m08 * 100) / SSS)));
//                        html = html.replace("PP09", String.valueOf(((m09 * 100) / SSS)));
//                        html = html.replace("PP10", String.valueOf(((m10 * 100) / SSS)));
//                        html = html.replace("PP11", String.valueOf(((m11 * 100) / SSS)));
//                        html = html.replace("PP12", String.valueOf(((m12 * 100) / SSS)));
//                        html = html.replace("PP13", String.valueOf(((m13 * 100) / SSS)));
//                        html = html.replace("PP14", String.valueOf(((m14 * 100) / SSS)));
//                        html = html.replace("PP15", String.valueOf(((m15 * 100) / SSS)));
//                        html = html.replace("PP16", String.valueOf(((m16 * 100) / SSS)));
//                        html = html.replace("PP17", String.valueOf(((m17 * 100) / SSS)));
//                        html = html.replace("PP18", String.valueOf(((m18 * 100) / SSS)));
//                        html = html.replace("PP19", String.valueOf(((m19 * 100) / SSS)));
//                        html = html.replace("PP20", String.valueOf(((m20 * 100) / SSS)));
//                        html = html.replace("PP21", String.valueOf(((m21 * 100) / SSS)));
//                        html = html.replace("PP22", String.valueOf(((m22 * 100) / SSS)));
//                        html = html.replace("PP23", String.valueOf(((m23 * 100) / SSS)));
//                        html = html.replace("PP24", String.valueOf(((m24 * 100) / SSS)));
//                        html = html.replace("PP25", String.valueOf(((m25 * 100) / SSS)));
//                        html = html.replace("PP26", String.valueOf(((m26 * 100) / SSS)));
//                        html = html.replace("PP27", String.valueOf(((m27 * 100) / SSS)));
//                        html = html.replace("PP28", String.valueOf(((m28 * 100) / SSS)));
//                        html = html.replace("PP29", String.valueOf(((m29 * 100) / SSS)));
//                        html = html.replace("PP30", String.valueOf(((m30 * 100) / SSS)));
//                        html = html.replace("PP31", String.valueOf(((m31 * 100) / SSS)));
//
//
//                        html = html.replace("DD01", String.valueOf(((m01 * 500) / SSS)));
//                        html = html.replace("DD02", String.valueOf(((m02 * 500) / SSS)));
//                        html = html.replace("DD03", String.valueOf(((m03 * 500) / SSS)));
//                        html = html.replace("DD04", String.valueOf(((m04 * 500) / SSS)));
//                        html = html.replace("DD05", String.valueOf(((m05 * 500) / SSS)));
//                        html = html.replace("DD06", String.valueOf(((m06 * 500) / SSS)));
//                        html = html.replace("DD07", String.valueOf(((m07 * 500) / SSS)));
//                        html = html.replace("DD08", String.valueOf(((m08 * 500) / SSS)));
//                        html = html.replace("DD09", String.valueOf(((m09 * 500) / SSS)));
//                        html = html.replace("DD10", String.valueOf(((m10 * 500) / SSS)));
//                        html = html.replace("DD11", String.valueOf(((m11 * 500) / SSS)));
//                        html = html.replace("DD12", String.valueOf(((m12 * 500) / SSS)));
//                        html = html.replace("DD13", String.valueOf(((m13 * 500) / SSS)));
//                        html = html.replace("DD14", String.valueOf(((m14 * 500) / SSS)));
//                        html = html.replace("DD15", String.valueOf(((m15 * 500) / SSS)));
//                        html = html.replace("DD16", String.valueOf(((m16 * 500) / SSS)));
//                        html = html.replace("DD17", String.valueOf(((m17 * 500) / SSS)));
//                        html = html.replace("DD18", String.valueOf(((m18 * 500) / SSS)));
//                        html = html.replace("DD19", String.valueOf(((m19 * 500) / SSS)));
//                        html = html.replace("DD20", String.valueOf(((m20 * 500) / SSS)));
//                        html = html.replace("DD21", String.valueOf(((m21 * 500) / SSS)));
//                        html = html.replace("DD22", String.valueOf(((m22 * 500) / SSS)));
//                        html = html.replace("DD23", String.valueOf(((m23 * 500) / SSS)));
//                        html = html.replace("DD24", String.valueOf(((m24 * 500) / SSS)));
//                        html = html.replace("DD25", String.valueOf(((m25 * 500) / SSS)));
//                        html = html.replace("DD26", String.valueOf(((m26 * 500) / SSS)));
//                        html = html.replace("DD27", String.valueOf(((m27 * 500) / SSS)));
//                        html = html.replace("DD28", String.valueOf(((m28 * 500) / SSS)));
//                        html = html.replace("DD29", String.valueOf(((m29 * 500) / SSS)));
//                        html = html.replace("DD30", String.valueOf(((m30 * 500) / SSS)));
//                        html = html.replace("DD31", String.valueOf(((m31 * 500) / SSS)));
//
//                        html = html.replace("MM01", year + "/" + month + "/1");
//                        html = html.replace("MM02", year + "/" + month + "/2");
//                        html = html.replace("MM03", year + "/" + month + "/3");
//                        html = html.replace("MM04", year + "/" + month + "/4");
//                        html = html.replace("MM05", year + "/" + month + "/5");
//                        html = html.replace("MM06", year + "/" + month + "/6");
//                        html = html.replace("MM07", year + "/" + month + "/7");
//                        html = html.replace("MM08", year + "/" + month + "/8");
//                        html = html.replace("MM09", year + "/" + month + "/9");
//                        html = html.replace("MM10", year + "/" + month + "/10");
//                        html = html.replace("MM11", year + "/" + month + "/11");
//                        html = html.replace("MM12", year + "/" + month + "/12");
//                        html = html.replace("MM13", year + "/" + month + "/13");
//                        html = html.replace("MM14", year + "/" + month + "/14");
//                        html = html.replace("MM15", year + "/" + month + "/15");
//                        html = html.replace("MM16", year + "/" + month + "/16");
//                        html = html.replace("MM17", year + "/" + month + "/17");
//                        html = html.replace("MM18", year + "/" + month + "/18");
//                        html = html.replace("MM19", year + "/" + month + "/19");
//                        html = html.replace("MM20", year + "/" + month + "/20");
//                        html = html.replace("MM21", year + "/" + month + "/21");
//                        html = html.replace("MM22", year + "/" + month + "/22");
//                        html = html.replace("MM23", year + "/" + month + "/23");
//                        html = html.replace("MM24", year + "/" + month + "/24");
//                        html = html.replace("MM25", year + "/" + month + "/25");
//                        html = html.replace("MM26", year + "/" + month + "/26");
//                        html = html.replace("MM27", year + "/" + month + "/27");
//                        html = html.replace("MM28", year + "/" + month + "/28");
//                        html = html.replace("MM29", year + "/" + month + "/29");
//                        html = html.replace("MM30", year + "/" + month + "/30");
//                        html = html.replace("MM31", year + "/" + month + "/31");
//
//
//                        html = html.replace("RR01", String.valueOf(m01));
//                        html = html.replace("RR02", String.valueOf(m02));
//                        html = html.replace("RR03", String.valueOf(m03));
//                        html = html.replace("RR04", String.valueOf(m04));
//                        html = html.replace("RR05", String.valueOf(m05));
//                        html = html.replace("RR06", String.valueOf(m06));
//                        html = html.replace("RR07", String.valueOf(m07));
//                        html = html.replace("RR08", String.valueOf(m08));
//                        html = html.replace("RR09", String.valueOf(m09));
//                        html = html.replace("RR10", String.valueOf(m10));
//                        html = html.replace("RR11", String.valueOf(m11));
//                        html = html.replace("RR12", String.valueOf(m12));
//                        html = html.replace("RR13", String.valueOf(m13));
//                        html = html.replace("RR14", String.valueOf(m14));
//                        html = html.replace("RR15", String.valueOf(m15));
//                        html = html.replace("RR16", String.valueOf(m16));
//                        html = html.replace("RR17", String.valueOf(m17));
//                        html = html.replace("RR18", String.valueOf(m18));
//                        html = html.replace("RR19", String.valueOf(m19));
//                        html = html.replace("RR20", String.valueOf(m20));
//                        html = html.replace("RR21", String.valueOf(m21));
//                        html = html.replace("RR22", String.valueOf(m22));
//                        html = html.replace("RR23", String.valueOf(m23));
//                        html = html.replace("RR24", String.valueOf(m24));
//                        html = html.replace("RR25", String.valueOf(m25));
//                        html = html.replace("RR26", String.valueOf(m26));
//                        html = html.replace("RR27", String.valueOf(m27));
//                        html = html.replace("RR28", String.valueOf(m28));
//                        html = html.replace("RR29", String.valueOf(m29));
//                        html = html.replace("RR30", String.valueOf(m30));
//                        html = html.replace("RR31", String.valueOf(m31));
//                        html = html.replace("KK01", String.valueOf(k01));
//                        html = html.replace("KK02", String.valueOf(k02));
//                        html = html.replace("KK03", String.valueOf(k03));
//                        html = html.replace("KK04", String.valueOf(k04));
//                        html = html.replace("KK05", String.valueOf(k05));
//                        html = html.replace("KK06", String.valueOf(k06));
//                        html = html.replace("KK07", String.valueOf(k07));
//                        html = html.replace("KK08", String.valueOf(k08));
//                        html = html.replace("KK09", String.valueOf(k09));
//                        html = html.replace("KK10", String.valueOf(k10));
//                        html = html.replace("KK11", String.valueOf(k11));
//                        html = html.replace("KK12", String.valueOf(k12));
//                        html = html.replace("KK13", String.valueOf(k13));
//                        html = html.replace("KK14", String.valueOf(k14));
//                        html = html.replace("KK15", String.valueOf(k15));
//                        html = html.replace("KK16", String.valueOf(k16));
//                        html = html.replace("KK17", String.valueOf(k17));
//                        html = html.replace("KK18", String.valueOf(k18));
//                        html = html.replace("KK19", String.valueOf(k19));
//                        html = html.replace("KK20", String.valueOf(k20));
//                        html = html.replace("KK21", String.valueOf(k21));
//                        html = html.replace("KK22", String.valueOf(k22));
//                        html = html.replace("KK23", String.valueOf(k23));
//                        html = html.replace("KK24", String.valueOf(k24));
//                        html = html.replace("KK25", String.valueOf(k25));
//                        html = html.replace("KK26", String.valueOf(k26));
//                        html = html.replace("KK27", String.valueOf(k27));
//                        html = html.replace("KK28", String.valueOf(k28));
//                        html = html.replace("KK29", String.valueOf(k29));
//                        html = html.replace("KK30", String.valueOf(k30));
//                        html = html.replace("KK31", String.valueOf(k31));
//
//                        html = html.replace("ZZ01", String.valueOf(m01 - k01));
//                        html = html.replace("ZZ02", String.valueOf(m02 - k02));
//                        html = html.replace("ZZ03", String.valueOf(m03 - k03));
//                        html = html.replace("ZZ04", String.valueOf(m04 - k04));
//                        html = html.replace("ZZ05", String.valueOf(m05 - k05));
//                        html = html.replace("ZZ06", String.valueOf(m06 - k06));
//                        html = html.replace("ZZ07", String.valueOf(m07 - k07));
//                        html = html.replace("ZZ08", String.valueOf(m08 - k08));
//                        html = html.replace("ZZ09", String.valueOf(m09 - k09));
//                        html = html.replace("ZZ10", String.valueOf(m10 - k10));
//                        html = html.replace("ZZ11", String.valueOf(m11 - k11));
//                        html = html.replace("ZZ12", String.valueOf(m12 - k12));
//                        html = html.replace("ZZ13", String.valueOf(m13 - k13));
//                        html = html.replace("ZZ14", String.valueOf(m14 - k14));
//                        html = html.replace("ZZ15", String.valueOf(m15 - k15));
//                        html = html.replace("ZZ16", String.valueOf(m16 - k16));
//                        html = html.replace("ZZ17", String.valueOf(m17 - k17));
//                        html = html.replace("ZZ18", String.valueOf(m18 - k18));
//                        html = html.replace("ZZ19", String.valueOf(m19 - k19));
//                        html = html.replace("ZZ20", String.valueOf(m20 - k20));
//                        html = html.replace("ZZ21", String.valueOf(m21 - k21));
//                        html = html.replace("ZZ22", String.valueOf(m22 - k22));
//                        html = html.replace("ZZ23", String.valueOf(m23 - k23));
//                        html = html.replace("ZZ24", String.valueOf(m24 - k24));
//                        html = html.replace("ZZ25", String.valueOf(m25 - k25));
//                        html = html.replace("ZZ26", String.valueOf(m26 - k26));
//                        html = html.replace("ZZ27", String.valueOf(m27 - k27));
//                        html = html.replace("ZZ28", String.valueOf(m28 - k28));
//                        html = html.replace("ZZ29", String.valueOf(m29 - k29));
//                        html = html.replace("ZZ30", String.valueOf(m30 - k30));
//                        html = html.replace("ZZ31", String.valueOf(m31 - k31));
//
//                        doWriteFile_UTF8(getProgramAddress() + "\\lib\\ProgramFiles\\Other\\ChartMonth1.htm", html.replace("height=5>","height=1>"));
//                        doCmdOpenFileInOneProgram(getProgramAddress() + "\\lib\\ProgramFiles\\Other\\ChartMonth1.htm");
//                    } catch (Exception ex) {
//                        dialog_ERROR_MESSAGE(ex, myJframe, "", "اشکال در نمایش نمودار");
//                        return;
//                    }
//                }
//            });
//            t.start();
//        } catch (Exception ex) {
//        }
//    }
}
