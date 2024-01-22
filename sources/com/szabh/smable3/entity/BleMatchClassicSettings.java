package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMatchClassicSettings extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int NAME_LENGTH = 25;
    @NotNull
    private List<Integer> mBreakTime;
    private int mButtonType;
    private int mGps;
    private int mMatchView;
    @NotNull
    private List<Integer> mPeriodTime;
    private int mPeriods;
    @NotNull
    private List<String> mRefereeRole;
    private int mScreen;
    @NotNull
    private List<String> mTeamNames;
    private int mVibration;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleMatchClassicSettings() {
        this(0, null, null, 0, 0, 0, 0, 0, null, null, 1023, null);
    }

    public /* synthetic */ BleMatchClassicSettings(int i, List list, List list2, int i2, int i3, int i4, int i5, int i6, List list3, List list4, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i, (i7 & 2) != 0 ? new ArrayList() : list, (i7 & 4) != 0 ? new ArrayList() : list2, (i7 & 8) != 0 ? 0 : i2, (i7 & 16) != 0 ? 0 : i3, (i7 & 32) != 0 ? 0 : i4, (i7 & 64) != 0 ? 0 : i5, (i7 & 128) == 0 ? i6 : 0, (i7 & 256) != 0 ? new ArrayList() : list3, (i7 & 512) != 0 ? new ArrayList() : list4);
    }

    public final int component1() {
        return this.mPeriods;
    }

    @NotNull
    public final List<String> component10() {
        return this.mRefereeRole;
    }

    @NotNull
    public final List<Integer> component2() {
        return this.mPeriodTime;
    }

    @NotNull
    public final List<Integer> component3() {
        return this.mBreakTime;
    }

    public final int component4() {
        return this.mVibration;
    }

    public final int component5() {
        return this.mButtonType;
    }

    public final int component6() {
        return this.mGps;
    }

    public final int component7() {
        return this.mScreen;
    }

    public final int component8() {
        return this.mMatchView;
    }

    @NotNull
    public final List<String> component9() {
        return this.mTeamNames;
    }

    @NotNull
    public final BleMatchClassicSettings copy(int i, @NotNull List<Integer> mPeriodTime, @NotNull List<Integer> mBreakTime, int i2, int i3, int i4, int i5, int i6, @NotNull List<String> mTeamNames, @NotNull List<String> mRefereeRole) {
        Intrinsics.checkNotNullParameter(mPeriodTime, "mPeriodTime");
        Intrinsics.checkNotNullParameter(mBreakTime, "mBreakTime");
        Intrinsics.checkNotNullParameter(mTeamNames, "mTeamNames");
        Intrinsics.checkNotNullParameter(mRefereeRole, "mRefereeRole");
        return new BleMatchClassicSettings(i, mPeriodTime, mBreakTime, i2, i3, i4, i5, i6, mTeamNames, mRefereeRole);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(1);
        writeInt8(this.mPeriods);
        int i = 0;
        int i2 = 0;
        while (i2 < 9) {
            List<Integer> list = this.mPeriodTime;
            BleWritable.writeInt16$default(this, ((i2 < 0 || i2 > CollectionsKt__CollectionsKt.getLastIndex(list)) ? 0 : list.get(i2)).intValue(), null, 2, null);
            i2++;
        }
        int i3 = 0;
        while (i3 < 8) {
            List<Integer> list2 = this.mBreakTime;
            BleWritable.writeInt16$default(this, ((i3 < 0 || i3 > CollectionsKt__CollectionsKt.getLastIndex(list2)) ? 0 : list2.get(i3)).intValue(), null, 2, null);
            i3++;
        }
        writeInt8(this.mVibration);
        writeInt8(this.mButtonType);
        writeInt8(this.mGps);
        writeInt8(this.mScreen);
        writeInt8(this.mMatchView);
        int i4 = 0;
        while (true) {
            String str = "";
            if (i4 >= 2) {
                break;
            }
            List<String> list3 = this.mTeamNames;
            if (i4 >= 0 && i4 <= CollectionsKt__CollectionsKt.getLastIndex(list3)) {
                str = list3.get(i4);
            }
            BleWritable.writeStringWithFix$default(this, str, 25, null, 4, null);
            i4++;
        }
        while (i < 4) {
            List<String> list4 = this.mRefereeRole;
            BleWritable.writeStringWithFix$default(this, (i < 0 || i > CollectionsKt__CollectionsKt.getLastIndex(list4)) ? "" : list4.get(i), 25, null, 4, null);
            i++;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchClassicSettings) {
            BleMatchClassicSettings bleMatchClassicSettings = (BleMatchClassicSettings) obj;
            return this.mPeriods == bleMatchClassicSettings.mPeriods && Intrinsics.areEqual(this.mPeriodTime, bleMatchClassicSettings.mPeriodTime) && Intrinsics.areEqual(this.mBreakTime, bleMatchClassicSettings.mBreakTime) && this.mVibration == bleMatchClassicSettings.mVibration && this.mButtonType == bleMatchClassicSettings.mButtonType && this.mGps == bleMatchClassicSettings.mGps && this.mScreen == bleMatchClassicSettings.mScreen && this.mMatchView == bleMatchClassicSettings.mMatchView && Intrinsics.areEqual(this.mTeamNames, bleMatchClassicSettings.mTeamNames) && Intrinsics.areEqual(this.mRefereeRole, bleMatchClassicSettings.mRefereeRole);
        }
        return false;
    }

    @NotNull
    public final List<Integer> getMBreakTime() {
        return this.mBreakTime;
    }

    public final int getMButtonType() {
        return this.mButtonType;
    }

    public final int getMGps() {
        return this.mGps;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 192;
    }

    public final int getMMatchView() {
        return this.mMatchView;
    }

    @NotNull
    public final List<Integer> getMPeriodTime() {
        return this.mPeriodTime;
    }

    public final int getMPeriods() {
        return this.mPeriods;
    }

    @NotNull
    public final List<String> getMRefereeRole() {
        return this.mRefereeRole;
    }

    public final int getMScreen() {
        return this.mScreen;
    }

    @NotNull
    public final List<String> getMTeamNames() {
        return this.mTeamNames;
    }

    public final int getMVibration() {
        return this.mVibration;
    }

    public int hashCode() {
        return (((((((((((((((((Integer.hashCode(this.mPeriods) * 31) + this.mPeriodTime.hashCode()) * 31) + this.mBreakTime.hashCode()) * 31) + Integer.hashCode(this.mVibration)) * 31) + Integer.hashCode(this.mButtonType)) * 31) + Integer.hashCode(this.mGps)) * 31) + Integer.hashCode(this.mScreen)) * 31) + Integer.hashCode(this.mMatchView)) * 31) + this.mTeamNames.hashCode()) * 31) + this.mRefereeRole.hashCode();
    }

    public final void setMBreakTime(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mBreakTime = list;
    }

    public final void setMButtonType(int i) {
        this.mButtonType = i;
    }

    public final void setMGps(int i) {
        this.mGps = i;
    }

    public final void setMMatchView(int i) {
        this.mMatchView = i;
    }

    public final void setMPeriodTime(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mPeriodTime = list;
    }

    public final void setMPeriods(int i) {
        this.mPeriods = i;
    }

    public final void setMRefereeRole(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mRefereeRole = list;
    }

    public final void setMScreen(int i) {
        this.mScreen = i;
    }

    public final void setMTeamNames(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mTeamNames = list;
    }

    public final void setMVibration(int i) {
        this.mVibration = i;
    }

    @NotNull
    public String toString() {
        return "BleMatchClassicSettings(mPeriods=" + this.mPeriods + ", mPeriodTime=" + this.mPeriodTime + ", mBreakTime=" + this.mBreakTime + ", mVibration=" + this.mVibration + ", mButtonType=" + this.mButtonType + ", mGps=" + this.mGps + ", mScreen=" + this.mScreen + ", mMatchView=" + this.mMatchView + ", mTeamNames=" + this.mTeamNames + ", mRefereeRole=" + this.mRefereeRole + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchClassicSettings(int i, @NotNull List<Integer> mPeriodTime, @NotNull List<Integer> mBreakTime, int i2, int i3, int i4, int i5, int i6, @NotNull List<String> mTeamNames, @NotNull List<String> mRefereeRole) {
        Intrinsics.checkNotNullParameter(mPeriodTime, "mPeriodTime");
        Intrinsics.checkNotNullParameter(mBreakTime, "mBreakTime");
        Intrinsics.checkNotNullParameter(mTeamNames, "mTeamNames");
        Intrinsics.checkNotNullParameter(mRefereeRole, "mRefereeRole");
        this.mPeriods = i;
        this.mPeriodTime = mPeriodTime;
        this.mBreakTime = mBreakTime;
        this.mVibration = i2;
        this.mButtonType = i3;
        this.mGps = i4;
        this.mScreen = i5;
        this.mMatchView = i6;
        this.mTeamNames = mTeamNames;
        this.mRefereeRole = mRefereeRole;
    }
}
