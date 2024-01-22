package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMatchPlayer extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 28;
    public static final int NAME_LENGTH = 25;
    private int hasYellowCard;
    private int isCaptain;
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

    public BleMatchPlayer() {
        this(null, 0, 0, 0, 15, null);
    }

    public /* synthetic */ BleMatchPlayer(String str, int i, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
        this((i4 & 1) != 0 ? "" : str, (i4 & 2) != 0 ? 0 : i, (i4 & 4) != 0 ? 0 : i2, (i4 & 8) != 0 ? 0 : i3);
    }

    public static /* synthetic */ BleMatchPlayer copy$default(BleMatchPlayer bleMatchPlayer, String str, int i, int i2, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = bleMatchPlayer.mName;
        }
        if ((i4 & 2) != 0) {
            i = bleMatchPlayer.mNum;
        }
        if ((i4 & 4) != 0) {
            i2 = bleMatchPlayer.hasYellowCard;
        }
        if ((i4 & 8) != 0) {
            i3 = bleMatchPlayer.isCaptain;
        }
        return bleMatchPlayer.copy(str, i, i2, i3);
    }

    @NotNull
    public final String component1() {
        return this.mName;
    }

    public final int component2() {
        return this.mNum;
    }

    public final int component3() {
        return this.hasYellowCard;
    }

    public final int component4() {
        return this.isCaptain;
    }

    @NotNull
    public final BleMatchPlayer copy(@NotNull String mName, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(mName, "mName");
        return new BleMatchPlayer(mName, i, i2, i3);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        BleWritable.writeStringWithFix$default(this, this.mName, 25, null, 4, null);
        writeInt8(this.mNum);
        writeInt8(this.hasYellowCard);
        writeInt8(this.isCaptain);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchPlayer) {
            BleMatchPlayer bleMatchPlayer = (BleMatchPlayer) obj;
            return Intrinsics.areEqual(this.mName, bleMatchPlayer.mName) && this.mNum == bleMatchPlayer.mNum && this.hasYellowCard == bleMatchPlayer.hasYellowCard && this.isCaptain == bleMatchPlayer.isCaptain;
        }
        return false;
    }

    public final int getHasYellowCard() {
        return this.hasYellowCard;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 28;
    }

    @NotNull
    public final String getMName() {
        return this.mName;
    }

    public final int getMNum() {
        return this.mNum;
    }

    public int hashCode() {
        return (((((this.mName.hashCode() * 31) + Integer.hashCode(this.mNum)) * 31) + Integer.hashCode(this.hasYellowCard)) * 31) + Integer.hashCode(this.isCaptain);
    }

    public final int isCaptain() {
        return this.isCaptain;
    }

    public final void setCaptain(int i) {
        this.isCaptain = i;
    }

    public final void setHasYellowCard(int i) {
        this.hasYellowCard = i;
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
        return "BleMatchPlayer(mName=" + this.mName + ", mNum=" + this.mNum + ", hasYellowCard=" + this.hasYellowCard + ", isCaptain=" + this.isCaptain + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchPlayer(@NotNull String mName, int i, int i2, int i3) {
        Intrinsics.checkNotNullParameter(mName, "mName");
        this.mName = mName;
        this.mNum = i;
        this.hasYellowCard = i2;
        this.isCaptain = i3;
    }
}
