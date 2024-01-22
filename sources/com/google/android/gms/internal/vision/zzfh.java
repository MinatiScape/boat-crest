package com.google.android.gms.internal.vision;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: classes10.dex */
public abstract class zzfh implements Serializable, Iterable<Byte> {
    public static final zzfh zzsd = new s1(zzgt.zzxi);
    private static final p1 zzse;
    private static final Comparator<zzfh> zzsf;
    private int zzmm = 0;

    static {
        zzse = e1.a() ? new u1(null) : new n1(null);
        zzsf = new l1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zza(byte b) {
        return b & 255;
    }

    public static zzfh zza(byte[] bArr, int i, int i2) {
        zzc(i, i + i2, bArr.length);
        return new s1(zzse.a(bArr, i, i2));
    }

    public static r1 zzaq(int i) {
        return new r1(i, null);
    }

    public static zzfh zzb(byte[] bArr, int i, int i2) {
        return new q1(bArr, i, i2);
    }

    public static int zzc(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) < 0) {
            if (i < 0) {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Beginning index: ");
                sb.append(i);
                sb.append(" < 0");
                throw new IndexOutOfBoundsException(sb.toString());
            } else if (i2 < i) {
                StringBuilder sb2 = new StringBuilder(66);
                sb2.append("Beginning index larger than ending index: ");
                sb2.append(i);
                sb2.append(", ");
                sb2.append(i2);
                throw new IndexOutOfBoundsException(sb2.toString());
            } else {
                StringBuilder sb3 = new StringBuilder(37);
                sb3.append("End index: ");
                sb3.append(i2);
                sb3.append(" >= ");
                sb3.append(i3);
                throw new IndexOutOfBoundsException(sb3.toString());
            }
        }
        return i4;
    }

    public static zzfh zzd(byte[] bArr) {
        return new s1(bArr);
    }

    public static zzfh zzw(String str) {
        return new s1(str.getBytes(zzgt.f10024a));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzmm;
        if (i == 0) {
            int size = size();
            i = zzb(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzmm = i;
        }
        return i;
    }

    @Override // java.lang.Iterable
    public /* synthetic */ Iterator<Byte> iterator() {
        return new m1(this);
    }

    public abstract int size();

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(size());
        objArr[2] = size() <= 50 ? c4.a(this) : String.valueOf(c4.a(zzf(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract String zza(Charset charset);

    public abstract void zza(zzfi zzfiVar) throws IOException;

    public abstract void zza(byte[] bArr, int i, int i2, int i3);

    public abstract byte zzao(int i);

    public abstract byte zzap(int i);

    public abstract int zzb(int i, int i2, int i3);

    public final String zzer() {
        return size() == 0 ? "" : zza(zzgt.f10024a);
    }

    public abstract boolean zzes();

    public final int zzet() {
        return this.zzmm;
    }

    public abstract zzfh zzf(int i, int i2);
}
