package com.coveiot.coveaccess.tappy.model;

import com.coveiot.utils.utility.ecg.EcgStyleReportUtil;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.jstyle.blesdk1860.constant.DeviceKey;
import java.io.Serializable;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public class SUserDataResponse implements Serializable {
    @SerializedName("AccountStatus")
    @Expose
    private String accountStatus;
    @SerializedName("AccountStatusReason")
    @Expose
    private String accountStatusReason;
    @SerializedName("Address")
    @Expose
    private Address address;
    @SerializedName("Age")
    @Expose
    private int age;
    @SerializedName("CreatedDate")
    @Expose
    private String createdDate;
    @SerializedName("Currency")
    @Expose
    private Currency currency;
    @SerializedName("CustomFieldAnswers")
    @Expose
    private ArrayList<CustomFieldAnswer> customFieldAnswers;
    @SerializedName("DateOfBirth")
    @Expose
    private String dateOfBirth;
    @SerializedName("EmailAddress")
    @Expose
    private String emailAddress;
    @SerializedName("EmailAddressChangeRequest")
    @Expose
    private String emailAddressChangeRequest;
    @SerializedName("FirstName")
    @Expose
    private String firstName;
    @SerializedName(EcgStyleReportUtil.UserInfoKey.GENDER)
    @Expose
    private String gender;
    @SerializedName("GlobalUserID")
    @Expose
    private long globalUserID;
    @SerializedName("IsDeleted")
    @Expose
    private boolean isDeleted;
    @SerializedName("LastName")
    @Expose
    private String lastName;
    @SerializedName("LastUpdatedDate")
    @Expose
    private String lastUpdatedDate;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("PhoneMobile")
    @Expose
    private String phoneMobile;
    @SerializedName("ProfileImageURL")
    @Expose
    private String profileImageURL;
    @SerializedName("TCUpdateNotified")
    @Expose
    private String tCUpdateNotified;
    @SerializedName(DeviceKey.TimeZone)
    @Expose
    private TimeZone timeZone;

    /* loaded from: classes8.dex */
    public class Address implements Serializable {
        @SerializedName("Address1")
        @Expose
        private String address1;
        @SerializedName("Address2")
        @Expose
        private String address2;
        @SerializedName("City")
        @Expose
        private String city;
        @SerializedName("Country")
        @Expose
        private String country;
        @SerializedName("PhoneNumber")
        @Expose
        private String phoneNumber;
        @SerializedName("PostalCode")
        @Expose
        private String postalCode;
        @SerializedName("State")
        @Expose
        private String state;

        public Address() {
        }

        public String getAddress1() {
            return this.address1;
        }

        public String getAddress2() {
            return this.address2;
        }

        public String getCity() {
            return this.city;
        }

        public String getCountry() {
            return this.country;
        }

        public String getPhoneNumber() {
            return this.phoneNumber;
        }

        public String getPostalCode() {
            return this.postalCode;
        }

        public String getState() {
            return this.state;
        }

        public void setAddress1(String str) {
            this.address1 = str;
        }

        public void setAddress2(String str) {
            this.address2 = str;
        }

        public void setCity(String str) {
            this.city = str;
        }

        public void setCountry(String str) {
            this.country = str;
        }

        public void setPhoneNumber(String str) {
            this.phoneNumber = str;
        }

        public void setPostalCode(String str) {
            this.postalCode = str;
        }

        public void setState(String str) {
            this.state = str;
        }
    }

    /* loaded from: classes8.dex */
    public static class Currency implements Serializable {
        @SerializedName("CurrencyID")
        @Expose
        private int currencyID;
        @SerializedName("Name")
        @Expose
        private String name;

        public int getCurrencyID() {
            return this.currencyID;
        }

        public String getName() {
            return this.name;
        }

        public void setCurrencyID(int i) {
            this.currencyID = i;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    /* loaded from: classes8.dex */
    public class CustomFieldAnswer implements Serializable {
        @SerializedName("CustomFieldID")
        @Expose
        private long customFieldID;
        @SerializedName("IsRequired")
        @Expose
        private boolean isRequired;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Page")
        @Expose
        private int page;
        @SerializedName("PossibleValues")
        @Expose
        private ArrayList<String> possibleValues;
        @SerializedName("SortOrder")
        @Expose
        private int sortOrder;
        @SerializedName("Type")
        @Expose
        private String type;
        @SerializedName("Value")
        @Expose
        private String value;

        public CustomFieldAnswer() {
        }

        public long getCustomFieldID() {
            return this.customFieldID;
        }

        public String getName() {
            return this.name;
        }

        public int getPage() {
            return this.page;
        }

        public ArrayList<String> getPossibleValues() {
            return this.possibleValues;
        }

        public int getSortOrder() {
            return this.sortOrder;
        }

        public String getType() {
            return this.type;
        }

        public String getValue() {
            return this.value;
        }

        public boolean isRequired() {
            return this.isRequired;
        }

        public void setCustomFieldID(long j) {
            this.customFieldID = j;
        }

        public void setName(String str) {
            this.name = str;
        }

        public void setPage(int i) {
            this.page = i;
        }

        public void setPossibleValues(ArrayList<String> arrayList) {
            this.possibleValues = arrayList;
        }

        public void setRequired(boolean z) {
            this.isRequired = z;
        }

        public void setSortOrder(int i) {
            this.sortOrder = i;
        }

        public void setType(String str) {
            this.type = str;
        }

        public void setValue(String str) {
            this.value = str;
        }
    }

    /* loaded from: classes8.dex */
    public class TimeZone implements Serializable {
        @SerializedName("ID")
        @Expose
        private String id;
        @SerializedName("Name")
        @Expose
        private String name;

        public TimeZone() {
        }

        public String getId() {
            return this.id;
        }

        public String getName() {
            return this.name;
        }

        public void setId(String str) {
            this.id = str;
        }

        public void setName(String str) {
            this.name = str;
        }
    }

    public String getAccountStatus() {
        return this.accountStatus;
    }

    public String getAccountStatusReason() {
        return this.accountStatusReason;
    }

    public Address getAddress() {
        return this.address;
    }

    public int getAge() {
        return this.age;
    }

    public String getCreatedDate() {
        return this.createdDate;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public ArrayList<CustomFieldAnswer> getCustomFieldAnswers() {
        return this.customFieldAnswers;
    }

    public String getDateOfBirth() {
        return this.dateOfBirth;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getEmailAddressChangeRequest() {
        return this.emailAddressChangeRequest;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getGender() {
        return this.gender;
    }

    public long getGlobalUserID() {
        return this.globalUserID;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getLastUpdatedDate() {
        return this.lastUpdatedDate;
    }

    public String getName() {
        return this.name;
    }

    public String getPhoneMobile() {
        return this.phoneMobile;
    }

    public String getProfileImageURL() {
        return this.profileImageURL;
    }

    public TimeZone getTimeZone() {
        return this.timeZone;
    }

    public String gettCUpdateNotified() {
        return this.tCUpdateNotified;
    }

    public boolean isDeleted() {
        return this.isDeleted;
    }

    public void setAccountStatus(String str) {
        this.accountStatus = str;
    }

    public void setAccountStatusReason(String str) {
        this.accountStatusReason = str;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setAge(int i) {
        this.age = i;
    }

    public void setCreatedDate(String str) {
        this.createdDate = str;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCustomFieldAnswers(ArrayList<CustomFieldAnswer> arrayList) {
        this.customFieldAnswers = arrayList;
    }

    public void setDateOfBirth(String str) {
        this.dateOfBirth = str;
    }

    public void setDeleted(boolean z) {
        this.isDeleted = z;
    }

    public void setEmailAddress(String str) {
        this.emailAddress = str;
    }

    public void setEmailAddressChangeRequest(String str) {
        this.emailAddressChangeRequest = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setGender(String str) {
        this.gender = str;
    }

    public void setGlobalUserID(long j) {
        this.globalUserID = j;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setLastUpdatedDate(String str) {
        this.lastUpdatedDate = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPhoneMobile(String str) {
        this.phoneMobile = str;
    }

    public void setProfileImageURL(String str) {
        this.profileImageURL = str;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void settCUpdateNotified(String str) {
        this.tCUpdateNotified = str;
    }
}
