package com.google.android.gms.fitness.data;

import android.content.Context;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
@SafeParcelable.Class(creator = "DeviceCreator")
@SafeParcelable.Reserved({3, 1000})
/* loaded from: classes6.dex */
public final class Device extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<Device> CREATOR = new zzo();
    public static final int TYPE_CHEST_STRAP = 4;
    public static final int TYPE_HEAD_MOUNTED = 6;
    public static final int TYPE_PHONE = 1;
    public static final int TYPE_SCALE = 5;
    public static final int TYPE_TABLET = 2;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WATCH = 3;
    @SafeParcelable.Field(getter = "getManufacturer", id = 1)
    public final String h;
    @SafeParcelable.Field(getter = "getModel", id = 2)
    public final String i;
    @SafeParcelable.Field(getter = "getUid", id = 4)
    public final String j;
    @SafeParcelable.Field(getter = "getType", id = 5)
    public final int k;
    @SafeParcelable.Field(getter = "getPlatformType", id = 6)
    public final int l;

    public Device(@NonNull String str, @NonNull String str2, @NonNull String str3, int i) {
        this(str, str2, str3, i, 0);
    }

    @NonNull
    public static Device getLocalDevice(@NonNull Context context) {
        int zzb = zzq.zzb(context);
        return new Device(Build.MANUFACTURER, Build.MODEL, zzq.zza(context), zzb, 2);
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Device) {
            Device device = (Device) obj;
            return Objects.equal(this.h, device.h) && Objects.equal(this.i, device.i) && Objects.equal(this.j, device.j) && this.k == device.k && this.l == device.l;
        }
        return false;
    }

    @NonNull
    public final String getManufacturer() {
        return this.h;
    }

    @NonNull
    public final String getModel() {
        return this.i;
    }

    public final String getStreamIdentifier() {
        return String.format("%s:%s:%s", this.h, this.i, this.j);
    }

    public final int getType() {
        return this.k;
    }

    @NonNull
    public final String getUid() {
        return this.j;
    }

    public final int hashCode() {
        return Objects.hashCode(this.h, this.i, this.j, Integer.valueOf(this.k));
    }

    @NonNull
    public final String toString() {
        return String.format("Device{%s:%s:%s}", getStreamIdentifier(), Integer.valueOf(this.k), Integer.valueOf(this.l));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getManufacturer(), false);
        SafeParcelWriter.writeString(parcel, 2, getModel(), false);
        SafeParcelWriter.writeString(parcel, 4, getUid(), false);
        SafeParcelWriter.writeInt(parcel, 5, getType());
        SafeParcelWriter.writeInt(parcel, 6, this.l);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @ShowFirstParty
    @SafeParcelable.Constructor
    public Device(@NonNull @SafeParcelable.Param(id = 1) String str, @NonNull @SafeParcelable.Param(id = 2) String str2, @NonNull @SafeParcelable.Param(id = 4) String str3, @SafeParcelable.Param(id = 5) int i, @SafeParcelable.Param(id = 6) int i2) {
        this.h = (String) Preconditions.checkNotNull(str);
        this.i = (String) Preconditions.checkNotNull(str2);
        if (str3 != null) {
            this.j = str3;
            this.k = i;
            this.l = i2;
            return;
        }
        throw new IllegalStateException("Device UID is null.");
    }
}
