package com.google.android.gms.internal.firebase_ml;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Locale;
/* loaded from: classes7.dex */
public abstract class zzvv implements Serializable, Iterable<Byte> {
    public static final zzvv zzchp = new h6(zzxd.zzcmh);
    private static final d6 zzchq;
    private static final Comparator<zzvv> zzchr;
    private int zzamb = 0;

    static {
        zzchq = t5.b() ? new g6(null) : new c6(null);
        zzchr = new a6();
    }

    private static zzvv zza(Iterator<zzvv> it, int i) {
        if (i > 0) {
            if (i == 1) {
                return it.next();
            }
            int i2 = i >>> 1;
            zzvv zza = zza(it, i2);
            zzvv zza2 = zza(it, i - i2);
            if (Integer.MAX_VALUE - zza.size() >= zza2.size()) {
                return y7.zza(zza, zza2);
            }
            int size = zza.size();
            int size2 = zza2.size();
            StringBuilder sb = new StringBuilder(53);
            sb.append("ByteString would be too long: ");
            sb.append(size);
            sb.append("+");
            sb.append(size2);
            throw new IllegalArgumentException(sb.toString());
        }
        throw new IllegalArgumentException(String.format("length (%s) must be >= 1", Integer.valueOf(i)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zzb(byte b) {
        return b & 255;
    }

    public static zzvv zzb(InputStream inputStream) throws IOException {
        ArrayList arrayList = new ArrayList();
        int i = 256;
        while (true) {
            byte[] bArr = new byte[i];
            int i2 = 0;
            while (i2 < i) {
                int read = inputStream.read(bArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            }
            zzvv zzc = i2 == 0 ? null : zzc(bArr, 0, i2);
            if (zzc == null) {
                break;
            }
            arrayList.add(zzc);
            i = Math.min(i << 1, 8192);
        }
        int size = arrayList.size();
        if (size == 0) {
            return zzchp;
        }
        return zza(arrayList.iterator(), size);
    }

    public static zzvv zzc(byte[] bArr, int i, int i2) {
        zzd(i, i + i2, bArr.length);
        return new h6(zzchq.a(bArr, i, i2));
    }

    public static zzvv zzcj(String str) {
        return new h6(str.getBytes(zzxd.f8814a));
    }

    public static f6 zzcy(int i) {
        return new f6(i, null);
    }

    public static int zzd(int i, int i2, int i3) {
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

    public static void zzg(int i, int i2) {
        if (((i2 - (i + 1)) | i) < 0) {
            if (i < 0) {
                StringBuilder sb = new StringBuilder(22);
                sb.append("Index < 0: ");
                sb.append(i);
                throw new ArrayIndexOutOfBoundsException(sb.toString());
            }
            StringBuilder sb2 = new StringBuilder(40);
            sb2.append("Index > length: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new ArrayIndexOutOfBoundsException(sb2.toString());
        }
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzamb;
        if (i == 0) {
            int size = size();
            i = zzc(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzamb = i;
        }
        return i;
    }

    public abstract int size();

    public final String toString() {
        Locale locale = Locale.ROOT;
        Object[] objArr = new Object[3];
        objArr[0] = Integer.toHexString(System.identityHashCode(this));
        objArr[1] = Integer.valueOf(size());
        objArr[2] = size() <= 50 ? q8.a(this) : String.valueOf(q8.a(zzf(0, 47))).concat("...");
        return String.format(locale, "<ByteString@%s size=%d contents=\"%s\">", objArr);
    }

    public abstract void zza(zzvs zzvsVar) throws IOException;

    public abstract int zzb(int i, int i2, int i3);

    public abstract String zzb(Charset charset);

    public abstract void zzb(byte[] bArr, int i, int i2, int i3);

    public abstract int zzc(int i, int i2, int i3);

    public abstract byte zzcw(int i);

    public abstract byte zzcx(int i);

    public abstract zzvv zzf(int i, int i2);

    @Override // java.lang.Iterable
    /* renamed from: zzto */
    public zzwa iterator() {
        return new y5(this);
    }

    public final String zztp() {
        return size() == 0 ? "" : zzb(zzxd.f8814a);
    }

    public abstract boolean zztq();

    public abstract int zztr();

    public abstract boolean zzts();

    public final int zztt() {
        return this.zzamb;
    }

    @Deprecated
    public final void zza(byte[] bArr, int i, int i2, int i3) {
        zzd(0, i3 + 0, size());
        zzd(i2, i2 + i3, bArr.length);
        if (i3 > 0) {
            zzb(bArr, 0, i2, i3);
        }
    }
}
