package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMatchRecord extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 920;
    public static final int PROFILE1 = 1;
    public static final int PROFILE2 = 2;
    public static final int TRAINING = 3;
    public static final int YOUTH = 0;
    @NotNull
    private List<BleMatchLog> mLogList;
    private int mLogListSize;
    @NotNull
    private BleMatchPeriod mPeriod;
    @NotNull
    private List<BleMatchPeriod> mPeriodList;
    private int mPeriodListSize;
    private int mStart;
    private int mType;
    private int mUndefined;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleMatchRecord() {
        this(0, 0, 0, 0, 0, null, null, null, 255, null);
    }

    public /* synthetic */ BleMatchRecord(int i, int i2, int i3, int i4, int i5, BleMatchPeriod bleMatchPeriod, List list, List list2, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? 0 : i, (i6 & 2) != 0 ? 0 : i2, (i6 & 4) != 0 ? 0 : i3, (i6 & 8) != 0 ? 0 : i4, (i6 & 16) == 0 ? i5 : 0, (i6 & 32) != 0 ? new BleMatchPeriod(0, 0, 0, 0, 0, 0, 0, 127, null) : bleMatchPeriod, (i6 & 64) != 0 ? new ArrayList() : list, (i6 & 128) != 0 ? new ArrayList() : list2);
    }

    public final int component1() {
        return this.mStart;
    }

    public final int component2() {
        return this.mType;
    }

    public final int component3() {
        return this.mPeriodListSize;
    }

    public final int component4() {
        return this.mLogListSize;
    }

    public final int component5() {
        return this.mUndefined;
    }

    @NotNull
    public final BleMatchPeriod component6() {
        return this.mPeriod;
    }

    @NotNull
    public final List<BleMatchPeriod> component7() {
        return this.mPeriodList;
    }

    @NotNull
    public final List<BleMatchLog> component8() {
        return this.mLogList;
    }

    @NotNull
    public final BleMatchRecord copy(int i, int i2, int i3, int i4, int i5, @NotNull BleMatchPeriod mPeriod, @NotNull List<BleMatchPeriod> mPeriodList, @NotNull List<BleMatchLog> mLogList) {
        Intrinsics.checkNotNullParameter(mPeriod, "mPeriod");
        Intrinsics.checkNotNullParameter(mPeriodList, "mPeriodList");
        Intrinsics.checkNotNullParameter(mLogList, "mLogList");
        return new BleMatchRecord(i, i2, i3, i4, i5, mPeriod, mPeriodList, mLogList);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        this.mStart = readInt32(LITTLE_ENDIAN);
        this.mType = readUInt8();
        this.mPeriodListSize = readUInt8();
        this.mLogListSize = readUInt8();
        this.mUndefined = readUInt8();
        BleReadable t = (BleReadable) BleMatchPeriod.class.newInstance();
        t.setMBytes(readBytes(12));
        t.decode();
        Intrinsics.checkNotNullExpressionValue(t, "t");
        this.mPeriod = (BleMatchPeriod) t;
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 9; i++) {
            BleReadable t2 = (BleReadable) BleMatchPeriod.class.newInstance();
            t2.setMBytes(readBytes(12));
            t2.decode();
            Intrinsics.checkNotNullExpressionValue(t2, "t");
            arrayList.add(t2);
        }
        this.mPeriodList = CollectionsKt___CollectionsKt.take(arrayList, this.mPeriodListSize);
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < 99; i2++) {
            BleReadable t3 = (BleReadable) BleMatchLog.class.newInstance();
            t3.setMBytes(readBytes(8));
            t3.decode();
            Intrinsics.checkNotNullExpressionValue(t3, "t");
            arrayList2.add(t3);
        }
        this.mLogList = CollectionsKt___CollectionsKt.take(arrayList2, this.mLogListSize);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchRecord) {
            BleMatchRecord bleMatchRecord = (BleMatchRecord) obj;
            return this.mStart == bleMatchRecord.mStart && this.mType == bleMatchRecord.mType && this.mPeriodListSize == bleMatchRecord.mPeriodListSize && this.mLogListSize == bleMatchRecord.mLogListSize && this.mUndefined == bleMatchRecord.mUndefined && Intrinsics.areEqual(this.mPeriod, bleMatchRecord.mPeriod) && Intrinsics.areEqual(this.mPeriodList, bleMatchRecord.mPeriodList) && Intrinsics.areEqual(this.mLogList, bleMatchRecord.mLogList);
        }
        return false;
    }

    @NotNull
    public final List<BleMatchLog> getMLogList() {
        return this.mLogList;
    }

    public final int getMLogListSize() {
        return this.mLogListSize;
    }

    @NotNull
    public final BleMatchPeriod getMPeriod() {
        return this.mPeriod;
    }

    @NotNull
    public final List<BleMatchPeriod> getMPeriodList() {
        return this.mPeriodList;
    }

    public final int getMPeriodListSize() {
        return this.mPeriodListSize;
    }

    public final int getMStart() {
        return this.mStart;
    }

    public final int getMType() {
        return this.mType;
    }

    public final int getMUndefined() {
        return this.mUndefined;
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.mStart) * 31) + Integer.hashCode(this.mType)) * 31) + Integer.hashCode(this.mPeriodListSize)) * 31) + Integer.hashCode(this.mLogListSize)) * 31) + Integer.hashCode(this.mUndefined)) * 31) + this.mPeriod.hashCode()) * 31) + this.mPeriodList.hashCode()) * 31) + this.mLogList.hashCode();
    }

    public final void setMLogList(@NotNull List<BleMatchLog> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mLogList = list;
    }

    public final void setMLogListSize(int i) {
        this.mLogListSize = i;
    }

    public final void setMPeriod(@NotNull BleMatchPeriod bleMatchPeriod) {
        Intrinsics.checkNotNullParameter(bleMatchPeriod, "<set-?>");
        this.mPeriod = bleMatchPeriod;
    }

    public final void setMPeriodList(@NotNull List<BleMatchPeriod> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mPeriodList = list;
    }

    public final void setMPeriodListSize(int i) {
        this.mPeriodListSize = i;
    }

    public final void setMStart(int i) {
        this.mStart = i;
    }

    public final void setMType(int i) {
        this.mType = i;
    }

    public final void setMUndefined(int i) {
        this.mUndefined = i;
    }

    @NotNull
    public String toString() {
        return "BleMatchRecord(mStart=" + this.mStart + ", mType=" + this.mType + ", mPeriodListSize=" + this.mPeriodListSize + ", mLogListSize=" + this.mLogListSize + ", mUndefined=" + this.mUndefined + ", mPeriod=" + this.mPeriod + ", mPeriodList=" + this.mPeriodList + ", mLogList=" + this.mLogList + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchRecord(int i, int i2, int i3, int i4, int i5, @NotNull BleMatchPeriod mPeriod, @NotNull List<BleMatchPeriod> mPeriodList, @NotNull List<BleMatchLog> mLogList) {
        Intrinsics.checkNotNullParameter(mPeriod, "mPeriod");
        Intrinsics.checkNotNullParameter(mPeriodList, "mPeriodList");
        Intrinsics.checkNotNullParameter(mLogList, "mLogList");
        this.mStart = i;
        this.mType = i2;
        this.mPeriodListSize = i3;
        this.mLogListSize = i4;
        this.mUndefined = i5;
        this.mPeriod = mPeriod;
        this.mPeriodList = mPeriodList;
        this.mLogList = mLogList;
    }
}
