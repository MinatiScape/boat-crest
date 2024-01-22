package com.mappls.sdk.direction.ui.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import com.mappls.sdk.geojson.Point;
@Keep
/* loaded from: classes11.dex */
public class DirectionPoint implements Parcelable {
    public static final Parcelable.Creator<DirectionPoint> CREATOR = new a();
    private Double latitude;
    private Double longitude;
    private String mapplsPin;
    private String placeAddress;
    private String placeName;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<DirectionPoint> {
        @Override // android.os.Parcelable.Creator
        public final DirectionPoint createFromParcel(Parcel parcel) {
            return new DirectionPoint(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final DirectionPoint[] newArray(int i) {
            return new DirectionPoint[i];
        }
    }

    private DirectionPoint() {
    }

    public DirectionPoint(Parcel parcel) {
        this.placeName = parcel.readString();
        double readDouble = parcel.readDouble();
        this.latitude = readDouble == 0.0d ? null : Double.valueOf(readDouble);
        double readDouble2 = parcel.readDouble();
        this.longitude = readDouble2 != 0.0d ? Double.valueOf(readDouble2) : null;
        this.placeAddress = parcel.readString();
        this.mapplsPin = parcel.readString();
    }

    public static DirectionPoint setDirection(Point point, String str, String str2) {
        DirectionPoint directionPoint = new DirectionPoint();
        directionPoint.latitude = Double.valueOf(point.latitude());
        directionPoint.longitude = Double.valueOf(point.longitude());
        directionPoint.placeName = str;
        directionPoint.placeAddress = str2;
        return directionPoint;
    }

    public static DirectionPoint setDirection(String str, String str2, String str3) {
        DirectionPoint directionPoint = new DirectionPoint();
        directionPoint.placeName = str2;
        directionPoint.placeAddress = str3;
        directionPoint.mapplsPin = str;
        return directionPoint;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public String getMapplsPin() {
        return this.mapplsPin;
    }

    public String getPlaceAddress() {
        return this.placeAddress;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setLatitude(double d) {
        this.latitude = Double.valueOf(d);
    }

    public void setLongitude(double d) {
        this.longitude = Double.valueOf(d);
    }

    public void setMapplsPin(String str) {
        this.mapplsPin = str;
    }

    public void setPlaceAddress(String str) {
        this.placeAddress = str;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public String toString() {
        return "DirectionPoint{placeName='" + this.placeName + "', latitude=" + this.latitude + ", longitude=" + this.longitude + ", placeAddress='" + this.placeAddress + "', mapplsPin='" + this.mapplsPin + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.placeName);
        Double d = this.latitude;
        parcel.writeDouble(d == null ? 0.0d : d.doubleValue());
        Double d2 = this.longitude;
        parcel.writeDouble(d2 != null ? d2.doubleValue() : 0.0d);
        parcel.writeString(this.placeAddress);
        parcel.writeString(this.mapplsPin);
    }
}
