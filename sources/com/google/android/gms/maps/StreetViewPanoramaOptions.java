package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;
@SafeParcelable.Class(creator = "StreetViewPanoramaOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class StreetViewPanoramaOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzap();
    @Nullable
    @SafeParcelable.Field(getter = "getStreetViewPanoramaCamera", id = 2)
    public StreetViewPanoramaCamera h;
    @Nullable
    @SafeParcelable.Field(getter = "getPanoramaId", id = 3)
    public String i;
    @Nullable
    @SafeParcelable.Field(getter = "getPosition", id = 4)
    public LatLng j;
    @Nullable
    @SafeParcelable.Field(getter = "getRadius", id = 5)
    public Integer k;
    @Nullable
    @SafeParcelable.Field(getter = "getUserNavigationEnabledForParcel", id = 6, type = "byte")
    public Boolean l;
    @Nullable
    @SafeParcelable.Field(getter = "getZoomGesturesEnabledForParcel", id = 7, type = "byte")
    public Boolean m;
    @Nullable
    @SafeParcelable.Field(getter = "getPanningGesturesEnabledForParcel", id = 8, type = "byte")
    public Boolean n;
    @Nullable
    @SafeParcelable.Field(getter = "getStreetNamesEnabledForParcel", id = 9, type = "byte")
    public Boolean o;
    @Nullable
    @SafeParcelable.Field(getter = "getUseViewLifecycleInFragmentForParcel", id = 10, type = "byte")
    public Boolean p;
    @SafeParcelable.Field(getter = "getSource", id = 11)
    public StreetViewSource q;

    public StreetViewPanoramaOptions() {
        Boolean bool = Boolean.TRUE;
        this.l = bool;
        this.m = bool;
        this.n = bool;
        this.o = bool;
        this.q = StreetViewSource.DEFAULT;
    }

    @Nullable
    public Boolean getPanningGesturesEnabled() {
        return this.n;
    }

    @Nullable
    public String getPanoramaId() {
        return this.i;
    }

    @Nullable
    public LatLng getPosition() {
        return this.j;
    }

    @Nullable
    public Integer getRadius() {
        return this.k;
    }

    @NonNull
    public StreetViewSource getSource() {
        return this.q;
    }

    @Nullable
    public Boolean getStreetNamesEnabled() {
        return this.o;
    }

    @Nullable
    public StreetViewPanoramaCamera getStreetViewPanoramaCamera() {
        return this.h;
    }

    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        return this.p;
    }

    @Nullable
    public Boolean getUserNavigationEnabled() {
        return this.l;
    }

    @Nullable
    public Boolean getZoomGesturesEnabled() {
        return this.m;
    }

    @NonNull
    public StreetViewPanoramaOptions panningGesturesEnabled(boolean z) {
        this.n = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public StreetViewPanoramaOptions panoramaCamera(@Nullable StreetViewPanoramaCamera streetViewPanoramaCamera) {
        this.h = streetViewPanoramaCamera;
        return this;
    }

    @NonNull
    public StreetViewPanoramaOptions panoramaId(@Nullable String str) {
        this.i = str;
        return this;
    }

    @NonNull
    public StreetViewPanoramaOptions position(@Nullable LatLng latLng) {
        this.j = latLng;
        return this;
    }

    @NonNull
    public StreetViewPanoramaOptions position(@Nullable LatLng latLng, @NonNull StreetViewSource streetViewSource) {
        this.j = latLng;
        this.q = streetViewSource;
        return this;
    }

    @NonNull
    public StreetViewPanoramaOptions position(@Nullable LatLng latLng, @Nullable Integer num) {
        this.j = latLng;
        this.k = num;
        return this;
    }

    @NonNull
    public StreetViewPanoramaOptions position(@Nullable LatLng latLng, @Nullable Integer num, @NonNull StreetViewSource streetViewSource) {
        this.j = latLng;
        this.k = num;
        this.q = streetViewSource;
        return this;
    }

    @NonNull
    public StreetViewPanoramaOptions streetNamesEnabled(boolean z) {
        this.o = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("PanoramaId", this.i).add("Position", this.j).add("Radius", this.k).add("Source", this.q).add("StreetViewPanoramaCamera", this.h).add("UserNavigationEnabled", this.l).add("ZoomGesturesEnabled", this.m).add("PanningGesturesEnabled", this.n).add("StreetNamesEnabled", this.o).add("UseViewLifecycleInFragment", this.p).toString();
    }

    @NonNull
    public StreetViewPanoramaOptions useViewLifecycleInFragment(boolean z) {
        this.p = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public StreetViewPanoramaOptions userNavigationEnabled(boolean z) {
        this.l = Boolean.valueOf(z);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getStreetViewPanoramaCamera(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getPanoramaId(), false);
        SafeParcelWriter.writeParcelable(parcel, 4, getPosition(), i, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, getRadius(), false);
        SafeParcelWriter.writeByte(parcel, 6, zza.zza(this.l));
        SafeParcelWriter.writeByte(parcel, 7, zza.zza(this.m));
        SafeParcelWriter.writeByte(parcel, 8, zza.zza(this.n));
        SafeParcelWriter.writeByte(parcel, 9, zza.zza(this.o));
        SafeParcelWriter.writeByte(parcel, 10, zza.zza(this.p));
        SafeParcelWriter.writeParcelable(parcel, 11, getSource(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public StreetViewPanoramaOptions zoomGesturesEnabled(boolean z) {
        this.m = Boolean.valueOf(z);
        return this;
    }

    @SafeParcelable.Constructor
    public StreetViewPanoramaOptions(@Nullable @SafeParcelable.Param(id = 2) StreetViewPanoramaCamera streetViewPanoramaCamera, @Nullable @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) LatLng latLng, @Nullable @SafeParcelable.Param(id = 5) Integer num, @SafeParcelable.Param(id = 6) byte b, @SafeParcelable.Param(id = 7) byte b2, @SafeParcelable.Param(id = 8) byte b3, @SafeParcelable.Param(id = 9) byte b4, @SafeParcelable.Param(id = 10) byte b5, @SafeParcelable.Param(id = 11) StreetViewSource streetViewSource) {
        Boolean bool = Boolean.TRUE;
        this.l = bool;
        this.m = bool;
        this.n = bool;
        this.o = bool;
        this.q = StreetViewSource.DEFAULT;
        this.h = streetViewPanoramaCamera;
        this.j = latLng;
        this.k = num;
        this.i = str;
        this.l = zza.zzb(b);
        this.m = zza.zzb(b2);
        this.n = zza.zzb(b3);
        this.o = zza.zzb(b4);
        this.p = zza.zzb(b5);
        this.q = streetViewSource;
    }
}
