package com.coveiot.coveaccess.onboarding.model;

import com.coveiot.coveaccess.model.CoveApiResponseBaseModel;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
/* loaded from: classes8.dex */
public final class UserByPhoneNumberRes extends CoveApiResponseBaseModel {
    @SerializedName("appTrackerId")
    private String appTrackerId;
    @SerializedName("axTrackerId")
    private String axTrackerId;
    @SerializedName("birthDate")
    private String birthDate;
    @SerializedName("dpExist")
    private boolean dpExist;
    @SerializedName("displayPicUrl")
    private String dpUrl;
    @SerializedName("emailId")
    private String emailId;
    @SerializedName("emailVerified")
    private boolean emailVerified;
    @SerializedName("firstName")
    private String firstName;
    @SerializedName("gender")
    private String gender;
    @SerializedName("id")
    private int id;
    @SerializedName("isExistingUser")
    private boolean isExistingUser;
    @SerializedName("lastName")
    private String lastName;
    @SerializedName("mobileNumber")
    private String mobileNumber;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    private String name;
    @SerializedName("organizationAccepted")
    private ArrayList<AngelNetworkAcceptedModel> organizationAccepted;
    @SerializedName("organizationBelongsTo")
    private ArrayList<AngelNetworkBelongsToModel> organizationBelongsTo;
    @SerializedName("socialMediaId")
    private String socialMediaId;

    /* loaded from: classes8.dex */
    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        public int f6669a;
        public boolean b;
        public int c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
        public ArrayList<AngelNetworkAcceptedModel> m;
        public ArrayList<AngelNetworkBelongsToModel> n;
        public boolean o;
        public boolean p;
        public String q;

        public UserByPhoneNumberRes build() {
            return new UserByPhoneNumberRes(this.f6669a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k, this.l, this.n, this.m, this.o, this.p, this.q);
        }

        public Builder setAppTrackerId(String str) {
            return this;
        }

        public Builder setAxTrackerId(String str) {
            this.q = str;
            return this;
        }

        public Builder setBirthDate(String str) {
            this.j = str;
            return this;
        }

        public Builder setCode(int i) {
            this.f6669a = i;
            return this;
        }

        public Builder setDpExist(boolean z) {
            this.o = z;
            return this;
        }

        public Builder setDpUrl(String str) {
            this.k = str;
            return this;
        }

        public Builder setEmailId(String str) {
            this.i = str;
            return this;
        }

        public Builder setEmailVerified(boolean z) {
            this.p = z;
            return this;
        }

        public Builder setExistingUser(boolean z) {
            this.b = z;
            return this;
        }

        public Builder setFirstName(String str) {
            this.e = str;
            return this;
        }

        public Builder setGender(String str) {
            this.g = str;
            return this;
        }

        public Builder setId(int i) {
            this.c = i;
            return this;
        }

        public Builder setLastName(String str) {
            this.f = str;
            return this;
        }

        public Builder setMobileNumber(String str) {
            this.h = str;
            return this;
        }

        public Builder setName(String str) {
            this.d = str;
            return this;
        }

        public Builder setOrganizationAccepted(ArrayList<AngelNetworkAcceptedModel> arrayList) {
            this.m = arrayList;
            return this;
        }

        public Builder setOrganizationBelongsToModel(ArrayList<AngelNetworkBelongsToModel> arrayList) {
            this.n = arrayList;
            return this;
        }

        public Builder setSocialMediaId(String str) {
            this.l = str;
            return this;
        }
    }

    public String getAppTrackerId() {
        return this.appTrackerId;
    }

    public String getAxTrackerId() {
        return this.axTrackerId;
    }

    public String getBirthDate() {
        return this.birthDate;
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

    public String getLastName() {
        return this.lastName;
    }

    public String getMobileNumber() {
        return this.mobileNumber;
    }

    public String getName() {
        return this.name;
    }

    public ArrayList<AngelNetworkAcceptedModel> getOrganizationAccepted() {
        return this.organizationAccepted;
    }

    public ArrayList<AngelNetworkBelongsToModel> getOrganizationBelongsTo() {
        return this.organizationBelongsTo;
    }

    public boolean isDpExist() {
        return this.dpExist;
    }

    public boolean isEmailVerified() {
        return this.emailVerified;
    }

    public boolean isExistingUser() {
        return this.isExistingUser;
    }

    private UserByPhoneNumberRes(int i, boolean z, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, boolean z2, boolean z3) {
        super(i);
        this.isExistingUser = z;
        this.id = i2;
        this.name = str;
        this.gender = str2;
        this.mobileNumber = str3;
        this.emailId = str4;
        this.birthDate = str5;
        this.dpUrl = str6;
        this.socialMediaId = str7;
        this.dpExist = z2;
        this.appTrackerId = this.appTrackerId;
        this.emailVerified = z3;
    }

    private UserByPhoneNumberRes(int i, boolean z, int i2, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, ArrayList<AngelNetworkBelongsToModel> arrayList, ArrayList<AngelNetworkAcceptedModel> arrayList2, boolean z2, boolean z3, String str10) {
        super(i);
        this.isExistingUser = z;
        this.id = i2;
        this.name = str;
        this.firstName = str2;
        this.lastName = str3;
        this.gender = str4;
        this.mobileNumber = str5;
        this.emailId = str6;
        this.birthDate = str7;
        this.dpUrl = str8;
        this.socialMediaId = str9;
        this.organizationAccepted = arrayList2;
        this.organizationBelongsTo = arrayList;
        this.dpExist = z2;
        this.emailVerified = z3;
        this.appTrackerId = this.appTrackerId;
        this.axTrackerId = str10;
    }
}
