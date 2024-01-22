package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@SafeParcelable.Class(creator = "PolygonOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class PolygonOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PolygonOptions> CREATOR = new zzl();
    @SafeParcelable.Field(getter = "getPoints", id = 2)
    public final List<LatLng> h;
    @SafeParcelable.Field(getter = "getHolesForParcel", id = 3, type = "java.util.List")
    public final List<List<LatLng>> i;
    @SafeParcelable.Field(getter = "getStrokeWidth", id = 4)
    public float j;
    @SafeParcelable.Field(getter = "getStrokeColor", id = 5)
    public int k;
    @SafeParcelable.Field(getter = "getFillColor", id = 6)
    public int l;
    @SafeParcelable.Field(getter = "getZIndex", id = 7)
    public float m;
    @SafeParcelable.Field(getter = "isVisible", id = 8)
    public boolean n;
    @SafeParcelable.Field(getter = "isGeodesic", id = 9)
    public boolean o;
    @SafeParcelable.Field(getter = "isClickable", id = 10)
    public boolean p;
    @SafeParcelable.Field(getter = "getStrokeJointType", id = 11)
    public int q;
    @Nullable
    @SafeParcelable.Field(getter = "getStrokePattern", id = 12)
    public List<PatternItem> r;

    public PolygonOptions() {
        this.j = 10.0f;
        this.k = ViewCompat.MEASURED_STATE_MASK;
        this.l = 0;
        this.m = 0.0f;
        this.n = true;
        this.o = false;
        this.p = false;
        this.q = 0;
        this.r = null;
        this.h = new ArrayList();
        this.i = new ArrayList();
    }

    @NonNull
    public PolygonOptions add(@NonNull LatLng latLng) {
        Preconditions.checkNotNull(latLng, "point must not be null.");
        this.h.add(latLng);
        return this;
    }

    @NonNull
    public PolygonOptions addAll(@NonNull Iterable<LatLng> iterable) {
        Preconditions.checkNotNull(iterable, "points must not be null.");
        for (LatLng latLng : iterable) {
            this.h.add(latLng);
        }
        return this;
    }

    @NonNull
    public PolygonOptions addHole(@NonNull Iterable<LatLng> iterable) {
        Preconditions.checkNotNull(iterable, "points must not be null.");
        ArrayList arrayList = new ArrayList();
        for (LatLng latLng : iterable) {
            arrayList.add(latLng);
        }
        this.i.add(arrayList);
        return this;
    }

    @NonNull
    public PolygonOptions clickable(boolean z) {
        this.p = z;
        return this;
    }

    @NonNull
    public PolygonOptions fillColor(int i) {
        this.l = i;
        return this;
    }

    @NonNull
    public PolygonOptions geodesic(boolean z) {
        this.o = z;
        return this;
    }

    public int getFillColor() {
        return this.l;
    }

    @NonNull
    public List<List<LatLng>> getHoles() {
        return this.i;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return this.h;
    }

    public int getStrokeColor() {
        return this.k;
    }

    public int getStrokeJointType() {
        return this.q;
    }

    @Nullable
    public List<PatternItem> getStrokePattern() {
        return this.r;
    }

    public float getStrokeWidth() {
        return this.j;
    }

    public float getZIndex() {
        return this.m;
    }

    public boolean isClickable() {
        return this.p;
    }

    public boolean isGeodesic() {
        return this.o;
    }

    public boolean isVisible() {
        return this.n;
    }

    @NonNull
    public PolygonOptions strokeColor(int i) {
        this.k = i;
        return this;
    }

    @NonNull
    public PolygonOptions strokeJointType(int i) {
        this.q = i;
        return this;
    }

    @NonNull
    public PolygonOptions strokePattern(@Nullable List<PatternItem> list) {
        this.r = list;
        return this;
    }

    @NonNull
    public PolygonOptions strokeWidth(float f) {
        this.j = f;
        return this;
    }

    @NonNull
    public PolygonOptions visible(boolean z) {
        this.n = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeList(parcel, 3, this.i, false);
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 10, isClickable());
        SafeParcelWriter.writeInt(parcel, 11, getStrokeJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public PolygonOptions zIndex(float f) {
        this.m = f;
        return this;
    }

    @NonNull
    public PolygonOptions add(@NonNull LatLng... latLngArr) {
        Preconditions.checkNotNull(latLngArr, "points must not be null.");
        this.h.addAll(Arrays.asList(latLngArr));
        return this;
    }

    @SafeParcelable.Constructor
    public PolygonOptions(@SafeParcelable.Param(id = 2) List<LatLng> list, @SafeParcelable.Param(id = 3) List list2, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) int i, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) float f2, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) boolean z3, @SafeParcelable.Param(id = 11) int i3, @Nullable @SafeParcelable.Param(id = 12) List<PatternItem> list3) {
        this.h = list;
        this.i = list2;
        this.j = f;
        this.k = i;
        this.l = i2;
        this.m = f2;
        this.n = z;
        this.o = z2;
        this.p = z3;
        this.q = i3;
        this.r = list3;
    }
}
