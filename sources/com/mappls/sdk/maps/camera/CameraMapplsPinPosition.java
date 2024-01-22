package com.mappls.sdk.maps.camera;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import com.mappls.sdk.maps.camera.CameraMapplsPinUpdateFactory;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.utils.MathUtils;
import java.util.Arrays;
/* loaded from: classes11.dex */
public class CameraMapplsPinPosition implements Parcelable {
    public static final Parcelable.Creator<CameraMapplsPinPosition> CREATOR = new a();
    @Keep
    public final double bearing;
    @Keep
    public final double[] padding;
    @Keep
    public final String target;
    @Keep
    public final double tilt;
    @Keep
    public final double zoom;

    /* loaded from: classes11.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public double f12689a;
        @Nullable
        public String b;
        public double c;
        public double d;
        public double[] e;

        public Builder() {
            this.f12689a = -1.0d;
            this.b = null;
            this.c = -1.0d;
            this.d = -1.0d;
            this.e = null;
        }

        @NonNull
        public Builder bearing(double d) {
            while (d >= 360.0d) {
                d -= 360.0d;
            }
            while (d < 0.0d) {
                d += 360.0d;
            }
            this.f12689a = d;
            return this;
        }

        public CameraMapplsPinPosition build() {
            return new CameraMapplsPinPosition(this.b, this.d, this.c, this.f12689a, this.e);
        }

        @NonNull
        public Builder padding(@Size(4) double[] dArr) {
            this.e = dArr;
            return this;
        }

        @NonNull
        public Builder target(String str) {
            this.b = str;
            return this;
        }

        @NonNull
        public Builder tilt(@FloatRange(from = 0.0d, to = 60.0d) double d) {
            this.c = MathUtils.clamp(d, 0.0d, 60.0d);
            return this;
        }

        @NonNull
        public Builder zoom(@FloatRange(from = 1.0d, to = 22.0d) double d) {
            this.d = d;
            return this;
        }

        @NonNull
        public Builder padding(double d, double d2, double d3, double d4) {
            this.e = new double[]{d, d2, d3, d4};
            return this;
        }

        public Builder(@Nullable CameraMapplsPinPosition cameraMapplsPinPosition) {
            this.f12689a = -1.0d;
            this.b = null;
            this.c = -1.0d;
            this.d = -1.0d;
            this.e = null;
            if (cameraMapplsPinPosition != null) {
                this.f12689a = cameraMapplsPinPosition.bearing;
                this.b = cameraMapplsPinPosition.target;
                this.c = cameraMapplsPinPosition.tilt;
                this.d = cameraMapplsPinPosition.zoom;
                this.e = cameraMapplsPinPosition.padding;
            }
        }

        public Builder(@Nullable CameraMapplsPinUpdateFactory.b bVar) {
            this.f12689a = -1.0d;
            this.b = null;
            this.c = -1.0d;
            this.d = -1.0d;
            this.e = null;
            if (bVar != null) {
                this.f12689a = bVar.a();
                this.b = bVar.c();
                this.c = bVar.d();
                this.d = bVar.e();
                this.e = bVar.b();
            }
        }

        public Builder(@Nullable CameraUpdateFactory.c cVar) {
            this.f12689a = -1.0d;
            this.b = null;
            this.c = -1.0d;
            this.d = -1.0d;
            this.e = null;
            if (cVar != null) {
                this.d = cVar.d();
            }
        }
    }

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<CameraMapplsPinPosition> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CameraMapplsPinPosition createFromParcel(Parcel parcel) {
            double[] dArr;
            double readDouble = parcel.readDouble();
            String readString = parcel.readString();
            double readDouble2 = parcel.readDouble();
            double readDouble3 = parcel.readDouble();
            int readInt = parcel.readInt();
            if (readInt > 0) {
                dArr = new double[readInt];
                for (int i = 0; i < readInt; i++) {
                    dArr[i] = parcel.readDouble();
                }
            } else {
                dArr = null;
            }
            return new CameraMapplsPinPosition(readString, readDouble3, readDouble2, readDouble, dArr);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CameraMapplsPinPosition[] newArray(int i) {
            return new CameraMapplsPinPosition[i];
        }
    }

    @Keep
    public CameraMapplsPinPosition(String str, double d, double d2, double d3, double[] dArr) {
        this.target = str;
        this.bearing = d3;
        this.tilt = d2;
        this.zoom = d;
        this.padding = dArr;
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
        CameraMapplsPinPosition cameraMapplsPinPosition = (CameraMapplsPinPosition) obj;
        String str = this.target;
        return (str == null || str.equals(cameraMapplsPinPosition.target)) && this.zoom == cameraMapplsPinPosition.zoom && this.tilt == cameraMapplsPinPosition.tilt && this.bearing == cameraMapplsPinPosition.bearing && Arrays.equals(this.padding, cameraMapplsPinPosition.padding);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.bearing);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31;
        String str = this.target;
        int hashCode = str != null ? str.hashCode() : 0;
        long doubleToLongBits2 = Double.doubleToLongBits(this.tilt);
        long doubleToLongBits3 = Double.doubleToLongBits(this.zoom);
        return ((((((i + hashCode) * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)))) * 31) + ((int) ((doubleToLongBits3 >>> 32) ^ doubleToLongBits3))) * 31) + Arrays.hashCode(this.padding);
    }

    public String toString() {
        return "Target: " + this.target + ", Zoom:" + this.zoom + ", Bearing:" + this.bearing + ", Tilt:" + this.tilt + ", Padding:" + Arrays.toString(this.padding);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.bearing);
        parcel.writeString(this.target);
        parcel.writeDouble(this.tilt);
        parcel.writeDouble(this.zoom);
        double[] dArr = this.padding;
        if (dArr != null) {
            parcel.writeInt(dArr.length);
            for (double d : this.padding) {
                parcel.writeDouble(d);
            }
            return;
        }
        parcel.writeInt(-1);
    }
}
