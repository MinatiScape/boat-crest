package com.bestmafen.baseble.data;

import androidx.annotation.CallSuper;
import androidx.annotation.IntRange;
import androidx.exifinterface.media.ExifInterface;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.UShort;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes.dex */
public class BleReadable extends BleByteArray {
    private static final byte BYTE0 = 0;
    @NotNull
    public static final Companion Companion = new Companion(null);

    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ List ofList$default(Companion companion, byte[] bytes, int i, int i2, int i3, int i4, Object obj) {
            if ((i4 & 4) != 0) {
                i2 = 0;
            }
            if ((i4 & 8) != 0) {
                i3 = bytes.length;
            }
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            ArrayList arrayList = new ArrayList();
            int i5 = (i3 - i2) / i;
            if (i5 > 0) {
                int i6 = 0;
                while (i6 < i5) {
                    Companion companion2 = BleReadable.Companion;
                    i6++;
                    byte[] copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(bytes, (i * i6) + i2, (i * i6) + i2);
                    int length = copyOfRange.length;
                    Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                    Object newInstance = BleReadable.class.newInstance();
                    BleReadable bleReadable = (BleReadable) newInstance;
                    bleReadable.setMBytes(ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, 0, length));
                    bleReadable.decode();
                    Intrinsics.checkNotNullExpressionValue(newInstance, "T::class.java.newInstanc…it.decode()\n            }");
                    arrayList.add(bleReadable);
                }
            }
            return arrayList;
        }

        public static /* synthetic */ BleReadable ofObject$default(Companion companion, byte[] bytes, int i, int i2, int i3, Object obj) {
            if ((i3 & 2) != 0) {
                i = 0;
            }
            if ((i3 & 4) != 0) {
                i2 = bytes.length;
            }
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Object newInstance = BleReadable.class.newInstance();
            BleReadable bleReadable = (BleReadable) newInstance;
            bleReadable.setMBytes(ArraysKt___ArraysJvmKt.copyOfRange(bytes, i, i2));
            bleReadable.decode();
            Intrinsics.checkNotNullExpressionValue(newInstance, "T::class.java.newInstanc…it.decode()\n            }");
            return bleReadable;
        }

        public final /* synthetic */ <T extends BleReadable> List<T> ofList(byte[] bytes, int i, int i2, int i3) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            ArrayList arrayList = new ArrayList();
            int i4 = (i3 - i2) / i;
            if (i4 > 0) {
                int i5 = 0;
                while (i5 < i4) {
                    Companion companion = BleReadable.Companion;
                    i5++;
                    byte[] copyOfRange = ArraysKt___ArraysJvmKt.copyOfRange(bytes, (i * i5) + i2, (i * i5) + i2);
                    int length = copyOfRange.length;
                    Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
                    Object newInstance = BleReadable.class.newInstance();
                    BleReadable bleReadable = (BleReadable) newInstance;
                    bleReadable.setMBytes(ArraysKt___ArraysJvmKt.copyOfRange(copyOfRange, 0, length));
                    bleReadable.decode();
                    Intrinsics.checkNotNullExpressionValue(newInstance, "T::class.java.newInstanc…it.decode()\n            }");
                    arrayList.add(bleReadable);
                }
            }
            return arrayList;
        }

        public final /* synthetic */ <T extends BleReadable> T ofObject(byte[] bytes, int i, int i2) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            Object newInstance = BleReadable.class.newInstance();
            T t = (T) newInstance;
            t.setMBytes(ArraysKt___ArraysJvmKt.copyOfRange(bytes, i, i2));
            t.decode();
            Intrinsics.checkNotNullExpressionValue(newInstance, "T::class.java.newInstanc…it.decode()\n            }");
            return t;
        }
    }

    public BleReadable() {
        super(null, null, 3, null);
    }

    public static /* synthetic */ double readDouble$default(BleReadable bleReadable, ByteOrder byteOrder, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                byteOrder = bleReadable.getMByteOrder();
            }
            return bleReadable.readDouble(byteOrder);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readDouble");
    }

    public static /* synthetic */ float readFloat$default(BleReadable bleReadable, ByteOrder byteOrder, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                byteOrder = bleReadable.getMByteOrder();
            }
            return bleReadable.readFloat(byteOrder);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readFloat");
    }

    public static /* synthetic */ short readInt16$default(BleReadable bleReadable, ByteOrder byteOrder, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                byteOrder = bleReadable.getMByteOrder();
            }
            return bleReadable.readInt16(byteOrder);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readInt16");
    }

    public static /* synthetic */ int readInt24$default(BleReadable bleReadable, ByteOrder byteOrder, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                byteOrder = bleReadable.getMByteOrder();
            }
            return bleReadable.readInt24(byteOrder);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readInt24");
    }

    public static /* synthetic */ int readInt32$default(BleReadable bleReadable, ByteOrder byteOrder, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                byteOrder = bleReadable.getMByteOrder();
            }
            return bleReadable.readInt32(byteOrder);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readInt32");
    }

    public static /* synthetic */ long readInt64$default(BleReadable bleReadable, ByteOrder byteOrder, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                byteOrder = bleReadable.getMByteOrder();
            }
            return bleReadable.readInt64(byteOrder);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readInt64");
    }

    public static /* synthetic */ String readString$default(BleReadable bleReadable, int i, Charset charset, int i2, Object obj) {
        if (obj == null) {
            if ((i2 & 2) != 0) {
                charset = Charset.defaultCharset();
                Intrinsics.checkNotNullExpressionValue(charset, "defaultCharset()");
            }
            return bleReadable.readString(i, charset);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readString");
    }

    public static /* synthetic */ String readStringUtil$default(BleReadable bleReadable, byte b, Charset charset, int i, Object obj) {
        if (obj == null) {
            if ((i & 2) != 0) {
                charset = Charset.defaultCharset();
                Intrinsics.checkNotNullExpressionValue(charset, "defaultCharset()");
            }
            return bleReadable.readStringUtil(b, charset);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readStringUtil");
    }

    public static /* synthetic */ char readUInt16$default(BleReadable bleReadable, ByteOrder byteOrder, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                byteOrder = bleReadable.getMByteOrder();
            }
            return bleReadable.readUInt16(byteOrder);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readUInt16");
    }

    public static /* synthetic */ long readUInt32$default(BleReadable bleReadable, ByteOrder byteOrder, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                byteOrder = bleReadable.getMByteOrder();
            }
            return bleReadable.readUInt32(byteOrder);
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: readUInt32");
    }

    @CallSuper
    public void decode() {
        resetOffset();
    }

    public final boolean readBoolean() {
        return readIntN(1) == 1;
    }

    @NotNull
    public final byte[] readBytes(int i) {
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr[i2] = readInt8();
        }
        return bArr;
    }

    @NotNull
    public final byte[] readBytesUtil(byte b) {
        byte[] mBytes = getMBytes();
        if (mBytes != null) {
            int length = mBytes.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                byte b2 = mBytes[i];
                int i3 = i2 + 1;
                if (i2 >= getMPositions()[0] && b2 == b) {
                    byte[] readBytes = readBytes(i2 - getMPositions()[0]);
                    skip(8);
                    return readBytes;
                }
                i++;
                i2 = i3;
            }
        }
        return new byte[0];
    }

    public final double readDouble(@NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        return Double.longBitsToDouble(readInt64(order));
    }

    public final float readFloat(@NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        return Float.intBitsToFloat(readInt32(order));
    }

    public final short readInt16(@NotNull ByteOrder order) {
        byte readInt8;
        byte readInt82;
        Intrinsics.checkNotNullParameter(order, "order");
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            readInt82 = readInt8();
            readInt8 = readInt8();
        } else {
            readInt8 = readInt8();
            readInt82 = readInt8();
        }
        return (short) (((readInt82 & 255) << 8) | (readInt8 & 255));
    }

    public final int readInt24(@NotNull ByteOrder order) {
        char readUInt16;
        char readUInt8;
        Intrinsics.checkNotNullParameter(order, "order");
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            readUInt8 = readUInt8();
            readUInt16 = readUInt16(order);
        } else {
            readUInt16 = readUInt16(order);
            readUInt8 = readUInt8();
        }
        return readUInt16 | (readUInt8 << 16);
    }

    public final int readInt32(@NotNull ByteOrder order) {
        short readInt16;
        short s;
        Intrinsics.checkNotNullParameter(order, "order");
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            readInt16 = readInt16(order);
            s = readInt16(order);
        } else {
            short readInt162 = readInt16(order);
            readInt16 = readInt16(order);
            s = readInt162;
        }
        return (s & UShort.MAX_VALUE) | ((readInt16 & UShort.MAX_VALUE) << 16);
    }

    public final long readInt64(@NotNull ByteOrder order) {
        int readInt32;
        int i;
        Intrinsics.checkNotNullParameter(order, "order");
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            readInt32 = readInt32(order);
            i = readInt32(order);
        } else {
            int readInt322 = readInt32(order);
            readInt32 = readInt32(order);
            i = readInt322;
        }
        return ((readInt32 & 4294967295L) << 32) | (4294967295L & i);
    }

    public final byte readInt8() {
        return readIntN(8);
    }

    public final byte readIntN(@IntRange(from = 1, to = 8) int i) {
        int i2;
        if (i < 1 || i > 8 || outOfRange(i)) {
            return (byte) 0;
        }
        if (getMPositions()[1] + i <= 8) {
            byte[] mBytes = getMBytes();
            Intrinsics.checkNotNull(mBytes);
            i2 = (mBytes[getMPositions()[0]] >> ((8 - getMPositions()[1]) - i)) & (((2 << i) >> 1) - 1);
        } else {
            byte[] mBytes2 = getMBytes();
            Intrinsics.checkNotNull(mBytes2);
            int i3 = (mBytes2[getMPositions()[0]] & (((2 << (8 - getMPositions()[1])) >> 1) - 1)) << ((getMPositions()[1] + i) - 8);
            byte[] mBytes3 = getMBytes();
            Intrinsics.checkNotNull(mBytes3);
            i2 = ((mBytes3[getMPositions()[0] + 1] & 255) >> ((16 - getMPositions()[1]) - i)) | i3;
        }
        skip(i);
        return (byte) i2;
    }

    public final /* synthetic */ <T extends BleReadable> List<T> readList(int i, int i2) {
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < i; i3++) {
            Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
            BleReadable t = (BleReadable) BleReadable.class.newInstance();
            t.setMBytes(readBytes(i2));
            t.decode();
            Intrinsics.checkNotNullExpressionValue(t, "t");
            arrayList.add(t);
        }
        return arrayList;
    }

    public final /* synthetic */ <T extends BleReadable> T readObject(int i) {
        Intrinsics.reifiedOperationMarker(4, ExifInterface.GPS_DIRECTION_TRUE);
        T t = (T) BleReadable.class.newInstance();
        t.setMBytes(readBytes(i));
        t.decode();
        Intrinsics.checkNotNullExpressionValue(t, "t");
        return t;
    }

    @NotNull
    public final String readString(int i, @NotNull Charset charset) {
        String str;
        int i2;
        Intrinsics.checkNotNullParameter(charset, "charset");
        try {
            byte[] readBytes = readBytes(i);
            int i3 = 0;
            if (!Intrinsics.areEqual(charset, Charsets.UTF_8) && !Intrinsics.areEqual(charset, Charsets.US_ASCII)) {
                if (Intrinsics.areEqual(charset, Charsets.UTF_16) || Intrinsics.areEqual(charset, Charsets.UTF_16BE) || Intrinsics.areEqual(charset, Charsets.UTF_16LE)) {
                    int i4 = i / 2;
                    while (true) {
                        if (i3 >= i4) {
                            i2 = -1;
                            break;
                        }
                        i2 = i3 * 2;
                        if (readBytes[i2] == 0 && readBytes[i2 + 1] == 0) {
                            break;
                        }
                        i3++;
                    }
                    if (i2 == -1) {
                        str = new String(readBytes, charset);
                    } else {
                        byte[] copyOf = Arrays.copyOf(readBytes, i2);
                        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                        str = new String(copyOf, charset);
                    }
                    return str;
                }
                return "";
            }
            int length = readBytes.length;
            int i5 = 0;
            while (true) {
                if (i5 >= length) {
                    i5 = -1;
                    break;
                }
                if (readBytes[i5] == 0) {
                    break;
                }
                i5++;
            }
            if (i5 == -1) {
                str = new String(readBytes, charset);
            } else {
                byte[] copyOf2 = Arrays.copyOf(readBytes, i5);
                Intrinsics.checkNotNullExpressionValue(copyOf2, "copyOf(this, newSize)");
                str = new String(copyOf2, charset);
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @NotNull
    public final String readStringUtil(byte b, @NotNull Charset charset) {
        Intrinsics.checkNotNullParameter(charset, "charset");
        byte[] readBytesUtil = readBytesUtil(b);
        return readBytesUtil.length == 0 ? "" : new String(readBytesUtil, charset);
    }

    public final char readUInt16(@NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        return (char) readInt16(order);
    }

    public final long readUInt32(@NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        return readInt32(order) & 4294967295L;
    }

    public final char readUInt8() {
        return (char) (readIntN(8) & 255);
    }
}
