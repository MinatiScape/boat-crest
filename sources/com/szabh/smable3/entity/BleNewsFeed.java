package com.szabh.smable3.entity;

import com.bestmafen.baseble.data.BleWritable;
import java.nio.ByteOrder;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
/* loaded from: classes12.dex */
public final class BleNewsFeed extends BleWritable {
    public static final int CONTENT_MAX_LENGTH = 470;
    @NotNull
    public static final Companion Companion = new Companion(null);
    public static final int TITLE_LENGTH = 32;
    private int mCategory;
    @NotNull
    private String mContent;
    private long mTime;
    @NotNull
    private String mTitle;
    private int mUid;

    /* loaded from: classes12.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public BleNewsFeed() {
        this(0, 0, 0L, null, null, 31, null);
    }

    public /* synthetic */ BleNewsFeed(int i, int i2, long j, String str, String str2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) == 0 ? i2 : 0, (i3 & 4) != 0 ? 0L : j, (i3 & 8) != 0 ? "" : str, (i3 & 16) != 0 ? "" : str2);
    }

    public static /* synthetic */ BleNewsFeed copy$default(BleNewsFeed bleNewsFeed, int i, int i2, long j, String str, String str2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = bleNewsFeed.mCategory;
        }
        if ((i3 & 2) != 0) {
            i2 = bleNewsFeed.mUid;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            j = bleNewsFeed.mTime;
        }
        long j2 = j;
        if ((i3 & 8) != 0) {
            str = bleNewsFeed.mTitle;
        }
        String str3 = str;
        if ((i3 & 16) != 0) {
            str2 = bleNewsFeed.mContent;
        }
        return bleNewsFeed.copy(i, i4, j2, str3, str2);
    }

    public final int component1() {
        return this.mCategory;
    }

    public final int component2() {
        return this.mUid;
    }

    public final long component3() {
        return this.mTime;
    }

    @NotNull
    public final String component4() {
        return this.mTitle;
    }

    @NotNull
    public final String component5() {
        return this.mContent;
    }

    @NotNull
    public final BleNewsFeed copy(int i, int i2, long j, @NotNull String mTitle, @NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        return new BleNewsFeed(i, i2, j, mTitle, mContent);
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public void encode() {
        super.encode();
        writeInt8(this.mCategory);
        int i = this.mUid;
        ByteOrder LITTLE_ENDIAN = ByteOrder.LITTLE_ENDIAN;
        Intrinsics.checkNotNullExpressionValue(LITTLE_ENDIAN, "LITTLE_ENDIAN");
        writeInt24(i, LITTLE_ENDIAN);
        writeObject(BleTime.Companion.ofLocal(this.mTime));
        BleWritable.writeStringWithFix$default(this, this.mTitle, 32, null, 4, null);
        BleWritable.writeStringWithLimit$default(this, this.mContent, CONTENT_MAX_LENGTH, null, true, 4, null);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof BleNewsFeed) {
            BleNewsFeed bleNewsFeed = (BleNewsFeed) obj;
            return this.mCategory == bleNewsFeed.mCategory && this.mUid == bleNewsFeed.mUid && this.mTime == bleNewsFeed.mTime && Intrinsics.areEqual(this.mTitle, bleNewsFeed.mTitle) && Intrinsics.areEqual(this.mContent, bleNewsFeed.mContent);
        }
        return false;
    }

    public final int getMCategory() {
        return this.mCategory;
    }

    @NotNull
    public final String getMContent() {
        return this.mContent;
    }

    @Override // com.bestmafen.baseble.data.BleWritable
    public int getMLengthToWrite() {
        byte[] bytes = this.mContent.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return Math.min(bytes.length, (int) CONTENT_MAX_LENGTH) + 42;
    }

    public final long getMTime() {
        return this.mTime;
    }

    @NotNull
    public final String getMTitle() {
        return this.mTitle;
    }

    public final int getMUid() {
        return this.mUid;
    }

    public int hashCode() {
        return (((((((Integer.hashCode(this.mCategory) * 31) + Integer.hashCode(this.mUid)) * 31) + Long.hashCode(this.mTime)) * 31) + this.mTitle.hashCode()) * 31) + this.mContent.hashCode();
    }

    public final void setMCategory(int i) {
        this.mCategory = i;
    }

    public final void setMContent(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mContent = str;
    }

    public final void setMTime(long j) {
        this.mTime = j;
    }

    public final void setMTitle(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.mTitle = str;
    }

    public final void setMUid(int i) {
        this.mUid = i;
    }

    @NotNull
    public String toString() {
        return "BleNotification(mCategory=" + this.mCategory + ", mUid=" + this.mUid + ", mTime=" + this.mTime + ", mTitle='" + this.mTitle + "', mContent='" + this.mContent + "')";
    }

    public BleNewsFeed(int i, int i2, long j, @NotNull String mTitle, @NotNull String mContent) {
        Intrinsics.checkNotNullParameter(mTitle, "mTitle");
        Intrinsics.checkNotNullParameter(mContent, "mContent");
        this.mCategory = i;
        this.mUid = i2;
        this.mTime = j;
        this.mTitle = mTitle;
        this.mContent = mContent;
    }
}
