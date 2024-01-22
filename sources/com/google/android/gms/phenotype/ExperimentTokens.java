package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.fido.fido2.api.common.DevicePublicKeyStringDef;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@KeepForSdk
@SafeParcelable.Class(creator = "ExperimentTokensCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public class ExperimentTokens extends AbstractSafeParcelable {
    @KeepForSdk
    public static final Parcelable.Creator<ExperimentTokens> CREATOR = new zzh();
    public static final byte[][] p;
    @SafeParcelable.Field(id = 2)
    public final String h;
    @SafeParcelable.Field(id = 3)
    public final byte[] i;
    @SafeParcelable.Field(id = 4)
    public final byte[][] j;
    @SafeParcelable.Field(id = 5)
    public final byte[][] k;
    @SafeParcelable.Field(id = 6)
    public final byte[][] l;
    @SafeParcelable.Field(id = 7)
    public final byte[][] m;
    @SafeParcelable.Field(id = 8)
    public final int[] n;
    @SafeParcelable.Field(id = 9)
    public final byte[][] o;

    static {
        byte[][] bArr = new byte[0];
        p = bArr;
        new ExperimentTokens("", null, bArr, bArr, bArr, bArr, null, null);
        new b();
        new c();
        new d();
        new e();
    }

    @SafeParcelable.Constructor
    public ExperimentTokens(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) byte[] bArr, @SafeParcelable.Param(id = 4) byte[][] bArr2, @SafeParcelable.Param(id = 5) byte[][] bArr3, @SafeParcelable.Param(id = 6) byte[][] bArr4, @SafeParcelable.Param(id = 7) byte[][] bArr5, @SafeParcelable.Param(id = 8) int[] iArr, @SafeParcelable.Param(id = 9) byte[][] bArr6) {
        this.h = str;
        this.i = bArr;
        this.j = bArr2;
        this.k = bArr3;
        this.l = bArr4;
        this.m = bArr5;
        this.n = iArr;
        this.o = bArr6;
    }

    public static List<Integer> a(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(iArr.length);
        for (int i : iArr) {
            arrayList.add(Integer.valueOf(i));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static List<String> b(byte[][] bArr) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] bArr2 : bArr) {
            arrayList.add(Base64.encodeToString(bArr2, 3));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public static void c(StringBuilder sb, String str, byte[][] bArr) {
        String str2;
        sb.append(str);
        sb.append("=");
        if (bArr == null) {
            str2 = "null";
        } else {
            sb.append("(");
            int length = bArr.length;
            boolean z = true;
            int i = 0;
            while (i < length) {
                byte[] bArr2 = bArr[i];
                if (!z) {
                    sb.append(", ");
                }
                sb.append("'");
                sb.append(Base64.encodeToString(bArr2, 3));
                sb.append("'");
                i++;
                z = false;
            }
            str2 = ")";
        }
        sb.append(str2);
    }

    public boolean equals(Object obj) {
        if (obj instanceof ExperimentTokens) {
            ExperimentTokens experimentTokens = (ExperimentTokens) obj;
            if (zzn.a(this.h, experimentTokens.h) && Arrays.equals(this.i, experimentTokens.i) && zzn.a(b(this.j), b(experimentTokens.j)) && zzn.a(b(this.k), b(experimentTokens.k)) && zzn.a(b(this.l), b(experimentTokens.l)) && zzn.a(b(this.m), b(experimentTokens.m)) && zzn.a(a(this.n), a(experimentTokens.n)) && zzn.a(b(this.o), b(experimentTokens.o))) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        String sb;
        StringBuilder sb2 = new StringBuilder("ExperimentTokens");
        sb2.append("(");
        String str = this.h;
        if (str == null) {
            sb = "null";
        } else {
            StringBuilder sb3 = new StringBuilder(String.valueOf(str).length() + 2);
            sb3.append("'");
            sb3.append(str);
            sb3.append("'");
            sb = sb3.toString();
        }
        sb2.append(sb);
        sb2.append(", ");
        byte[] bArr = this.i;
        sb2.append(DevicePublicKeyStringDef.DIRECT);
        sb2.append("=");
        if (bArr == null) {
            sb2.append("null");
        } else {
            sb2.append("'");
            sb2.append(Base64.encodeToString(bArr, 3));
            sb2.append("'");
        }
        sb2.append(", ");
        c(sb2, "GAIA", this.j);
        sb2.append(", ");
        c(sb2, "PSEUDO", this.k);
        sb2.append(", ");
        c(sb2, "ALWAYS", this.l);
        sb2.append(", ");
        c(sb2, "OTHER", this.m);
        sb2.append(", ");
        int[] iArr = this.n;
        sb2.append("weak");
        sb2.append("=");
        if (iArr == null) {
            sb2.append("null");
        } else {
            sb2.append("(");
            int length = iArr.length;
            boolean z = true;
            int i = 0;
            while (i < length) {
                int i2 = iArr[i];
                if (!z) {
                    sb2.append(", ");
                }
                sb2.append(i2);
                i++;
                z = false;
            }
            sb2.append(")");
        }
        sb2.append(", ");
        c(sb2, "directs", this.o);
        sb2.append(")");
        return sb2.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.h, false);
        SafeParcelWriter.writeByteArray(parcel, 3, this.i, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 4, this.j, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 5, this.k, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 6, this.l, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 7, this.m, false);
        SafeParcelWriter.writeIntArray(parcel, 8, this.n, false);
        SafeParcelWriter.writeByteArrayArray(parcel, 9, this.o, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
