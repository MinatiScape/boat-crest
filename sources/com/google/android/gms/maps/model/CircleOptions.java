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
import java.util.List;
@SafeParcelable.Class(creator = "CircleOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class CircleOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<CircleOptions> CREATOR = new zzc();
    @Nullable
    @SafeParcelable.Field(getter = "getCenter", id = 2)
    public LatLng h;
    @SafeParcelable.Field(getter = "getRadius", id = 3)
    public double i;
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
    @SafeParcelable.Field(getter = "isClickable", id = 9)
    public boolean o;
    @Nullable
    @SafeParcelable.Field(getter = "getStrokePattern", id = 10)
    public List<PatternItem> p;

    public CircleOptions() {
        this.h = null;
        this.i = 0.0d;
        this.j = 10.0f;
        this.k = ViewCompat.MEASURED_STATE_MASK;
        this.l = 0;
        this.m = 0.0f;
        this.n = true;
        this.o = false;
        this.p = null;
    }

    @NonNull
    public CircleOptions center(@NonNull LatLng latLng) {
        Preconditions.checkNotNull(latLng, "center must not be null.");
        this.h = latLng;
        return this;
    }

    @NonNull
    public CircleOptions clickable(boolean z) {
        this.o = z;
        return this;
    }

    @NonNull
    public CircleOptions fillColor(int i) {
        this.l = i;
        return this;
    }

    @Nullable
    public LatLng getCenter() {
        return this.h;
    }

    public int getFillColor() {
        return this.l;
    }

    public double getRadius() {
        return this.i;
    }

    public int getStrokeColor() {
        return this.k;
    }

    @Nullable
    public List<PatternItem> getStrokePattern() {
        return this.p;
    }

    public float getStrokeWidth() {
        return this.j;
    }

    public float getZIndex() {
        return this.m;
    }

    public boolean isClickable() {
        return this.o;
    }

    public boolean isVisible() {
        return this.n;
    }

    @NonNull
    public CircleOptions radius(double d) {
        this.i = d;
        return this;
    }

    @NonNull
    public CircleOptions strokeColor(int i) {
        this.k = i;
        return this;
    }

    @NonNull
    public CircleOptions strokePattern(@Nullable List<PatternItem> list) {
        this.p = list;
        return this;
    }

    @NonNull
    public CircleOptions strokeWidth(float f) {
        this.j = f;
        return this;
    }

    @NonNull
    public CircleOptions visible(boolean z) {
        this.n = z;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getCenter(), i, false);
        SafeParcelWriter.writeDouble(parcel, 3, getRadius());
        SafeParcelWriter.writeFloat(parcel, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(parcel, 5, getStrokeColor());
        SafeParcelWriter.writeInt(parcel, 6, getFillColor());
        SafeParcelWriter.writeFloat(parcel, 7, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 8, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 9, isClickable());
        SafeParcelWriter.writeTypedList(parcel, 10, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public CircleOptions zIndex(float f) {
        this.m = f;
        return this;
    }

    @SafeParcelable.Constructor
    public CircleOptions(@SafeParcelable.Param(id = 2) LatLng latLng, @SafeParcelable.Param(id = 3) double d, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) int i, @SafeParcelable.Param(id = 6) int i2, @SafeParcelable.Param(id = 7) float f2, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) boolean z2, @Nullable @SafeParcelable.Param(id = 10) List<PatternItem> list) {
        this.h = latLng;
        this.i = d;
        this.j = f;
        this.k = i;
        this.l = i2;
        this.m = f2;
        this.n = z;
        this.o = z2;
        this.p = list;
    }
}
