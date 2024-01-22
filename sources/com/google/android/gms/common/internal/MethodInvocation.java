package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.jstyle.blesdk1860.constant.BleConst;
@KeepForSdk
@SafeParcelable.Class(creator = "MethodInvocationCreator")
/* loaded from: classes6.dex */
public class MethodInvocation extends AbstractSafeParcelable {
    @NonNull
    public static final Parcelable.Creator<MethodInvocation> CREATOR = new zan();
    @SafeParcelable.Field(getter = "getMethodKey", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getResultStatusCode", id = 2)
    public final int i;
    @SafeParcelable.Field(getter = "getConnectionResultStatusCode", id = 3)
    public final int j;
    @SafeParcelable.Field(getter = "getStartTimeMillis", id = 4)
    public final long k;
    @SafeParcelable.Field(getter = "getEndTimeMillis", id = 5)
    public final long l;
    @Nullable
    @SafeParcelable.Field(getter = "getCallingModuleId", id = 6)
    public final String m;
    @Nullable
    @SafeParcelable.Field(getter = "getCallingEntryPoint", id = 7)
    public final String n;
    @SafeParcelable.Field(defaultValue = BleConst.GetDeviceTime, getter = "getServiceId", id = 8)
    public final int o;
    @SafeParcelable.Field(defaultValue = "-1", getter = "getLatencyMillis", id = 9)
    public final int p;

    @KeepForSdk
    @Deprecated
    public MethodInvocation(int i, int i2, int i3, long j, long j2, @Nullable String str, @Nullable String str2, int i4) {
        this(i, i2, i3, j, j2, str, str2, i4, -1);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NonNull Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeInt(parcel, 2, this.i);
        SafeParcelWriter.writeInt(parcel, 3, this.j);
        SafeParcelWriter.writeLong(parcel, 4, this.k);
        SafeParcelWriter.writeLong(parcel, 5, this.l);
        SafeParcelWriter.writeString(parcel, 6, this.m, false);
        SafeParcelWriter.writeString(parcel, 7, this.n, false);
        SafeParcelWriter.writeInt(parcel, 8, this.o);
        SafeParcelWriter.writeInt(parcel, 9, this.p);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @SafeParcelable.Constructor
    public MethodInvocation(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) int i3, @SafeParcelable.Param(id = 4) long j, @SafeParcelable.Param(id = 5) long j2, @Nullable @SafeParcelable.Param(id = 6) String str, @Nullable @SafeParcelable.Param(id = 7) String str2, @SafeParcelable.Param(id = 8) int i4, @SafeParcelable.Param(id = 9) int i5) {
        this.h = i;
        this.i = i2;
        this.j = i3;
        this.k = j;
        this.l = j2;
        this.m = str;
        this.n = str2;
        this.o = i4;
        this.p = i5;
    }
}
