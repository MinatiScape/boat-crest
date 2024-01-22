package com.coveiot.coveaccess.model.server;

import java.util.List;
/* loaded from: classes8.dex */
public class SRegisterReturningUserModel {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private String appTrackerId;
        private String birthDate;
        private String cloveDeviceBleId;
        private String dpUrl;
        private String emailId;
        private String firstName;
        private String gender;
        private int id;
        private int isCloveUser;
        private String lastName;
        private String mobileNumber;
        private String name;
        private List<?> organizationAccepted;
        private List<?> organizationBelongsTo;
        private String profilePictureName;
        private Object socialMediaDetails;
        private int status;

        public String getAppTrackerId() {
            return this.appTrackerId;
        }

        public String getBirthDate() {
            return this.birthDate;
        }

        public String getCloveDeviceBleId() {
            return this.cloveDeviceBleId;
        }

        public String getDpUrl() {
            return this.dpUrl;
        }

        public String getEmailId() {
            return this.emailId;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getGender() {
            return this.gender;
        }

        public int getId() {
            return this.id;
        }

        public int getIsCloveUser() {
            return this.isCloveUser;
        }

        public String getLastName() {
            return this.lastName;
        }

        public String getMobileNumber() {
            return this.mobileNumber;
        }

        public String getName() {
            return this.name;
        }

        public List<?> getOrganizationAccepted() {
            return this.organizationAccepted;
        }

        public List<?> getOrganizationBelongsTo() {
            return this.organizationBelongsTo;
        }

        public String getProfilePictureName() {
            return this.profilePictureName;
        }

        public Object getSocialMediaDetails() {
            return this.socialMediaDetails;
        }

        public int getStatus() {
            return this.status;
        }

        public void setAppTrackerId(String str) {
            this.appTrackerId = str;
        }

        public void setBirthDate(String str) {
            this.birthDate = str;
        }

        public void setCloveDeviceBleId(String str) {
            this.cloveDeviceBleId = str;
        }

        public void setDpUrl(String str) {
            this.dpUrl = str;
        }

        public void setEmailId(String str) {
            this.emailId = str;
        }

        public void setFirstName(String str) {
            this.firstName = str;
        }

        public void setGender(String str) {
            this.gender = str;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setIsCloveUser(int i) {
            this.isCloveUser = i;
        }

        public void setLastName(String str) {
            this.lastName = str;
        }

        public void setMobileNumber(String str) {
            this.mobileNumber = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setOrganizationAccepted(List<?> list) {
            this.organizationAccepted = list;
        }

        public void setOrganizationBelongsTo(List<?> list) {
            this.organizationBelongsTo = list;
        }

        public void setProfilePictureName(String str) {
            this.profilePictureName = str;
        }

        public void setSocialMediaDetails(Object obj) {
            this.socialMediaDetails = obj;
        }

        public void setStatus(int i) {
            this.status = i;
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
