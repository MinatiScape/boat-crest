package com.mappls.sdk.maps.camera;

import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.FloatRange;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Size;
import com.mappls.sdk.maps.R;
import com.mappls.sdk.maps.camera.CameraUpdateFactory;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.utils.MathUtils;
import java.util.Arrays;
/* loaded from: classes11.dex */
public final class CameraPosition implements Parcelable {
    @Keep
    public final double bearing;
    @Keep
    public final double[] padding;
    @Keep
    public final LatLng target;
    @Keep
    public final double tilt;
    @Keep
    public final double zoom;
    public static final CameraPosition DEFAULT = new CameraPosition(new LatLng(28.0d, 77.0d), 4.0d, 0.0d, 0.0d, new double[]{0.0d, 0.0d, 0.0d, 0.0d});
    public static final Parcelable.Creator<CameraPosition> CREATOR = new a();

    /* loaded from: classes11.dex */
    public static final class Builder {

        /* renamed from: a  reason: collision with root package name */
        public double f12693a;
        @Nullable
        public LatLng b;
        public double c;
        public double d;
        public double[] e;

        public Builder() {
            this.f12693a = -1.0d;
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
            this.f12693a = d;
            return this;
        }

        public CameraPosition build() {
            return new CameraPosition(this.b, this.d, this.c, this.f12693a, this.e);
        }

        @NonNull
        public Builder padding(@Size(4) double[] dArr) {
            this.e = dArr;
            return this;
        }

        @NonNull
        public Builder target(LatLng latLng) {
            this.b = latLng;
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

        public Builder(@Nullable CameraPosition cameraPosition) {
            this.f12693a = -1.0d;
            this.b = null;
            this.c = -1.0d;
            this.d = -1.0d;
            this.e = null;
            if (cameraPosition != null) {
                this.f12693a = cameraPosition.bearing;
                this.b = cameraPosition.target;
                this.c = cameraPosition.tilt;
                this.d = cameraPosition.zoom;
                this.e = cameraPosition.padding;
            }
        }

        public Builder(@Nullable TypedArray typedArray) {
            this.f12693a = -1.0d;
            this.b = null;
            this.c = -1.0d;
            this.d = -1.0d;
            this.e = null;
            if (typedArray != null) {
                this.f12693a = typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraBearing, 0.0f);
                this.b = new LatLng(typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraTargetLat, 0.0f), typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraTargetLng, 0.0f));
                this.c = typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraTilt, 0.0f);
                this.d = typedArray.getFloat(R.styleable.mappls_maps_MapView_mappls_maps_cameraZoom, 0.0f);
            }
        }

        public Builder(@Nullable CameraUpdateFactory.b bVar) {
            this.f12693a = -1.0d;
            this.b = null;
            this.c = -1.0d;
            this.d = -1.0d;
            this.e = null;
            if (bVar != null) {
                this.f12693a = bVar.a();
                this.b = bVar.c();
                this.c = bVar.d();
                this.d = bVar.e();
                this.e = bVar.b();
            }
        }

        public Builder(@Nullable CameraUpdateFactory.c cVar) {
            this.f12693a = -1.0d;
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
    public class a implements Parcelable.Creator<CameraPosition> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public CameraPosition createFromParcel(Parcel parcel) {
            double[] dArr;
            double readDouble = parcel.readDouble();
            LatLng latLng = (LatLng) parcel.readParcelable(LatLng.class.getClassLoader());
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
            return new CameraPosition(latLng, readDouble3, readDouble2, readDouble, dArr);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public CameraPosition[] newArray(int i) {
            return new CameraPosition[i];
        }
    }

    @Keep
    public CameraPosition(LatLng latLng, double d, double d2, double d3, double[] dArr) {
        this.target = latLng;
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
        if (obj == null || CameraPosition.class != obj.getClass()) {
            return false;
        }
        CameraPosition cameraPosition = (CameraPosition) obj;
        LatLng latLng = this.target;
        return (latLng == null || latLng.equals(cameraPosition.target)) && this.zoom == cameraPosition.zoom && this.tilt == cameraPosition.tilt && this.bearing == cameraPosition.bearing && Arrays.equals(this.padding, cameraPosition.padding);
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.bearing);
        int i = ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) * 31;
        LatLng latLng = this.target;
        int hashCode = latLng != null ? latLng.hashCode() : 0;
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
        parcel.writeParcelable(this.target, i);
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
