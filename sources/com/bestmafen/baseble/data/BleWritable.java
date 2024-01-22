package com.bestmafen.baseble.data;

import androidx.annotation.CallSuper;
import androidx.annotation.IntRange;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.h;
import kotlin.text.Typography;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public class BleWritable extends BleReadable implements BleBuffer {
    public static /* synthetic */ void writeBytes$default(BleWritable bleWritable, byte[] bArr, ByteOrder byteOrder, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeBytes");
        }
        if ((i & 2) != 0) {
            byteOrder = bleWritable.getMByteOrder();
        }
        bleWritable.writeBytes(bArr, byteOrder);
    }

    public static /* synthetic */ void writeDouble$default(BleWritable bleWritable, double d, ByteOrder byteOrder, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeDouble");
        }
        if ((i & 2) != 0) {
            byteOrder = bleWritable.getMByteOrder();
        }
        bleWritable.writeDouble(d, byteOrder);
    }

    public static /* synthetic */ void writeFloat$default(BleWritable bleWritable, float f, ByteOrder byteOrder, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeFloat");
        }
        if ((i & 2) != 0) {
            byteOrder = bleWritable.getMByteOrder();
        }
        bleWritable.writeFloat(f, byteOrder);
    }

    public static /* synthetic */ void writeInt16$default(BleWritable bleWritable, int i, ByteOrder byteOrder, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeInt16");
        }
        if ((i2 & 2) != 0) {
            byteOrder = bleWritable.getMByteOrder();
        }
        bleWritable.writeInt16(i, byteOrder);
    }

    public static /* synthetic */ void writeInt24$default(BleWritable bleWritable, int i, ByteOrder byteOrder, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeInt24");
        }
        if ((i2 & 2) != 0) {
            byteOrder = bleWritable.getMByteOrder();
        }
        bleWritable.writeInt24(i, byteOrder);
    }

    public static /* synthetic */ void writeInt32$default(BleWritable bleWritable, int i, ByteOrder byteOrder, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeInt32");
        }
        if ((i2 & 2) != 0) {
            byteOrder = bleWritable.getMByteOrder();
        }
        bleWritable.writeInt32(i, byteOrder);
    }

    public static /* synthetic */ void writeLong$default(BleWritable bleWritable, long j, ByteOrder byteOrder, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeLong");
        }
        if ((i & 2) != 0) {
            byteOrder = bleWritable.getMByteOrder();
        }
        bleWritable.writeLong(j, byteOrder);
    }

    public static /* synthetic */ void writeString$default(BleWritable bleWritable, String str, Charset charset, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeString");
        }
        if ((i & 2) != 0) {
            charset = Charset.defaultCharset();
            Intrinsics.checkNotNullExpressionValue(charset, "defaultCharset()");
        }
        bleWritable.writeString(str, charset);
    }

    public static /* synthetic */ void writeStringWithFix$default(BleWritable bleWritable, String str, int i, Charset charset, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeStringWithFix");
        }
        if ((i2 & 4) != 0) {
            charset = Charset.defaultCharset();
            Intrinsics.checkNotNullExpressionValue(charset, "defaultCharset()");
        }
        bleWritable.writeStringWithFix(str, i, charset);
    }

    public static /* synthetic */ void writeStringWithLimit$default(BleWritable bleWritable, String str, int i, Charset charset, boolean z, int i2, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeStringWithLimit");
        }
        if ((i2 & 4) != 0) {
            charset = Charset.defaultCharset();
            Intrinsics.checkNotNullExpressionValue(charset, "defaultCharset()");
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        bleWritable.writeStringWithLimit(str, i, charset, z);
    }

    public static /* synthetic */ void writeUInt32$default(BleWritable bleWritable, long j, ByteOrder byteOrder, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: writeUInt32");
        }
        if ((i & 2) != 0) {
            byteOrder = bleWritable.getMByteOrder();
        }
        bleWritable.writeUInt32(j, byteOrder);
    }

    @CallSuper
    public void encode() {
        resetOffset();
        setMBytes(new byte[getMLengthToWrite()]);
    }

    public int getMLengthToWrite() {
        return 0;
    }

    @Override // com.bestmafen.baseble.data.BleBuffer
    @NotNull
    public byte[] toByteArray() {
        encode();
        byte[] mBytes = getMBytes();
        Intrinsics.checkNotNull(mBytes);
        return mBytes;
    }

    public final void writeBoolean(boolean z) {
        writeIntN(z ? 1 : 0, 1);
    }

    public final void writeBytes(@Nullable byte[] bArr, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        if (bArr == null) {
            return;
        }
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            for (byte b : bArr) {
                writeInt8(b);
            }
            return;
        }
        for (Number number : ArraysKt___ArraysKt.reversed(bArr)) {
            writeInt8(number.byteValue());
        }
    }

    public final void writeDouble(double d, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        writeLong(Double.doubleToLongBits(d), order);
    }

    public final void writeFloat(float f, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        writeInt32(Float.floatToIntBits(f), order);
    }

    public final void writeInt16(int i, @NotNull ByteOrder order) {
        int i2;
        int i3;
        Intrinsics.checkNotNullParameter(order, "order");
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            i2 = (i >> 8) & 255;
            i3 = i & 255;
        } else {
            i2 = i & 255;
            i3 = (i >> 8) & 255;
        }
        writeInt8(i2);
        writeInt8(i3);
    }

    public final void writeInt24(int i, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            writeInt16(i >> 8, order);
            writeInt8(i & 255);
            return;
        }
        writeInt16(65535 & i, order);
        writeInt8(i >> 16);
    }

    public final void writeInt32(int i, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            writeInt16(i >> 16, order);
            writeInt16(i & 65535, order);
            return;
        }
        writeInt16(i & 65535, order);
        writeInt16(i >> 16, order);
    }

    public final void writeInt8(int i) {
        writeIntN(i, 8);
    }

    public final void writeIntN(int i, @IntRange(from = 1, to = 8) int i2) {
        if (i2 < 1 || i2 > 8 || outOfRange(i2)) {
            return;
        }
        int i3 = i & (((2 << i2) >> 1) - 1);
        if (getMPositions()[1] + i2 <= 8) {
            int i4 = 8 - (getMPositions()[1] + i2);
            byte[] mBytes = getMBytes();
            Intrinsics.checkNotNull(mBytes);
            int i5 = getMPositions()[0];
            byte[] mBytes2 = getMBytes();
            Intrinsics.checkNotNull(mBytes2);
            mBytes[i5] = (byte) (((byte) (i3 << i4)) | mBytes2[getMPositions()[0]]);
        } else {
            int i6 = (getMPositions()[1] + i2) - 8;
            byte[] mBytes3 = getMBytes();
            Intrinsics.checkNotNull(mBytes3);
            int i7 = getMPositions()[0];
            byte[] mBytes4 = getMBytes();
            Intrinsics.checkNotNull(mBytes4);
            mBytes3[i7] = (byte) (mBytes4[getMPositions()[0]] | ((byte) (i3 >> i6)));
            byte[] mBytes5 = getMBytes();
            Intrinsics.checkNotNull(mBytes5);
            byte[] mBytes6 = getMBytes();
            Intrinsics.checkNotNull(mBytes6);
            mBytes5[getMPositions()[0] + 1] = (byte) (((byte) ((i3 & (((2 << i6) >> 1) - 1)) << (8 - i6))) | mBytes6[getMPositions()[0] + 1]);
        }
        skip(i2);
    }

    public final void writeList(@Nullable List<? extends BleBuffer> list) {
        if (list != null) {
            for (BleBuffer bleBuffer : list) {
                writeObject(bleBuffer);
            }
        }
    }

    public final void writeLong(long j, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            writeInt32((int) (j >> 32), order);
            writeInt32((int) j, order);
            return;
        }
        writeInt32((int) j, order);
        writeInt32((int) (j >> 32), order);
    }

    public final void writeObject(@Nullable BleBuffer bleBuffer) {
        if (bleBuffer == null) {
            return;
        }
        writeBytes$default(this, bleBuffer.toByteArray(), null, 2, null);
    }

    public final void writeString(@Nullable String str, @NotNull Charset charSet) {
        Intrinsics.checkNotNullParameter(charSet, "charSet");
        if (str != null) {
            if (str.length() == 0) {
                return;
            }
            byte[] bytes = str.getBytes(charSet);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            writeBytes$default(this, bytes, null, 2, null);
        }
    }

    public final void writeStringWithFix(@Nullable String str, int i, @NotNull Charset charSet) {
        Intrinsics.checkNotNullParameter(charSet, "charSet");
        if (str != null) {
            if (!(str.length() == 0)) {
                byte[] bytes = str.getBytes(charSet);
                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                writeBytes$default(this, ArraysKt___ArraysKt.sliceArray(bytes, h.until(0, Math.min(bytes.length, i))), null, 2, null);
                if (bytes.length < i) {
                    int length = i - bytes.length;
                    byte[] bArr = new byte[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        bArr[i2] = 0;
                    }
                    writeBytes$default(this, bArr, null, 2, null);
                    return;
                }
                return;
            }
        }
        skip(i * 8);
    }

    public final void writeStringWithLimit(@Nullable String str, int i, @NotNull Charset charSet, boolean z) {
        Intrinsics.checkNotNullParameter(charSet, "charSet");
        if (str != null) {
            if (str.length() == 0) {
                return;
            }
            byte[] bytes = str.getBytes(charSet);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            if (bytes.length <= i) {
                writeBytes$default(this, bytes, null, 2, null);
            } else if (z) {
                for (int length = str.length() - 1; -1 < length; length--) {
                    StringBuilder sb = new StringBuilder();
                    String substring = str.substring(0, length);
                    Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                    sb.append(substring);
                    sb.append(Typography.ellipsis);
                    byte[] bytes2 = sb.toString().getBytes(charSet);
                    Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                    if (bytes2.length <= i) {
                        writeBytes$default(this, bytes2, null, 2, null);
                        return;
                    }
                }
            } else {
                writeBytes$default(this, ArraysKt___ArraysKt.sliceArray(bytes, h.until(0, Math.min(bytes.length, i))), null, 2, null);
            }
        }
    }

    public final void writeUInt32(long j, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        writeInt32((int) j, order);
    }
}
