package com.coveiot.coveaccess.model.server;
/* loaded from: classes8.dex */
public class SRegisterNewUserModel {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private String appTrackerId;
        private String axTrackerId;
        private Object birthDate;
        private String cloveDeviceBleId;
        private String dpUrl;
        private Object emailId;
        private Object gender;
        private int id;
        private int isCloveUser;
        private String mobileNumber;
        private String name;
        private String profilePictureName;
        private String socialMediaDetails;
        private int status;

        public String getAppTrackerId() {
            return this.appTrackerId;
        }

        public String getAxTrackerId() {
            return this.axTrackerId;
        }

        public Object getBirthDate() {
            return this.birthDate;
        }

        public String getCloveDeviceBleId() {
            return this.cloveDeviceBleId;
        }

        public String getDpUrl() {
            return this.dpUrl;
        }

        public Object getEmailId() {
            return this.emailId;
        }

        public Object getGender() {
            return this.gender;
        }

        public int getId() {
            return this.id;
        }

        public int getIsCloveUser() {
            return this.isCloveUser;
        }

        public String getMobileNumber() {
            return this.mobileNumber;
        }

        public String getName() {
            return this.name;
        }

        public String getProfilePictureName() {
            return this.profilePictureName;
        }

        public String getSocialMediaDetails() {
            return this.socialMediaDetails;
        }

        public int getStatus() {
            return this.status;
        }

        public void setAppTrackerId(String str) {
            this.appTrackerId = str;
        }

        public void setAxTrackerId(String str) {
            this.axTrackerId = str;
        }

        public void setBirthDate(Object obj) {
            this.birthDate = obj;
        }

        public void setCloveDeviceBleId(String str) {
            this.cloveDeviceBleId = str;
        }

        public void setDpUrl(String str) {
            this.dpUrl = str;
        }

        public void setEmailId(Object obj) {
            this.emailId = obj;
        }

        public void setGender(Object obj) {
            this.gender = obj;
        }

        public void setId(int i) {
            this.id = i;
        }

        public void setIsCloveUser(int i) {
            this.isCloveUser = i;
        }

        public void setMobileNumber(String str) {
            this.mobileNumber = str;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setProfilePictureName(String str) {
            this.profilePictureName = str;
        }

        public void setSocialMediaDetails(String str) {
            this.socialMediaDetails = str;
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
