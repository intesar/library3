package com.opensource.roomate.service;

import com.opensource.roomate.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.apache.log4j.Logger;
/**
 *
 * @author intesar shannan mohammed
 *  mdshannan@gmail.com
 */
public class RoomateServiceImpl implements RoomateService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Roomate");
    static Logger logger = Logger.getLogger(RoomateServiceImpl.class);
    
    public void post(Post post) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(post);
            em.getTransaction().commit();
        } catch (Exception e) {
            logger.warn(e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public List<Post> search(String keywords, int currentPage, int pageSize) {
        try {
            // create native Lucene query
            String[] fields = new String[]{"id", "postedBy", "posterContact", "postDate", "sex", "rent", "addressLine", "city", "zipcode", "usa"};
            MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, new StandardAnalyzer());
            org.apache.lucene.search.Query query = parser.parse(keywords);
            return executeLuceneQuery(query, currentPage, pageSize);
        } catch (ParseException e) {
            logger.warn(e);
        }
        return new ArrayList<Post>();
    }

    private List<Post> executeLuceneQuery(Query query, int currentPage, int pageSize) {
        EntityManager em = emf.createEntityManager();
        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Post.class);
//        int resultSize = fullTextQuery.getResultSize();
        org.apache.lucene.search.Sort sort = new Sort(new SortField("postDate", true));
        fullTextQuery.setSort(sort);
        fullTextQuery.setFirstResult(currentPage);
        fullTextQuery.setMaxResults(pageSize);
        // execute search
        List<Post> result = fullTextQuery.getResultList();
        return result;
    }
}
