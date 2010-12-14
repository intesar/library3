package com.bizintelapps.cars.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 *
 * @author intesar
 */
//@Cacheable(true)
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE, region="")
@Entity
@NamedQueries({
    @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c ORDER BY c.createDate DESC"),
    @NamedQuery(name = "Car.findByImageFolderId", query = "SELECT c FROM Car c WHERE c.photosFolderId = :photosFolderId")
})
public class Car implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private String style;
    private String bodyStyle;
    @Column(name = "year_")
    private Integer year;
    private Double askingPrice;
    private Long mileage;
    private String exteriorColor;
    private String interiorColor;
    private String vin;
    private String fuleType;
    // gas diesel
    private String transmission;
    // 4,5,6,8
    private String engineCylinder;
    private String driveTrain;
    // 2, 3, 4, 5
    private String doors;
    private Boolean acFront = true;
    private Boolean acRear = true;
    private Boolean airbagDriver = true;
    private Boolean airbagPassenger = true;
    private Boolean airbagSide = true;
    private Boolean alarm = true;
    private Boolean alloyWheels = true;
    private Boolean antilockBrakes = true;
    private Boolean bucketSeats = true;
    private Boolean cdChanger = true;
    private Boolean casset = true;
    private Boolean cdPlayer = true;
    private Boolean cruiseControl = true;
    private Boolean fogLights = true;
    private Boolean leatherInterior = true;
    private Boolean memorySeats = true;
    private Boolean navigation = true;
    private Boolean powerLocks = true;
    private Boolean powerSeats = true;
    private Boolean powerStearing = true;
    private Boolean powerWindows = true;
    private Boolean premiumSound = true;
    private Boolean readWindowDefronster = true;
    private Boolean rearWindowWiper = true;
    private Boolean remoteKeyless = true;
    private Boolean soundSystem = true;
    private Boolean sunroof = true;
    private Boolean thirdRowSeats = true;
    private Boolean tintedGlass = true;
    private Boolean towPackage = true;
    private Boolean dvd = true;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date buyDate;
    private String ownerName;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date createDate;
    private String createdBy;
    private String comments;
    private String video;
    private Boolean sold;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date soldDate;
    private Double sellingPrice;
    private String sellerComments;
    private Boolean active = true;
    private Long photosFolderId;
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @ElementCollection(fetch=FetchType.EAGER)
    private List<Image> images = new ArrayList<Image>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getAcFront() {
        return acFront;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setAcFront(Boolean acFront) {
        this.acFront = acFront;
    }

    public Boolean getAcRear() {
        return acRear;
    }

    public void setAcRear(Boolean acRear) {
        this.acRear = acRear;
    }

    public Boolean getAirbagDriver() {
        return airbagDriver;
    }

    public void setAirbagDriver(Boolean airbagDriver) {
        this.airbagDriver = airbagDriver;
    }

    public Boolean getAirbagPassenger() {
        return airbagPassenger;
    }

    public void setAirbagPassenger(Boolean airbagPassenger) {
        this.airbagPassenger = airbagPassenger;
    }

    public Boolean getAirbagSide() {
        return airbagSide;
    }

    public void setAirbagSide(Boolean airbagSide) {
        this.airbagSide = airbagSide;
    }

    public Boolean getAlarm() {
        return alarm;
    }

    public void setAlarm(Boolean alarm) {
        this.alarm = alarm;
    }

    public Boolean getAlloyWheels() {
        return alloyWheels;
    }

    public void setAlloyWheels(Boolean alloyWheels) {
        this.alloyWheels = alloyWheels;
    }

    public Boolean getAntilockBrakes() {
        return antilockBrakes;
    }

    public void setAntilockBrakes(Boolean antilockBrakes) {
        this.antilockBrakes = antilockBrakes;
    }

    public Double getAskingPrice() {
        return askingPrice;
    }

    public void setAskingPrice(Double askingPrice) {
        this.askingPrice = askingPrice;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public Boolean getBucketSeats() {
        return bucketSeats;
    }

    public void setBucketSeats(Boolean bucketSeats) {
        this.bucketSeats = bucketSeats;
    }

    public Boolean getCasset() {
        return casset;
    }

    public void setCasset(Boolean casset) {
        this.casset = casset;
    }

    public Boolean getCdChanger() {
        return cdChanger;
    }

    public void setCdChanger(Boolean cdChanger) {
        this.cdChanger = cdChanger;
    }

    public Boolean getCdPlayer() {
        return cdPlayer;
    }

    public void setCdPlayer(Boolean cdPlayer) {
        this.cdPlayer = cdPlayer;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Boolean getCruiseControl() {
        return cruiseControl;
    }

    public void setCruiseControl(Boolean cruiseControl) {
        this.cruiseControl = cruiseControl;
    }

    public String getDoors() {
        return doors;
    }

    public void setDoors(String doors) {
        this.doors = doors;
    }

    public String getDriveTrain() {
        return driveTrain;
    }

    public void setDriveTrain(String driveTrain) {
        this.driveTrain = driveTrain;
    }

    public Boolean getDvd() {
        return dvd;
    }

    public void setDvd(Boolean dvd) {
        this.dvd = dvd;
    }

    public String getEngineCylinder() {
        return engineCylinder;
    }

    public void setEngineCylinder(String engineCylinder) {
        this.engineCylinder = engineCylinder;
    }

    public String getExteriorColor() {
        return exteriorColor;
    }

    public void setExteriorColor(String exteriorColor) {
        this.exteriorColor = exteriorColor;
    }

    public Boolean getFogLights() {
        return fogLights;
    }

    public void setFogLights(Boolean fogLights) {
        this.fogLights = fogLights;
    }

    public String getFuleType() {
        return fuleType;
    }

    public void setFuleType(String fuleType) {
        this.fuleType = fuleType;
    }

    public String getInteriorColor() {
        return interiorColor;
    }

    public void setInteriorColor(String interiorColor) {
        this.interiorColor = interiorColor;
    }

    public Boolean getLeatherInterior() {
        return leatherInterior;
    }

    public void setLeatherInterior(Boolean leatherInterior) {
        this.leatherInterior = leatherInterior;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public Boolean getMemorySeats() {
        return memorySeats;
    }

    public void setMemorySeats(Boolean memorySeats) {
        this.memorySeats = memorySeats;
    }

    public Long getMileage() {
        return mileage;
    }

    public void setMileage(Long mileage) {
        this.mileage = mileage;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Boolean getNavigation() {
        return navigation;
    }

    public void setNavigation(Boolean navigation) {
        this.navigation = navigation;
    }

    public Boolean getPowerLocks() {
        return powerLocks;
    }

    public void setPowerLocks(Boolean powerLocks) {
        this.powerLocks = powerLocks;
    }

    public Boolean getPowerSeats() {
        return powerSeats;
    }

    public void setPowerSeats(Boolean powerSeats) {
        this.powerSeats = powerSeats;
    }

    public Boolean getPowerStearing() {
        return powerStearing;
    }

    public void setPowerStearing(Boolean powerStearing) {
        this.powerStearing = powerStearing;
    }

    public Boolean getPowerWindows() {
        return powerWindows;
    }

    public void setPowerWindows(Boolean powerWindows) {
        this.powerWindows = powerWindows;
    }

    public Boolean getPremiumSound() {
        return premiumSound;
    }

    public void setPremiumSound(Boolean premiumSound) {
        this.premiumSound = premiumSound;
    }

    public Boolean getReadWindowDefronster() {
        return readWindowDefronster;
    }

    public void setReadWindowDefronster(Boolean readWindowDefronster) {
        this.readWindowDefronster = readWindowDefronster;
    }

    public Boolean getRearWindowWiper() {
        return rearWindowWiper;
    }

    public void setRearWindowWiper(Boolean rearWindowWiper) {
        this.rearWindowWiper = rearWindowWiper;
    }

    public Boolean getRemoteKeyless() {
        return remoteKeyless;
    }

    public void setRemoteKeyless(Boolean remoteKeyless) {
        this.remoteKeyless = remoteKeyless;
    }

    public Boolean getSoundSystem() {
        return soundSystem;
    }

    public void setSoundSystem(Boolean soundSystem) {
        this.soundSystem = soundSystem;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Boolean getSunroof() {
        return sunroof;
    }

    public void setSunroof(Boolean sunroof) {
        this.sunroof = sunroof;
    }

    public Boolean getThirdRowSeats() {
        return thirdRowSeats;
    }

    public void setThirdRowSeats(Boolean thirdRowSeats) {
        this.thirdRowSeats = thirdRowSeats;
    }

    public Boolean getTintedGlass() {
        return tintedGlass;
    }

    public void setTintedGlass(Boolean tintedGlass) {
        this.tintedGlass = tintedGlass;
    }

    public Boolean getTowPackage() {
        return towPackage;
    }

    public void setTowPackage(Boolean towPackage) {
        this.towPackage = towPackage;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getSellerComments() {
        return sellerComments;
    }

    public void setSellerComments(String sellerComments) {
        this.sellerComments = sellerComments;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public Date getSoldDate() {
        return soldDate;
    }

    public void setSoldDate(Date soldDate) {
        this.soldDate = soldDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Long getPhotosFolderId() {
        return photosFolderId;
    }

    public void setPhotosFolderId(Long photosFolderId) {
        this.photosFolderId = photosFolderId;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Car)) {
            return false;
        }
        Car other = (Car) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + "make=" + make + "model=" + model + "style=" + style + "bodyStyle=" + bodyStyle + "year=" + year + "askingPrice=" + askingPrice + "mileage=" + mileage + "exteriorColor=" + exteriorColor + "interiorColor=" + interiorColor + "vin=" + vin + "fuleType=" + fuleType + "transmission=" + transmission + "engineCylinder=" + engineCylinder + "driveTrain=" + driveTrain + "doors=" + doors + "acFront=" + acFront + "acRear=" + acRear + "airbagDriver=" + airbagDriver + "airbagPassenger=" + airbagPassenger + "airbagSide=" + airbagSide + "alarm=" + alarm + "alloyWheels=" + alloyWheels + "antilockBrakes=" + antilockBrakes + "bucketSeats=" + bucketSeats + "cdChanger=" + cdChanger + "casset=" + casset + "cdPlayer=" + cdPlayer + "cruiseControl=" + cruiseControl + "fogLights=" + fogLights + "leatherInterior=" + leatherInterior + "memorySeats=" + memorySeats + "navigation=" + navigation + "powerLocks=" + powerLocks + "powerSeats=" + powerSeats + "powerStearing=" + powerStearing + "powerWindows=" + powerWindows + "premiumSound=" + premiumSound + "readWindowDefronster=" + readWindowDefronster + "rearWindowWiper=" + rearWindowWiper + "remoteKeyless=" + remoteKeyless + "soundSystem=" + soundSystem + "sunroof=" + sunroof + "thirdRowSeats=" + thirdRowSeats + "tintedGlass=" + tintedGlass + "towPackage=" + towPackage + "dvd=" + dvd + "buyDate=" + buyDate + "ownerName=" + ownerName + "createDate=" + createDate + "createdBy=" + createdBy + "comments=" + comments + "sold=" + sold + "soldDate=" + soldDate + "sellingPrice=" + sellingPrice + "sellerComments=" + sellerComments + "active=" + active + '}';
    }
}
