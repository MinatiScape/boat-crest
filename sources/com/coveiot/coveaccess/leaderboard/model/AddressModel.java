package com.coveiot.coveaccess.leaderboard.model;

import java.io.Serializable;
/* loaded from: classes8.dex */
public final class AddressModel implements Serializable {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean implements Serializable {
        private String administrativeArea;
        private String city;
        private String country;
        private String createdDate;
        private int id;
        private double latitude;
        private String locality;
        private String locationType;
        private double longitude;
        private String placeId;
        private int userId;

        public String getAdministrativeArea() {
            return this.administrativeArea;
        }

        public String getCity() {
            return this.city;
        }

        public String getCountry() {
            return this.country;
        }

        public String getCreatedDate() {
            return this.createdDate;
        }

        public int getId() {
            return this.id;
        }

        public double getLatitude() {
            return this.latitude;
        }

        public String getLocality() {
            return this.locality;
        }

        public String getLocationType() {
            return this.locationType;
        }

        public double getLongitude() {
            return this.longitude;
        }

        public String getPlaceId() {
            return this.placeId;
        }

        public int getUserId() {
            return this.userId;
        }

        public void setAdministrativeArea(String str) {
            this.administrativeArea = str;
        }

        public void setCity(String str) {
            this.city = str;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public void setCreatedDate(String str) {
            this.createdDate = str;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setLatitude(double d) {
            this.latitude = d;
        }

        public void setLocality(String str) {
            this.locality = str;
        }

        public void setLocationType(String str) {
            this.locationType = str;
        }

        public void setLongitude(double d) {
            this.longitude = d;
        }

        public void setPlaceId(String str) {
            this.placeId = str;
        }

        public void setUserId(int i) {
            this.userId = i;
        }
    }

    public DataBean getData() {
        return this.data;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public void setStatus(String str) {
        this.status = str;
    }
}
