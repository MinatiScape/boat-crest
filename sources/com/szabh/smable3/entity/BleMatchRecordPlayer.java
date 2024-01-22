package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMatchRecordPlayer extends BleReadable implements Serializable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int NAME_LENGTH = 26;
    @NotNull
    private String mName;
    private int mNum;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleMatchRecordPlayer() {
        this(null, 0, 3, null);
    }

    public /* synthetic */ BleMatchRecordPlayer(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? 0 : i);
    }

    public static /* synthetic */ BleMatchRecordPlayer copy$default(BleMatchRecordPlayer bleMatchRecordPlayer, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bleMatchRecordPlayer.mName;
        }
        if ((i2 & 2) != 0) {
            i = bleMatchRecordPlayer.mNum;
        }
        return bleMatchRecordPlayer.copy(str, i);
    }

    @NotNull
    public final String component1() {
        return this.mName;
    }

    public final int component2() {
        return this.mNum;
    }

    @NotNull
    public final BleMatchRecordPlayer copy(@NotNull String mName, int i) {
        Intrinsics.checkNotNullParameter(mName, "mName");
        return new BleMatchRecordPlayer(mName, i);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mName = BleReadable.readString$default(this, 26, null, 2, null);
        this.mNum = readInt8();
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchRecordPlayer) {
            BleMatchRecordPlayer bleMatchRecordPlayer = (BleMatchRecordPlayer) obj;
            return Intrinsics.areEqual(this.mName, bleMatchRecordPlayer.mName) && this.mNum == bleMatchRecordPlayer.mNum;
        }
        return false;
    }

    @NotNull
    public final String getMName() {
        return this.mName;
    }

    public final int getMNum() {
        return this.mNum;
    }

    public int hashCode() {
        return (this.mName.hashCode() * 31) + Integer.hashCode(this.mNum);
    }

    public final void setMName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mName = str;
    }

    public final void setMNum(int i) {
        this.mNum = i;
    }

    @NotNull
    public String toString() {
        return "BleMatchRecordPlayer(mName=" + this.mName + ", mNum=" + this.mNum + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchRecordPlayer(@NotNull String mName, int i) {
        Intrinsics.checkNotNullParameter(mName, "mName");
        this.mName = mName;
        this.mNum = i;
    }
}
