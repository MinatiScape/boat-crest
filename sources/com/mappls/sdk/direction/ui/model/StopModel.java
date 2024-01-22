package com.mappls.sdk.direction.ui.model;

import androidx.annotation.Keep;
import com.mappls.sdk.geojson.Point;
@Keep
/* loaded from: classes11.dex */
public class StopModel {
    public static int TYPE_BLANK = 3;
    public static int TYPE_CURRENT_LOCATION = 0;
    public static int TYPE_STOP = 1;
    private int locationType;
    private String mapplsPin;
    private String placeAddress;
    private String placeName;
    private double latitude = 0.0d;
    private double longitude = 0.0d;
    private double entryLatitude = 0.0d;
    private double entryLongitude = 0.0d;

    public Point getEntryLocation() {
        double d = this.entryLatitude;
        if (d != 0.0d) {
            double d2 = this.entryLongitude;
            if (d2 == 0.0d) {
                return null;
            }
            return Point.fromLngLat(d2, d);
        }
        return null;
    }

    public Point getLocation() {
        double d = this.latitude;
        if (d != 0.0d) {
            double d2 = this.longitude;
            if (d2 == 0.0d) {
                return null;
            }
            return Point.fromLngLat(d2, d);
        }
        return null;
    }

    public int getLocationType() {
        return this.locationType;
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

    public void setEntryLocation(Point point) {
        double d;
        if (point != null) {
            this.entryLatitude = point.latitude();
            d = point.longitude();
        } else {
            d = 0.0d;
            this.entryLatitude = 0.0d;
        }
        this.entryLongitude = d;
    }

    public void setLocation(Point point) {
        double d;
        if (point != null) {
            this.latitude = point.latitude();
            d = point.longitude();
        } else {
            d = 0.0d;
            this.latitude = 0.0d;
        }
        this.longitude = d;
    }

    public void setLocationType(int i) {
        this.locationType = i;
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
}
