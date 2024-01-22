package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleGSensorRaw extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    private short mX;
    private short mY;
    private short mZ;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleGSensorRaw() {
        this((short) 0, (short) 0, (short) 0, 7, null);
    }

    public /* synthetic */ BleGSensorRaw(short s, short s2, short s3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (short) 0 : s, (i & 2) != 0 ? (short) 0 : s2, (i & 4) != 0 ? (short) 0 : s3);
    }

    public static /* synthetic */ BleGSensorRaw copy$default(BleGSensorRaw bleGSensorRaw, short s, short s2, short s3, int i, Object obj) {
        if ((i & 1) != 0) {
            s = bleGSensorRaw.mX;
        }
        if ((i & 2) != 0) {
            s2 = bleGSensorRaw.mY;
        }
        if ((i & 4) != 0) {
            s3 = bleGSensorRaw.mZ;
        }
        return bleGSensorRaw.copy(s, s2, s3);
    }

    public final short component1() {
        return this.mX;
    }

    public final short component2() {
        return this.mY;
    }

    public final short component3() {
        return this.mZ;
    }

    @NotNull
    public final BleGSensorRaw copy(short s, short s2, short s3) {
        return new BleGSensorRaw(s, s2, s3);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mX = readInt16(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mY = readInt16(LITTLE_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mZ = readInt16(LITTLE_ENDIAN);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleGSensorRaw) {
            BleGSensorRaw bleGSensorRaw = (BleGSensorRaw) obj;
            return this.mX == bleGSensorRaw.mX && this.mY == bleGSensorRaw.mY && this.mZ == bleGSensorRaw.mZ;
        }
        return false;
    }

    public final short getMX() {
        return this.mX;
    }

    public final short getMY() {
        return this.mY;
    }

    public final short getMZ() {
        return this.mZ;
    }

    public int hashCode() {
        return (((Short.hashCode(this.mX) * 31) + Short.hashCode(this.mY)) * 31) + Short.hashCode(this.mZ);
    }

    public final void setMX(short s) {
        this.mX = s;
    }

    public final void setMY(short s) {
        this.mY = s;
    }

    public final void setMZ(short s) {
        this.mZ = s;
    }

    @NotNull
    public String toString() {
        return "BleGSensorRaw(mX=" + ((int) this.mX) + ", mY=" + ((int) this.mY) + ", mZ=" + ((int) this.mZ) + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleGSensorRaw(short s, short s2, short s3) {
        this.mX = s;
        this.mY = s2;
        this.mZ = s3;
    }
}
