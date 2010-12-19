package com.bizintelapps.cars.dao.impl;

import com.bizintelapps.cars.dao.CarDao;
import com.bizintelapps.cars.entity.Car;
import java.util.List;
import javax.persistence.Query;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

/**
 *
 * @author intesar
 */
@Repository
public class CarDaoImpl extends GenericDaoImpl<Car, Long> implements CarDao {

    public CarDaoImpl() {
        super(Car.class);
    }

    @Override
    public Car findCarByImageFolderId(long folderId) {
        return entityManager.createNamedQuery("Car.findByImageFolderId", Car.class)
                .setParameter("photosFolderId", folderId)
                .getSingleResult();
    }
   

    /**
     *
     *
     * @param priceLimit
     *         remove filter  --> 0
     *         less then $5000 --> 10
     *         $5,000 - $10,000 --> 20
     *         $10,000 or more --> 30
     *
     * @param mileageLimit
     *         remove filter  --> 0
     *         less then 30,000 --> 10
     *         30,000 - 50,000 --> 20
     *         50,000 or more --> 30
     * @param start
     * @param max
     * @return
     */
    @Override
    public List<Car> search(int priceLimit, int mileageLimit, int start, int max) {
        List<Car> list = null;
        String ql = "SELECT c FROM Car c ";
        boolean isPriceLimitEnabled = false;
        if (priceLimit > 0) {
            if (priceLimit == 10) {
                ql += "WHERE c.askingPrice <= 5000.0";
            } else if (priceLimit == 10) {
                ql += "WHERE c.askingPrice BETWEEN 5000.0 and 10000.0";
            } else if (priceLimit == 10) {
                ql += "WHERE c.askingPrice >= 10000.0";
            }
            isPriceLimitEnabled = true;
        }
        if (mileageLimit > 0) {
            if ( isPriceLimitEnabled ) {
                ql += " AND ";
            } else {
                ql += " WHERE ";
            }
            if (mileageLimit == 10) {
                ql += "c.mileage <= 30000";
            } else if (mileageLimit == 10) {
                ql += "c.mileage BETWEEN 30000 and 50000";
            } else if (mileageLimit == 10) {
                ql += "c.mileage >= 50000";
            }
        }
        ql += " ORDER BY c.createDate DESC";
        Query query = entityManager.createQuery(ql);
        query.setFirstResult(start);
        query.setMaxResults(max);
        list = query.getResultList();
        return list;
    }

    protected static final Log logger = LogFactory.getLog(CarDaoImpl.class);
}