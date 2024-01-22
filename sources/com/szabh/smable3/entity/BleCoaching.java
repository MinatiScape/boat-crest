package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import com.goodix.ble.libcomx.util.HexStringBuilder;
import java.util.List;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleCoaching extends BleIdObject {
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int ITEM_LENGTH = 21;
    public static final int LENGTH_FIXED = 19;
    public static final int LENGTH_TITLE = 15;
    public static final int MAX_LENGTH_DESCRIPTION = 128;
    @NotNull
    private String mDescription;
    private int mRepeat;
    @NotNull
    private List<BleCoachingSegment> mSegments;
    @NotNull
    private String mTitle;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleCoaching() {
        this(null, null, 0, null, 15, null);
    }

    public /* synthetic */ BleCoaching(String str, String str2, int i, List list, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? "" : str, (i2 & 2) != 0 ? "" : str2, (i2 & 4) != 0 ? 1 : i, (i2 & 8) != 0 ? CollectionsKt__CollectionsKt.emptyList() : list);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ BleCoaching copy$default(BleCoaching bleCoaching, String str, String str2, int i, List list, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = bleCoaching.mTitle;
        }
        if ((i2 & 2) != 0) {
            str2 = bleCoaching.mDescription;
        }
        if ((i2 & 4) != 0) {
            i = bleCoaching.mRepeat;
        }
        if ((i2 & 8) != 0) {
            list = bleCoaching.mSegments;
        }
        return bleCoaching.copy(str, str2, i, list);
    }

    private final int getMDescriptionLength() {
        byte[] bytes = this.mDescription.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return Math.min(bytes.length, 128);
    }

    private final int getMSegmentsCount() {
        return this.mSegments.size();
    }

    @NotNull
    public final String component1() {
        return this.mTitle;
    }

    @NotNull
    public final String component2() {
        return this.mDescription;
    }

    public final int component3() {
        return this.mRepeat;
    }

    @NotNull
    public final List<BleCoachingSegment> component4() {
        return this.mSegments;
    }

    @NotNull
    public final BleCoaching copy(@NotNull String mTitle, @NotNull String mDescription, int i, @NotNull List<BleCoachingSegment> mSegments) {
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mDescription, "mDescription");
        Intrinsics.checkNotNullParameter(mSegments, "mSegments");
        return new BleCoaching(mTitle, mDescription, i, mSegments);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(getMId());
        BleWritable.writeStringWithFix$default(this, this.mTitle, 15, null, 4, null);
        writeInt8(getMDescriptionLength());
        BleWritable.writeStringWithFix$default(this, this.mDescription, getMDescriptionLength(), null, 4, null);
        writeInt8(this.mRepeat);
        writeInt8(getMSegmentsCount());
        writeList(this.mSegments);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleCoaching) {
            BleCoaching bleCoaching = (BleCoaching) obj;
            return Intrinsics.areEqual(this.mTitle, bleCoaching.mTitle) && Intrinsics.areEqual(this.mDescription, bleCoaching.mDescription) && this.mRepeat == bleCoaching.mRepeat && Intrinsics.areEqual(this.mSegments, bleCoaching.mSegments);
        }
        return false;
    }

    @NotNull
    public final String getMDescription() {
        return this.mDescription;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        int mDescriptionLength = getMDescriptionLength() + 19;
        int i = 0;
        for (BleCoachingSegment bleCoachingSegment : this.mSegments) {
            i += bleCoachingSegment.getMLengthToWrite();
        }
        return mDescriptionLength + i;
    }

    public final int getMRepeat() {
        return this.mRepeat;
    }

    @NotNull
    public final List<BleCoachingSegment> getMSegments() {
        return this.mSegments;
    }

    @NotNull
    public final String getMTitle() {
        return this.mTitle;
    }

    public int hashCode() {
        return (((((this.mTitle.hashCode() * 31) + this.mDescription.hashCode()) * 31) + Integer.hashCode(this.mRepeat)) * 31) + this.mSegments.hashCode();
    }

    public final void setMDescription(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mDescription = str;
    }

    public final void setMRepeat(int i) {
        this.mRepeat = i;
    }

    public final void setMSegments(@NotNull List<BleCoachingSegment> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mSegments = list;
    }

    public final void setMTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mTitle = str;
    }

    @NotNull
    public String toString() {
        return "BleCoaching(mId=" + getMId() + ", mTitle='" + this.mTitle + "', mDescription='" + this.mDescription + "', mRepeat=" + this.mRepeat + ", mSegments=" + this.mSegments + HexStringBuilder.COMMENT_END_CHAR;
    }

    public BleCoaching(@NotNull String mTitle, @NotNull String mDescription, int i, @NotNull List<BleCoachingSegment> mSegments) {
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mDescription, "mDescription");
        Intrinsics.checkNotNullParameter(mSegments, "mSegments");
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mRepeat = i;
        this.mSegments = mSegments;
    }
}
