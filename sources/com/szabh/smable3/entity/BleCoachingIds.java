package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
/* loaded from: classes12.dex */
public final class BleCoachingIds extends BleReadable {
    private int mCount;
    @NotNull
    private List<Integer> mIds;

    public BleCoachingIds() {
        this(0, null, 3, null);
    }

    public /* synthetic */ BleCoachingIds(int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? 0 : i, (i2 & 2) != 0 ? new ArrayList() : list);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mIds.clear();
        byte readInt8 = readInt8();
        this.mCount = readInt8;
        for (int i = 0; i < readInt8; i++) {
            this.mIds.add(Integer.valueOf(readInt8()));
        }
    }

    public final int getMCount() {
        return this.mCount;
    }

    @NotNull
    public final List<Integer> getMIds() {
        return this.mIds;
    }

    public final void setMCount(int i) {
        this.mCount = i;
    }

    public final void setMIds(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mIds = list;
    }

    @NotNull
    public String toString() {
        return "BleCoachingIds(mCount=" + this.mCount + ", mIds=" + this.mIds + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleCoachingIds(int i, @NotNull List<Integer> mIds) {
        Intrinsics.checkNotNullParameter(mIds, "mIds");
        this.mCount = i;
        this.mIds = mIds;
    }
}
