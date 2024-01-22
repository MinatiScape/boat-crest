package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleReadable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleWatchFaceId extends BleReadable {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final long WATCHFACE_ID_INVALID = 4294967295L;
    @NotNull
    private List<Integer> mIdList;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleWatchFaceId() {
        this(null, 1, null);
    }

    public /* synthetic */ BleWatchFaceId(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ArrayList() : list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BleWatchFaceId copy$default(BleWatchFaceId bleWatchFaceId, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = bleWatchFaceId.mIdList;
        }
        return bleWatchFaceId.copy(list);
    }

    @NotNull
    public final List<Integer> component1() {
        return this.mIdList;
    }

    @NotNull
    public final BleWatchFaceId copy(@NotNull List<Integer> mIdList) {
        Intrinsics.checkNotNullParameter(mIdList, "mIdList");
        return new BleWatchFaceId(mIdList);
    }

    @Override // com.bestmafen.baseble.data.BleReadable
    public void decode() {
        int length;
        super.decode();
        byte[] mBytes = getMBytes();
        if (mBytes == null || (length = mBytes.length / 4) == 0) {
            return;
        }
        for (int i = 0; i < length; i++) {
            this.mIdList.add(Integer.valueOf(BleReadable.readInt32$default(this, null, 1, null)));
        }
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof BleWatchFaceId) && Intrinsics.areEqual(this.mIdList, ((BleWatchFaceId) obj).mIdList);
    }

    @NotNull
    public final List<Integer> getMIdList() {
        return this.mIdList;
    }

    public int hashCode() {
        return this.mIdList.hashCode();
    }

    public final void setMIdList(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mIdList = list;
    }

    @NotNull
    public String toString() {
        return "BleWatchFaceId(mIdList=" + this.mIdList + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleWatchFaceId(@NotNull List<Integer> mIdList) {
        Intrinsics.checkNotNullParameter(mIdList, "mIdList");
        this.mIdList = mIdList;
    }
}
