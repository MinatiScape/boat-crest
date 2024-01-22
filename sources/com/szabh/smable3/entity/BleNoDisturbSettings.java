package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleNoDisturbSettings extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 16;
    @NotNull
    private BleTimeRange mBleTimeRange1;
    @NotNull
    private BleTimeRange mBleTimeRange2;
    @NotNull
    private BleTimeRange mBleTimeRange3;
    private int mEnabled;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleNoDisturbSettings() {
        this(0, null, null, null, 15, null);
    }

    public /* synthetic */ BleNoDisturbSettings(int i, BleTimeRange bleTimeRange, BleTimeRange bleTimeRange2, BleTimeRange bleTimeRange3, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? new BleTimeRange(0, 0, 0, 0, 0, 31, null) : bleTimeRange, (i2 & 4) != 0 ? new BleTimeRange(0, 0, 0, 0, 0, 31, null) : bleTimeRange2, (i2 & 8) != 0 ? new BleTimeRange(0, 0, 0, 0, 0, 31, null) : bleTimeRange3);
    }

    public static /* synthetic */ BleNoDisturbSettings copy$default(BleNoDisturbSettings bleNoDisturbSettings, int i, BleTimeRange bleTimeRange, BleTimeRange bleTimeRange2, BleTimeRange bleTimeRange3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleNoDisturbSettings.mEnabled;
        }
        if ((i2 & 2) != 0) {
            bleTimeRange = bleNoDisturbSettings.mBleTimeRange1;
        }
        if ((i2 & 4) != 0) {
            bleTimeRange2 = bleNoDisturbSettings.mBleTimeRange2;
        }
        if ((i2 & 8) != 0) {
            bleTimeRange3 = bleNoDisturbSettings.mBleTimeRange3;
        }
        return bleNoDisturbSettings.copy(i, bleTimeRange, bleTimeRange2, bleTimeRange3);
    }

    public final int component1() {
        return this.mEnabled;
    }

    @NotNull
    public final BleTimeRange component2() {
        return this.mBleTimeRange1;
    }

    @NotNull
    public final BleTimeRange component3() {
        return this.mBleTimeRange2;
    }

    @NotNull
    public final BleTimeRange component4() {
        return this.mBleTimeRange3;
    }

    @NotNull
    public final BleNoDisturbSettings copy(int i, @NotNull BleTimeRange mBleTimeRange1, @NotNull BleTimeRange mBleTimeRange2, @NotNull BleTimeRange mBleTimeRange3) {
        Intrinsics.checkNotNullParameter(mBleTimeRange1, "mBleTimeRange1");
        Intrinsics.checkNotNullParameter(mBleTimeRange2, "mBleTimeRange2");
        Intrinsics.checkNotNullParameter(mBleTimeRange3, "mBleTimeRange3");
        return new BleNoDisturbSettings(i, mBleTimeRange1, mBleTimeRange2, mBleTimeRange3);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mEnabled = readUInt8();
        BleReadable t = (BleReadable) BleTimeRange.class.newInstance();
        t.setMBytes(readBytes(5));
        t.decode();
        Intrinsics.checkNotNullExpressionValue(t, "t");
        this.mBleTimeRange1 = (BleTimeRange) t;
        BleReadable t2 = (BleReadable) BleTimeRange.class.newInstance();
        t2.setMBytes(readBytes(5));
        t2.decode();
        Intrinsics.checkNotNullExpressionValue(t2, "t");
        this.mBleTimeRange2 = (BleTimeRange) t2;
        BleReadable t3 = (BleReadable) BleTimeRange.class.newInstance();
        t3.setMBytes(readBytes(5));
        t3.decode();
        Intrinsics.checkNotNullExpressionValue(t3, "t");
        this.mBleTimeRange3 = (BleTimeRange) t3;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mEnabled);
        writeObject(this.mBleTimeRange1);
        writeObject(this.mBleTimeRange2);
        writeObject(this.mBleTimeRange3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleNoDisturbSettings) {
            BleNoDisturbSettings bleNoDisturbSettings = (BleNoDisturbSettings) obj;
            return this.mEnabled == bleNoDisturbSettings.mEnabled && Intrinsics.areEqual(this.mBleTimeRange1, bleNoDisturbSettings.mBleTimeRange1) && Intrinsics.areEqual(this.mBleTimeRange2, bleNoDisturbSettings.mBleTimeRange2) && Intrinsics.areEqual(this.mBleTimeRange3, bleNoDisturbSettings.mBleTimeRange3);
        }
        return false;
    }

    @NotNull
    public final BleTimeRange getMBleTimeRange1() {
        return this.mBleTimeRange1;
    }

    @NotNull
    public final BleTimeRange getMBleTimeRange2() {
        return this.mBleTimeRange2;
    }

    @NotNull
    public final BleTimeRange getMBleTimeRange3() {
        return this.mBleTimeRange3;
    }

    public final int getMEnabled() {
        return this.mEnabled;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 16;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mEnabled) * 31) + this.mBleTimeRange1.hashCode()) * 31) + this.mBleTimeRange2.hashCode()) * 31) + this.mBleTimeRange3.hashCode();
    }

    public final void setMBleTimeRange1(@NotNull BleTimeRange bleTimeRange) {
        Intrinsics.checkNotNullParameter(bleTimeRange, "<set-?>");
        this.mBleTimeRange1 = bleTimeRange;
    }

    public final void setMBleTimeRange2(@NotNull BleTimeRange bleTimeRange) {
        Intrinsics.checkNotNullParameter(bleTimeRange, "<set-?>");
        this.mBleTimeRange2 = bleTimeRange;
    }

    public final void setMBleTimeRange3(@NotNull BleTimeRange bleTimeRange) {
        Intrinsics.checkNotNullParameter(bleTimeRange, "<set-?>");
        this.mBleTimeRange3 = bleTimeRange;
    }

    public final void setMEnabled(int i) {
        this.mEnabled = i;
    }

    @NotNull
    public String toString() {
        return "BleNoDisturbSettings(mEnabled=" + this.mEnabled + ", mBleTimeRange1=" + this.mBleTimeRange1 + ", mBleTimeRange2=" + this.mBleTimeRange2 + ", mBleTimeRange3=" + this.mBleTimeRange3 + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleNoDisturbSettings(int i, @NotNull BleTimeRange mBleTimeRange1, @NotNull BleTimeRange mBleTimeRange2, @NotNull BleTimeRange mBleTimeRange3) {
        Intrinsics.checkNotNullParameter(mBleTimeRange1, "mBleTimeRange1");
        Intrinsics.checkNotNullParameter(mBleTimeRange2, "mBleTimeRange2");
        Intrinsics.checkNotNullParameter(mBleTimeRange3, "mBleTimeRange3");
        this.mEnabled = i;
        this.mBleTimeRange1 = mBleTimeRange1;
        this.mBleTimeRange2 = mBleTimeRange2;
        this.mBleTimeRange3 = mBleTimeRange3;
    }
}
