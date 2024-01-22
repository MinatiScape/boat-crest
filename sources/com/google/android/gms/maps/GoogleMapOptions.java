package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
@SafeParcelable.Class(creator = "GoogleMapOptionsCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    @NonNull
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzab();
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZOrderOnTopForParcel", id = 2, type = "byte")
    public Boolean h;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getUseViewLifecycleInFragmentForParcel", id = 3, type = "byte")
    public Boolean i;
    @SafeParcelable.Field(getter = "getMapType", id = 4)
    public int j;
    @Nullable
    @SafeParcelable.Field(getter = "getCamera", id = 5)
    public CameraPosition k;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZoomControlsEnabledForParcel", id = 6, type = "byte")
    public Boolean l;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getCompassEnabledForParcel", id = 7, type = "byte")
    public Boolean m;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getScrollGesturesEnabledForParcel", id = 8, type = "byte")
    public Boolean n;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getZoomGesturesEnabledForParcel", id = 9, type = "byte")
    public Boolean o;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getTiltGesturesEnabledForParcel", id = 10, type = "byte")
    public Boolean p;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getRotateGesturesEnabledForParcel", id = 11, type = "byte")
    public Boolean q;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLiteModeForParcel", id = 12, type = "byte")
    public Boolean r;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getMapToolbarEnabledForParcel", id = 14, type = "byte")
    public Boolean s;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getAmbientEnabledForParcel", id = 15, type = "byte")
    public Boolean t;
    @Nullable
    @SafeParcelable.Field(getter = "getMinZoomPreference", id = 16)
    public Float u;
    @Nullable
    @SafeParcelable.Field(getter = "getMaxZoomPreference", id = 17)
    public Float v;
    @Nullable
    @SafeParcelable.Field(getter = "getLatLngBoundsForCameraTarget", id = 18)
    public LatLngBounds w;
    @Nullable
    @SafeParcelable.Field(defaultValue = "-1", getter = "getScrollGesturesEnabledDuringRotateOrZoomForParcel", id = 19, type = "byte")
    public Boolean x;
    @Nullable
    @ColorInt
    @SafeParcelable.Field(getter = "getBackgroundColor", id = 20)
    public Integer y;
    @Nullable
    @SafeParcelable.Field(getter = "getMapId", id = 21)
    public String z;

    public GoogleMapOptions() {
        this.j = -1;
        this.u = null;
        this.v = null;
        this.w = null;
        this.y = null;
        this.z = null;
    }

    public static int a(Context context, String str) {
        return context.getResources().getIdentifier(str, "attr", context.getPackageName());
    }

    @Nullable
    public static GoogleMapOptions createFromAttributes(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        String string;
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        int i = R.styleable.MapAttrs_mapType;
        if (obtainAttributes.hasValue(i)) {
            googleMapOptions.mapType(obtainAttributes.getInt(i, -1));
        }
        int i2 = R.styleable.MapAttrs_zOrderOnTop;
        if (obtainAttributes.hasValue(i2)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(i2, false));
        }
        int i3 = R.styleable.MapAttrs_useViewLifecycle;
        if (obtainAttributes.hasValue(i3)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(i3, false));
        }
        int i4 = R.styleable.MapAttrs_uiCompass;
        if (obtainAttributes.hasValue(i4)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(i4, true));
        }
        int i5 = R.styleable.MapAttrs_uiRotateGestures;
        if (obtainAttributes.hasValue(i5)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(i5, true));
        }
        int i6 = R.styleable.MapAttrs_uiScrollGesturesDuringRotateOrZoom;
        if (obtainAttributes.hasValue(i6)) {
            googleMapOptions.scrollGesturesEnabledDuringRotateOrZoom(obtainAttributes.getBoolean(i6, true));
        }
        int i7 = R.styleable.MapAttrs_uiScrollGestures;
        if (obtainAttributes.hasValue(i7)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(i7, true));
        }
        int i8 = R.styleable.MapAttrs_uiTiltGestures;
        if (obtainAttributes.hasValue(i8)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(i8, true));
        }
        int i9 = R.styleable.MapAttrs_uiZoomGestures;
        if (obtainAttributes.hasValue(i9)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(i9, true));
        }
        int i10 = R.styleable.MapAttrs_uiZoomControls;
        if (obtainAttributes.hasValue(i10)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(i10, true));
        }
        int i11 = R.styleable.MapAttrs_liteMode;
        if (obtainAttributes.hasValue(i11)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(i11, false));
        }
        int i12 = R.styleable.MapAttrs_uiMapToolbar;
        if (obtainAttributes.hasValue(i12)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(i12, true));
        }
        int i13 = R.styleable.MapAttrs_ambientEnabled;
        if (obtainAttributes.hasValue(i13)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(i13, false));
        }
        int i14 = R.styleable.MapAttrs_cameraMinZoomPreference;
        if (obtainAttributes.hasValue(i14)) {
            googleMapOptions.minZoomPreference(obtainAttributes.getFloat(i14, Float.NEGATIVE_INFINITY));
        }
        if (obtainAttributes.hasValue(i14)) {
            googleMapOptions.maxZoomPreference(obtainAttributes.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
        }
        TypedArray obtainAttributes2 = context.getResources().obtainAttributes(attributeSet, new int[]{a(context, "backgroundColor"), a(context, "mapId")});
        if (obtainAttributes2.hasValue(0)) {
            googleMapOptions.backgroundColor(Integer.valueOf(obtainAttributes2.getColor(0, 0)));
        }
        if (obtainAttributes2.hasValue(1) && (string = obtainAttributes2.getString(1)) != null && !string.isEmpty()) {
            googleMapOptions.mapId(string);
        }
        obtainAttributes2.recycle();
        googleMapOptions.latLngBoundsForCameraTarget(zzb(context, attributeSet));
        googleMapOptions.camera(zza(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    @Nullable
    public static CameraPosition zza(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        int i = R.styleable.MapAttrs_cameraTargetLat;
        float f = obtainAttributes.hasValue(i) ? obtainAttributes.getFloat(i, 0.0f) : 0.0f;
        int i2 = R.styleable.MapAttrs_cameraTargetLng;
        LatLng latLng = new LatLng(f, obtainAttributes.hasValue(i2) ? obtainAttributes.getFloat(i2, 0.0f) : 0.0f);
        CameraPosition.Builder builder = CameraPosition.builder();
        builder.target(latLng);
        int i3 = R.styleable.MapAttrs_cameraZoom;
        if (obtainAttributes.hasValue(i3)) {
            builder.zoom(obtainAttributes.getFloat(i3, 0.0f));
        }
        int i4 = R.styleable.MapAttrs_cameraBearing;
        if (obtainAttributes.hasValue(i4)) {
            builder.bearing(obtainAttributes.getFloat(i4, 0.0f));
        }
        int i5 = R.styleable.MapAttrs_cameraTilt;
        if (obtainAttributes.hasValue(i5)) {
            builder.tilt(obtainAttributes.getFloat(i5, 0.0f));
        }
        obtainAttributes.recycle();
        return builder.build();
    }

    @Nullable
    public static LatLngBounds zzb(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        if (context == null || attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        int i = R.styleable.MapAttrs_latLngBoundsSouthWestLatitude;
        Float valueOf = obtainAttributes.hasValue(i) ? Float.valueOf(obtainAttributes.getFloat(i, 0.0f)) : null;
        int i2 = R.styleable.MapAttrs_latLngBoundsSouthWestLongitude;
        Float valueOf2 = obtainAttributes.hasValue(i2) ? Float.valueOf(obtainAttributes.getFloat(i2, 0.0f)) : null;
        int i3 = R.styleable.MapAttrs_latLngBoundsNorthEastLatitude;
        Float valueOf3 = obtainAttributes.hasValue(i3) ? Float.valueOf(obtainAttributes.getFloat(i3, 0.0f)) : null;
        int i4 = R.styleable.MapAttrs_latLngBoundsNorthEastLongitude;
        Float valueOf4 = obtainAttributes.hasValue(i4) ? Float.valueOf(obtainAttributes.getFloat(i4, 0.0f)) : null;
        obtainAttributes.recycle();
        if (valueOf == null || valueOf2 == null || valueOf3 == null || valueOf4 == null) {
            return null;
        }
        return new LatLngBounds(new LatLng(valueOf.floatValue(), valueOf2.floatValue()), new LatLng(valueOf3.floatValue(), valueOf4.floatValue()));
    }

    @NonNull
    public GoogleMapOptions ambientEnabled(boolean z) {
        this.t = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions backgroundColor(@Nullable @ColorInt Integer num) {
        this.y = num;
        return this;
    }

    @NonNull
    public GoogleMapOptions camera(@Nullable CameraPosition cameraPosition) {
        this.k = cameraPosition;
        return this;
    }

    @NonNull
    public GoogleMapOptions compassEnabled(boolean z) {
        this.m = Boolean.valueOf(z);
        return this;
    }

    @Nullable
    public Boolean getAmbientEnabled() {
        return this.t;
    }

    @Nullable
    @ColorInt
    public Integer getBackgroundColor() {
        return this.y;
    }

    @Nullable
    public CameraPosition getCamera() {
        return this.k;
    }

    @Nullable
    public Boolean getCompassEnabled() {
        return this.m;
    }

    @Nullable
    public LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.w;
    }

    @Nullable
    public Boolean getLiteMode() {
        return this.r;
    }

    @Nullable
    public String getMapId() {
        return this.z;
    }

    @Nullable
    public Boolean getMapToolbarEnabled() {
        return this.s;
    }

    public int getMapType() {
        return this.j;
    }

    @Nullable
    public Float getMaxZoomPreference() {
        return this.v;
    }

    @Nullable
    public Float getMinZoomPreference() {
        return this.u;
    }

    @Nullable
    public Boolean getRotateGesturesEnabled() {
        return this.q;
    }

    @Nullable
    public Boolean getScrollGesturesEnabled() {
        return this.n;
    }

    @Nullable
    public Boolean getScrollGesturesEnabledDuringRotateOrZoom() {
        return this.x;
    }

    @Nullable
    public Boolean getTiltGesturesEnabled() {
        return this.p;
    }

    @Nullable
    public Boolean getUseViewLifecycleInFragment() {
        return this.i;
    }

    @Nullable
    public Boolean getZOrderOnTop() {
        return this.h;
    }

    @Nullable
    public Boolean getZoomControlsEnabled() {
        return this.l;
    }

    @Nullable
    public Boolean getZoomGesturesEnabled() {
        return this.o;
    }

    @NonNull
    public GoogleMapOptions latLngBoundsForCameraTarget(@Nullable LatLngBounds latLngBounds) {
        this.w = latLngBounds;
        return this;
    }

    @NonNull
    public GoogleMapOptions liteMode(boolean z) {
        this.r = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions mapId(@NonNull String str) {
        this.z = str;
        return this;
    }

    @NonNull
    public GoogleMapOptions mapToolbarEnabled(boolean z) {
        this.s = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions mapType(int i) {
        this.j = i;
        return this;
    }

    @NonNull
    public GoogleMapOptions maxZoomPreference(float f) {
        this.v = Float.valueOf(f);
        return this;
    }

    @NonNull
    public GoogleMapOptions minZoomPreference(float f) {
        this.u = Float.valueOf(f);
        return this;
    }

    @NonNull
    public GoogleMapOptions rotateGesturesEnabled(boolean z) {
        this.q = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions scrollGesturesEnabled(boolean z) {
        this.n = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions scrollGesturesEnabledDuringRotateOrZoom(boolean z) {
        this.x = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions tiltGesturesEnabled(boolean z) {
        this.p = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.j)).add("LiteMode", this.r).add("Camera", this.k).add("CompassEnabled", this.m).add("ZoomControlsEnabled", this.l).add("ScrollGesturesEnabled", this.n).add("ZoomGesturesEnabled", this.o).add("TiltGesturesEnabled", this.p).add("RotateGesturesEnabled", this.q).add("ScrollGesturesEnabledDuringRotateOrZoom", this.x).add("MapToolbarEnabled", this.s).add("AmbientEnabled", this.t).add("MinZoomPreference", this.u).add("MaxZoomPreference", this.v).add("BackgroundColor", this.y).add("LatLngBoundsForCameraTarget", this.w).add("ZOrderOnTop", this.h).add("UseViewLifecycleInFragment", this.i).toString();
    }

    @NonNull
    public GoogleMapOptions useViewLifecycleInFragment(boolean z) {
        this.i = Boolean.valueOf(z);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeByte(parcel, 2, zza.zza(this.h));
        SafeParcelWriter.writeByte(parcel, 3, zza.zza(this.i));
        SafeParcelWriter.writeInt(parcel, 4, getMapType());
        SafeParcelWriter.writeParcelable(parcel, 5, getCamera(), i, false);
        SafeParcelWriter.writeByte(parcel, 6, zza.zza(this.l));
        SafeParcelWriter.writeByte(parcel, 7, zza.zza(this.m));
        SafeParcelWriter.writeByte(parcel, 8, zza.zza(this.n));
        SafeParcelWriter.writeByte(parcel, 9, zza.zza(this.o));
        SafeParcelWriter.writeByte(parcel, 10, zza.zza(this.p));
        SafeParcelWriter.writeByte(parcel, 11, zza.zza(this.q));
        SafeParcelWriter.writeByte(parcel, 12, zza.zza(this.r));
        SafeParcelWriter.writeByte(parcel, 14, zza.zza(this.s));
        SafeParcelWriter.writeByte(parcel, 15, zza.zza(this.t));
        SafeParcelWriter.writeFloatObject(parcel, 16, getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(parcel, 17, getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(parcel, 18, getLatLngBoundsForCameraTarget(), i, false);
        SafeParcelWriter.writeByte(parcel, 19, zza.zza(this.x));
        SafeParcelWriter.writeIntegerObject(parcel, 20, getBackgroundColor(), false);
        SafeParcelWriter.writeString(parcel, 21, getMapId(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @NonNull
    public GoogleMapOptions zOrderOnTop(boolean z) {
        this.h = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions zoomControlsEnabled(boolean z) {
        this.l = Boolean.valueOf(z);
        return this;
    }

    @NonNull
    public GoogleMapOptions zoomGesturesEnabled(boolean z) {
        this.o = Boolean.valueOf(z);
        return this;
    }

    @SafeParcelable.Constructor
    public GoogleMapOptions(@SafeParcelable.Param(id = 2) byte b, @SafeParcelable.Param(id = 3) byte b2, @SafeParcelable.Param(id = 4) int i, @Nullable @SafeParcelable.Param(id = 5) CameraPosition cameraPosition, @SafeParcelable.Param(id = 6) byte b3, @SafeParcelable.Param(id = 7) byte b4, @SafeParcelable.Param(id = 8) byte b5, @SafeParcelable.Param(id = 9) byte b6, @SafeParcelable.Param(id = 10) byte b7, @SafeParcelable.Param(id = 11) byte b8, @SafeParcelable.Param(id = 12) byte b9, @SafeParcelable.Param(id = 14) byte b10, @SafeParcelable.Param(id = 15) byte b11, @Nullable @SafeParcelable.Param(id = 16) Float f, @Nullable @SafeParcelable.Param(id = 17) Float f2, @Nullable @SafeParcelable.Param(id = 18) LatLngBounds latLngBounds, @SafeParcelable.Param(id = 19) byte b12, @Nullable @SafeParcelable.Param(id = 20) @ColorInt Integer num, @Nullable @SafeParcelable.Param(id = 21) String str) {
        this.j = -1;
        this.u = null;
        this.v = null;
        this.w = null;
        this.y = null;
        this.z = null;
        this.h = zza.zzb(b);
        this.i = zza.zzb(b2);
        this.j = i;
        this.k = cameraPosition;
        this.l = zza.zzb(b3);
        this.m = zza.zzb(b4);
        this.n = zza.zzb(b5);
        this.o = zza.zzb(b6);
        this.p = zza.zzb(b7);
        this.q = zza.zzb(b8);
        this.r = zza.zzb(b9);
        this.s = zza.zzb(b10);
        this.t = zza.zzb(b11);
        this.u = f;
        this.v = f2;
        this.w = latLngBounds;
        this.x = zza.zzb(b12);
        this.y = num;
        this.z = str;
    }
}
