package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleHrWarningSettings extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 4;
    private int mHighSwitch;
    private int mHighValue;
    private int mLowSwitch;
    private int mLowValue;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleHrWarningSettings() {
        this(0, 0, 0, 0, 15, null);
    }

    public /* synthetic */ BleHrWarningSettings(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }

    public static /* synthetic */ BleHrWarningSettings copy$default(BleHrWarningSettings bleHrWarningSettings, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = bleHrWarningSettings.mHighSwitch;
        }
        if ((i5 & 2) != 0) {
            i2 = bleHrWarningSettings.mHighValue;
        }
        if ((i5 & 4) != 0) {
            i3 = bleHrWarningSettings.mLowSwitch;
        }
        if ((i5 & 8) != 0) {
            i4 = bleHrWarningSettings.mLowValue;
        }
        return bleHrWarningSettings.copy(i, i2, i3, i4);
    }

    public final int component1() {
        return this.mHighSwitch;
    }

    public final int component2() {
        return this.mHighValue;
    }

    public final int component3() {
        return this.mLowSwitch;
    }

    public final int component4() {
        return this.mLowValue;
    }

    @NotNull
    public final BleHrWarningSettings copy(int i, int i2, int i3, int i4) {
        return new BleHrWarningSettings(i, i2, i3, i4);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mHighSwitch = readInt8();
        this.mHighValue = readInt8();
        this.mLowSwitch = readUInt8();
        this.mLowValue = readUInt8();
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mHighSwitch);
        writeInt8(this.mHighValue);
        writeInt8(this.mLowSwitch);
        writeInt8(this.mLowValue);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleHrWarningSettings) {
            BleHrWarningSettings bleHrWarningSettings = (BleHrWarningSettings) obj;
            return this.mHighSwitch == bleHrWarningSettings.mHighSwitch && this.mHighValue == bleHrWarningSettings.mHighValue && this.mLowSwitch == bleHrWarningSettings.mLowSwitch && this.mLowValue == bleHrWarningSettings.mLowValue;
        }
        return false;
    }

    public final int getMHighSwitch() {
        return this.mHighSwitch;
    }

    public final int getMHighValue() {
        return this.mHighValue;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 4;
    }

    public final int getMLowSwitch() {
        return this.mLowSwitch;
    }

    public final int getMLowValue() {
        return this.mLowValue;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mHighSwitch) * 31) + Integer.hashCode(this.mHighValue)) * 31) + Integer.hashCode(this.mLowSwitch)) * 31) + Integer.hashCode(this.mLowValue);
    }

    public final void setMHighSwitch(int i) {
        this.mHighSwitch = i;
    }

    public final void setMHighValue(int i) {
        this.mHighValue = i;
    }

    public final void setMLowSwitch(int i) {
        this.mLowSwitch = i;
    }

    public final void setMLowValue(int i) {
        this.mLowValue = i;
    }

    @NotNull
    public String toString() {
        return "BleHrWarningSettings(mHighSwitch=" + this.mHighSwitch + ", mHighValue=" + this.mHighValue + ", mLowSwitch=" + this.mLowSwitch + ", mLowValue=" + this.mLowValue + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleHrWarningSettings(int i, int i2, int i3, int i4) {
        this.mHighSwitch = i;
        this.mHighValue = i2;
        this.mLowSwitch = i3;
        this.mLowValue = i4;
    }
}
