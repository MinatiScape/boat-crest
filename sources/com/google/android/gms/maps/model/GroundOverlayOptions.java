package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.dynamic.IObjectWrapper;
@SafeParcelable.Class(creator = "GroundOverlayOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class GroundOverlayOptions extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
    public static final float NO_DIMENSION = -1.0f;
    @SafeParcelable.Field(getter = "getWrappedImageDescriptorImplBinder", id = 2, type = "android.os.IBinder")
    public BitmapDescriptor h;
    @Nullable
    @SafeParcelable.Field(getter = "getLocation", id = 3)
    public LatLng i;
    @SafeParcelable.Field(getter = "getWidth", id = 4)
    public float j;
    @SafeParcelable.Field(getter = "getHeight", id = 5)
    public float k;
    @Nullable
    @SafeParcelable.Field(getter = "getBounds", id = 6)
    public LatLngBounds l;
    @SafeParcelable.Field(getter = "getBearing", id = 7)
    public float m;
    @SafeParcelable.Field(getter = "getZIndex", id = 8)
    public float n;
    @SafeParcelable.Field(getter = "isVisible", id = 9)
    public boolean o;
    @SafeParcelable.Field(getter = "getTransparency", id = 10)
    public float p;
    @SafeParcelable.Field(getter = "getAnchorU", id = 11)
    public float q;
    @SafeParcelable.Field(getter = "getAnchorV", id = 12)
    public float r;
    @SafeParcelable.Field(getter = "isClickable", id = 13)
    public boolean s;

    public GroundOverlayOptions() {
        this.o = true;
        this.p = 0.0f;
        this.q = 0.5f;
        this.r = 0.5f;
        this.s = false;
    }

    public final GroundOverlayOptions a(LatLng latLng, float f, float f2) {
        this.i = latLng;
        this.j = f;
        this.k = f2;
        return this;
    }

    @NonNull
    public GroundOverlayOptions anchor(float f, float f2) {
        this.q = f;
        this.r = f2;
        return this;
    }

    @NonNull
    public GroundOverlayOptions bearing(float f) {
        this.m = ((f % 360.0f) + 360.0f) % 360.0f;
        return this;
    }

    @NonNull
    public GroundOverlayOptions clickable(boolean z) {
        this.s = z;
        return this;
    }

    public float getAnchorU() {
        return this.q;
    }

    public float getAnchorV() {
        return this.r;
    }

    public float getBearing() {
        return this.m;
    }

    @Nullable
    public LatLngBounds getBounds() {
        return this.l;
    }

    public float getHeight() {
        return this.k;
    }

    @NonNull
    public BitmapDescriptor getImage() {
        return this.h;
    }

    @Nullable
    public LatLng getLocation() {
        return this.i;
    }

    public float getTransparency() {
        return this.p;
    }

    public float getWidth() {
        return this.j;
    }

    public float getZIndex() {
        return this.n;
    }

    @NonNull
    public GroundOverlayOptions image(@NonNull BitmapDescriptor bitmapDescriptor) {
        Preconditions.checkNotNull(bitmapDescriptor, "imageDescriptor must not be null");
        this.h = bitmapDescriptor;
        return this;
    }

    public boolean isClickable() {
        return this.s;
    }

    public boolean isVisible() {
        return this.o;
    }

    @NonNull
    public GroundOverlayOptions position(@NonNull LatLng latLng, float f) {
        Preconditions.checkState(this.l == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng != null, "Location must be specified");
        Preconditions.checkArgument(f >= 0.0f, "Width must be non-negative");
        a(latLng, f, -1.0f);
        return this;
    }

    @NonNull
    public GroundOverlayOptions positionFromBounds(@NonNull LatLngBounds latLngBounds) {
        LatLng latLng = this.i;
        Preconditions.checkState(latLng == null, "Position has already been set using position: ".concat(String.valueOf(latLng)));
        this.l = latLngBounds;
        return this;
    }

    @NonNull
    public GroundOverlayOptions transparency(float f) {
        boolean z = false;
        if (f >= 0.0f && f <= 1.0f) {
            z = true;
        }
        Preconditions.checkArgument(z, "Transparency must be in the range [0..1]");
        this.p = f;
        return this;
    }

    @NonNull
    public GroundOverlayOptions visible(boolean z) {
        this.o = z;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeIBinder(parcel, 2, this.h.zza().asBinder(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getLocation(), i, false);
        SafeParcelWriter.writeFloat(parcel, 4, getWidth());
        SafeParcelWriter.writeFloat(parcel, 5, getHeight());
        SafeParcelWriter.writeParcelable(parcel, 6, getBounds(), i, false);
        SafeParcelWriter.writeFloat(parcel, 7, getBearing());
        SafeParcelWriter.writeFloat(parcel, 8, getZIndex());
        SafeParcelWriter.writeBoolean(parcel, 9, isVisible());
        SafeParcelWriter.writeFloat(parcel, 10, getTransparency());
        SafeParcelWriter.writeFloat(parcel, 11, getAnchorU());
        SafeParcelWriter.writeFloat(parcel, 12, getAnchorV());
        SafeParcelWriter.writeBoolean(parcel, 13, isClickable());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public GroundOverlayOptions zIndex(float f) {
        this.n = f;
        return this;
    }

    @SafeParcelable.Constructor
    public GroundOverlayOptions(@SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) LatLng latLng, @SafeParcelable.Param(id = 4) float f, @SafeParcelable.Param(id = 5) float f2, @SafeParcelable.Param(id = 6) LatLngBounds latLngBounds, @SafeParcelable.Param(id = 7) float f3, @SafeParcelable.Param(id = 8) float f4, @SafeParcelable.Param(id = 9) boolean z, @SafeParcelable.Param(id = 10) float f5, @SafeParcelable.Param(id = 11) float f6, @SafeParcelable.Param(id = 12) float f7, @SafeParcelable.Param(id = 13) boolean z2) {
        this.o = true;
        this.p = 0.0f;
        this.q = 0.5f;
        this.r = 0.5f;
        this.s = false;
        this.h = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(iBinder));
        this.i = latLng;
        this.j = f;
        this.k = f2;
        this.l = latLngBounds;
        this.m = f3;
        this.n = f4;
        this.o = z;
        this.p = f5;
        this.q = f6;
        this.r = f7;
        this.s = z2;
    }

    @NonNull
    public GroundOverlayOptions position(@NonNull LatLng latLng, float f, float f2) {
        Preconditions.checkState(this.l == null, "Position has already been set using positionFromBounds");
        Preconditions.checkArgument(latLng != null, "Location must be specified");
        Preconditions.checkArgument(f >= 0.0f, "Width must be non-negative");
        Preconditions.checkArgument(f2 >= 0.0f, "Height must be non-negative");
        a(latLng, f, f2);
        return this;
    }
}
