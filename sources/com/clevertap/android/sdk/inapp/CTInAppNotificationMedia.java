package com.clevertap.android.sdk.inapp;

import android.os.Parcel;
import android.os.Parcelable;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.Logger;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class CTInAppNotificationMedia implements Parcelable {
    public static final Parcelable.Creator<CTInAppNotificationMedia> CREATOR = new a();
    public int h;
    public String i;
    public String j;
    public String k;

    /* loaded from: classes2.dex */
    public class a implements Parcelable.Creator<CTInAppNotificationMedia> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CTInAppNotificationMedia createFromParcel(Parcel parcel) {
            return new CTInAppNotificationMedia(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CTInAppNotificationMedia[] newArray(int i) {
            return new CTInAppNotificationMedia[i];
        }
    }

    public /* synthetic */ CTInAppNotificationMedia(Parcel parcel, a aVar) {
        this(parcel);
    }

    public String a() {
        return this.i;
    }

    public String b() {
        return this.j;
    }

    public String c() {
        return this.k;
    }

    public CTInAppNotificationMedia d(JSONObject jSONObject, int i) {
        this.h = i;
        try {
            this.j = jSONObject.has("content_type") ? jSONObject.getString("content_type") : "";
            String string = jSONObject.has("url") ? jSONObject.getString("url") : "";
            if (!string.isEmpty()) {
                if (this.j.startsWith("image")) {
                    this.k = string;
                    if (jSONObject.has(Constants.KEY_KEY)) {
                        this.i = UUID.randomUUID().toString() + jSONObject.getString(Constants.KEY_KEY);
                    } else {
                        this.i = UUID.randomUUID().toString();
                    }
                } else {
                    this.k = string;
                }
            }
        } catch (JSONException e) {
            Logger.v("Error parsing Media JSONObject - " + e.getLocalizedMessage());
        }
        if (this.j.isEmpty()) {
            return null;
        }
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean e() {
        String b = b();
        return (b == null || this.k == null || !b.startsWith("audio")) ? false : true;
    }

    public boolean f() {
        String b = b();
        return (b == null || this.k == null || !b.equals("image/gif")) ? false : true;
    }

    public boolean g() {
        String b = b();
        return (b == null || this.k == null || !b.startsWith("image") || b.equals("image/gif")) ? false : true;
    }

    public int getOrientation() {
        return this.h;
    }

    public boolean h() {
        String b = b();
        return (b == null || this.k == null || !b.startsWith("video")) ? false : true;
    }

    public void i(String str) {
        this.k = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.k);
        parcel.writeString(this.j);
        parcel.writeString(this.i);
        parcel.writeInt(this.h);
    }

    public CTInAppNotificationMedia() {
    }

    public CTInAppNotificationMedia(Parcel parcel) {
        this.k = parcel.readString();
        this.j = parcel.readString();
        this.i = parcel.readString();
        this.h = parcel.readInt();
    }
}
