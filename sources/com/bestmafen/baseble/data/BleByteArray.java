package com.bestmafen.baseble.data;

import java.nio.ByteOrder;
import java.util.Arrays;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes.dex */
public class BleByteArray {
    @NotNull
    private transient ByteOrder mByteOrder;
    @Nullable
    private transient byte[] mBytes;
    @NotNull
    private final transient int[] mPositions;

    public BleByteArray() {
        this(null, null, 3, null);
    }

    public BleByteArray(@Nullable byte[] bArr, @NotNull ByteOrder mByteOrder) {
        Intrinsics.checkNotNullParameter(mByteOrder, "mByteOrder");
        this.mBytes = bArr;
        this.mByteOrder = mByteOrder;
        this.mPositions = new int[2];
    }

    public final int bitsLeft() {
        byte[] bArr = this.mBytes;
        if (bArr == null) {
            return 0;
        }
        Intrinsics.checkNotNull(bArr);
        return (bArr.length * 8) - bitsOffset();
    }

    public final int bitsOffset() {
        int[] iArr = this.mPositions;
        return (iArr[0] * 8) + iArr[1];
    }

    @NotNull
    public final ByteOrder getMByteOrder() {
        return this.mByteOrder;
    }

    @Nullable
    public final byte[] getMBytes() {
        return this.mBytes;
    }

    @NotNull
    public final int[] getMPositions() {
        return this.mPositions;
    }

    public final boolean outOfRange(int i) {
        int bitsOffset;
        if (this.mBytes != null && (bitsOffset = bitsOffset() + i) >= 0) {
            byte[] bArr = this.mBytes;
            Intrinsics.checkNotNull(bArr);
            return bitsOffset > bArr.length * 8;
        }
        return true;
    }

    public final void resetOffset() {
        Arrays.fill(this.mPositions, 0);
    }

    public final void setMByteOrder(@NotNull ByteOrder byteOrder) {
        Intrinsics.checkNotNullParameter(byteOrder, "<set-?>");
        this.mByteOrder = byteOrder;
    }

    public final void setMBytes(@Nullable byte[] bArr) {
        this.mBytes = bArr;
    }

    public final void skip(int i) {
        int bitsOffset = bitsOffset() + i;
        if (bitsOffset < 0) {
            bitsOffset = 0;
        }
        int[] iArr = this.mPositions;
        iArr[0] = bitsOffset / 8;
        iArr[1] = bitsOffset % 8;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ BleByteArray(byte[] r1, java.nio.ByteOrder r2, int r3, kotlin.jvm.internal.DefaultConstructorMarker r4) {
        /*
            r0 = this;
            r4 = r3 & 1
            if (r4 == 0) goto L5
            r1 = 0
        L5:
            r3 = r3 & 2
            if (r3 == 0) goto L10
            java.nio.ByteOrder r2 = java.nio.ByteOrder.BIG_ENDIAN
            java.lang.String r3 = "BIG_ENDIAN"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r3)
        L10:
            r0.<init>(r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bestmafen.baseble.data.BleByteArray.<init>(byte[], java.nio.ByteOrder, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }
}
