package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleBloodOxygen extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    private int mTime;
    private int mValue;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleBloodOxygen() {
        this(0, 0, 3, null);
    }

    public /* synthetic */ BleBloodOxygen(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public static /* synthetic */ BleBloodOxygen copy$default(BleBloodOxygen bleBloodOxygen, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bleBloodOxygen.mTime;
        }
        if ((i3 & 2) != 0) {
            i2 = bleBloodOxygen.mValue;
        }
        return bleBloodOxygen.copy(i, i2);
    }

    public final int component1() {
        return this.mTime;
    }

    public final int component2() {
        return this.mValue;
    }

    @NotNull
    public final BleBloodOxygen copy(int i, int i2) {
        return new BleBloodOxygen(i, i2);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mTime = BleReadable.readInt32$default(this, null, 1, null);
        this.mValue = readInt8();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleBloodOxygen) {
            BleBloodOxygen bleBloodOxygen = (BleBloodOxygen) obj;
            return this.mTime == bleBloodOxygen.mTime && this.mValue == bleBloodOxygen.mValue;
        }
        return false;
    }

    public final int getMTime() {
        return this.mTime;
    }

    public final int getMValue() {
        return this.mValue;
    }

    public int hashCode() {
        return (Integer.hashCode(this.mTime) * 31) + Integer.hashCode(this.mValue);
    }

    public final void setMTime(int i) {
        this.mTime = i;
    }

    public final void setMValue(int i) {
        this.mValue = i;
    }

    @NotNull
    public String toString() {
        return "BleBloodOxygen(mTime=" + this.mTime + ", mValue=" + this.mValue + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleBloodOxygen(int i, int i2) {
        this.mTime = i;
        this.mValue = i2;
    }
}
