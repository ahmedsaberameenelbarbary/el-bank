package project;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CRUD implements DBmethodes {
    
    @Override
    public void insert(share c) {
        Session s = Util.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            s.save(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }
    
    @Override
    public void update(share c) {
        Session s = Util.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            s.update(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
    }
    
    @Override
    public List customselect(int c) {
        Session s = Util.getSessionFactory().openSession();
        List<Customer> cus = new ArrayList<Customer>();
        try {
            s.beginTransaction();
            Query q = s.createQuery("from Customer where customer_Account =?");
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
    public List customselectuser(int c) {
        Session s = Util.getSessionFactory().openSession();
        List<useres> cus = new ArrayList<useres>();
        try {
            s.beginTransaction();
            Query q = s.createQuery("from useres where id =?");
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
    
    @Override
    public List customselecttran(int c) {
        Session s = Util.getSessionFactory().openSession();
        List<Transac> cus = new ArrayList<Transac>();
        try {
            s.beginTransaction();
            Query q = s.createQuery("from Transac where Customer_Accout =?");
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

    @Override
    public List allcustselect(share a) {
        Session s = Util.getSessionFactory().openSession();
        List<Customer> cus = new ArrayList<Customer>();
        try {
            s.beginTransaction();
            
            org.hibernate.Query q = s.createQuery("from Customer");
            cus = q.list();
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }
        return cus;
    }
    
    @Override
    public void deletecustomer(int i) {
        Session s = Util.getSessionFactory().openSession();
        try {
            s.beginTransaction();
            useres c = (useres) s.load(useres.class, new Integer(i));
            s.delete(c);
            s.getTransaction().commit();
        } catch (Exception ex) {
            s.getTransaction().rollback();
        } finally {
            s.close();
        }        
    }

    ///get number from string
    @Override
    public void select(share c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public int getnumfromstr(String s) {
        String ss = "";
        if (s == null || s.isEmpty() || "[null]".equals(s)) {
            s = "0";
        }
        
        char ch[] = s.toCharArray();
        for (char h : ch) {
            if (h == '0' || h == '1' || h == '2' || h == '3' || h == '4' || h == '5' || h == '6' || h == '7' || h == '8' || h == '9') {
                ss += h;
            }
        }
        return Integer.parseInt(ss);
    }
    
}
