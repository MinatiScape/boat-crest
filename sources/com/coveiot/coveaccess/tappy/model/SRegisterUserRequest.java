package com.coveiot.coveaccess.tappy.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes8.dex */
public class SRegisterUserRequest implements Serializable {
    @SerializedName("EmailAddress")
    @Expose
    private String emailAddress = null;
    @SerializedName("Name")
    @Expose
    private String name = null;
    @SerializedName("FirstName")
    @Expose
    private String firstName = null;
    @SerializedName("MiddleName")
    @Expose
    private String middleName = null;
    @SerializedName("LastName")
    @Expose
    private String lastName = null;
    @SerializedName("PhoneMobile")
    @Expose
    private String phoneMobile = null;
    @SerializedName("Password")
    @Expose
    private String password = null;

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getMiddleName() {
        return this.middleName;
    }

    public String getName() {
        return this.name;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPhoneMobile() {
        return this.phoneMobile;
    }

    public void setEmailAddress(String str) {
        this.emailAddress = str;
    }

    public void setFirstName(String str) {
        this.firstName = str;
    }

    public void setLastName(String str) {
        this.lastName = str;
    }

    public void setMiddleName(String str) {
        this.middleName = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPassword(String str) {
        this.password = str;
    }

    public void setPhoneMobile(String str) {
        this.phoneMobile = str;
    }
}
