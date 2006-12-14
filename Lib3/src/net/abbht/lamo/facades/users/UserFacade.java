package net.abbht.lamo.facades.users;

import java.util.Iterator;
import java.util.List;
import net.abbht.lamo.persistence.users.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.abbht.lamo.utils.*;

/**
 *
 *
 * @netbeans.hibernate.facade beanClass = "net.abbht.lamo.persistence.users.User
 * "
 */
public class UserFacade {
    public void saveUser(User user) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
        } finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
    }
    
    public void saveOrUpdateUser(User user) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
        } finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
    }
    
    public List findByLastName(String lastname) {
        String sql = "from net.abbht.lamo.persistence.users.User u where u.lastname = '" + lastname + "'";
        try {
            return  HibernateUtil.currentSession().createQuery(sql).list();
        } finally {
            HibernateUtil.closeSession();
        }
        
        
    }
    public List findByFirstName(String firstname) {
        String sql = "from net.abbht.lamo.persistence.users.User u where u.firstname = '" + firstname + "'";
        try {
            return  HibernateUtil.currentSession().createQuery(sql).list();
        } finally {
            HibernateUtil.closeSession();
        }        
    }
    public List findByTitle(String title) {
        String sql = "from net.abbht.lamo.persistence.users.User u where u.title = '" + title + "'";
        try {
            return  HibernateUtil.currentSession().createQuery(sql).list();
        } finally {
            HibernateUtil.closeSession();
        }        
    }
    
   public List findByEmail(String email) {
        String sql = "from net.abbht.lamo.persistence.users.User u where u.email = '" + email + "'";
        try {
            return  HibernateUtil.currentSession().createQuery(sql).list();
        } finally {
            HibernateUtil.closeSession();
        }        
    }    
   
    public List findByTotalDueDays(String days) {
        String sql = "from net.abbht.lamo.persistence.users.User u where u.totalduedays = '" + days + "'";
        try {
            return  HibernateUtil.currentSession().createQuery(sql).list();
        } finally {
            HibernateUtil.closeSession();
        }        
    }  
   
    public User findByUserName(String username) {
        String sql = "from net.abbht.lamo.persistence.users.User u where u.username = '" + username + "'";
        User u = null;
        try {
            u = (User) HibernateUtil.currentSession().createQuery(sql).uniqueResult();
        } finally {
            HibernateUtil.closeSession();
        }
        return u;
    }
    
}
