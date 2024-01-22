package com.sifli.siflidfu;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
/* loaded from: classes12.dex */
public class DFUImagePath implements Parcelable {
    public static final Parcelable.Creator<DFUImagePath> CREATOR = new a();
    public String h;
    public Uri i;
    public int j;

    /* loaded from: classes12.dex */
    public class a implements Parcelable.Creator<DFUImagePath> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public DFUImagePath createFromParcel(Parcel parcel) {
            return new DFUImagePath(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public DFUImagePath[] newArray(int i) {
            return new DFUImagePath[i];
        }
    }

    public DFUImagePath(String str, Uri uri, int i) {
        this.h = str;
        this.i = uri;
        this.j = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getImagePath() {
        return this.h;
    }

    public int getImageType() {
        return this.j;
    }

    public Uri getImageUri() {
        return this.i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.h);
        parcel.writeParcelable(this.i, i);
        parcel.writeInt(this.j);
    }

    public DFUImagePath(Parcel parcel) {
        this.h = parcel.readString();
        this.i = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.j = parcel.readInt();
    }
}
