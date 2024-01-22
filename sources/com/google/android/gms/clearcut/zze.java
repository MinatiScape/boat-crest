package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.clearcut.ClearcutLogger;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.clearcut.zzha;
import com.google.android.gms.internal.clearcut.zzr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.Arrays;
@SafeParcelable.Class(creator = "LogEventParcelableCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes6.dex */
public final class zze extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zze> CREATOR = new zzf();
    @SafeParcelable.Field(id = 4)
    public int[] h;
    @SafeParcelable.Field(id = 5)
    public String[] i;
    @SafeParcelable.Field(id = 6)
    public int[] j;
    @SafeParcelable.Field(id = 7)
    public byte[][] k;
    @SafeParcelable.Field(id = 9)
    public ExperimentTokens[] l;
    @SafeParcelable.Field(defaultValue = "true", id = 8)
    public boolean m;
    public final zzha zzaa;
    @SafeParcelable.Field(id = 2)
    public zzr zzag;
    @SafeParcelable.Field(id = 3)
    public byte[] zzah;
    public final ClearcutLogger.zzb zzan;
    public final ClearcutLogger.zzb zzt;

    public zze(zzr zzrVar, zzha zzhaVar, ClearcutLogger.zzb zzbVar, ClearcutLogger.zzb zzbVar2, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, ExperimentTokens[] experimentTokensArr, boolean z) {
        this.zzag = zzrVar;
        this.zzaa = zzhaVar;
        this.zzt = zzbVar;
        this.zzan = null;
        this.h = iArr;
        this.i = null;
        this.j = iArr2;
        this.k = null;
        this.l = null;
        this.m = z;
    }

    @SafeParcelable.Constructor
    public zze(@SafeParcelable.Param(id = 2) zzr zzrVar, @SafeParcelable.Param(id = 3) byte[] bArr, @SafeParcelable.Param(id = 4) int[] iArr, @SafeParcelable.Param(id = 5) String[] strArr, @SafeParcelable.Param(id = 6) int[] iArr2, @SafeParcelable.Param(id = 7) byte[][] bArr2, @SafeParcelable.Param(id = 8) boolean z, @SafeParcelable.Param(id = 9) ExperimentTokens[] experimentTokensArr) {
        this.zzag = zzrVar;
        this.zzah = bArr;
        this.h = iArr;
        this.i = strArr;
        this.zzaa = null;
        this.zzt = null;
        this.zzan = null;
        this.j = iArr2;
        this.k = bArr2;
        this.l = experimentTokensArr;
        this.m = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zze) {
            zze zzeVar = (zze) obj;
            if (Objects.equal(this.zzag, zzeVar.zzag) && Arrays.equals(this.zzah, zzeVar.zzah) && Arrays.equals(this.h, zzeVar.h) && Arrays.equals(this.i, zzeVar.i) && Objects.equal(this.zzaa, zzeVar.zzaa) && Objects.equal(this.zzt, zzeVar.zzt) && Objects.equal(this.zzan, zzeVar.zzan) && Arrays.equals(this.j, zzeVar.j) && Arrays.deepEquals(this.k, zzeVar.k) && Arrays.equals(this.l, zzeVar.l) && this.m == zzeVar.m) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzag, this.zzah, this.h, this.i, this.zzaa, this.zzt, this.zzan, this.j, this.k, this.l, Boolean.valueOf(this.m));
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("LogEventParcelable[");
        sb.append(this.zzag);
        sb.append(", LogEventBytes: ");
        byte[] bArr = this.zzah;
        sb.append(bArr == null ? null : new String(bArr));
        sb.append(", TestCodes: ");
        sb.append(Arrays.toString(this.h));
        sb.append(", MendelPackages: ");
        sb.append(Arrays.toString(this.i));
        sb.append(", LogEvent: ");
        sb.append(this.zzaa);
        sb.append(", ExtensionProducer: ");
        sb.append(this.zzt);
        sb.append(", VeProducer: ");
        sb.append(this.zzan);
        sb.append(", ExperimentIDs: ");
        sb.append(Arrays.toString(this.j));
        sb.append(", ExperimentTokens: ");
        sb.append(Arrays.toString(this.k));
        sb.append(", ExperimentTokensParcelables: ");
        sb.append(Arrays.toString(this.l));
        sb.append(", AddPhenotypeExperimentTokens: ");
        sb.append(this.m);
        sb.append("]");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzag, i, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzah, false);
        SafeParcelWriter.writeIntArray(parcel, 4, this.h, false);
        SafeParcelWriter.writeStringArray(parcel, 5, this.i, false);
        SafeParcelWriter.writeIntArray(parcel, 6, this.j, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 7, this.k, false);
        SafeParcelWriter.writeBoolean(parcel, 8, this.m);
        SafeParcelWriter.writeTypedArray(parcel, 9, this.l, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
