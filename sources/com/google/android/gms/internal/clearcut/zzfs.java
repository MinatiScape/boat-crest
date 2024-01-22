package com.google.android.gms.internal.clearcut;

import com.htsmart.wristband2.WristbandManager;
import java.io.IOException;
import java.nio.BufferOverflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
/* loaded from: classes7.dex */
public final class zzfs {

    /* renamed from: a  reason: collision with root package name */
    public final ByteBuffer f8621a;
    public zzbn b;
    public int c;

    public zzfs(ByteBuffer byteBuffer) {
        this.f8621a = byteBuffer;
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
    }

    public zzfs(byte[] bArr, int i, int i2) {
        this(ByteBuffer.wrap(bArr, i, i2));
    }

    public static int a(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            if (i2 >= length) {
                break;
            }
            char charAt = charSequence.charAt(i2);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                int length2 = charSequence.length();
                while (i2 < length2) {
                    char charAt2 = charSequence.charAt(i2);
                    if (charAt2 < 2048) {
                        i += (127 - charAt2) >>> 31;
                    } else {
                        i += 2;
                        if (55296 <= charAt2 && charAt2 <= 57343) {
                            if (Character.codePointAt(charSequence, i2) < 65536) {
                                StringBuilder sb = new StringBuilder(39);
                                sb.append("Unpaired surrogate at index ");
                                sb.append(i2);
                                throw new IllegalArgumentException(sb.toString());
                            }
                            i2++;
                        }
                    }
                    i2++;
                }
                i3 += i;
            }
        }
        if (i3 >= length) {
            return i3;
        }
        StringBuilder sb2 = new StringBuilder(54);
        sb2.append("UTF-8 length does not fit in int: ");
        sb2.append(i3 + 4294967296L);
        throw new IllegalArgumentException(sb2.toString());
    }

    public static void d(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int i2;
        char charAt;
        int i3;
        if (byteBuffer.isReadOnly()) {
            throw new ReadOnlyBufferException();
        }
        int i4 = 0;
        if (!byteBuffer.hasArray()) {
            int length = charSequence.length();
            while (i4 < length) {
                char charAt2 = charSequence.charAt(i4);
                char c = charAt2;
                if (charAt2 >= 128) {
                    if (charAt2 < 2048) {
                        i3 = (charAt2 >>> 6) | 960;
                    } else if (charAt2 >= 55296 && 57343 >= charAt2) {
                        int i5 = i4 + 1;
                        if (i5 != charSequence.length()) {
                            char charAt3 = charSequence.charAt(i5);
                            if (Character.isSurrogatePair(charAt2, charAt3)) {
                                int codePoint = Character.toCodePoint(charAt2, charAt3);
                                byteBuffer.put((byte) ((codePoint >>> 18) | 240));
                                byteBuffer.put((byte) (((codePoint >>> 12) & 63) | 128));
                                byteBuffer.put((byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put((byte) ((codePoint & 63) | 128));
                                i4 = i5;
                                i4++;
                            } else {
                                i4 = i5;
                            }
                        }
                        StringBuilder sb = new StringBuilder(39);
                        sb.append("Unpaired surrogate at index ");
                        sb.append(i4 - 1);
                        throw new IllegalArgumentException(sb.toString());
                    } else {
                        byteBuffer.put((byte) ((charAt2 >>> '\f') | 480));
                        i3 = ((charAt2 >>> 6) & 63) | 128;
                    }
                    byteBuffer.put((byte) i3);
                    c = (charAt2 & org.apache.commons.codec.net.a.SEP) | 128;
                }
                byteBuffer.put((byte) c);
                i4++;
            }
            return;
        }
        try {
            byte[] array = byteBuffer.array();
            int arrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
            int remaining = byteBuffer.remaining();
            int length2 = charSequence.length();
            int i6 = remaining + arrayOffset;
            while (i4 < length2) {
                int i7 = i4 + arrayOffset;
                if (i7 >= i6 || (charAt = charSequence.charAt(i4)) >= 128) {
                    break;
                }
                array[i7] = (byte) charAt;
                i4++;
            }
            if (i4 == length2) {
                i = arrayOffset + length2;
            } else {
                i = arrayOffset + i4;
                while (i4 < length2) {
                    char charAt4 = charSequence.charAt(i4);
                    if (charAt4 >= 128 || i >= i6) {
                        if (charAt4 < 2048 && i <= i6 - 2) {
                            int i8 = i + 1;
                            array[i] = (byte) ((charAt4 >>> 6) | 960);
                            i = i8 + 1;
                            array[i8] = (byte) ((charAt4 & org.apache.commons.codec.net.a.SEP) | 128);
                        } else if ((charAt4 >= 55296 && 57343 >= charAt4) || i > i6 - 3) {
                            if (i > i6 - 4) {
                                StringBuilder sb2 = new StringBuilder(37);
                                sb2.append("Failed writing ");
                                sb2.append(charAt4);
                                sb2.append(" at index ");
                                sb2.append(i);
                                throw new ArrayIndexOutOfBoundsException(sb2.toString());
                            }
                            int i9 = i4 + 1;
                            if (i9 != charSequence.length()) {
                                char charAt5 = charSequence.charAt(i9);
                                if (Character.isSurrogatePair(charAt4, charAt5)) {
                                    int codePoint2 = Character.toCodePoint(charAt4, charAt5);
                                    int i10 = i + 1;
                                    array[i] = (byte) ((codePoint2 >>> 18) | 240);
                                    int i11 = i10 + 1;
                                    array[i10] = (byte) (((codePoint2 >>> 12) & 63) | 128);
                                    int i12 = i11 + 1;
                                    array[i11] = (byte) (((codePoint2 >>> 6) & 63) | 128);
                                    i = i12 + 1;
                                    array[i12] = (byte) ((codePoint2 & 63) | 128);
                                    i4 = i9;
                                } else {
                                    i4 = i9;
                                }
                            }
                            StringBuilder sb3 = new StringBuilder(39);
                            sb3.append("Unpaired surrogate at index ");
                            sb3.append(i4 - 1);
                            throw new IllegalArgumentException(sb3.toString());
                        } else {
                            int i13 = i + 1;
                            array[i] = (byte) ((charAt4 >>> '\f') | 480);
                            int i14 = i13 + 1;
                            array[i13] = (byte) (((charAt4 >>> 6) & 63) | 128);
                            i2 = i14 + 1;
                            array[i14] = (byte) ((charAt4 & org.apache.commons.codec.net.a.SEP) | 128);
                        }
                        i4++;
                    } else {
                        i2 = i + 1;
                        array[i] = (byte) charAt4;
                    }
                    i = i2;
                    i4++;
                }
            }
            byteBuffer.position(i - byteBuffer.arrayOffset());
        } catch (ArrayIndexOutOfBoundsException e) {
            BufferOverflowException bufferOverflowException = new BufferOverflowException();
            bufferOverflowException.initCause(e);
            throw bufferOverflowException;
        }
    }

    public static int e(int i) {
        if ((i & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzb(int i, zzfz zzfzVar) {
        int zzr = zzr(i);
        int zzas = zzfzVar.zzas();
        return zzr + e(zzas) + zzas;
    }

    public static int zzb(int i, String str) {
        return zzr(i) + zzh(str);
    }

    public static int zzb(int i, byte[] bArr) {
        return zzr(i) + zzh(bArr);
    }

    public static int zzd(int i, long j) {
        return zzr(i) + zzo(j);
    }

    public static zzfs zzg(byte[] bArr) {
        return zzh(bArr, 0, bArr.length);
    }

    public static int zzh(String str) {
        int a2 = a(str);
        return e(a2) + a2;
    }

    public static int zzh(byte[] bArr) {
        return e(bArr.length) + bArr.length;
    }

    public static zzfs zzh(byte[] bArr, int i, int i2) {
        return new zzfs(bArr, 0, i2);
    }

    public static long zzj(long j) {
        return (j >> 63) ^ (j << 1);
    }

    public static int zzo(long j) {
        if (((-128) & j) == 0) {
            return 1;
        }
        if (((-16384) & j) == 0) {
            return 2;
        }
        if (((-2097152) & j) == 0) {
            return 3;
        }
        if (((-268435456) & j) == 0) {
            return 4;
        }
        if (((-34359738368L) & j) == 0) {
            return 5;
        }
        if (((-4398046511104L) & j) == 0) {
            return 6;
        }
        if (((-562949953421312L) & j) == 0) {
            return 7;
        }
        if (((-72057594037927936L) & j) == 0) {
            return 8;
        }
        return (j & Long.MIN_VALUE) == 0 ? 9 : 10;
    }

    public static int zzr(int i) {
        return e(i << 3);
    }

    public static int zzs(int i) {
        if (i >= 0) {
            return e(i);
        }
        return 10;
    }

    public final void b(int i) throws IOException {
        byte b = (byte) i;
        if (!this.f8621a.hasRemaining()) {
            throw new zzft(this.f8621a.position(), this.f8621a.limit());
        }
        this.f8621a.put(b);
    }

    public final void c(int i) throws IOException {
        while ((i & WristbandManager.SYNC_STATE_FAILED_UNKNOWN) != 0) {
            b((i & 127) | 128);
            i >>>= 7;
        }
        b(i);
    }

    public final void zza(int i, zzfz zzfzVar) throws IOException {
        zzb(i, 2);
        if (zzfzVar.zzrs < 0) {
            zzfzVar.zzas();
        }
        c(zzfzVar.zzrs);
        zzfzVar.zza(this);
    }

    public final void zza(int i, String str) throws IOException {
        zzb(i, 2);
        try {
            int e = e(str.length());
            if (e != e(str.length() * 3)) {
                c(a(str));
                d(str, this.f8621a);
                return;
            }
            int position = this.f8621a.position();
            if (this.f8621a.remaining() < e) {
                throw new zzft(position + e, this.f8621a.limit());
            }
            this.f8621a.position(position + e);
            d(str, this.f8621a);
            int position2 = this.f8621a.position();
            this.f8621a.position(position);
            c((position2 - position) - e);
            this.f8621a.position(position2);
        } catch (BufferOverflowException e2) {
            zzft zzftVar = new zzft(this.f8621a.position(), this.f8621a.limit());
            zzftVar.initCause(e2);
            throw zzftVar;
        }
    }

    public final void zza(int i, byte[] bArr) throws IOException {
        zzb(i, 2);
        c(bArr.length);
        int length = bArr.length;
        if (this.f8621a.remaining() < length) {
            throw new zzft(this.f8621a.position(), this.f8621a.limit());
        }
        this.f8621a.put(bArr, 0, length);
    }

    public final void zzb(int i, int i2) throws IOException {
        c((i << 3) | i2);
    }

    public final void zzb(int i, boolean z) throws IOException {
        zzb(25, 0);
        byte b = z ? (byte) 1 : (byte) 0;
        if (!this.f8621a.hasRemaining()) {
            throw new zzft(this.f8621a.position(), this.f8621a.limit());
        }
        this.f8621a.put(b);
    }

    public final void zzc(int i, int i2) throws IOException {
        zzb(i, 0);
        if (i2 >= 0) {
            c(i2);
        } else {
            zzn(i2);
        }
    }

    public final void zze(int i, zzdo zzdoVar) throws IOException {
        if (this.b != null) {
            if (this.c != this.f8621a.position()) {
                this.b.write(this.f8621a.array(), this.c, this.f8621a.position() - this.c);
            }
            zzbn zzbnVar = this.b;
            zzbnVar.zza(i, zzdoVar);
            zzbnVar.flush();
            this.c = this.f8621a.position();
        }
        this.b = zzbn.zza(this.f8621a);
        this.c = this.f8621a.position();
        zzbn zzbnVar2 = this.b;
        zzbnVar2.zza(i, zzdoVar);
        zzbnVar2.flush();
        this.c = this.f8621a.position();
    }

    public final void zzem() {
        if (this.f8621a.remaining() != 0) {
            throw new IllegalStateException(String.format("Did not write as much data as expected, %s bytes remaining.", Integer.valueOf(this.f8621a.remaining())));
        }
    }

    public final void zzi(int i, long j) throws IOException {
        zzb(i, 0);
        zzn(j);
    }

    public final void zzn(long j) throws IOException {
        while (((-128) & j) != 0) {
            b((((int) j) & 127) | 128);
            j >>>= 7;
        }
        b((int) j);
    }
}
