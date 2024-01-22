package com.mappls.sdk.maps.offline;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.geojson.Feature;
import com.mappls.sdk.geojson.Geometry;
import com.mappls.sdk.maps.geometry.LatLngBounds;
import com.mappls.sdk.turf.TurfMeasurement;
/* loaded from: classes11.dex */
public class OfflineGeometryRegionDefinition implements OfflineRegionDefinition {
    public static final Parcelable.Creator CREATOR = new a();
    @Nullable
    @Keep
    private Geometry geometry;
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
        public OfflineGeometryRegionDefinition createFromParcel(@NonNull Parcel parcel) {
            return new OfflineGeometryRegionDefinition(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public OfflineGeometryRegionDefinition[] newArray(int i) {
            return new OfflineGeometryRegionDefinition[i];
        }
    }

    @Keep
    public OfflineGeometryRegionDefinition(String str, Geometry geometry, double d, double d2, float f) {
        this(str, geometry, d, d2, f, false);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.mappls.sdk.maps.offline.OfflineRegionDefinition
    @Nullable
    public LatLngBounds getBounds() {
        Geometry geometry = this.geometry;
        if (geometry == null) {
            return null;
        }
        double[] bbox = TurfMeasurement.bbox(geometry);
        return LatLngBounds.from(bbox[3], bbox[2], bbox[1], bbox[0]);
    }

    @Nullable
    public Geometry getGeometry() {
        return this.geometry;
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
        return "shaperegion";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.styleURL);
        parcel.writeString(Feature.fromGeometry(this.geometry).toJson());
        parcel.writeDouble(this.minZoom);
        parcel.writeDouble(this.maxZoom);
        parcel.writeFloat(this.pixelRatio);
        parcel.writeByte(this.includeIdeographs ? (byte) 1 : (byte) 0);
    }

    @Keep
    public OfflineGeometryRegionDefinition(String str, Geometry geometry, double d, double d2, float f, boolean z) {
        this.styleURL = str;
        this.geometry = geometry;
        this.minZoom = d;
        this.maxZoom = d2;
        this.pixelRatio = f;
        this.includeIdeographs = z;
    }

    public OfflineGeometryRegionDefinition(Parcel parcel) {
        this.styleURL = parcel.readString();
        this.geometry = Feature.fromJson(parcel.readString()).geometry();
        this.minZoom = parcel.readDouble();
        this.maxZoom = parcel.readDouble();
        this.pixelRatio = parcel.readFloat();
        this.includeIdeographs = parcel.readByte() != 0;
    }
}
