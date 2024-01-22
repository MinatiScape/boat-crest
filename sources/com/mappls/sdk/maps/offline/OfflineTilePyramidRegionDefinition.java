package com.mappls.sdk.maps.offline;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.mappls.sdk.maps.geometry.LatLng;
import com.mappls.sdk.maps.geometry.LatLngBounds;
/* loaded from: classes11.dex */
public class OfflineTilePyramidRegionDefinition implements OfflineRegionDefinition {
    public static final Parcelable.Creator CREATOR = new a();
    @Keep
    private LatLngBounds bounds;
    @Keep
    private boolean includeIdeographs;
    @Keep
    private double maxZoom;
    @Keep
    private double minZoom;
    @Keep
    private float pixelRatio;
    @Keep
    private String styleURL;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public OfflineTilePyramidRegionDefinition createFromParcel(@NonNull Parcel parcel) {
            return new OfflineTilePyramidRegionDefinition(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public OfflineTilePyramidRegionDefinition[] newArray(int i) {
            return new OfflineTilePyramidRegionDefinition[i];
        }
    }

    @Keep
    public OfflineTilePyramidRegionDefinition(String str, LatLngBounds latLngBounds, double d, double d2, float f) {
        this(str, latLngBounds, d, d2, f, false);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.mappls.sdk.maps.offline.OfflineRegionDefinition
    public LatLngBounds getBounds() {
        return this.bounds;
    }

    @Override // com.mappls.sdk.maps.offline.OfflineRegionDefinition
    public boolean getIncludeIdeographs() {
        return this.includeIdeographs;
    }

    @Override // com.mappls.sdk.maps.offline.OfflineRegionDefinition
    public double getMaxZoom() {
        return this.maxZoom;
    }

    @Override // com.mappls.sdk.maps.offline.OfflineRegionDefinition
    public double getMinZoom() {
        return this.minZoom;
    }

    @Override // com.mappls.sdk.maps.offline.OfflineRegionDefinition
    public float getPixelRatio() {
        return this.pixelRatio;
    }

    @Override // com.mappls.sdk.maps.offline.OfflineRegionDefinition
    public String getStyleURL() {
        return this.styleURL;
    }

    @Override // com.mappls.sdk.maps.offline.OfflineRegionDefinition
    @NonNull
    public String getType() {
        return "tileregion";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.styleURL);
        parcel.writeDouble(this.bounds.getLatNorth());
        parcel.writeDouble(this.bounds.getLonEast());
        parcel.writeDouble(this.bounds.getLatSouth());
        parcel.writeDouble(this.bounds.getLonWest());
        parcel.writeDouble(this.minZoom);
        parcel.writeDouble(this.maxZoom);
        parcel.writeFloat(this.pixelRatio);
        parcel.writeByte(this.includeIdeographs ? (byte) 1 : (byte) 0);
    }

    @Keep
    public OfflineTilePyramidRegionDefinition(String str, LatLngBounds latLngBounds, double d, double d2, float f, boolean z) {
        this.styleURL = str;
        this.bounds = latLngBounds;
        this.minZoom = d;
        this.maxZoom = d2;
        this.pixelRatio = f;
        this.includeIdeographs = z;
    }

    public OfflineTilePyramidRegionDefinition(Parcel parcel) {
        this.styleURL = parcel.readString();
        this.bounds = new LatLngBounds.Builder().include(new LatLng(parcel.readDouble(), parcel.readDouble())).include(new LatLng(parcel.readDouble(), parcel.readDouble())).build();
        this.minZoom = parcel.readDouble();
        this.maxZoom = parcel.readDouble();
        this.pixelRatio = parcel.readFloat();
        this.includeIdeographs = parcel.readByte() != 0;
    }
}
