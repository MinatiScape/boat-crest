package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleGSensorMotion extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 2;
    private short mMotion;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleGSensorMotion() {
        this((short) 0, 1, null);
    }

    public /* synthetic */ BleGSensorMotion(short s, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (short) 0 : s);
    }

    public static /* synthetic */ BleGSensorMotion copy$default(BleGSensorMotion bleGSensorMotion, short s, int i, Object obj) {
        if ((i & 1) != 0) {
            s = bleGSensorMotion.mMotion;
        }
        return bleGSensorMotion.copy(s);
    }

    public final short component1() {
        return this.mMotion;
    }

    @NotNull
    public final BleGSensorMotion copy(short s) {
        return new BleGSensorMotion(s);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mMotion = readInt16(LITTLE_ENDIAN);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleGSensorMotion) && this.mMotion == ((BleGSensorMotion) obj).mMotion;
    }

    public final short getMMotion() {
        return this.mMotion;
    }

    public int hashCode() {
        return Short.hashCode(this.mMotion);
    }

    public final void setMMotion(short s) {
        this.mMotion = s;
    }

    @NotNull
    public String toString() {
        return "BleGSensorMotion(mMotion=" + ((int) this.mMotion) + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleGSensorMotion(short s) {
        this.mMotion = s;
    }
}
