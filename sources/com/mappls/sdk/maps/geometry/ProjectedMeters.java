package com.mappls.sdk.maps.geometry;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
/* loaded from: classes11.dex */
public class ProjectedMeters implements Parcelable {
    public static final Parcelable.Creator<ProjectedMeters> CREATOR = new a();
    public double h;
    public double i;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<ProjectedMeters> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public ProjectedMeters createFromParcel(@NonNull Parcel parcel) {
            return new ProjectedMeters(parcel, (a) null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public ProjectedMeters[] newArray(int i) {
            return new ProjectedMeters[i];
        }
    }

    public /* synthetic */ ProjectedMeters(Parcel parcel, a aVar) {
        this(parcel);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ProjectedMeters projectedMeters = (ProjectedMeters) obj;
        return Double.compare(projectedMeters.i, this.i) == 0 && Double.compare(projectedMeters.h, this.h) == 0;
    }

    public double getEasting() {
        return this.i;
    }

    public double getNorthing() {
        return this.h;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.i);
        long doubleToLongBits2 = Double.doubleToLongBits(this.h);
        return (((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31) + ((int) ((doubleToLongBits2 >>> 32) ^ doubleToLongBits2));
    }

    @NonNull
    public String toString() {
        return "ProjectedMeters [northing=" + this.h + ", easting=" + this.i + "]";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeDouble(this.h);
        parcel.writeDouble(this.i);
    }

    @Keep
    public ProjectedMeters(double d, double d2) {
        this.h = d;
        this.i = d2;
    }

    public ProjectedMeters(ProjectedMeters projectedMeters) {
        this.h = projectedMeters.h;
        this.i = projectedMeters.i;
    }

    public ProjectedMeters(Parcel parcel) {
        this.h = parcel.readDouble();
        this.i = parcel.readDouble();
    }
}
