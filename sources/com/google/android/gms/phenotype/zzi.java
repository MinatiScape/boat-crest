package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.Arrays;
@SafeParcelable.Class(creator = "FlagCreator")
@SafeParcelable.Reserved({1})
/* loaded from: classes10.dex */
public final class zzi extends AbstractSafeParcelable implements Comparable<zzi> {
    public static final Parcelable.Creator<zzi> CREATOR = new zzk();
    @SafeParcelable.Field(id = 3)
    public final long h;
    @SafeParcelable.Field(id = 4)
    public final boolean i;
    @SafeParcelable.Field(id = 5)
    public final double j;
    @SafeParcelable.Field(id = 6)
    public final String k;
    @SafeParcelable.Field(id = 7)
    public final byte[] l;
    @SafeParcelable.Field(id = 8)
    public final int m;
    @SafeParcelable.Field(id = 2)
    public final String name;
    @SafeParcelable.Field(id = 9)
    public final int zzah;

    static {
        new f();
    }

    @SafeParcelable.Constructor
    public zzi(@SafeParcelable.Param(id = 2) String str, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) boolean z, @SafeParcelable.Param(id = 5) double d, @SafeParcelable.Param(id = 6) String str2, @SafeParcelable.Param(id = 7) byte[] bArr, @SafeParcelable.Param(id = 8) int i, @SafeParcelable.Param(id = 9) int i2) {
        this.name = str;
        this.h = j;
        this.i = z;
        this.j = d;
        this.k = str2;
        this.l = bArr;
        this.m = i;
        this.zzah = i2;
    }

    public static int a(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i == i2 ? 0 : 1;
    }

    @Override // java.lang.Comparable
    public final /* synthetic */ int compareTo(zzi zziVar) {
        zzi zziVar2 = zziVar;
        int compareTo = this.name.compareTo(zziVar2.name);
        if (compareTo != 0) {
            return compareTo;
        }
        int a2 = a(this.m, zziVar2.m);
        if (a2 != 0) {
            return a2;
        }
        int i = this.m;
        if (i == 1) {
            int i2 = (this.h > zziVar2.h ? 1 : (this.h == zziVar2.h ? 0 : -1));
            if (i2 < 0) {
                return -1;
            }
            return i2 == 0 ? 0 : 1;
        } else if (i == 2) {
            boolean z = this.i;
            if (z == zziVar2.i) {
                return 0;
            }
            return z ? 1 : -1;
        } else if (i != 3) {
            if (i == 4) {
                String str = this.k;
                String str2 = zziVar2.k;
                if (str == str2) {
                    return 0;
                }
                if (str == null) {
                    return -1;
                }
                if (str2 == null) {
                    return 1;
                }
                return str.compareTo(str2);
            } else if (i != 5) {
                int i3 = this.m;
                StringBuilder sb = new StringBuilder(31);
                sb.append("Invalid enum value: ");
                sb.append(i3);
                throw new AssertionError(sb.toString());
            } else {
                byte[] bArr = this.l;
                byte[] bArr2 = zziVar2.l;
                if (bArr == bArr2) {
                    return 0;
                }
                if (bArr == null) {
                    return -1;
                }
                if (bArr2 == null) {
                    return 1;
                }
                for (int i4 = 0; i4 < Math.min(this.l.length, zziVar2.l.length); i4++) {
                    int i5 = this.l[i4] - zziVar2.l[i4];
                    if (i5 != 0) {
                        return i5;
                    }
                }
                return a(this.l.length, zziVar2.l.length);
            }
        } else {
            return Double.compare(this.j, zziVar2.j);
        }
    }

    public final boolean equals(Object obj) {
        int i;
        if (obj instanceof zzi) {
            zzi zziVar = (zzi) obj;
            if (zzn.a(this.name, zziVar.name) && (i = this.m) == zziVar.m && this.zzah == zziVar.zzah) {
                if (i != 1) {
                    if (i == 2) {
                        return this.i == zziVar.i;
                    } else if (i == 3) {
                        return this.j == zziVar.j;
                    } else if (i != 4) {
                        if (i == 5) {
                            return Arrays.equals(this.l, zziVar.l);
                        }
                        int i2 = this.m;
                        StringBuilder sb = new StringBuilder(31);
                        sb.append("Invalid enum value: ");
                        sb.append(i2);
                        throw new AssertionError(sb.toString());
                    } else {
                        return zzn.a(this.k, zziVar.k);
                    }
                } else if (this.h == zziVar.h) {
                    return true;
                }
            }
        }
        return false;
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append("Flag(");
        sb.append(this.name);
        sb.append(", ");
        int i = this.m;
        if (i == 1) {
            sb.append(this.h);
        } else if (i == 2) {
            sb.append(this.i);
        } else if (i != 3) {
            if (i == 4) {
                sb.append("'");
                str = this.k;
            } else if (i != 5) {
                String str2 = this.name;
                int i2 = this.m;
                StringBuilder sb2 = new StringBuilder(String.valueOf(str2).length() + 27);
                sb2.append("Invalid type: ");
                sb2.append(str2);
                sb2.append(", ");
                sb2.append(i2);
                throw new AssertionError(sb2.toString());
            } else if (this.l == null) {
                sb.append("null");
            } else {
                sb.append("'");
                str = Base64.encodeToString(this.l, 3);
            }
            sb.append(str);
            sb.append("'");
        } else {
            sb.append(this.j);
        }
        sb.append(", ");
        sb.append(this.m);
        sb.append(", ");
        sb.append(this.zzah);
        sb.append(")");
        return sb.toString();
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeLong(parcel, 3, this.h);
        SafeParcelWriter.writeBoolean(parcel, 4, this.i);
        SafeParcelWriter.writeDouble(parcel, 5, this.j);
        SafeParcelWriter.writeString(parcel, 6, this.k, false);
        SafeParcelWriter.writeByteArray(parcel, 7, this.l, false);
        SafeParcelWriter.writeInt(parcel, 8, this.m);
        SafeParcelWriter.writeInt(parcel, 9, this.zzah);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
