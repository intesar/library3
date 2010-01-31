package com.opensource.roomate.service;

import com.opensource.roomate.entity.ResultDto;
import com.opensource.roomate.service.util.NewPostAlert;
import com.opensource.roomate.service.util.MessageAlert;
import com.opensource.roomate.service.util.ReportAbuseAlert;
import com.opensource.roomate.entity.Post;
import com.opensource.roomate.entity.PostMessage;
import com.opensource.roomate.entity.PostReportAbuse;
import com.opensource.roomate.entity.SubscribeUser;
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
            MessageAlert er = new MessageAlert(post.getEmail(), post.getId(), message);
            pool.execute(er);
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
            String[] fields = new String[]{"id", "postedBy", "phone", "postDate", "sex", "rent", "addressLine", "city", "zipcode", "country", "comment"};
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
            org.apache.lucene.search.Query query = parser.parse(keywords);
            return executeLuceneQuery(query, currentPage, pageSize);
//            printReport();
        } catch (ParseException e) {
            logger.warn(e);
        }
        return new ResultDto(list, currentPage, pageSize, 0);
    }

    @Override
    public List<Post> searchByCityZipcodeRentAndType(String city, String zipcode, Double maxRent, String type, int currentPage, int pageSize) {
        List<Post> list = new ArrayList<Post>();
        EntityManager em = emf.createEntityManager();
        try {
            String allTypes = "Shared, Shared - Seperate room, New Rental, Commercial";
            String types = type.equals("All") ? allTypes : type;
            list = em.createNamedQuery("Post.findByCityZipcodeRentAndType").setParameter(1, city).setParameter(2, zipcode).setParameter(3, maxRent).setParameter(4, types).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn(e);
        } finally {
            em.close();
        }
        return list;


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
                ReportAbuseAlert er = new ReportAbuseAlert(post.getId(), list);
                pool.execute(er);
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

    public void reIndex() {
        EntityManager em = emf.createEntityManager();
        try {
            List<Post> list = em.createNamedQuery("Post.findAll").getResultList();
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
            for (Post p : list) {
                fullTextEntityManager.index(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
}
