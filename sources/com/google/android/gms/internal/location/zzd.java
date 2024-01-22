package com.google.android.gms.internal.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
import java.util.List;
import org.eclipse.paho.client.mqttv3.MqttTopic;
@SafeParcelable.Class(creator = "ClientIdentityCreator")
/* loaded from: classes8.dex */
public final class zzd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzd> CREATOR = new zze();
    @SafeParcelable.Field(getter = "getUid", id = 1)
    public final int h;
    @SafeParcelable.Field(getter = "getPid", id = 2)
    public final int i;
    @SafeParcelable.Field(getter = "getPackageName", id = 3)
    public final String j;
    @Nullable
    @SafeParcelable.Field(getter = "getAttributionTag", id = 4)
    public final String k;
    @SafeParcelable.Field(getter = "getClientSdkVersion", id = 5)
    public final int l;
    @Nullable
    @SafeParcelable.Field(getter = "getListenerId", id = 6)
    public final String m;
    @Nullable
    @SafeParcelable.Field(getter = "getImpersonator", id = 7)
    public final zzd n;
    @SafeParcelable.Field(defaultValueUnchecked = "com.google.common.collect.ImmutableList.of()", getter = "getClientFeatures", id = 8)
    public final List o;

    static {
        Process.myUid();
        Process.myPid();
    }

    @SafeParcelable.Constructor
    public zzd(@SafeParcelable.Param(id = 1) int i, @SafeParcelable.Param(id = 2) int i2, @SafeParcelable.Param(id = 3) String str, @Nullable @SafeParcelable.Param(id = 4) String str2, @Nullable @SafeParcelable.Param(id = 6) String str3, @SafeParcelable.Param(id = 5) int i3, @SafeParcelable.Param(id = 8) List list, @Nullable @SafeParcelable.Param(id = 7) zzd zzdVar) {
        this.h = i;
        this.i = i2;
        this.j = str;
        this.k = str2;
        this.m = str3;
        this.l = i3;
        this.o = zzds.zzj(list);
        this.n = zzdVar;
    }

    public final boolean equals(@Nullable Object obj) {
        if (obj instanceof zzd) {
            zzd zzdVar = (zzd) obj;
            if (this.h == zzdVar.h && this.i == zzdVar.i && this.l == zzdVar.l && this.j.equals(zzdVar.j) && zzdl.zza(this.k, zzdVar.k) && zzdl.zza(this.m, zzdVar.m) && zzdl.zza(this.n, zzdVar.n) && this.o.equals(zzdVar.o)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.h), this.j, this.k, this.m});
    }

    public final String toString() {
        int length = this.j.length() + 18;
        String str = this.k;
        if (str != null) {
            length += str.length();
        }
        StringBuilder sb = new StringBuilder(length);
        sb.append(this.h);
        sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
        sb.append(this.j);
        if (this.k != null) {
            sb.append("[");
            if (this.k.startsWith(this.j)) {
                sb.append((CharSequence) this.k, this.j.length(), this.k.length());
            } else {
                sb.append(this.k);
            }
            sb.append("]");
        }
        if (this.m != null) {
            sb.append(MqttTopic.TOPIC_LEVEL_SEPARATOR);
            sb.append(Integer.toHexString(this.m.hashCode()));
        }
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.h);
        SafeParcelWriter.writeInt(parcel, 2, this.i);
        SafeParcelWriter.writeString(parcel, 3, this.j, false);
        SafeParcelWriter.writeString(parcel, 4, this.k, false);
        SafeParcelWriter.writeInt(parcel, 5, this.l);
        SafeParcelWriter.writeString(parcel, 6, this.m, false);
        SafeParcelWriter.writeParcelable(parcel, 7, this.n, i, false);
        SafeParcelWriter.writeTypedList(parcel, 8, this.o, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
