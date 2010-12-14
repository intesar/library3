package com.bizintelapps.cars.ajax;

import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.entity.ResultDto;
import com.bizintelapps.cars.portlet.SessionHandler;
import com.bizintelapps.cars.service.CarService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.imagegallery.model.IGFolder;
import com.liferay.portlet.imagegallery.service.IGFolderLocalServiceUtil;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author intesar
 */
public class CarsAjaxService {

    /**
     *
     * @param car
     */
    public Car saveCar(Car car, HttpSession session) {
        try {
            if (car.getId() == null || car.getPhotosFolderId() == null) {
                ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute(SessionHandler.THEME_DISPLAY);
                ServiceContext serviceContext = (ServiceContext) session.getAttribute(SessionHandler.SERVICE_CONTEXT);
                String name = car.getMake() + " " + car.getMake() + " " + car.getYear();
                String description = car.getVin();
                if(log.isTraceEnabled()) {
                    log.trace("themeDisplay " + themeDisplay);
                    log.trace("serviceContext " + serviceContext);
                }
                IGFolder igFolder = IGFolderLocalServiceUtil.addFolder(themeDisplay.getUserId(), 0L, name, description, serviceContext);
                car.setPhotosFolderId(igFolder.getFolderId());
            }
            return carService.saveCar(car);
        } catch (PortalException ex) {
            log.warn(ex.getMessage(), ex);
        } catch (SystemException ex) {
            log.warn(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     *
     * @param carId
     * @return
     * http://localhost:8080/image/image_gallery?uuid=2589decf-c2d5-423e-a29c-c076f180d52c&groupId=12104
     */
    public Car getCar(Long carId) {
        Car car = carService.getCar(carId);
        if (log.isTraceEnabled()) {
            log.trace(car.toString());
        }
        return car;
    }

    /**
     * User can search cars by applying easy filters on home page
     * @param priceLimit
     * Filter Price
     *  $5000 or less
     *  $5000 - $10000
     *  $1000 or more
     * @param mileageLimit
     * Filter Mileage
     *  50,000 or less
     *  50,000 - 70,000
     *  70,000 or more
     * @param start -- begin with 0
     * @param max -- max elements to return
     *
     * @return
     */
    public ResultDto<Car> search(int priceLimit, int mileageLimit, int start, int max) {
        return carService.search(priceLimit, mileageLimit, start, max);
    }
    /** private methods **/
    protected static final Log log = LogFactory.getLog(CarsAjaxService.class);
    @Autowired
    protected CarService carService;
}
