package project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.*;
import javax.persistence.*;
import org.hibernate.Session;

@ManagedBean(name = "t")
@SessionScoped
@Entity
@Table(name = "Transactions")
public class Transac implements share {

    private int tran_id;
    private int tran_amount;
    private int tran_oldbalance;
    private int tran_finalbalance;
    private int cus_acco;
    private int cus_acco2;
    private String cus_name;
    private String tran_place;
    private String tran_type;
    private String tran_moode;
    private String msg;
    private String checkmsg;
    private String checkmsg2;
    private Date tran_date;
    private String count;
    private List<Transac> table;

    //constructor
    public Transac() {
        this.tran_amount = 0;
        this.tran_oldbalance = 0;
        this.tran_finalbalance = 0;
        cus_acco2 = 0;
        cus_acco = 0;
    }

    //functions
    private void clearAll() {
        tran_id = 0;
        tran_amount = 0;
        tran_oldbalance = 0;
        tran_finalbalance = 0;
        cus_acco = 0;
        cus_name = "";
        tran_type = "";
        tran_moode = "";
        tran_place = "";
        cus_acco2 = 0;
    }

    public void clearall() {
        clearAll();
        msg = "";
        checkmsg = "";
        checkmsg2 = "";
    }

//    //transfer save
    public void save2() {
        Session s = Util.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            CRUD cr = new CRUD();
            List<Customer> c1 = cr.customselect(cus_acco);
            List<Customer> c2 = cr.customselect(cus_acco2);
            Customer cus = new Customer();
            Customer cus2 = new Customer();
            if (!c1.isEmpty()) {
                cus = c1.get(0);
            }
            if (!c2.isEmpty()) {
                cus2 = c2.get(0);
            }

            Transac ta = new Transac();
            Transac tb = new Transac();
            ta.setCus_acco(cus_acco);
            tb.setCus_acco(cus_acco2);
            ta.setTran_finalbalance(cus.getCus_balance());
            tb.setTran_finalbalance(cus2.getCus_balance());
            ta.setTran_amount(-this.tran_amount);
            tb.setTran_amount(this.tran_amount);
            tb.setTran_oldbalance(tb.getTran_finalbalance());
            ta.setTran_oldbalance(ta.getTran_finalbalance());
            tb.setTran_finalbalance(tb.getTran_finalbalance() + tran_amount);
            ta.setTran_finalbalance(ta.getTran_finalbalance() - tran_amount);
            ta.setCus_name(cus.getCus_name());
            tb.setCus_name(cus2.getCus_name());
            ta.setTran_place(tran_place);
            tb.setTran_place(tran_place);
            ta.setTran_moode("cash");
            tb.setTran_moode("cash");
            ta.setTran_type("transfer");
            tb.setTran_type("transfer");
            s.save(ta);
            s.save(tb);
            cus.setCus_balance(ta.getTran_finalbalance());
            cus2.setCus_balance(tb.getTran_finalbalance());
            s.update(cus);
            s.update(cus2);
            s.getTransaction().commit();
            msg = "information saved successfully";
            clearAll();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    //    //Deposite save
    public void save() {
        Session s = Util.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            CRUD cr = new CRUD();
            List<Customer> c1 = cr.customselect(cus_acco);
            Customer cus = new Customer();

            if (!c1.isEmpty()) {
                cus = c1.get(0);
            }
            Transac ta = new Transac();
            ta.setCus_acco(cus_acco);
            ta.setTran_finalbalance(cus.getCus_balance());
            ta.setTran_amount(this.tran_amount);
            ta.setTran_oldbalance(ta.getTran_finalbalance());
            ta.setTran_finalbalance(ta.getTran_finalbalance() + tran_amount);
            ta.setCus_name(cus.getCus_name());
            ta.setTran_place(tran_place);
            ta.setTran_moode(tran_moode);
            ta.setTran_type("Deposite");
            s.save(ta);
            cus.setCus_balance(ta.getTran_finalbalance());
            s.update(cus);
            s.getTransaction().commit();
            msg = "information saved successfully";
            clearAll();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }

    //    WITH DRAW save
    public void save3() {
        Session s = Util.getSessionFactory().openSession();

        try {
            s.beginTransaction();
            CRUD cr = new CRUD();
            List<Customer> c1 = cr.customselect(cus_acco);
            Customer cus = new Customer();

            if (!c1.isEmpty()) {
                cus = c1.get(0);
            }
            Transac ta = new Transac();
            ta.setCus_acco(cus_acco);
            ta.setTran_finalbalance(cus.getCus_balance());
            ta.setTran_amount(-this.tran_amount);
            ta.setTran_oldbalance(ta.getTran_finalbalance());
            ta.setTran_finalbalance(ta.getTran_finalbalance() - tran_amount);
            ta.setCus_name(cus.getCus_name());
            ta.setTran_place(tran_place);
            ta.setTran_moode(tran_moode);
            ta.setTran_type("WITH DRAW");
            s.save(ta);
            cus.setCus_balance(ta.getTran_finalbalance());
            s.update(cus);
            s.getTransaction().commit();
            msg = "information saved successfully";
            clearAll();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }
    //count methode

    public String selectcount() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select count(Transaction_Id)from Transac");

            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
//        if (cus ==null||cus.isEmpty())
//            cus="1";
        return cus;
    }
    //sum methode

    public String selectsum(int i) {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select Sum(tran_amount)from Transac where cus_acco=?");
            q.setParameter(0, i);
            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
    //one customer

    public void getcus() {
        CRUD cr = new CRUD();
        List<Transac> c = cr.customselecttran(cus_acco);
        List<Customer> c1 = cr.customselect(cus_acco);

        Transac s = new Transac();
        if (!c.isEmpty()) {
            s = c.get(0);
        }

        for (Customer cus : c1) {
//            tran_amount = s.tran_amount;
            tran_oldbalance = cus.getCus_balance();
            tran_finalbalance = s.tran_finalbalance;
            cus_acco = cus.getCus_account_num();
            cus_name = cus.getCus_name();
            tran_type = s.tran_type;
            tran_moode = s.tran_moode;
            tran_place = s.tran_place;
        }
    }

    //table one
    private List selectone() {
        Session s = Util.getSessionFactory().openSession();
        List<Customer> cus = new ArrayList<Customer>();
        try {
            s.beginTransaction();

            org.hibernate.Query q = s.createQuery("from Transac where cus_acco =?");
            q.setParameter(0, cus_acco);
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    //check account
    public void checkaccount() {
        CRUD cr = new CRUD();
        List<Customer> c = cr.customselect(cus_acco);
        if (c.isEmpty()) {
            checkmsg = "not Avaliable!!!!";
        } else {
            checkmsg = "avaliable!!";
        }
    }
    //check account

    public void checkaccount1() {
        CRUD cr = new CRUD();
        List<Customer> c = cr.customselect(cus_acco2);
        if (c.isEmpty()) {
            checkmsg2 = "not Avaliable!!!!";
        }
    }
    //check account

    public void checkaccount3() {
        CRUD cr = new CRUD();
        List<Customer> c = cr.customselect(cus_acco2);
        if (c.isEmpty()) {
            checkmsg2 = "not Avaliable!!!!";
        }

    }

    //count methode
    public String count() {
        Session s = Util.getSessionFactory().openSession();
        String cus = "";
        try {
            s.beginTransaction();
            org.hibernate.Query q = s.createQuery(" select count(cus_acco)from Transac where cus_acco=? ");
            q.setParameter(0, cus_acco);
            cus = q.list().toString();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }

    /////getter and setter
    @Id
    @TableGenerator(name = "de", pkColumnName = "pk", pkColumnValue = "pkv", initialValue = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "de")
    @Column(name = "Transaction_Id", nullable = false, unique = true, length = 255)
    public int getTran_id() {
        return tran_id;
    }

    public void setTran_id(int tran_id) {
        this.tran_id = tran_id;
    }

    @Column(name = "Customer_Accout", length = 255)
    public int getCus_acco() {
        return cus_acco;
    }

    public void setCus_acco(int cus_acco) {
        this.cus_acco = cus_acco;
    }

    @Column(name = "customer_Name", length = 255)
    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    @Column(name = "Transaction_Place", length = 255)
    public String getTran_place() {
        return tran_place;
    }

    public void setTran_place(String tran_place) {
        this.tran_place = tran_place;
    }

    @Column(name = "Transaction_Type", length = 255)
    public String getTran_type() {
        return tran_type;
    }

    public void setTran_type(String tran_type) {
        this.tran_type = tran_type;
    }

    @Column(name = "Transaction_MooDe", length = 255)
    public String getTran_moode() {
        return tran_moode;
    }

    public void setTran_moode(String tran_moode) {
        this.tran_moode = tran_moode;
    }

    @Column(name = "Transaction_Amount", length = 255)
    public int getTran_amount() {
        return tran_amount;
    }

    public void setTran_amount(int tran_amount) {
        this.tran_amount = tran_amount;
    }

    @Column(name = "Transaction_Old_Balance", length = 255)
    public int getTran_oldbalance() {
        return tran_oldbalance;
    }

    public void setTran_oldbalance(int tran_oldbalance) {
        this.tran_oldbalance = tran_oldbalance;
    }

    @Column(name = "Transaction_Final_Balance", length = 255)
    public int getTran_finalbalance() {
        return tran_finalbalance;
    }

    public void setTran_finalbalance(int tran_finalbalance) {
        this.tran_finalbalance = tran_finalbalance;
    }

    @Column(name = "Transaction_Date", length = 255)
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getTran_date() {
        return tran_date = new Date();
    }

    public void setTran_date(Date tran_date) {
        this.tran_date = tran_date;
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
    public int getCus_acco2() {
        return cus_acco2;
    }

    public void setCus_acco2(int cus_acco2) {
        this.cus_acco2 = cus_acco2;
    }

    @Transient
    public String getCheckmsg2() {
        return checkmsg2;
    }

    public void setCheckmsg2(String checkmsg2) {
        this.checkmsg2 = checkmsg2;
    }

    @Transient
    public String getCount() {
        return count = count();
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Transient
    public List<Transac> getTable() {
        return table = selectone();
    }

    public void setTable(List<Transac> table) {
        this.table = table;
    }

}
