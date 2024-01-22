package com.mappls.sdk.navigation.ui.model;

import android.os.Parcel;
import android.os.Parcelable;
/* loaded from: classes11.dex */
public class a implements Parcelable {
    public final boolean h;
    public final boolean i;
    public final boolean j;
    public final boolean k;
    public final boolean l;

    public a(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.h = z;
        this.i = z2;
        this.j = z3;
        this.k = z4;
        this.l = z5;
    }

    public boolean a() {
        return this.l;
    }

    public boolean b() {
        return this.h;
    }

    public boolean c() {
        return this.i;
    }

    public boolean d() {
        return this.k;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean e() {
        return this.j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.k ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.l ? (byte) 1 : (byte) 0);
    }
}
