package com.coveiot.utils.model;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.telephony.PhoneNumberUtils;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
/* loaded from: classes9.dex */
public class CoveContact implements Serializable {
    public String dpUrl;
    public boolean isSelected;
    public String mId;
    @SerializedName(AppMeasurementSdk.ConditionalUserProperty.NAME)
    @Expose
    public String mName;
    @SerializedName("mobileNumber")
    @Expose
    public String mPhoneNumber;
    public long mRunningContactId;
    public boolean isRequestSent = false;
    public boolean isRequestReceived = false;
    public boolean isFitCrew = false;

    public CoveContact(String str, String str2, String str3, long j) {
        this.mName = str2;
        this.mPhoneNumber = str3;
        this.mId = str;
        this.mRunningContactId = j;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof CoveContact) {
            CoveContact coveContact = (CoveContact) obj;
            String str = this.mName;
            return str != null && str.equals(coveContact.mName) && PhoneNumberUtils.compare(this.mPhoneNumber, coveContact.mPhoneNumber);
        }
        return false;
    }

    public String getDpUrl() {
        return this.dpUrl;
    }

    public String getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getPhoneNumber() {
        return this.mPhoneNumber;
    }

    public Uri getPhotoUri(Context context) {
        Uri withAppendedPath = Uri.withAppendedPath(ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, new Long(this.mId).longValue()), "photo");
        Cursor query = context.getContentResolver().query(withAppendedPath, new String[]{"data15"}, null, null, null);
        if (query != null && !query.moveToFirst()) {
            query.close();
            return null;
        }
        query.close();
        return withAppendedPath;
    }

    public long getRunningContactId() {
        return this.mRunningContactId;
    }

    public boolean isFitCrew() {
        return this.isFitCrew;
    }

    public boolean isRequestReceived() {
        return this.isRequestReceived;
    }

    public boolean isRequestSent() {
        return this.isRequestSent;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setDpUrl(String str) {
        this.dpUrl = str;
    }

    public void setFitCrew(boolean z) {
        this.isFitCrew = z;
    }

    public void setRequestReceived(boolean z) {
        this.isRequestReceived = z;
    }

    public void setRequestSent(boolean z) {
        this.isRequestSent = z;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }

    public String toString() {
        return "CloveContact{mRunningContactId=" + this.mRunningContactId + ", mName='" + this.mName + "', mPhoneNumber='" + this.mPhoneNumber + "', mId='" + this.mId + "'}";
    }
}
