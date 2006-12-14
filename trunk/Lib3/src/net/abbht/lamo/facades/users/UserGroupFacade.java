package net.abbht.lamo.facades.users;

import java.util.Iterator;
import java.util.List;
import net.abbht.lamo.persistence.users.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import net.abbht.lamo.utils.*;
/**
 *  @netbeans.hibernate.facade beanClass=net.abbht.lamo.persistence.users.UserGroup
 */
public class UserGroupFacade {
    public void saveUserGroup(UserGroup userGroup) {
        Session session = HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(userGroup);
        }  finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
    }
    
    public void updateUserGroup(UserGroup userGroup) {
        Session session = net.abbht.lamo.utils.HibernateUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(userGroup);
        }  finally {
            tx.commit();
            HibernateUtil.closeSession();
        }
    }
    
    
    public UserGroup findByGroupName(String gname) {
        String sql = "from net.abbht.lamo.persistence.users.UserGroup u where u.groupname = '" + gname + "'";
        try {
            return ( UserGroup ) HibernateUtil.currentSession().createQuery(sql).uniqueResult();
        }  finally {
            HibernateUtil.closeSession();
        }
    }
    public List findAll () {
        String sql = "from net.abbht.lamo.persistence.users.UserGroup";
        try {
            return HibernateUtil.currentSession().createQuery(sql).list();
        }  finally {
            HibernateUtil.closeSession();
        }
    }    
}
