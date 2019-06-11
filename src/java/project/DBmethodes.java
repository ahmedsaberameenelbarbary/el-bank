package project;

import java.util.List;

public interface DBmethodes {

    public void insert(share s);

    public void update(share s);

    public void deletecustomer(int i);

    public void select(share s);

    public List customselect(int s);

    public List customselecttran(int s);

    public List allcustselect(share s);

    public int getnumfromstr(String s);

}
