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
    public Car findByStock(long stock) {
        return (Car) entityManager.createNamedQuery("Car.findByStock").setParameter(1, stock).getSingleResult();
    }

    @Override
    public Car findCarByImageFolderId(long folderId) {
        return entityManager.createNamedQuery("Car.findByImageFolderId", Car.class).setParameter("photosFolderId", folderId).getSingleResult();
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
    public List<Car> search(int priceLimit, int mileageLimit, int sortBy, int start, int max) {
        List<Car> list = null;
        String ql = "SELECT c FROM Car c WHERE c.active = true ";
        if (priceLimit > 0) {
            if (priceLimit == 10) {
                ql += " AND c.askingPrice <= 5000.0";
            } else if (priceLimit == 20) {
                ql += " AND c.askingPrice BETWEEN 5000.0 and 10000.0";
            } else if (priceLimit == 30) {
                ql += " AND c.askingPrice >= 10000.0";
            }
        }
        if (mileageLimit > 0) {
            if (mileageLimit == 10) {
                ql += " AND c.mileage <= 30000";
            } else if (mileageLimit == 20) {
                ql += " AND c.mileage BETWEEN 30000 and 50000";
            } else if (mileageLimit == 30) {
                ql += " AND c.mileage >= 50000";
            }
        }
        if (sortBy == 1) {
            ql += " ORDER BY c.createDate DESC";
        } else if (sortBy == 2) {
            ql += " ORDER BY c.askingPrice";
        } else if (sortBy == 3) {
            ql += " ORDER BY c.mileage";
        } else if (sortBy == 4) {
            ql += " ORDER BY c.year DESC";
        }
        Query query = entityManager.createQuery(ql);
        query.setFirstResult(start);
        query.setMaxResults(max);
        list = query.getResultList();
        return list;
    }
    protected static final Log logger = LogFactory.getLog(CarDaoImpl.class);
}
