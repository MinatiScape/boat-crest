package com.mappls.sdk.maps.util;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
/* loaded from: classes11.dex */
public class DefaultStyle implements Parcelable {
    public static final Parcelable.Creator<DefaultStyle> CREATOR = new a();
    @Keep
    private String name;
    @Keep
    private String url;
    @Keep
    private int version;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<DefaultStyle> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public DefaultStyle createFromParcel(@NonNull Parcel parcel) {
            return new DefaultStyle(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public DefaultStyle[] newArray(int i) {
            return new DefaultStyle[i];
        }
    }

    @Keep
    public DefaultStyle(String str, String str2, int i) {
        setUrl(str);
        setName(str2);
        setVersion(i);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public String getUrl() {
        return this.url;
    }

    public int getVersion() {
        return this.version;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.url);
        parcel.writeString(this.name);
        parcel.writeInt(this.version);
    }

    public DefaultStyle(Parcel parcel) {
        setUrl(parcel.readString());
        setName(parcel.readString());
        setVersion(parcel.readInt());
    }
}
