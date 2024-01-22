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
@SafeParcelable.Class(creator = "PolylineOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class PolylineOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<PolylineOptions> CREATOR = new zzm();
    @SafeParcelable.Field(getter = "getPoints", id = 2)
    public final List<LatLng> h;
    @SafeParcelable.Field(getter = "getWidth", id = 3)
    public float i;
    @SafeParcelable.Field(getter = "getColor", id = 4)
    public int j;
    @SafeParcelable.Field(getter = "getZIndex", id = 5)
    public float k;
    @SafeParcelable.Field(getter = "isVisible", id = 6)
    public boolean l;
    @SafeParcelable.Field(getter = "isGeodesic", id = 7)
    public boolean m;
    @SafeParcelable.Field(getter = "isClickable", id = 8)
    public boolean n;
    @SafeParcelable.Field(getter = "getStartCap", id = 9)
    public Cap o;
    @SafeParcelable.Field(getter = "getEndCap", id = 10)
    public Cap p;
    @SafeParcelable.Field(getter = "getJointType", id = 11)
    public int q;
    @Nullable
    @SafeParcelable.Field(getter = "getPattern", id = 12)
    public List<PatternItem> r;

    public PolylineOptions() {
        this.i = 10.0f;
        this.j = ViewCompat.MEASURED_STATE_MASK;
        this.k = 0.0f;
        this.l = true;
        this.m = false;
        this.n = false;
        this.o = new ButtCap();
        this.p = new ButtCap();
        this.q = 0;
        this.r = null;
        this.h = new ArrayList();
    }

    @NonNull
    public PolylineOptions add(@NonNull LatLng latLng) {
        Preconditions.checkNotNull(this.h, "point must not be null.");
        this.h.add(latLng);
        return this;
    }

    @NonNull
    public PolylineOptions addAll(@NonNull Iterable<LatLng> iterable) {
        Preconditions.checkNotNull(iterable, "points must not be null.");
        for (LatLng latLng : iterable) {
            this.h.add(latLng);
        }
        return this;
    }

    @NonNull
    public PolylineOptions clickable(boolean z) {
        this.n = z;
        return this;
    }

    @NonNull
    public PolylineOptions color(int i) {
        this.j = i;
        return this;
    }

    @NonNull
    public PolylineOptions endCap(@NonNull Cap cap) {
        this.p = (Cap) Preconditions.checkNotNull(cap, "endCap must not be null");
        return this;
    }

    @NonNull
    public PolylineOptions geodesic(boolean z) {
        this.m = z;
        return this;
    }

    public int getColor() {
        return this.j;
    }

    @NonNull
    public Cap getEndCap() {
        return this.p;
    }

    public int getJointType() {
        return this.q;
    }

    @Nullable
    public List<PatternItem> getPattern() {
        return this.r;
    }

    @NonNull
    public List<LatLng> getPoints() {
        return this.h;
    }

    @NonNull
    public Cap getStartCap() {
        return this.o;
    }

    public float getWidth() {
        return this.i;
    }

    public float getZIndex() {
        return this.k;
    }

    public boolean isClickable() {
        return this.n;
    }

    public boolean isGeodesic() {
        return this.m;
    }

    public boolean isVisible() {
        return this.l;
    }

    @NonNull
    public PolylineOptions jointType(int i) {
        this.q = i;
        return this;
    }

    @NonNull
    public PolylineOptions pattern(@Nullable List<PatternItem> list) {
        this.r = list;
        return this;
    }

    @NonNull
    public PolylineOptions startCap(@NonNull Cap cap) {
        this.o = (Cap) Preconditions.checkNotNull(cap, "startCap must not be null");
        return this;
    }

    @NonNull
    public PolylineOptions visible(boolean z) {
        this.l = z;
        return this;
    }

    @NonNull
    public PolylineOptions width(float f) {
        this.i = f;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, getPoints(), false);
        SafeParcelWriter.writeFloat(parcel, 3, getWidth());
        SafeParcelWriter.writeInt(parcel, 4, getColor());
        SafeParcelWriter.writeFloat(parcel, 5, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 6, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 7, isGeodesic());
        SafeParcelWriter.writeBoolean(parcel, 8, isClickable());
        SafeParcelWriter.writeParcelable(parcel, 9, getStartCap(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 10, getEndCap(), i, false);
        SafeParcelWriter.writeInt(parcel, 11, getJointType());
        SafeParcelWriter.writeTypedList(parcel, 12, getPattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public PolylineOptions zIndex(float f) {
        this.k = f;
        return this;
    }

    @NonNull
    public PolylineOptions add(@NonNull LatLng... latLngArr) {
        Preconditions.checkNotNull(latLngArr, "points must not be null.");
        this.h.addAll(Arrays.asList(latLngArr));
        return this;
    }

    @SafeParcelable.Constructor
    public PolylineOptions(@SafeParcelable.Param(id = 2) List list, @SafeParcelable.Param(id = 3) float f, @SafeParcelable.Param(id = 4) int i, @SafeParcelable.Param(id = 5) float f2, @SafeParcelable.Param(id = 6) boolean z, @SafeParcelable.Param(id = 7) boolean z2, @SafeParcelable.Param(id = 8) boolean z3, @Nullable @SafeParcelable.Param(id = 9) Cap cap, @Nullable @SafeParcelable.Param(id = 10) Cap cap2, @SafeParcelable.Param(id = 11) int i2, @Nullable @SafeParcelable.Param(id = 12) List<PatternItem> list2) {
        this.i = 10.0f;
        this.j = ViewCompat.MEASURED_STATE_MASK;
        this.k = 0.0f;
        this.l = true;
        this.m = false;
        this.n = false;
        this.o = new ButtCap();
        this.p = new ButtCap();
        this.h = list;
        this.i = f;
        this.j = i;
        this.k = f2;
        this.l = z;
        this.m = z2;
        this.n = z3;
        if (cap != null) {
            this.o = cap;
        }
        if (cap2 != null) {
            this.p = cap2;
        }
        this.q = i2;
        this.r = list2;
    }
}
