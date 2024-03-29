

package com.bizintelapps.cars.dao;

import com.bizintelapps.cars.entity.Car;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface CarDao extends GenericDao<Car, Long> {

    /**
     * 
     * @param stock
     * @return
     */
    Car findByStock(long stock);
    /**
     * 
     * @param start
     * @param max
     * @return
     */
    List<Car> search(int priceLimit, int mileageLimit, int sortBy, int start, int max);

    /**
     * 
     * @param folderId
     * @return
     */
    Car findCarByImageFolderId(long folderId);
}
