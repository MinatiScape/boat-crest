package com.bestmafen.baseble.data;

import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.ArraysKt___ArraysJvmKt;
import kotlin.collections.ArraysKt___ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public final class ByteArrayExtKt {
    @NotNull
    public static final byte[] append(@Nullable byte[] bArr, @Nullable byte[] bArr2) {
        if (bArr == null && bArr2 == null) {
            return new byte[0];
        }
        if (bArr != null) {
            return bArr2 == null ? bArr : ArraysKt___ArraysJvmKt.plus(bArr, bArr2);
        }
        Intrinsics.checkNotNull(bArr2);
        return bArr2;
    }

    @NotNull
    public static final byte[] byteArrayOfBoolean(boolean z) {
        return new byte[]{z ? (byte) 1 : (byte) 0};
    }

    @NotNull
    public static final byte[] byteArrayOfInt16(int i, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        byte[] bArr = new byte[2];
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            bArr[0] = (byte) ((i >> 8) & 255);
            bArr[1] = (byte) (i & 255);
        } else {
            bArr[1] = (byte) ((i >> 8) & 255);
            bArr[0] = (byte) (i & 255);
        }
        return bArr;
    }

    @NotNull
    public static final byte[] byteArrayOfInt24(int i, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        byte[] bArr = new byte[3];
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            bArr[0] = (byte) ((i >> 16) & 255);
            bArr[1] = (byte) ((i >> 8) & 255);
            bArr[2] = (byte) (i & 255);
        } else {
            bArr[2] = (byte) ((i >> 16) & 255);
            bArr[1] = (byte) ((i >> 8) & 255);
            bArr[0] = (byte) (i & 255);
        }
        return bArr;
    }

    @NotNull
    public static final byte[] byteArrayOfInt32(int i, @NotNull ByteOrder order) {
        Intrinsics.checkNotNullParameter(order, "order");
        byte[] bArr = new byte[4];
        if (Intrinsics.areEqual(order, ByteOrder.BIG_ENDIAN)) {
            bArr[0] = (byte) ((i >> 24) & 255);
            bArr[1] = (byte) ((i >> 16) & 255);
            bArr[2] = (byte) ((i >> 8) & 255);
            bArr[3] = (byte) (i & 255);
        } else {
            bArr[0] = (byte) (i & 255);
            bArr[1] = (byte) ((i >> 8) & 255);
            bArr[2] = (byte) ((i >> 16) & 255);
            bArr[3] = (byte) ((i >> 24) & 255);
        }
        return bArr;
    }

    @NotNull
    public static final byte[] byteArrayOfInt8(int i) {
        return new byte[]{(byte) i};
    }

    public static final int getInt(@NotNull byte[] bArr, int i, int i2, @NotNull ByteOrder byteOrder) {
        byte b;
        int i3;
        int i4;
        byte b2;
        Intrinsics.checkNotNullParameter(bArr, "<this>");
        Intrinsics.checkNotNullParameter(byteOrder, "byteOrder");
        if (i2 >= 1 && i2 <= 4) {
            if (i + i2 > bArr.length) {
                return 0;
            }
            if (!Intrinsics.areEqual(byteOrder, ByteOrder.BIG_ENDIAN)) {
                if (i2 == 1) {
                    b = bArr[i];
                    return b & 255;
                }
                if (i2 == 2) {
                    i3 = bArr[i] & 255;
                    i4 = (bArr[i + 1] & 255) << 8;
                } else if (i2 == 3) {
                    i3 = (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8);
                    i4 = (bArr[i + 2] & 255) << 16;
                } else if (i2 != 4) {
                    return 0;
                } else {
                    i3 = (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
                    i4 = (bArr[i + 3] & 255) << 24;
                }
                return i3 | i4;
            } else if (i2 == 1) {
                b = bArr[i];
                return b & 255;
            } else {
                if (i2 == 2) {
                    i3 = (bArr[i] & 255) << 8;
                    b2 = bArr[i + 1];
                } else if (i2 == 3) {
                    i3 = ((bArr[i] & 255) << 16) | ((bArr[i + 1] & 255) << 8);
                    b2 = bArr[i + 2];
                } else if (i2 != 4) {
                    return 0;
                } else {
                    i3 = ((bArr[i] & 255) << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
                    b2 = bArr[i + 3];
                }
                i4 = b2 & 255;
                return i3 | i4;
            }
        }
        throw new IllegalArgumentException("length must be in [1, 4]".toString());
    }

    public static /* synthetic */ int getInt$default(byte[] bArr, int i, int i2, ByteOrder BIG_ENDIAN, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            BIG_ENDIAN = ByteOrder.BIG_ENDIAN;
            Intrinsics.checkNotNullExpressionValue(BIG_ENDIAN, "BIG_ENDIAN");
        }
        return getInt(bArr, i, i2, BIG_ENDIAN);
    }

    @NotNull
    public static final String getMHexString(@Nullable byte[] bArr) {
        return bArr == null ? "" : ArraysKt___ArraysKt.joinToString$default(bArr, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) new Function1<Byte, CharSequence>() { // from class: com.bestmafen.baseble.data.ByteArrayExtKt$mHexString$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Byte b) {
                return invoke(b.byteValue());
            }

            @NotNull
            public final CharSequence invoke(byte b) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String format = String.format("0x%02X", Arrays.copyOf(new Object[]{Byte.valueOf(b)}, 1));
                Intrinsics.checkNotNullExpressionValue(format, "format(format, *args)");
                return format;
            }
        }, 30, (Object) null);
    }

    @NotNull
    public static final List<byte[]> splitWith0(@Nullable byte[] bArr, int i) {
        ArrayList arrayList = new ArrayList();
        if (bArr != null) {
            int length = bArr.length;
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            int i4 = 0;
            while (i2 < length) {
                byte b = bArr[i2];
                int i5 = i3 + 1;
                if (i3 >= i) {
                    if (b != 0) {
                        if (!z) {
                            z = true;
                            i4 = i3;
                        }
                        if (i3 == ArraysKt___ArraysKt.getLastIndex(bArr) && z) {
                            arrayList.add(ArraysKt___ArraysJvmKt.copyOfRange(bArr, i4, i5));
                        }
                    } else if (z) {
                        arrayList.add(ArraysKt___ArraysJvmKt.copyOfRange(bArr, i4, i3));
                        z = false;
                    }
                }
                i2++;
                i3 = i5;
            }
        }
        return arrayList;
    }

    public static /* synthetic */ List splitWith0$default(byte[] bArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 0;
        }
        return splitWith0(bArr, i);
    }
}
