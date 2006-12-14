package net.abbht.lamo.facades.users;

import java.util.Iterator;
import java.util.List;
import net.abbht.lamo.persistence.users.*;
import net.abbht.lamo.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *  @netbeans.hibernate.facade beanClass=net.abbht.lamo.persistence.users.Address
 */
public class AddressFacade {
    public void saveAddress(Address address) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(address);
        } finally {
            tx.commit();
            net.abbht.lamo.utils.HibernateUtil.closeSession();
        }
    }
    
    public void updateAddress(Address address) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            session.update(address);
        } finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
    }
    
   public List <Address> findByUsername(String username) {
        String sql = "from net.abbht.lamo.persistence.users.Address u where u.username = '" + username + "'";
        Session session = HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            session.beginTransaction();
            return HibernateUtil.currentSession().createQuery(sql).list();
        } finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
        
    }
  }
