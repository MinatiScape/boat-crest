package okio;

import com.jstyle.blesdk1860.constant.BleConst;
import kotlin.UShort;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.m;
import okio.Buffer;
import okio.internal._ByteStringKt;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class _UtilKt {
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    public static final Buffer.UnsafeCursor f14321a = new Buffer.UnsafeCursor();
    public static final int b = -1234567890;

    public static final int and(byte b2, int i) {
        return b2 & i;
    }

    public static final long and(byte b2, long j) {
        return b2 & j;
    }

    public static final long and(int i, long j) {
        return i & j;
    }

    public static final boolean arrayRangeEquals(@NotNull byte[] a2, int i, @NotNull byte[] b2, int i2, int i3) {
        Intrinsics.checkNotNullParameter(a2, "a");
        Intrinsics.checkNotNullParameter(b2, "b");
        for (int i4 = 0; i4 < i3; i4++) {
            if (a2[i4 + i] != b2[i4 + i2]) {
                return false;
            }
        }
        return true;
    }

    public static final void checkOffsetAndCount(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException("size=" + j + " offset=" + j2 + " byteCount=" + j3);
        }
    }

    public static final int getDEFAULT__ByteString_size() {
        return b;
    }

    @NotNull
    public static final Buffer.UnsafeCursor getDEFAULT__new_UnsafeCursor() {
        return f14321a;
    }

    public static /* synthetic */ void getDEFAULT__new_UnsafeCursor$annotations() {
    }

    public static final int leftRotate(int i, int i2) {
        return (i >>> (32 - i2)) | (i << i2);
    }

    public static final long minOf(long j, int i) {
        return Math.min(j, i);
    }

    @NotNull
    public static final Buffer.UnsafeCursor resolveDefaultParameter(@NotNull Buffer.UnsafeCursor unsafeCursor) {
        Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
        return unsafeCursor == f14321a ? new Buffer.UnsafeCursor() : unsafeCursor;
    }

    public static final int reverseBytes(int i) {
        return ((i & 255) << 24) | (((-16777216) & i) >>> 24) | ((16711680 & i) >>> 8) | ((65280 & i) << 8);
    }

    public static final long reverseBytes(long j) {
        return ((j & 255) << 56) | (((-72057594037927936L) & j) >>> 56) | ((71776119061217280L & j) >>> 40) | ((280375465082880L & j) >>> 24) | ((1095216660480L & j) >>> 8) | ((4278190080L & j) << 8) | ((16711680 & j) << 24) | ((65280 & j) << 40);
    }

    public static final short reverseBytes(short s) {
        int i = s & UShort.MAX_VALUE;
        return (short) (((i & 255) << 8) | ((65280 & i) >>> 8));
    }

    public static final long rightRotate(long j, int i) {
        return (j << (64 - i)) | (j >>> i);
    }

    public static final int shl(byte b2, int i) {
        return b2 << i;
    }

    public static final int shr(byte b2, int i) {
        return b2 >> i;
    }

    @NotNull
    public static final String toHexString(byte b2) {
        return m.concatToString(new char[]{_ByteStringKt.getHEX_DIGIT_CHARS()[(b2 >> 4) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[b2 & 15]});
    }

    public static final byte xor(byte b2, byte b3) {
        return (byte) (b2 ^ b3);
    }

    public static final long minOf(int i, long j) {
        return Math.min(i, j);
    }

    public static final int resolveDefaultParameter(@NotNull ByteString byteString, int i) {
        Intrinsics.checkNotNullParameter(byteString, "<this>");
        return i == b ? byteString.size() : i;
    }

    public static final int resolveDefaultParameter(@NotNull byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        return i == b ? bArr.length : i;
    }

    @NotNull
    public static final String toHexString(int i) {
        if (i == 0) {
            return BleConst.GetDeviceTime;
        }
        int i2 = 0;
        char[] cArr = {_ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 28) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 24) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 20) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 16) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 12) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 8) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[(i >> 4) & 15], _ByteStringKt.getHEX_DIGIT_CHARS()[i & 15]};
        while (i2 < 8 && cArr[i2] == '0') {
            i2++;
        }
        return m.concatToString(cArr, i2, 8);
    }

    @NotNull
    public static final String toHexString(long j) {
        if (j == 0) {
            return BleConst.GetDeviceTime;
        }
        int i = 0;
        char[] cArr = {_ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 60) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 56) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 52) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 48) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 44) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 40) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 36) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 32) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 28) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 24) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 20) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 16) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 12) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 8) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) ((j >> 4) & 15)], _ByteStringKt.getHEX_DIGIT_CHARS()[(int) (j & 15)]};
        while (i < 16 && cArr[i] == '0') {
            i++;
        }
        return m.concatToString(cArr, i, 16);
    }
}
