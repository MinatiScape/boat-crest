package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleGestureWake extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 5;
    @NotNull
    private BleTimeRange mBleTimeRange;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleGestureWake() {
        this(null, 1, null);
    }

    public /* synthetic */ BleGestureWake(BleTimeRange bleTimeRange, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new BleTimeRange(0, 0, 0, 0, 0, 31, null) : bleTimeRange);
    }

    public static /* synthetic */ BleGestureWake copy$default(BleGestureWake bleGestureWake, BleTimeRange bleTimeRange, int i, Object obj) {
        if ((i & 1) != 0) {
            bleTimeRange = bleGestureWake.mBleTimeRange;
        }
        return bleGestureWake.copy(bleTimeRange);
    }

    @NotNull
    public final BleTimeRange component1() {
        return this.mBleTimeRange;
    }

    @NotNull
    public final BleGestureWake copy(@NotNull BleTimeRange mBleTimeRange) {
        Intrinsics.checkNotNullParameter(mBleTimeRange, "mBleTimeRange");
        return new BleGestureWake(mBleTimeRange);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        BleReadable t = (BleReadable) BleTimeRange.class.newInstance();
        t.setMBytes(readBytes(5));
        t.decode();
        Intrinsics.checkNotNullExpressionValue(t, "t");
        this.mBleTimeRange = (BleTimeRange) t;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeObject(this.mBleTimeRange);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleGestureWake) && Intrinsics.areEqual(this.mBleTimeRange, ((BleGestureWake) obj).mBleTimeRange);
    }

    @NotNull
    public final BleTimeRange getMBleTimeRange() {
        return this.mBleTimeRange;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 5;
    }

    public int hashCode() {
        return this.mBleTimeRange.hashCode();
    }

    public final void setMBleTimeRange(@NotNull BleTimeRange bleTimeRange) {
        Intrinsics.checkNotNullParameter(bleTimeRange, "<set-?>");
        this.mBleTimeRange = bleTimeRange;
    }

    @NotNull
    public String toString() {
        return "BleGestureWake(mBleTimeRange=" + this.mBleTimeRange + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleGestureWake(@NotNull BleTimeRange mBleTimeRange) {
        Intrinsics.checkNotNullParameter(mBleTimeRange, "mBleTimeRange");
        this.mBleTimeRange = mBleTimeRange;
    }
}
