package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleTemperature extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 6;
    private int mTemperature;
    private int mTime;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleTemperature() {
        this(0, 0, 3, null);
    }

    public /* synthetic */ BleTemperature(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2);
    }

    public static /* synthetic */ BleTemperature copy$default(BleTemperature bleTemperature, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bleTemperature.mTime;
        }
        if ((i3 & 2) != 0) {
            i2 = bleTemperature.mTemperature;
        }
        return bleTemperature.copy(i, i2);
    }

    public final int component1() {
        return this.mTime;
    }

    public final int component2() {
        return this.mTemperature;
    }

    @NotNull
    public final BleTemperature copy(int i, int i2) {
        return new BleTemperature(i, i2);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mTime = BleReadable.readInt32$default(this, null, 1, null);
        this.mTemperature = BleReadable.readInt16$default(this, null, 1, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleTemperature) {
            BleTemperature bleTemperature = (BleTemperature) obj;
            return this.mTime == bleTemperature.mTime && this.mTemperature == bleTemperature.mTemperature;
        }
        return false;
    }

    public final int getMTemperature() {
        return this.mTemperature;
    }

    public final int getMTime() {
        return this.mTime;
    }

    public int hashCode() {
        return (Integer.hashCode(this.mTime) * 31) + Integer.hashCode(this.mTemperature);
    }

    public final void setMTemperature(int i) {
        this.mTemperature = i;
    }

    public final void setMTime(int i) {
        this.mTime = i;
    }

    @NotNull
    public String toString() {
        return "BleTemperature(mTime=" + this.mTime + ", mTemperature=" + this.mTemperature + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleTemperature(int i, int i2) {
        this.mTime = i;
        this.mTemperature = i2;
    }
}
