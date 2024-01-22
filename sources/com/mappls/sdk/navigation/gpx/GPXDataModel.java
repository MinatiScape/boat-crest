package com.mappls.sdk.navigation.gpx;

import android.os.Parcel;
import android.os.Parcelable;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
/* loaded from: classes11.dex */
public class GPXDataModel implements Parcelable {
    public static final Parcelable.Creator<GPXDataModel> CREATOR = new a();
    public String h;
    public long i;
    public ArrayList<LatLng> j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<GPXDataModel> {
        @Override // android.os.Parcelable.Creator
        public final GPXDataModel createFromParcel(Parcel parcel) {
            return new GPXDataModel(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final GPXDataModel[] newArray(int i) {
            return new GPXDataModel[i];
        }
    }

    public GPXDataModel() {
        this.h = null;
        this.i = 0L;
        this.j = new ArrayList<>();
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
    }

    public GPXDataModel(Parcel parcel) {
        this.h = null;
        this.i = 0L;
        this.j = new ArrayList<>();
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.h = parcel.readString();
        this.i = parcel.readLong();
        if (parcel.readByte() == 1) {
            ArrayList<LatLng> arrayList = new ArrayList<>();
            this.j = arrayList;
            parcel.readList(arrayList, LatLng.class.getClassLoader());
        } else {
            this.j = null;
        }
        this.k = parcel.readString();
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
        this.p = parcel.readString();
        this.q = parcel.readString();
        this.r = parcel.readString();
        this.s = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAltitudeRange() {
        return this.q;
    }

    public String getAverageAltitude() {
        return this.p;
    }

    public String getAverageSpeed() {
        return this.r;
    }

    public String getDistance() {
        return this.l;
    }

    public String getEndTime() {
        return this.n;
    }

    public String getIsSynced() {
        return this.k;
    }

    public String getMaxSpeed() {
        return this.s;
    }

    public String getStartTime() {
        return this.m;
    }

    public String getTimeMoving() {
        return this.o;
    }

    public long getTrackID() {
        return this.i;
    }

    public String getTrackName() {
        return this.h;
    }

    public ArrayList<LatLng> getTrackPath() {
        return this.j;
    }

    public void setAltitudeRange(String str) {
        this.q = str;
    }

    public void setAverageAltitude(String str) {
        this.p = str;
    }

    public void setAverageSpeed(String str) {
        this.r = str;
    }

    public void setDistance(String str) {
        this.l = str;
    }

    public void setEndTime(String str) {
        this.n = str;
    }

    public void setIsSynced(String str) {
        this.k = str;
    }

    public void setMaxSpeed(String str) {
        this.s = str;
    }

    public void setStartTime(String str) {
        this.m = str;
    }

    public void setTimeMoving(String str) {
        this.o = str;
    }

    public void setTrackID(long j) {
        this.i = j;
    }

    public void setTrackName(String str) {
        this.h = str;
    }

    public void setTrackPath(ArrayList<LatLng> arrayList) {
        this.j = arrayList;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.h);
        parcel.writeLong(this.i);
        if (this.j == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeList(this.j);
        }
        parcel.writeString(this.k);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
        parcel.writeString(this.p);
        parcel.writeString(this.q);
        parcel.writeString(this.r);
        parcel.writeString(this.s);
    }
}
