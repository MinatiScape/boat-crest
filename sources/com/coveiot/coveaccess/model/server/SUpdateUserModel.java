package com.coveiot.coveaccess.model.server;

import java.util.List;
/* loaded from: classes8.dex */
public class SUpdateUserModel {
    private DataBean data;
    private String message;
    private String status;

    /* loaded from: classes8.dex */
    public static class DataBean {
        private List<String> _unset;
        private String birthDate;
        private String cloveDeviceBleId;
        private boolean dpExist;
        private String dpUrl;
        private String emailId;
        private String gender;
        private int id;
        private int isCloveUser;
        private String mobileNumber;
        private String name;
        private List<?> organizationAccepted;
        private List<?> organizationBelongsTo;
        private String profilePictureName;
        private Object socialMediaDetails;
        private int status;

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

        public String getGender() {
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

        public List<String> get_unset() {
            return this._unset;
        }

        public boolean isDpExist() {
            return this.dpExist;
        }

        public void setBirthDate(String str) {
            this.birthDate = str;
        }

        public void setCloveDeviceBleId(String str) {
            this.cloveDeviceBleId = str;
        }

        public void setDpExist(boolean z) {
            this.dpExist = z;
        }

        public void setDpUrl(String str) {
            this.dpUrl = str;
        }

        public void setEmailId(String str) {
            this.emailId = str;
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

        public void set_unset(List<String> list) {
            this._unset = list;
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
