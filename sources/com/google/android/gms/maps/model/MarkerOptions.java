package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
@SafeParcelable.Class(creator = "MarkerOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class MarkerOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzi();
    @SafeParcelable.Field(getter = "getPosition", id = 2)
    public LatLng h;
    @Nullable
    @SafeParcelable.Field(getter = "getTitle", id = 3)
    public String i;
    @Nullable
    @SafeParcelable.Field(getter = "getSnippet", id = 4)
    public String j;
    @Nullable
    @SafeParcelable.Field(getter = "getWrappedIconDescriptorImplBinder", id = 5, type = "android.os.IBinder")
    public BitmapDescriptor k;
    @SafeParcelable.Field(getter = "getAnchorU", id = 6)
    public float l;
    @SafeParcelable.Field(getter = "getAnchorV", id = 7)
    public float m;
    @SafeParcelable.Field(getter = "isDraggable", id = 8)
    public boolean n;
    @SafeParcelable.Field(getter = "isVisible", id = 9)
    public boolean o;
    @SafeParcelable.Field(getter = "isFlat", id = 10)
    public boolean p;
    @SafeParcelable.Field(getter = "getRotation", id = 11)
    public float q;
    @SafeParcelable.Field(defaultValue = "0.5f", getter = "getInfoWindowAnchorU", id = 12)
    public float r;
    @SafeParcelable.Field(getter = "getInfoWindowAnchorV", id = 13)
    public float s;
    @SafeParcelable.Field(defaultValue = "1.0f", getter = "getAlpha", id = 14)
    public float t;
    @SafeParcelable.Field(getter = "getZIndex", id = 15)
    public float u;

    public MarkerOptions() {
        this.l = 0.5f;
        this.m = 1.0f;
        this.o = true;
        this.p = false;
        this.q = 0.0f;
        this.r = 0.5f;
        this.s = 0.0f;
        this.t = 1.0f;
    }

    @NonNull
    public MarkerOptions alpha(float f) {
        this.t = f;
        return this;
    }

    @NonNull
    public MarkerOptions anchor(float f, float f2) {
        this.l = f;
        this.m = f2;
        return this;
    }

    @NonNull
    public MarkerOptions draggable(boolean z) {
        this.n = z;
        return this;
    }

    @NonNull
    public MarkerOptions flat(boolean z) {
        this.p = z;
        return this;
    }

    public float getAlpha() {
        return this.t;
    }

    public float getAnchorU() {
        return this.l;
    }

    public float getAnchorV() {
        return this.m;
    }

    @Nullable
    public BitmapDescriptor getIcon() {
        return this.k;
    }

    public float getInfoWindowAnchorU() {
        return this.r;
    }

    public float getInfoWindowAnchorV() {
        return this.s;
    }

    @NonNull
    public LatLng getPosition() {
        return this.h;
    }

    public float getRotation() {
        return this.q;
    }

    @Nullable
    public String getSnippet() {
        return this.j;
    }

    @Nullable
    public String getTitle() {
        return this.i;
    }

    public float getZIndex() {
        return this.u;
    }

    @NonNull
    public MarkerOptions icon(@Nullable BitmapDescriptor bitmapDescriptor) {
        this.k = bitmapDescriptor;
        return this;
    }

    @NonNull
    public MarkerOptions infoWindowAnchor(float f, float f2) {
        this.r = f;
        this.s = f2;
        return this;
    }

    public boolean isDraggable() {
        return this.n;
    }

    public boolean isFlat() {
        return this.p;
    }

    public boolean isVisible() {
        return this.o;
    }

    @NonNull
    public MarkerOptions position(@NonNull LatLng latLng) {
        if (latLng != null) {
            this.h = latLng;
            return this;
        }
        throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }

    @NonNull
    public MarkerOptions rotation(float f) {
        this.q = f;
        return this;
    }

    @NonNull
    public MarkerOptions snippet(@Nullable String str) {
        this.j = str;
        return this;
    }

    @NonNull
    public MarkerOptions title(@Nullable String str) {
        this.i = str;
        return this;
    }

    @NonNull
    public MarkerOptions visible(boolean z) {
        this.o = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getPosition(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeString(parcel, 4, getSnippet(), false);
        BitmapDescriptor bitmapDescriptor = this.k;
        SafeParcelWriter.writeIBinder(parcel, 5, bitmapDescriptor == null ? null : bitmapDescriptor.zza().asBinder(), false);
        SafeParcelWriter.writeFloat(parcel, 6, getAnchorU());
        SafeParcelWriter.writeFloat(parcel, 7, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel, 8, isDraggable());
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.writeBoolean(parcel, 10, isFlat());
        SafeParcelWriter.writeFloat(parcel, 11, getRotation());
        SafeParcelWriter.writeFloat(parcel, 12, getInfoWindowAnchorU());
        SafeParcelWriter.writeFloat(parcel, 13, getInfoWindowAnchorV());
        SafeParcelWriter.writeFloat(parcel, 14, getAlpha());
        SafeParcelWriter.writeFloat(parcel, 15, getZIndex());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public MarkerOptions zIndex(float f) {
        this.u = f;
        return this;
    }

    @SafeParcelable.Constructor
    public MarkerOptions(@SafeParcelable.Param(id = 2) LatLng latLng, @SafeParcelable.Param(id = 3) String str, @SafeParcelable.Param(id = 4) String str2, @Nullable @SafeParcelable.Param(id = 5) IBinder iBinder, @SafeParcelable.Param(id = 6) float f, @SafeParcelable.Param(id = 7) float f2, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) boolean z2, @SafeParcelable.Param(id = 10) boolean z3, @SafeParcelable.Param(id = 11) float f3, @SafeParcelable.Param(id = 12) float f4, @SafeParcelable.Param(id = 13) float f5, @SafeParcelable.Param(id = 14) float f6, @SafeParcelable.Param(id = 15) float f7) {
        this.l = 0.5f;
        this.m = 1.0f;
        this.o = true;
        this.p = false;
        this.q = 0.0f;
        this.r = 0.5f;
        this.s = 0.0f;
        this.t = 1.0f;
        this.h = latLng;
        this.i = str;
        this.j = str2;
        if (iBinder == null) {
            this.k = null;
        } else {
            this.k = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        }
        this.l = f;
        this.m = f2;
        this.n = z;
        this.o = z2;
        this.p = z3;
        this.q = f3;
        this.r = f4;
        this.s = f5;
        this.t = f6;
        this.u = f7;
    }
}
