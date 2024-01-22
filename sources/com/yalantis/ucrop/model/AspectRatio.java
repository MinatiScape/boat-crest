package com.yalantis.ucrop.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Nullable;
/* loaded from: classes12.dex */
public class AspectRatio implements Parcelable {
    public static final Parcelable.Creator<AspectRatio> CREATOR = new a();
    @Nullable
    public final String h;
    public final float i;
    public final float j;

    /* loaded from: classes12.dex */
    public static class a implements Parcelable.Creator<AspectRatio> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public AspectRatio createFromParcel(Parcel parcel) {
            return new AspectRatio(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public AspectRatio[] newArray(int i) {
            return new AspectRatio[i];
        }
    }

    public AspectRatio(@Nullable String str, float f, float f2) {
        this.h = str;
        this.i = f;
        this.j = f2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Nullable
    public String getAspectRatioTitle() {
        return this.h;
    }

    public float getAspectRatioX() {
        return this.i;
    }

    public float getAspectRatioY() {
        return this.j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.h);
        parcel.writeFloat(this.i);
        parcel.writeFloat(this.j);
    }

    public AspectRatio(Parcel parcel) {
        this.h = parcel.readString();
        this.i = parcel.readFloat();
        this.j = parcel.readFloat();
    }
}
