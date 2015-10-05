/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jj;

/**
 *
 * @author Milad
 */
public class jjFileVCF_Entity {

    String name, cellPhone, home, work, fax, other;

    public jjFileVCF_Entity() {
        name = "";
        cellPhone = "";
        home = "";
        work = "";
        fax = "";
        other = "";
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String CellPhone) {
        this.cellPhone = CellPhone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String Fax) {
        this.fax = Fax;
    }

    public String getName() {
        return name;
    }

    public void setName(String FirstName) {
        this.name = FirstName;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String Home) {
        this.home = Home;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String Other) {
        this.other = Other;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String Work) {
        this.work = Work;
    }
}
