package net.abbht.lamo.facades.users;

import java.util.Iterator;
import java.util.List;
import net.abbht.lamo.persistence.users.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.abbht.lamo.utils.*;

/**
 *  @netbeans.hibernate.facade beanClass=net.abbht.lamo.persistence.users.Phone
 */
public class PhoneFacade {
    public void savePhone(Phone phone) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(phone);
        } finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
    }
    
    public void updatePhone(Phone phone) {
        Session session = net.abbht.lamo.utils.HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            session.update(phone);
        } finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
    }
    
    public Phone findByPhoneNo(String ph) {
        String sql = "from net.abbht.lamo.persistence.users.Phone u where u.phoneNo ='" + ph + "'";
        try {
        return (Phone) HibernateUtil.currentSession().createQuery(sql).uniqueResult();
        } finally {
            HibernateUtil.closeSession();
        }        
    }
    
    public List <Phone> findByUsername(String username) {
        String sql = "from net.abbht.lamo.persistence.users.Phone u where u.username ='" + username + "'";
        try {
            return  HibernateUtil.currentSession().createQuery(sql).list();
        } finally {
            HibernateUtil.closeSession();
        }        
    }
    
     public void savePhoneType(PhoneTypes phoneType) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(phoneType);
        } finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
    }
        
    public List <PhoneTypes> findAllPhoneType () {
        String sql = "from net.abbht.lamo.persistence.users.PhoneTypes";
        try {
            return HibernateUtil.currentSession().createQuery(sql).list();
        } finally {
            HibernateUtil.closeSession();
        }        
    }
    
}
