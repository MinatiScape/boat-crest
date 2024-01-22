package com.mappls.sdk.maps.annotations;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.mappls.sdk.maps.geometry.LatLng;
import java.util.ArrayList;
import java.util.List;
@Deprecated
/* loaded from: classes11.dex */
public final class PolygonOptions implements Parcelable {
    public static final Parcelable.Creator<PolygonOptions> CREATOR = new a();
    public Polygon h;

    /* loaded from: classes11.dex */
    public class a implements Parcelable.Creator<PolygonOptions> {
        @Override // android.os.Parcelable.Creator
        /* renamed from: a */
        public PolygonOptions createFromParcel(@NonNull Parcel parcel) {
            return new PolygonOptions(parcel, null);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b */
        public PolygonOptions[] newArray(int i) {
            return new PolygonOptions[i];
        }
    }

    public /* synthetic */ PolygonOptions(Parcel parcel, a aVar) {
        this(parcel);
    }

    @NonNull
    public PolygonOptions add(LatLng latLng) {
        this.h.addPoint(latLng);
        return this;
    }

    @NonNull
    public PolygonOptions addAll(Iterable<LatLng> iterable) {
        for (LatLng latLng : iterable) {
            add(latLng);
        }
        return this;
    }

    @NonNull
    public PolygonOptions addAllHoles(Iterable<List<LatLng>> iterable) {
        for (List<LatLng> list : iterable) {
            addHole(list);
        }
        return this;
    }

    @NonNull
    public PolygonOptions addHole(List<LatLng> list) {
        this.h.b(list);
        return this;
    }

    @NonNull
    public PolygonOptions alpha(float f) {
        this.h.setAlpha(f);
        return this;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PolygonOptions.class != obj.getClass()) {
            return false;
        }
        PolygonOptions polygonOptions = (PolygonOptions) obj;
        if (Float.compare(polygonOptions.getAlpha(), getAlpha()) == 0 && getFillColor() == polygonOptions.getFillColor() && getStrokeColor() == polygonOptions.getStrokeColor()) {
            if (getPoints() == null ? polygonOptions.getPoints() == null : getPoints().equals(polygonOptions.getPoints())) {
                if (getHoles() != null) {
                    if (getHoles().equals(polygonOptions.getHoles())) {
                        return true;
                    }
                } else if (polygonOptions.getHoles() == null) {
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @NonNull
    public PolygonOptions fillColor(int i) {
        this.h.setFillColor(i);
        return this;
    }

    public float getAlpha() {
        return this.h.getAlpha();
    }

    public int getFillColor() {
        return this.h.getFillColor();
    }

    public List<List<LatLng>> getHoles() {
        return this.h.getHoles();
    }

    public List<LatLng> getPoints() {
        return this.h.getPoints();
    }

    public Polygon getPolygon() {
        return this.h;
    }

    public int getStrokeColor() {
        return this.h.getStrokeColor();
    }

    public int hashCode() {
        return (((((((((getAlpha() != 0.0f ? Float.floatToIntBits(getAlpha()) : 0) + 31) * 31) + getFillColor()) * 31) + getStrokeColor()) * 31) + (getPoints() != null ? getPoints().hashCode() : 0)) * 31) + (getHoles() != null ? getHoles().hashCode() : 0);
    }

    @NonNull
    public PolygonOptions strokeColor(int i) {
        this.h.setStrokeColor(i);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeList(getPoints());
        parcel.writeList(getHoles());
        parcel.writeFloat(getAlpha());
        parcel.writeInt(getFillColor());
        parcel.writeInt(getStrokeColor());
    }

    public PolygonOptions(Parcel parcel) {
        this.h = new Polygon();
        ArrayList arrayList = new ArrayList();
        parcel.readList(arrayList, LatLng.class.getClassLoader());
        addAll(arrayList);
        ArrayList arrayList2 = new ArrayList();
        parcel.readList(arrayList2, LatLng.class.getClassLoader());
        addAllHoles(arrayList2);
        alpha(parcel.readFloat());
        fillColor(parcel.readInt());
        strokeColor(parcel.readInt());
    }

    @NonNull
    public PolygonOptions add(LatLng... latLngArr) {
        for (LatLng latLng : latLngArr) {
            add(latLng);
        }
        return this;
    }

    @NonNull
    public PolygonOptions addHole(List<LatLng>... listArr) {
        for (List<LatLng> list : listArr) {
            addHole(list);
        }
        return this;
    }

    public PolygonOptions() {
        this.h = new Polygon();
    }
}
