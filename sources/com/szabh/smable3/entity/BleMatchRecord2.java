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
public final class BleMatchRecord2 extends BleReadable {
    public static final int CLASSIC = 1;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 3256;
    public static final int PROFILE = 2;
    public static final int TRAINING = 3;
    public static final int YOUTH = 0;
    private int mIsStopWatchData;
    @NotNull
    private List<BleMatchLog2> mLogList;
    private int mLogListSize;
    @NotNull
    private BleMatchPeriod2 mPeriod;
    @NotNull
    private List<BleMatchPeriod2> mPeriodList;
    private int mPeriodListSize;
    private int mStart;
    @NotNull
    private List<Integer> mStopWatchList;
    @NotNull
    private BleMatchRecordTeam mTeamInfo;
    private int mType;
    @NotNull
    private BleWeather mWeather;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleMatchRecord2() {
        this(0, 0, 0, 0, 0, null, null, null, null, null, null, 2047, null);
    }

    public /* synthetic */ BleMatchRecord2(int i, int i2, int i3, int i4, int i5, BleMatchPeriod2 bleMatchPeriod2, List list, List list2, List list3, BleMatchRecordTeam bleMatchRecordTeam, BleWeather bleWeather, int i6, DefaultConstructorMarker defaultConstructorMarker) {
        this((i6 & 1) != 0 ? 0 : i, (i6 & 2) != 0 ? 0 : i2, (i6 & 4) != 0 ? 0 : i3, (i6 & 8) != 0 ? 0 : i4, (i6 & 16) == 0 ? i5 : 0, (i6 & 32) != 0 ? new BleMatchPeriod2(0, 0, 0, 0, 0, 0, 0, 0, 255, null) : bleMatchPeriod2, (i6 & 64) != 0 ? new ArrayList() : list, (i6 & 128) != 0 ? new ArrayList() : list2, (i6 & 256) != 0 ? new ArrayList() : list3, (i6 & 512) != 0 ? new BleMatchRecordTeam(null, 0, 0, null, null, null, null, null, null, 511, null) : bleMatchRecordTeam, (i6 & 1024) != 0 ? new BleWeather(0, 0, 0, 0, 0, 0, 0, 0, 0, 511, null) : bleWeather);
    }

    public final int component1() {
        return this.mStart;
    }

    @NotNull
    public final BleMatchRecordTeam component10() {
        return this.mTeamInfo;
    }

    @NotNull
    public final BleWeather component11() {
        return this.mWeather;
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
        return this.mIsStopWatchData;
    }

    @NotNull
    public final BleMatchPeriod2 component6() {
        return this.mPeriod;
    }

    @NotNull
    public final List<BleMatchPeriod2> component7() {
        return this.mPeriodList;
    }

    @NotNull
    public final List<BleMatchLog2> component8() {
        return this.mLogList;
    }

    @NotNull
    public final List<Integer> component9() {
        return this.mStopWatchList;
    }

    @NotNull
    public final BleMatchRecord2 copy(int i, int i2, int i3, int i4, int i5, @NotNull BleMatchPeriod2 mPeriod, @NotNull List<BleMatchPeriod2> mPeriodList, @NotNull List<BleMatchLog2> mLogList, @NotNull List<Integer> mStopWatchList, @NotNull BleMatchRecordTeam mTeamInfo, @NotNull BleWeather mWeather) {
        Intrinsics.checkNotNullParameter(mPeriod, "mPeriod");
        Intrinsics.checkNotNullParameter(mPeriodList, "mPeriodList");
        Intrinsics.checkNotNullParameter(mLogList, "mLogList");
        Intrinsics.checkNotNullParameter(mStopWatchList, "mStopWatchList");
        Intrinsics.checkNotNullParameter(mTeamInfo, "mTeamInfo");
        Intrinsics.checkNotNullParameter(mWeather, "mWeather");
        return new BleMatchRecord2(i, i2, i3, i4, i5, mPeriod, mPeriodList, mLogList, mStopWatchList, mTeamInfo, mWeather);
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
        this.mIsStopWatchData = readUInt8();
        BleReadable t = (BleReadable) BleMatchPeriod2.class.newInstance();
        t.setMBytes(readBytes(14));
        t.decode();
        Intrinsics.checkNotNullExpressionValue(t, "t");
        this.mPeriod = (BleMatchPeriod2) t;
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < 9; i2++) {
            BleReadable t2 = (BleReadable) BleMatchPeriod2.class.newInstance();
            t2.setMBytes(readBytes(14));
            t2.decode();
            Intrinsics.checkNotNullExpressionValue(t2, "t");
            arrayList.add(t2);
        }
        this.mPeriodList = CollectionsKt___CollectionsKt.take(arrayList, this.mPeriodListSize);
        if (this.mIsStopWatchData == 1) {
            ArrayList arrayList2 = new ArrayList();
            while (i < 297) {
                ByteOrder LITTLE_ENDIAN2 = ByteOrder.LITTLE_ENDIAN;
                Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN2, "LITTLE_ENDIAN");
                arrayList2.add(Integer.valueOf(readInt32(LITTLE_ENDIAN2)));
                i++;
            }
            this.mStopWatchList = CollectionsKt___CollectionsKt.take(arrayList2, this.mLogListSize);
        } else {
            ArrayList arrayList3 = new ArrayList();
            while (i < 99) {
                BleReadable t3 = (BleReadable) BleMatchLog2.class.newInstance();
                t3.setMBytes(readBytes(12));
                t3.decode();
                Intrinsics.checkNotNullExpressionValue(t3, "t");
                arrayList3.add(t3);
                i++;
            }
            this.mLogList = CollectionsKt___CollectionsKt.take(arrayList3, this.mLogListSize);
        }
        BleReadable t4 = (BleReadable) BleMatchRecordTeam.class.newInstance();
        t4.setMBytes(readBytes(BleMatchRecordTeam.ITEM_LENGTH));
        t4.decode();
        Intrinsics.checkNotNullExpressionValue(t4, "t");
        this.mTeamInfo = (BleMatchRecordTeam) t4;
        BleReadable t5 = (BleReadable) BleWeather.class.newInstance();
        t5.setMBytes(readBytes(10));
        t5.decode();
        Intrinsics.checkNotNullExpressionValue(t5, "t");
        this.mWeather = (BleWeather) t5;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchRecord2) {
            BleMatchRecord2 bleMatchRecord2 = (BleMatchRecord2) obj;
            return this.mStart == bleMatchRecord2.mStart && this.mType == bleMatchRecord2.mType && this.mPeriodListSize == bleMatchRecord2.mPeriodListSize && this.mLogListSize == bleMatchRecord2.mLogListSize && this.mIsStopWatchData == bleMatchRecord2.mIsStopWatchData && Intrinsics.areEqual(this.mPeriod, bleMatchRecord2.mPeriod) && Intrinsics.areEqual(this.mPeriodList, bleMatchRecord2.mPeriodList) && Intrinsics.areEqual(this.mLogList, bleMatchRecord2.mLogList) && Intrinsics.areEqual(this.mStopWatchList, bleMatchRecord2.mStopWatchList) && Intrinsics.areEqual(this.mTeamInfo, bleMatchRecord2.mTeamInfo) && Intrinsics.areEqual(this.mWeather, bleMatchRecord2.mWeather);
        }
        return false;
    }

    public final int getMIsStopWatchData() {
        return this.mIsStopWatchData;
    }

    @NotNull
    public final List<BleMatchLog2> getMLogList() {
        return this.mLogList;
    }

    public final int getMLogListSize() {
        return this.mLogListSize;
    }

    @NotNull
    public final BleMatchPeriod2 getMPeriod() {
        return this.mPeriod;
    }

    @NotNull
    public final List<BleMatchPeriod2> getMPeriodList() {
        return this.mPeriodList;
    }

    public final int getMPeriodListSize() {
        return this.mPeriodListSize;
    }

    public final int getMStart() {
        return this.mStart;
    }

    @NotNull
    public final List<Integer> getMStopWatchList() {
        return this.mStopWatchList;
    }

    @NotNull
    public final BleMatchRecordTeam getMTeamInfo() {
        return this.mTeamInfo;
    }

    public final int getMType() {
        return this.mType;
    }

    @NotNull
    public final BleWeather getMWeather() {
        return this.mWeather;
    }

    public int hashCode() {
        return (((((((((((((((((((Integer.hashCode(this.mStart) * 31) + Integer.hashCode(this.mType)) * 31) + Integer.hashCode(this.mPeriodListSize)) * 31) + Integer.hashCode(this.mLogListSize)) * 31) + Integer.hashCode(this.mIsStopWatchData)) * 31) + this.mPeriod.hashCode()) * 31) + this.mPeriodList.hashCode()) * 31) + this.mLogList.hashCode()) * 31) + this.mStopWatchList.hashCode()) * 31) + this.mTeamInfo.hashCode()) * 31) + this.mWeather.hashCode();
    }

    public final void setMIsStopWatchData(int i) {
        this.mIsStopWatchData = i;
    }

    public final void setMLogList(@NotNull List<BleMatchLog2> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mLogList = list;
    }

    public final void setMLogListSize(int i) {
        this.mLogListSize = i;
    }

    public final void setMPeriod(@NotNull BleMatchPeriod2 bleMatchPeriod2) {
        Intrinsics.checkNotNullParameter(bleMatchPeriod2, "<set-?>");
        this.mPeriod = bleMatchPeriod2;
    }

    public final void setMPeriodList(@NotNull List<BleMatchPeriod2> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mPeriodList = list;
    }

    public final void setMPeriodListSize(int i) {
        this.mPeriodListSize = i;
    }

    public final void setMStart(int i) {
        this.mStart = i;
    }

    public final void setMStopWatchList(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mStopWatchList = list;
    }

    public final void setMTeamInfo(@NotNull BleMatchRecordTeam bleMatchRecordTeam) {
        Intrinsics.checkNotNullParameter(bleMatchRecordTeam, "<set-?>");
        this.mTeamInfo = bleMatchRecordTeam;
    }

    public final void setMType(int i) {
        this.mType = i;
    }

    public final void setMWeather(@NotNull BleWeather bleWeather) {
        Intrinsics.checkNotNullParameter(bleWeather, "<set-?>");
        this.mWeather = bleWeather;
    }

    @NotNull
    public String toString() {
        return "BleMatchRecord2(mStart=" + this.mStart + ", mType=" + this.mType + ", mPeriodListSize=" + this.mPeriodListSize + ", mLogListSize=" + this.mLogListSize + ", mIsStopWatchData=" + this.mIsStopWatchData + ", mPeriod=" + this.mPeriod + ", mPeriodList=" + this.mPeriodList + ", mLogList=" + this.mLogList + ", mStopWatchList=" + this.mStopWatchList + ", mTeamInfo=" + this.mTeamInfo + ", mWeather=" + this.mWeather + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchRecord2(int i, int i2, int i3, int i4, int i5, @NotNull BleMatchPeriod2 mPeriod, @NotNull List<BleMatchPeriod2> mPeriodList, @NotNull List<BleMatchLog2> mLogList, @NotNull List<Integer> mStopWatchList, @NotNull BleMatchRecordTeam mTeamInfo, @NotNull BleWeather mWeather) {
        Intrinsics.checkNotNullParameter(mPeriod, "mPeriod");
        Intrinsics.checkNotNullParameter(mPeriodList, "mPeriodList");
        Intrinsics.checkNotNullParameter(mLogList, "mLogList");
        Intrinsics.checkNotNullParameter(mStopWatchList, "mStopWatchList");
        Intrinsics.checkNotNullParameter(mTeamInfo, "mTeamInfo");
        Intrinsics.checkNotNullParameter(mWeather, "mWeather");
        this.mStart = i;
        this.mType = i2;
        this.mPeriodListSize = i3;
        this.mLogListSize = i4;
        this.mIsStopWatchData = i5;
        this.mPeriod = mPeriod;
        this.mPeriodList = mPeriodList;
        this.mLogList = mLogList;
        this.mStopWatchList = mStopWatchList;
        this.mTeamInfo = mTeamInfo;
        this.mWeather = mWeather;
    }
}
