package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMatchPlayerSettings extends BleWritable {
    private int mMatchSetType;
    @NotNull
    private List<BleMatchPlayer> mPlayerList;

    public BleMatchPlayerSettings() {
        this(0, null, 3, null);
    }

    public /* synthetic */ BleMatchPlayerSettings(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 4 : i, (i2 & 2) != 0 ? new ArrayList() : list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BleMatchPlayerSettings copy$default(BleMatchPlayerSettings bleMatchPlayerSettings, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = bleMatchPlayerSettings.mMatchSetType;
        }
        if ((i2 & 2) != 0) {
            list = bleMatchPlayerSettings.mPlayerList;
        }
        return bleMatchPlayerSettings.copy(i, list);
    }

    public final int component1() {
        return this.mMatchSetType;
    }

    @NotNull
    public final List<BleMatchPlayer> component2() {
        return this.mPlayerList;
    }

    @NotNull
    public final BleMatchPlayerSettings copy(int i, @NotNull List<BleMatchPlayer> mPlayerList) {
        Intrinsics.checkNotNullParameter(mPlayerList, "mPlayerList");
        return new BleMatchPlayerSettings(i, mPlayerList);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mMatchSetType);
        writeInt8(this.mPlayerList.size());
        for (BleMatchPlayer bleMatchPlayer : this.mPlayerList) {
            writeObject(bleMatchPlayer);
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchPlayerSettings) {
            BleMatchPlayerSettings bleMatchPlayerSettings = (BleMatchPlayerSettings) obj;
            return this.mMatchSetType == bleMatchPlayerSettings.mMatchSetType && Intrinsics.areEqual(this.mPlayerList, bleMatchPlayerSettings.mPlayerList);
        }
        return false;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return (this.mPlayerList.size() * 28) + 2;
    }

    public final int getMMatchSetType() {
        return this.mMatchSetType;
    }

    @NotNull
    public final List<BleMatchPlayer> getMPlayerList() {
        return this.mPlayerList;
    }

    public int hashCode() {
        return (Integer.hashCode(this.mMatchSetType) * 31) + this.mPlayerList.hashCode();
    }

    public final void setMMatchSetType(int i) {
        this.mMatchSetType = i;
    }

    public final void setMPlayerList(@NotNull List<BleMatchPlayer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mPlayerList = list;
    }

    @NotNull
    public String toString() {
        return "BleMatchPlayerSettings(mMatchSetType=" + this.mMatchSetType + ", mPlayerList=" + this.mPlayerList + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchPlayerSettings(int i, @NotNull List<BleMatchPlayer> mPlayerList) {
        Intrinsics.checkNotNullParameter(mPlayerList, "mPlayerList");
        this.mMatchSetType = i;
        this.mPlayerList = mPlayerList;
    }
}
