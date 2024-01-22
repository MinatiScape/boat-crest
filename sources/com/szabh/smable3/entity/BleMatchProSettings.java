package com.szabh.smable3.entity;

import android.text.TextUtils;
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
public final class BleMatchProSettings extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int NAME_LENGTH = 25;
    @NotNull
    private List<Integer> mBreakTime;
    private int mButtonType;
    @NotNull
    private List<String> mGoalTypes;
    private int mGoalTypesEnable;
    private int mGps;
    private int mGuestTeamColor;
    private int mHomeTeamColor;
    private int mMatchView;
    @NotNull
    private List<Integer> mPeriodTime;
    private int mPeriods;
    @NotNull
    private List<String> mRedCardTypes;
    private int mRedCardTypesEnable;
    @NotNull
    private List<String> mRefereeRole;
    private int mScreen;
    @NotNull
    private List<String> mTeamNames;
    private int mVibration;
    @NotNull
    private List<String> mYellowCardTypes;
    private int mYellowCardTypesEnable;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleMatchProSettings() {
        this(0, null, null, 0, 0, 0, 0, 0, 0, 0, null, null, null, null, null, 0, 0, 0, 262143, null);
    }

    public /* synthetic */ BleMatchProSettings(int i, List list, List list2, int i2, int i3, int i4, int i5, int i6, int i7, int i8, List list3, List list4, List list5, List list6, List list7, int i9, int i10, int i11, int i12, DefaultConstructorMarker defaultConstructorMarker) {
        this((i12 & 1) != 0 ? 0 : i, (i12 & 2) != 0 ? new ArrayList() : list, (i12 & 4) != 0 ? new ArrayList() : list2, (i12 & 8) != 0 ? 0 : i2, (i12 & 16) != 0 ? 0 : i3, (i12 & 32) != 0 ? 0 : i4, (i12 & 64) != 0 ? 0 : i5, (i12 & 128) != 0 ? 0 : i6, (i12 & 256) != 0 ? 0 : i7, (i12 & 512) != 0 ? 0 : i8, (i12 & 1024) != 0 ? new ArrayList() : list3, (i12 & 2048) != 0 ? new ArrayList() : list4, (i12 & 4096) != 0 ? new ArrayList() : list5, (i12 & 8192) != 0 ? new ArrayList() : list6, (i12 & 16384) != 0 ? new ArrayList() : list7, (i12 & 32768) != 0 ? 0 : i9, (i12 & 65536) != 0 ? 0 : i10, (i12 & 131072) != 0 ? 0 : i11);
    }

    public final int component1() {
        return this.mPeriods;
    }

    public final int component10() {
        return this.mMatchView;
    }

    @NotNull
    public final List<String> component11() {
        return this.mTeamNames;
    }

    @NotNull
    public final List<String> component12() {
        return this.mRefereeRole;
    }

    @NotNull
    public final List<String> component13() {
        return this.mGoalTypes;
    }

    @NotNull
    public final List<String> component14() {
        return this.mYellowCardTypes;
    }

    @NotNull
    public final List<String> component15() {
        return this.mRedCardTypes;
    }

    public final int component16() {
        return this.mGoalTypesEnable;
    }

    public final int component17() {
        return this.mYellowCardTypesEnable;
    }

    public final int component18() {
        return this.mRedCardTypesEnable;
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
        return this.mHomeTeamColor;
    }

    public final int component7() {
        return this.mGuestTeamColor;
    }

    public final int component8() {
        return this.mGps;
    }

    public final int component9() {
        return this.mScreen;
    }

    @NotNull
    public final BleMatchProSettings copy(int i, @NotNull List<Integer> mPeriodTime, @NotNull List<Integer> mBreakTime, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @NotNull List<String> mTeamNames, @NotNull List<String> mRefereeRole, @NotNull List<String> mGoalTypes, @NotNull List<String> mYellowCardTypes, @NotNull List<String> mRedCardTypes, int i9, int i10, int i11) {
        Intrinsics.checkNotNullParameter(mPeriodTime, "mPeriodTime");
        Intrinsics.checkNotNullParameter(mBreakTime, "mBreakTime");
        Intrinsics.checkNotNullParameter(mTeamNames, "mTeamNames");
        Intrinsics.checkNotNullParameter(mRefereeRole, "mRefereeRole");
        Intrinsics.checkNotNullParameter(mGoalTypes, "mGoalTypes");
        Intrinsics.checkNotNullParameter(mYellowCardTypes, "mYellowCardTypes");
        Intrinsics.checkNotNullParameter(mRedCardTypes, "mRedCardTypes");
        return new BleMatchProSettings(i, mPeriodTime, mBreakTime, i2, i3, i4, i5, i6, i7, i8, mTeamNames, mRefereeRole, mGoalTypes, mYellowCardTypes, mRedCardTypes, i9, i10, i11);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(2);
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
        writeInt8(this.mHomeTeamColor);
        writeInt8(this.mGuestTeamColor);
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
        int i5 = 0;
        while (i5 < 4) {
            List<String> list4 = this.mRefereeRole;
            BleWritable.writeStringWithFix$default(this, (i5 < 0 || i5 > CollectionsKt__CollectionsKt.getLastIndex(list4)) ? "" : list4.get(i5), 25, null, 4, null);
            i5++;
        }
        int i6 = 0;
        for (String str2 : this.mGoalTypes) {
            if (!TextUtils.isEmpty(str2)) {
                i6++;
            }
        }
        if (this.mGoalTypesEnable == 0) {
            i6 = 0;
        }
        writeInt8(i6);
        int i7 = 0;
        while (i7 < 8) {
            List<String> list5 = this.mGoalTypes;
            BleWritable.writeStringWithFix$default(this, (i7 < 0 || i7 > CollectionsKt__CollectionsKt.getLastIndex(list5)) ? "" : list5.get(i7), 25, null, 4, null);
            i7++;
        }
        int i8 = 0;
        for (String str3 : this.mYellowCardTypes) {
            if (!TextUtils.isEmpty(str3)) {
                i8++;
            }
        }
        if (this.mYellowCardTypesEnable == 0) {
            i8 = 0;
        }
        writeInt8(i8);
        int i9 = 0;
        while (i9 < 5) {
            List<String> list6 = this.mYellowCardTypes;
            BleWritable.writeStringWithFix$default(this, (i9 < 0 || i9 > CollectionsKt__CollectionsKt.getLastIndex(list6)) ? "" : list6.get(i9), 25, null, 4, null);
            i9++;
        }
        int i10 = 0;
        for (String str4 : this.mRedCardTypes) {
            if (!TextUtils.isEmpty(str4)) {
                i10++;
            }
        }
        if (this.mRedCardTypesEnable == 0) {
            i10 = 0;
        }
        writeInt8(i10);
        while (i < 5) {
            List<String> list7 = this.mRedCardTypes;
            BleWritable.writeStringWithFix$default(this, (i < 0 || i > CollectionsKt__CollectionsKt.getLastIndex(list7)) ? "" : list7.get(i), 25, null, 4, null);
            i++;
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchProSettings) {
            BleMatchProSettings bleMatchProSettings = (BleMatchProSettings) obj;
            return this.mPeriods == bleMatchProSettings.mPeriods && Intrinsics.areEqual(this.mPeriodTime, bleMatchProSettings.mPeriodTime) && Intrinsics.areEqual(this.mBreakTime, bleMatchProSettings.mBreakTime) && this.mVibration == bleMatchProSettings.mVibration && this.mButtonType == bleMatchProSettings.mButtonType && this.mHomeTeamColor == bleMatchProSettings.mHomeTeamColor && this.mGuestTeamColor == bleMatchProSettings.mGuestTeamColor && this.mGps == bleMatchProSettings.mGps && this.mScreen == bleMatchProSettings.mScreen && this.mMatchView == bleMatchProSettings.mMatchView && Intrinsics.areEqual(this.mTeamNames, bleMatchProSettings.mTeamNames) && Intrinsics.areEqual(this.mRefereeRole, bleMatchProSettings.mRefereeRole) && Intrinsics.areEqual(this.mGoalTypes, bleMatchProSettings.mGoalTypes) && Intrinsics.areEqual(this.mYellowCardTypes, bleMatchProSettings.mYellowCardTypes) && Intrinsics.areEqual(this.mRedCardTypes, bleMatchProSettings.mRedCardTypes) && this.mGoalTypesEnable == bleMatchProSettings.mGoalTypesEnable && this.mYellowCardTypesEnable == bleMatchProSettings.mYellowCardTypesEnable && this.mRedCardTypesEnable == bleMatchProSettings.mRedCardTypesEnable;
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

    @NotNull
    public final List<String> getMGoalTypes() {
        return this.mGoalTypes;
    }

    public final int getMGoalTypesEnable() {
        return this.mGoalTypesEnable;
    }

    public final int getMGps() {
        return this.mGps;
    }

    public final int getMGuestTeamColor() {
        return this.mGuestTeamColor;
    }

    public final int getMHomeTeamColor() {
        return this.mHomeTeamColor;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        return 646;
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
    public final List<String> getMRedCardTypes() {
        return this.mRedCardTypes;
    }

    public final int getMRedCardTypesEnable() {
        return this.mRedCardTypesEnable;
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

    @NotNull
    public final List<String> getMYellowCardTypes() {
        return this.mYellowCardTypes;
    }

    public final int getMYellowCardTypesEnable() {
        return this.mYellowCardTypesEnable;
    }

    public int hashCode() {
        return (((((((((((((((((((((((((((((((((Integer.hashCode(this.mPeriods) * 31) + this.mPeriodTime.hashCode()) * 31) + this.mBreakTime.hashCode()) * 31) + Integer.hashCode(this.mVibration)) * 31) + Integer.hashCode(this.mButtonType)) * 31) + Integer.hashCode(this.mHomeTeamColor)) * 31) + Integer.hashCode(this.mGuestTeamColor)) * 31) + Integer.hashCode(this.mGps)) * 31) + Integer.hashCode(this.mScreen)) * 31) + Integer.hashCode(this.mMatchView)) * 31) + this.mTeamNames.hashCode()) * 31) + this.mRefereeRole.hashCode()) * 31) + this.mGoalTypes.hashCode()) * 31) + this.mYellowCardTypes.hashCode()) * 31) + this.mRedCardTypes.hashCode()) * 31) + Integer.hashCode(this.mGoalTypesEnable)) * 31) + Integer.hashCode(this.mYellowCardTypesEnable)) * 31) + Integer.hashCode(this.mRedCardTypesEnable);
    }

    public final void setMBreakTime(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mBreakTime = list;
    }

    public final void setMButtonType(int i) {
        this.mButtonType = i;
    }

    public final void setMGoalTypes(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mGoalTypes = list;
    }

    public final void setMGoalTypesEnable(int i) {
        this.mGoalTypesEnable = i;
    }

    public final void setMGps(int i) {
        this.mGps = i;
    }

    public final void setMGuestTeamColor(int i) {
        this.mGuestTeamColor = i;
    }

    public final void setMHomeTeamColor(int i) {
        this.mHomeTeamColor = i;
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

    public final void setMRedCardTypes(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mRedCardTypes = list;
    }

    public final void setMRedCardTypesEnable(int i) {
        this.mRedCardTypesEnable = i;
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

    public final void setMYellowCardTypes(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mYellowCardTypes = list;
    }

    public final void setMYellowCardTypesEnable(int i) {
        this.mYellowCardTypesEnable = i;
    }

    @NotNull
    public String toString() {
        return "BleMatchProSettings(mPeriods=" + this.mPeriods + ", mPeriodTime=" + this.mPeriodTime + ", mBreakTime=" + this.mBreakTime + ", mVibration=" + this.mVibration + ", mButtonType=" + this.mButtonType + ", mHomeTeamColor=" + this.mHomeTeamColor + ", mGuestTeamColor=" + this.mGuestTeamColor + ", mGps=" + this.mGps + ", mScreen=" + this.mScreen + ", mMatchView=" + this.mMatchView + ", mTeamNames=" + this.mTeamNames + ", mRefereeRole=" + this.mRefereeRole + ", mGoalTypes=" + this.mGoalTypes + ", mYellowCardTypes=" + this.mYellowCardTypes + ", mRedCardTypes=" + this.mRedCardTypes + ", mGoalTypesEnable=" + this.mGoalTypesEnable + ", mYellowCardTypesEnable=" + this.mYellowCardTypesEnable + ", mRedCardTypesEnable=" + this.mRedCardTypesEnable + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchProSettings(int i, @NotNull List<Integer> mPeriodTime, @NotNull List<Integer> mBreakTime, int i2, int i3, int i4, int i5, int i6, int i7, int i8, @NotNull List<String> mTeamNames, @NotNull List<String> mRefereeRole, @NotNull List<String> mGoalTypes, @NotNull List<String> mYellowCardTypes, @NotNull List<String> mRedCardTypes, int i9, int i10, int i11) {
        Intrinsics.checkNotNullParameter(mPeriodTime, "mPeriodTime");
        Intrinsics.checkNotNullParameter(mBreakTime, "mBreakTime");
        Intrinsics.checkNotNullParameter(mTeamNames, "mTeamNames");
        Intrinsics.checkNotNullParameter(mRefereeRole, "mRefereeRole");
        Intrinsics.checkNotNullParameter(mGoalTypes, "mGoalTypes");
        Intrinsics.checkNotNullParameter(mYellowCardTypes, "mYellowCardTypes");
        Intrinsics.checkNotNullParameter(mRedCardTypes, "mRedCardTypes");
        this.mPeriods = i;
        this.mPeriodTime = mPeriodTime;
        this.mBreakTime = mBreakTime;
        this.mVibration = i2;
        this.mButtonType = i3;
        this.mHomeTeamColor = i4;
        this.mGuestTeamColor = i5;
        this.mGps = i6;
        this.mScreen = i7;
        this.mMatchView = i8;
        this.mTeamNames = mTeamNames;
        this.mRefereeRole = mRefereeRole;
        this.mGoalTypes = mGoalTypes;
        this.mYellowCardTypes = mYellowCardTypes;
        this.mRedCardTypes = mRedCardTypes;
        this.mGoalTypesEnable = i9;
        this.mYellowCardTypesEnable = i10;
        this.mRedCardTypesEnable = i11;
    }
}
