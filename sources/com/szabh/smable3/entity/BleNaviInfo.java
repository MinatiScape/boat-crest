package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleNaviInfo extends BleWritable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int NAME_MAX_LENGTH = 232;
    public static final int NAVI_END = 4;
    public static final int NAVI_GOING = 1;
    public static final int NAVI_PAUSE = 2;
    public static final int NAVI_RESUME = 3;
    public static final int NAVI_START = 0;
    public static final int TURN_ALONG = 0;
    public static final int TURN_BACK = 1;
    public static final int TURN_BACK_2BRANCH_LEFT = 2;
    public static final int TURN_BACK_2BRANCH_RIGHT = 3;
    public static final int TURN_BACK_3BRANCH_CENTER = 4;
    public static final int TURN_BACK_3BRANCH_LEFT = 5;
    public static final int TURN_BACK_3BRANCH_RIGHT = 6;
    public static final int TURN_BRANCH_CENTER = 7;
    public static final int TURN_BRANCH_LEFT = 8;
    public static final int TURN_BRANCH_LEFT_STRAIGHT = 9;
    public static final int TURN_BRANCH_RIGHT = 10;
    public static final int TURN_BRANCH_RIGHT_STRAIGHT = 11;
    public static final int TURN_DEST = 12;
    public static final int TURN_FRONT = 13;
    public static final int TURN_INFERRY = 14;
    public static final int TURN_LB_2BRANCH_LEFT = 15;
    public static final int TURN_LB_2BRANCH_RIGHT = 16;
    public static final int TURN_LB_3BRANCH_CENTER = 17;
    public static final int TURN_LB_3BRANCH_LEFT = 18;
    public static final int TURN_LB_3BRANCH_RIGHT = 19;
    public static final int TURN_LEFT = 20;
    public static final int TURN_LEFT_BACK = 21;
    public static final int TURN_LEFT_FRONT = 22;
    public static final int TURN_LEFT_SIDE = 23;
    public static final int TURN_LEFT_SIDE_IC = 24;
    public static final int TURN_LEFT_SIDE_MAIN = 25;
    public static final int TURN_LF_2BRANCH_LEFT = 26;
    public static final int TURN_LF_2BRANCH_RIGHT = 27;
    public static final int TURN_RB_2BRANCH_LEFT = 28;
    public static final int TURN_RB_2BRANCH_RIGHT = 29;
    public static final int TURN_RB_3BRANCH_CENTER = 30;
    public static final int TURN_RB_3BRANCH_LEFT = 31;
    public static final int TURN_RB_3BRANCH_RIGHT = 32;
    public static final int TURN_RF_2BRANCH_LEFT = 33;
    public static final int TURN_RF_2BRANCH_RIGHT = 34;
    public static final int TURN_RIGHT = 35;
    public static final int TURN_RIGHT_BACK = 36;
    public static final int TURN_RIGHT_FRONT = 37;
    public static final int TURN_RIGHT_SIDE = 38;
    public static final int TURN_RIGHT_SIDE_IC = 39;
    public static final int TURN_RIGHT_SIDE_MAIN = 40;
    public static final int TURN_RING = 41;
    public static final int TURN_RING_FRONT = 42;
    public static final int TURN_RING_LEFT = 43;
    public static final int TURN_RING_LEFTBACK = 44;
    public static final int TURN_RING_LEFTFRONT = 45;
    public static final int TURN_RING_RIGHT = 46;
    public static final int TURN_RING_RIGHTBACK = 47;
    public static final int TURN_RING_RIGHTFRONT = 48;
    public static final int TURN_RING_TURNBACK = 49;
    private int mDistance;
    private int mRemainDistance;
    private int mRemainTime;
    @NotNull
    private String mRoadName;
    private int mSpeed;
    private int mState;
    private long mTime;
    private int mTurnType;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final int bdTurnIconNameToType(@NotNull String turnIconName) {
            Intrinsics.checkNotNullParameter(turnIconName, "turnIconName");
            switch (turnIconName.hashCode()) {
                case -2118620201:
                    return !turnIconName.equals("turn_back_3branch_right.png") ? 0 : 6;
                case -2042173033:
                    return !turnIconName.equals("turn_ring_front.png") ? 0 : 42;
                case -1890594296:
                    return !turnIconName.equals("turn_left_side.png") ? 0 : 23;
                case -1856743837:
                    return !turnIconName.equals("turn_rf_2branch_right.png") ? 0 : 34;
                case -1799532762:
                    return !turnIconName.equals("turn_left_side_main.png") ? 0 : 25;
                case -1736025363:
                    return !turnIconName.equals("turn_ring.png") ? 0 : 41;
                case -1655525354:
                    return !turnIconName.equals("turn_ring_leftback.png") ? 0 : 44;
                case -1549633778:
                    return !turnIconName.equals("turn_inferry.png") ? 0 : 14;
                case -1420513952:
                    return !turnIconName.equals("turn_rb_3branch_right.png") ? 0 : 32;
                case -1123731332:
                    return !turnIconName.equals("turn_branch_right.png") ? 0 : 10;
                case -1069071681:
                    return !turnIconName.equals("turn_right_front.png") ? 0 : 37;
                case -1039245827:
                    return !turnIconName.equals("turn_branch_left.png") ? 0 : 8;
                case -944844816:
                    return !turnIconName.equals("turn_ring_leftfront.png") ? 0 : 45;
                case -878361000:
                    return !turnIconName.equals("turn_right_side_ic.png") ? 0 : 39;
                case -753402194:
                    return !turnIconName.equals("turn_left_front.png") ? 0 : 22;
                case -569421649:
                    return !turnIconName.equals("turn_ring_left.png") ? 0 : 43;
                case -320878299:
                    return !turnIconName.equals("turn_lb_2branch_right.png") ? 0 : 16;
                case -99661865:
                    return !turnIconName.equals("turn_right_side.png") ? 0 : 38;
                case -78988135:
                    return !turnIconName.equals("turn_rb_3branch_left.png") ? 0 : 31;
                case 13218200:
                    return !turnIconName.equals("turn_left_back.png") ? 0 : 21;
                case 24534771:
                    return !turnIconName.equals("turn_branch_right_straight.png") ? 0 : 11;
                case 191541781:
                    return !turnIconName.equals("turn_right.png") ? 0 : 35;
                case 239499041:
                    return !turnIconName.equals("turn_lb_3branch_center.png") ? 0 : 17;
                case 255550837:
                    return !turnIconName.equals("turn_ring_rightfront.png") ? 0 : 48;
                case 295314103:
                    return !turnIconName.equals("turn_right_side_main.png") ? 0 : 40;
                case 431546042:
                    return !turnIconName.equals("turn_rb_2branch_left.png") ? 0 : 28;
                case 456824278:
                    turnIconName.equals("turn_along.png");
                    return 0;
                case 534537823:
                    return !turnIconName.equals("turn_dest.png") ? 0 : 12;
                case 555916298:
                    return !turnIconName.equals("turn_ring_right.png") ? 0 : 46;
                case 587315852:
                    return !turnIconName.equals("turn_ring_turnback.png") ? 0 : 49;
                case 596201513:
                    return !turnIconName.equals("turn_lf_2branch_right.png") ? 0 : 27;
                case 692875795:
                    return !turnIconName.equals("turn_lb_3branch_left.png") ? 0 : 18;
                case 738223926:
                    return !turnIconName.equals("turn_rf_2branch_left.png") ? 0 : 33;
                case 823037398:
                    return !turnIconName.equals("turn_back_2branch_right.png") ? 0 : 3;
                case 1032431398:
                    return !turnIconName.equals("turn_lb_3branch_right.png") ? 0 : 19;
                case 1154143729:
                    return !turnIconName.equals("turn_ring_rightback.png") ? 0 : 47;
                case 1203409972:
                    return !turnIconName.equals("turn_lb_2branch_left.png") ? 0 : 15;
                case 1219939652:
                    return !turnIconName.equals("turn_left.png") ? 0 : 20;
                case 1290328722:
                    return !turnIconName.equals("turn_branch_left_straight.png") ? 0 : 9;
                case 1341147280:
                    return !turnIconName.equals("turn_back_3branch_center.png") ? 0 : 4;
                case 1422512962:
                    return !turnIconName.equals("turn_back_3branch_left.png") ? 0 : 5;
                case 1507604519:
                    return !turnIconName.equals("turn_rb_3branch_center.png") ? 0 : 30;
                case 1510087856:
                    return !turnIconName.equals("turn_lf_2branch_left.png") ? 0 : 26;
                case 1521143647:
                    return !turnIconName.equals("turn_rb_2branch_right.png") ? 0 : 29;
                case 1622458916:
                    return !turnIconName.equals("turn_back.png") ? 0 : 1;
                case 1804150631:
                    return !turnIconName.equals("turn_right_back.png") ? 0 : 36;
                case 1832305287:
                    return !turnIconName.equals("turn_left_side_ic.png") ? 0 : 24;
                case 1888419746:
                    return !turnIconName.equals("turn_front.png") ? 0 : 13;
                case 1933047139:
                    return !turnIconName.equals("turn_back_2branch_left.png") ? 0 : 2;
                case 2117931147:
                    return !turnIconName.equals("turn_branch_center.png") ? 0 : 7;
                default:
                    return 0;
            }
        }
    }

    public BleNaviInfo() {
        this(0, 0L, 0, 0, 0, 0, 0, null, 255, null);
    }

    public /* synthetic */ BleNaviInfo(int i, long j, int i2, int i3, int i4, int i5, int i6, String str, int i7, DefaultConstructorMarker defaultConstructorMarker) {
        this((i7 & 1) != 0 ? 0 : i, (i7 & 2) != 0 ? 0L : j, (i7 & 4) != 0 ? 0 : i2, (i7 & 8) != 0 ? 0 : i3, (i7 & 16) != 0 ? 0 : i4, (i7 & 32) != 0 ? 0 : i5, (i7 & 64) == 0 ? i6 : 0, (i7 & 128) != 0 ? "" : str);
    }

    public final int component1() {
        return this.mState;
    }

    public final long component2() {
        return this.mTime;
    }

    public final int component3() {
        return this.mTurnType;
    }

    public final int component4() {
        return this.mRemainDistance;
    }

    public final int component5() {
        return this.mRemainTime;
    }

    public final int component6() {
        return this.mDistance;
    }

    public final int component7() {
        return this.mSpeed;
    }

    @NotNull
    public final String component8() {
        return this.mRoadName;
    }

    @NotNull
    public final BleNaviInfo copy(int i, long j, int i2, int i3, int i4, int i5, int i6, @NotNull String mRoadName) {
        Intrinsics.checkNotNullParameter(mRoadName, "mRoadName");
        return new BleNaviInfo(i, j, i2, i3, i4, i5, i6, mRoadName);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        this.mState = readInt8();
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mState);
        writeObject(BleTime.Companion.ofLocal(this.mTime));
        writeInt8(this.mTurnType);
        BleWritable.writeInt32$default(this, this.mRemainDistance, null, 2, null);
        BleWritable.writeInt32$default(this, this.mRemainTime, null, 2, null);
        BleWritable.writeInt32$default(this, this.mDistance, null, 2, null);
        writeInt8(this.mSpeed);
        writeInt8(0);
        byte[] bytes = this.mRoadName.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        BleWritable.writeInt16$default(this, Math.min(bytes.length, 232), null, 2, null);
        BleWritable.writeStringWithLimit$default(this, this.mRoadName, 232, null, true, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleNaviInfo) {
            BleNaviInfo bleNaviInfo = (BleNaviInfo) obj;
            return this.mState == bleNaviInfo.mState && this.mTime == bleNaviInfo.mTime && this.mTurnType == bleNaviInfo.mTurnType && this.mRemainDistance == bleNaviInfo.mRemainDistance && this.mRemainTime == bleNaviInfo.mRemainTime && this.mDistance == bleNaviInfo.mDistance && this.mSpeed == bleNaviInfo.mSpeed && Intrinsics.areEqual(this.mRoadName, bleNaviInfo.mRoadName);
        }
        return false;
    }

    public final int getMDistance() {
        return this.mDistance;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        byte[] bytes = this.mRoadName.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return Math.min(bytes.length, 232) + 24;
    }

    public final int getMRemainDistance() {
        return this.mRemainDistance;
    }

    public final int getMRemainTime() {
        return this.mRemainTime;
    }

    @NotNull
    public final String getMRoadName() {
        return this.mRoadName;
    }

    public final int getMSpeed() {
        return this.mSpeed;
    }

    public final int getMState() {
        return this.mState;
    }

    public final long getMTime() {
        return this.mTime;
    }

    public final int getMTurnType() {
        return this.mTurnType;
    }

    public int hashCode() {
        return (((((((((((((Integer.hashCode(this.mState) * 31) + Long.hashCode(this.mTime)) * 31) + Integer.hashCode(this.mTurnType)) * 31) + Integer.hashCode(this.mRemainDistance)) * 31) + Integer.hashCode(this.mRemainTime)) * 31) + Integer.hashCode(this.mDistance)) * 31) + Integer.hashCode(this.mSpeed)) * 31) + this.mRoadName.hashCode();
    }

    public final void setMDistance(int i) {
        this.mDistance = i;
    }

    public final void setMRemainDistance(int i) {
        this.mRemainDistance = i;
    }

    public final void setMRemainTime(int i) {
        this.mRemainTime = i;
    }

    public final void setMRoadName(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mRoadName = str;
    }

    public final void setMSpeed(int i) {
        this.mSpeed = i;
    }

    public final void setMState(int i) {
        this.mState = i;
    }

    public final void setMTime(long j) {
        this.mTime = j;
    }

    public final void setMTurnType(int i) {
        this.mTurnType = i;
    }

    @NotNull
    public String toString() {
        return "BleNaviInfo(mState=" + this.mState + ", mTime=" + this.mTime + ", mTurnType=" + this.mTurnType + ", mRemainDistance=" + this.mRemainDistance + ", mRemainTime=" + this.mRemainTime + ", mDistance=" + this.mDistance + ", mSpeed=" + this.mSpeed + ", mRoadName='" + this.mRoadName + "')";
    }

    public BleNaviInfo(int i, long j, int i2, int i3, int i4, int i5, int i6, @NotNull String mRoadName) {
        Intrinsics.checkNotNullParameter(mRoadName, "mRoadName");
        this.mState = i;
        this.mTime = j;
        this.mTurnType = i2;
        this.mRemainDistance = i3;
        this.mRemainTime = i4;
        this.mDistance = i5;
        this.mSpeed = i6;
        this.mRoadName = mRoadName;
    }
}
