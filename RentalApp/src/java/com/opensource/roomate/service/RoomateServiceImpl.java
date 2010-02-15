package com.opensource.roomate.service;

import com.opensource.roomate.entity.ContactUs;
import com.opensource.roomate.entity.ResultDto;
import com.opensource.roomate.service.util.NewPostAlert;
import com.opensource.roomate.service.util.MessageAlert;
import com.opensource.roomate.service.util.ReportAbuseAlert;
import com.opensource.roomate.entity.Post;
import com.opensource.roomate.entity.PostMessage;
import com.opensource.roomate.entity.PostRemoved;
import com.opensource.roomate.entity.PostReportAbuse;
import com.opensource.roomate.entity.SubscribeUser;
import com.opensource.roomate.service.util.ContactUsAlert;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TemporalType;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.apache.log4j.Logger;
import org.hibernate.search.jpa.Search;

/**
 *
 * @author intesar shannan mohammed
 *  mdshannan@gmail.com
 */
public class RoomateServiceImpl implements RoomateService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Roomate");
    static Logger logger = Logger.getLogger(RoomateServiceImpl.class);
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    @Override
    public void addPost(Post post, String ip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            post.setEmail(post.getEmailTransient());
            post.setCreateIp(ip);
            post.setCreateDate(new Date());
            post.setPostDate(new Date());
            post.setRentCategory(calculateRange(post.getRent()));
            em.persist(post);
            em.getTransaction().commit();
            sendMail(post.getEmail(), post.getPostDate());
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void updatePost(Post post, String email, String ip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Post post1 = (Post) em.createNamedQuery("Post.findByEmaiAndId").setParameter(1, email).setParameter(2, post.getId()).getSingleResult();
            post1.setPostedBy(post.getPostedBy());
            post1.setPhone(post.getPhone());
            post1.setAddressLine(post.getAddressLine());
            post1.setCity(post.getCity());
            post1.setZipcode(post.getZipcode());
            post1.setRent(post.getRent());
            post1.setRentalType(post.getRentalType());
            post1.setBeds(post.getBeds());
            post1.setArea(post.getArea());
            post1.setComment(post.getComment());
            post1.setUpdateIp(ip);
            post1.setUpdateDate(new Date());
            em.merge(post1);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void deletePost(long postId, String email, String ip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Post post1 = (Post) em.createNamedQuery("Post.findByEmaiAndId").setParameter(1, email).setParameter(2, postId).getSingleResult();
            post1.setDeleteIp(ip);
            post1.setDeleteDate(new Date());
            em.merge(post1);
            PostRemoved pr = new PostRemoved();
            copy(post1, pr);
            em.persist(pr);
            em.remove(post1);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void sendMessage(long postId, String message, String ip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Post post = (Post) em.find(Post.class, postId);
            PostMessage pm = new PostMessage(null, message, new Date(), ip, new Date(), post);
            em.persist(pm);
            Runnable runnable = new MessageAlert(post.getEmail(), post.getId(), message);
            pool.execute(runnable);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void subscribeUser(String email, String keywords, String ip) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            SubscribeUser su = new SubscribeUser(null, email, keywords, ip, new Date());
            em.persist(su);
            em.getTransaction().commit();
//            sendMail(post.getEmail(), post.getPostDate());
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public ResultDto searchById(Long id) {
        List list = new ArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            Post post = (Post) em.createNamedQuery("Post.findById").setParameter(1, id).getSingleResult();
            list.add(post);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
        } finally {
            em.close();
        }
        return new ResultDto(list, 0, 1, 1);
    }

    @Override
    public ResultDto search(String keywords, int currentPage, int pageSize) {
        List<Post> list = new ArrayList<Post>();
        try {
            // create native Lucene query
            String[] fields = new String[]{"id", "postedBy", "phone", "postDate", "sex", "rentalType", "rentCategory", "addressLine", "city", "zipcode", "country", "comment"};
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
//            org.apache.lucene.queryParser.QueryParser parser =
//                    new QueryParser("id", new StopAnalyzer());
            org.apache.lucene.search.Query query = parser.parse(keywords);
            return executeLuceneQuery(query, currentPage, pageSize);
//            printReport();
        } catch (ParseException e) {
            logger.warn(e);
        }
        return new ResultDto(list, currentPage, pageSize, 0);
    }

    @Override
    public ResultDto searchByEmailAndId(String email, long id) {
        List list = new ArrayList();
        EntityManager em = emf.createEntityManager();
        try {
            Post post = (Post) em.createNamedQuery("Post.findByEmaiAndId").setParameter(1, email).setParameter(2, id).getSingleResult();
            list.add(post);
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
        } finally {
            em.close();
        }
        return new ResultDto(list, 0, 1, 1);
    }

    @Override
    public void reportAbuse(Long postId, String reportType, String reportIp) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Post post = (Post) em.find(Post.class, postId);
            post.setReportAbuse(post.getReportAbuse() + 1);
            em.merge(post);
            PostReportAbuse pm = new PostReportAbuse(null, reportType, reportIp, new Date(), post);
            em.persist(pm);
            // alerting admin
            if (post.getReportAbuse() > 1) {
                List<PostReportAbuse> list = em.createNamedQuery("PostReportAbuse.findByPostId").setParameter("postId", post.getId()).getResultList();
                list.add(pm);
                Runnable runnable  = new ReportAbuseAlert(post.getId(), list);
                pool.execute(runnable);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    @Override
    public void reIndex() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Post> list = em.createNamedQuery("Post.findAll").getResultList();
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
            for (Post p : list) {
                p.setRentCategory(calculateRange(p.getRent()));
                fullTextEntityManager.index(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
        } finally {
            em.close();
        }
    }

    @Override
    public void contactUs(ContactUs contactUs) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(contactUs);
            // alerting admin
            Runnable runnable = new ContactUsAlert(contactUs);
            pool.execute(runnable);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            logger.warn(e);
        } finally {
            em.close();
        }
    }
    // ------------------------------------------------------------------------//

    private ResultDto executeLuceneQuery(Query query, int currentPage, int pageSize) {
        EntityManager em = emf.createEntityManager();
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Post.class);
        org.apache.lucene.search.Sort sort = new Sort(new SortField("postDate", true));
        fullTextQuery.setSort(sort);
        fullTextQuery.setFirstResult(currentPage);
        fullTextQuery.setMaxResults(pageSize);
        List<Post> result = fullTextQuery.getResultList();
        int total = fullTextQuery.getResultSize();
        return new ResultDto(result, currentPage, pageSize, total);
    }

    private void sendMail(String email, Date postDate) {
        EntityManager em = emf.createEntityManager();
        try {
            Post p = (Post) em.createNamedQuery("Post.findByEmailAndPostDate").setParameter(1, email).setParameter(2, postDate, TemporalType.TIMESTAMP).getSingleResult();
            pool.execute(new NewPostAlert(p.getEmail(), p.getId()));
        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    private void printReport() {
//        EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl) emf;
//        System.out.println(" statistics start..");
//        System.out.println(empImpl.getSessionFactory().getStatistics());
//        System.out.println(" statistics end..");
    }

    /**
     * 1 - 100 = 100
     * 101 - 200 = 200
     * 201 - 400 = 400
     * 401 - 600 = 600
     * 601 - 800 = 800
     * 801 - 1000 = 1000
     * 1001 - 1200 = 1200
     * 1201 - 1400 = 1400
     * 1401 - 1600 = 1600
     * 1601 - 1800 = 1800
     * 1801 - 2000 = 2000
     * 2001 - 2500 = 2500
     * 2501 - 3000 = 3000
     * @param rent
     * @return
     */
    private static int calculateRange(Double rent) {
        if (rent == null) {
            return 0;
        }
        return (int) Math.ceil(rent / 100);
    }

    private void copy(Post p, PostRemoved pr) {
        pr.setAddressLine(p.getAddressLine());
        pr.setArea(p.getArea());
        pr.setBeds(p.getBeds());
        pr.setCity(p.getCity());
        pr.setComment(p.getComment());
        pr.setCountry(p.getCountry());
        pr.setCreateDate(p.getCreateDate());
        pr.setCreateIp(p.getCreateIp());
        pr.setCurrency(p.getCurrency());
        pr.setDate(p.getDate());
        pr.setDeleteDate(p.getDeleteDate());
        pr.setDeleteIp(p.getDeleteIp());
        pr.setEmail(p.getEmail());
        pr.setId(p.getId());
        pr.setPhone(p.getPhone());
        pr.setPostDate(p.getPostDate());
        pr.setPostedBy(p.getPostedBy());
        pr.setRent(p.getRent());
        pr.setRentCategory(p.getRentCategory());
        pr.setRentalType(p.getRentalType());
        pr.setReportAbuse(p.getReportAbuse());
        pr.setSex(p.getSex());
        pr.setUpdateDate(p.getUpdateDate());
        pr.setUpdateIp(p.getUpdateIp());
        pr.setZipcode(pr.getZipcode());
    }
}
