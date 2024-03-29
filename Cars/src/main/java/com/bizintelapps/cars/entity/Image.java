package com.bizintelapps.cars.entity;

import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author intesar
 */
@Embeddable
public class Image implements Serializable {

    private String uuid;
    private long groupId;
    private boolean prefered;
    private long imageId;
    private long smallImageId;
    private long largeImageId;

    public Image() {
    }

    public Image(String uuid, long groupId, boolean prefered, long imageId, long smallImageId, long largeImageId) {
        this.uuid = uuid;
        this.groupId = groupId;
        this.prefered = prefered;
        this.imageId = imageId;
        this.smallImageId = smallImageId;
        this.largeImageId = largeImageId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public boolean isPrefered() {
        return prefered;
    }

    public void setPrefered(boolean prefered) {
        this.prefered = prefered;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public long getLargeImageId() {
        return largeImageId;
    }

    public void setLargeImageId(long largeImageId) {
        this.largeImageId = largeImageId;
    }

    public long getSmallImageId() {
        return smallImageId;
    }

    public void setSmallImageId(long smallImageId) {
        this.smallImageId = smallImageId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Image other = (Image) obj;
        if ((this.uuid == null) ? (other.uuid != null) : !this.uuid.equals(other.uuid)) {
            return false;
        }
        if (this.groupId != other.groupId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.uuid != null ? this.uuid.hashCode() : 0);
        hash = 11 * hash + (int) (this.groupId ^ (this.groupId >>> 32));
        return hash;
    }
}
