package com.szabh.smable3.entity;

import android.text.TextUtils;
import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleMatchRecordTeam extends BleReadable implements Serializable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 1908;
    public static final int NAME_LENGTH = 25;
    @NotNull
    private List<String> mGoalTypes;
    @NotNull
    private List<BleMatchRecordPlayer> mGuestPlayers;
    private int mGuestTeamColor;
    @NotNull
    private List<BleMatchRecordPlayer> mHomePlayers;
    private int mHomeTeamColor;
    @NotNull
    private List<String> mRedCardTypes;
    @NotNull
    private List<String> mRefereeRoles;
    @NotNull
    private List<String> mTeamNames;
    @NotNull
    private List<String> mYellowCardTypes;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleMatchRecordTeam() {
        this(null, 0, 0, null, null, null, null, null, null, 511, null);
    }

    public /* synthetic */ BleMatchRecordTeam(List list, int i, int i2, List list2, List list3, List list4, List list5, List list6, List list7, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? new ArrayList() : list, (i3 & 2) != 0 ? 0 : i, (i3 & 4) == 0 ? i2 : 0, (i3 & 8) != 0 ? new ArrayList() : list2, (i3 & 16) != 0 ? new ArrayList() : list3, (i3 & 32) != 0 ? new ArrayList() : list4, (i3 & 64) != 0 ? new ArrayList() : list5, (i3 & 128) != 0 ? new ArrayList() : list6, (i3 & 256) != 0 ? new ArrayList() : list7);
    }

    @NotNull
    public final List<String> component1() {
        return this.mTeamNames;
    }

    public final int component2() {
        return this.mHomeTeamColor;
    }

    public final int component3() {
        return this.mGuestTeamColor;
    }

    @NotNull
    public final List<String> component4() {
        return this.mRefereeRoles;
    }

    @NotNull
    public final List<BleMatchRecordPlayer> component5() {
        return this.mHomePlayers;
    }

    @NotNull
    public final List<BleMatchRecordPlayer> component6() {
        return this.mGuestPlayers;
    }

    @NotNull
    public final List<String> component7() {
        return this.mGoalTypes;
    }

    @NotNull
    public final List<String> component8() {
        return this.mYellowCardTypes;
    }

    @NotNull
    public final List<String> component9() {
        return this.mRedCardTypes;
    }

    @NotNull
    public final BleMatchRecordTeam copy(@NotNull List<String> mTeamNames, int i, int i2, @NotNull List<String> mRefereeRoles, @NotNull List<BleMatchRecordPlayer> mHomePlayers, @NotNull List<BleMatchRecordPlayer> mGuestPlayers, @NotNull List<String> mGoalTypes, @NotNull List<String> mYellowCardTypes, @NotNull List<String> mRedCardTypes) {
        Intrinsics.checkNotNullParameter(mTeamNames, "mTeamNames");
        Intrinsics.checkNotNullParameter(mRefereeRoles, "mRefereeRoles");
        Intrinsics.checkNotNullParameter(mHomePlayers, "mHomePlayers");
        Intrinsics.checkNotNullParameter(mGuestPlayers, "mGuestPlayers");
        Intrinsics.checkNotNullParameter(mGoalTypes, "mGoalTypes");
        Intrinsics.checkNotNullParameter(mYellowCardTypes, "mYellowCardTypes");
        Intrinsics.checkNotNullParameter(mRedCardTypes, "mRedCardTypes");
        return new BleMatchRecordTeam(mTeamNames, i, i2, mRefereeRoles, mHomePlayers, mGuestPlayers, mGoalTypes, mYellowCardTypes, mRedCardTypes);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        super.decode();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 2; i++) {
            String readString$default = BleReadable.readString$default(this, 25, null, 2, null);
            if (!TextUtils.isEmpty(readString$default)) {
                arrayList.add(readString$default);
            }
        }
        this.mTeamNames = arrayList;
        this.mHomeTeamColor = readInt8();
        this.mGuestTeamColor = readInt8();
        ArrayList arrayList2 = new ArrayList();
        for (int i2 = 0; i2 < 4; i2++) {
            String readString$default2 = BleReadable.readString$default(this, 25, null, 2, null);
            if (!TextUtils.isEmpty(readString$default2)) {
                arrayList2.add(readString$default2);
            }
        }
        this.mRefereeRoles = arrayList2;
        ArrayList arrayList3 = new ArrayList();
        for (int i3 = 0; i3 < 25; i3++) {
            BleReadable t = (BleReadable) BleMatchRecordPlayer.class.newInstance();
            t.setMBytes(readBytes(26));
            t.decode();
            Intrinsics.checkNotNullExpressionValue(t, "t");
            arrayList3.add(t);
        }
        ArrayList arrayList4 = new ArrayList();
        for (int i4 = 0; i4 < 25; i4++) {
            BleReadable t2 = (BleReadable) BleMatchRecordPlayer.class.newInstance();
            t2.setMBytes(readBytes(26));
            t2.decode();
            Intrinsics.checkNotNullExpressionValue(t2, "t");
            arrayList4.add(t2);
        }
        ArrayList arrayList5 = new ArrayList();
        for (int i5 = 0; i5 < 8; i5++) {
            arrayList5.add(BleReadable.readString$default(this, 25, null, 2, null));
        }
        ArrayList arrayList6 = new ArrayList();
        for (int i6 = 0; i6 < 5; i6++) {
            arrayList6.add(BleReadable.readString$default(this, 25, null, 2, null));
        }
        ArrayList arrayList7 = new ArrayList();
        for (int i7 = 0; i7 < 5; i7++) {
            arrayList7.add(BleReadable.readString$default(this, 25, null, 2, null));
        }
        this.mHomePlayers = CollectionsKt___CollectionsKt.take(arrayList3, readInt8());
        this.mGuestPlayers = CollectionsKt___CollectionsKt.take(arrayList4, readInt8());
        this.mGoalTypes = CollectionsKt___CollectionsKt.take(arrayList5, readInt8());
        this.mYellowCardTypes = CollectionsKt___CollectionsKt.take(arrayList6, readInt8());
        this.mRedCardTypes = CollectionsKt___CollectionsKt.take(arrayList7, readInt8());
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleMatchRecordTeam) {
            BleMatchRecordTeam bleMatchRecordTeam = (BleMatchRecordTeam) obj;
            return Intrinsics.areEqual(this.mTeamNames, bleMatchRecordTeam.mTeamNames) && this.mHomeTeamColor == bleMatchRecordTeam.mHomeTeamColor && this.mGuestTeamColor == bleMatchRecordTeam.mGuestTeamColor && Intrinsics.areEqual(this.mRefereeRoles, bleMatchRecordTeam.mRefereeRoles) && Intrinsics.areEqual(this.mHomePlayers, bleMatchRecordTeam.mHomePlayers) && Intrinsics.areEqual(this.mGuestPlayers, bleMatchRecordTeam.mGuestPlayers) && Intrinsics.areEqual(this.mGoalTypes, bleMatchRecordTeam.mGoalTypes) && Intrinsics.areEqual(this.mYellowCardTypes, bleMatchRecordTeam.mYellowCardTypes) && Intrinsics.areEqual(this.mRedCardTypes, bleMatchRecordTeam.mRedCardTypes);
        }
        return false;
    }

    @NotNull
    public final List<String> getMGoalTypes() {
        return this.mGoalTypes;
    }

    @NotNull
    public final List<BleMatchRecordPlayer> getMGuestPlayers() {
        return this.mGuestPlayers;
    }

    public final int getMGuestTeamColor() {
        return this.mGuestTeamColor;
    }

    @NotNull
    public final List<BleMatchRecordPlayer> getMHomePlayers() {
        return this.mHomePlayers;
    }

    public final int getMHomeTeamColor() {
        return this.mHomeTeamColor;
    }

    @NotNull
    public final List<String> getMRedCardTypes() {
        return this.mRedCardTypes;
    }

    @NotNull
    public final List<String> getMRefereeRoles() {
        return this.mRefereeRoles;
    }

    @NotNull
    public final List<String> getMTeamNames() {
        return this.mTeamNames;
    }

    @NotNull
    public final List<String> getMYellowCardTypes() {
        return this.mYellowCardTypes;
    }

    public int hashCode() {
        return (((((((((((((((this.mTeamNames.hashCode() * 31) + Integer.hashCode(this.mHomeTeamColor)) * 31) + Integer.hashCode(this.mGuestTeamColor)) * 31) + this.mRefereeRoles.hashCode()) * 31) + this.mHomePlayers.hashCode()) * 31) + this.mGuestPlayers.hashCode()) * 31) + this.mGoalTypes.hashCode()) * 31) + this.mYellowCardTypes.hashCode()) * 31) + this.mRedCardTypes.hashCode();
    }

    public final void setMGoalTypes(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mGoalTypes = list;
    }

    public final void setMGuestPlayers(@NotNull List<BleMatchRecordPlayer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mGuestPlayers = list;
    }

    public final void setMGuestTeamColor(int i) {
        this.mGuestTeamColor = i;
    }

    public final void setMHomePlayers(@NotNull List<BleMatchRecordPlayer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mHomePlayers = list;
    }

    public final void setMHomeTeamColor(int i) {
        this.mHomeTeamColor = i;
    }

    public final void setMRedCardTypes(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mRedCardTypes = list;
    }

    public final void setMRefereeRoles(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mRefereeRoles = list;
    }

    public final void setMTeamNames(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mTeamNames = list;
    }

    public final void setMYellowCardTypes(@NotNull List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mYellowCardTypes = list;
    }

    @NotNull
    public String toString() {
        return "BleMatchRecordTeam(mTeamNames=" + this.mTeamNames + ", mHomeTeamColor=" + this.mHomeTeamColor + ", mGuestTeamColor=" + this.mGuestTeamColor + ", mRefereeRoles=" + this.mRefereeRoles + ", mHomePlayers=" + this.mHomePlayers + ", mGuestPlayers=" + this.mGuestPlayers + ", mGoalTypes=" + this.mGoalTypes + ", mYellowCardTypes=" + this.mYellowCardTypes + ", mRedCardTypes=" + this.mRedCardTypes + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleMatchRecordTeam(@NotNull List<String> mTeamNames, int i, int i2, @NotNull List<String> mRefereeRoles, @NotNull List<BleMatchRecordPlayer> mHomePlayers, @NotNull List<BleMatchRecordPlayer> mGuestPlayers, @NotNull List<String> mGoalTypes, @NotNull List<String> mYellowCardTypes, @NotNull List<String> mRedCardTypes) {
        Intrinsics.checkNotNullParameter(mTeamNames, "mTeamNames");
        Intrinsics.checkNotNullParameter(mRefereeRoles, "mRefereeRoles");
        Intrinsics.checkNotNullParameter(mHomePlayers, "mHomePlayers");
        Intrinsics.checkNotNullParameter(mGuestPlayers, "mGuestPlayers");
        Intrinsics.checkNotNullParameter(mGoalTypes, "mGoalTypes");
        Intrinsics.checkNotNullParameter(mYellowCardTypes, "mYellowCardTypes");
        Intrinsics.checkNotNullParameter(mRedCardTypes, "mRedCardTypes");
        this.mTeamNames = mTeamNames;
        this.mHomeTeamColor = i;
        this.mGuestTeamColor = i2;
        this.mRefereeRoles = mRefereeRoles;
        this.mHomePlayers = mHomePlayers;
        this.mGuestPlayers = mGuestPlayers;
        this.mGoalTypes = mGoalTypes;
        this.mYellowCardTypes = mYellowCardTypes;
        this.mRedCardTypes = mRedCardTypes;
    }
}
