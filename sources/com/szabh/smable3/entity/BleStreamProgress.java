package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleStreamProgress extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 9;
    private int mCompleted;
    private int mErrorCode;
    private int mStatus;
    private int mTotal;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleStreamProgress() {
        this(0, 0, 0, 0, 15, null);
    }

    public /* synthetic */ BleStreamProgress(int i, int i2, int i3, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this((i5 & 1) != 0 ? 0 : i, (i5 & 2) != 0 ? 0 : i2, (i5 & 4) != 0 ? 0 : i3, (i5 & 8) != 0 ? 0 : i4);
    }

    public static /* synthetic */ BleStreamProgress copy$default(BleStreamProgress bleStreamProgress, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = bleStreamProgress.mStatus;
        }
        if ((i5 & 2) != 0) {
            i2 = bleStreamProgress.mErrorCode;
        }
        if ((i5 & 4) != 0) {
            i3 = bleStreamProgress.mTotal;
        }
        if ((i5 & 8) != 0) {
            i4 = bleStreamProgress.mCompleted;
        }
        return bleStreamProgress.copy(i, i2, i3, i4);
    }

    public final int component1() {
        return this.mStatus;
    }

    public final int component2() {
        return this.mErrorCode;
    }

    public final int component3() {
        return this.mTotal;
    }

    public final int component4() {
        return this.mCompleted;
    }

    @NotNull
    public final BleStreamProgress copy(int i, int i2, int i3, int i4) {
        return new BleStreamProgress(i, i2, i3, i4);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mStatus = readIntN(4);
        this.mErrorCode = readIntN(4);
        this.mTotal = BleReadable.readInt32$default(this, null, 1, null);
        this.mCompleted = BleReadable.readInt32$default(this, null, 1, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleStreamProgress) {
            BleStreamProgress bleStreamProgress = (BleStreamProgress) obj;
            return this.mStatus == bleStreamProgress.mStatus && this.mErrorCode == bleStreamProgress.mErrorCode && this.mTotal == bleStreamProgress.mTotal && this.mCompleted == bleStreamProgress.mCompleted;
        }
        return false;
    }

    public final int getMCompleted() {
        return this.mCompleted;
    }

    public final int getMErrorCode() {
        return this.mErrorCode;
    }

    public final int getMStatus() {
        return this.mStatus;
    }

    public final int getMTotal() {
        return this.mTotal;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.mStatus) * 31) + Integer.hashCode(this.mErrorCode)) * 31) + Integer.hashCode(this.mTotal)) * 31) + Integer.hashCode(this.mCompleted);
    }

    public final void setMCompleted(int i) {
        this.mCompleted = i;
    }

    public final void setMErrorCode(int i) {
        this.mErrorCode = i;
    }

    public final void setMStatus(int i) {
        this.mStatus = i;
    }

    public final void setMTotal(int i) {
        this.mTotal = i;
    }

    @NotNull
    public String toString() {
        return "BleStreamProgress(mStatus=" + this.mStatus + ", mErrorCode=" + this.mErrorCode + ", mTotal=" + this.mTotal + ", mCompleted=" + this.mCompleted + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleStreamProgress(int i, int i2, int i3, int i4) {
        this.mStatus = i;
        this.mErrorCode = i2;
        this.mTotal = i3;
        this.mCompleted = i4;
    }
}
