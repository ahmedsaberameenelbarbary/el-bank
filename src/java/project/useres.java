package project;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import org.hibernate.Session;

@ManagedBean(name = "u")
@SessionScoped
@Entity
public class useres extends CRUD implements share {

    private int id;
    private int autonum;
    private String name;
    private String pass;
    private String confirm_name;
    private String msg;
    private String checkmsg;
    private String bankplace;
    private String opencus;
    private String opentran;
    private String openuser;

    CRUD cr = new CRUD();

    //Functions 
    public void save() {
        if (pass.equals(confirm_name)) {
            cr.insert(this);
            msg = "information saved successfully";
            clearAll();
        } else {
            msg = "confirm not equal";
        }
    }

    public void delete() {

        cr.deletecustomer(id);
        msg = "information deleted successfully";
        clearAll();
    }

    public void clearAll() {
        this.id = 0;
        this.name = "";
        this.confirm_name = "";
        this.pass = "";
        this.bankplace = "";
    }

    public void clearall() {
        clearAll();
        this.msg = "";
    }
//check account

    public void checkaccount() {

        List<useres> c = customselect(id);
        if (c.isEmpty()) {
            checkmsg = "not founded!!!!";
        } else {
            checkmsg = "founded-->write a nother one!!";
        }
    }
//check user

    @Override
    public List customselect(int c) {
        Session s = Util.getSessionFactory().openSession();
        List<useres> cus = new ArrayList<useres>();
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery("from useres where id =?");
            q.setParameter(0, c);
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    public String checkusre() {

        return check(name, pass);
    }

    private String check(String a, String b) {
        Session s = Util.getSessionFactory().openSession();
        List<useres> cus = new ArrayList<useres>();
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery("from useres where name =? and pass=?");
            q.setParameter(0, a);
            q.setParameter(1, b);
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        
        String m = "";
        if (!cus.isEmpty()) {
            return m = "interface";
        }

        return m = "error";
    }
    //one customer

    public void getcus() {

        List<useres> c = cr.customselectuser(id);
        for (useres s : c) {
            name = s.name;
            id = s.id;
        }
    }
    //count methode

    public String count() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select count(name)from useres");

            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
//buttons function
    public String opencus(){return opencus;}
    public String opentran(){return opentran;}
    public String openuser(){return openuser;}
    //getter and setter
    @Id
    @TableGenerator(name = "de", pkColumnName = "pk", pkColumnValue = "pkv", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "de")
    @Column(name = "user_Id", nullable = false, unique = true, length = 255)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "user_Name", length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "pass_word", length = 255)
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Transient
    public String getConfirm_name() {
        return confirm_name;
    }

    public void setConfirm_name(String confirm_name) {
        this.confirm_name = confirm_name;
    }

    @Transient
    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Transient
    public String getCheckmsg() {
        return checkmsg;
    }

    public void setCheckmsg(String checkmsg) {
        this.checkmsg = checkmsg;
    }

    @Transient
    public int getAutonum() {
        return autonum = cr.getnumfromstr(count()) + 1;
    }

    public void setAutonum(int autonum) {
        this.autonum = autonum;
    }

    public String getBankplace() {
        return bankplace;
    }

    public void setBankplace(String bankplace) {
        this.bankplace = bankplace;
    }

    @Transient
    public String getOpencus() {
        return opencus;
    }

    public void setOpencus(String opencus) {
        this.opencus = opencus;
    }

    @Transient
    public String getOpentran() {
        return opentran;
    }

    public void setOpentran(String opentran) {
        this.opentran = opentran;
    }

    @Transient
    public String getOpenuser() {
        return openuser;
    }

    public void setOpenuser(String openuser) {
        this.openuser = openuser;
    }

}
