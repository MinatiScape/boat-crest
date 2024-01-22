package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleHRRaw extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 4;
    private int mPpg;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleHRRaw() {
        this(0, 1, null);
    }

    public /* synthetic */ BleHRRaw(int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i);
    }

    public static /* synthetic */ BleHRRaw copy$default(BleHRRaw bleHRRaw, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleHRRaw.mPpg;
        }
        return bleHRRaw.copy(i);
    }

    public final int component1() {
        return this.mPpg;
    }

    @NotNull
    public final BleHRRaw copy(int i) {
        return new BleHRRaw(i);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mPpg = readInt32(LITTLE_ENDIAN);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleHRRaw) && this.mPpg == ((BleHRRaw) obj).mPpg;
    }

    public final int getMPpg() {
        return this.mPpg;
    }

    public int hashCode() {
        return Integer.hashCode(this.mPpg);
    }

    public final void setMPpg(int i) {
        this.mPpg = i;
    }

    @NotNull
    public String toString() {
        return "BleHRRaw(mPpg=" + this.mPpg + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleHRRaw(int i) {
        this.mPpg = i;
    }
}
