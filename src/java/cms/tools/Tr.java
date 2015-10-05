/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cms.tools;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Milad
 */
public class Tr {

    private int id ;
    private List<Td> tds = new ArrayList<Td>();

    public int getId() {
        return id;
    }

    public void setId(int rowId) {
        this.id = rowId;
    }

    public List<Td> getTds() {
        return tds;
    }

    public void setTds(List<Td> tds) {
        this.tds = tds;
    }
    public void addTd(Td td) {
        this.tds.add(td);
    }

    
}
